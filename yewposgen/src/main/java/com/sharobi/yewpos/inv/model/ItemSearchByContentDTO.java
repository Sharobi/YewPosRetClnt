package com.sharobi.yewpos.inv.model;

import java.io.Serializable;

public class ItemSearchByContentDTO implements Serializable{
	
	private int itemId;

	private String itemName;

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	@Override
	public String toString() {
		return "ItemSearchByContentDTO [itemId=" + itemId + ", itemName=" + itemName + "]";
	}


}
