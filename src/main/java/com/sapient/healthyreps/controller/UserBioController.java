package com.sapient.healthyreps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.healthyreps.dao.UserBioDAO;
import com.sapient.healthyreps.entity.UserBio;

@RestController
public class UserBioController {
	@Autowired
	UserBioDAO userBioDao;


	@PostMapping("/userBio")
	public boolean insertUser(@RequestBody UserBio u) {
		return userBioDao.insertuserToDB(u);
	}

	@DeleteMapping("/userBio/{user_id}")
	public boolean deleteUser(@PathVariable int user_id) {
		return userBioDao.deleteuserToDB(user_id);
	}
	
	@GetMapping("/userBio/{user_id}")
	public UserBio getHtandWt(@PathVariable int user_id) {
		return userBioDao.getHtandWt(user_id);

	}
    
}
