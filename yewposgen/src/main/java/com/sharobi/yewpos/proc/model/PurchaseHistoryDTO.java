/**
 * 
 */
package com.sharobi.yewpos.proc.model;

import java.io.Serializable;

/**
 * @author Manodip Jana
 * 
 * 
 */
public class PurchaseHistoryDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	private int invNo;
	private String invDate;
	private int itemId;
	private String itemName;
	private String batchNo;
	private String expiryDate;
	private String expiryDateFormat;
	private int packUnitId;
	private String packUnitName;
	private double packQty;
	private int conversion;
	private int looseQty;
	private int freeQty;
	private double mrp;
	private double rate;
	private double amount;
	private double edPer;
	private double ed;
	private double taxPer;
	private double tax;
	private double vatPer;
	private double vat;
	private double discPer;
	private double disc;
	private double itemLotAdjAmount;
	private double totAmount;
	private int taxId;
	private String taxName;
	private double taxPercentage;
	private double taxAmount;
	private double taxableRate;
	private String taxMode;
	private int isGroupTax;
	private int isTaxOnMrp;
	private int distributorId;
	private String distributor_name;
	private double saleRate;
	
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

	public int getInvNo() {
		return invNo;
	}

	public void setInvNo(int invNo) {
		this.invNo = invNo;
	}

	public String getInvDate() {
		return invDate;
	}

	public void setInvDate(String invDate) {
		this.invDate = invDate;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
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

	public String getExpiryDateFormat() {
		return expiryDateFormat;
	}

	public void setExpiryDateFormat(String expiryDateFormat) {
		this.expiryDateFormat = expiryDateFormat;
	}

	public int getPackUnitId() {
		return packUnitId;
	}

	public void setPackUnitId(int packUnitId) {
		this.packUnitId = packUnitId;
	}

	public String getPackUnitName() {
		return packUnitName;
	}

	public void setPackUnitName(String packUnitName) {
		this.packUnitName = packUnitName;
	}

	public double getPackQty() {
		return packQty;
	}

	public void setPackQty(double packQty) {
		this.packQty = packQty;
	}

	public int getConversion() {
		return conversion;
	}

	public void setConversion(int conversion) {
		this.conversion = conversion;
	}

	public int getLooseQty() {
		return looseQty;
	}

	public void setLooseQty(int looseQty) {
		this.looseQty = looseQty;
	}

	public int getFreeQty() {
		return freeQty;
	}

	public void setFreeQty(int freeQty) {
		this.freeQty = freeQty;
	}

	public double getMrp() {
		return mrp;
	}

	public void setMrp(double mrp) {
		this.mrp = mrp;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getEdPer() {
		return edPer;
	}

	public void setEdPer(double edPer) {
		this.edPer = edPer;
	}

	public double getEd() {
		return ed;
	}

	public void setEd(double ed) {
		this.ed = ed;
	}

	public double getTaxPer() {
		return taxPer;
	}

	public void setTaxPer(double taxPer) {
		this.taxPer = taxPer;
	}

	public double getTax() {
		return tax;
	}

	public void setTax(double tax) {
		this.tax = tax;
	}

	public double getVatPer() {
		return vatPer;
	}

	public void setVatPer(double vatPer) {
		this.vatPer = vatPer;
	}

	public double getVat() {
		return vat;
	}

	public void setVat(double vat) {
		this.vat = vat;
	}

	public double getDiscPer() {
		return discPer;
	}

	public void setDiscPer(double discPer) {
		this.discPer = discPer;
	}

	public double getDisc() {
		return disc;
	}

	public void setDisc(double disc) {
		this.disc = disc;
	}

	public double getItemLotAdjAmount() {
		return itemLotAdjAmount;
	}

	public void setItemLotAdjAmount(double itemLotAdjAmount) {
		this.itemLotAdjAmount = itemLotAdjAmount;
	}

	public double getTotAmount() {
		return totAmount;
	}

	public void setTotAmount(double totAmount) {
		this.totAmount = totAmount;
	}

	public int getTaxId() {
		return taxId;
	}

	public void setTaxId(int taxId) {
		this.taxId = taxId;
	}

	public String getTaxName() {
		return taxName;
	}

	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}

	public double getTaxPercentage() {
		return taxPercentage;
	}

	public void setTaxPercentage(double taxPercentage) {
		this.taxPercentage = taxPercentage;
	}

	public double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public double getTaxableRate() {
		return taxableRate;
	}

	public void setTaxableRate(double taxableRate) {
		this.taxableRate = taxableRate;
	}

	public String getTaxMode() {
		return taxMode;
	}

	public void setTaxMode(String taxMode) {
		this.taxMode = taxMode;
	}

	public int getIsGroupTax() {
		return isGroupTax;
	}

	public void setIsGroupTax(int isGroupTax) {
		this.isGroupTax = isGroupTax;
	}

	public int getIsTaxOnMrp() {
		return isTaxOnMrp;
	}

	public void setIsTaxOnMrp(int isTaxOnMrp) {
		this.isTaxOnMrp = isTaxOnMrp;
	}

	public int getDistributorId() {
		return distributorId;
	}

	public void setDistributorId(int distributorId) {
		this.distributorId = distributorId;
	}

	public String getDistributor_name() {
		return distributor_name;
	}

	public void setDistributor_name(String distributor_name) {
		this.distributor_name = distributor_name;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PurchaseHistoryDTO [invNo=" + invNo + ", invDate=" + invDate + ", itemId=" + itemId + ", itemName="
				+ itemName + ", batchNo=" + batchNo + ", expiryDate=" + expiryDate + ", expiryDateFormat="
				+ expiryDateFormat + ", packUnitId=" + packUnitId + ", packUnitName=" + packUnitName + ", packQty="
				+ packQty + ", conversion=" + conversion + ", looseQty=" + looseQty + ", freeQty=" + freeQty + ", mrp="
				+ mrp + ", rate=" + rate + ", amount=" + amount + ", edPer=" + edPer + ", ed=" + ed + ", taxPer="
				+ taxPer + ", tax=" + tax + ", vatPer=" + vatPer + ", vat=" + vat + ", discPer=" + discPer + ", disc="
				+ disc + ", itemLotAdjAmount=" + itemLotAdjAmount + ", totAmount=" + totAmount + ", taxId=" + taxId
				+ ", taxName=" + taxName + ", taxPercentage=" + taxPercentage + ", taxAmount=" + taxAmount
				+ ", taxableRate=" + taxableRate + ", taxMode=" + taxMode + ", isGroupTax=" + isGroupTax
				+ ", isTaxOnMrp=" + isTaxOnMrp + ", distributorId=" + distributorId + ", distributor_name="
				+ distributor_name + ", saleRate=" + saleRate + "]";
	}

}
