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

public class ListWCMUse extends WCMUse{
	private static final Logger LOGGER = LoggerFactory.getLogger(ListWCMUse.class);
	private List<Link> Links = new ArrayList<Link>();
	private String type ="";
	@Override
	public void activate() throws Exception {
		Node currentNode = getResource().adaptTo(Node.class);
		type = currentNode.getProperty("type").getString();
		
		if(null != currentNode){
			try {
				if(type.equals("link")){
				if(currentNode.getProperty("listLinkItems").isMultiple()){
					String[] items =getProperties().get("listLinkItems", String[].class);
					LOGGER.info("----------------------- " + items.length);
					if(null != items){
						for(String item: items){
							Links = getLinkItems(item);
						}
					}
				}else if(!currentNode.getProperty("listLinkItems").isMultiple()){
					String item = currentNode.getProperty("listLinkItems").getString();
					Links = getLinkItems(item);
			}}else{
				if(currentNode.getProperty("imageListItems").isMultiple()){
					String[] items =getProperties().get("imageListItems", String[].class);
					LOGGER.info("----------------------- " + items.length);
					if(null != items){
						for(String item: items){
							Links = getLinkItems(item);
						}
					}
				}else if(!currentNode.getProperty("imageListItems").isMultiple()){
					String item = currentNode.getProperty("imageListItems").getString();
					Links = getLinkItems(item);
			}
			}			
			}catch (PathNotFoundException e) {
				e.printStackTrace();
			} catch (ValueFormatException e) {
				e.printStackTrace();
			} catch (RepositoryException e) {
				e.printStackTrace();
			}
		
	}

	}

	private List<Link> getLinkItems(String item) throws JSONException {
		JSONObject links = new JSONObject(item);
		Link listitems = new Link();
		if(type.equals("link")){
		listitems.setText((String) links.get("label"));
		String listLinkUrl = (String) links.get("url");
		listitems.setDescription((String)links.get("text"));
		listitems.setUrl(LinksUtil.checkInternalExternalURLByResource(listLinkUrl,
				getResource().getResourceResolver().getResource(listLinkUrl)));
		}else{
			listitems.setImagePath((String) links.get("imagePath"));
			listitems.setVideoPath((String) links.get("videoPath"));
			}
		Links.add(listitems);
		return Links;
	}

	public List<Link> getListLinks(){
		return Links;
	}
}