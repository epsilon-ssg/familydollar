package com.familydollar.corporate.core.use;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;

import com.adobe.cq.sightly.WCMUse;
import com.familydollar.corporate.core.models.SocialIcon;
import com.familydollar.corporate.core.utils.LinksUtil;
import com.google.gson.Gson;

public class FooterWCMUse extends WCMUse{
	
	private String termsAndPrivacyUrl;
	
	private List<SocialIcon> socialIcons = new ArrayList<SocialIcon>();

	@Override
	public void activate() throws Exception {
		Node currentNode = getResource().adaptTo(Node.class);
		if(null != currentNode){
			termsAndPrivacyUrl = currentNode.hasProperty("termsAndPrivacyUrl") ? currentNode.getProperty("termsAndPrivacyUrl").getString() : null;
			Gson gson = new Gson();
			if(currentNode.getProperty("socialIcons").isMultiple()){
				String[] socialIcons = getProperties().get("socialIcons", String[].class);
				if(null != socialIcons){
					for(String icon: socialIcons){
						getSocialIcon(gson, icon);
					}
				}
			} else if(!currentNode.getProperty("socialIcons").isMultiple()){
				String icon = currentNode.getProperty("socialIcons").getString();
				getSocialIcon(gson, icon);
			}
		}
	}
	
	private void getSocialIcon(Gson gson, String icon) {
		SocialIcon socialIcon = gson.fromJson(icon, SocialIcon.class);
		socialIcon.setIconLinkURL(LinksUtil.checkInternalURLByPath(socialIcon.getIconLinkURL(), getResourceResolver()));
		socialIcons.add(socialIcon);
		
	}
	
	public String getTest(){
		return "Testing!";
	}

	public String getTermsAndPrivacyUrl(){
		return LinksUtil.checkInternalURLByPath(termsAndPrivacyUrl, getResourceResolver());
	}
	
	public List<SocialIcon> getSocialIcons(){
		return socialIcons;
	}

}
