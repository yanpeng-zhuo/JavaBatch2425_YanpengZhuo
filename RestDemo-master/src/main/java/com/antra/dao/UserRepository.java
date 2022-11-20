package com.antra.dao;

import com.antra.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<StudentEntity, Long>{

    StudentEntity findByGradeBetween(int from, int to);

    StudentEntity findByGradeAndName(int grade, String name);
}
