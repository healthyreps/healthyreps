package com.sapient.healthyreps.controller;

import java.util.List;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.healthyreps.dao.UserDAO;
import com.sapient.healthyreps.entity.User;
import com.sapient.healthyreps.entity.UserLogin;
import com.sapient.healthyreps.interfaces.IUserDAO;

@RestController
public class LoginController {
	IUserDAO dao = new UserDAO();

	@PostMapping("/api/login")
	public String userLogin(@ModelAttribute UserLogin user) {

		String email = user.getUserEmail(), password = user.getPassword();
		List<User> userObtain = dao.getUserByEmailAndPwd(email, password);
		return userObtain.isEmpty() ? "Unsuccessful Login" : "Logged in Successfull";
	}
}