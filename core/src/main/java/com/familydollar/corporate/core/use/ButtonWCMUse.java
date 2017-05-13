package com.familydollar.corporate.core.use;

import com.adobe.cq.sightly.WCMUse;
import com.familydollar.corporate.core.utils.LinksUtil;

public class ButtonWCMUse extends WCMUse{
	
	private String buttonLink;

	@Override
	public void activate() throws Exception {
		if(getProperties().containsKey("buttonLink"))
		buttonLink = LinksUtil.checkInternalURLByPath(getProperties().get("buttonLink", String.class), getResourceResolver());
	}
	
	public String getButtonLink(){
		return buttonLink;
	}

}
