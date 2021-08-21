package com.sharobi.yewpos.pos.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author Manodip
 */
public class Sales implements Serializable {

	private static final long serialVersionUID = 1L;

	private int id;

	private String invNo;

	private String invDate;

	private String invTime;

	private int customerId;

	private String customerName;

	private String customerAddress;

	private String customerPhone;

	private int doctorId;

	private String doctorName;

	private int invMode;

	private double grossAmount;

	private double edAmount;

	private double discAmount;

	private double taxAmount;

	private double vatAmount;

	/** for return adjasment
	 *
	 */
	private double adjAmount;

	private double netAmount;

	private double roundoff;

	private String remarks;

	private int pstId;

	private String pstNo;

	private int finyrId;

	private int storeId;

	private int companyId;

	private int isDeleted;

	private int createdBy;

	private Date createdDate;

	private int updatedBy;

	private Date updatedDate;

	private String lang;

	private List<SalesDetails> salesDetails;

	private String finyrCode;

	private double cashAmount;

	private double cardAmount;

	private double creditAmount;

	private String cardFourDigit;

	private String cardExpDate;

	private double tenderAmount;

	private int printCount;

	private int holdFlag;

	private int isposted;

	private double customerDiscPer;

	private String dateText;
	private int saleReturnId;
	private String saleReturnInvNo;
	private List<SaleReturn> saleReturns;
	private double specialDiscPer;
	private double specialDiscAmount;
	private double totalMrp;
	private String prescriptionRegNo;
	private String prescriptionIssueDate;
	private String slipNo;
	private String esiCode;
	private String esiType;
	private String qs;
	private String mulSeriesPrefix;
	private double lotAdjAmount;
	private double othAdjAmount;

     /*
      * for ledger account
      */
	 private int duties_ledger_id;
	 private int round_ledger_id;
	 private int sale_ledger_id;
	 private int discount_ledger_id;
	 private int debitor_ledger_id;
	 private int debitor_cash_ledger_id;
	 private int card_ledger_id;
	 private int is_account;
	 private int lotAdjAmountId;
	 private int othAdjAmountId;


	/**
 * @return the is_account
 */
public int getIs_account() {
	return is_account;
}

/**
 * @param is_account the is_account to set
 */
public void setIs_account(int is_account) {
	this.is_account = is_account;
}

	/**
 * @return the duties_ledger_id
 */
public int getDuties_ledger_id() {
	return duties_ledger_id;
}

/**
 * @param duties_ledger_id the duties_ledger_id to set
 */
public void setDuties_ledger_id(int duties_ledger_id) {
	this.duties_ledger_id = duties_ledger_id;
}

/**
 * @return the round_ledger_id
 */
public int getRound_ledger_id() {
	return round_ledger_id;
}

/**
 * @param round_ledger_id the round_ledger_id to set
 */
public void setRound_ledger_id(int round_ledger_id) {
	this.round_ledger_id = round_ledger_id;
}

/**
 * @return the sale_ledger_id
 */
public int getSale_ledger_id() {
	return sale_ledger_id;
}

/**
 * @param sale_ledger_id the sale_ledger_id to set
 */
public void setSale_ledger_id(int sale_ledger_id) {
	this.sale_ledger_id = sale_ledger_id;
}

/**
 * @return the discount_ledger_id
 */
public int getDiscount_ledger_id() {
	return discount_ledger_id;
}

/**
 * @param discount_ledger_id the discount_ledger_id to set
 */
public void setDiscount_ledger_id(int discount_ledger_id) {
	this.discount_ledger_id = discount_ledger_id;
}

/**
 * @return the debitor_ledger_id
 */
public int getDebitor_ledger_id() {
	return debitor_ledger_id;
}

/**
 * @param debitor_ledger_id the debitor_ledger_id to set
 */
public void setDebitor_ledger_id(int debitor_ledger_id) {
	this.debitor_ledger_id = debitor_ledger_id;
}

/**
 * @return the debitor_cash_ledger_id
 */
public int getDebitor_cash_ledger_id() {
	return debitor_cash_ledger_id;
}

/**
 * @param debitor_cash_ledger_id the debitor_cash_ledger_id to set
 */
public void setDebitor_cash_ledger_id(int debitor_cash_ledger_id) {
	this.debitor_cash_ledger_id = debitor_cash_ledger_id;
}

/**
 * @return the card_ledger_id
 */
public int getCard_ledger_id() {
	return card_ledger_id;
}

/**
 * @param card_ledger_id the card_ledger_id to set
 */
public void setCard_ledger_id(int card_ledger_id) {
	this.card_ledger_id = card_ledger_id;
}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getInvTime() {
		return invTime;
	}

