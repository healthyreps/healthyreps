package com.sapient.healthyreps.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sapient.healthyreps.entity.Question;
import com.sapient.healthyreps.interfaces.IQuestionDAO;
import com.sapient.healthyreps.utils.DbConnect;

@Service
public class QuestionDAO implements IQuestionDAO {

	@Override
	public int getquestionidoflastRow() {
		// TODO Auto-generated method stub\
		String sql = "select * from question";
		int question_id = 0;
		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = ps.executeQuery();
			rs.last();
			question_id = rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return question_id;
	}

	@Override
	public boolean insertQuestion(Question question) {

		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(dt);
		String sql = "insert into question(title,description,votes,modified_at,category_id,user_id,image_link,reliability) values(?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
//			ps.setInt(1, question.getquestionId());
			ps.setString(1, question.getTitle());
			ps.setString(2, question.getDescription());
			ps.setInt(3, question.getVotes());
			ps.setString(4, currentTime);
			ps.setInt(5, question.getCategoryID());
			ps.setInt(6, question.getUserId());
			ps.setString(7, question.getImageLink());
			ps.setInt(8, question.getReliability());

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public List<Question> getAllQuestion() {
		String sql = "select question_id,title,description,votes, modified_at,category_id,user_id,image_link,reliability from question order by modified_at DESC";

		List<Question> list;
		list = new ArrayList<Question>();
		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {

				Question question = new Question();
				question.setquestionId(rs.getInt(1));
				question.setTitle(rs.getString(2));
				question.setDescription(rs.getString(3));
				question.setVotes(rs.getInt(4));
				question.setModifiedAt(rs.getString(5));
				question.setCategoryID(rs.getInt(6));
				question.setUserId(rs.getInt(7));
				question.setImageLink(rs.getString(8));
				question.setReliability(rs.getInt(9));

				list.add(question);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Question getquestionfromquestionId(int questionidsearch) {
		// TODO Auto-generated method stub
		String sql = "select * from question where question_id=" + questionidsearch;
		Question question = new Question();
		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				question.setquestionId(rs.getInt(1));
				question.setTitle(rs.getString(2));
				question.setDescription(rs.getString(3));
				question.setVotes(rs.getInt(4));
				question.setModifiedAt(rs.getString(5));
				question.setCategoryID(rs.getInt(6));
				question.setUserId(rs.getInt(7));
				question.setImageLink(rs.getString(8));
				question.setReliability(rs.getInt(9));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return question;
	}

	@Override
	public List<Question> getallquestionsfromuserId(int useridsearch) {
		// TODO Auto-generated method stub
		String sql = "select * from question where user_id=" + useridsearch + " order by modified_at DESC";
		List<Question> list;
		list = new ArrayList<Question>();
		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Question question = new Question();
				question.setquestionId(rs.getInt(1));
				question.setTitle(rs.getString(2));
				question.setDescription(rs.getString(3));
				question.setVotes(rs.getInt(4));
				question.setModifiedAt(rs.getString(5));
				question.setCategoryID(rs.getInt(6));
				question.setUserId(rs.getInt(7));
				question.setImageLink(rs.getString(8));
				question.setReliability(rs.getInt(9));
				list.add(question);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

	@Override
	public List<Question> getallquestionsfromuseridbyvotesASC(int useridsearchasc, String ord) {
		// TODO Auto-generated method stub
		String sql = "select * from question where user_id=? order by votes " + ord + ", modified_at DESC";
		List<Question> list;
		list = new ArrayList<Question>();
		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1, useridsearchasc);
//			ps.setString(2, ord);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Question question = new Question();
				question.setquestionId(rs.getInt(1));
				question.setTitle(rs.getString(2));
				question.setDescription(rs.getString(3));
				question.setVotes(rs.getInt(4));
				question.setModifiedAt(rs.getString(5));
				question.setCategoryID(rs.getInt(6));
				question.setUserId(rs.getInt(7));
				question.setImageLink(rs.getString(8));
				question.setReliability(rs.getInt(9));
				list.add(question);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;

	}

//	@Override
//	public List<Question> getallquestionsfromuseridbyvotesDEC(int useridsearchdec) {
//		// TODO Auto-generated method stub
//		String sql = "select * from question where user_id=? order by votes " +ord+ ", modified_at DESC";
//		List<Question> list;
//		list = new ArrayList<Question>();
//		try {
//			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
//			ps.setInt(1, useridsearchdec);
//			ResultSet rs = ps.executeQuery();
//			while (rs.next()) {
//				Question question = new Question();
//				question.setquestionId(rs.getInt(1));
//				question.setTitle(rs.getString(2));
//				question.setDescription(rs.getString(3));
//				question.setVotes(rs.getInt(4));
//				question.setModifiedAt(rs.getString(5));
//				question.setCategoryID(rs.getInt(6));
//				question.setUserId(rs.getInt(7));
//				question.setImageLink(rs.getString(8));
//				question.setReliability(rs.getInt(9));
//				list.add(question);
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return list;
//	}

	@Override
	public List<Question> getallquestionsfromcategoryId(int categoryidsearch) {
		// TODO Auto-generated method stub
		String sql = "select * from sql6411086.question where sql6411086.question.category_id=" + categoryidsearch
				+ " order by modified_at DESC";
		List<Question> list;
		list = new ArrayList<Question>();
		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Question question = new Question();
				question.setquestionId(rs.getInt(1));
				question.setTitle(rs.getString(2));
				question.setDescription(rs.getString(3));
				question.setVotes(rs.getInt(4));
				question.setModifiedAt(rs.getString(5));
				question.setCategoryID(rs.getInt(6));
				question.setUserId(rs.getInt(7));
				question.setImageLink(rs.getString(8));
				question.setReliability(rs.getInt(9));
				list.add(question);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<Question> getallquestionsfromcategoryidorderbyvotesASC(int categoryidsearchasc, String ord) {
		// TODO Auto-generated method stub
		String sql = "select * from question where category_id=? order by votes " + ord + ", modified_at DESC";
		List<Question> list;
		list = new ArrayList<Question>();
		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1, categoryidsearchasc);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Question question = new Question();
				question.setquestionId(rs.getInt(1));
				question.setTitle(rs.getString(2));
				question.setDescription(rs.getString(3));
				question.setVotes(rs.getInt(4));
				question.setModifiedAt(rs.getString(5));
				question.setCategoryID(rs.getInt(6));
				question.setUserId(rs.getInt(7));
				question.setImageLink(rs.getString(8));
				question.setReliability(rs.getInt(9));
				list.add(question);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

//	@Override
//	public List<Question> getallquestionsfromcategoryidorderbyvotesDEC(int categoryidsearchdec) {
//		// TODO Auto-generated method stub
//		String sql = "select * from questions where category_id=? order by votes DESC , modified_at DESC";
//		List<Question> list;
//		list = new ArrayList<Question>();
//		try {
//			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
//			ps.setInt(1, categoryidsearchdec);
//			ResultSet rs = ps.executeQuery();
//			while (rs.next()) {
//				Question question = new Question();
//				question.setquestionId(rs.getInt(1));
//				question.setTitle(rs.getString(2));
//				question.setDescription(rs.getString(3));
//				question.setVotes(rs.getInt(4));
//				question.setModifiedAt(rs.getString(5));
//				question.setCategoryID(rs.getInt(6));
//				question.setUserId(rs.getInt(7));
//				question.setImageLink(rs.getString(8));
//				question.setReliability(rs.getInt(9));
//				list.add(question);
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return list;
//	}

	@Override
	public List<Question> getallquestionsfromcategoryidorderbyreputationASC(int categoryidsearchascr, String ord) {
		// TODO Auto-generated method stub
		String sql = "select question.* from question, user where question.category_id=? and question.user_id = User.user_id order by reputation "
				+ ord + ", modified_at desc";
		List<Question> list;
		list = new ArrayList<Question>();
		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1, categoryidsearchascr);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Question question = new Question();
				question.setquestionId(rs.getInt(1));
				question.setTitle(rs.getString(2));
				question.setDescription(rs.getString(3));
				question.setVotes(rs.getInt(4));
				question.setModifiedAt(rs.getString(5));
				question.setCategoryID(rs.getInt(6));
				question.setUserId(rs.getInt(7));
				question.setImageLink(rs.getString(8));
				question.setReliability(rs.getInt(9));
				list.add(question);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

//	@Override
//	public List<Question> getallquestionsfromcategoryidorderbyreputationDEC(int categoryidsearchdecr) {
//		// TODO Auto-generated method stub
//		String sql = "select questions.* from reps.questions, reps.sampleuser where questions.category_id=? and questions.user_id = sampleuser.user_id order by reputation DESC, modified_at desc";
//		List<Question> list;
//		list = new ArrayList<Question>();
//		try {
//			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
//			ps.setInt(1, categoryidsearchdecr);
//			ResultSet rs = ps.executeQuery();
//			while (rs.next()) {
//				Question question = new Question();
//				question.setquestionId(rs.getInt(1));
//				question.setTitle(rs.getString(2));
//				question.setDescription(rs.getString(3));
//				question.setVotes(rs.getInt(4));
//				question.setModifiedAt(rs.getString(5));
//				question.setCategoryID(rs.getInt(6));
//				question.setUserId(rs.getInt(7));
//				question.setImageLink(rs.getString(8));
//				question.setReliability(rs.getInt(9));
//				list.add(question);
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return list;
//	}

	@Override
	public boolean deletequestionsfromquestionid(int questioniddel) {
		// TODO Auto-generated method stub
		String sql = "Delete from question where question_id=?";
		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1, questioniddel);

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updatequestionfromquestionid(Question question, int question_id) {
		// TODO Auto-generated method stub
		Date dt = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String currentTime = sdf.format(dt);
		String sql = "UPDATE question SET title = ?, description = ?, modified_at=?, category_id=?, image_link=? , votes =? WHERE question_id=?";
		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setString(1, question.getTitle());
			ps.setString(2, question.getDescription());
			ps.setString(3, currentTime);
			ps.setInt(4, question.getCategoryID());
			ps.setString(5, question.getImageLink());
			ps.setInt(7, question_id);
			ps.setInt(6, question.getVotes());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
