package com.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DBConnectionConfig {

	
	

	@Bean
	public Connection getConnection(){
		Connection con=null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver"); 			  
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","exercise","exercise");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
		return con;
	}
}
