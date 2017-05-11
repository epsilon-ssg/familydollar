package com.familydollar.corporate.core.utils;


import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUse;
import com.familydollar.corporate.core.models.NavDetails;

public class RequestUtil extends WCMUse{

	private static final Logger logger = LoggerFactory.getLogger(RequestUtil.class);

	@Override
	public void activate() throws Exception {
		
		logger.debug(":::SubNavigation::::");
		String attributeName = get("navPath", String.class);
		logger.info("::::RequestUtil attributeName::::{}", attributeName);
		Map<String, NavDetails> navMap = null != getRequest().getAttribute("navMap") ?  (Map<String, NavDetails>) getRequest()
				.getAttribute("navMap") : null;
				logger.info("+++++++ " + navMap.size()+ "---- "+ navMap);
		if (null != navMap) {
			NavDetails navItem = navMap.get(attributeName);
			logger.info(":navItem:::{}", navItem);
			if (null != navItem) {
				getRequest().setAttribute("navItem", navItem);
				
			}
		}

	}

}
