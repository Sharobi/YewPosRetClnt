/**
 *
 */
package com.sharobi.yewpos.pos.model;

import java.io.Serializable;
import java.util.Date;

public class SaleOrderDetails implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private int id;

    private int saleOrderId;

    private String invNo;

    private Date invDate;

    private int itemId;

    private int packUnitId;

    private double packQty;

    private int conversion;

    private double looseQty;

    private double freeQty;

    private double soldPackQty;

    private double soldLooseQty;

    private double mrp;

    private double rate;

    private double saleRate;

    private double grossAmount;

    private double discPer;

    private double disc;

    private int taxId;

    private double taxPercentage;

    private double taxAmount;

    private String taxMode;

    private int isGroupTax;

    private double netAmount;

    private int tmpId;

    private int finyrId;

    private int storeId;

    private int companyId;

    private String lang;

    private String invType;

    private double itemTotalMrp;
    private int taxTypeId;
    private int itemdualstock;
    
    private String batchNo;
    private String expiryDate;
    private String mfdDate;
    private double itemLotAdjAmount;
    private int itemFreeAgainstItem;
    private String expiryDateFormat;
    private String remarks; // new added 7.5.2019
    public SaleOrderDetails(){
    	
    }

	/**
	 * @return the taxTypeId
	 */
	public int getTaxTypeId() {
		return taxTypeId;
	}

	/**
	 * @param taxTypeId the taxTypeId to set
	 */
	public void setTaxTypeId(int taxTypeId) {
		this.taxTypeId = taxTypeId;
	}

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
	 * @return the saleOrderId
	 */
	public int getSaleOrderId() {
		return saleOrderId;
	}

	/**
	 * @param saleOrderId the saleOrderId to set
	 */
	public void setSaleOrderId(int saleOrderId) {
		this.saleOrderId = saleOrderId;
	}

	/**
	 * @return the invNo
	 */
	public String getInvNo() {
		return invNo;
	}

	/**
	 * @param invNo the invNo to set
	 */
	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}

	/**
	 * @return the invDate
	 */
	public Date getInvDate() {
		return invDate;
	}

	/**
	 * @param invDate the invDate to set
	 */
	public void setInvDate(Date invDate) {
		this.invDate = invDate;
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
	 * @return the packUnitId
	 */
	public int getPackUnitId() {
		return packUnitId;
	}

	/**
	 * @param packUnitId the packUnitId to set
	 */
	public void setPackUnitId(int packUnitId) {
		this.packUnitId = packUnitId;
	}

	/**
	 * @return the packQty
	 */
	public double getPackQty() {
		return packQty;
	}

	/**
	 * @param packQty the packQty to set
	 */
	public void setPackQty(double packQty) {
		this.packQty = packQty;
	}

	/**
	 * @return the conversion
	 */
	public int getConversion() {
		return conversion;
	}

	/**
	 * @param conversion the conversion to set
	 */
	public void setConversion(int conversion) {
		this.conversion = conversion;
	}

	/**
	 * @return the looseQty
	 */
	public double getLooseQty() {
		return looseQty;
	}

	/**
	 * @param looseQty the looseQty to set
	 */
	public void setLooseQty(double looseQty) {
		this.looseQty = looseQty;
	}

	/**
	 * @return the freeQty
	 */
	public double getFreeQty() {
		return freeQty;
	}

	/**
	 * @param freeQty the freeQty to set
	 */
	public void setFreeQty(double freeQty) {
		this.freeQty = freeQty;
	}


	/**
	 * @return the soldPackQty
	 */
	public double getSoldPackQty() {
		return soldPackQty;
	}

	/**
	 * @param soldPackQty the soldPackQty to set
	 */
	public void setSoldPackQty(double soldPackQty) {
		this.soldPackQty = soldPackQty;
	}

	/**
	 * @return the soldLooseQty
	 */
	public double getSoldLooseQty() {
		return soldLooseQty;
	}

	/**
	 * @param soldLooseQty the soldLooseQty to set
	 */
	public void setSoldLooseQty(double soldLooseQty) {
		this.soldLooseQty = soldLooseQty;
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
	 * @return the rate
	 */
	public double getRate() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(double rate) {
		this.rate = rate;
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

	/**
	 * @return the grossAmount
	 */
	public double getGrossAmount() {
		return grossAmount;
	}

	/**
	 * @param grossAmount the grossAmount to set
	 */
	public void setGrossAmount(double grossAmount) {
		this.grossAmount = grossAmount;
	}

	/**
	 * @return the discPer
	 */
	public double getDiscPer() {
		return discPer;
	}

	/**
	 * @param discPer the discPer to set
	 */
	public void setDiscPer(double discPer) {
		this.discPer = discPer;
	}

	/**
	 * @return the disc
	 */
	public double getDisc() {
		return disc;
	}

	/**
	 * @param disc the disc to set
	 */
	public void setDisc(double disc) {
		this.disc = disc;
	}

	/**
	 * @return the taxId
	 */
	public int getTaxId() {
		return taxId;
	}

	/**
	 * @param taxId the taxId to set
	 */
	public void setTaxId(int taxId) {
		this.taxId = taxId;
	}

	/**
	 * @return the taxPercentage
	 */
	public double getTaxPercentage() {
		return taxPercentage;
	}

	/**
	 * @param taxPercentage the taxPercentage to set
	 */
	public void setTaxPercentage(double taxPercentage) {
		this.taxPercentage = taxPercentage;
	}

	/**
	 * @return the taxAmount
	 */
	public double getTaxAmount() {
		return taxAmount;
	}

	/**
	 * @param taxAmount the taxAmount to set
	 */
	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}

	/**
	 * @return the taxMode
	 */
	public String getTaxMode() {
		return taxMode;
	}

	/**
	 * @param taxMode the taxMode to set
	 */
	public void setTaxMode(String taxMode) {
		this.taxMode = taxMode;
	}

	/**
	 * @return the isGroupTax
	 */
	public int getIsGroupTax() {
		return isGroupTax;
	}

	/**
	 * @param isGroupTax the isGroupTax to set
	 */
	public void setIsGroupTax(int isGroupTax) {
		this.isGroupTax = isGroupTax;
	}

	/**
	 * @return the netAmount
	 */
	public double getNetAmount() {
		return netAmount;
	}

	/**
	 * @param netAmount the netAmount to set
	 */
	public void setNetAmount(double netAmount) {
		this.netAmount = netAmount;
	}

	/**
	 * @return the tmpId
	 */
	public int getTmpId() {
		return tmpId;
	}

	/**
	 * @param tmpId the tmpId to set
	 */
	public void setTmpId(int tmpId) {
		this.tmpId = tmpId;
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

	/**
	 * @return the lang
	 */
	public String getLang() {
		return lang;
	}

	/**
	 * @param lang the lang to set
	 */
	public void setLang(String lang) {
		this.lang = lang;
	}

	/**
	 * @return the invType
	 */
	public String getInvType() {
		return invType;
	}

	/**
	 * @param invType the invType to set
	 */
	public void setInvType(String invType) {
		this.invType = invType;
	}

	/**
	 * @return the itemTotalMrp
	 */
	public double getItemTotalMrp() {
		return itemTotalMrp;
	}

	/**
	 * @param itemTotalMrp the itemTotalMrp to set
	 */
	public void setItemTotalMrp(double itemTotalMrp) {
		this.itemTotalMrp = itemTotalMrp;
	}

	/**
	 * @return the itemdualstock
	 */
	public int getItemdualstock() {
		return itemdualstock;
	}

	/**
	 * @param itemdualstock the itemdualstock to set
	 */
	public void setItemdualstock(int itemdualstock) {
		this.itemdualstock = itemdualstock;
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
	 * @return the mfdDate
	 */
	public String getMfdDate() {
		return mfdDate;
	}

	/**
	 * @param mfdDate the mfdDate to set
	 */
	public void setMfdDate(String mfdDate) {
		this.mfdDate = mfdDate;
	}

	/**
	 * @return the itemLotAdjAmount
	 */
	public double getItemLotAdjAmount() {
		return itemLotAdjAmount;
	}

	/**
	 * @param itemLotAdjAmount the itemLotAdjAmount to set
	 */
	public void setItemLotAdjAmount(double itemLotAdjAmount) {
		this.itemLotAdjAmount = itemLotAdjAmount;
	}

	/**
	 * @return the itemFreeAgainstItem
	 */
	public int getItemFreeAgainstItem() {
		return itemFreeAgainstItem;
	}

	/**
	 * @param itemFreeAgainstItem the itemFreeAgainstItem to set
	 */
	public void setItemFreeAgainstItem(int itemFreeAgainstItem) {
		this.itemFreeAgainstItem = itemFreeAgainstItem;
	}

	/**
	 * @return the expiryDateFormat
	 */
	public String getExpiryDateFormat() {
		return expiryDateFormat;
	}

	/**
	 * @param expiryDateFormat the expiryDateFormat to set
	 */
	public void setExpiryDateFormat(String expiryDateFormat) {
		this.expiryDateFormat = expiryDateFormat;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "SaleOrderDetails [id=" + id + ", saleOrderId=" + saleOrderId + ", invNo=" + invNo + ", invDate="
				+ invDate + ", itemId=" + itemId + ", packUnitId=" + packUnitId + ", packQty=" + packQty
				+ ", conversion=" + conversion + ", looseQty=" + looseQty + ", freeQty=" + freeQty + ", soldPackQty="
				+ soldPackQty + ", soldLooseQty=" + soldLooseQty + ", mrp=" + mrp + ", rate=" + rate + ", saleRate="
				+ saleRate + ", grossAmount=" + grossAmount + ", discPer=" + discPer + ", disc=" + disc + ", taxId="
				+ taxId + ", taxPercentage=" + taxPercentage + ", taxAmount=" + taxAmount + ", taxMode=" + taxMode
				+ ", isGroupTax=" + isGroupTax + ", netAmount=" + netAmount + ", tmpId=" + tmpId + ", finyrId="
				+ finyrId + ", storeId=" + storeId + ", companyId=" + companyId + ", lang=" + lang + ", invType="
				+ invType + ", itemTotalMrp=" + itemTotalMrp + ", taxTypeId=" + taxTypeId + ", itemdualstock="
				+ itemdualstock + ", batchNo=" + batchNo + ", expiryDate=" + expiryDate + ", mfdDate=" + mfdDate
				+ ", itemLotAdjAmount=" + itemLotAdjAmount + ", itemFreeAgainstItem=" + itemFreeAgainstItem
				+ ", expiryDateFormat=" + expiryDateFormat + ", remarks=" + remarks + "]";
	}
	
	

}
