package com.tap;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/login")
public class login extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email=request.getParameter("email");
		String password=request.getParameter("password");
		
		    Connection con = null;
	        PreparedStatement statement = null;
	     
	    	
	    	String url="jdbc:mysql://localhost:3306/jee123";
	    	String name1="root";
	    	String pwd="necece@12345";
	    	
	    	try
	    	{
	    		Class.forName("com.mysql.cj.jdbc.Driver");
	    		con = DriverManager.getConnection(url,name1,pwd);
	    		
	    		String sql = "SELECT * FROM user WHERE email = ? AND password = ?";
	            statement = con.prepareStatement(sql);
	            statement.setString(1, email);
	            statement.setString(2, password);

	            ResultSet resultSet = statement.executeQuery();

	            if (resultSet.next()) {
	                PrintWriter pw = null;
					pw.println("welcome"+" "+resultSet.getString("name"));
	            } else {
	                response.sendRedirect("Register.html");
	            }
	    	}
	    	catch(Exception e)
	    	{
	    	  e.printStackTrace();	
	    	}
		
	}
	
}
