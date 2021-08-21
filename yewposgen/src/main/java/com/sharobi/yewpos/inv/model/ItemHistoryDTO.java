/**
 * 
 */
package com.sharobi.yewpos.inv.model;

import java.io.Serializable;

/**
 * @author Manodip Jana
 * 
 * 
 */
public class ItemHistoryDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private int itemId;
	private String batchNo;
	private String expiryDateFormat;
	private double inPackQty;
	private int inLooseQty;
	private double inCalculateLooseQty;
	private String inStockQty;
	private double outPackQty;
	private int outLooseQty;
	private double outCalculateLooseQty;
	private String outStockQty;
	private String tranType;
	private String invNo;
	private String invDate;
	private String partyName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getExpiryDateFormat() {
		return expiryDateFormat;
	}

	public void setExpiryDateFormat(String expiryDateFormat) {
		this.expiryDateFormat = expiryDateFormat;
	}

	public double getInPackQty() {
		return inPackQty;
	}

	public void setInPackQty(double inPackQty) {
		this.inPackQty = inPackQty;
	}

	public int getInLooseQty() {
		return inLooseQty;
	}

	public void setInLooseQty(int inLooseQty) {
		this.inLooseQty = inLooseQty;
	}

	public double getInCalculateLooseQty() {
		return inCalculateLooseQty;
	}

	public void setInCalculateLooseQty(double inCalculateLooseQty) {
		this.inCalculateLooseQty = inCalculateLooseQty;
	}

	public String getInStockQty() {
		return inStockQty;
	}

	public void setInStockQty(String inStockQty) {
		this.inStockQty = inStockQty;
	}

	public double getOutPackQty() {
		return outPackQty;
	}

	public void setOutPackQty(int outPackQty) {
		this.outPackQty = outPackQty;
	}

	public int getOutLooseQty() {
		return outLooseQty;
	}

	public void setOutLooseQty(int outLooseQty) {
		this.outLooseQty = outLooseQty;
	}

	public double getOutCalculateLooseQty() {
		return outCalculateLooseQty;
	}

	public void setOutCalculateLooseQty(double outCalculateLooseQty) {
		this.outCalculateLooseQty = outCalculateLooseQty;
	}

	public String getOutStockQty() {
		return outStockQty;
	}

	public void setOutStockQty(String outStockQty) {
		this.outStockQty = outStockQty;
	}

	public String getTranType() {
		return tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

	public String getInvNo() {
		return invNo;
	}

	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}

	public String getInvDate() {
		return invDate;
	}

	public void setInvDate(String invDate) {
		this.invDate = invDate;
	}
	
	public String getPartyName() {
		return partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	@Override
	public String toString() {
		return "ItemHistoryDTO [name=" + name + ", itemId=" + itemId + ", batchNo=" + batchNo + ", expiryDateFormat=" + expiryDateFormat + ", inPackQty=" + inPackQty + ", inLooseQty=" + inLooseQty + ", inCalculateLooseQty=" + inCalculateLooseQty + ", inStockQty=" + inStockQty + ", outPackQty=" + outPackQty + ", outLooseQty=" + outLooseQty + ", outCalculateLooseQty=" + outCalculateLooseQty + ", outStockQty=" + outStockQty + ", tranType=" + tranType + ", invNo=" + invNo + ", invDate=" + invDate + ", partyName=" + partyName + "]";
	}

}
