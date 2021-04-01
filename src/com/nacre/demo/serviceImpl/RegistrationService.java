package com.nacre.demo.serviceImpl;

import com.nacre.demo.bo.RegistrationBo;
import com.nacre.demo.daoImpl.RegistrationDao;
import com.nacre.demo.dto.RegistrationDto;
import com.nacre.demo.serviceI.IRegistrationService;

public class RegistrationService implements IRegistrationService {

	@Override
	public boolean register(RegistrationDto registrationDto) {
		RegistrationBo registrationBo = null;
		RegistrationDao registrationDao = null;
		boolean flag = false;
		if(registrationDto.getAge() >= 18) {
			//if the age is greater than or equal to 18 then set the values into Bo Object
			registrationBo = new RegistrationBo();
			registrationBo.setFname(registrationDto.getFname());
			registrationBo.setLname(registrationDto.getLname());
			registrationBo.setGender(registrationDto.getGender());
			registrationBo.setDob(registrationDto.getDob());
			registrationBo.setAge(registrationDto.getAge());
			registrationBo.setPer(registrationDto.getPer());
			
			//pass the Bo object to Dao class Curd Operation methods as an argument
			registrationDao = new RegistrationDao();
			
			//if values are updated into database the count method returns greater than 0
			int count = registrationDao.insert(registrationBo);
			
			//if count > 0 then return true and goes back delegates
			if(count > 0) {
				return true;
			}
		}
		return false;
	}

}
