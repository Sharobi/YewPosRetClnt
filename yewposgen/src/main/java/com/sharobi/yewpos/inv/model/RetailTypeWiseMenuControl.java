/**
 * 
 */
package com.sharobi.yewpos.inv.model;

import java.io.Serializable;

/**
 * @author Manodip Jana
 *
 * 30-Nov-2017
 */
public class RetailTypeWiseMenuControl implements Serializable{
	private static final long serialVersionUID = 1L;

	private int id;
	private int retailTypeId;
	private int menuId;
	private String controlName;
	private int isVisible;
	private int storeId;
	private int companyId;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
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
	 * @return the menuId
	 */
	public int getMenuId() {
		return menuId;
	}
	/**
	 * @param menuId the menuId to set
	 */
	public void setMenuId(int menuId) {
		this.menuId = menuId;
	}
	/**
	 * @return the controlName
	 */
	public String getControlName() {
		return controlName;
	}
	/**
	 * @param controlName the controlName to set
	 */
	public void setControlName(String controlName) {
		this.controlName = controlName;
	}
	/**
	 * @return the isVisible
	 */
	public int getIsVisible() {
		return isVisible;
	}
	/**
	 * @param isVisible the isVisible to set
	 */
	public void setIsVisible(int isVisible) {
		this.isVisible = isVisible;
	}
	/**
	 * @return the storeId
	 */
	public int getStoreId() {
		return storeId;
	}
	/**
	 * @param storeId the storeId to set
	 */
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	/**
	 * @return the companyId
	 */
	public int getCompanyId() {
		return companyId;
	}
	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RetailTypeWiseMenuControl [id=" + id + ", retailTypeId="
				+ retailTypeId + ", menuId=" + menuId + ", controlName="
				+ controlName + ", isVisible=" + isVisible + ", storeId="
				+ storeId + ", companyId=" + companyId + "]";
	}
	
}
