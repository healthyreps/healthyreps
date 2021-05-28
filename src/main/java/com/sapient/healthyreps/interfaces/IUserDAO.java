package com.sapient.healthyreps.interfaces;

import java.util.List;

import com.sapient.healthyreps.entity.User;
import com.sapient.healthyreps.entity.UserCredential;

public interface IUserDAO {

	public boolean insertUser(UserCredential user);

	public List<User> getAllUserRegisterInfo();

	public List<User> getUser(int uid);

	public List<User> getUserByEmailAndPwd(String email, String Pwd);

	public Boolean updatePassword(String email, String newPassword);

	public List<User> getUserId(String email);

}