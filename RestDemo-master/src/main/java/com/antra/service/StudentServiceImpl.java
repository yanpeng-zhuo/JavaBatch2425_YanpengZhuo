package com.antra.service;

import com.antra.dao.UserRepository;
import com.antra.entity.StudentEntity;
import com.antra.util.StudentEntityConverter;
import com.antra.vo.PagedResponse;
import com.antra.vo.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service("studentService")
public class StudentServiceImpl implements StudentService {

	@Autowired
	private UserRepository userRepo;

	public List<Student> findAllStudents() {
		List<StudentEntity> students = userRepo.findAll();

		return students.stream().map(e -> new Student(e.getId(), e.getName(), e.getMajor(), e.getGrade()))
				.collect(Collectors.toList());
	}

	public Student findById(long id) {
		StudentEntity userEntity = userRepo.findById(id).orElse(null);
		return StudentEntityConverter.convertEntityToUser(userEntity);
	}

	@Transactional
	public Student saveStudent(Student student) {
		StudentEntity userEntity = userRepo.save(new StudentEntity(student.getId(), student.getName(), student.getMajor(), student.getGrade()));
		return StudentEntityConverter.convertEntityToUser(userEntity);
	}

	public Student updateStudent(Student student) {
		StudentEntity userEntity = userRepo.saveAndFlush(new StudentEntity(student.getId(), student.getName(), student.getMajor(), student.getGrade()));
		return StudentEntityConverter.convertEntityToUser(userEntity);
	}

	public void deleteStudentById(long id) {
		//userRepo.findAll(new PageRequest(1,2));
		userRepo.deleteById(id);
	}

	public PagedResponse<Student> findPaginated(int page, int size, String orderBy) {

		Sort sort = null;
		if (orderBy != null) {
			sort = Sort.by(Sort.Direction.ASC, orderBy);
		}
		Page<StudentEntity> page1 = userRepo.findAll(PageRequest.of(page, size, sort));
		List<StudentEntity> list = page1.getContent();
		PagedResponse<Student> result = new PagedResponse<>();
		result.setPage(page1.getNumber());
		result.setRows(page1.getSize());
		result.setTotalPage(page1.getTotalPages());
		result.setTotalElement(page1.getTotalElements());
		result.setOrder(page1.getSort().toString());
		result.setBody(list.stream().map(e -> new Student(e.getId(), e.getName(), e.getMajor(), e.getGrade()))
				.collect(Collectors.toList()));
		return result;
	}
}
