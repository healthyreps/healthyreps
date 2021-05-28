package com.sapient.healthyreps.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sapient.healthyreps.entity.SocialLinks;
import com.sapient.healthyreps.exception.InvalidId;
import com.sapient.healthyreps.interfaces.ISocialLinksDAO;
import com.sapient.healthyreps.utils.DbConnect;

import org.springframework.stereotype.Service;

@Service
public class SocialLinksDAO implements ISocialLinksDAO {

	@Override
	public boolean insertUrls(SocialLinks link) {
		// TODO Auto-generated method stub
		try {
			UserDAO.checkIdOfUser(link.getUserId());
		} catch (InvalidId e) {
			e.printStackTrace();
			return false;
		}
		String sql = "insert into social_link values(?,?,?,?,?)";

		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1, link.getLinkId());
			ps.setString(2, link.getFacebookUrl());
			ps.setString(3, link.getInstagramUrl());
			ps.setString(4, link.getTwitterUrl());
			ps.setInt(5, link.getUserId());

			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<SocialLinks> getAlLUrls() {
		// TODO Auto-generated method stub
		String sql = "Select link_id,facebook_url,instagram_url,twitter_Url,user_id from social_link";
		List<SocialLinks> links = new ArrayList<>();

		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				SocialLinks socialLink = new SocialLinks();
				socialLink.setLinkId(rs.getInt(1));
				socialLink.setFacebookUrl(rs.getString(2));
				socialLink.setInstagramUrl(rs.getString(3));
				socialLink.setTwitterUrl(rs.getString(4));
				links.add(socialLink);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return links;
	}

	@Override
	public List<String> getUserLinks(int uid) {
		// TODO Auto-generated method stub
		String sql = "Select facebook_url,instagram_url,twitter_Url from social_link where user_id=?";
		List<String> links = new ArrayList<>();

		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1, uid);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				links.add(rs.getString(1));
				links.add(rs.getString(2));
				links.add(rs.getString(3));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return links;
	}

}
