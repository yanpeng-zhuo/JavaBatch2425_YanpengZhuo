package com.antra.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="student")
public class StudentEntity {

	public StudentEntity() {
		
	}
	public StudentEntity(Long id, String name, String major, Integer grade) {
		super();
		this.id = id;
		this.name = name;
		this.major = major;
		this.grade = grade;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;
    
	@Column(name = "name")
    private String name;
	
    @Column(name = "major")
    private String major;
    
    @Column(name = "grade")
    private Integer grade;

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

	public void setGrade(Integer Grade) {
		this.grade = grade;
	}

	
}