	public void setInvTime(String invTime) {
		this.invTime = invTime;
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

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
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

	public int getInvMode() {
		return invMode;
	}

	public void setInvMode(int invMode) {
		this.invMode = invMode;
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

	public double getTaxAmount() {
		return taxAmount;
	}

	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}

	public double getVatAmount() {
		return vatAmount;
	}

	public void setVatAmount(double vatAmount) {
		this.vatAmount = vatAmount;
	}

	public double getAdjAmount() {
		return adjAmount;
	}

	public void setAdjAmount(double adjAmount) {
		this.adjAmount = adjAmount;
	}

	public double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(double netAmount) {
		this.netAmount = netAmount;
	}

	public double getRoundoff() {
		return roundoff;
	}

	public void setRoundoff(double roundoff) {
		this.roundoff = roundoff;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public int getPstId() {
		return pstId;
	}

	public void setPstId(int pstId) {
		this.pstId = pstId;
	}

	public String getPstNo() {
		return pstNo;
	}

	public void setPstNo(String pstNo) {
		this.pstNo = pstNo;
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

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public List<SalesDetails> getSalesDetails() {
		return salesDetails;
	}

	public void setSalesDetails(List<SalesDetails> salesDetails) {
		this.salesDetails = salesDetails;
	}

	public String getFinyrCode() {
		return finyrCode;
	}

	public void setFinyrCode(String finyrCode) {
		this.finyrCode = finyrCode;
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

	public String getCardFourDigit() {
		return cardFourDigit;
	}

	public void setCardFourDigit(String cardFourDigit) {
		this.cardFourDigit = cardFourDigit;
	}

	public String getCardExpDate() {
		return cardExpDate;
	}

	public void setCardExpDate(String cardExpDate) {
		this.cardExpDate = cardExpDate;
	}

	public double getTenderAmount() {
		return tenderAmount;
	}

	public void setTenderAmount(double tenderAmount) {
		this.tenderAmount = tenderAmount;
	}

	public int getPrintCount() {
		return printCount;
	}

	public void setPrintCount(int printCount) {
		this.printCount = printCount;
	}

	public int getHoldFlag() {
		return holdFlag;
	}

	public void setHoldFlag(int holdFlag) {
		this.holdFlag = holdFlag;
	}

	public int getIsposted() {
		return isposted;
	}

	public void setIsposted(int isposted) {
		this.isposted = isposted;
	}

	public double getCustomerDiscPer() {
		return customerDiscPer;
	}

	public void setCustomerDiscPer(double customerDiscPer) {
		this.customerDiscPer = customerDiscPer;
	}

	public String getDateText() {
		return dateText;
	}

	public void setDateText(String dateText) {
		this.dateText = dateText;
	}

	public int getSaleReturnId() {
		return saleReturnId;
	}

	public void setSaleReturnId(int saleReturnId) {
		this.saleReturnId = saleReturnId;
	}

	public String getSaleReturnInvNo() {
		return saleReturnInvNo;
	}

	public void setSaleReturnInvNo(String saleReturnInvNo) {
		this.saleReturnInvNo = saleReturnInvNo;
	}

	public List<SaleReturn> getSaleReturns() {
		return saleReturns;
	}

	public void setSaleReturns(List<SaleReturn> saleReturns) {
		this.saleReturns = saleReturns;
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

	public double getTotalMrp() {
		return totalMrp;
	}

	public void setTotalMrp(double totalMrp) {
		this.totalMrp = totalMrp;
	}

	public String getPrescriptionRegNo() {
		return prescriptionRegNo;
	}

	public void setPrescriptionRegNo(String prescriptionRegNo) {
		this.prescriptionRegNo = prescriptionRegNo;
	}

	public String getPrescriptionIssueDate() {
		return prescriptionIssueDate;
	}

	public void setPrescriptionIssueDate(String prescriptionIssueDate) {
		this.prescriptionIssueDate = prescriptionIssueDate;
	}

	public String getSlipNo() {
		return slipNo;
	}

	public void setSlipNo(String slipNo) {
		this.slipNo = slipNo;
	}

	public String getEsiCode() {
		return esiCode;
	}

	public void setEsiCode(String esiCode) {
		this.esiCode = esiCode;
	}

	public String getEsiType() {
		return esiType;
	}

	public void setEsiType(String esiType) {
		this.esiType = esiType;
	}


	/**
	 * @return the qs
	 */
	public String getQs() {
		return qs;
	}

	/**
	 * @param qs the qs to set
	 */
	public void setQs(String qs) {
		this.qs = qs;
	}

	/**
	 * @return the mulSeriesPrefix
	 */
	public String getMulSeriesPrefix() {
		return mulSeriesPrefix;
	}

	/**
	 * @param mulSeriesPrefix the mulSeriesPrefix to set
	 */
	public void setMulSeriesPrefix(String mulSeriesPrefix) {
		this.mulSeriesPrefix = mulSeriesPrefix;
	}








	/**
	 * @return the lotAdjAmount
	 */
	public double getLotAdjAmount() {
		return lotAdjAmount;
	}

	/**
	 * @param lotAdjAmount the lotAdjAmount to set
	 */
	public void setLotAdjAmount(double lotAdjAmount) {
		this.lotAdjAmount = lotAdjAmount;
	}

	/**
	 * @return the othAdjAmount
	 */
	public double getOthAdjAmount() {
		return othAdjAmount;
	}

	/**
	 * @param othAdjAmount the othAdjAmount to set
	 */
	public void setOthAdjAmount(double othAdjAmount) {
		this.othAdjAmount = othAdjAmount;
	}


	/**
	 * @return the lotAdjAmountId
	 */
	public int getLotAdjAmountId() {
		return lotAdjAmountId;
	}

	/**
	 * @param lotAdjAmountId the lotAdjAmountId to set
	 */
	public void setLotAdjAmountId(int lotAdjAmountId) {
		this.lotAdjAmountId = lotAdjAmountId;
	}

	/**
	 * @return the othAdjAmountId
	 */
	public int getOthAdjAmountId() {
		return othAdjAmountId;
	}

	/**
	 * @param othAdjAmountId the othAdjAmountId to set
	 */
	public void setOthAdjAmountId(int othAdjAmountId) {
		this.othAdjAmountId = othAdjAmountId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Sales [id=" + id + ", invNo=" + invNo + ", invDate=" + invDate + ", invTime=" + invTime
				+ ", customerId=" + customerId + ", customerName=" + customerName + ", customerAddress="
				+ customerAddress + ", customerPhone=" + customerPhone + ", doctorId=" + doctorId + ", doctorName="
				+ doctorName + ", invMode=" + invMode + ", grossAmount=" + grossAmount + ", edAmount=" + edAmount
				+ ", discAmount=" + discAmount + ", taxAmount=" + taxAmount + ", vatAmount=" + vatAmount
				+ ", adjAmount=" + adjAmount + ", netAmount=" + netAmount + ", roundoff=" + roundoff + ", remarks="
				+ remarks + ", pstId=" + pstId + ", pstNo=" + pstNo + ", finyrId=" + finyrId + ", storeId=" + storeId
				+ ", companyId=" + companyId + ", isDeleted=" + isDeleted + ", createdBy=" + createdBy
				+ ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate
				+ ", lang=" + lang + ", salesDetails=" + salesDetails + ", finyrCode=" + finyrCode + ", cashAmount="
				+ cashAmount + ", cardAmount=" + cardAmount + ", creditAmount=" + creditAmount + ", cardFourDigit="
				+ cardFourDigit + ", cardExpDate=" + cardExpDate + ", tenderAmount=" + tenderAmount + ", printCount="
				+ printCount + ", holdFlag=" + holdFlag + ", isposted=" + isposted + ", customerDiscPer="
				+ customerDiscPer + ", dateText=" + dateText + ", saleReturnId=" + saleReturnId + ", saleReturnInvNo="
				+ saleReturnInvNo + ", saleReturns=" + saleReturns + ", specialDiscPer=" + specialDiscPer
				+ ", specialDiscAmount=" + specialDiscAmount + ", totalMrp=" + totalMrp + ", prescriptionRegNo="
				+ prescriptionRegNo + ", prescriptionIssueDate=" + prescriptionIssueDate + ", slipNo=" + slipNo
				+ ", esiCode=" + esiCode + ", esiType=" + esiType + ", qs=" + qs + ", mulSeriesPrefix="
				+ mulSeriesPrefix + ", lotAdjAmount=" + lotAdjAmount + ", othAdjAmount=" + othAdjAmount
				+ ", duties_ledger_id=" + duties_ledger_id + ", round_ledger_id=" + round_ledger_id
				+ ", sale_ledger_id=" + sale_ledger_id + ", discount_ledger_id=" + discount_ledger_id
				+ ", debitor_ledger_id=" + debitor_ledger_id + ", debitor_cash_ledger_id=" + debitor_cash_ledger_id
				+ ", card_ledger_id=" + card_ledger_id + ", is_account=" + is_account + ", lotAdjAmountId="
				+ lotAdjAmountId + ", othAdjAmountId=" + othAdjAmountId + "]";
	}






}
