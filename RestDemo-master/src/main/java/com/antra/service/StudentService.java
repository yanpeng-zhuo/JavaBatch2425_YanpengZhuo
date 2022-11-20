package com.antra.service;

import java.util.List;

import com.antra.vo.PagedResponse;
import com.antra.vo.Student;

public interface StudentService {

		Student findById(long id);

		Student saveStudent(Student student);

		Student updateStudent(Student student);

		void deleteStudentById(long id);
	 
	    List<Student> findAllStudents();

		PagedResponse<Student> findPaginated(int page, int size, String orderBy);
	     
	    
}