package com.antra.controller;

import com.antra.exception.StudentException;
import com.antra.exception.StudentNotFoundException;
import com.antra.service.StudentService;
import com.antra.util.Constants;
import com.antra.vo.ErrorResponse;
import com.antra.vo.PagedResponse;
import com.antra.vo.ResponseMessage;
import com.antra.vo.Student;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api")
@Api(value = "Student", description = "REST API for Students", tags={"Student"})
public class StudentRestController {

	private static Logger logger = LoggerFactory.getLogger(StudentRestController.class);

	StudentService studentService;

	Constants messages;

	@Autowired
	public StudentRestController(StudentService studentService,Constants messages) {
		this.studentService = studentService;
		this.messages = messages;
	}
	/**
	 * retrives single student
	 * 
	 **/
	@ApiOperation(value = "gets a single student")
	@RequestMapping(value = "/student/{uid}", method = RequestMethod.GET)
	public ResponseEntity<?> getStudent(@PathVariable("uid") long id) throws StudentException {
		Student user = studentService.findById(id);
		if (user == null) {
			throw new StudentNotFoundException(messages.getMessage("STUDENT_NOT_FOUND"));
		}
		return new ResponseEntity<Student>(user, HttpStatus.OK);
	}

	/**
	 *  Get student by using pagination, if no parameters are provided, the first page with 10 records will be returned
	 *
	 **/
	@ApiOperation(value = "get students accordingly")
	@RequestMapping(value = "/student",  method = RequestMethod.GET)
	public ResponseEntity<PagedResponse<Student>> getStudentPagenation(@RequestParam(required = false, defaultValue = "0") Integer pageNo,
														   @RequestParam(required = false, defaultValue = "5") Integer rows,
														   @RequestParam(required = false, defaultValue = "name") String orderBy) {

		PagedResponse<Student> users = studentService.findPaginated(pageNo, rows, orderBy);
		if (users.isEmpty()) {
			throw new StudentNotFoundException(messages.getMessage("STUDENT_NOT_FOUND"));
		}
		return new ResponseEntity<PagedResponse<Student>>(users, HttpStatus.OK);

	}

	/** create a student **/
	@ApiOperation(value = "create a student")
	@RequestMapping(value = "/student", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<ResponseMessage> createStudent(@Validated @RequestBody Student user, UriComponentsBuilder ucBuilder) {
		Student savedUser = studentService.saveStudent(user);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/user/{id}").buildAndExpand(user.getId()).toUri());
		return new ResponseEntity<ResponseMessage>(new ResponseMessage(messages.getMessage("USER_CREATED"),savedUser), headers, HttpStatus.CREATED);
	}

	/**
	 * update a student
	 * 
	 **/
	@ApiOperation(value = "update a student")
	@RequestMapping(value = "/student/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Student> updateStudent(@PathVariable("id") long id, @RequestBody Student student){
		Student currentStudent = studentService.findById(id);

		if (currentStudent == null) {
			throw new StudentNotFoundException(messages.getMessage("STUDENT_NOT_FOUND"));
		}

		currentStudent.setName(student.getName());
		currentStudent.setMajor(student.getMajor());
		currentStudent.setGrade(student.getGrade());

		studentService.updateStudent(currentStudent);
		return new ResponseEntity<Student>(currentStudent, HttpStatus.OK);
	}

	/**
	 * delete a student
	 * 
	 * @throws StudentException
	 **/
	@ApiOperation(value = "delete a student")
	@RequestMapping(value = "/student/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<ResponseMessage> deleteStudent(@PathVariable("id") long id) {

		Student student = studentService.findById(id);
		if (student == null) {
			throw new StudentNotFoundException(messages.getMessage("STUDENT_NOT_FOUND"));
		}
		studentService.deleteStudentById(id);
		return new ResponseEntity<ResponseMessage>(new ResponseMessage(messages.getMessage("STUDENT_DELETED"),student), HttpStatus.OK);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
		error.setMessage(ex.getMessage());
		logger.error("Controller Error",ex);
		return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<ErrorResponse> exceptionHandlerStudentNotFound(Exception ex) {
		logger.error("Cannot find student");
		ErrorResponse error = new ErrorResponse();
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
}
