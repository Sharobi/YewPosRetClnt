package com.sharobi.yewpos.inv.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Manodip
 */

public class ItemCurrentStockDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int itemId;
	private String batchNo;
	private Date expiryDate;
	private String expiryDateFormat;
	private int packUnitId;
	private int conversion;
	private double mrp;
	private double packQty;
	private double looseQty;
	private String packUnitName;
	private String contentName;
	private String manufacturerName;
	private String netContent;
	private String stockQty;
	private String rackName;
	private String holdQty;
	private double calculateLooseHoldQty;
	private String groupName;
	private int looseUnitId;
	private String looseUnitName;
	private int scheduleId;
	private String scheduleName;
	private double calculateLooseQty;
	private String itemUniqueKey;
	private int expiryStatusMode;
	private String expiryStatus;
	private double vatPer;
	private double taxPer;
	private String itemName;
	private String sku;
	private String hsnCode;
	private int taxId;
	private String taxName;
	private double taxPercentage;
	private String taxMode;
	private int isGroupTax;
	private double discount;
	private int isDiscount;
	private double maxDiscountLimit;
	private double purchaseCostPerUnit;
	private String note;
    private double saleRate;
    private double purchaseRate;
    private int claculateLooseReorderLevelQty;
    private int stockRequired;
	private int batchWiseStock;
	private int expiryDateRequired;
	private int expiryMonthRequired;
	private int dualStockRequired;
	private int locationRequired;
	private int priceListRequired;
	private int sizeWiseStockRequired;
	private int colourWiseStockRequired;
	private int warrantyRequired;
	private int warrantyMonth;
	private int mrpRequired;
	private int serialNoRequired;
	private int serialNoSuffRequired;
	private int serialNoPrefRequired;
	private int rateOn;
	private String serialNoType;
	private String warrantyTypeOn;
    private String size;
    private String sizeType;
    private String colour;
    private int locationId;
    private String mfdDate;
    private int taxTypeId;

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

	public int getConversion() {
		return conversion;
	}

	public void setConversion(int conversion) {
		this.conversion = conversion;
	}

	public double getMrp() {
		return mrp;
	}

	public void setMrp(double mrp) {
		this.mrp = mrp;
	}

	public double getPackQty() {
		return packQty;
	}

	public void setPackQty(double packQty) {
		this.packQty = packQty;
	}

	public double getLooseQty() {
		return looseQty;
	}

	public void setLooseQty(double looseQty) {
		this.looseQty = looseQty;
	}

	public String getPackUnitName() {
		return packUnitName;
	}

	public void setPackUnitName(String packUnitName) {
		this.packUnitName = packUnitName;
	}

	public String getContentName() {
		return contentName;
	}

	public void setContentName(String contentName) {
		this.contentName = contentName;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}

	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}

	public String getNetContent() {
		return netContent;
	}

	public void setNetContent(String netContent) {
		this.netContent = netContent;
	}

	public String getStockQty() {
		return stockQty;
	}

	public void setStockQty(String stockQty) {
		this.stockQty = stockQty;
	}

	public String getRackName() {
		return rackName;
	}

	public void setRackName(String rackName) {
		this.rackName = rackName;
	}

	public String getHoldQty() {
		return holdQty;
	}

	public void setHoldQty(String holdQty) {
		this.holdQty = holdQty;
	}

	public double getCalculateLooseHoldQty() {
		return calculateLooseHoldQty;
	}

	public void setCalculateLooseHoldQty(double calculateLooseHoldQty) {
		this.calculateLooseHoldQty = calculateLooseHoldQty;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public int getLooseUnitId() {
		return looseUnitId;
	}

	public void setLooseUnitId(int looseUnitId) {
		this.looseUnitId = looseUnitId;
	}

	public String getLooseUnitName() {
		return looseUnitName;
	}

	public void setLooseUnitName(String looseUnitName) {
		this.looseUnitName = looseUnitName;
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

	public double getCalculateLooseQty() {
		return calculateLooseQty;
	}

	public void setCalculateLooseQty(double calculateLooseQty) {
		this.calculateLooseQty = calculateLooseQty;
	}

	public String getItemUniqueKey() {
		return itemUniqueKey;
	}

	public void setItemUniqueKey(String itemUniqueKey) {
		this.itemUniqueKey = itemUniqueKey;
	}

	public int getExpiryStatusMode() {
		return expiryStatusMode;
	}

	public void setExpiryStatusMode(int expiryStatusMode) {
		this.expiryStatusMode = expiryStatusMode;
	}

	public String getExpiryStatus() {
		return expiryStatus;
	}

	public void setExpiryStatus(String expiryStatus) {
		this.expiryStatus = expiryStatus;
	}

	public double getVatPer() {
		return vatPer;
	}

	public void setVatPer(double vatPer) {
		this.vatPer = vatPer;
	}

	public double getTaxPer() {
		return taxPer;
	}

	public void setTaxPer(double taxPer) {
		this.taxPer = taxPer;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
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
	
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public double getSaleRate() {
		return saleRate;
	}

	public void setSaleRate(double saleRate) {
		this.saleRate = saleRate;
	}

	public double getPurchaseRate() {
		return purchaseRate;
	}

	public void setPurchaseRate(double purchaseRate) {
		this.purchaseRate = purchaseRate;
	}
	
	public int getClaculateLooseReorderLevelQty() {
		return claculateLooseReorderLevelQty;
	}

	public void setClaculateLooseReorderLevelQty(int claculateLooseReorderLevelQty) {
		this.claculateLooseReorderLevelQty = claculateLooseReorderLevelQty;
	}

	/**
	 * @return the stockRequired
	 */
	public int getStockRequired() {
		return stockRequired;
	}

	/**
	 * @param stockRequired the stockRequired to set
	 */
	public void setStockRequired(int stockRequired) {
		this.stockRequired = stockRequired;
	}

	/**
	 * @return the batchWiseStock
	 */
	public int getBatchWiseStock() {
		return batchWiseStock;
	}

	/**
	 * @param batchWiseStock the batchWiseStock to set
	 */
	public void setBatchWiseStock(int batchWiseStock) {
		this.batchWiseStock = batchWiseStock;
	}

	/**
	 * @return the expiryDateRequired
	 */
	public int getExpiryDateRequired() {
		return expiryDateRequired;
	}

	/**
	 * @param expiryDateRequired the expiryDateRequired to set
	 */
	public void setExpiryDateRequired(int expiryDateRequired) {
		this.expiryDateRequired = expiryDateRequired;
	}

	/**
	 * @return the expiryMonthRequired
	 */
	public int getExpiryMonthRequired() {
		return expiryMonthRequired;
	}

	/**
	 * @param expiryMonthRequired the expiryMonthRequired to set
	 */
	public void setExpiryMonthRequired(int expiryMonthRequired) {
		this.expiryMonthRequired = expiryMonthRequired;
	}

	/**
	 * @return the dualStockRequired
	 */
	public int getDualStockRequired() {
		return dualStockRequired;
	}

	/**
	 * @param dualStockRequired the dualStockRequired to set
	 */
	public void setDualStockRequired(int dualStockRequired) {
		this.dualStockRequired = dualStockRequired;
	}

	/**
	 * @return the locationRequired
	 */
	public int getLocationRequired() {
		return locationRequired;
	}

	/**
	 * @param locationRequired the locationRequired to set
	 */
	public void setLocationRequired(int locationRequired) {
		this.locationRequired = locationRequired;
	}

	/**
	 * @return the priceListRequired
	 */
	public int getPriceListRequired() {
		return priceListRequired;
	}

	/**
	 * @param priceListRequired the priceListRequired to set
	 */
	public void setPriceListRequired(int priceListRequired) {
		this.priceListRequired = priceListRequired;
	}

	/**
	 * @return the sizeWiseStockRequired
	 */
	public int getSizeWiseStockRequired() {
		return sizeWiseStockRequired;
	}

	/**
	 * @param sizeWiseStockRequired the sizeWiseStockRequired to set
	 */
	public void setSizeWiseStockRequired(int sizeWiseStockRequired) {
		this.sizeWiseStockRequired = sizeWiseStockRequired;
	}

	/**
	 * @return the colourWiseStockRequired
	 */
	public int getColourWiseStockRequired() {
		return colourWiseStockRequired;
	}

	/**
	 * @param colourWiseStockRequired the colourWiseStockRequired to set
	 */
	public void setColourWiseStockRequired(int colourWiseStockRequired) {
		this.colourWiseStockRequired = colourWiseStockRequired;
	}

	/**
	 * @return the warrantyRequired
	 */
	public int getWarrantyRequired() {
		return warrantyRequired;
	}

	/**
	 * @param warrantyRequired the warrantyRequired to set
	 */
	public void setWarrantyRequired(int warrantyRequired) {
		this.warrantyRequired = warrantyRequired;
	}

	/**
	 * @return the warrantyMonth
	 */
	public int getWarrantyMonth() {
		return warrantyMonth;
	}

	/**
	 * @param warrantyMonth the warrantyMonth to set
	 */
	public void setWarrantyMonth(int warrantyMonth) {
		this.warrantyMonth = warrantyMonth;
	}

	/**
	 * @return the mrpRequired
	 */
	public int getMrpRequired() {
		return mrpRequired;
	}

	/**
	 * @param mrpRequired the mrpRequired to set
	 */
	public void setMrpRequired(int mrpRequired) {
		this.mrpRequired = mrpRequired;
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
	 * @return the serialNoSuffRequired
	 */
	public int getSerialNoSuffRequired() {
		return serialNoSuffRequired;
	}

	/**
	 * @param serialNoSuffRequired the serialNoSuffRequired to set
	 */
	public void setSerialNoSuffRequired(int serialNoSuffRequired) {
		this.serialNoSuffRequired = serialNoSuffRequired;
	}

	/**
	 * @return the serialNoPrefRequired
	 */
	public int getSerialNoPrefRequired() {
		return serialNoPrefRequired;
	}

	/**
	 * @param serialNoPrefRequired the serialNoPrefRequired to set
	 */
	public void setSerialNoPrefRequired(int serialNoPrefRequired) {
		this.serialNoPrefRequired = serialNoPrefRequired;
	}

	/**
	 * @return the rateOn
	 */
	public int getRateOn() {
		return rateOn;
	}

	/**
	 * @param rateOn the rateOn to set
	 */
	public void setRateOn(int rateOn) {
		this.rateOn = rateOn;
	}

	/**
	 * @return the serialNoType
	 */
	public String getSerialNoType() {
		return serialNoType;
	}

	/**
	 * @param serialNoType the serialNoType to set
	 */
	public void setSerialNoType(String serialNoType) {
		this.serialNoType = serialNoType;
	}

	/**
	 * @return the warrantyTypeOn
	 */
	public String getWarrantyTypeOn() {
		return warrantyTypeOn;
	}

	/**
	 * @param warrantyTypeOn the warrantyTypeOn to set
	 */
	public void setWarrantyTypeOn(String warrantyTypeOn) {
		this.warrantyTypeOn = warrantyTypeOn;
	}

	/**
	 * @return the size
	 */
	public String getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(String size) {
		this.size = size;
	}

	/**
	 * @return the sizeType
	 */
	public String getSizeType() {
		return sizeType;
	}

	/**
	 * @param sizeType the sizeType to set
	 */
	public void setSizeType(String sizeType) {
		this.sizeType = sizeType;
	}

	/**
	 * @return the colour
	 */
	public String getColour() {
		return colour;
	}

	/**
	 * @param colour the colour to set
	 */
	public void setColour(String colour) {
		this.colour = colour;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ItemCurrentStockDTO [itemId=" + itemId + ", batchNo=" + batchNo
				+ ", expiryDate=" + expiryDate + ", expiryDateFormat="
				+ expiryDateFormat + ", packUnitId=" + packUnitId
				+ ", conversion=" + conversion + ", mrp=" + mrp + ", packQty="
				+ packQty + ", looseQty=" + looseQty + ", packUnitName="
				+ packUnitName + ", contentName=" + contentName
				+ ", manufacturerName=" + manufacturerName + ", netContent="
				+ netContent + ", stockQty=" + stockQty + ", rackName="
				+ rackName + ", holdQty=" + holdQty
				+ ", calculateLooseHoldQty=" + calculateLooseHoldQty
				+ ", groupName=" + groupName + ", looseUnitId=" + looseUnitId
				+ ", looseUnitName=" + looseUnitName + ", scheduleId="
				+ scheduleId + ", scheduleName=" + scheduleName
				+ ", calculateLooseQty=" + calculateLooseQty
				+ ", itemUniqueKey=" + itemUniqueKey + ", expiryStatusMode="
				+ expiryStatusMode + ", expiryStatus=" + expiryStatus
				+ ", vatPer=" + vatPer + ", taxPer=" + taxPer + ", itemName="
				+ itemName + ", sku=" + sku + ", hsnCode=" + hsnCode
				+ ", taxId=" + taxId + ", taxName=" + taxName
				+ ", taxPercentage=" + taxPercentage + ", taxMode=" + taxMode
				+ ", isGroupTax=" + isGroupTax + ", discount=" + discount
				+ ", isDiscount=" + isDiscount + ", maxDiscountLimit="
				+ maxDiscountLimit + ", purchaseCostPerUnit="
				+ purchaseCostPerUnit + ", note=" + note + ", saleRate="
				+ saleRate + ", purchaseRate=" + purchaseRate
				+ ", claculateLooseReorderLevelQty="
				+ claculateLooseReorderLevelQty + ", stockRequired="
				+ stockRequired + ", batchWiseStock=" + batchWiseStock
				+ ", expiryDateRequired=" + expiryDateRequired
				+ ", expiryMonthRequired=" + expiryMonthRequired
				+ ", dualStockRequired=" + dualStockRequired
				+ ", locationRequired=" + locationRequired
				+ ", priceListRequired=" + priceListRequired
				+ ", sizeWiseStockRequired=" + sizeWiseStockRequired
				+ ", colourWiseStockRequired=" + colourWiseStockRequired
				+ ", warrantyRequired=" + warrantyRequired + ", warrantyMonth="
				+ warrantyMonth + ", mrpRequired=" + mrpRequired
				+ ", serialNoRequired=" + serialNoRequired
				+ ", serialNoSuffRequired=" + serialNoSuffRequired
				+ ", serialNoPrefRequired=" + serialNoPrefRequired
				+ ", rateOn=" + rateOn + ", serialNoType=" + serialNoType
				+ ", warrantyTypeOn=" + warrantyTypeOn + ", size=" + size
				+ ", sizeType=" + sizeType + ", colour=" + colour
				+ ", locationId=" + locationId + ", mfdDate=" + mfdDate
				+ ", taxTypeId=" + taxTypeId + "]";
	}

}
