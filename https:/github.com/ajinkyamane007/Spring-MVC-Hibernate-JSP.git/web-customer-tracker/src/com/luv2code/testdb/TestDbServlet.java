package com.luv2code.testdb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.*;

/**
 * Servlet implementation class TestDbServlet
 */
@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// setup connection variables
		String user="ajstudent";
		String password="ajstudent";
		
		String jdbcUrl="jdbc:mysql://localhost:3306/web_customer_tracker?userSSl=false";
		String driver="com.mysql.jdbc.Driver";
		
		// Get connectin to Database
		try 
		{
			PrintWriter out = response.getWriter();
			out.println("Connecting to database :" + jdbcUrl);
			
			Class.forName(driver);
			Connection mycon=DriverManager.getConnection(jdbcUrl, user, password);
			
			out.println("Success !!!");
			mycon.close();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			throw new ServletException();
		}
		
	}

}
