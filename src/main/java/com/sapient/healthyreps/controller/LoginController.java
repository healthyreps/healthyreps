package com.sapient.healthyreps.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.healthyreps.dao.UserDAO;
import com.sapient.healthyreps.entity.User;
import com.sapient.healthyreps.entity.UserLogin;

@RestController
public class LoginController {

	@Autowired
	UserDAO dao = new UserDAO();

	@PostMapping("/api/login")
	public String userLogin(@RequestBody UserLogin user) {

		String email = user.getUserEmail(), password = user.getPassword();
		List<User> userObtain = dao.getUserByEmailAndPwd(email, password);
		return userObtain.isEmpty() ? "Unsuccessful Login" : "Logged in Successfull";
	}
}