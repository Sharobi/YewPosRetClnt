package com.sharobi.yewpos.proc.model;


import java.io.Serializable;
import java.util.List;

public class PurchaseInvDetails implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	
	
	public PurchaseInvDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	private int id;
	
	private int purchaseInvId;
	
	private String invNo;
	
	
	private String invDate;
	
	private int retailTypeId;
	
	private int itemId;
	
	private String batchNo;
	
	private int expiryMonth;
	
	private String mfdDate;
	
	private String expiryDate;
	
	private int packUnitId;
	
	private double packQty;
	
	private int conversion;
	
	private int looseQty;
	
	private double freeQty;
	
	private double mrp;
	
	private double rate;
	
	private double saleRate;
	
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
	
	private double taxId;
	
	private double taxPercentage;
	
	private double taxAmount;
	
	private double isGroupTax;
	
	private String taxMode;
	
	private double purchaseNetAmount;
	
	private double totAmount;
	
	private double purchaseOrderId;
	
	private int tmpMappingId;
	
	private int locationId;
	
	private int companyId;

	private int storeId;
	
	private int finyrId;

	private List<PurchaseInvDetailsSerialMapping> purchaseDetailsSerialMapper;
	
	//NEW fields
		
		
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

		
		
		private String expiryDateFormat;
		
		
		
		private String manuCode;
		
		
		
		private String itemUniqueKey;
		
		
		
		
		private String taxName;
		
		
		
		private double taxableRate;
		
		
		
		private int isTaxOnMrp;
		
		
		
		private String sku;
		
		
		
		private String hsnCode;
		
		
		
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
		
		
		
		private String warrantyTypeOn;
		
		
		
		private String size;
		
		
		
		private String sizeType;
		
		
		
		private String colour;

		
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPurchaseInvId() {
		return purchaseInvId;
	}

	public void setPurchaseInvId(int purchaseInvId) {
		this.purchaseInvId = purchaseInvId;
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

	public int getRetailTypeId() {
		return retailTypeId;
	}

	public void setRetailTypeId(int retailTypeId) {
		this.retailTypeId = retailTypeId;
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

	public int getExpiryMonth() {
		return expiryMonth;
	}

	public void setExpiryMonth(int expiryMonth) {
		this.expiryMonth = expiryMonth;
	}

	public String getMfdDate() {
		return mfdDate;
	}

	public void setMfdDate(String mfdDate) {
		this.mfdDate = mfdDate;
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

	public double getSaleRate() {
		return saleRate;
	}

	public void setSaleRate(double saleRate) {
		this.saleRate = saleRate;
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

	public double getTaxId() {
		return taxId;
	}

	public void setTaxId(double taxId) {
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

	public double getIsGroupTax() {
		return isGroupTax;
	}

	public void setIsGroupTax(double isGroupTax) {
		this.isGroupTax = isGroupTax;
	}

	public String getTaxMode() {
		return taxMode;
	}

	public void setTaxMode(String taxMode) {
		this.taxMode = taxMode;
	}

	public double getPurchaseNetAmount() {
		return purchaseNetAmount;
	}

	public void setPurchaseNetAmount(double purchaseNetAmount) {
		this.purchaseNetAmount = purchaseNetAmount;
	}

	public double getTotAmount() {
		return totAmount;
	}

	public void setTotAmount(double totAmount) {
		this.totAmount = totAmount;
	}

	public double getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(double purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}

	public int getTmpMappingId() {
		return tmpMappingId;
	}

	public void setTmpMappingId(int tmpMappingId) {
		this.tmpMappingId = tmpMappingId;
	}

	public int getLocationId() {
		return locationId;
	}

	public void setLocationId(int locationId) {
		this.locationId = locationId;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getStoreId() {
		return storeId;
	}

	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}

	public int getFinyrId() {
		return finyrId;
	}

	public void setFinyrId(int finyrId) {
		this.finyrId = finyrId;
	}

	public List<PurchaseInvDetailsSerialMapping> getPurchaseDetailsSerialMapper() {
		return purchaseDetailsSerialMapper;
	}

	public void setPurchaseDetailsSerialMapper(List<PurchaseInvDetailsSerialMapping> purchaseDetailsSerialMapper) {
		this.purchaseDetailsSerialMapper = purchaseDetailsSerialMapper;
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

	public String getManuCode() {
		return manuCode;
	}

	public void setManuCode(String manuCode) {
		this.manuCode = manuCode;
	}

	public String getItemUniqueKey() {
		return itemUniqueKey;
	}

	public void setItemUniqueKey(String itemUniqueKey) {
		this.itemUniqueKey = itemUniqueKey;
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

	public int getIsTaxOnMrp() {
		return isTaxOnMrp;
	}

	public void setIsTaxOnMrp(int isTaxOnMrp) {
		this.isTaxOnMrp = isTaxOnMrp;
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

	public String getPurchaseOrderInvNo() {
		return purchaseOrderInvNo;
	}

	public void setPurchaseOrderInvNo(String purchaseOrderInvNo) {
		this.purchaseOrderInvNo = purchaseOrderInvNo;
	}

	public int getStockRequired() {
		return stockRequired;
	}

	public void setStockRequired(int stockRequired) {
		this.stockRequired = stockRequired;
	}

	public int getBatchWiseStock() {
		return batchWiseStock;
	}

	public void setBatchWiseStock(int batchWiseStock) {
		this.batchWiseStock = batchWiseStock;
	}

	public int getExpiryDateRequired() {
		return expiryDateRequired;
	}

	public void setExpiryDateRequired(int expiryDateRequired) {
		this.expiryDateRequired = expiryDateRequired;
	}

	public int getExpiryMonthRequired() {
		return expiryMonthRequired;
	}

	public void setExpiryMonthRequired(int expiryMonthRequired) {
		this.expiryMonthRequired = expiryMonthRequired;
	}

	public int getDualStockRequired() {
		return dualStockRequired;
	}

	public void setDualStockRequired(int dualStockRequired) {
		this.dualStockRequired = dualStockRequired;
	}

	public int getLocationRequired() {
		return locationRequired;
	}

	public void setLocationRequired(int locationRequired) {
		this.locationRequired = locationRequired;
	}

	public int getPriceListRequired() {
		return priceListRequired;
	}

	public void setPriceListRequired(int priceListRequired) {
		this.priceListRequired = priceListRequired;
	}

	public int getSizeWiseStockRequired() {
		return sizeWiseStockRequired;
	}

	public void setSizeWiseStockRequired(int sizeWiseStockRequired) {
		this.sizeWiseStockRequired = sizeWiseStockRequired;
	}

	public int getColourWiseStockRequired() {
		return colourWiseStockRequired;
	}

	public void setColourWiseStockRequired(int colourWiseStockRequired) {
		this.colourWiseStockRequired = colourWiseStockRequired;
	}

	public int getWarrantyRequired() {
		return warrantyRequired;
	}

	public void setWarrantyRequired(int warrantyRequired) {
		this.warrantyRequired = warrantyRequired;
	}

	public int getWarrantyMonth() {
		return warrantyMonth;
	}

	public void setWarrantyMonth(int warrantyMonth) {
		this.warrantyMonth = warrantyMonth;
	}

	public int getMrpRequired() {
		return mrpRequired;
	}

	public void setMrpRequired(int mrpRequired) {
		this.mrpRequired = mrpRequired;
	}

	public int getSerialNoRequired() {
		return serialNoRequired;
	}

	public void setSerialNoRequired(int serialNoRequired) {
		this.serialNoRequired = serialNoRequired;
	}

	public int getSerialNoSuffRequired() {
		return serialNoSuffRequired;
	}

	public void setSerialNoSuffRequired(int serialNoSuffRequired) {
		this.serialNoSuffRequired = serialNoSuffRequired;
	}

	public int getSerialNoPrefRequired() {
		return serialNoPrefRequired;
	}

	public void setSerialNoPrefRequired(int serialNoPrefRequired) {
		this.serialNoPrefRequired = serialNoPrefRequired;
	}

	public int getRateOn() {
		return rateOn;
	}

	public void setRateOn(int rateOn) {
		this.rateOn = rateOn;
	}

	public String getSerialNoType() {
		return serialNoType;
	}

	public void setSerialNoType(String serialNoType) {
		this.serialNoType = serialNoType;
	}

	public String getWarrantyTypeOn() {
		return warrantyTypeOn;
	}

	public void setWarrantyTypeOn(String warrantyTypeOn) {
		this.warrantyTypeOn = warrantyTypeOn;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getSizeType() {
		return sizeType;
	}

	public void setSizeType(String sizeType) {
		this.sizeType = sizeType;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	@Override
	public String toString() {
		return "PurchaseInvDetails [id=" + id + ", purchaseInvId=" + purchaseInvId + ", invNo=" + invNo + ", invDate="
				+ invDate + ", retailTypeId=" + retailTypeId + ", itemId=" + itemId + ", batchNo=" + batchNo
				+ ", expiryMonth=" + expiryMonth + ", mfdDate=" + mfdDate + ", expiryDate=" + expiryDate
				+ ", packUnitId=" + packUnitId + ", packQty=" + packQty + ", conversion=" + conversion + ", looseQty="
				+ looseQty + ", freeQty=" + freeQty + ", mrp=" + mrp + ", rate=" + rate + ", saleRate=" + saleRate
				+ ", amount=" + amount + ", edPer=" + edPer + ", ed=" + ed + ", taxPer=" + taxPer + ", tax=" + tax
				+ ", vatPer=" + vatPer + ", vat=" + vat + ", discPer=" + discPer + ", disc=" + disc
				+ ", itemLotAdjAmount=" + itemLotAdjAmount + ", taxId=" + taxId + ", taxPercentage=" + taxPercentage
				+ ", taxAmount=" + taxAmount + ", isGroupTax=" + isGroupTax + ", taxMode=" + taxMode
				+ ", purchaseNetAmount=" + purchaseNetAmount + ", totAmount=" + totAmount + ", purchaseOrderId="
				+ purchaseOrderId + ", tmpMappingId=" + tmpMappingId + ", locationId=" + locationId + ", companyId="
				+ companyId + ", storeId=" + storeId + ", finyrId=" + finyrId + ", purchaseDetailsSerialMapper="
				+ purchaseDetailsSerialMapper + ", lang=" + lang + ", qryCondition=" + qryCondition + ", packUnitName="
				+ packUnitName + ", itemName=" + itemName + ", grpId=" + grpId + ", grpName=" + grpName + ", schdId="
				+ schdId + ", schdName=" + schdName + ", manuId=" + manuId + ", manuName=" + manuName
				+ ", expiryDateFormat=" + expiryDateFormat + ", manuCode=" + manuCode + ", itemUniqueKey="
				+ itemUniqueKey + ", taxName=" + taxName + ", taxableRate=" + taxableRate + ", isTaxOnMrp=" + isTaxOnMrp
				+ ", sku=" + sku + ", hsnCode=" + hsnCode + ", purchaseOrderInvNo=" + purchaseOrderInvNo
				+ ", stockRequired=" + stockRequired + ", batchWiseStock=" + batchWiseStock + ", expiryDateRequired="
				+ expiryDateRequired + ", expiryMonthRequired=" + expiryMonthRequired + ", dualStockRequired="
				+ dualStockRequired + ", locationRequired=" + locationRequired + ", priceListRequired="
				+ priceListRequired + ", sizeWiseStockRequired=" + sizeWiseStockRequired + ", colourWiseStockRequired="
				+ colourWiseStockRequired + ", warrantyRequired=" + warrantyRequired + ", warrantyMonth="
				+ warrantyMonth + ", mrpRequired=" + mrpRequired + ", serialNoRequired=" + serialNoRequired
				+ ", serialNoSuffRequired=" + serialNoSuffRequired + ", serialNoPrefRequired=" + serialNoPrefRequired
				+ ", rateOn=" + rateOn + ", serialNoType=" + serialNoType + ", warrantyTypeOn=" + warrantyTypeOn
				+ ", size=" + size + ", sizeType=" + sizeType + ", colour=" + colour + "]";
	}


}
