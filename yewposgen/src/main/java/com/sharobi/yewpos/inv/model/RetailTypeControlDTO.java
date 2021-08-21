/**
 * 
 */
package com.sharobi.yewpos.inv.model;

import java.io.Serializable;

/**
 * @author Manodip Jana
 *
 */
public class RetailTypeControlDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private int id;
	private int retailTypeId;
	private String retailTypeName;
	private int menuId;
	private String controlName;
	private int isVisible;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RetailTypeControlDTO [id=" + id + ", retailTypeId="
				+ retailTypeId + ", retailTypeName=" + retailTypeName
				+ ", menuId=" + menuId + ", controlName=" + controlName
				+ ", isVisible=" + isVisible + "]";
	}
	
}
