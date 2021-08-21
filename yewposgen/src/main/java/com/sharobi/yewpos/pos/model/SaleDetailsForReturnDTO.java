
package com.sharobi.yewpos.pos.model;

import java.util.Date;
import java.util.List;

/**
 * @author Manodip Jana
 *
 *
 */
public class SaleDetailsForReturnDTO {
	private int saleId;
    private String saleInvNo;
    private Date saleInvDate;
    private String saleInvTime;
    private String saleInvModeName;
    private double grossAmount;
    private double edAmount;
    private double discAmount;
    private double vatAmount;
    private double taxAmount;
    private double adjAmount;
    private double roundOff;
    private double netAmount;
    private double cashAmount;
    private double cardAmount;
    private double creditAmount;
    private int customerId;
    private String customerName;
    private String customerPhone;
    private String customerAddress;
    private int doctorId;
    private String doctorName;
    private int itemId;
    private String itemName;
    private String batchNo;
    private Date expiryDate;
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
    private double mrpPerUnit;
    private double ratePerUnit;
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
    private int calculateLooseQty;
    private int prevReturnPackQty;
    private int prevReturnLooseQty;
    private String manufacturerName;
    private String contentName;
    private String itemUniqueKey;
	private double specialDiscPer;
	private double specialDiscAmount;
     private int taxId;
     private String taxName;
     private double taxPercentage;
     private double itemTaxAmount;
     private int isGroupTax;
     private String taxMode;
     private String sku;
     private String hsnCode;
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
     private List<SaleReturnDetailsSerialMapper> saleReturnDetailsSerialMapper;
     private int saleDetailsId;
     private int itemdualstock;
     private double itemLotAdjAmount;
     private double freeQty;
     private int taxTypeId;
	public int getSaleId() {
		return saleId;
	}
	public void setSaleId(int saleId) {
		this.saleId = saleId;
	}
	public String getSaleInvNo() {
		return saleInvNo;
	}
	public void setSaleInvNo(String saleInvNo) {
		this.saleInvNo = saleInvNo;
	}
	public Date getSaleInvDate() {
		return saleInvDate;
	}
	public void setSaleInvDate(Date saleInvDate) {
		this.saleInvDate = saleInvDate;
	}
	public String getSaleInvTime() {
		return saleInvTime;
	}
	public void setSaleInvTime(String saleInvTime) {
		this.saleInvTime = saleInvTime;
	}
	public String getSaleInvModeName() {
		return saleInvModeName;
	}
	public void setSaleInvModeName(String saleInvModeName) {
		this.saleInvModeName = saleInvModeName;
	}
	public double getGrossAmount() {
		return grossAmount;
	}
	public void setGrossAmount(double grossAmount) {
		this.grossAmount = grossAmount;
	}
	public double getEdAmount() {
		return edAmount;
	}
	public void setEdAmount(double edAmount) {
		this.edAmount = edAmount;
	}
	public double getDiscAmount() {
		return discAmount;
	}
	public void setDiscAmount(double discAmount) {
		this.discAmount = discAmount;
	}
	public double getVatAmount() {
		return vatAmount;
	}
	public void setVatAmount(double vatAmount) {
		this.vatAmount = vatAmount;
	}
	public double getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}
	public double getAdjAmount() {
		return adjAmount;
	}
	public void setAdjAmount(double adjAmount) {
		this.adjAmount = adjAmount;
	}
	public double getRoundOff() {
		return roundOff;
	}
	public void setRoundOff(double roundOff) {
		this.roundOff = roundOff;
	}
	public double getNetAmount() {
		return netAmount;
	}
	public void setNetAmount(double netAmount) {
		this.netAmount = netAmount;
	}
	public double getCashAmount() {
		return cashAmount;
	}
	public void setCashAmount(double cashAmount) {
		this.cashAmount = cashAmount;
	}
	public double getCardAmount() {
		return cardAmount;
	}
	public void setCardAmount(double cardAmount) {
		this.cardAmount = cardAmount;
	}
	public double getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(double creditAmount) {
		this.creditAmount = creditAmount;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public int getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
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
	public int getCalculateLooseQty() {
		return calculateLooseQty;
	}
	public void setCalculateLooseQty(int calculateLooseQty) {
		this.calculateLooseQty = calculateLooseQty;
	}
	public int getPrevReturnPackQty() {
		return prevReturnPackQty;
	}
	public void setPrevReturnPackQty(int prevReturnPackQty) {
		this.prevReturnPackQty = prevReturnPackQty;
	}
	public int getPrevReturnLooseQty() {
		return prevReturnLooseQty;
	}
	public void setPrevReturnLooseQty(int prevReturnLooseQty) {
		this.prevReturnLooseQty = prevReturnLooseQty;
	}

	public String getManufacturerName() {
		return manufacturerName;
	}
	public void setManufacturerName(String manufacturerName) {
		this.manufacturerName = manufacturerName;
	}
	public String getContentName() {
		return contentName;
	}
	public void setContentName(String contentName) {
		this.contentName = contentName;
	}

	public String getItemUniqueKey() {
		return itemUniqueKey;
	}
	public void setItemUniqueKey(String itemUniqueKey) {
		this.itemUniqueKey = itemUniqueKey;
	}

	public double getSpecialDiscPer() {
		return specialDiscPer;
	}
	public void setSpecialDiscPer(double specialDiscPer) {
		this.specialDiscPer = specialDiscPer;
	}
	public double getSpecialDiscAmount() {
		return specialDiscAmount;
	}
	public void setSpecialDiscAmount(double specialDiscAmount) {
		this.specialDiscAmount = specialDiscAmount;
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
	 * @return the itemTaxAmount
	 */
	public double getItemTaxAmount() {
		return itemTaxAmount;
	}
	/**
	 * @param itemTaxAmount the itemTaxAmount to set
	 */
	public void setItemTaxAmount(double itemTaxAmount) {
		this.itemTaxAmount = itemTaxAmount;
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


	/**
	 * @return the saleReturnDetailsSerialMapper
	 */
	public List<SaleReturnDetailsSerialMapper> getSaleReturnDetailsSerialMapper() {
		return saleReturnDetailsSerialMapper;
	}
	/**
	 * @param saleReturnDetailsSerialMapper the saleReturnDetailsSerialMapper to set
	 */
	public void setSaleReturnDetailsSerialMapper(
			List<SaleReturnDetailsSerialMapper> saleReturnDetailsSerialMapper) {
		this.saleReturnDetailsSerialMapper = saleReturnDetailsSerialMapper;
	}
	/**
	 * @return the saleDetailsId
	 */
	public int getSaleDetailsId() {
		return saleDetailsId;
	}
	/**
	 * @param saleDetailsId the saleDetailsId to set
	 */
	public void setSaleDetailsId(int saleDetailsId) {
		this.saleDetailsId = saleDetailsId;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SaleDetailsForReturnDTO [saleId=" + saleId + ", saleInvNo=" + saleInvNo + ", saleInvDate=" + saleInvDate
				+ ", saleInvTime=" + saleInvTime + ", saleInvModeName=" + saleInvModeName + ", grossAmount="
				+ grossAmount + ", edAmount=" + edAmount + ", discAmount=" + discAmount + ", vatAmount=" + vatAmount
				+ ", taxAmount=" + taxAmount + ", adjAmount=" + adjAmount + ", roundOff=" + roundOff + ", netAmount="
				+ netAmount + ", cashAmount=" + cashAmount + ", cardAmount=" + cardAmount + ", creditAmount="
				+ creditAmount + ", customerId=" + customerId + ", customerName=" + customerName + ", customerPhone="
				+ customerPhone + ", customerAddress=" + customerAddress + ", doctorId=" + doctorId + ", doctorName="
				+ doctorName + ", itemId=" + itemId + ", itemName=" + itemName + ", batchNo=" + batchNo
				+ ", expiryDate=" + expiryDate + ", expiryDateFormat=" + expiryDateFormat + ", packUnitId=" + packUnitId
				+ ", packUnitName=" + packUnitName + ", packQty=" + packQty + ", conversion=" + conversion
				+ ", looseUnitId=" + looseUnitId + ", looseUnitName=" + looseUnitName + ", looseQty=" + looseQty
				+ ", mrp=" + mrp + ", rate=" + rate + ", mrpPerUnit=" + mrpPerUnit + ", ratePerUnit=" + ratePerUnit
				+ ", amount=" + amount + ", edPer=" + edPer + ", ed=" + ed + ", taxPer=" + taxPer + ", tax=" + tax
				+ ", vatPer=" + vatPer + ", vat=" + vat + ", discPer=" + discPer + ", disc=" + disc + ", totAmount="
				+ totAmount + ", calculateLooseQty=" + calculateLooseQty + ", prevReturnPackQty=" + prevReturnPackQty
				+ ", prevReturnLooseQty=" + prevReturnLooseQty + ", manufacturerName=" + manufacturerName
				+ ", contentName=" + contentName + ", itemUniqueKey=" + itemUniqueKey + ", specialDiscPer="
				+ specialDiscPer + ", specialDiscAmount=" + specialDiscAmount + ", taxId=" + taxId + ", taxName="
				+ taxName + ", taxPercentage=" + taxPercentage + ", itemTaxAmount=" + itemTaxAmount + ", isGroupTax="
				+ isGroupTax + ", taxMode=" + taxMode + ", sku=" + sku + ", hsnCode=" + hsnCode + ", stockRequired="
				+ stockRequired + ", batchWiseStock=" + batchWiseStock + ", expiryDateRequired=" + expiryDateRequired
				+ ", expiryMonthRequired=" + expiryMonthRequired + ", dualStockRequired=" + dualStockRequired
				+ ", locationRequired=" + locationRequired + ", priceListRequired=" + priceListRequired
				+ ", sizeWiseStockRequired=" + sizeWiseStockRequired + ", colourWiseStockRequired="
				+ colourWiseStockRequired + ", warrantyRequired=" + warrantyRequired + ", warrantyMonth="
				+ warrantyMonth + ", mrpRequired=" + mrpRequired + ", serialNoRequired=" + serialNoRequired
				+ ", serialNoSuffRequired=" + serialNoSuffRequired + ", serialNoPrefRequired=" + serialNoPrefRequired
				+ ", rateOn=" + rateOn + ", serialNoType=" + serialNoType + ", warrantyTypeOn=" + warrantyTypeOn
				+ ", size=" + size + ", sizeType=" + sizeType + ", colour=" + colour + ", discount=" + discount
				+ ", isDiscount=" + isDiscount + ", saleReturnDetailsSerialMapper=" + saleReturnDetailsSerialMapper
				+ ", saleDetailsId=" + saleDetailsId + ", itemdualstock=" + itemdualstock + ", itemLotAdjAmount="
				+ itemLotAdjAmount + ", freeQty=" + freeQty + ", taxTypeId=" + taxTypeId + "]";
	}




}
