package com.sharobi.yewpos.pos.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author Manodip
 */
public class SalesDetails implements Serializable{

	private static final long serialVersionUID = 1L;

    private int id;
    private int saleId;
    private String invNo;
    private String invDate;
    private int itemId;
    private String batchNo;
    private String expiryDate;
    private int packUnitId;
    private double packQty;
    private int conversion;
    private int looseUnitId;
    private int looseQty;
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
    private double totAmount;
    private int finyrId;
    private int storeId;
    private int companyId;
    private int multiplier;
    private String lang;
    private String expiryDateFormat;
    private double mrpPerUnit;
    private double ratePerUnit;
    private int taxId;
    private double taxPercentage;
    private double taxAmount;
    private int isGroupTax;
    private String taxMode;
    private double purchaseCostPerUnit;
    private double itemTotalMrp;
    private double saleRate;
    private int isExclusive;
    private String esiType;
    private String prescriptionRegNo;
    private String prescriptionIssueDate;
    private String slipNo;
    private String esiCode;
    private String customerCode;
    private int locationId;
    private String mfdDate;
    private double purchaseRate;
    private List<SalesDetailsSerialMapper> salesDetailsSerialMapper;
    private int retailTypeId;
    private int tmpMappingId;
    private int taxTypeId;
    private int itemdualstock;
    private int salesmanId;
    private double salesmanFactor;
    private double salesmanAmount;

    private double freeQty;
    private double itemLotAdjAmount;
    private int itemFreeAgainstItem;
    
    private int saleOrderId;
    
    private String remarks; // new added 7.5.2019

