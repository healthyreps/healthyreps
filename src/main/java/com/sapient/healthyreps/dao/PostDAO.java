package com.sapient.healthyreps.dao;

import java.sql.*;
import java.util.*;

import com.sapient.healthyreps.entity.post;
import com.sapient.healthyreps.exception.InvalidId;
import com.sapient.healthyreps.interfaces.IPostDAO;
import com.sapient.healthyreps.utils.DbConnect;



public class PostDAO implements IPostDAO{

	
	@Override
	public boolean insertpost(int postId, int userId, String title, int categoryId, String content, int votes,
			Timestamp timestamp, int reported) {
		String sql = "insert into post(postId, userId, title, categoryId, content, votes, timestamp, reported) values(?,?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1, postId);  
			ps.setInt(2, userId);
			ps.setString(3, title);
			ps.setInt(4, categoryId);
			ps.setString(5, content);
			ps.setInt(6, votes);
			ps.setTimestamp(7, timestamp);
			ps.setInt(8, reported);
			
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return false;
	}
	
	@Override
	public boolean insertpost(int userId, String title, int categoryId, String content, int votes, Timestamp timestamp, int reported) {
		
		
		String sql = "insert into post(userId, title, categoryId, content, votes, timestamp, reported) values(?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
//			ps.setInt(1, postId);  taken care by 'autoincrement' property in DB
			ps.setInt(1, userId);
			ps.setString(2, title);
			ps.setInt(3, categoryId);
			ps.setString(4, content);
			ps.setInt(5, votes);
			ps.setTimestamp(6, timestamp);
			ps.setInt(7, reported);
			
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return false;
	}
	
