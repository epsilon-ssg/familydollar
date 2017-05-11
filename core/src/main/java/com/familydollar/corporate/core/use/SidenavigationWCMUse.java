package com.familydollar.corporate.core.use;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.adobe.cq.sightly.WCMUse;
import com.day.cq.wcm.api.Page;
import com.familydollar.corporate.core.models.NavDetails;

public class SidenavigationWCMUse extends WCMUse {
	protected Map<String, NavDetails> navMap;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SidenavigationWCMUse.class);
	List<NavDetails> navList = new ArrayList<NavDetails>();

	@Override
	public void activate() throws Exception {
		navMap = new LinkedHashMap<String, NavDetails>();
		Page currentPage = getResourcePage();
		Page parentPage = currentPage.getParent();
		Iterator<Page> currentpageLvelchildren;
		if(currentPage.getDepth()<=5){
			 currentpageLvelchildren = currentPage.listChildren();
		}else{
		 currentpageLvelchildren = parentPage.listChildren();
		}
		while (currentpageLvelchildren.hasNext()) {
			Page curentlevelPages = currentpageLvelchildren.next();
			NavDetails navObj = new NavDetails();
			navObj.setPageTitle(curentlevelPages.getName());
			navObj.setPagePath(curentlevelPages.getPath());
			List<NavDetails> childrenPages = new ArrayList<NavDetails>();
			if(currentPage.getDepth()<=5){
				childrenPages = sublevelPages(curentlevelPages, childrenPages);
			}else{
			if (curentlevelPages.getName().equals(currentPage.getName())) {
				childrenPages = sublevelPages(curentlevelPages, childrenPages);
			}
			}			
			navObj.setChildren(childrenPages);
			navMap.put(navObj.getPagePath(), navObj);
			navList.add(navObj);
		}
		getRequest().setAttribute("navMap", navMap);
	}

	private List<NavDetails> sublevelPages(Page curentlevelPages,
			List<NavDetails> childrenPages) {
		Iterator<Page> sublevelPages = curentlevelPages.listChildren();
		while (sublevelPages.hasNext()) {
			Page subPageName = sublevelPages.next();
			NavDetails subObj = new NavDetails();
			subObj.setPageTitle(subPageName.getName());
			subObj.setPagePath(subPageName.getPath());
			List<NavDetails> sublevelchildPages = new ArrayList<NavDetails>();
			sublevelchildPages = sublevelPages(subPageName, sublevelchildPages);
			subObj.setChildren(sublevelchildPages);
			navMap.put(subObj.getPagePath(), subObj);
			childrenPages.add(subObj);
		}
		return childrenPages;
	}

	public List<NavDetails> getNavList() {
		return navList;

	}
}
