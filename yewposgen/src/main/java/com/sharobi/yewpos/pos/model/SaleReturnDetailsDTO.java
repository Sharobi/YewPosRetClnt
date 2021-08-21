/**
 *
 */
package com.sharobi.yewpos.pos.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Manodip Jana
 *
 *
 */
public class SaleReturnDetailsDTO implements Serializable {

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
    private int saleId;
    private String saleInvNo;
    private double mrpPerUnit;
    private double ratePerUnit;
    private int hidePackQty;
    private int hideLooseQty;
    private String manufacturerName;
    private String manufacturerCode;
    private String contentName;
    private int remainingPackQty;
    private int remainingLooseQty;
    private String itemUniqueKey;
	private double specialDiscPer;
	private double specialDiscAmount;
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
    private int saleReturnId;
    private int saleReturnDetailsId;
    private List<SaleReturnDetailsSerialMapper> saleReturnDetailsSerialMapper;
    private int itemdualstock;

    private int salesmanId;
    private double salesmanFactor;
    private double salesmanAmount;
    private double itemLotAdjAmount;
    private double freeQty;
    private int itemFreeAgainstItem;
	private int taxTypeId;

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
	public String getContentName() {
		return contentName;
	}
	public void setContentName(String contentName) {
		this.contentName = contentName;
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
	 * @return the saleReturnId
	 */
	public int getSaleReturnId() {
		return saleReturnId;
	}
	/**
	 * @param saleReturnId the saleReturnId to set
	 */
	public void setSaleReturnId(int saleReturnId) {
		this.saleReturnId = saleReturnId;
	}
	/**
	 * @return the saleReturnDetailsId
	 */
	public int getSaleReturnDetailsId() {
		return saleReturnDetailsId;
	}
	/**
	 * @param saleReturnDetailsId the saleReturnDetailsId to set
	 */
	public void setSaleReturnDetailsId(int saleReturnDetailsId) {
		this.saleReturnDetailsId = saleReturnDetailsId;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SaleReturnDetailsDTO [itemId=" + itemId + ", itemName=" + itemName + ", batchNo=" + batchNo
				+ ", expiryDate=" + expiryDate + ", expiryDateFormat=" + expiryDateFormat + ", packUnitId=" + packUnitId
				+ ", packUnitName=" + packUnitName + ", packQty=" + packQty + ", conversion=" + conversion
				+ ", looseUnitId=" + looseUnitId + ", looseUnitName=" + looseUnitName + ", looseQty=" + looseQty
				+ ", mrp=" + mrp + ", rate=" + rate + ", amount=" + amount + ", edPer=" + edPer + ", ed=" + ed
				+ ", taxPer=" + taxPer + ", tax=" + tax + ", vatPer=" + vatPer + ", vat=" + vat + ", discPer=" + discPer
				+ ", disc=" + disc + ", totAmount=" + totAmount + ", saleId=" + saleId + ", saleInvNo=" + saleInvNo
				+ ", mrpPerUnit=" + mrpPerUnit + ", ratePerUnit=" + ratePerUnit + ", hidePackQty=" + hidePackQty
				+ ", hideLooseQty=" + hideLooseQty + ", manufacturerName=" + manufacturerName + ", manufacturerCode="
				+ manufacturerCode + ", contentName=" + contentName + ", remainingPackQty=" + remainingPackQty
				+ ", remainingLooseQty=" + remainingLooseQty + ", itemUniqueKey=" + itemUniqueKey + ", specialDiscPer="
				+ specialDiscPer + ", specialDiscAmount=" + specialDiscAmount + ", taxId=" + taxId + ", taxName="
				+ taxName + ", taxPercentage=" + taxPercentage + ", taxAmount=" + taxAmount + ", isGroupTax="
				+ isGroupTax + ", taxMode=" + taxMode + ", sku=" + sku + ", hsnCode=" + hsnCode + ", reasonId="
				+ reasonId + ", reasonTypeName=" + reasonTypeName + ", stockRequired=" + stockRequired
				+ ", batchWiseStock=" + batchWiseStock + ", expiryDateRequired=" + expiryDateRequired
				+ ", expiryMonthRequired=" + expiryMonthRequired + ", dualStockRequired=" + dualStockRequired
				+ ", locationRequired=" + locationRequired + ", priceListRequired=" + priceListRequired
				+ ", sizeWiseStockRequired=" + sizeWiseStockRequired + ", colourWiseStockRequired="
				+ colourWiseStockRequired + ", warrantyRequired=" + warrantyRequired + ", warrantyMonth="
				+ warrantyMonth + ", mrpRequired=" + mrpRequired + ", serialNoRequired=" + serialNoRequired
				+ ", serialNoSuffRequired=" + serialNoSuffRequired + ", serialNoPrefRequired=" + serialNoPrefRequired
				+ ", rateOn=" + rateOn + ", serialNoType=" + serialNoType + ", warrantyTypeOn=" + warrantyTypeOn
				+ ", size=" + size + ", sizeType=" + sizeType + ", colour=" + colour + ", discount=" + discount
				+ ", isDiscount=" + isDiscount + ", saleReturnId=" + saleReturnId + ", saleReturnDetailsId="
				+ saleReturnDetailsId + ", saleReturnDetailsSerialMapper=" + saleReturnDetailsSerialMapper
				+ ", itemdualstock=" + itemdualstock + ", salesmanId=" + salesmanId + ", salesmanFactor="
				+ salesmanFactor + ", salesmanAmount=" + salesmanAmount + ", itemLotAdjAmount=" + itemLotAdjAmount
				+ ", freeQty=" + freeQty + ", itemFreeAgainstItem=" + itemFreeAgainstItem + ", taxTypeId=" + taxTypeId
				+ "]";
	}



}
