package com.sapient.healthyreps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.sapient.healthyreps.dao.MediaDAO;
import com.sapient.healthyreps.entity.*;
import com.sapient.healthyreps.interfaces.IMediaDAO;

@RestController
@RequestMapping("/media")
public class MediaController {

	
	IMediaDAO mediaDAO = new MediaDAO();
	
	@GetMapping
	public String Media() {
		return "Welcome to Media";
	}
	
	@GetMapping("{pid}")
	public String getMediaByPostId(@PathVariable int pid) {
		
		return mediaDAO.getMediaByPostId(pid);
	}
	@DeleteMapping("/delete/{pid}")
	public String deleteMediaByPostId(@PathVariable int pid) {
		return mediaDAO.deleteMediaByPostId(pid)
				?"deleted":"Not deleted";
	}

   @PutMapping("/update/{pid}/{link}")
   public String updateMediaByPostId(@PathVariable int pid, String link) {
	     return mediaDAO.updateMediaByPostId(pid,link)
			     ?"updated":"Not updated";
}
	
}
