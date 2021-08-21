/**
 * 
 */
package com.sharobi.yewpos.inv.model;

import java.io.Serializable;

/**
 * @author Manodip Jana
 *
 */
public class SerialStockStatusDTO implements Serializable{
	private static final long serialVersionUID = 1L;
    private int isChecked;
    private int itemId;
    private String uniqueIdentifierNo;
    private String checkStatus;
    private double mrp;
    private double purchaseRate;
    private double saleRate;
	
    /**
	 * @return the isChecked
	 */
	public int getIsChecked() {
		return isChecked;
	}
	/**
	 * @param isChecked the isChecked to set
	 */
	public void setIsChecked(int isChecked) {
		this.isChecked = isChecked;
	}
	/**
	 * @return the itemId
	 */
	public int getItemId() {
		return itemId;
	}
	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	/**
	 * @return the uniqueIdentifierNo
	 */
	public String getUniqueIdentifierNo() {
		return uniqueIdentifierNo;
	}
	/**
	 * @param uniqueIdentifierNo the uniqueIdentifierNo to set
	 */
	public void setUniqueIdentifierNo(String uniqueIdentifierNo) {
		this.uniqueIdentifierNo = uniqueIdentifierNo;
	}
	
	/**
	 * @return the checkStatus
	 */
	public String getCheckStatus() {
		return checkStatus;
	}
	/**
	 * @param checkStatus the checkStatus to set
	 */
	public void setCheckStatus(String checkStatus) {
		this.checkStatus = checkStatus;
	}
	
	
	/**
	 * @return the mrp
	 */
	public double getMrp() {
		return mrp;
	}
	/**
	 * @param mrp the mrp to set
	 */
	public void setMrp(double mrp) {
		this.mrp = mrp;
	}
	/**
	 * @return the purchaseRate
	 */
	public double getPurchaseRate() {
		return purchaseRate;
	}
	/**
	 * @param purchaseRate the purchaseRate to set
	 */
	public void setPurchaseRate(double purchaseRate) {
		this.purchaseRate = purchaseRate;
	}
	/**
	 * @return the saleRate
	 */
	public double getSaleRate() {
		return saleRate;
	}
	/**
	 * @param saleRate the saleRate to set
	 */
	public void setSaleRate(double saleRate) {
		this.saleRate = saleRate;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SerialStockStatusDTO [isChecked=" + isChecked + ", itemId="
				+ itemId + ", uniqueIdentifierNo=" + uniqueIdentifierNo
				+ ", checkStatus=" + checkStatus + ", mrp=" + mrp
				+ ", purchaseRate=" + purchaseRate + ", saleRate=" + saleRate
				+ "]";
	}
    
}
