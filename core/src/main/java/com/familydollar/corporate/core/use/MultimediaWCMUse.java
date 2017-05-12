package com.familydollar.corporate.core.use;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;

import com.adobe.cq.sightly.WCMUse;
import com.familydollar.corporate.core.models.Multimedia;
import com.google.gson.Gson;

public class MultimediaWCMUse extends WCMUse{
	
	List<Multimedia> multimediaList = new ArrayList<Multimedia>();

	@Override
	public void activate() throws Exception {
		Node currentNode = getResource().adaptTo(Node.class);
		if(null != currentNode && currentNode.hasProperty("multimedia")){
			Gson gson = new Gson();
			if(currentNode.getProperty("multimedia").isMultiple()){
				String[] quesAndAnsItems = getProperties().get("multimedia", String[].class);
				if(null != quesAndAnsItems){
					for(String item: quesAndAnsItems){
						addMultimediaItem(gson, item);
					}
				}
			} else if(!currentNode.getProperty("multimedia").isMultiple()){
				String item = currentNode.getProperty("multimedia").getString();
				addMultimediaItem(gson, item);
			}
		}
		
	}

	
	private void addMultimediaItem(Gson gson, String item) {
		Multimedia multimedia = gson.fromJson(item, Multimedia.class);
		multimediaList.add(multimedia);
	}
	
	public List<Multimedia> getMultimediaList(){
		return multimediaList;
	}
}

