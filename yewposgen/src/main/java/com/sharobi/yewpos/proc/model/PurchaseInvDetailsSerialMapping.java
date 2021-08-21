package com.sharobi.yewpos.proc.model;

import java.io.Serializable;
import java.util.Date;


public class PurchaseInvDetailsSerialMapping implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	public PurchaseInvDetailsSerialMapping() {
		super();
		// TODO Auto-generated constructor stub
	}

	private int id;
	
	private int purchaseInvId;
	private int purchaseInvDetailsId;
	
	private int itemId;
	
	private String batchNo;
	
	private Date expiryDate;
	
	private String uniqueIdentifierNo;
	
	private double qty;
	
	private int companyId;
	
	private int storeId;
	
	private int finyrId;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPurchaseInvId() {
		return purchaseInvId;
	}

	public void setPurchaseInvId(int purchaseInvId) {
		this.purchaseInvId = purchaseInvId;
	}

	public int getPurchaseInvDetailsId() {
		return purchaseInvDetailsId;
	}

	public void setPurchaseInvDetailsId(int purchaseInvDetailsId) {
		this.purchaseInvDetailsId = purchaseInvDetailsId;
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

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
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

	public int getFinyrId() {
		return finyrId;
	}

	public void setFinyrId(int finyrId) {
		this.finyrId = finyrId;
	}

	@Override
	public String toString() {
		return "PurchaseInvDetailsSerialMapping [id=" + id + ", purchaseInvId=" + purchaseInvId
				+ ", purchaseInvDetailsId=" + purchaseInvDetailsId + ", itemId=" + itemId + ", batchNo=" + batchNo
				+ ", expiryDate=" + expiryDate + ", uniqueIdentifierNo=" + uniqueIdentifierNo + ", qty=" + qty
				+ ", companyId=" + companyId + ", storeId=" + storeId + ", finyrId=" + finyrId + "]";
	}

	
}
