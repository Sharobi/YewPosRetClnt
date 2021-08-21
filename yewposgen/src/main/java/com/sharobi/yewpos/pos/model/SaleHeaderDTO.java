package com.sharobi.yewpos.pos.model;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Manodip
 */

public class SaleHeaderDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int saleId;
	private String invNo;
	private String invDate;
	private int invMode;
	private String invModeName;
	private double grossAmount;
	private double edAmount;
	private double discAmount;
	private double vatAmount;
	private double taxAmount; // now used as gstamt
	private double adjAmount;
	private double roundoff;
	private double netAmount;
	private String remarks;
	private int customerId;
	private String customerName;
	private String customerPhone;
	private String customerAddress;
	 private String gstNo;
	private int doctorId;
	private String doctorName;
	private double cashAmount;
	private double cardAmount;
	private double creditAmount;
	private String cardFourDigit;
	private String cardExpDate;
	private double tenderAmount;
	private int printCount;
	private int holdFlag;
	private int isPosted;
	private double customerDiscPer;
	private int saleReturnId;
	private double specialDiscPer;
	private double specialDiscAmount;
	private double creditLimit;
	private double totalAmount;
	private double taxableAmount;
	private double cgst;
	private double sgst;
	private double igst;
	private double totalMrp;
	private String esiType;
	private String prescriptionRegNo;
	private String prescriptionIssueDate;
	private String slipNo;
	private String esiCode;
	private String customerCode;
	private String gender;
	private String placeOfTreatment;
	private double totalReturnAmount;
	private String allReturnIds;
	private double lotAdjAmount;
	private double othAdjAmount;
	
	private String dlNo;
	private String panNo;
    
	private String customerEmail; // new added 10.5.2019
	private String customerGstNo;// new added 10.5.2019
	private String invTime; // new added 29.5.2019

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

	public double getSgst() {
		return sgst;
	}

	public void setSgst(double sgst) {
		this.sgst = sgst;
	}

	public double getIgst() {
		return igst;
	}

	public void setIgst(double igst) {
		this.igst = igst;
	}

	public double getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}

	public double getCustomerDiscPer() {
		return customerDiscPer;
	}

	public void setCustomerDiscPer(double customerDiscPer) {
		this.customerDiscPer = customerDiscPer;
	}

	public int getSaleId() {
		return saleId;
	}

	public void setSaleId(int saleId) {
		this.saleId = saleId;
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

	public int getInvMode() {
		return invMode;
	}

	public void setInvMode(int invMode) {
		this.invMode = invMode;
	}

	public String getInvModeName() {
		return invModeName;
	}

	public void setInvModeName(String invModeName) {
		this.invModeName = invModeName;
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

	public double getRoundoff() {
		return roundoff;
	}

	public void setRoundoff(double roundoff) {
		this.roundoff = roundoff;
	}

	public double getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(double netAmount) {
		this.netAmount = netAmount;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
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

	public void setCreditmount(double creditAmount) {
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

	public int getIsPosted() {
		return isPosted;
	}

	public void setIsPosted(int isPosted) {
		this.isPosted = isPosted;
	}

	public String getInvTime() {
		return invTime;
	}

	public void setInvTime(String invTime) {
		this.invTime = invTime;
	}

	public int getSaleReturnId() {
		return saleReturnId;
	}

	public void setSaleReturnId(int saleReturnId) {
		this.saleReturnId = saleReturnId;
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

	public void setCreditAmount(double creditAmount) {
		this.creditAmount = creditAmount;
	}

	public double getTotalMrp() {
		return totalMrp;
	}

	public void setTotalMrp(double totalMrp) {
		this.totalMrp = totalMrp;
	}

	public String getEsiType() {
		return esiType;
	}

	public void setEsiType(String esiType) {
		this.esiType = esiType;
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

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getPlaceOfTreatment() {
		return placeOfTreatment;
	}

	public void setPlaceOfTreatment(String placeOfTreatment) {
		this.placeOfTreatment = placeOfTreatment;
	}

	public double getTotalReturnAmount() {
		return totalReturnAmount;
	}

	public void setTotalReturnAmount(double totalReturnAmount) {
		this.totalReturnAmount = totalReturnAmount;
	}

	public String getAllReturnIds() {
		return allReturnIds;
	}

	public void setAllReturnIds(String allReturnIds) {
		this.allReturnIds = allReturnIds;
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
	 * @return the gstNo
	 */
	public String getGstNo() {
		return gstNo;
	}

	/**
	 * @param gstNo the gstNo to set
	 */
	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}

	/**
	 * @return the dlNo
	 */
	public String getDlNo() {
		return dlNo;
	}

	/**
	 * @param dlNo the dlNo to set
	 */
	public void setDlNo(String dlNo) {
		this.dlNo = dlNo;
	}

	/**
	 * @return the panNo
	 */
	public String getPanNo() {
		return panNo;
	}

	/**
	 * @param panNo the panNo to set
	 */
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	

	public String getCustomerGstNo() {
		return customerGstNo;
	}

	public void setCustomerGstNo(String customerGstNo) {
		this.customerGstNo = customerGstNo;
	}

	@Override
	public String toString() {
		return "SaleHeaderDTO [saleId=" + saleId + ", invNo=" + invNo + ", invDate=" + invDate + ", invMode=" + invMode
				+ ", invModeName=" + invModeName + ", grossAmount=" + grossAmount + ", edAmount=" + edAmount
				+ ", discAmount=" + discAmount + ", vatAmount=" + vatAmount + ", taxAmount=" + taxAmount
				+ ", adjAmount=" + adjAmount + ", roundoff=" + roundoff + ", netAmount=" + netAmount + ", remarks="
				+ remarks + ", customerId=" + customerId + ", customerName=" + customerName + ", customerPhone="
				+ customerPhone + ", customerAddress=" + customerAddress + ", gstNo=" + gstNo + ", doctorId=" + doctorId
				+ ", doctorName=" + doctorName + ", cashAmount=" + cashAmount + ", cardAmount=" + cardAmount
				+ ", creditAmount=" + creditAmount + ", cardFourDigit=" + cardFourDigit + ", cardExpDate=" + cardExpDate
				+ ", tenderAmount=" + tenderAmount + ", printCount=" + printCount + ", holdFlag=" + holdFlag
				+ ", isPosted=" + isPosted + ", customerDiscPer=" + customerDiscPer + ", invTime=" + invTime
				+ ", saleReturnId=" + saleReturnId + ", specialDiscPer=" + specialDiscPer + ", specialDiscAmount="
				+ specialDiscAmount + ", creditLimit=" + creditLimit + ", totalAmount=" + totalAmount
				+ ", taxableAmount=" + taxableAmount + ", cgst=" + cgst + ", sgst=" + sgst + ", igst=" + igst
				+ ", totalMrp=" + totalMrp + ", esiType=" + esiType + ", prescriptionRegNo=" + prescriptionRegNo
				+ ", prescriptionIssueDate=" + prescriptionIssueDate + ", slipNo=" + slipNo + ", esiCode=" + esiCode
				+ ", customerCode=" + customerCode + ", gender=" + gender + ", placeOfTreatment=" + placeOfTreatment
				+ ", totalReturnAmount=" + totalReturnAmount + ", allReturnIds=" + allReturnIds + ", lotAdjAmount="
				+ lotAdjAmount + ", othAdjAmount=" + othAdjAmount + ", dlNo=" + dlNo + ", panNo=" + panNo
				+ ", customerEmail=" + customerEmail + ", customerGstNo=" + customerGstNo + "]";
	}
	
	

}
