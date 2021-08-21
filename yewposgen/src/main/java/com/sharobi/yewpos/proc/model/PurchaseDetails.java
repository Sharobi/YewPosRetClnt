package com.sharobi.yewpos.proc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Manodip
 */
public class PurchaseDetails implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private int purchaseId;
	private String invNo;
	private String invDate;
	private int itemId;
	private String batchNo;
	private String expiryDate;
	private int packUnitId;
	private double packQty;
	private int conversion;
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
	private double freeQty;
	private int isDeleted;
	private int createdBy;
	private String createdDate;
	private int updatedBy;
	private String updatedDate;
	private String lang;
	private String qryCondition;
	private String packUnitName;
	private String itemName;
	private int grpId;
    private String grpName;
    private int schdId;
    private String schdName;
    private int manuId;
    private String manuName;
    private String manuCode;
    private String expiryDateFormat;
    private String itemUniqueKey;
    private double itemLotAdjAmount;
    private int taxId;
    private double taxPercentage;
    private double taxAmount;
    private String taxName;
    private double taxableRate;
    private String taxMode;
    private int taxTypeId;
    private int isGroupTax;
    private int isTaxOnMrp;
    private String sku;
    private String hsnCode;
    private double purchaseNetAmount;
    private double saleRate;
    private int purchaseOrderId;
    private String purchaseOrderInvNo;
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
    private String mfdDate;
    private int retailTypeId;
    private int tmpMappingId;
    private int locationId;
    private List<PurchaseDetailsSerialMapper> purchaseDetailsSerialMapper;
    private String warrantyTypeOn;
    private String size;
    private String sizeType;
    private String colour;
    private int expiryMonth;
   
    //New added 23.5.2019
	private String categoryName;
    private String subcategoryName;
    private int categoryId;
    private int subcategoryId;
 

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
	 * @return the purchaseOrderInvNo
	 */
	public String getPurchaseOrderInvNo() {
		return purchaseOrderInvNo;
	}
	/**
	 * @param purchaseOrderInvNo the purchaseOrderInvNo to set
	 */
	public void setPurchaseOrderInvNo(String purchaseOrderInvNo) {
		this.purchaseOrderInvNo = purchaseOrderInvNo;
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
	public String getSku() {
		return sku;
	}
	public void setSku(String sku) {
		this.sku = sku;
	}
	public double getPurchaseNetAmount() {
		return purchaseNetAmount;
	}
	public void setPurchaseNetAmount(double purchaseNetAmount) {
		this.purchaseNetAmount = purchaseNetAmount;
	}
	public String getHsnCode() {
		return hsnCode;
	}
	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}
	public double getItemLotAdjAmount() {
		return itemLotAdjAmount;
	}
	public void setItemLotAdjAmount(double itemLotAdjAmount) {
		this.itemLotAdjAmount = itemLotAdjAmount;
	}
	public String getManuCode() {
		return manuCode;
	}
	public void setManuCode(String manuCode) {
		this.manuCode = manuCode;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPurchaseId() {
		return purchaseId;
	}
	public void setPurchaseId(int purchaseId) {
		this.purchaseId = purchaseId;
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
	public double getFreeQty() {
		return freeQty;
	}
	public void setFreeQty(double freeQty) {
		this.freeQty = freeQty;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	public int getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}
	public String getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}
	public int getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}
	public String getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getLang() {
		return lang;
	}
	public void setLang(String lang) {
		this.lang = lang;
	}
	public String getQryCondition() {
		return qryCondition;
	}
	public void setQryCondition(String qryCondition) {
		this.qryCondition = qryCondition;
	}
	public String getPackUnitName() {
		return packUnitName;
	}
	public void setPackUnitName(String packUnitName) {
		this.packUnitName = packUnitName;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
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
	public int getSchdId() {
		return schdId;
	}
	public void setSchdId(int schdId) {
		this.schdId = schdId;
	}
	public String getSchdName() {
		return schdName;
	}
	public void setSchdName(String schdName) {
		this.schdName = schdName;
	}
	public int getManuId() {
		return manuId;
	}
	public void setManuId(int manuId) {
		this.manuId = manuId;
	}
	public String getManuName() {
		return manuName;
	}
	public void setManuName(String manuName) {
		this.manuName = manuName;
	}
	public String getExpiryDateFormat() {
		return expiryDateFormat;
	}
	public void setExpiryDateFormat(String expiryDateFormat) {
		this.expiryDateFormat = expiryDateFormat;
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
	public String getTaxName() {
		return taxName;
	}
	public void setTaxName(String taxName) {
		this.taxName = taxName;
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

	public double getSaleRate() {
		return saleRate;
	}
	public void setSaleRate(double saleRate) {
		this.saleRate = saleRate;
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
	 * @return the purchaseDetailsSerialMapper
	 */
	public List<PurchaseDetailsSerialMapper> getPurchaseDetailsSerialMapper() {
		return purchaseDetailsSerialMapper;
	}
	/**
	 * @param purchaseDetailsSerialMapper the purchaseDetailsSerialMapper to set
	 */
	public void setPurchaseDetailsSerialMapper(
			List<PurchaseDetailsSerialMapper> purchaseDetailsSerialMapper) {
		this.purchaseDetailsSerialMapper = purchaseDetailsSerialMapper;
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
	 * @return the expiryMonth
	 */
	public int getExpiryMonth() {
		return expiryMonth;
	}
	/**
	 * @param expiryMonth the expiryMonth to set
	 */
	public void setExpiryMonth(int expiryMonth) {
		this.expiryMonth = expiryMonth;
	}
	
	
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getSubcategoryName() {
		return subcategoryName;
	}
	public void setSubcategoryName(String subcategoryName) {
		this.subcategoryName = subcategoryName;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getSubcategoryId() {
		return subcategoryId;
	}
	public void setSubcategoryId(int subcategoryId) {
		this.subcategoryId = subcategoryId;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PurchaseDetails [id=" + id + ", purchaseId=" + purchaseId + ", invNo=" + invNo + ", invDate=" + invDate
				+ ", itemId=" + itemId + ", batchNo=" + batchNo + ", expiryDate=" + expiryDate + ", packUnitId="
				+ packUnitId + ", packQty=" + packQty + ", conversion=" + conversion + ", looseQty=" + looseQty
				+ ", mrp=" + mrp + ", rate=" + rate + ", amount=" + amount + ", edPer=" + edPer + ", ed=" + ed
				+ ", taxPer=" + taxPer + ", tax=" + tax + ", vatPer=" + vatPer + ", vat=" + vat + ", discPer=" + discPer
				+ ", disc=" + disc + ", totAmount=" + totAmount + ", finyrId=" + finyrId + ", storeId=" + storeId
				+ ", companyId=" + companyId + ", freeQty=" + freeQty + ", isDeleted=" + isDeleted + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate="
				+ updatedDate + ", lang=" + lang + ", qryCondition=" + qryCondition + ", packUnitName=" + packUnitName
				+ ", itemName=" + itemName + ", grpId=" + grpId + ", grpName=" + grpName + ", schdId=" + schdId
				+ ", schdName=" + schdName + ", manuId=" + manuId + ", manuName=" + manuName + ", manuCode=" + manuCode
				+ ", expiryDateFormat=" + expiryDateFormat + ", itemUniqueKey=" + itemUniqueKey + ", itemLotAdjAmount="
				+ itemLotAdjAmount + ", taxId=" + taxId + ", taxPercentage=" + taxPercentage + ", taxAmount="
				+ taxAmount + ", taxName=" + taxName + ", taxableRate=" + taxableRate + ", taxMode=" + taxMode
				+ ", taxTypeId=" + taxTypeId + ", isGroupTax=" + isGroupTax + ", isTaxOnMrp=" + isTaxOnMrp + ", sku="
				+ sku + ", hsnCode=" + hsnCode + ", purchaseNetAmount=" + purchaseNetAmount + ", saleRate=" + saleRate
				+ ", purchaseOrderId=" + purchaseOrderId + ", purchaseOrderInvNo=" + purchaseOrderInvNo
				+ ", stockRequired=" + stockRequired + ", batchWiseStock=" + batchWiseStock + ", expiryDateRequired="
				+ expiryDateRequired + ", expiryMonthRequired=" + expiryMonthRequired + ", dualStockRequired="
				+ dualStockRequired + ", locationRequired=" + locationRequired + ", priceListRequired="
				+ priceListRequired + ", sizeWiseStockRequired=" + sizeWiseStockRequired + ", colourWiseStockRequired="
				+ colourWiseStockRequired + ", warrantyRequired=" + warrantyRequired + ", warrantyMonth="
				+ warrantyMonth + ", mrpRequired=" + mrpRequired + ", serialNoRequired=" + serialNoRequired
				+ ", serialNoSuffRequired=" + serialNoSuffRequired + ", serialNoPrefRequired=" + serialNoPrefRequired
				+ ", rateOn=" + rateOn + ", serialNoType=" + serialNoType + ", mfdDate=" + mfdDate + ", retailTypeId="
				+ retailTypeId + ", tmpMappingId=" + tmpMappingId + ", locationId=" + locationId
				+ ", purchaseDetailsSerialMapper=" + purchaseDetailsSerialMapper + ", warrantyTypeOn=" + warrantyTypeOn
				+ ", size=" + size + ", sizeType=" + sizeType + ", colour=" + colour + ", expiryMonth=" + expiryMonth
				+ ", categoryName=" + categoryName + ", subcategoryName=" + subcategoryName + ", categoryId="
				+ categoryId + ", subcategoryId=" + subcategoryId + "]";
	}
	

}
