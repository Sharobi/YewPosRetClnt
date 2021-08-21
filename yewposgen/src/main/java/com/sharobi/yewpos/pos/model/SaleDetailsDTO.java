package com.sharobi.yewpos.pos.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author Manodip
 */

/**
 * @author Manodip Jana
 *
 * May 17, 2018
 */
public class SaleDetailsDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int itemId;
	private String itemName;
	private String batchNo;
	private String expiryDate;
	private String expiryDateFormat;
	private int packUnitId;
	private String packUnitName;
	private double packQty;
	private int conversion;
	private int looseUnitId;
	private String looseUnitName;
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
	private int groupId;
	private String groupName;
	private int scheduleId;
	private String scheduleName;
	private int manufacturerId;
	private String manufacturerName;
	private String manufacturerCode;
	private int contentId;
    private String contentName;
    private double mrpPerUnit;
    private double ratePerUnit;
    private String itemUniqueKey;
    private int taxId;
    private String taxName;
    private double taxPercentage;
    private double taxAmount;
    private int isGroupTax;
    private String taxMode;
    private String sku;
    private String hsnCode;
    private double discount;
    private int isDiscount;
    private double maxDiscountLimit;
    private double purchaseCostPerUnit;
    private double totalAmount;
    private double taxableAmount;
    private double cgst;
    private double cgstPercentage;
    private double sgst;
    private double sgstPercentage;
    private double igst;
    private double igstPercentage;
    private double itemTotalMrp;
    private double saleRate;
    private String netContent;
    private double purchaseRate;
    private int taxTypeId;
    private List<SalesDetailsSerialMapper> salesDetailsSerialMapper;
    private double itemLotAdjAmount;
    private int serialNoRequired;
    private String serialNoDtl;
    private int itemdualstock;
    private int salesmanId;
    private double salesmanFactor;
    private double salesmanAmount;

    private double freeQty;
    private int itemFreeAgainstItem;
    private int saleOrderId;
    
    private String itemCode; // new added 7.5.2019
    private String remarks;// new added 7.5.2019
    private String salesmanName; // new added 7.5.2019
    private String itemRemarks;// new added 7.5.2019
    private String saleOrderInvNo; //  new added 21.5.2019
    /**
	 * @return the netContent
	 */
	public String getNetContent() {
		return netContent;
	}

	/**
	 * @param netContent the netContent to set
	 */
	public void setNetContent(String netContent) {
		this.netContent = netContent;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public double getTaxableAmount() {
		return taxableAmount;
	}

	public void setTaxableAmount(double taxableAmount) {
		this.taxableAmount = taxableAmount;
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

	public String getManufacturerCode() {
		return manufacturerCode;
	}

	public void setManufacturerCode(String manufacturerCode) {
		this.manufacturerCode = manufacturerCode;
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

	public String getLooseUnitName() {
		return looseUnitName;
	}

	public void setLooseUnitName(String looseUnitName) {
		this.looseUnitName = looseUnitName;
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

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public String getScheduleName() {
		return scheduleName;
	}

	public void setScheduleName(String scheduleName) {
		this.scheduleName = scheduleName;
	}

	public int getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(int manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public String getContentName() {
		return contentName;
	}

	public void setContentName(String contentName) {
		this.contentName = contentName;
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

	public int getLooseUnitId() {
		return looseUnitId;
	}

	public void setLooseUnitId(int looseUnitId) {
		this.looseUnitId = looseUnitId;
	}

	public String getItemUniqueKey() {
		return itemUniqueKey;
	}

	public void setItemUniqueKey(String itemUniqueKey) {
		this.itemUniqueKey = itemUniqueKey;
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

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getHsnCode() {
		return hsnCode;
	}

	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
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
	 * @return the serialNoRequired
	 */
	public int getSerialNoRequired() {
		return serialNoRequired;
	}

	/**
	 * @param serialNoRequired the serialNoRequired to set
	 */
	public void setSerialNoRequired(int serialNoRequired) {
		this.serialNoRequired = serialNoRequired;
	}


	/**
	 * @return the serialNoDtl
	 */
	public String getSerialNoDtl() {
		return serialNoDtl;
	}

	/**
	 * @param serialNoDtl the serialNoDtl to set
	 */
	public void setSerialNoDtl(String serialNoDtl) {
		this.serialNoDtl = serialNoDtl;
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
	

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
	}
	
	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	
	public String getSalesmanName() {
		return salesmanName;
	}

	public void setSalesmanName(String salesmanName) {
		this.salesmanName = salesmanName;
	}

	

	public String getItemRemarks() {
		return itemRemarks;
	}

	public void setItemRemarks(String itemRemarks) {
		this.itemRemarks = itemRemarks;
	}

	

	public String getSaleOrderInvNo() {
		return saleOrderInvNo;
	}

	public void setSaleOrderInvNo(String saleOrderInvNo) {
		this.saleOrderInvNo = saleOrderInvNo;
	}

	@Override
	public String toString() {
		return "SaleDetailsDTO [itemId=" + itemId + ", itemName=" + itemName + ", batchNo=" + batchNo + ", expiryDate="
				+ expiryDate + ", expiryDateFormat=" + expiryDateFormat + ", packUnitId=" + packUnitId
				+ ", packUnitName=" + packUnitName + ", packQty=" + packQty + ", conversion=" + conversion
				+ ", looseUnitId=" + looseUnitId + ", looseUnitName=" + looseUnitName + ", looseQty=" + looseQty
				+ ", mrp=" + mrp + ", rate=" + rate + ", amount=" + amount + ", edPer=" + edPer + ", ed=" + ed
				+ ", taxPer=" + taxPer + ", tax=" + tax + ", vatPer=" + vatPer + ", vat=" + vat + ", discPer=" + discPer
				+ ", disc=" + disc + ", totAmount=" + totAmount + ", groupId=" + groupId + ", groupName=" + groupName
				+ ", scheduleId=" + scheduleId + ", scheduleName=" + scheduleName + ", manufacturerId=" + manufacturerId
				+ ", manufacturerName=" + manufacturerName + ", manufacturerCode=" + manufacturerCode + ", contentId="
				+ contentId + ", contentName=" + contentName + ", mrpPerUnit=" + mrpPerUnit + ", ratePerUnit="
				+ ratePerUnit + ", itemUniqueKey=" + itemUniqueKey + ", taxId=" + taxId + ", taxName=" + taxName
				+ ", taxPercentage=" + taxPercentage + ", taxAmount=" + taxAmount + ", isGroupTax=" + isGroupTax
				+ ", taxMode=" + taxMode + ", sku=" + sku + ", hsnCode=" + hsnCode + ", discount=" + discount
				+ ", isDiscount=" + isDiscount + ", maxDiscountLimit=" + maxDiscountLimit + ", purchaseCostPerUnit="
				+ purchaseCostPerUnit + ", totalAmount=" + totalAmount + ", taxableAmount=" + taxableAmount + ", cgst="
				+ cgst + ", cgstPercentage=" + cgstPercentage + ", sgst=" + sgst + ", sgstPercentage=" + sgstPercentage
				+ ", igst=" + igst + ", igstPercentage=" + igstPercentage + ", itemTotalMrp=" + itemTotalMrp
				+ ", saleRate=" + saleRate + ", netContent=" + netContent + ", purchaseRate=" + purchaseRate
				+ ", taxTypeId=" + taxTypeId + ", salesDetailsSerialMapper=" + salesDetailsSerialMapper
				+ ", itemLotAdjAmount=" + itemLotAdjAmount + ", serialNoRequired=" + serialNoRequired + ", serialNoDtl="
				+ serialNoDtl + ", itemdualstock=" + itemdualstock + ", salesmanId=" + salesmanId + ", salesmanFactor="
				+ salesmanFactor + ", salesmanAmount=" + salesmanAmount + ", freeQty=" + freeQty
				+ ", itemFreeAgainstItem=" + itemFreeAgainstItem + ", saleOrderId=" + saleOrderId + ", itemCode="
				+ itemCode + ", remarks=" + remarks + ", salesmanName=" + salesmanName + ", itemRemarks=" + itemRemarks
				+ ", saleOrderInvNo=" + saleOrderInvNo + "]";
	}

	
	
}
