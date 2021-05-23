package com.sapient.healthyreps.dao;

import java.sql.*;
import java.util.*;

import com.sapient.healthyreps.exception.InvalidId;
import com.sapient.healthyreps.interfaces.ICommentsPostDAO;
import com.sapient.healthyreps.utils.DbConnect;
import com.sapient.healthyreps.entity.CommentsPost;


public class CommentsPostDAO implements ICommentsPostDAO{
	
	@Override
	public boolean insertComment(CommentsPost comment)
	{
		String sql = "insert into CommentsPost (votes, content , pid, uid, timestamp, reported) values(?,?,?,?,?,?)";
		try {

			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1, comment.getVotes());
			ps.setString(2, comment.getContent());
			ps.setInt(3, comment.getPid());
			ps.setInt(4, comment.getUid());
			ps.setTimestamp(5, comment.getTimestamp());
			ps.setInt(6, comment.getReported());

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean insertComment(int comid, int votes, String content, int pid, int uid, Timestamp timestamp, int reported)
	{
		String sql = "insert into CommentsPost (comid, votes, content , pid, uid, timestamp, reported) values(?,?,?,?,?,?,?)";
		try {

			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1, comid);
			ps.setInt(2, votes);
			ps.setString(3, content);
			ps.setInt(4, pid);
			ps.setInt(5, uid);
			ps.setTimestamp(6, timestamp);
			ps.setInt(7, reported);

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}
	
	
	@Override 
	public boolean updateComment(int comid, int votes, String content, int pid, int uid, Timestamp timestamp, int reported)
	{
		String sql= "update CommentsPost set votes=?, content= ?, pid= ?, uid = ?, timestamp= ?, reported= ? where comid=?";
		
		try {
			PreparedStatement ps= DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1,  votes);
			ps.setString(2,  content);
			ps.setInt(3, pid);
			ps.setInt(4, uid);
			ps.setTimestamp(5, timestamp);
			ps.setInt(6, reported);
			ps.setInt(7, comid);
			
			return ps.executeUpdate()>0;
		}
		
		catch(SQLException e)
		{
			//TODO Auto-generated
			e.printStackTrace();
		}
		
		return false;
	}
	 @Override
	 public boolean updateComment(CommentsPost comment)
	 {
		 
		 String sql = "update CommentsPost set votes=?, content=?, pid=?, uid=?, timestamp=?, reported= ? where comid = ?";

			try {
				PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
				ps.setInt(1, comment.getVotes());
				ps.setString(2, comment.getContent());
				ps.setInt(3, comment.getPid());
				ps.setInt(4, comment.getUid());
				ps.setTimestamp(5, comment.getTimestamp());
				ps.setInt(6, comment.getReported());
				ps.setInt(7, comment.getId());

				return ps.executeUpdate() > 0;

			} catch (SQLException e) {
				e.printStackTrace();
			}

			return false;
	 }
	 @Override
	 public List<CommentsPost> getAllComments()
	 {
		 List<CommentsPost> commentsPost = new ArrayList<>();
			String statement = "select * from CommentsPost";
			try {
					PreparedStatement ps= DbConnect.getMySQLConn().prepareStatement(statement);
					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						CommentsPost commentPost = new CommentsPost(rs.getInt(1),
								rs.getInt(2),
								rs.getString(3),
								rs.getInt(4),
								rs.getInt(5),
								rs.getTimestamp(6),
								rs.getInt(7)); 
							
						commentsPost.add(commentPost); 
					}
				}catch(SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			return commentsPost;
	 }
	 
	 @Override
		public CommentsPost getCommentByCommentId(int comid) {

			try {
				checkCommentId(comid);
			} catch (InvalidId e1) {
				
				e1.printStackTrace();
				return null;
			}

			String sql = "select comid, votes, content, pid, uid, timestamp,reported from CommentsPost where comid=?";
			try {

				PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
				ps.setInt(1, comid);
				ResultSet rs = ps.executeQuery();

				rs.next();

				CommentsPost comment = new CommentsPost();
				comment.setId(rs.getInt(1));
				comment.setVotes(rs.getInt(2));
				comment.setContent(rs.getString(3));
				comment.setPid(rs.getInt(4));
				comment.setUid(rs.getInt(5));
				comment.setTimestamp(rs.getTimestamp(6));
				comment.setReported(rs.getInt(7));

				return comment;

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return null;

		}

	 @Override
	 public List<CommentsPost> getAllCommentsByPostId(int pid) {
			List<CommentsPost> commentsPost = new ArrayList<>();
			String statement = "select comid, votes, content, pid, uid, timestamp, reported from CommentsPost where pid = ?";
			try {
					PreparedStatement ps= DbConnect.getMySQLConn().prepareStatement(statement);
					ps.setInt(1,pid);
					ResultSet rs = ps.executeQuery();
					while(rs.next()) {
						CommentsPost commentPost = new CommentsPost(rs.getInt(1),
								rs.getInt(2),
								rs.getString(3),
								rs.getInt(4),
								rs.getInt(5),
								rs.getTimestamp(6),
								rs.getInt(7)); 
							
						commentsPost.add(commentPost); 
					}
				}catch(SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			return commentsPost;
		}
	 
	 
	 @Override
	 public boolean deleteCommentById(int comid)
	 {
		 try {
				checkCommentId(comid);
			} catch(InvalidId e1) {
				e1.printStackTrace();
				return false;
			}
			
			String sql = "delete from CommentsPost where comid = ?";
			try {
				PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
				ps.setInt(1, comid);
				
				return ps.executeUpdate() > 0;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
	 }
	 

	
	 @Override
		public boolean updateVotebyCommentId(int comid, int votes) {
			
			
			String sql = "update CommentsPost set votes=? where comid=?";
			
			try {
				PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
				ps.setInt(1, votes);
				ps.setInt(2, comid);
				
				return ps.executeUpdate() > 0;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
	 
	
	 
	 @Override
		public boolean updateStatusofComment(int comid,int newstatus) {
			
			
			String sql= "update CommentsPost set reported = ? where comid = ?";
			
			try {
				PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
				ps.setInt(1, newstatus);
				ps.setInt(2, comid);
				
				return ps.executeUpdate() > 0;
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
		}
	 
	 @Override
	 public CommentsPost getMostVotedComment(int pid) {
		
		 String sql="select * from CommentsPost where pid=? order by votes desc limit 1;";

		 CommentsPost mostVotedComment= new CommentsPost();


		 //=new Comments_post();
		 try {
				PreparedStatement ps= DbConnect.getMySQLConn().prepareStatement(sql);
				ps.setInt(1,pid);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					CommentsPost commentPost = new CommentsPost(rs.getInt(1),
							rs.getInt(2),
							rs.getString(3),
							rs.getInt(4),
							rs.getInt(5),
							rs.getTimestamp(6),
							rs.getInt(7)); 
					mostVotedComment=commentPost;
					 break;
				}
			}catch(SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 return mostVotedComment;
		 
		 
	 }
	 
	 public void checkCommentId(int comid) throws InvalidId {
			// TODO Auto-generated method stub

			String sqlForException = "select * from CommentsPost where comid = ?";
			try {
				PreparedStatement psException = DbConnect.getMySQLConn().prepareStatement(sqlForException);
				psException.setInt(1, comid);
				ResultSet rs = psException.executeQuery();
				if (!rs.next()) {
					throw new InvalidId("Comment");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	


		
		
	
}
