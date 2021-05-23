package com.sapient.healthyreps.dao;

import java.sql.*;
import java.util.*;

import com.sapient.healthyreps.entity.*;
import com.sapient.healthyreps.interfaces.IMediaDAO;
import com.sapient.healthyreps.utils.DbConnect;

public class MediaDAO implements IMediaDAO{

	@Override
	public boolean createMedia(int postId, String link) {
		String sql = "insert into Media_post(link, postId) values (?,?)";
		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			
			ps.setString(1, link);
			ps.setInt(2, postId);
			
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return false;
	}

	@Override
	public String getMediaByPostId(int postId) {
		String post_media="";
		String sql = "select link from Media_post where postId=?";
		try {
				PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
				ps.setInt(1, postId);
				ResultSet rs = ps.executeQuery();	
				while(rs.next()) {		
						post_media = new String(rs.getString(1));
					}
			} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
			}
			
		return post_media;
	}
	
	@Override
	public boolean deleteMediaByPostId(int postId)
	{
		String sql = "delete from Media_post where postId = ?";
		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setInt(1, postId);
			
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	@Override
	public boolean updateMediaByPostId(int postId, String link) {
		
		String sql = "update Media_post set link=? where postId=?";
		
		try {
			PreparedStatement ps = DbConnect.getMySQLConn().prepareStatement(sql);
			ps.setString(1, link);
			ps.setInt(2, postId);
			
			return ps.executeUpdate() > 0;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	

}
// abcd