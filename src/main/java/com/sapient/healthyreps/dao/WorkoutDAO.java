package com.sapient.healthyreps.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.stereotype.Service;

import com.sapient.healthyreps.entity.Workout;
import com.sapient.healthyreps.interfaces.IWorkoutDAO;
import com.sapient.healthyreps.utils.DbConnect;

@Service
public class WorkoutDAO implements IWorkoutDAO {

	@Override
	public boolean insertWork(Workout workout) {
		String sql = "insert into work_out_time values(?,?,?,?,?,?,?)";
		try {

			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1, workout.getWorkoutId());
			ps.setInt(2, workout.getUserId());
			ps.setInt(3, workout.getDay());
			ps.setString(4, workout.getTime());
			ps.setInt(5, workout.getSets());
			ps.setInt(6, workout.getReps_per_set());
			ps.setString(7, workout.getDescription());

			return ps.executeUpdate() > 0; // DML statement
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean deleteWork(int userid) {
		String sql = "delete from work_out_time where user_id=?";
		try {

			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1, userid);
			return ps.executeUpdate() > 0; // DML statement

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public Workout getSetsReps(int userid) {

		String sql = "select sets, reps_set from work_out_time where user_id=?";
		try {

			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1, userid);
			ResultSet rs = ps.executeQuery();

			rs.next();

			Workout w = new Workout();
			w.setSets(rs.getInt(1));
			w.setReps_per_set(rs.getInt(2));
			return w;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean updateWork(Workout workout, int user_id) {
		String q = "update  work_out_time set day = ? ,time = ? ,sets = ? ,reps_set = ? , description = ? where user_id = ? ";
		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(q);

			ps.setInt(1, workout.getDay());
			ps.setString(2, workout.getTime());
			ps.setInt(3, workout.getSets());
			ps.setInt(4, workout.getReps_per_set());
			ps.setString(5, workout.getDescription());
			ps.setInt(6, workout.getUserId());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
