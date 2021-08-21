/**
 * 
 */
package com.sharobi.yewpos.inv.model;

import java.io.Serializable;

/**
 * @author Manodip Jana
 *
 */
public class RetailTypeDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int retailTypeId;
	private String retailTypeName;
	
	/**
	 * @return the retailTypeId
	 */
	public int getRetailTypeId() {
		return retailTypeId;
	}
	/**
	 * @param retailTypeId the retailTypeId to set
	 */
	public void setRetailTypeId(int retailTypeId) {
		this.retailTypeId = retailTypeId;
	}
	/**
	 * @return the retailTypeName
	 */
	public String getRetailTypeName() {
		return retailTypeName;
	}
	/**
	 * @param retailTypeName the retailTypeName to set
	 */
	public void setRetailTypeName(String retailTypeName) {
		this.retailTypeName = retailTypeName;
	}
	
	
}
