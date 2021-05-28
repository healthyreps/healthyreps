package com.sapient.healthyreps.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sapient.healthyreps.entity.User;
import com.sapient.healthyreps.entity.UserCredential;
import com.sapient.healthyreps.exception.DuplicateEmail;
import com.sapient.healthyreps.exception.InvalidId;
import com.sapient.healthyreps.exception.PasswordIsWeak;
import com.sapient.healthyreps.exception.PasswordTooSmall;
import com.sapient.healthyreps.interfaces.IUserDAO;
import com.sapient.healthyreps.utils.DbConnect;

public class UserDAO implements IUserDAO {

	@Override
	public boolean insertUser(UserCredential user) {
		try {
			passwordCheck(user.getPassword());
			duplicateEmail(user.getUserEmail());
		} catch (DuplicateEmail e1) {
			e1.printStackTrace();
			return false;
		} catch (PasswordTooSmall e) {
			e.printStackTrace();
			return false;
		} catch (PasswordIsWeak e) {
			e.printStackTrace();
			return false;
		}

		String sql = "insert into user (user_name,email_id,password,is_admin,reputation) values (?,?,?,?,?)";
		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getUserEmail());
			ps.setString(3, user.getPassword());
			ps.setBoolean(4, false);
			ps.setInt(5, 0);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	public static void passwordCheck(String password) throws PasswordTooSmall, PasswordIsWeak {
		if (password.length() < 8)
			throw new PasswordTooSmall("PasswordTooSmall");
		List<Boolean> distinctValueIndicator = new ArrayList<>();
		distinctValueIndicator.add(false);
		distinctValueIndicator.add(false);
		distinctValueIndicator.add(false);
		distinctValueIndicator.add(false);
		for (int i = 0; i < password.length(); i++) {
			if (password.charAt(i) >= '0' && password.charAt(i) <= '9')
				distinctValueIndicator.set(0, true);
			else if (password.charAt(i) >= 'a' && password.charAt(i) <= 'z')
				distinctValueIndicator.set(1, true);
			else if (password.charAt(i) >= 'A' && password.charAt(i) <= 'Z')
				distinctValueIndicator.set(2, true);
			else
				distinctValueIndicator.set(3, true);
		}
		if (distinctValueIndicator.contains(false)) {
			throw new PasswordIsWeak("PaaswordIsWeek");
		}
	}

	private void duplicateEmail(String email_id) throws DuplicateEmail {
		String sql = "Select * from user where email_id=?";

		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setString(1, email_id);
			ResultSet rs = ps.executeQuery();

			if (rs.next())
				throw new DuplicateEmail("Email is Present");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public List<User> getAllUserRegisterInfo() {
		String sql = "select user_id,user_name,email_id,password from user";
		List<User> allUserInfo = new ArrayList<User>();
		try (PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql); ResultSet rs = ps.executeQuery();) {
			while (rs.next()) {
				User user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				allUserInfo.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allUserInfo;
	}

	@Override
	public List<User> getUser(int uid) {
		List<User> userList = new ArrayList<>();
		try {
			checkIdOfUser(uid);
		} catch (InvalidId e1) { // TODO Auto-generated
			e1.printStackTrace();
			return userList;
		}
		String sql = "select user_id,user_name,email_id,password,is_admin from user where user_id=?";
		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1, uid);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setEmailId(rs.getString(3));
				user.setPassword(rs.getString(4));
				user.setIsAdmin(rs.getBoolean(5));
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	public static void checkIdOfUser(int uid) throws InvalidId {
		String sql = "Select * from user where user_id=?";

		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1, uid);
			ResultSet rs = ps.executeQuery();
			if (!rs.next()) {
				throw new InvalidId("User");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<User> getUserByEmailAndPwd(String email, String Pwd) {
		String sql = "select * from user where email_id = ? And password= ? ";
		List<User> userList = new ArrayList<>();
		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setString(1, email);
			ps.setString(2, Pwd);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setEmailId(rs.getString(3));
				user.setPassword(rs.getString(4));
				user.setIsAdmin(rs.getBoolean(5));
				user.setReputation(rs.getInt(6));
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}

	@Override
	public Boolean updatePassword(int uid, String newPassword) {
		String sql = "update user set password = ?  where user_id = ? ";
		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setString(1, newPassword);
			ps.setInt(2, uid);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<User> getUserId(String email) {
		// TODO Auto-generated method stub
		String sql = "select * from user where email_id = ?";
		List<User> userList = new ArrayList<>();
		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setString(1, email);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				User user = new User();
				user.setUserId(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setEmailId(rs.getString(3));
				user.setPassword(rs.getString(4));
				user.setIsAdmin(rs.getBoolean(5));
				user.setReputation(rs.getInt(6));
				userList.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return userList;
	}
}