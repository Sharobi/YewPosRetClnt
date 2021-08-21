/**
 * 
 */
package com.sharobi.yewpos.proc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Manodip Jana
 * 
 * 
 */
public class PurchaseReturnDetailsDTO implements Serializable {

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
	private int looseQty;
	private double freeQty;
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
	private int purchaseId;
	private String purchaseInvNo;
	private int hidePackQty;
	private int hideLooseQty;
	private int scheduleId;
	private String scheduleName;
	private int manufacturerId;
	private String manufacturerName;
	private String manufacturerCode;
	private int remainingPackQty;
	private int remainingLooseQty;
	private int grpId;  
    private String grpName;
    private String itemUniqueKey;
    private double itemLotAdjAmount;
    private String stockQty; 
    private int taxId;
    private String taxName;
    private double taxPercentage;
    private double taxAmount;
    private int isGroupTax;
    private String taxMode;
    private String sku;
    private String hsnCode; 
    private int reasonId;
    private String reasonTypeName; 
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
    private double discount;
    private int isDiscount;
    private int purchaseReturnId;
    private List<PurchaseReturnDetailsSerialMapper> purchaseReturnDetailsSerialMapper;

    
	/**
	 * @return the purchaseReturnId
	 */
	public int getPurchaseReturnId() {
		return purchaseReturnId;
	}

	/**
	 * @param purchaseReturnId the purchaseReturnId to set
	 */
	public void setPurchaseReturnId(int purchaseReturnId) {
		this.purchaseReturnId = purchaseReturnId;
	}

	/**
	 * @return the purchaseReturnDetailsSerialMapper
	 */
	public List<PurchaseReturnDetailsSerialMapper> getPurchaseReturnDetailsSerialMapper() {
		return purchaseReturnDetailsSerialMapper;
	}

