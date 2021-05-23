package com.sapient.healthyreps.controller;

import java.util.List;

import com.sapient.healthyreps.dao.CommentsPostDAO;

import com.sapient.healthyreps.entity.CommentsPost;

import com.sapient.healthyreps.exception.InvalidId;
import com.sapient.healthyreps.interfaces.ICommentsPostDAO;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/CommentsPost")
public class CommentsPostController {

	
	ICommentsPostDAO CommentsPostDAO = new CommentsPostDAO();
	
	@GetMapping
	public String generalPage() {
		return "Welcome to Comments under the Blog post";
	}
	
	@GetMapping("/all")
	public List<CommentsPost> getAllComments(){
		
		List<CommentsPost> allComments = CommentsPostDAO.getAllComments();
		
		return allComments;
		
	}
	
	@GetMapping("/post/{pid}")
	public List<CommentsPost> getAllCommentsByPostId(@PathVariable int pid) {
		
		List<CommentsPost> allCommentsOfAPost= CommentsPostDAO.getAllCommentsByPostId(pid);
		return allCommentsOfAPost;
	}
	
	@GetMapping("post/{pid}/comment{comid}")
	public String getCommentbyCommentId(@PathVariable int comid) {
		try {
			CommentsPostDAO.checkCommentId(comid);
		} catch (InvalidId e) {
			e.printStackTrace();
			return "Invalid Comment ID";
		}
		return CommentsPostDAO.getCommentByCommentId(comid).toString();
	}
	
}
