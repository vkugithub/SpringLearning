package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.StudentDao;
import com.model.Student1;

@Service("studentService")
public class StudentServiceImpl {
	
	public StudentServiceImpl(){
		System.out.println(" StudentServiceImpl ");
	}

	@Autowired
	public StudentDao studentDao;
	
	public Student1 saveStudentDetails(Student1 student){
		student=studentDao.save(student);
		return student;
	}
}
