package com.antra.vo;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
	public class Student{
	
	private Long id;

	@NotNull
    private String name;
	@Max(100)
	@Min(10)
	private String major;

	private Integer grade;

	public Student() {

	}
	public Student(Long id, String name, String major, Integer grade) {
		this.id = id;
		this.name = name;
		this.major = major;
		this.grade = grade;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMajor() {
		return major;
	}

	public void setMajor(String major) {
		this.major = major;
	}

	public Integer getGrade() {
		return grade;
	}

	public void setGrade(Integer grade) {
		this.grade = grade;
	}
}
