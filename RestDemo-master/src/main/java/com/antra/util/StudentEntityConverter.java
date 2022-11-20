package com.antra.util;

import com.antra.entity.StudentEntity;
import com.antra.vo.Student;

public class StudentEntityConverter {
    public static Student convertEntityToUser(StudentEntity studentEntity){
        if (studentEntity != null) {
            Student user = new Student();
            user.setId(studentEntity.getId());
            user.setName(studentEntity.getName());
            user.setMajor(studentEntity.getMajor());
            user.setGrade(studentEntity.getGrade());
            return user;
        } else {
            return null;
        }
    }
}
