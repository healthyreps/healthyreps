package com.sapient.healthyreps.entity;

public class Media {

	
	private int id;
	private int postId;
	private String link;
	
	
	public Media() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Media(int id, int postId, String link) {
		super();
		this.id = id;
		this.postId = postId;
		this.link = link;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public int getpostId() {
		return postId;
	}


	public void setpostId(int postId) {
		this.postId = postId;
	}


	public String getLink() {
		return link;
	}


	public void setLink(String link) {
		this.link = link;
	}


	@Override
	public String toString() {
		return "Media [id=" + id + ", postId=" + postId + ", link=" + link + "]";
	}
	
	
	
}
