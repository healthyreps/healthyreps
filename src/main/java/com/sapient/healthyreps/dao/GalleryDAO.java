package com.sapient.healthyreps.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.sapient.healthyreps.entity.Gallery;
import com.sapient.healthyreps.exception.ImageAlreadyPresent;
import com.sapient.healthyreps.exception.InvalidId;
import com.sapient.healthyreps.interfaces.IGalleryDAO;
import com.sapient.healthyreps.utils.DbConnect;

@Service
public class GalleryDAO implements IGalleryDAO {

	@Override
	public boolean insertNewImage(Gallery images) {
		// TODO Auto-generated method stub
		try {
			UserDAO.checkIdOfUser(images.getUserId());
			checkUrlOfImage(images.getImageUrl(), images.getUserId());
		} catch (InvalidId e) {
			e.printStackTrace();
			return false;
		} catch (ImageAlreadyPresent e) {
			e.printStackTrace();
			return false;
		}

		String sql = "insert into gallery values(?,?,?)";

		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1, images.getImageId());
			ps.setString(2, images.getImageUrl());
			ps.setInt(3, images.getUserId());

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	private void checkUrlOfImage(String image_url, int uid) throws ImageAlreadyPresent {
		String sql = "Select * from gallery where image_url=? and user_id=?";

		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setString(1, image_url);
			ps.setInt(2, uid);

			ResultSet rs = ps.executeQuery();
			if (rs.next())
				throw new ImageAlreadyPresent("ImageAlreadyPresent for user");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public List<Gallery> getAllImages() {
		String sql = "Select image_id,image_url,user_id from gallery";
		List<Gallery> images = new ArrayList<>();

		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Gallery galleryImage = new Gallery();
				galleryImage.setImageId(rs.getInt(1));
				galleryImage.setImageUrl(rs.getString(2));
				galleryImage.setUserId(rs.getInt(3));
				images.add(galleryImage);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return images;
	}

	@Override
	public List<String> getUserImageLinks(int uid) {
		// TODO Auto-generated method stub
		String sql = "Select image_url from gallery where user_id=?";
		List<String> images = new ArrayList<>();

		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1, uid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				images.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return images;
	}
}
