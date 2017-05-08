package com.familydollar.corporate.core.use;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;

import com.adobe.cq.sightly.WCMUse;
import com.familydollar.corporate.core.models.Link;
import com.familydollar.corporate.core.utils.LinksUtil;
import com.google.gson.Gson;

public class FooterLinksWCMUse extends WCMUse{
	
	private List<Link> footerLinks = new ArrayList<Link>();
	
	private String headerLinkUrl;

	@Override
	public void activate() throws Exception {
		Node currentNode = getResource().adaptTo(Node.class);
		if(null != currentNode){
			
			headerLinkUrl = currentNode.hasProperty("headerLinkUrl") ? currentNode.getProperty("headerLinkUrl").getString() : null;
			Gson gson = new Gson();
			if(currentNode.getProperty("items").isMultiple()){
				String[] footerItems = getProperties().get("items", String[].class);
				if(null != footerItems){
					for(String item: footerItems){
						addFooterItem(gson, item);
					}
				}
			} else if(!currentNode.getProperty("items").isMultiple()){
				String item = currentNode.getProperty("items").getString();
				addFooterItem(gson, item);
			}
		}
	}

	private void addFooterItem(Gson gson, String item) {
		Link footerItem = gson.fromJson(item, Link.class);
		footerItem.setUrl(LinksUtil.checkInternalURLByPath(footerItem.getUrl(), getResourceResolver()));
		footerLinks.add(footerItem);
	}

	public List<Link> getFooterLinks(){
		return footerLinks;
	}
	
	public String getHeaderLinkUrl(){
		return LinksUtil.checkInternalURLByPath(headerLinkUrl, getResourceResolver());
	}
}