	/**
	 * @param purchaseReturnDetailsSerialMapper the purchaseReturnDetailsSerialMapper to set
	 */
	public void setPurchaseReturnDetailsSerialMapper(
			List<PurchaseReturnDetailsSerialMapper> purchaseReturnDetailsSerialMapper) {
		this.purchaseReturnDetailsSerialMapper = purchaseReturnDetailsSerialMapper;
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
	 * @return the discount
	 */
	public double getDiscount() {
		return discount;
	}

	/**
	 * @param discount the discount to set
	 */
	public void setDiscount(double discount) {
		this.discount = discount;
	}

	/**
	 * @return the isDiscount
	 */
	public int getIsDiscount() {
		return isDiscount;
	}

	/**
	 * @param isDiscount the isDiscount to set
	 */
	public void setIsDiscount(int isDiscount) {
		this.isDiscount = isDiscount;
	}

	public int getReasonId() {
		return reasonId;
	}

	public void setReasonId(int reasonId) {
		this.reasonId = reasonId;
	}

	public String getReasonTypeName() {
		return reasonTypeName;
	}

	public void setReasonTypeName(String reasonTypeName) {
		this.reasonTypeName = reasonTypeName;
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

	public String getStockQty() {
		return stockQty;
	}

	public void setStockQty(String stockQty) {
		this.stockQty = stockQty;
	}

	public double getItemLotAdjAmount() {
		return itemLotAdjAmount;
	}

	public void setItemLotAdjAmount(double itemLotAdjAmount) {
		this.itemLotAdjAmount = itemLotAdjAmount;
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

	public void setPackQty(int packQty) {
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

	public double getFreeQty() {
		return freeQty;
	}

	public void setFreeQty(double freeQty) {
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

	public double getTotAmount() {
		return totAmount;
	}

	public void setTotAmount(double totAmount) {
		this.totAmount = totAmount;
	}

	public int getPurchaseId() {
		return purchaseId;
	}

	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
	}

	public String getPurchaseInvNo() {
		return purchaseInvNo;
	}

	public void setPurchaseInvNo(String purchaseInvNo) {
		this.purchaseInvNo = purchaseInvNo;
	}

	public int getHidePackQty() {
		return hidePackQty;
	}

	public void setHidePackQty(int hidePackQty) {
		this.hidePackQty = hidePackQty;
	}

	public int getHideLooseQty() {
		return hideLooseQty;
	}

	public void setHideLooseQty(int hideLooseQty) {
		this.hideLooseQty = hideLooseQty;
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

	public String getManufacturerCode() {
		return manufacturerCode;
	}

	public void setManufacturerCode(String manufacturerCode) {
		this.manufacturerCode = manufacturerCode;
	}

	public int getRemainingPackQty() {
		return remainingPackQty;
	}

	public void setRemainingPackQty(int remainingPackQty) {
		this.remainingPackQty = remainingPackQty;
	}

	public int getRemainingLooseQty() {
		return remainingLooseQty;
	}

	public void setRemainingLooseQty(int remainingLooseQty) {
		this.remainingLooseQty = remainingLooseQty;
	}

	public int getGrpId() {
		return grpId;
	}

	public void setGrpId(int grpId) {
		this.grpId = grpId;
	}

	public String getGrpName() {
		return grpName;
	}

	public void setGrpName(String grpName) {
		this.grpName = grpName;
	}
	
	public String getItemUniqueKey() {
		return itemUniqueKey;
	}

	public void setItemUniqueKey(String itemUniqueKey) {
		this.itemUniqueKey = itemUniqueKey;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PurchaseReturnDetailsDTO [itemId=" + itemId + ", itemName=" + itemName + ", batchNo=" + batchNo
				+ ", expiryDate=" + expiryDate + ", expiryDateFormat=" + expiryDateFormat + ", packUnitId=" + packUnitId
				+ ", packUnitName=" + packUnitName + ", packQty=" + packQty + ", conversion=" + conversion
				+ ", looseQty=" + looseQty + ", freeQty=" + freeQty + ", mrp=" + mrp + ", rate=" + rate + ", amount="
				+ amount + ", edPer=" + edPer + ", ed=" + ed + ", taxPer=" + taxPer + ", tax=" + tax + ", vatPer="
				+ vatPer + ", vat=" + vat + ", discPer=" + discPer + ", disc=" + disc + ", totAmount=" + totAmount
				+ ", purchaseId=" + purchaseId + ", purchaseInvNo=" + purchaseInvNo + ", hidePackQty=" + hidePackQty
				+ ", hideLooseQty=" + hideLooseQty + ", scheduleId=" + scheduleId + ", scheduleName=" + scheduleName
				+ ", manufacturerId=" + manufacturerId + ", manufacturerName=" + manufacturerName
				+ ", manufacturerCode=" + manufacturerCode + ", remainingPackQty=" + remainingPackQty
				+ ", remainingLooseQty=" + remainingLooseQty + ", grpId=" + grpId + ", grpName=" + grpName
				+ ", itemUniqueKey=" + itemUniqueKey + ", itemLotAdjAmount=" + itemLotAdjAmount + ", stockQty="
				+ stockQty + ", taxId=" + taxId + ", taxName=" + taxName + ", taxPercentage=" + taxPercentage
				+ ", taxAmount=" + taxAmount + ", isGroupTax=" + isGroupTax + ", taxMode=" + taxMode + ", sku=" + sku
				+ ", hsnCode=" + hsnCode + ", reasonId=" + reasonId + ", reasonTypeName=" + reasonTypeName
				+ ", stockRequired=" + stockRequired + ", batchWiseStock=" + batchWiseStock + ", expiryDateRequired="
				+ expiryDateRequired + ", expiryMonthRequired=" + expiryMonthRequired + ", dualStockRequired="
				+ dualStockRequired + ", locationRequired=" + locationRequired + ", priceListRequired="
				+ priceListRequired + ", sizeWiseStockRequired=" + sizeWiseStockRequired + ", colourWiseStockRequired="
				+ colourWiseStockRequired + ", warrantyRequired=" + warrantyRequired + ", warrantyMonth="
				+ warrantyMonth + ", mrpRequired=" + mrpRequired + ", serialNoRequired=" + serialNoRequired
				+ ", serialNoSuffRequired=" + serialNoSuffRequired + ", serialNoPrefRequired=" + serialNoPrefRequired
				+ ", rateOn=" + rateOn + ", serialNoType=" + serialNoType + ", warrantyTypeOn=" + warrantyTypeOn
				+ ", size=" + size + ", sizeType=" + sizeType + ", colour=" + colour + ", discount=" + discount
				+ ", isDiscount=" + isDiscount + ", purchaseReturnId=" + purchaseReturnId
				+ ", purchaseReturnDetailsSerialMapper=" + purchaseReturnDetailsSerialMapper + "]";
	}

			
}
