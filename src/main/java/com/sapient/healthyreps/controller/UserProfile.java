package com.sapient.healthyreps.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.healthyreps.dao.GalleryDAO;
import com.sapient.healthyreps.dao.SocialLinksDAO;

@CrossOrigin
@RestController
public class UserProfile {

	@Autowired
	SocialLinksDAO daolinks = new SocialLinksDAO();

	@Autowired
	GalleryDAO daoimage = new GalleryDAO();

	@GetMapping("/api/user/sociallinks/{uid}")
	public List<String> getAllLinksOfUser(@PathVariable("uid") int uid) {
		return daolinks.getUserLinks(uid);
	}

	@GetMapping("/api/user/gallery/{uid}")
	public List<String> getAllLinksOfImagesOfUser(@PathVariable("uid") int uid) {
		return daoimage.getUserImageLinks(uid);
	}
}