    public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSaleId() {
		return saleId;
	}
	public void setSaleId(int saleId) {
		this.saleId = saleId;
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
	public int getPackUnitId() {
		return packUnitId;
	}
	public void setPackUnitId(int packUnitId) {
		this.packUnitId = packUnitId;
	}
	public double getPackQty() {
		return packQty;
	}
	public void setPackQty(int packQty) {
		this.packQty = packQty;
	}
	public int getConversion() {
		return conversion;
	}
	public void setConversion(int conversion) {
		this.conversion = conversion;
	}
	public int getLooseUnitId() {
		return looseUnitId;
	}
	public void setLooseUnitId(int looseUnitId) {
		this.looseUnitId = looseUnitId;
	}
	public int getLooseQty() {
		return looseQty;
	}
	public void setLooseQty(int looseQty) {
		this.looseQty = looseQty;
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
	public double getTotAmount() {
		return totAmount;
	}
	public void setTotAmount(double totAmount) {
		this.totAmount = totAmount;
	}
	public int getFinyrId() {
		return finyrId;
	}
	public void setFinyrId(int finyrId) {
		this.finyrId = finyrId;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public int getMultiplier() {
		return multiplier;
	}
	public void setMultiplier(int multiplier) {
		this.multiplier = multiplier;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getExpiryDateFormat() {
		return expiryDateFormat;
	}
	public void setExpiryDateFormat(String expiryDateFormat) {
		this.expiryDateFormat = expiryDateFormat;
	}
	public double getMrpPerUnit() {
		return mrpPerUnit;
	}
	public void setMrpPerUnit(double mrpPerUnit) {
		this.mrpPerUnit = mrpPerUnit;
	}
	public double getRatePerUnit() {
		return ratePerUnit;
	}
	public void setRatePerUnit(double ratePerUnit) {
		this.ratePerUnit = ratePerUnit;
	}

	public int getTaxId() {
		return taxId;
	}
	public void setTaxId(int taxId) {
		this.taxId = taxId;
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
	public int getIsGroupTax() {
		return isGroupTax;
	}
	public void setIsGroupTax(int isGroupTax) {
		this.isGroupTax = isGroupTax;
	}
	public String getTaxMode() {
		return taxMode;
	}
	public void setTaxMode(String taxMode) {
		this.taxMode = taxMode;
	}

	public double getPurchaseCostPerUnit() {
		return purchaseCostPerUnit;
	}
	public void setPurchaseCostPerUnit(double purchaseCostPerUnit) {
		this.purchaseCostPerUnit = purchaseCostPerUnit;
	}

	public double getItemTotalMrp() {
		return itemTotalMrp;
	}
	public void setItemTotalMrp(double itemTotalMrp) {
		this.itemTotalMrp = itemTotalMrp;
	}

	public double getSaleRate() {
		return saleRate;
	}
	public void setSaleRate(double saleRate) {
		this.saleRate = saleRate;
	}

	public int getIsExclusive() {
		return isExclusive;
	}
	public void setIsExclusive(int isExclusive) {
		this.isExclusive = isExclusive;
	}

	public String getEsiType() {
		return esiType;
	}
	public void setEsiType(String esiType) {
		this.esiType = esiType;
	}
	public String getPrescriptionRegNo() {
		return prescriptionRegNo;
	}
	public void setPrescriptionRegNo(String prescriptionRegNo) {
		this.prescriptionRegNo = prescriptionRegNo;
	}
	public String getPrescriptionIssueDate() {
		return prescriptionIssueDate;
	}
	public void setPrescriptionIssueDate(String prescriptionIssueDate) {
		this.prescriptionIssueDate = prescriptionIssueDate;
	}
	public String getSlipNo() {
		return slipNo;
	}
	public void setSlipNo(String slipNo) {
		this.slipNo = slipNo;
	}
	public String getEsiCode() {
		return esiCode;
	}
	public void setEsiCode(String esiCode) {
		this.esiCode = esiCode;
	}
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}


	/**
	 * @return the locationId
	 */
	public int getLocationId() {
		return locationId;
	}
	/**
	 * @param locationId the locationId to set
	 */
	public void setLocationId(int locationId) {
		this.locationId = locationId;
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
	 * @return the salesDetailsSerialMapper
	 */
	public List<SalesDetailsSerialMapper> getSalesDetailsSerialMapper() {
		return salesDetailsSerialMapper;
	}
	/**
	 * @param salesDetailsSerialMapper the salesDetailsSerialMapper to set
	 */
	public void setSalesDetailsSerialMapper(
			List<SalesDetailsSerialMapper> salesDetailsSerialMapper) {
		this.salesDetailsSerialMapper = salesDetailsSerialMapper;
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
	 * @return the tmpMappingId
	 */
	public int getTmpMappingId() {
		return tmpMappingId;
	}
	/**
	 * @param tmpMappingId the tmpMappingId to set
	 */
	public void setTmpMappingId(int tmpMappingId) {
		this.tmpMappingId = tmpMappingId;
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
	 * @param packQty the packQty to set
	 */
	public void setPackQty(double packQty) {
		this.packQty = packQty;
	}


	/**
	 * @return the salesmanId
	 */
	public int getSalesmanId() {
		return salesmanId;
	}
	/**
	 * @param salesmanId the salesmanId to set
	 */
	public void setSalesmanId(int salesmanId) {
		this.salesmanId = salesmanId;
	}
	/**
	 * @return the salesmanFactor
	 */
	public double getSalesmanFactor() {
		return salesmanFactor;
	}
	/**
	 * @param salesmanFactor the salesmanFactor to set
	 */
	public void setSalesmanFactor(double salesmanFactor) {
		this.salesmanFactor = salesmanFactor;
	}
	/**
	 * @return the salesmanAmount
	 */
	public double getSalesmanAmount() {
		return salesmanAmount;
	}
	/**
	 * @param salesmanAmount the salesmanAmount to set
	 */
	public void setSalesmanAmount(double salesmanAmount) {
		this.salesmanAmount = salesmanAmount;
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
		return "SalesDetails [id=" + id + ", saleId=" + saleId + ", invNo=" + invNo + ", invDate=" + invDate
				+ ", itemId=" + itemId + ", batchNo=" + batchNo + ", expiryDate=" + expiryDate + ", packUnitId="
				+ packUnitId + ", packQty=" + packQty + ", conversion=" + conversion + ", looseUnitId=" + looseUnitId
				+ ", looseQty=" + looseQty + ", mrp=" + mrp + ", rate=" + rate + ", amount=" + amount + ", edPer="
				+ edPer + ", ed=" + ed + ", taxPer=" + taxPer + ", tax=" + tax + ", vatPer=" + vatPer + ", vat=" + vat
				+ ", discPer=" + discPer + ", disc=" + disc + ", totAmount=" + totAmount + ", finyrId=" + finyrId
				+ ", storeId=" + storeId + ", companyId=" + companyId + ", multiplier=" + multiplier + ", lang=" + lang
				+ ", expiryDateFormat=" + expiryDateFormat + ", mrpPerUnit=" + mrpPerUnit + ", ratePerUnit="
				+ ratePerUnit + ", taxId=" + taxId + ", taxPercentage=" + taxPercentage + ", taxAmount=" + taxAmount
				+ ", isGroupTax=" + isGroupTax + ", taxMode=" + taxMode + ", purchaseCostPerUnit=" + purchaseCostPerUnit
				+ ", itemTotalMrp=" + itemTotalMrp + ", saleRate=" + saleRate + ", isExclusive=" + isExclusive
				+ ", esiType=" + esiType + ", prescriptionRegNo=" + prescriptionRegNo + ", prescriptionIssueDate="
				+ prescriptionIssueDate + ", slipNo=" + slipNo + ", esiCode=" + esiCode + ", customerCode="
				+ customerCode + ", locationId=" + locationId + ", mfdDate=" + mfdDate + ", purchaseRate="
				+ purchaseRate + ", salesDetailsSerialMapper=" + salesDetailsSerialMapper + ", retailTypeId="
				+ retailTypeId + ", tmpMappingId=" + tmpMappingId + ", taxTypeId=" + taxTypeId + ", itemdualstock="
				+ itemdualstock + ", salesmanId=" + salesmanId + ", salesmanFactor=" + salesmanFactor
				+ ", salesmanAmount=" + salesmanAmount + ", freeQty=" + freeQty + ", itemLotAdjAmount="
				+ itemLotAdjAmount + ", itemFreeAgainstItem=" + itemFreeAgainstItem + ", saleOrderId=" + saleOrderId
				+ ", remarks=" + remarks + "]";
	}
    
}
