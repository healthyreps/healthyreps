package com.sapient.healthyreps.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.sapient.healthyreps.entity.Post;
import com.sapient.healthyreps.exception.InvalidId;
import com.sapient.healthyreps.interfaces.IPostDAO;
import com.sapient.healthyreps.utils.DbConnect;

public class PostDAO implements IPostDAO {

	@Override
	public boolean insertPost(int postId, int userId, String title, int categoryId, String content, int votes,
			Timestamp timeStamp, int reported) {
		String sql = "INSERT INTO post(post_id, user_id, title, category_id, content, votes, time_stamp, reported) VALUES(?,?,?,?,?,?,?,?)";

		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1, postId);
			ps.setInt(2, userId);
			ps.setString(3, title);
			ps.setInt(4, categoryId);
			ps.setString(5, content);
			ps.setInt(6, votes);
			ps.setTimestamp(7, timeStamp);
			ps.setInt(8, reported);

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean insertPost(int userId, String title, int categoryId, String content, int votes, Timestamp timeStamp,
			int reported) {

		String sql = "INSERT INTO post(user_id, title, category_id, content, votes, time_stamp, reported) VALUES(?,?,?,?,?,?,?)";

		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
//			ps.setInt(1, pid);  taken care by 'autoincrement' property in DB
			ps.setInt(1, userId);
			ps.setString(2, title);
			ps.setInt(3, categoryId);
			ps.setString(4, content);
			ps.setInt(5, votes);
			ps.setTimestamp(6, timeStamp);
			ps.setInt(7, reported);

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	// Added today
	public boolean insertPost(Post post) {
		String sql = "INSERT INTO post(user_id, title, category_id, content, votes, time_stamp, reported) VALUES(?,?,?,?,?,?,?)";

		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);

			ps.setInt(1, post.getUid());
			ps.setString(2, post.getTitle());
			ps.setInt(3, post.getCategoryId());
			ps.setString(4, post.getContent());
			ps.setInt(5, post.getVotes());
			ps.setTimestamp(6, post.getTimeStamp());
			ps.setInt(7, post.getReported());

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public List<Post> getAllPosts() {

		List<Post> posts = new ArrayList<>();
		String sql = "select * from post";

		try (PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql); ResultSet rs = ps.executeQuery();) {
			while (rs.next()) {
				Post post = new Post(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5),
						rs.getInt(6), rs.getTimestamp(7), rs.getInt(8));

				posts.add(post);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return posts;
	}

	@Override
	public Post getPostbyId(int postId) {
		try {
			checkPostId(postId);
		} catch (InvalidId e) {
			e.printStackTrace();
			return null;
		}

		String sql = "select * from post where post_id = ?";

		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1, postId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Post post = new Post(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5),
						rs.getInt(6), rs.getTimestamp(7), rs.getInt(8));

				return post;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public boolean deletePostbyId(int postId) {

		try {
			checkPostId(postId);
		} catch (InvalidId e) {
			e.printStackTrace();
			return false;
		}

		String sql = "DELETE FROM post WHERE post_id = ?";
		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1, postId);

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updatePost(int postId, String title, String content, int categoryId, int votes, Timestamp timeStamp,
			int reported) {

		String sql = "UPDATE post SET title=?, content=?, category_id=?, votes=?, time_stamp=?, reported=? WHERE post_id=?";

		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setString(1, title);
			ps.setString(2, content);
			ps.setInt(3, categoryId);
			ps.setInt(4, votes);
			ps.setTimestamp(5, timeStamp);
			ps.setInt(6, reported);
			ps.setInt(7, postId);

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public boolean updatePost(Post post) {
		String sql = "UPDATE post SET title=?, content=?, category_id=?, votes=?, time_stamp=?, reported=? WHERE post_id=?";

		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);

			ps.setString(1, post.getTitle());
			ps.setString(2, post.getContent());
			ps.setInt(3, post.getCategoryId());
			ps.setInt(4, post.getVotes());
			ps.setTimestamp(5, post.getTimeStamp());
			ps.setInt(6, post.getReported());
			ps.setInt(7, post.getPid());

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean updateVoteCountbyId(int postId, int votes) {

		try {
			checkPostId(postId);
		} catch (InvalidId e1) {
			e1.printStackTrace();
			return false;
		}

		String sql = "UPDATE post SET votes=? WHERE post_id=?";

		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1, votes);
			ps.setInt(2, postId);

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Post> getAllPostbyUserId(int userId) {
		List<Post> posts = new ArrayList<>();
		String statement = "select * from post where user_id = ?";
		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(statement);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Post post = new Post(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5),
						rs.getInt(6), rs.getTimestamp(7), rs.getInt(8));

				posts.add(post);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return posts;
	}

	@Override
	public List<Post> getAllVisiblePosts() {
		List<Post> posts = new ArrayList<>();
		String statement = "select * from post where reported >= 100";
		try (PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(statement);
				ResultSet rs = ps.executeQuery();) {
			while (rs.next()) {
				Post post = new Post(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5),
						rs.getInt(6), rs.getTimestamp(7), rs.getInt(8));

				posts.add(post);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return posts;
	}

	@Override
	public boolean updateStatusofPost(int postId, int newstatus) {

		try {
			checkPostId(postId);
		} catch (InvalidId e1) {
			e1.printStackTrace();
			return false;
		}

		String sql = "UPDATE post SET reported = ? where post_id = ?";

		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1, newstatus);
			ps.setInt(2, postId);

			return ps.executeUpdate() > 0;

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Post> getAllDraftPosts(int userId) {
		List<Post> posts = new ArrayList<>();
		String statement = "select * from post where user_id = ? and reported = -100";
		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(statement);
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Post post = new Post(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5),
						rs.getInt(6), rs.getTimestamp(7), rs.getInt(8));

				posts.add(post);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return posts;
	}

	@Override
	public List<Post> getAllReportedPosts() {
		List<Post> posts = new ArrayList<>();
		String statement = "select * from post where reported < 0 and reported != -100";
		try (PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(statement);
				ResultSet rs = ps.executeQuery();) {
			while (rs.next()) {
				Post post = new Post(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5),
						rs.getInt(6), rs.getTimestamp(7), rs.getInt(8));

				posts.add(post);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return posts;
	}

	@Override
	public List<Post> getAllPostsByCategoryId(int categoryId) {
		List<Post> posts = new ArrayList<>();
		String sql = "select postId, user_id, title, category_id, content, votes, time_stamp, reported from post where category_id= ?";

		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1, categoryId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Post post = new Post(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4), rs.getString(5),
						rs.getInt(6), rs.getTimestamp(7), rs.getInt(8));

				posts.add(post);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return posts;
	}

	private void checkPostId(int postId) throws InvalidId {
		// TODO Auto-generated method stub

		String sqlForException = "SELECT * FROM post WHERE post_id = ?";
		try {
			PreparedStatement psException = DbConnect.getMySQLConn().prepareStatement(sqlForException);
			psException.setInt(1, postId);
			ResultSet rs = psException.executeQuery();
			if (!rs.next()) {
				throw new InvalidId("post");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
