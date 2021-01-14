package com.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.entity.Student;

public class MyRepositoryJDBC {
	
	public static Connection con = null;
	public MyRepositoryJDBC() {
		
		try{  
			Class.forName("com.mysql.jdbc.Driver");  
			con=DriverManager.getConnection(  
			"jdbc:mysql://localhost:3306/dbJPA","root","root");  
			}catch(Exception e){
				System.out.println(e);
			}  
	}

    public static Object findData(int stId) {
    	String query= "select * from student where sId ="+stId;
    	String name = null;
    	try {
	    	Statement stmt=con.createStatement();  
			ResultSet rs=stmt.executeQuery(query);  
			while(rs.next())  
			name  = rs.getString(2);
			con.close();
			    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return name;
    }
    
    public static Object persistData(Student stObj) {
    	String query= "insert into student values (?,?,?)";
    	try {
	    	PreparedStatement stmt=con.prepareStatement(query);
	    	stmt.setInt(1, stObj.getsId());
	    	stmt.setString(2, stObj.getName());
	    	stmt.setString(3, stObj.getPhn());
			stmt.executeUpdate();
			con.close();
			    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return stObj;
    }
    
    public static int updateData(int id) {
    	String query= "update student set name = ? where sId = ?";
    	try {
	    	PreparedStatement stmt=con.prepareStatement(query);
	    	
	    	stmt.setString(1, "Ashwini");
	    	stmt.setInt(2,id);
	    	stmt.executeUpdate();
			con.close();
			    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return id;
    }
    
    public static int removeData(int id) {
    	String query= "delete from student where sId = "+id;
    	try {
    		Statement stmt=con.createStatement();  
			stmt.executeUpdate(query);  
			con.close();
			    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	return id;
    }

    
}
