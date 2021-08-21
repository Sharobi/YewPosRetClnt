package com.sharobi.yewpos.inv.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Manodip
 * 
 */
public class ItemMaster implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	private String name;

	private String code;

	private int groupId;

	private int categoryId;

	private int subCategoryId;

	private int scheduleId;

	private int contentId;

	private int brandId;

	private int manufacturerId;

	private Date entryDate;

	private double vat;

	private int isOnMrp;

	private int packUnitId;

	private int conversion;

	private int looseUnitId;

	private String storage;

	private String care;

	private int reorderLevel;

	private int reorderLevelUnitId;

	private double price;

	private int isTaxable;

	private String note;

	private int companyId;

	private int isDeleted;

	private int createdBy;

	private Date createdDate;

	private int updatedBy;

	private Date updatedDate;

	private int rackId;

	private GroupMaster groupMaster;

	private CategoryMaster categoryMaster;

	private SubCategoryMaster subCategoryMaster;

	private ScheduleMaster scheduleMaster;

	private ContentMaster contentMaster;

	private BrandMaster brandMaster;

	private ManufacturerMaster manufacturerMaster;

	private UnitMaster packUnit;

	private UnitMaster looseUnit;

	private UnitMaster reorderLevelUnit;

	private RackMaster rack;

	private double markup;

	private String strength;

	private String netContent;

	private String lang;
	private int purchaseTaxId;
	private double purchaseTaxPercentage;
	private int saleTaxId;
	private double saleTaxPercentage;
	private String sku;
	private double discount;
	private double maxDiscountLimit;
	private int isDiscount;
	private TaxMaster saleTax;
	private TaxMaster purchaseTax;
	private String hsnCode;
	private int isLooseSale;

	// add on 3_11_2017

	private int marketerId;

	private int taxTypeId;

	private int reorderLevelQty;

	private int maximumQty;

	private double purchaseRate;

	private double saleRate;

	private double mrp;

	private String launchDate;

	private String discontinueDate;

	private MarketerMaster marketerMaster;
	
    private String description;
	
	private String printName;
	
	private String imgUrl;
	
	private int maxLevel;
	
	private int minLevel;
	
	private int isActive;
	
	private String colour;
	
	private String sizeType;
	
	private String size;
	
	private String weight;
	
	private String partNumber;
	
	private String authorName;
	
	private String edition;
	
	private double listedMrp;
	
	private double wsp;
	
	private int stockRequired;
	
	private int batchWiseStock;
	
	private int expiryDateRequired;
	
	private int dualStockRequired;
	
	private String saleRateOn;
	
	private int locationRequired;
	
	private int priceListRequired;
	
	private int sizeWiseStockRequired;
	
	private int warrantyRequired;
	
	private String warrantyTypeOn;
	
	private int warrantyMonth;
	
	private int mrpRequired;
	
	private int serialNoRequired;
	
	private int serialNoSuffRequired;
	
	private int serialNoPrefRequired;
	
	private String serialNoType;
	
	private int retailTypeId;
	private int expiryMonthRequired;
    private int colourWiseStockRequired;
    private int rateOn;
	
	/**
	 * @return the marketerMaster
	 */
	public MarketerMaster getMarketerMaster() {
		return marketerMaster;
	}
	/**
	 * @param marketerMaster the marketerMaster to set
	 */
	public void setMarketerMaster(MarketerMaster marketerMaster) {
		this.marketerMaster = marketerMaster;
	}
	/**
	 * @return the launchDate
	 */
	public String getLaunchDate() {
		return launchDate;
	}
	/**
	 * @param launchDate the launchDate to set
	 */
	public void setLaunchDate(String launchDate) {
		this.launchDate = launchDate;
	}
	/**
	 * @return the discontinueDate
	 */
	public String getDiscontinueDate() {
		return discontinueDate;
	}
	/**
	 * @param discontinueDate the discontinueDate to set
	 */
	public void setDiscontinueDate(String discontinueDate) {
		this.discontinueDate = discontinueDate;
	}
	/**
	 * 
	 */
	public ItemMaster() {
		// TODO Auto-generated constructor stub
	}
	/**
	 * @return the marketerId
	 */
	public int getMarketerId() {
		return marketerId;
	}

	/**
	 * @param marketerId
	 *            the marketerId to set
	 */
	public void setMarketerId(int marketerId) {
		this.marketerId = marketerId;
	}

	/**
	 * @return the taxTypeId
	 */
	public int getTaxTypeId() {
		return taxTypeId;
	}

	/**
	 * @param taxTypeId
	 *            the taxTypeId to set
	 */
	public void setTaxTypeId(int taxTypeId) {
		this.taxTypeId = taxTypeId;
	}

	/**
	 * @return the reorderLevelQty
	 */
	public int getReorderLevelQty() {
		return reorderLevelQty;
	}

	/**
	 * @param reorderLevelQty
	 *            the reorderLevelQty to set
	 */
	public void setReorderLevelQty(int reorderLevelQty) {
		this.reorderLevelQty = reorderLevelQty;
	}

	/**
	 * @return the maximumQty
	 */
	public int getMaximumQty() {
		return maximumQty;
	}

	/**
	 * @param maximumQty
	 *            the maximumQty to set
	 */
	public void setMaximumQty(int maximumQty) {
		this.maximumQty = maximumQty;
	}

	/**
	 * @return the purchaseRate
	 */
	public double getPurchaseRate() {
		return purchaseRate;
	}

	/**
	 * @param purchaseRate
	 *            the purchaseRate to set
	 */
	public void setPurchaseRate(double purchaseRate) {
		this.purchaseRate = purchaseRate;
	}

	/**
	 * @return the saleRate
	 */
	public double getSaleRate() {
		return saleRate;
	}

	/**
	 * @param saleRate
	 *            the saleRate to set
	 */
	public void setSaleRate(double saleRate) {
		this.saleRate = saleRate;
	}

	/**
	 * @return the mrp
	 */
	public double getMrp() {
		return mrp;
	}

	/**
	 * @param mrp
	 *            the mrp to set
	 */
	public void setMrp(double mrp) {
		this.mrp = mrp;
	}



	public int getIsLooseSale() {
		return isLooseSale;
	}

	public void setIsLooseSale(int isLooseSale) {
		this.isLooseSale = isLooseSale;
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

	public double getMaxDiscountLimit() {
		return maxDiscountLimit;
	}

	public void setMaxDiscountLimit(double maxDiscountLimit) {
		this.maxDiscountLimit = maxDiscountLimit;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getNetContent() {
		return netContent;
	}

	public void setNetContent(String netContent) {
		this.netContent = netContent;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getSubCategoryId() {
		return subCategoryId;
	}

	public void setSubCategoryId(int subCategoryId) {
		this.subCategoryId = subCategoryId;
	}

	public int getScheduleId() {
		return scheduleId;
	}

	public void setScheduleId(int scheduleId) {
		this.scheduleId = scheduleId;
	}

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public int getBrandId() {
		return brandId;
	}

	public void setBrandId(int brandId) {
		this.brandId = brandId;
	}

	public int getManufacturerId() {
		return manufacturerId;
	}

	public void setManufacturerId(int manufacturerId) {
		this.manufacturerId = manufacturerId;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public double getVat() {
		return vat;
	}

	public void setVat(double vat) {
		this.vat = vat;
	}

	public int getIsOnMrp() {
		return isOnMrp;
	}

	public void setIsOnMrp(int isOnMrp) {
		this.isOnMrp = isOnMrp;
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

	public int getLooseUnitId() {
		return looseUnitId;
	}

	public void setLooseUnitId(int looseUnitId) {
		this.looseUnitId = looseUnitId;
	}

	public String getStorage() {
		return storage;
	}

	public void setStorage(String storage) {
		this.storage = storage;
	}

	public String getCare() {
		return care;
	}

	public void setCare(String care) {
		this.care = care;
	}

	public int getReorderLevel() {
		return reorderLevel;
	}

	public void setReorderLevel(int reorderLevel) {
		this.reorderLevel = reorderLevel;
	}

	public int getReorderLevelUnitId() {
		return reorderLevelUnitId;
	}

	public void setReorderLevelUnitId(int reorderLevelUnitId) {
		this.reorderLevelUnitId = reorderLevelUnitId;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getIsTaxable() {
		return isTaxable;
	}

	public void setIsTaxable(int isTaxable) {
		this.isTaxable = isTaxable;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
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

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public int getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getRackId() {
		return rackId;
	}

	public void setRackId(int rackId) {
		this.rackId = rackId;
	}

	public GroupMaster getGroupMaster() {
		return groupMaster;
	}

	public void setGroupMaster(GroupMaster groupMaster) {
		this.groupMaster = groupMaster;
	}

	public CategoryMaster getCategoryMaster() {
		return categoryMaster;
	}

	public void setCategoryMaster(CategoryMaster categoryMaster) {
		this.categoryMaster = categoryMaster;
	}

	public SubCategoryMaster getSubCategoryMaster() {
		return subCategoryMaster;
	}

	public void setSubCategoryMaster(SubCategoryMaster subCategoryMaster) {
		this.subCategoryMaster = subCategoryMaster;
	}

	public ScheduleMaster getScheduleMaster() {
		return scheduleMaster;
	}

	public void setScheduleMaster(ScheduleMaster scheduleMaster) {
		this.scheduleMaster = scheduleMaster;
	}

	public ContentMaster getContentMaster() {
		return contentMaster;
	}

	public void setContentMaster(ContentMaster contentMaster) {
		this.contentMaster = contentMaster;
	}

	public BrandMaster getBrandMaster() {
		return brandMaster;
	}

	public void setBrandMaster(BrandMaster brandMaster) {
		this.brandMaster = brandMaster;
	}

	public ManufacturerMaster getManufacturerMaster() {
		return manufacturerMaster;
	}

	public void setManufacturerMaster(ManufacturerMaster manufacturerMaster) {
		this.manufacturerMaster = manufacturerMaster;
	}

	public UnitMaster getPackUnit() {
		return packUnit;
	}

	public void setPackUnit(UnitMaster packUnit) {
		this.packUnit = packUnit;
	}

	public UnitMaster getLooseUnit() {
		return looseUnit;
	}

	public void setLooseUnit(UnitMaster looseUnit) {
		this.looseUnit = looseUnit;
	}

	public UnitMaster getReorderLevelUnit() {
		return reorderLevelUnit;
	}

	public void setReorderLevelUnit(UnitMaster reorderLevelUnit) {
		this.reorderLevelUnit = reorderLevelUnit;
	}

	public RackMaster getRack() {
		return rack;
	}

	public void setRack(RackMaster rack) {
		this.rack = rack;
	}

	public double getMarkup() {
		return markup;
	}

	public void setMarkup(double markup) {
		this.markup = markup;
	}

	public String getStrength() {
		return strength;
	}

	public void setStrength(String strength) {
		this.strength = strength;
	}

	public int getPurchaseTaxId() {
		return purchaseTaxId;
	}

	public void setPurchaseTaxId(int purchaseTaxId) {
		this.purchaseTaxId = purchaseTaxId;
	}

	public double getPurchaseTaxPercentage() {
		return purchaseTaxPercentage;
	}

	public void setPurchaseTaxPercentage(double purchaseTaxPercentage) {
		this.purchaseTaxPercentage = purchaseTaxPercentage;
	}

	public int getSaleTaxId() {
		return saleTaxId;
	}

	public void setSaleTaxId(int saleTaxId) {
		this.saleTaxId = saleTaxId;
	}

	public double getSaleTaxPercentage() {
		return saleTaxPercentage;
	}

	public void setSaleTaxPercentage(double saleTaxPercentage) {
		this.saleTaxPercentage = saleTaxPercentage;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public int getIsDiscount() {
		return isDiscount;
	}

	public void setIsDiscount(int isDiscount) {
		this.isDiscount = isDiscount;
	}

	public TaxMaster getSaleTax() {
		return saleTax;
	}

	public void setSaleTax(TaxMaster saleTax) {
		this.saleTax = saleTax;
	}

	public TaxMaster getPurchaseTax() {
		return purchaseTax;
	}

	public void setPurchaseTax(TaxMaster purchaseTax) {
		this.purchaseTax = purchaseTax;
	}
	
	

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the printName
	 */
	public String getPrintName() {
		return printName;
	}
	/**
	 * @param printName the printName to set
	 */
	public void setPrintName(String printName) {
		this.printName = printName;
	}
	/**
	 * @return the imgUrl
	 */
	public String getImgUrl() {
		return imgUrl;
	}
	/**
	 * @param imgUrl the imgUrl to set
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	/**
	 * @return the maxLevel
	 */
	public int getMaxLevel() {
		return maxLevel;
	}
	/**
	 * @param maxLevel the maxLevel to set
	 */
	public void setMaxLevel(int maxLevel) {
		this.maxLevel = maxLevel;
	}
	/**
	 * @return the minLevel
	 */
	public int getMinLevel() {
		return minLevel;
	}
	/**
	 * @param minLevel the minLevel to set
	 */
	public void setMinLevel(int minLevel) {
		this.minLevel = minLevel;
	}
	/**
	 * @return the isActive
	 */
	public int getIsActive() {
		return isActive;
	}
	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(int isActive) {
		this.isActive = isActive;
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
	 * @return the weight
	 */
	public String getWeight() {
		return weight;
	}
	/**
	 * @param weight the weight to set
	 */
	public void setWeight(String weight) {
		this.weight = weight;
	}
	/**
	 * @return the partNumber
	 */
	public String getPartNumber() {
		return partNumber;
	}
	/**
	 * @param partNumber the partNumber to set
	 */
	public void setPartNumber(String partNumber) {
		this.partNumber = partNumber;
	}
	/**
	 * @return the authorName
	 */
	public String getAuthorName() {
		return authorName;
	}
	/**
	 * @param authorName the authorName to set
	 */
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	/**
	 * @return the edition
	 */
	public String getEdition() {
		return edition;
	}
	/**
	 * @param edition the edition to set
	 */
	public void setEdition(String edition) {
		this.edition = edition;
	}
	/**
	 * @return the listedMrp
	 */
	public double getListedMrp() {
		return listedMrp;
	}
	/**
	 * @param listedMrp the listedMrp to set
	 */
	public void setListedMrp(double listedMrp) {
		this.listedMrp = listedMrp;
	}
	/**
	 * @return the wsp
	 */
	public double getWsp() {
		return wsp;
	}
	/**
	 * @param wsp the wsp to set
	 */
	public void setWsp(double wsp) {
		this.wsp = wsp;
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
	 * @return the saleRateOn
	 */
	public String getSaleRateOn() {
		return saleRateOn;
	}
	/**
	 * @param saleRateOn the saleRateOn to set
	 */
	public void setSaleRateOn(String saleRateOn) {
		this.saleRateOn = saleRateOn;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ItemMaster [id=" + id + ", name=" + name + ", code=" + code
				+ ", groupId=" + groupId + ", categoryId=" + categoryId
				+ ", subCategoryId=" + subCategoryId + ", scheduleId="
				+ scheduleId + ", contentId=" + contentId + ", brandId="
				+ brandId + ", manufacturerId=" + manufacturerId
				+ ", entryDate=" + entryDate + ", vat=" + vat + ", isOnMrp="
				+ isOnMrp + ", packUnitId=" + packUnitId + ", conversion="
				+ conversion + ", looseUnitId=" + looseUnitId + ", storage="
				+ storage + ", care=" + care + ", reorderLevel=" + reorderLevel
				+ ", reorderLevelUnitId=" + reorderLevelUnitId + ", price="
				+ price + ", isTaxable=" + isTaxable + ", note=" + note
				+ ", companyId=" + companyId + ", isDeleted=" + isDeleted
				+ ", createdBy=" + createdBy + ", createdDate=" + createdDate
				+ ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate
				+ ", rackId=" + rackId + ", groupMaster=" + groupMaster
				+ ", categoryMaster=" + categoryMaster + ", subCategoryMaster="
				+ subCategoryMaster + ", scheduleMaster=" + scheduleMaster
				+ ", contentMaster=" + contentMaster + ", brandMaster="
				+ brandMaster + ", manufacturerMaster=" + manufacturerMaster
				+ ", packUnit=" + packUnit + ", looseUnit=" + looseUnit
				+ ", reorderLevelUnit=" + reorderLevelUnit + ", rack=" + rack
				+ ", markup=" + markup + ", strength=" + strength
				+ ", netContent=" + netContent + ", lang=" + lang
				+ ", purchaseTaxId=" + purchaseTaxId
				+ ", purchaseTaxPercentage=" + purchaseTaxPercentage
				+ ", saleTaxId=" + saleTaxId + ", saleTaxPercentage="
				+ saleTaxPercentage + ", sku=" + sku + ", discount=" + discount
				+ ", maxDiscountLimit=" + maxDiscountLimit + ", isDiscount="
				+ isDiscount + ", saleTax=" + saleTax + ", purchaseTax="
				+ purchaseTax + ", hsnCode=" + hsnCode + ", isLooseSale="
				+ isLooseSale + ", marketerId=" + marketerId + ", taxTypeId="
				+ taxTypeId + ", reorderLevelQty=" + reorderLevelQty
				+ ", maximumQty=" + maximumQty + ", purchaseRate="
				+ purchaseRate + ", saleRate=" + saleRate + ", mrp=" + mrp
				+ ", launchDate=" + launchDate + ", discontinueDate="
				+ discontinueDate + ", marketerMaster=" + marketerMaster
				+ ", description=" + description + ", printName=" + printName
				+ ", imgUrl=" + imgUrl + ", maxLevel=" + maxLevel
				+ ", minLevel=" + minLevel + ", isActive=" + isActive
				+ ", colour=" + colour + ", sizeType=" + sizeType + ", size="
				+ size + ", weight=" + weight + ", partNumber=" + partNumber
				+ ", authorName=" + authorName + ", edition=" + edition
				+ ", listedMrp=" + listedMrp + ", wsp=" + wsp
				+ ", stockRequired=" + stockRequired + ", batchWiseStock="
				+ batchWiseStock + ", expiryDateRequired=" + expiryDateRequired
				+ ", dualStockRequired=" + dualStockRequired + ", saleRateOn="
				+ saleRateOn + ", locationRequired=" + locationRequired
				+ ", priceListRequired=" + priceListRequired
				+ ", sizeWiseStockRequired=" + sizeWiseStockRequired
				+ ", warrantyRequired=" + warrantyRequired
				+ ", warrantyTypeOn=" + warrantyTypeOn + ", warrantyMonth="
				+ warrantyMonth + ", mrpRequired=" + mrpRequired
				+ ", serialNoRequired=" + serialNoRequired
				+ ", serialNoSuffRequired=" + serialNoSuffRequired
				+ ", serialNoPrefRequired=" + serialNoPrefRequired
				+ ", serialNoType=" + serialNoType + ", retailTypeId="
				+ retailTypeId + ", expiryMonthRequired=" + expiryMonthRequired
				+ ", colourWiseStockRequired=" + colourWiseStockRequired
				+ ", rateOn=" + rateOn + "]";
	}

}
