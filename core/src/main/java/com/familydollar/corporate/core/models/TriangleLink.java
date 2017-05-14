package com.familydollar.corporate.core.models;

public class TriangleLink {
	private String triangleImage;	
	
	private String triangleImageUrl;

	private String popupImage;
	
	private String popupImageUrl;
	
	private boolean newWindow;
	
	public String getTriangleImage() {
		return triangleImage;
	}

	public void setTriangleImage(String triangleImage) {
		this.triangleImage = triangleImage;
	}
	
	public String getTriangleImageUrl() {
		return triangleImageUrl;
	}

	public void setTriangleImageUrl(String triangleImageUrl) {
		this.triangleImageUrl = triangleImageUrl;
	}

	public String getPopupImage() {
		return popupImage;
	}

	public void setPopupImage(String popupImage) {
		this.popupImage = popupImage;
	}
	
	public String getPopupImageUrl() {
		return popupImageUrl;
	}

	public void setPopupImageUrl(String popupImageUrl) {
		this.popupImageUrl = popupImageUrl;
		
	}
		public boolean isNewWindow() {
			return newWindow;
		}

		public void setNewWindow(boolean newWindow) {
			this.newWindow = newWindow;
		}
}


