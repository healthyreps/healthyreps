package com.sapient.healthyreps.interfaces;

import java.sql.Timestamp;
import java.util.List;

import com.sapient.healthyreps.entity.post;

public interface IPostDAO {

	public boolean insertpost(int postId, int userId, String title, int categoryId, String content, int votes, Timestamp timestamp, int reported);
	public boolean insertpost(int userId, String title, int categoryId, String content, int votes, Timestamp timestamp, int reported);
	
	//added today
	public boolean insertpost(post post);
	
	public List<post> getAllposts();
	public boolean deletepostbyId(int postId);
	public boolean updatepost(int postId,String title, String content,  int categoryId, int votes, Timestamp timestamp, int reported);
	
	//added today
	public boolean updatepost(post post);
	public post getpostbyId(int postId);
	
	public boolean updateVoteCountbyId(int postId, int votes);
	
	public List<post> getAllpostsByCategoryId(int categoryId);
	
	
//	public HashMap<Integer,HashMap<String,String>> getinBrief();
	
	public List<post> getAllpostbyuserId(int postId);
	
	public List<post> getAllVisibleposts();
	
	public boolean updateStatusofpost(int postId,int newstatus);
	
	public List<post> getAllDraftposts(int userId);
	
	public List<post> getAllReportedposts();
	
}
