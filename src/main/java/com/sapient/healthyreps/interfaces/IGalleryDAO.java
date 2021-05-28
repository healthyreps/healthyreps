package com.sapient.healthyreps.interfaces;

import java.util.List;

import com.sapient.healthyreps.entity.Gallery;

public interface IGalleryDAO {
	public boolean insertNewImage(Gallery user);

	public List<Gallery> getAllImages();

	public List<String> getUserImageLinks(int uid);
}