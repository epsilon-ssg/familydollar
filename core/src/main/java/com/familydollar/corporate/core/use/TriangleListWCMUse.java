package com.familydollar.corporate.core.use;

import java.util.ArrayList;
import java.util.List;

import javax.jcr.Node;

import org.apache.sling.commons.json.JSONException;
import org.apache.sling.commons.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUse;
import com.familydollar.corporate.core.models.Link;
import com.familydollar.corporate.core.models.TriangleLink;
import com.familydollar.corporate.core.utils.LinksUtil;

public class TriangleListWCMUse extends WCMUse {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ListWCMUse.class);
	private List<TriangleLink> trianleLinks = new ArrayList<TriangleLink>();

	@Override
	public void activate() throws Exception {
		Node currentNode = getResource().adaptTo(Node.class);
		if (null != currentNode) {
			if (currentNode.getProperty("items").isMultiple()) {
				String[] items = getProperties().get("items", String[].class);
				if (null != items) {
					for (String item : items) {
						trianleLinks = getLinkItems(item);
					}
				}
			} else if (!currentNode.getProperty("items").isMultiple()) {
				String item = currentNode.getProperty("items").getString();
				trianleLinks = getLinkItems(item);
			}
		}
	}

	private List<TriangleLink> getLinkItems(String item) throws JSONException {
		JSONObject links = new JSONObject(item);
		TriangleLink trianglelistitems = new TriangleLink();
		trianglelistitems.setTriangleImage((String) links.get("triangleImage"));
		trianglelistitems.setPopupImage((String) links.get("popupImage"));
		String triangleImageUrl = (String) links.get("triangleImageurl");
		String popupImageUrl = (String) links.get("popupImageUrl");
		trianglelistitems.setPopupImageUrl(LinksUtil
				.checkInternalExternalURLByResource(
						popupImageUrl,
						getResource().getResourceResolver().getResource(
								popupImageUrl)));
		trianglelistitems.setTriangleImageUrl(LinksUtil
				.checkInternalExternalURLByResource(
						triangleImageUrl,
						getResource().getResourceResolver().getResource(
								triangleImageUrl)));
		trianleLinks.add(trianglelistitems);
		return trianleLinks;
	}
	public List<TriangleLink> getListLinks(){
		return trianleLinks;
	}

}
