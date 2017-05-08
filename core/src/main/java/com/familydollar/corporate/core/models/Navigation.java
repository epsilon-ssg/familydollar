package com.familydollar.corporate.core.models;

import java.util.List;

public class Navigation {
	
	private String label;
	private String path;
	private boolean newWindow;
	private List<Navigation> children;
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public boolean isNewWindow() {
		return newWindow;
	}
	public void setNewWindow(boolean newWindow) {
		this.newWindow = newWindow;
	}
	public List<Navigation> getChildren() {
		return children;
	}
	public void setChildren(List<Navigation> children) {
		this.children = children;
	}

}
