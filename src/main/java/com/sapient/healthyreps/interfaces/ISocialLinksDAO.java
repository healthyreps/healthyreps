package com.sapient.healthyreps.interfaces;

import java.util.List;

import com.sapient.healthyreps.entity.SocialLinks;

public interface ISocialLinksDAO {

	public boolean insertUrls(SocialLinks user);

	public List<SocialLinks> getAlLUrls();

	public List<String> getUserLinks(int uid);

}
