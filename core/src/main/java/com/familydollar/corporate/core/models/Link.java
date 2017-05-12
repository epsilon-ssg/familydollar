package com.familydollar.corporate.core.models;

public class Link {
	
	private String text;
	
	private String url;
	
	private boolean newWindow;
	
	private String description;

	private String imagePath;
	
	private String videoPath;
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	public String getVideoPath() {
		return videoPath;
	}

	public void setVideoPath(String videoPath) {
		this.videoPath = videoPath;
	}
	public String getDescription() {
		return description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isNewWindow() {
		return newWindow;
	}

	public void setNewWindow(boolean newWindow) {
		this.newWindow = newWindow;
	}
	public void setDescription(String description) {
		this.description = description;
	}

}
