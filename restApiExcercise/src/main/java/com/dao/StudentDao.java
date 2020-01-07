package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.model.Student;
import com.model.Student1;



@Repository("studentDao")
public class StudentDao {
	
	@Autowired
	@Qualifier("getConnection")
	Connection connection;

	public Student1 save(Student1 student) {
		// TODO Auto-generated method stub
		try {
			PreparedStatement preapredStatement=connection.prepareStatement("insert into student (student_id,student_name,age) values(?,?,?)");
			preapredStatement.setString(1, student.getId().toString());
			preapredStatement.setString(2, student.getName());
			preapredStatement.setInt(3, student.getAge());
//			preapredStatement.setDate(4, new java.sql.Date(20151212));
			preapredStatement.executeUpdate();
			preapredStatement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch(Exception ex){
			ex.printStackTrace();
		}
		System.out.println(" Student Object saved");
		return student;
	}

}
