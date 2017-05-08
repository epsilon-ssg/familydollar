package com.familydollar.corporate.core.use;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.adobe.cq.sightly.WCMUse;
import com.day.cq.wcm.api.Page;
import com.familydollar.corporate.core.models.Navigation;
import com.familydollar.corporate.core.utils.LinksUtil;

public class MainNavigationWCMUse extends WCMUse{
	
	protected static final String REDIRECT_TARGET = "redirectTarget";
	protected static final String DOT_HTML = ".html";
	protected static final String HTTP = "http";
	private List<Navigation> navItems = new ArrayList<Navigation>();
	

	@Override
	public void activate() throws Exception {
		final String rootPath = getProperties().get(
				"navRoot", getCurrentPage().getPath());
		final Page rootPage = 	getPageManager().getPage(rootPath);
		final Iterator<Page> pageIterator = rootPage.listChildren();
		buildNavigationItem(pageIterator, navItems);
	}


	private void buildNavigationItem(final Iterator<Page> pageIterator, List<Navigation> navItems) {
		while(pageIterator.hasNext()){
			Page page = pageIterator.next();
			Navigation navItem = new Navigation();
			navItem.setLabel(getNavigationLabel(page));
			String redirect = page.getProperties().get(REDIRECT_TARGET,
					String.class);
			if (redirect != null && redirect.startsWith(HTTP)) {
				navItem.setNewWindow(true);
				navItem.setPath(redirect);
			} else {
				navItem.setNewWindow(false);
				navItem.setPath(LinksUtil.checkInternalURLByPath(page.getPath(), getResourceResolver()));
			}
			buildSubNavigationItems(page, navItem);
			navItems.add(navItem);
		}
	}
	
	/**
	 * 
	 * @param page
	 * @param navItem
	 */
	private void buildSubNavigationItems(Page page, Navigation navItem) {
		List<Navigation> subNavItems = new ArrayList<Navigation>();
		buildNavigationItem(page.listChildren(),subNavItems);
		navItem.setChildren(subNavItems); 
	}


	/**
	 * 
	 * @param page
	 * @return
	 */
	protected String getNavigationLabel(Page page) {
		if (StringUtils.isNotEmpty(page.getNavigationTitle())) {
			return page.getNavigationTitle();
		} else if (StringUtils.isNotEmpty(page.getPageTitle())) {
			return page.getPageTitle();
		} else {
			return page.getTitle();
		}
	}
	
	public List<Navigation> getNavigationItems(){
		return navItems;
	}
	
	

}
