package com.sapient.healthyreps.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sapient.healthyreps.entity.Answer;
import com.sapient.healthyreps.interfaces.IAnswerDAO;
import com.sapient.healthyreps.utils.DbConnect;

@Service
public class AnswerDAO implements IAnswerDAO {

	@Override
	public boolean insertAnswer(Answer answer, int qid) {

		String sql = "insert into answer (description,votes,modified_at,question_id,user_id,reliability) values(?,?,?,?,?,?)";
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(dt);
		try {

			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setString(1, answer.getDescription());
			ps.setInt(2, 0);
			ps.setString(3, currentTime);
			ps.setInt(4, qid);
			ps.setInt(5, answer.getUserID());
			ps.setInt(6, 10);

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public Answer getAnswerByAnswerID(int answer_id) {

		String sql = "select answer_id,description,votes,modified_at,question_id,user_id,reliability from answer where answer_id=?";
		try {

			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1, answer_id);
			ResultSet rs = ps.executeQuery();

			rs.next();

			Answer answer = new Answer();
			answer.setAnswerID(rs.getInt(1));
			answer.setDescription(rs.getString(2));
			answer.setVotes(rs.getInt(3));
			answer.setModifiedAt(rs.getString(4));
			answer.setQuestionID(rs.getInt(5));
			answer.setUserID(rs.getInt(6));
			answer.setReliability(rs.getInt(7));

			return answer;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public List<Answer> getAllAnswersByQuestionID(int question_id, String order) {

		String sql = "select answer_id,description,votes,modified_at,question_id,user_id,reliability from answer where question_id= ? order by votes "
				+ order;

		List<Answer> list;
		list = new ArrayList<Answer>();
		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1, question_id);
//			ps.setString(2, order);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Answer answer = new Answer();
				answer.setAnswerID(rs.getInt(1));
				answer.setDescription(rs.getString(2));
				answer.setVotes(rs.getInt(3));
				answer.setModifiedAt(rs.getString(4));
				answer.setQuestionID(rs.getInt(5));
				answer.setUserID(rs.getInt(6));
				answer.setReliability(rs.getInt(7));

				list.add(answer);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	@Override
	public boolean deleteAnswer(int answer_id) {

		try {
			String sql = "DELETE from answer where answer_id= ? ";
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1, answer_id);

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

	@Override
	public boolean deleteAnswersByQuestionID(int question_id) {

		try {
			String sql = "delete from answer where question_id= ? ";
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1, question_id);

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}

	@Override
	public boolean updateAnswerByAnswerID(Answer answer, int aid) {

		String sql = "update answer set description=?,modified_at=? where answer_id=?";
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(dt);

		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setString(1, answer.getDescription());
//			ps.setInt(2, 0);
			ps.setString(2, currentTime);
//			ps.setInt(4, answer.getQuestionID());
//			ps.setInt(5, answer.getUserID());
//			ps.setInt(6, answer.getReliability());
			ps.setInt(3, aid);

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;

	}
}
