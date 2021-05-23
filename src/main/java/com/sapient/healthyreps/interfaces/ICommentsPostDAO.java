package com.sapient.healthyreps.interfaces;

import java.sql.Timestamp;
import java.util.List;

import com.sapient.healthyreps.entity.CommentsPost;
import com.sapient.healthyreps.exception.InvalidId;

public interface ICommentsPostDAO {

	public void checkCommentId (int comid) throws InvalidId;
	public boolean insertComment(int comid, int votes, String content, int pid, int uid, Timestamp timestamp, int reported);
	public boolean insertComment(CommentsPost comment);
	public boolean updateComment(int comid, int votes, String content, int pid, int uid, Timestamp timestamp, int reported);
	public boolean updateComment(CommentsPost comment);
	public boolean deleteCommentById(int comid);
	public List<CommentsPost> getAllComments();
	
	public List<CommentsPost> getAllCommentsByPostId(int pid);
	public CommentsPost getCommentByCommentId(int comid);
	public boolean updateVotebyCommentId(int comid, int votes);
	
	public boolean updateStatusofComment(int comid,int newstatus);
	
	public CommentsPost getMostVotedComment(int comid);
	
}
