package com.nacre.demo.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nacre.demo.delegatesI.IRegistrationDelegates;
import com.nacre.demo.delegatesImpl.RegistrationDelegates;
import com.nacre.demo.vo.RegistrationVo;

@WebServlet("/registerController")
public class RegistrationController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String fname = null;
		String lname = null;
		String gender = null;
		String dob = null;
		String age = null;
		String per = null;
		
		RegistrationVo registrationVo = null;
		IRegistrationDelegates iRegistrationDelegates = null;
		boolean resp = false;
		
		PrintWriter out = response.getWriter();;
		
		//accepting the request parameters in string format and sending values to delegates through Vo object
		fname = request.getParameter("fName");
		lname = request.getParameter("lName");
		gender = request.getParameter("gender");
		dob = request.getParameter("dob");
		age = request.getParameter("age");
		per = request.getParameter("per");
		
		
		//creating VO object and setting the values into object to prevent the problem of data inconsistency 
		registrationVo = new RegistrationVo();
		registrationVo.setFname(fname);
		registrationVo.setLname(lname);
		registrationVo.setGender(gender);
		registrationVo.setDob(dob);
		registrationVo.setAge(age);
		registrationVo.setPer(per);
		
		//after setting values into Vo object pass the object to Delegates class parse method as an argument
		iRegistrationDelegates = new RegistrationDelegates();
		resp = iRegistrationDelegates.parse(registrationVo);
		
		if(resp) {
			out.println("<h1>SuccessFully Registered</h1>");
		}else {
			out.println("<h1>Failed to Insert</h1>");
		}
		
		
	}

}
