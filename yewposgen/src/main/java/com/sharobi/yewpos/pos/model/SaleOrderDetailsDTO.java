/**
 *
 */
package com.sharobi.yewpos.pos.model;

import java.io.Serializable;
import java.util.Date;

public class SaleOrderDetailsDTO implements Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private int itemId;

    private String itemName;

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

    private double netAmount;

    private int tmpId;

    private int groupId;

    private String groupName;

    private int scheduleId;

    private String scheduleName;

    private int manufacturerId;

    private String manufacturerName;

    private String manufacturerCode;

    private int taxId;

    private String taxName;

    private double taxPercentage;

    private double taxAmount;

    private String taxMode;

    private int isGroupTax;

    private String sku;

    private String hsnCode;

    private String invType;

    private int id;

    private int purchaseOrderId;

    private double itemTotalMrp;

    private int customerId;

    private String customerName;
    private String customerPhone;
    
    private int doctorId;
    private int doctorName;
    
    private double advAmount;
    
    private int orderPackQty;

    private int orderLooseQty;

    private String batchNo;

    private String expiryDate;

    private String expiryDateFormat;

    private String itemUniqueKey;

    private String packUnitName;

    private int pendingPackQty;

    private int pendingRecLooseQty;

    private double amount;

    private double edPer;

    private double ed;

    private double taxPer;

    private double tax;

    private double vatPer;

    private double vat;

    private double itemLotAdjAmount;

    private double totAmount;

    private double taxableRate;

    private int isTaxOnMrp;

    private double purchaseNetAmount;
    private int taxTypeId;

    private String mfdDate;
    private int itemFreeAgainstItem;
    private double purchaseCostPerUnit;
    private double purchaseRate;
    private String remarks; // new added 7.5.2019
    private String itemCode; // new added 7.5.2019
    private double taxableAmount;// new added 12.06.2019
    private String itemRemarks;
    private double cgst;
    private double cgstPercentage;
    private double sgst;
    private double sgstPercentage;
    private double igst;
    private double igstPercentage;// new added 12.06.2019
    // 26.6.2019
    private int isDiscount; 
    private double maxDiscountLimit;
    private double discount;
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

	/**
	 * @return the itemUniqueKey
	 */
	public String getItemUniqueKey() {
		return itemUniqueKey;
	}

	/**
	 * @param itemUniqueKey the itemUniqueKey to set
	 */
	public void setItemUniqueKey(String itemUniqueKey) {
		this.itemUniqueKey = itemUniqueKey;
	}

	/**
	 * @return the packUnitName
	 */
	public String getPackUnitName() {
		return packUnitName;
	}

	/**
	 * @param packUnitName the packUnitName to set
	 */
	public void setPackUnitName(String packUnitName) {
		this.packUnitName = packUnitName;
	}

	/**
	 * @return the pendingPackQty
	 */
	public int getPendingPackQty() {
		return pendingPackQty;
	}

	/**
	 * @param pendingPackQty the pendingPackQty to set
	 */
	public void setPendingPackQty(int pendingPackQty) {
		this.pendingPackQty = pendingPackQty;
	}

	/**
	 * @return the pendingRecLooseQty
	 */
	public int getPendingRecLooseQty() {
		return pendingRecLooseQty;
	}

	/**
	 * @param pendingRecLooseQty the pendingRecLooseQty to set
	 */
	public void setPendingRecLooseQty(int pendingRecLooseQty) {
		this.pendingRecLooseQty = pendingRecLooseQty;
	}

	/**
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(double amount) {
		this.amount = amount;
	}

	/**
	 * @return the edPer
	 */
	public double getEdPer() {
		return edPer;
	}

	/**
	 * @param edPer the edPer to set
	 */
	public void setEdPer(double edPer) {
		this.edPer = edPer;
	}

	/**
	 * @return the ed
	 */
	public double getEd() {
		return ed;
	}

	/**
	 * @param ed the ed to set
	 */
	public void setEd(double ed) {
		this.ed = ed;
	}

	/**
	 * @return the taxPer
	 */
	public double getTaxPer() {
		return taxPer;
	}

	/**
	 * @param taxPer the taxPer to set
	 */
	public void setTaxPer(double taxPer) {
		this.taxPer = taxPer;
	}

	/**
	 * @return the tax
	 */
	public double getTax() {
		return tax;
	}

	/**
	 * @param tax the tax to set
	 */
	public void setTax(double tax) {
		this.tax = tax;
	}

	/**
	 * @return the vatPer
	 */
	public double getVatPer() {
		return vatPer;
	}

	/**
	 * @param vatPer the vatPer to set
	 */
	public void setVatPer(double vatPer) {
		this.vatPer = vatPer;
	}

	/**
	 * @return the vat
	 */
	public double getVat() {
		return vat;
	}

	/**
	 * @param vat the vat to set
	 */
	public void setVat(double vat) {
		this.vat = vat;
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
	 * @return the totAmount
	 */
	public double getTotAmount() {
		return totAmount;
	}

	/**
	 * @param totAmount the totAmount to set
	 */
	public void setTotAmount(double totAmount) {
		this.totAmount = totAmount;
	}

	/**
	 * @return the taxableRate
	 */
	public double getTaxableRate() {
		return taxableRate;
	}

	/**
	 * @param taxableRate the taxableRate to set
	 */
	public void setTaxableRate(double taxableRate) {
		this.taxableRate = taxableRate;
	}

	/**
	 * @return the isTaxOnMrp
	 */
	public int getIsTaxOnMrp() {
		return isTaxOnMrp;
	}

	/**
	 * @param isTaxOnMrp the isTaxOnMrp to set
	 */
	public void setIsTaxOnMrp(int isTaxOnMrp) {
		this.isTaxOnMrp = isTaxOnMrp;
	}

	/**
	 * @return the purchaseNetAmount
	 */
	public double getPurchaseNetAmount() {
		return purchaseNetAmount;
	}

	/**
	 * @param purchaseNetAmount the purchaseNetAmount to set
	 */
	public void setPurchaseNetAmount(double purchaseNetAmount) {
		this.purchaseNetAmount = purchaseNetAmount;
	}

	/**
	 * @return the customerId
	 */
	public int getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the customerPhone
	 */
	public String getCustomerPhone() {
		return customerPhone;
	}

	/**
	 * @param customerPhone the customerPhone to set
	 */
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	/**
	 * @return the doctorId
	 */
	public int getDoctorId() {
		return doctorId;
	}

	/**
	 * @param doctorId the doctorId to set
	 */
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	/**
	 * @return the doctorName
	 */
	public int getDoctorName() {
		return doctorName;
	}

	/**
	 * @param doctorName the doctorName to set
	 */
	public void setDoctorName(int doctorName) {
		this.doctorName = doctorName;
	}

	/**
	 * @return the advAmount
	 */
	public double getAdvAmount() {
		return advAmount;
	}

	/**
	 * @param advAmount the advAmount to set
	 */
	public void setAdvAmount(double advAmount) {
		this.advAmount = advAmount;
	}

	/**
	 * @return the orderPackQty
	 */
	public int getOrderPackQty() {
		return orderPackQty;
	}

	/**
	 * @param orderPackQty the orderPackQty to set
	 */
	public void setOrderPackQty(int orderPackQty) {
		this.orderPackQty = orderPackQty;
	}

	/**
	 * @return the orderLooseQty
	 */
	public int getOrderLooseQty() {
		return orderLooseQty;
	}

	/**
	 * @param orderLooseQty the orderLooseQty to set
	 */
	public void setOrderLooseQty(int orderLooseQty) {
		this.orderLooseQty = orderLooseQty;
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
	 * @return the purchaseOrderId
	 */
	public int getPurchaseOrderId() {
		return purchaseOrderId;
	}

	/**
	 * @param purchaseOrderId the purchaseOrderId to set
	 */
	public void setPurchaseOrderId(int purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
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
	 * @return the itemName
	 */
	public String getItemName() {
		return itemName;
	}

	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName) {
		this.itemName = itemName;
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
	 * @return the groupId
	 */
	public int getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the groupName
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName the groupName to set
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @return the scheduleId
	 */
	public int getScheduleId() {
		return scheduleId;
	}

	/**
	 * @param scheduleId the scheduleId to set
	 */
	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	/**
	 * @return the scheduleName
	 */
	public String getScheduleName() {
		return scheduleName;
	}

	/**
	 * @param scheduleName the scheduleName to set
	 */
	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}

	/**
	 * @return the manufacturerId
	 */
	public int getManufacturerId() {
		return manufacturerId;
	}

	/**
	 * @param manufacturerId the manufacturerId to set
	 */
	public void setManufacturerId(int manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	/**
	 * @return the manufacturerName
	 */
	public String getManufacturerName() {
		return manufacturerName;
	}

	/**
	 * @param manufacturerName the manufacturerName to set
	 */
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	/**
	 * @return the manufacturerCode
	 */
	public String getManufacturerCode() {
		return manufacturerCode;
	}

	/**
	 * @param manufacturerCode the manufacturerCode to set
	 */
	public void setManufacturerCode(String manufacturerCode) {
		this.manufacturerCode = manufacturerCode;
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
	 * @return the taxName
	 */
	public String getTaxName() {
		return taxName;
	}

	/**
	 * @param taxName the taxName to set
	 */
	public void setTaxName(String taxName) {
		this.taxName = taxName;
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
	 * @return the sku
	 */
	public String getSku() {
		return sku;
	}

	/**
	 * @param sku the sku to set
	 */
	public void setSku(String sku) {
		this.sku = sku;
	}

	/**
	 * @return the hsnCode
	 */
	public String getHsnCode() {
		return hsnCode;
	}

	/**
	 * @param hsnCode the hsnCode to set
	 */
	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
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
	 * @return the purchaseCostPerUnit
	 */
	public double getPurchaseCostPerUnit() {
		return purchaseCostPerUnit;
	}

	/**
	 * @param purchaseCostPerUnit the purchaseCostPerUnit to set
	 */
	public void setPurchaseCostPerUnit(double purchaseCostPerUnit) {
		this.purchaseCostPerUnit = purchaseCostPerUnit;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	
	

	public double getTaxableAmount() {
		return taxableAmount;
	}

	public void setTaxableAmount(double taxableAmount) {
		this.taxableAmount = taxableAmount;
	}

	public String getItemRemarks() {
		return itemRemarks;
	}

	public void setItemRemarks(String itemRemarks) {
		this.itemRemarks = itemRemarks;
	}
	

	public double getCgst() {
		return cgst;
	}

	public void setCgst(double cgst) {
		this.cgst = cgst;
	}

	public double getCgstPercentage() {
		return cgstPercentage;
	}

	public void setCgstPercentage(double cgstPercentage) {
		this.cgstPercentage = cgstPercentage;
	}

	public double getSgst() {
		return sgst;
	}

	public void setSgst(double sgst) {
		this.sgst = sgst;
	}

	public double getSgstPercentage() {
		return sgstPercentage;
	}

	public void setSgstPercentage(double sgstPercentage) {
		this.sgstPercentage = sgstPercentage;
	}

	public double getIgst() {
		return igst;
	}

	public void setIgst(double igst) {
		this.igst = igst;
	}

	public double getIgstPercentage() {
		return igstPercentage;
	}

	public void setIgstPercentage(double igstPercentage) {
		this.igstPercentage = igstPercentage;
	}

	public int getIsDiscount() {
		return isDiscount;
	}

	public void setIsDiscount(int isDiscount) {
		this.isDiscount = isDiscount;
	}

	public double getMaxDiscountLimit() {
		return maxDiscountLimit;
	}

	public void setMaxDiscountLimit(double maxDiscountLimit) {
		this.maxDiscountLimit = maxDiscountLimit;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	@Override
	public String toString() {
		return "SaleOrderDetailsDTO [itemId=" + itemId + ", itemName=" + itemName + ", packUnitId=" + packUnitId
				+ ", packQty=" + packQty + ", conversion=" + conversion + ", looseQty=" + looseQty + ", freeQty="
				+ freeQty + ", soldPackQty=" + soldPackQty + ", soldLooseQty=" + soldLooseQty + ", mrp=" + mrp
				+ ", rate=" + rate + ", saleRate=" + saleRate + ", grossAmount=" + grossAmount + ", discPer=" + discPer
				+ ", disc=" + disc + ", netAmount=" + netAmount + ", tmpId=" + tmpId + ", groupId=" + groupId
				+ ", groupName=" + groupName + ", scheduleId=" + scheduleId + ", scheduleName=" + scheduleName
				+ ", manufacturerId=" + manufacturerId + ", manufacturerName=" + manufacturerName
				+ ", manufacturerCode=" + manufacturerCode + ", taxId=" + taxId + ", taxName=" + taxName
				+ ", taxPercentage=" + taxPercentage + ", taxAmount=" + taxAmount + ", taxMode=" + taxMode
				+ ", isGroupTax=" + isGroupTax + ", sku=" + sku + ", hsnCode=" + hsnCode + ", invType=" + invType
				+ ", id=" + id + ", purchaseOrderId=" + purchaseOrderId + ", itemTotalMrp=" + itemTotalMrp
				+ ", customerId=" + customerId + ", customerName=" + customerName + ", customerPhone=" + customerPhone
				+ ", doctorId=" + doctorId + ", doctorName=" + doctorName + ", advAmount=" + advAmount
				+ ", orderPackQty=" + orderPackQty + ", orderLooseQty=" + orderLooseQty + ", batchNo=" + batchNo
				+ ", expiryDate=" + expiryDate + ", expiryDateFormat=" + expiryDateFormat + ", itemUniqueKey="
				+ itemUniqueKey + ", packUnitName=" + packUnitName + ", pendingPackQty=" + pendingPackQty
				+ ", pendingRecLooseQty=" + pendingRecLooseQty + ", amount=" + amount + ", edPer=" + edPer + ", ed="
				+ ed + ", taxPer=" + taxPer + ", tax=" + tax + ", vatPer=" + vatPer + ", vat=" + vat
				+ ", itemLotAdjAmount=" + itemLotAdjAmount + ", totAmount=" + totAmount + ", taxableRate=" + taxableRate
				+ ", isTaxOnMrp=" + isTaxOnMrp + ", purchaseNetAmount=" + purchaseNetAmount + ", taxTypeId=" + taxTypeId
				+ ", mfdDate=" + mfdDate + ", itemFreeAgainstItem=" + itemFreeAgainstItem + ", purchaseCostPerUnit="
				+ purchaseCostPerUnit + ", purchaseRate=" + purchaseRate + ", remarks=" + remarks + ", itemCode="
				+ itemCode + ", taxableAmount=" + taxableAmount + ", itemRemarks=" + itemRemarks + ", cgst=" + cgst
				+ ", cgstPercentage=" + cgstPercentage + ", sgst=" + sgst + ", sgstPercentage=" + sgstPercentage
				+ ", igst=" + igst + ", igstPercentage=" + igstPercentage + ", isDiscount=" + isDiscount
				+ ", maxDiscountLimit=" + maxDiscountLimit + ", discount=" + discount + "]";
	}

	
	

}
