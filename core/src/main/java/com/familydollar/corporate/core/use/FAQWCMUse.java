package com.familydollar.corporate.core.use;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.NodeIterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUse;

public class FAQWCMUse extends WCMUse {
	
    private final Logger logger = LoggerFactory.getLogger(getClass());

	List<String> faqList = new ArrayList<String>();

	@Override
	public void activate() throws Exception {
		Node currentNode = getResource().adaptTo(Node.class);
		logger.info("activate::"+currentNode.getPath());
		if (null != currentNode && currentNode.hasNode("faqcontent") && currentNode.getNode("faqcontent").hasNodes()) {
			logger.info("has Nodes::");
			NodeIterator iterator = currentNode.getNode("faqcontent").getNodes();
			while (iterator.hasNext()) {
				Node faqCatNode = iterator.nextNode();
				logger.info("faqCatNode::"+faqCatNode.getName());
				if (faqCatNode.hasProperty("sling:resourceType")
						&& faqCatNode
								.getProperty("sling:resourceType").getString()
								.equals("familydollarcorporate/components/content/faq-category")) {
					logger.info("category::"+faqCatNode.getProperty("category").getString());
					faqList.add(faqCatNode.getProperty("category").getString());
				}
			}
		}

	}

	public List<String> getFaqCategoryList() {
		return faqList;
	}

}
