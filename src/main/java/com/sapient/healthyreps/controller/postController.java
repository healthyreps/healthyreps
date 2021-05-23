package com.sapient.healthyreps.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.sapient.healthyreps.dao.PostDAO;
import com.sapient.healthyreps.entity.post;
import com.sapient.healthyreps.interfaces.IPostDAO;

@RestController
@RequestMapping("/post")
public class postController {

	
	IPostDAO PostDAO = new PostDAO();
	
	@GetMapping
	public String generalPage() {
		return "Welcome to posts";
	}
	
	@GetMapping("/all")
	public List<post> getAllposts(){
		
//		List<post> allposts = PostDAO.getAllposts();
		return PostDAO.getAllposts();
		
	}
	
	@GetMapping("/user/{userId}")
	public List<post> getAllpostsbyuserId(@PathVariable int userId) {
		
		return PostDAO.getAllpostbyuserId(userId);
	}
	
	
	@GetMapping("{postId}")
	public post getpostbyId(@PathVariable int postId) {
		post np=PostDAO.getpostbyId(postId);
		System.out.println(np);
		return np;
	}

	@PostMapping("/new_post/{userId}/{postId}")
	public String postPost(@RequestBody post post, @PathVariable int userId,@PathVariable int postId) {
		return PostDAO.insertpost(post)
				?"inserted":"Not inserted";
	}
	
	@DeleteMapping("/delete_post/{userId}/{postId}")
		public String deletepost(@PathVariable int postId) {
			return PostDAO.deletepostbyId(postId)
					?"deleted":"Not deleted";
		}
	
	@PutMapping("/new_post/{userId}/{postId}")
	public String updatepost(@RequestBody post post) {
		return PostDAO.updatepost(post)
				?"updated":"Not updated";
	}
	
	@GetMapping("all/{userId}")
	public List<post> getAllpostbyuserId(@PathVariable int userId){
		return PostDAO.getAllpostbyuserId(userId);
	}

	@GetMapping("/reported")
	public List<post> getAllReportedposts(){
		return PostDAO.getAllReportedposts();
	}
	
	@GetMapping("/draft/{userId}")
	public List<post> getAllDraftposts(@PathVariable int userId){
		return PostDAO.getAllDraftposts(userId);
	}
	
	@PutMapping("/updateStatus/{postId}/{newstatus}")
	public boolean updateStatusofpost(@PathVariable int postId,@PathVariable int newstatus) {
		return PostDAO.updateStatusofpost(postId,newstatus);
	}
}
