package com.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Component;

@Component("student")
public class Student1 {
	@NotEmpty(message="Age should be empty")
	private Integer age;
	@Size(min=3, max=30, message="Name should be string between 3 to 30") 
	private String name;
	@NotEmpty(message="Id should be empty")
	private Integer id;

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getAge() {
		return age;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getId() {
		return id;
	}
}
