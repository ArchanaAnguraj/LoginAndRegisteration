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


@WebServlet("/register")
public class register extends HttpServlet {

    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String cpassword= request.getParameter("cpassword");
        String address = request.getParameter("address");
        String phoneNumber = request.getParameter("PhoneNumber");

        Connection con = null;
        PreparedStatement statement = null;
     
    	
    	String url="jdbc:mysql://localhost:3306/jee123";
    	String name1="root";
    	String pwd="necece@12345";
    	
    
        if(password.equals(cpassword)) {
        try {
        	Class.forName("com.mysql.cj.jdbc.Driver");
    		con = DriverManager.getConnection(url,name1,pwd);
        	
        	String sql = "INSERT INTO user (username, email, password, address, phoneNumber) VALUES (?, ?, ?, ?, ?)";
            statement = con.prepareStatement(sql); 
            statement.setString(1, username);
            statement.setString(2, email);
            statement.setString(3, password);
            statement.setString(4, address);
            statement.setString(5, phoneNumber);

            int result1 = statement.executeUpdate();

            if (result1 > 0) {
                
                response.sendRedirect("login.html");
            } else {
                
                response.getWriter().println("Registration failed, please try again.");
            }

        } catch (SQLException e) {
           
            e.printStackTrace();
            response.getWriter().println("An error occurred during registration: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().println("An unexpected error occurred: " + e.getMessage());
        }
        
    }
        else
        {
        	PrintWriter pw = null;
			pw.println("password does not match please try again!!!!");
        }
    }
}