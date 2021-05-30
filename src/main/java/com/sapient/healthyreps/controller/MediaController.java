package com.sapient.healthyreps.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.healthyreps.dao.MediaDAO;
import com.sapient.healthyreps.interfaces.IMediaDAO;

@CrossOrigin
@RestController
@RequestMapping("/media")
public class MediaController {

	IMediaDAO mediaDAO = new MediaDAO();

	@GetMapping
	public String Media() {
		return "Welcome to Media";
	}

	@GetMapping("{postId}")
	public String getMediabyPostId(@PathVariable int postId) {

		return mediaDAO.getMediaByPostId(postId);
	}

	@DeleteMapping("/delete/{postId}")
	public String deleteMediaByPostId(@PathVariable int postId) {
		return mediaDAO.deleteMediaByPostId(postId) ? "Deleted" : "Not Deleted";
	}

	@PutMapping("/update/{postId}/{link}")
	public String updateMediaByPostId(@PathVariable int postId, String link) {
		return mediaDAO.updateMediaByPostId(postId, link) ? "Updated" : "Not Updated";
	}

}
