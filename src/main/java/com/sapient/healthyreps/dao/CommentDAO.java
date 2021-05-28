package com.sapient.healthyreps.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sapient.healthyreps.entity.Comment;
import com.sapient.healthyreps.interfaces.ICommentDAO;
import com.sapient.healthyreps.utils.DbConnect;

@Service
public class CommentDAO implements ICommentDAO {

	@Override
	public boolean insertComment(Comment comment) {
		String sql = "insert into comment (description,modified_at,answer_id,user_id,reliability) values(?,?,?,?,?)";
		try {

			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setString(1, comment.getDescription());
			ps.setString(2, comment.getModifiedAt());
			ps.setInt(3, comment.getAnswerID());
			ps.setInt(4, comment.getUserID());
			ps.setInt(5, comment.getReliability());

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Comment getCommentByCommentID(int comment_id) {

		String sql = "select comment_id, description, modified_at, answer_id, user_id, reliability from comment where comment_id=?";
		try {

			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1, comment_id);
			ResultSet rs = ps.executeQuery();

			rs.next();

			Comment comment = new Comment();
			comment.setCommentID(rs.getInt(1));
			comment.setDescription(rs.getString(2));
			comment.setModifiedAt(rs.getString(3));
			comment.setAnswerID(rs.getInt(4));
			comment.setUserID(rs.getInt(5));
			comment.setReliability(rs.getInt(6));

			return comment;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public List<Comment> getAllComments(int answer_id) {

		String sql = "select comment_id, description, modified_at, answer_id, user_id, reliability from comment where answer_id= ?";

		List<Comment> list = new ArrayList<Comment>();
		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1, answer_id);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Comment comment = new Comment();
				comment.setCommentID(rs.getInt(1));
				comment.setDescription(rs.getString(2));
				comment.setModifiedAt(rs.getString(3));
				comment.setAnswerID(rs.getInt(4));
				comment.setUserID(rs.getInt(5));
				comment.setReliability(rs.getInt(6));

				list.add(comment);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public boolean deleteComment(int comment_id) {

		try {
			String sql = "delete from comment where comment_id= ? ";
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1, comment_id);

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean deleteCommentsByAnswerID(int answer_id) {

		try {
			String sql = "delete from comment where answer_id= ? ";
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1, answer_id);

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean updateCommentByCommentID(Comment comment) {

		String sql = "UPDATE comment SET description=?,modified_at=?,answer_id=?,user_id=?,reliability=? WHERE comment_id=?";

		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setString(1, comment.getDescription());
			ps.setString(2, comment.getModifiedAt());
			ps.setInt(3, comment.getAnswerID());
			ps.setInt(4, comment.getUserID());
			ps.setInt(5, comment.getReliability());
			ps.setInt(6, comment.getCommentID());

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean updateComment(Comment comment) {

		String sql = "update comment set description=?, modified_at=?, answer_id=?, user_id=?, reliability=? where comment_id = ?";

		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setString(1, comment.getDescription());
			ps.setString(2, comment.getModifiedAt());
			ps.setInt(3, comment.getAnswerID());
			ps.setInt(4, comment.getUserID());
			ps.setInt(5, comment.getReliability());
			ps.setInt(6, comment.getCommentID());

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}
}