	//Added today
	public boolean insertpost(post post) {
		String sql = "insert into post(userId, title, categoryId, content, votes, timestamp, reported) values(?,?,?,?,?,?,?)";
		
		try{
				PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
				
				ps.setInt(1, post.getuserId());
				ps.setString(2, post.getTitle());
				ps.setInt(3, post.getCategoryId());
				ps.setString(4, post.getContent());
				ps.setInt(5, post.getVotes());
				ps.setTimestamp(6, post.getTimeStamp());
				ps.setInt(7, post.getReported());
				
				return ps.executeUpdate() > 0;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public List<post> getAllposts() {
		
		List<post> posts = new ArrayList<>();
		String sql = "select * from post";
		
		try (
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
				)
				{	
					while(rs.next()) {
						post post = new post(rs.getInt(1),
							rs.getInt(2),
							rs.getString(3),
							rs.getInt(4),
							rs.getString(5),
							rs.getInt(6),
							rs.getTimestamp(7),
							rs.getInt(8)); 
						
						posts.add(post); 
					}
				} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		
		return posts;
	}
	
	@Override
	public post getpostbyId(int postId) {
		try {
			checkpostId(postId);
		} catch(InvalidId e) {
			e.printStackTrace();
			return null;
		}
		
		String sql = "select * post where postId = ?";
		
		
		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1, postId);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {		
				post post = new post(rs.getInt(1),
						rs.getInt(2),
						rs.getString(3),
						rs.getInt(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getTimestamp(7),
						rs.getInt(8));  
				
				return post;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean deletepostbyId(int postId) {
		
		try {
			checkpostId(postId);
		} catch(InvalidId e) {
			e.printStackTrace();
			return false;
		}
		
		String sql = "delete from post where postId = ?";
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
	public boolean updatepost(int postId,String title, String content,  int categoryId, int votes, Timestamp timestamp, int reported) {
		
		String sql = "update post set title=?, content=?, categoryId=?, votes=?, timestamp=?, reported=? where postId=?";
		
		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setString(1, title);
			ps.setString(2, content);
			ps.setInt(3, categoryId);
			ps.setInt(4, votes);
			ps.setTimestamp(5, timestamp);
			ps.setInt(6, reported);
			ps.setInt(7, postId);
			
			return ps.executeUpdate() > 0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}

	public boolean updatepost(post post) {
		String sql = "update post set title=?, content=?, categoryId=?, votes=?, timestamp=?, reported=? where postId=?";
		
		try{
				PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
				
				ps.setString(1, post.getTitle());
				ps.setString(2, post.getContent());
				ps.setInt(3, post.getCategoryId());
				ps.setInt(4, post.getVotes());
				ps.setTimestamp(5, post.getTimeStamp());
				ps.setInt(6, post.getReported());
				ps.setInt(7,post.getpostId());
				
				return ps.executeUpdate() > 0;
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	@Override
	public boolean updateVoteCountbyId(int postId, int votes) {
		
		try {
			checkpostId(postId);
		} catch(InvalidId e1) {
			e1.printStackTrace();
			return false;
		}
		
		String sql = "update post set votes=? where postId=?";
		
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
	public List<post> getAllpostbyuserId(int userId) {
		List<post> posts = new ArrayList<>();
		String statement = "select * from post where userId = ?";
		try {
				PreparedStatement ps= DbConnect.getMySQLConn().prepareStatement(statement);
				ps.setInt(1,userId);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					post post = new post(rs.getInt(1),
							rs.getInt(2),
							rs.getString(3),
							rs.getInt(4),
							rs.getString(5),
							rs.getInt(6),
							rs.getTimestamp(7),
							rs.getInt(8)); 
						
					posts.add(post); 
				}
			}catch(SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return posts;
	}

	@Override
	public List<post> getAllVisibleposts() {
		List<post> posts = new ArrayList<>();
		String statement = "select * from post where reported >= 100";
		try (
				PreparedStatement ps= DbConnect.getMySQLConn().prepareStatement(statement);
				ResultSet rs = ps.executeQuery();
				)
				{	
					while(rs.next()) {
						post post = new post(rs.getInt(1),
							rs.getInt(2),
							rs.getString(3),
							rs.getInt(4),
							rs.getString(5),
							rs.getInt(6),
							rs.getTimestamp(7),
							rs.getInt(8)); 
						
						posts.add(post); 
					}
				}catch(SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return posts;
	}

	@Override
	public boolean updateStatusofpost(int postId,int newstatus) {
		
		try {
			checkpostId(postId);
		} catch(InvalidId e1) {
			e1.printStackTrace();
			return false;
		}
		
		String sql= "update post set reported = ? where postId = ?";
		
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
	public List<post> getAllDraftposts(int userId) {
		List<post> posts = new ArrayList<>();
		String statement = "select * from post where userId = ? and reported = -100";
		try {
				PreparedStatement ps= DbConnect.getMySQLConn().prepareStatement(statement);
				ps.setInt(1, userId);
				ResultSet rs = ps.executeQuery();
					
				while(rs.next()) {
					post post = new post(rs.getInt(1),
						rs.getInt(2),
						rs.getString(3),
						rs.getInt(4),
						rs.getString(5),
						rs.getInt(6),
						rs.getTimestamp(7),
						rs.getInt(8)); 
						
					posts.add(post); 
				}
			}catch(SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return posts;
	}

	@Override
	public List<post> getAllReportedposts() {
		List<post> posts = new ArrayList<>();
		String statement = "select * from post where reported < 0 and reported != -100";
		try (
				PreparedStatement ps= DbConnect.getMySQLConn().prepareStatement(statement);
				ResultSet rs = ps.executeQuery();
				)
				{	
					while(rs.next()) {
						post post = new post(rs.getInt(1),
							rs.getInt(2),
							rs.getString(3),
							rs.getInt(4),
							rs.getString(5),
							rs.getInt(6),
							rs.getTimestamp(7),
							rs.getInt(8)); 
						
						posts.add(post); 
					}
				}catch(SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return posts;
	}

    
	@Override
	public List<post> getAllpostsByCategoryId(int categoryId)
	{
		List<post> posts = new ArrayList<>();
		String sql = "select postId, userId, title, categoryId, content, votes, timestamp, reported from post where categoryId= ?";
		
		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1, categoryId);
			ResultSet rs = ps.executeQuery();	
					while(rs.next()) {		
						post post = new post(rs.getInt(1),
								rs.getInt(2),
								rs.getString(3),
								rs.getInt(4),
								rs.getString(5),
								rs.getInt(6),
								rs.getTimestamp(7),
								rs.getInt(8)); 
						
						posts.add(post); 
					}
				} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		return posts;
	}

	private void checkpostId(int postId) throws InvalidId {
		// TODO Auto-generated method stub

		String sqlForException = "select * from questions where postId = ?";
		try {
			PreparedStatement psException = DbConnect.getMySQLConn().prepareStatement(sqlForException);
			psException.setInt(1, postId);
			ResultSet rs = psException.executeQuery();
			if (!rs.next()) {
				throw new InvalidId("Question");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	
}
