/**
 * 
 */
package com.sharobi.yewpos.proc.model;

import java.io.Serializable;

/**
 * @author Arunima Roy 
 *
 * Feb 26, 2018
 */
public class PurchaseReturnDetailsSerialMapper implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
    private int purchaseReturnId;
    private int purchaseReturnDetailsId;
    private int itemId;
    private String batchNo;
    private String expiryDate;
    private String uniqueIdentifierNo;
    private double qty;
    private int finyrId;
    private int companyId;
    private int storeId;
    private String expiryDateFormat;
    private int tmpMappingId;
    private int purchaseId;
    private int purchaseDetailsId;

	public int getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}
	
	public int getPurchaseDetailsId() {
		return purchaseDetailsId;
	}
	public void setPurchaseDetailsId(int purchaseDetailsId) {
		this.purchaseDetailsId = purchaseDetailsId;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public int getPurchaseReturnId() {
		return purchaseReturnId;
	}
	public void setPurchaseReturnId(int purchaseReturnId) {
		this.purchaseReturnId = purchaseReturnId;
	}
	
	public int getPurchaseReturnDetailsId() {
		return purchaseReturnDetailsId;
	}
	public void setPurchaseReturnDetailsId(int purchaseReturnDetailsId) {
		this.purchaseReturnDetailsId = purchaseReturnDetailsId;
	}
	
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	
	public String getBatchNo() {
		return batchNo;
	}
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	
	public String getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}
	
	public String getUniqueIdentifierNo() {
		return uniqueIdentifierNo;
	}
	public void setUniqueIdentifierNo(String uniqueIdentifierNo) {
		this.uniqueIdentifierNo = uniqueIdentifierNo;
	}
	
	public double getQty() {
		return qty;
	}
	public void setQty(double qty) {
		this.qty = qty;
	}
	
	public int getFinyrId() {
		return finyrId;
	}
	public void setFinyrId(int finyrId) {
		this.finyrId = finyrId;
	}
	
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	
	public String getExpiryDateFormat() {
		return expiryDateFormat;
	}
	public void setExpiryDateFormat(String expiryDateFormat) {
		this.expiryDateFormat = expiryDateFormat;
	}
	
	public int getTmpMappingId() {
		return tmpMappingId;
	}
	public void setTmpMappingId(int tmpMappingId) {
		this.tmpMappingId = tmpMappingId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PurchaseReturnDetailsSerialMapper [id=" + id + ", purchaseReturnId=" + purchaseReturnId
				+ ", purchaseReturnDetailsId=" + purchaseReturnDetailsId + ", itemId=" + itemId + ", batchNo=" + batchNo
				+ ", expiryDate=" + expiryDate + ", uniqueIdentifierNo=" + uniqueIdentifierNo + ", qty=" + qty
				+ ", finyrId=" + finyrId + ", companyId=" + companyId + ", storeId=" + storeId + ", expiryDateFormat="
				+ expiryDateFormat + ", tmpMappingId=" + tmpMappingId + ", purchaseId=" + purchaseId
				+ ", purchaseDetailsId=" + purchaseDetailsId + ", getPurchaseId()=" + getPurchaseId()
				+ ", getPurchaseDetailsId()=" + getPurchaseDetailsId() + ", getId()=" + getId()
				+ ", getPurchaseReturnId()=" + getPurchaseReturnId() + ", getPurchaseReturnDetailsId()="
				+ getPurchaseReturnDetailsId() + ", getItemId()=" + getItemId() + ", getBatchNo()=" + getBatchNo()
				+ ", getExpiryDate()=" + getExpiryDate() + ", getUniqueIdentifierNo()=" + getUniqueIdentifierNo()
				+ ", getQty()=" + getQty() + ", getFinyrId()=" + getFinyrId() + ", getCompanyId()=" + getCompanyId()
				+ ", getStoreId()=" + getStoreId() + ", getExpiryDateFormat()=" + getExpiryDateFormat()
				+ ", getTmpMappingId()=" + getTmpMappingId() + ", getClass()=" + getClass() + ", hashCode()="
				+ hashCode() + ", toString()=" + super.toString() + "]";
	}

}
