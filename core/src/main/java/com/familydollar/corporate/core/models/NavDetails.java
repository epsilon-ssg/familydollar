package com.familydollar.corporate.core.models;

import java.util.List;



public class NavDetails {
	private String pageTitle;
	private String pagePath;
	private List<NavDetails> children;
	public void setPageTitle(String title) {
		this.pageTitle = title;
	}

	public String getPageTitle() {
		return this.pageTitle;
	}

	public void setPagePath(String path) {
		this.pagePath = path;
	}
	
	public String getPagePath() {
		return this.pagePath;
	}
	
	public void setChildren(List<NavDetails> children) {
		this.children = children;
	}

	public List<NavDetails> getChildren() {
		return this.children;
	}
	
}
