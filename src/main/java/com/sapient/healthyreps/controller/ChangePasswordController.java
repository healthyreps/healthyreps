package com.sapient.healthyreps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.healthyreps.dao.UserDAO;
import com.sapient.healthyreps.entity.RequirementForChangePassword;

@CrossOrigin
@RestController
public class ChangePasswordController {

	@Autowired
	UserDAO dao;

	@PostMapping("/api/changePassword")
	public String userPasswordReset(@RequestBody RequirementForChangePassword passwordChangeRequest) {
		/*
		 * try { UserDAO.passwordCheck(passwordChangeRequest.getNewPassword()); } catch
		 * (PasswordTooSmall e) { e.printStackTrace(); return "PasswordTooSmall"; }
		 * catch (PasswordIsWeak e) { e.printStackTrace(); return "PasswordIsWeak"; }
		 */

		// List<User> user = dao.getUserId(passwordChangeRequest.getEmail());
		return dao.updatePassword(passwordChangeRequest.getEmail(), passwordChangeRequest.getNewPassword())
				? "Password Changed Successfully"
				: "Email is wrong";
	}
}
