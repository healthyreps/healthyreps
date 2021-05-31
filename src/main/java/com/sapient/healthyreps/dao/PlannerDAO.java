package com.sapient.healthyreps.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.sapient.healthyreps.entity.Planner;
import com.sapient.healthyreps.interfaces.IPlannerDAO;
import com.sapient.healthyreps.utils.DbConnect;

@Service
public class PlannerDAO implements IPlannerDAO {
	@Override
	public boolean insertPlanner(Planner planner) {
		String q = "insert into planner values(?,?,?,?,?,?);";
		try {
			PreparedStatement prep = DbConnect.getMySQLConn().prepareStatement(q);
			prep.setInt(1, planner.getUserId());
			prep.setString(2, planner.getTiming());
			prep.setString(3, planner.getExercisePerform());
			prep.setString(4, planner.getTarget());
			prep.setString(5, planner.getDietGoal());
			prep.setString(6, planner.getDate());
			return prep.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deletePlanner(int user_id) {
		String sql = "delete from planner where user_id=?";
		try {

			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1, user_id);
			return ps.executeUpdate() > 0; // DML statement

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public Planner getPlanner(int user_id) {

		String sql = "select * from planner where user_id=?";
		try {

			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1, user_id);
			ResultSet rs = ps.executeQuery();

			rs.next();

			Planner pl = new Planner();
			pl.setUserId(rs.getInt(1));
			pl.setTiming(rs.getString(2));
			pl.setExercisePerform(rs.getString(3));
			pl.setTarget(rs.getString(4));
			pl.setTarget(rs.getString(4));
			pl.setDietGoal(rs.getString(5));
			pl.setDate(rs.getString(6));

			return pl;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateExercise_perform(String exercise_perform, int user_id) {
		String q = "update planner set exercise_perform=?" + " where user_id = ?;";
		try {
			PreparedStatement prep = DbConnect.getMySQLConn().prepareStatement(q);
			prep.setInt(2, user_id);
			prep.setString(1, exercise_perform);
			return prep.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateDiet_goal(String diet_goal, int user_id) {
		String q = "update planner set diet_goal=?" + " where user_id = ?;";
		try {
			PreparedStatement prep = DbConnect.getMySQLConn().prepareStatement(q);
			prep.setInt(2, user_id);
			prep.setString(1, diet_goal);
			return prep.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
