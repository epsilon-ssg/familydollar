package com.familydollar.corporate.core.use;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.familydollar.corporate.core.models.NavDetails;
import com.adobe.cq.sightly.WCMUse;

public class SubNavigation extends WCMUse{

	private static final Logger logger = LoggerFactory.getLogger(SubNavigation.class);

	@Override
	public void activate() throws Exception {
		logger.debug(":::SubNavigation::::"); 
	}

	public NavDetails getNavItem() {
		NavDetails navItem = null != getRequest().getAttribute("navItem") ? (NavDetails) getRequest()
				.getAttribute("navItem") : null;
		return navItem;
	}

}
