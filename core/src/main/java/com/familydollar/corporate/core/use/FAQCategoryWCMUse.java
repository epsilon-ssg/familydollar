package com.familydollar.corporate.core.use;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;

import com.adobe.cq.sightly.WCMUse;
import com.familydollar.corporate.core.models.FAQ;
import com.google.gson.Gson;

public class FAQCategoryWCMUse extends WCMUse{
	
	List<FAQ> faqList = new ArrayList<FAQ>();

	@Override
	public void activate() throws Exception {
		Node currentNode = getResource().adaptTo(Node.class);
		if(null != currentNode && currentNode.hasProperty("quesAndAns")){
			Gson gson = new Gson();
			if(currentNode.getProperty("quesAndAns").isMultiple()){
				String[] quesAndAnsItems = getProperties().get("quesAndAns", String[].class);
				if(null != quesAndAnsItems){
					for(String item: quesAndAnsItems){
						addQuesAndAnsItem(gson, item);
					}
				}
			} else if(!currentNode.getProperty("quesAndAns").isMultiple()){
				String item = currentNode.getProperty("quesAndAns").getString();
				addQuesAndAnsItem(gson, item);
			}
		}
		
	}

	
	private void addQuesAndAnsItem(Gson gson, String item) {
		FAQ faqItem = gson.fromJson(item, FAQ.class);
		faqList.add(faqItem);
	}
	
	public List<FAQ> getFaqList(){
		return faqList;
	}
}

