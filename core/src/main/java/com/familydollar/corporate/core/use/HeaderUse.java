package com.familydollar.corporate.core.use;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.PathNotFoundException;
import javax.jcr.RepositoryException;
import javax.jcr.ValueFormatException;

import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUse;
import com.familydollar.corporate.core.models.Link;
import com.familydollar.corporate.core.utils.LinksUtil;

public class HeaderUse extends WCMUse{
	/** The Constant logger. */
	private static final Logger LOGGER = LoggerFactory.getLogger(HeaderUse.class);
		
	private List<Link> headerLinks = new ArrayList<Link>();
	@Override
	public void activate() throws Exception {
		Node currentNode = getResource().adaptTo(Node.class);
		if(null != currentNode){
			try {
				if(currentNode.getProperty("items").isMultiple()){
					String[] items =getProperties().get("items", String[].class);
					if(null != items){
						for(String item: items){
							headerLinks = getHeaderItems(item);
						}
					}
				} else if(!currentNode.getProperty("items").isMultiple()){
					String item = currentNode.getProperty("items").getString();
					headerLinks = getHeaderItems(item);
				}
			} catch (PathNotFoundException e) {
				e.printStackTrace();
			} catch (ValueFormatException e) {
				e.printStackTrace();
			} catch (RepositoryException e) {
				e.printStackTrace();
			}
		}

	}


	private List<Link> getHeaderItems(String item) throws JSONException {
		JSONObject header = new JSONObject(item);
		Link headerproperties = new Link();
		headerproperties.setText((String) header.get("ctaText"));
		String listLinkUrl = (String) header.get("ctaUrl");
		headerproperties.setUrl(LinksUtil.checkInternalExternalURLByResource(listLinkUrl,
				getResource().getResourceResolver().getResource(listLinkUrl)));
		headerLinks.add(headerproperties);
		return headerLinks;
	}
	
	public List<Link> getHeaderLinks(){
		return headerLinks;
	}
}
