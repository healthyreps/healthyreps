package com.sapient.healthyreps.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.healthyreps.dao.UserDAO;
import com.sapient.healthyreps.entity.User;
import com.sapient.healthyreps.entity.UserCredential;
import com.sapient.healthyreps.exception.InvalidId;
import com.sapient.healthyreps.interfaces.IUserDAO;

@RestController
public class UserRegisterController {

	IUserDAO dao = new UserDAO();

	@GetMapping("/api/AllRegisteredUsers")
	public List<User> getAllUser() {
		return dao.getAllUserRegisterInfo();
	}

	@PostMapping("/api/register")
	public String insertUser(@ModelAttribute UserCredential user) {
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