package com.sapient.healthyreps.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.healthyreps.dao.CommentsPostDAO;
import com.sapient.healthyreps.entity.CommentsPost;
import com.sapient.healthyreps.exception.InvalidId;
import com.sapient.healthyreps.interfaces.ICommentsPostDAO;

@CrossOrigin
@RestController
@RequestMapping("/CommentsPost")
public class CommentsPostController {

	ICommentsPostDAO CommentsPostDAO = new CommentsPostDAO();

	@GetMapping
	public String generalPage() {
		return "Welcome to Comments under the Blog post";
	}

	@GetMapping("/all")
	public List<CommentsPost> getAllComments() {

		List<CommentsPost> allComments = CommentsPostDAO.getAllComments();

		return allComments;

	}

	@GetMapping("/post/{postId}")
	public List<CommentsPost> getAllCommentsByPostId(@PathVariable int postId) {

		List<CommentsPost> allCommentsOfAPost = CommentsPostDAO.getAllCommentsByPostId(postId);
		return allCommentsOfAPost;
	}

	@GetMapping("post/{postId}/comment{commentId}")
	public String getCommentbyCommentId(@PathVariable int commentId) {
		try {
			CommentsPostDAO.checkCommentId(commentId);
		} catch (InvalidId e) {
			e.printStackTrace();
			return "Invalid Comment ID";
		}
		return CommentsPostDAO.getCommentByCommentId(commentId).toString();
	}

}