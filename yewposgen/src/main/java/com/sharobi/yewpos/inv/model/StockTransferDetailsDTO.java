package com.sharobi.yewpos.inv.model;

import java.io.Serializable;
import java.util.Date;

public class StockTransferDetailsDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private int transId;
	private int transDetailId;
	private String stockTransNo;
	private String stockTransDate;
	private int itemId;
	private String itemName;
	private String itemCode;
	private String sku;
	private String hsnCode;
	private String batchNo;
	private String mfdDate;
	private String expiryDate;
	private String expiryDateFormat;
	private int packUnitId;
	private String packUnitName;
	private double sendPackQty;
	private double actSendPackQty;
	private double recvdPackQty;
	private double transitPackQty;
	private int conversion;
	private int looseUnitId;
	private String looseUnitName;
	private int sendLooseQty;
	private int actSendLooseQty;
	private int recvdLooseQty;
	private int transitLooseQty;
	private double mrp;
	private double rate;
	private double saleRate;
	private double purchaseRate;
	private double mrpPerUnit;
	private double ratePerUnit;
	private double sendItemGrossAmount;
	private double recvdItemGrossAmount;
	private double transitItemGrossAmount;
	private double discPer;
	private double sendItemDiscAmount;
	private double recvdItemDiscAmount;
	private double transitItemDiscAmount;
	private int taxId;
	private int taxTypeId;
	private double taxPercentage;
	private double sendItemTaxAmount;
	private double recvdItemTaxAmount;
	private double transitItemTaxAmount;
	private double sendItemNetAmount;
	private double recvdItemNetAmount;
	private double transitItemNetAmount;
	private int isDeleted;
	private int companyId;
	private int fromStoreId;
	private String fromStoreName;
	private int fromFinyrId;
	private int toStoreId;
	private String toStoreName;
	private int toFinyrId;
	private int groupId;
	private String groupName;
	private int manufacturerId;
	private String manufacturerName;
	private String itemUniqueKey;
	
	public int getTransId() {
		return transId;
	}

	public void setTransId(int transId) {
		this.transId = transId;
	}

	public int getTransDetailId() {
		return transDetailId;
	}

	public void setTransDetailId(int transDetailId) {
		this.transDetailId = transDetailId;
	}

	public String getStockTransNo() {
		return stockTransNo;
	}

	public void setStockTransNo(String stockTransNo) {
		this.stockTransNo = stockTransNo;
	}

	public String getStockTransDate() {
		return stockTransDate;
	}

	public void setStockTransDate(String stockTransDate) {
		this.stockTransDate = stockTransDate;
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

	public String getItemCode() {
		return itemCode;
	}

	public void setItemCode(String itemCode) {
		this.itemCode = itemCode;
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

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
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

	public double getSendPackQty() {
		return sendPackQty;
	}

	public void setSendPackQty(double sendPackQty) {
		this.sendPackQty = sendPackQty;
	}

	public double getActSendPackQty() {
		return actSendPackQty;
	}

	public void setActSendPackQty(double actSendPackQty) {
		this.actSendPackQty = actSendPackQty;
	}

	public double getRecvdPackQty() {
		return recvdPackQty;
	}

	public void setRecvdPackQty(double recvdPackQty) {
		this.recvdPackQty = recvdPackQty;
	}

	public double getTransitPackQty() {
		return transitPackQty;
	}

	public void setTransitPackQty(double transitPackQty) {
		this.transitPackQty = transitPackQty;
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

	public int getSendLooseQty() {
		return sendLooseQty;
	}

	public void setSendLooseQty(int sendLooseQty) {
		this.sendLooseQty = sendLooseQty;
	}

	public int getActSendLooseQty() {
		return actSendLooseQty;
	}

	public void setActSendLooseQty(int actSendLooseQty) {
		this.actSendLooseQty = actSendLooseQty;
	}

	public int getRecvdLooseQty() {
		return recvdLooseQty;
	}

	public void setRecvdLooseQty(int recvdLooseQty) {
		this.recvdLooseQty = recvdLooseQty;
	}

	public int getTransitLooseQty() {
		return transitLooseQty;
	}

	public void setTransitLooseQty(int transitLooseQty) {
		this.transitLooseQty = transitLooseQty;
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

	public double getPurchaseRate() {
		return purchaseRate;
	}

	public void setPurchaseRate(double purchaseRate) {
		this.purchaseRate = purchaseRate;
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

	public double getSendItemGrossAmount() {
		return sendItemGrossAmount;
	}

	public void setSendItemGrossAmount(double sendItemGrossAmount) {
		this.sendItemGrossAmount = sendItemGrossAmount;
	}

	public double getRecvdItemGrossAmount() {
		return recvdItemGrossAmount;
	}

	public void setRecvdItemGrossAmount(double recvdItemGrossAmount) {
		this.recvdItemGrossAmount = recvdItemGrossAmount;
	}

	public double getTransitItemGrossAmount() {
		return transitItemGrossAmount;
	}

	public void setTransitItemGrossAmount(double transitItemGrossAmount) {
		this.transitItemGrossAmount = transitItemGrossAmount;
	}

	public double getDiscPer() {
		return discPer;
	}

	public void setDiscPer(double discPer) {
		this.discPer = discPer;
	}

	public double getSendItemDiscAmount() {
		return sendItemDiscAmount;
	}

	public void setSendItemDiscAmount(double sendItemDiscAmount) {
		this.sendItemDiscAmount = sendItemDiscAmount;
	}

	public double getRecvdItemDiscAmount() {
		return recvdItemDiscAmount;
	}

	public void setRecvdItemDiscAmount(double recvdItemDiscAmount) {
		this.recvdItemDiscAmount = recvdItemDiscAmount;
	}

	public double getTransitItemDiscAmount() {
		return transitItemDiscAmount;
	}

	public void setTransitItemDiscAmount(double transitItemDiscAmount) {
		this.transitItemDiscAmount = transitItemDiscAmount;
	}

	public int getTaxId() {
		return taxId;
	}

	public void setTaxId(int taxId) {
		this.taxId = taxId;
	}

	public int getTaxTypeId() {
		return taxTypeId;
	}

	public void setTaxTypeId(int taxTypeId) {
		this.taxTypeId = taxTypeId;
	}

	public double getTaxPercentage() {
		return taxPercentage;
	}

	public void setTaxPercentage(double taxPercentage) {
		this.taxPercentage = taxPercentage;
	}

	public double getSendItemTaxAmount() {
		return sendItemTaxAmount;
	}

	public void setSendItemTaxAmount(double sendItemTaxAmount) {
		this.sendItemTaxAmount = sendItemTaxAmount;
	}

	public double getRecvdItemTaxAmount() {
		return recvdItemTaxAmount;
	}

	public void setRecvdItemTaxAmount(double recvdItemTaxAmount) {
		this.recvdItemTaxAmount = recvdItemTaxAmount;
	}

	public double getTransitItemTaxAmount() {
		return transitItemTaxAmount;
	}

	public void setTransitItemTaxAmount(double transitItemTaxAmount) {
		this.transitItemTaxAmount = transitItemTaxAmount;
	}

	public double getSendItemNetAmount() {
		return sendItemNetAmount;
	}

	public void setSendItemNetAmount(double sendItemNetAmount) {
		this.sendItemNetAmount = sendItemNetAmount;
	}

	public double getRecvdItemNetAmount() {
		return recvdItemNetAmount;
	}

	public void setRecvdItemNetAmount(double recvdItemNetAmount) {
		this.recvdItemNetAmount = recvdItemNetAmount;
	}

	public double getTransitItemNetAmount() {
		return transitItemNetAmount;
	}

	public void setTransitItemNetAmount(double transitItemNetAmount) {
		this.transitItemNetAmount = transitItemNetAmount;
	}

	public int getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public int getFromStoreId() {
		return fromStoreId;
	}

	public void setFromStoreId(int fromStoreId) {
		this.fromStoreId = fromStoreId;
	}

	public String getFromStoreName() {
		return fromStoreName;
	}

	public void setFromStoreName(String fromStoreName) {
		this.fromStoreName = fromStoreName;
	}

	public int getFromFinyrId() {
		return fromFinyrId;
	}

	public void setFromFinyrId(int fromFinyrId) {
		this.fromFinyrId = fromFinyrId;
	}

	public int getToStoreId() {
		return toStoreId;
	}

	public void setToStoreId(int toStoreId) {
		this.toStoreId = toStoreId;
	}

	public String getToStoreName() {
		return toStoreName;
	}

	public void setToStoreName(String toStoreName) {
		this.toStoreName = toStoreName;
	}

	public int getToFinyrId() {
		return toFinyrId;
	}

	public void setToFinyrId(int toFinyrId) {
		this.toFinyrId = toFinyrId;
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

	public String getItemUniqueKey() {
		return itemUniqueKey;
	}

	public void setItemUniqueKey(String itemUniqueKey) {
		this.itemUniqueKey = itemUniqueKey;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return "StockTransferDetailsDTO [transId=" + transId + ", transDetailId=" + transDetailId + ", stockTransNo="
				+ stockTransNo + ", stockTransDate=" + stockTransDate + ", itemId=" + itemId + ", itemName=" + itemName
				+ ", itemCode=" + itemCode + ", sku=" + sku + ", hsnCode=" + hsnCode + ", batchNo=" + batchNo
				+ ", mfdDate=" + mfdDate + ", expiryDate=" + expiryDate + ", expiryDateFormat=" + expiryDateFormat
				+ ", packUnitId=" + packUnitId + ", packUnitName=" + packUnitName + ", sendPackQty=" + sendPackQty
				+ ", actSendPackQty=" + actSendPackQty + ", recvdPackQty=" + recvdPackQty + ", transitPackQty="
				+ transitPackQty + ", conversion=" + conversion + ", looseUnitId=" + looseUnitId + ", looseUnitName="
				+ looseUnitName + ", sendLooseQty=" + sendLooseQty + ", actSendLooseQty=" + actSendLooseQty
				+ ", recvdLooseQty=" + recvdLooseQty + ", transitLooseQty=" + transitLooseQty + ", mrp=" + mrp
				+ ", rate=" + rate + ", saleRate=" + saleRate + ", purchaseRate=" + purchaseRate + ", mrpPerUnit="
				+ mrpPerUnit + ", ratePerUnit=" + ratePerUnit + ", sendItemGrossAmount=" + sendItemGrossAmount
				+ ", recvdItemGrossAmount=" + recvdItemGrossAmount + ", transitItemGrossAmount="
				+ transitItemGrossAmount + ", discPer=" + discPer + ", sendItemDiscAmount=" + sendItemDiscAmount
				+ ", recvdItemDiscAmount=" + recvdItemDiscAmount + ", transitItemDiscAmount=" + transitItemDiscAmount
				+ ", taxId=" + taxId + ", taxTypeId=" + taxTypeId + ", taxPercentage=" + taxPercentage
				+ ", sendItemTaxAmount=" + sendItemTaxAmount + ", recvdItemTaxAmount=" + recvdItemTaxAmount
				+ ", transitItemTaxAmount=" + transitItemTaxAmount + ", sendItemNetAmount=" + sendItemNetAmount
				+ ", recvdItemNetAmount=" + recvdItemNetAmount + ", transitItemNetAmount=" + transitItemNetAmount
				+ ", isDeleted=" + isDeleted + ", companyId=" + companyId + ", fromStoreId=" + fromStoreId
				+ ", fromStoreName=" + fromStoreName + ", fromFinyrId=" + fromFinyrId + ", toStoreId=" + toStoreId
				+ ", toStoreName=" + toStoreName + ", toFinyrId=" + toFinyrId + ", groupId=" + groupId + ", groupName="
				+ groupName + ", manufacturerId=" + manufacturerId + ", manufacturerName=" + manufacturerName
				+ ", itemUniqueKey=" + itemUniqueKey + "]";
	}
	

}
