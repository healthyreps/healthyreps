package com.sapient.healthyreps.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.sapient.healthyreps.entity.UserBio;
import com.sapient.healthyreps.interfaces.IUserBioDAO;
import com.sapient.healthyreps.utils.DbConnect;

@Service
public class UserBioDAO implements IUserBioDAO {

	@Override
	public boolean insertuserToDB(UserBio u)

	{

		try {

			String q = "insert into user_bio values(?,?,?,?,?,?,?)";

			PreparedStatement pstmt = DbConnect.getMySQLConn().prepareStatement(q);

			pstmt.setInt(1, u.getWeight());
			pstmt.setInt(2, u.getHeight());
			pstmt.setString(3, u.getGender());
			pstmt.setInt(4, u.getWaist());
			pstmt.setInt(5, u.getWorkoutPlanInDays());
			pstmt.setInt(6, u.getReputation());
			pstmt.setInt(7, u.getUserId());

			return pstmt.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteuserToDB(int user_id) {
		try {

			String q = "delete from user_bio where user_id=?";

			PreparedStatement pstmt = DbConnect.getMySQLConn().prepareStatement(q);
			pstmt.setInt(1, user_id);

			return pstmt.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	@Override
	public UserBio getHtandWt(int user_id) {
		try {

			String q = "select height,weight from user_bio where user_id=?";

			PreparedStatement pstmt = DbConnect.getMySQLConn().prepareStatement(q);
			pstmt.setInt(1, user_id);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				UserBio us = new UserBio();
				us.setHeight(rs.getInt("height"));
				us.setWeight(rs.getInt("weight"));

				return us;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
