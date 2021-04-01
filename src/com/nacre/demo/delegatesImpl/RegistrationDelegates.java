package com.nacre.demo.delegatesImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.nacre.demo.delegatesI.IRegistrationDelegates;
import com.nacre.demo.dto.RegistrationDto;
import com.nacre.demo.serviceI.IRegistrationService;
import com.nacre.demo.serviceImpl.RegistrationService;
import com.nacre.demo.vo.RegistrationVo;

public class RegistrationDelegates implements IRegistrationDelegates {

	@Override
	public boolean parse(RegistrationVo registrationVo) {
		int age = 0;
		double per = 0.0;
		char gender = ' ';
		Date utilDate = null;
		java.sql.Date sqlDate = null;
		RegistrationDto registrationDto = null;
		IRegistrationService iRegistrationService;
		boolean flag = false;
		
		//parsing the values according to the requirement by getting the values form Vo object
		age = Integer.parseInt(registrationVo.getAge());
		per = Double.parseDouble(registrationVo.getPer());
		gender = registrationVo.getGender().charAt(0);//taking only first char (i.e., f or m)
		
		//Date parsed(converted) into sql fromat(i.e., from string to java.util.date to java.util.sql)
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//parsing to required format using sdf
		try {
			System.out.println("Dob: "+registrationVo.getDob());
			utilDate = sdf.parse(registrationVo.getDob());
			//long date_ms = utilDate.getTime();
			//sqlDate = new java.sql.Date(date_ms);
			sqlDate = new java.sql.Date(utilDate.getTime());
			System.out.println("Util Date: "+utilDate);
			System.out.println("Sql Date: "+sqlDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		// After parsing set the values into dto object
		registrationDto = new RegistrationDto();
		registrationDto.setFname(registrationVo.getFname());
		registrationDto.setLname(registrationVo.getLname());
		registrationDto.setGender(""+gender);
		registrationDto.setDob(sqlDate);
		registrationDto.setAge(age);
		registrationDto.setPer(per);
		
		//send the dto object to the service class bussiness method as an argument(i.e., register())
		iRegistrationService = new RegistrationService();
		flag = iRegistrationService.register(registrationDto);
		//if flag is true go backs to the controller with success full response
		return flag;
	}

}
