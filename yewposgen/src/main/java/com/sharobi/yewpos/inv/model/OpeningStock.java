package com.sharobi.yewpos.inv.model;

import java.io.Serializable;
import java.util.List;

public class OpeningStock implements Serializable{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<OpeningStockDetails> openingStockDetails;

	public List<OpeningStockDetails> getOpeningStockDetails() {
		return openingStockDetails;
	}

	public void setOpeningStockDetails(List<OpeningStockDetails> openingStockDetails) {
		this.openingStockDetails = openingStockDetails;
	}
	 
	 

}
