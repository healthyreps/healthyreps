package com.sapient.healthyreps.entity;

import java.sql.Timestamp;

public class post {

	
	private int postId;
	private int userId;
	private String title;
	private String content;
	private int categoryId;
	private int votes;
	private Timestamp timestamp;
	private int reported;
	
	
	public post() {}
	
	
	public post(int postId, int userId, String title, int categoryId, String content,int votes, Timestamp timeStamp,
			int reported) {
		super();
		this.postId = postId;
		this.userId = userId;
		this.title = title;
		this.content = content;
		this.categoryId = categoryId;
		this.votes = votes;
		this.timestamp = timeStamp;
		this.reported = reported;
	}



	public int getpostId() {
		return postId;
	}
	public void setpostId(int postId) {
		this.postId = postId;
	}
	public int getuserId() {
		return userId;
	}
	public void setuserId(int userId) {
		this.userId = userId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getVotes() {
		return votes;
	}
	public void setVotes(int votes) {
		this.votes = votes;
	}
	
	public int getReported() {
		return reported;
	}
	public void setReported(int reported) {
		this.reported = reported;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public Timestamp getTimeStamp() {
		return timestamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timestamp = timeStamp;
	}

	@Override
	public String toString() {
		return "post [postId=" + postId + ", userId=" + userId + ", title=" + title + ", content=" + content + ", categoryId="
				+ categoryId + ", votes=" + votes + ", timeStamp=" + timestamp + ", reported=" + reported + "]";
	}
	
	
	
}
