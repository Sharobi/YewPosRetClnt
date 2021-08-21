/**
 * 
 */
package com.sharobi.yewpos.inv.model;

import java.io.Serializable;

/**
 * @author Manodip Jana
 *
 * 11-Dec-2017
 */
public class InvOpeningStockDetails implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
    private int itemId;
    private int opStockId;
    private String batchNo;
    private String expiryDate;
    private String uniqueIdentifierNo;
    private double qty;
    private int finyrId;
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
	 * @return the opStockId
	 */
	public int getOpStockId() {
		return opStockId;
	}
	/**
	 * @param opStockId the opStockId to set
	 */
	public void setOpStockId(int opStockId) {
		this.opStockId = opStockId;
	}
	/**
	 * @return the batchNo
	 */
	public String getBatchNo() {
		return batchNo;
	}
	/**
	 * @param batchNo the batchNo to set
	 */
	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}
	/**
	 * @return the expiryDate
	 */
	public String getExpiryDate() {
		return expiryDate;
	}
	/**
	 * @param expiryDate the expiryDate to set
	 */
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
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
	 * @return the qty
	 */
	public double getQty() {
		return qty;
	}
	/**
	 * @param qty the qty to set
	 */
	public void setQty(double qty) {
		this.qty = qty;
	}
	/**
	 * @return the finyrId
	 */
	public int getFinyrId() {
		return finyrId;
	}
	/**
	 * @param finyrId the finyrId to set
	 */
	public void setFinyrId(int finyrId) {
		this.finyrId = finyrId;
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
		return "InvOpeningStockDetails [id=" + id + ", itemId=" + itemId
				+ ", opStockId=" + opStockId + ", batchNo=" + batchNo
				+ ", expiryDate=" + expiryDate + ", uniqueIdentifierNo="
				+ uniqueIdentifierNo + ", qty=" + qty + ", finyrId=" + finyrId
				+ ", storeId=" + storeId + ", companyId=" + companyId + "]";
	}
    
    
}
