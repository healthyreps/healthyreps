package com.sapient.healthyreps.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.healthyreps.dao.UserDAO;
import com.sapient.healthyreps.entity.User;
import com.sapient.healthyreps.entity.UserCredential;
import com.sapient.healthyreps.exception.InvalidId;

@RestController
public class UserRegisterController {

	@Autowired
	UserDAO dao = new UserDAO();

	@GetMapping("/api/AllRegisteredUsers")
	public List<User> getAllUser() {
		return dao.getAllUserRegisterInfo();
	}

	@PostMapping("/api/register")
	public String insertUser(@RequestBody UserCredential user) {
		return dao.insertUser(user) ? "Inserted" : "Not-Inserted";
	}

	@GetMapping("/api/register/{uId}")
	public User getUser(@PathVariable("uId") int uid) {
		try {
			UserDAO.checkIdOfUser(uid);
		} catch (InvalidId e) {
			e.printStackTrace();
			return null;
		}
		User user = ((dao.getUser(uid)).get(0));
		return user;
	}
}