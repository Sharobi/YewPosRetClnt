/**
 * 
 */
package com.sharobi.yewpos.inv.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author Manodip Jana
 * 
 * 
 */
public class ExcelUploadResultObj implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String result; // contains invoice numbr / 0
	private List<String> failedItemList;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public List<String> getFailedItemList() {
		return failedItemList;
	}

	public void setFailedItemList(List<String> failedItemList) {
		this.failedItemList = failedItemList;
	}

}
