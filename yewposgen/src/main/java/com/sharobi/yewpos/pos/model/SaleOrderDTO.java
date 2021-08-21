/**
 * 
 */
package com.sharobi.yewpos.pos.model;

import java.io.Serializable;
import java.util.Date;

public class SaleOrderDTO  implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int saleOrderId;

    private String invNo;

    private String invDate;

    private String dueDate;

    private String invType;

    private int customerId;
	
	private String customerName;
	
	private String customerAddress;
	
	private String customerPhone;

	private double customerDiscPer;
	
	private int doctorId;
	
	private String doctorName;

    private double grossAmount;

    private double discAmount;

    private double adjAmount;

    private double taxAmount;

    private double roundoff;

    private double netAmount;

    private double totalMrp;
    
    private double advAmount;

    private String remarks;

    private int status;

    private int isPosted;
    
    private double rDvSph;
	
	private double rDvCyl;
	
	private double rDvAxis;
	
	private double lDvSph;
	
	private double lDvCyl;
	
	private double lDvAxis;
	
	private double rNvSph;
	
	private double rNvCyl;
	
	private double rNvAxis;
	
	private double lNvSph;
	
	private double lNvCyl;
	
	private double lNvAxis;
	
	private double rAdd;
	
	private double lAdd;
	
	private int companyId;
	private int storeId;
	
	private String customerEmail; // new added 10.5.2019
	private String customerGstNo; // new added 10.5.2019
	

	/**
	 * @return the saleOrderId
	 */
	public int getSaleOrderId() {
		return saleOrderId;
	}

	/**
	 * @param saleOrderId the saleOrderId to set
	 */
	public void setSaleOrderId(int saleOrderId) {
		this.saleOrderId = saleOrderId;
	}

	/**
	 * @return the invNo
	 */
	public String getInvNo() {
		return invNo;
	}

	/**
	 * @param invNo the invNo to set
	 */
	public void setInvNo(String invNo) {
		this.invNo = invNo;
	}

	/**
	 * @return the invDate
	 */
	public String getInvDate() {
		return invDate;
	}

	/**
	 * @param invDate the invDate to set
	 */
	public void setInvDate(String invDate) {
		this.invDate = invDate;
	}

	/**
	 * @return the dueDate
	 */
	public String getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * @return the invType
	 */
	public String getInvType() {
		return invType;
	}

	/**
	 * @param invType the invType to set
	 */
	public void setInvType(String invType) {
		this.invType = invType;
	}

	/**
	 * @return the customerId
	 */
	public int getCustomerId() {
		return customerId;
	}

	/**
	 * @param customerId the customerId to set
	 */
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	/**
	 * @return the customerName
	 */
	public String getCustomerName() {
		return customerName;
	}

	/**
	 * @param customerName the customerName to set
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	/**
	 * @return the customerAddress
	 */
	public String getCustomerAddress() {
		return customerAddress;
	}

	/**
	 * @param customerAddress the customerAddress to set
	 */
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	/**
	 * @return the customerPhone
	 */
	public String getCustomerPhone() {
		return customerPhone;
	}

	/**
	 * @param customerPhone the customerPhone to set
	 */
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	/**
	 * @return the customerDiscPer
	 */
	public double getCustomerDiscPer() {
		return customerDiscPer;
	}

	/**
	 * @param customerDiscPer the customerDiscPer to set
	 */
	public void setCustomerDiscPer(double customerDiscPer) {
		this.customerDiscPer = customerDiscPer;
	}

	/**
	 * @return the advAmount
	 */
	public double getAdvAmount() {
		return advAmount;
	}

	/**
	 * @param advAmount the advAmount to set
	 */
	public void setAdvAmount(double advAmount) {
		this.advAmount = advAmount;
	}

	/**
	 * @return the grossAmount
	 */
	public double getGrossAmount() {
		return grossAmount;
	}

	/**
	 * @param grossAmount the grossAmount to set
	 */
	public void setGrossAmount(double grossAmount) {
		this.grossAmount = grossAmount;
	}

	/**
	 * @return the discAmount
	 */
	public double getDiscAmount() {
		return discAmount;
	}

	/**
	 * @param discAmount the discAmount to set
	 */
	public void setDiscAmount(double discAmount) {
		this.discAmount = discAmount;
	}

	/**
	 * @return the adjAmount
	 */
	public double getAdjAmount() {
		return adjAmount;
	}

	/**
	 * @param adjAmount the adjAmount to set
	 */
	public void setAdjAmount(double adjAmount) {
		this.adjAmount = adjAmount;
	}

	/**
	 * @return the taxAmount
	 */
	public double getTaxAmount() {
		return taxAmount;
	}

	/**
	 * @param taxAmount the taxAmount to set
	 */
	public void setTaxAmount(double taxAmount) {
		this.taxAmount = taxAmount;
	}

	/**
	 * @return the roundoff
	 */
	public double getRoundoff() {
		return roundoff;
	}

	/**
	 * @param roundoff the roundoff to set
	 */
	public void setRoundoff(double roundoff) {
		this.roundoff = roundoff;
	}

	/**
	 * @return the netAmount
	 */
	public double getNetAmount() {
		return netAmount;
	}

	/**
	 * @param netAmount the netAmount to set
	 */
	public void setNetAmount(double netAmount) {
		this.netAmount = netAmount;
	}

	/**
	 * @return the totalMrp
	 */
	public double getTotalMrp() {
		return totalMrp;
	}

	/**
	 * @param totalMrp the totalMrp to set
	 */
	public void setTotalMrp(double totalMrp) {
		this.totalMrp = totalMrp;
	}

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the isPosted
	 */
	public int getIsPosted() {
		return isPosted;
	}

	/**
	 * @param isPosted the isPosted to set
	 */
	public void setIsPosted(int isPosted) {
		this.isPosted = isPosted;
	}

	/**
	 * @return the doctorId
	 */
	public int getDoctorId() {
		return doctorId;
	}

	/**
	 * @param doctorId the doctorId to set
	 */
	public void setDoctorId(int doctorId) {
		this.doctorId = doctorId;
	}

	/**
	 * @return the doctorName
	 */
	public String getDoctorName() {
		return doctorName;
	}

	/**
	 * @param doctorName the doctorName to set
	 */
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	/**
	 * @return the rDvSph
	 */
	public double getrDvSph() {
		return rDvSph;
	}

	/**
	 * @param rDvSph the rDvSph to set
	 */
	public void setrDvSph(double rDvSph) {
		this.rDvSph = rDvSph;
	}

	/**
	 * @return the rDvCyl
	 */
	public double getrDvCyl() {
		return rDvCyl;
	}

	/**
	 * @param rDvCyl the rDvCyl to set
	 */
	public void setrDvCyl(double rDvCyl) {
		this.rDvCyl = rDvCyl;
	}

	/**
	 * @return the rDvAxis
	 */
	public double getrDvAxis() {
		return rDvAxis;
	}

	/**
	 * @param rDvAxis the rDvAxis to set
	 */
	public void setrDvAxis(double rDvAxis) {
		this.rDvAxis = rDvAxis;
	}

	/**
	 * @return the lDvSph
	 */
	public double getlDvSph() {
		return lDvSph;
	}

	/**
	 * @param lDvSph the lDvSph to set
	 */
	public void setlDvSph(double lDvSph) {
		this.lDvSph = lDvSph;
	}

	/**
	 * @return the lDvCyl
	 */
	public double getlDvCyl() {
		return lDvCyl;
	}

	/**
	 * @param lDvCyl the lDvCyl to set
	 */
	public void setlDvCyl(double lDvCyl) {
		this.lDvCyl = lDvCyl;
	}

	/**
	 * @return the lDvAxis
	 */
	public double getlDvAxis() {
		return lDvAxis;
	}

	/**
	 * @param lDvAxis the lDvAxis to set
	 */
	public void setlDvAxis(double lDvAxis) {
		this.lDvAxis = lDvAxis;
	}

	/**
	 * @return the rNvSph
	 */
	public double getrNvSph() {
		return rNvSph;
	}

	/**
	 * @param rNvSph the rNvSph to set
	 */
	public void setrNvSph(double rNvSph) {
		this.rNvSph = rNvSph;
	}

	/**
	 * @return the rNvCyl
	 */
	public double getrNvCyl() {
		return rNvCyl;
	}

	/**
	 * @param rNvCyl the rNvCyl to set
	 */
	public void setrNvCyl(double rNvCyl) {
		this.rNvCyl = rNvCyl;
	}

	/**
	 * @return the rNvAxis
	 */
	public double getrNvAxis() {
		return rNvAxis;
	}

	/**
	 * @param rNvAxis the rNvAxis to set
	 */
	public void setrNvAxis(double rNvAxis) {
		this.rNvAxis = rNvAxis;
	}

	/**
	 * @return the lNvSph
	 */
	public double getlNvSph() {
		return lNvSph;
	}

	/**
	 * @param lNvSph the lNvSph to set
	 */
	public void setlNvSph(double lNvSph) {
		this.lNvSph = lNvSph;
	}

	/**
	 * @return the lNvCyl
	 */
	public double getlNvCyl() {
		return lNvCyl;
	}

	/**
	 * @param lNvCyl the lNvCyl to set
	 */
	public void setlNvCyl(double lNvCyl) {
		this.lNvCyl = lNvCyl;
	}

	/**
	 * @return the lNvAxis
	 */
	public double getlNvAxis() {
		return lNvAxis;
	}

	/**
	 * @param lNvAxis the lNvAxis to set
	 */
	public void setlNvAxis(double lNvAxis) {
		this.lNvAxis = lNvAxis;
	}

	/**
	 * @return the rAdd
	 */
	public double getrAdd() {
		return rAdd;
	}

	/**
	 * @param rAdd the rAdd to set
	 */
	public void setrAdd(double rAdd) {
		this.rAdd = rAdd;
	}

	/**
	 * @return the lAdd
	 */
	public double getlAdd() {
		return lAdd;
	}

	/**
	 * @param lAdd the lAdd to set
	 */
	public void setlAdd(double lAdd) {
		this.lAdd = lAdd;
	}

	/**
	 * @return the companyId
	 */
	public int getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	/**
	 * @return the storeId
	 */
	public int getStoreId() {
		return storeId;
	}

	/**
	 * @param storeId the storeId to set
	 */
	public void setStoreId(int storeId) {
		this.storeId = storeId;
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
		return "SaleOrderDTO [saleOrderId=" + saleOrderId + ", invNo=" + invNo + ", invDate=" + invDate + ", dueDate="
				+ dueDate + ", invType=" + invType + ", customerId=" + customerId + ", customerName=" + customerName
				+ ", customerAddress=" + customerAddress + ", customerPhone=" + customerPhone + ", customerDiscPer="
				+ customerDiscPer + ", doctorId=" + doctorId + ", doctorName=" + doctorName + ", grossAmount="
				+ grossAmount + ", discAmount=" + discAmount + ", adjAmount=" + adjAmount + ", taxAmount=" + taxAmount
				+ ", roundoff=" + roundoff + ", netAmount=" + netAmount + ", totalMrp=" + totalMrp + ", advAmount="
				+ advAmount + ", remarks=" + remarks + ", status=" + status + ", isPosted=" + isPosted + ", rDvSph="
				+ rDvSph + ", rDvCyl=" + rDvCyl + ", rDvAxis=" + rDvAxis + ", lDvSph=" + lDvSph + ", lDvCyl=" + lDvCyl
				+ ", lDvAxis=" + lDvAxis + ", rNvSph=" + rNvSph + ", rNvCyl=" + rNvCyl + ", rNvAxis=" + rNvAxis
				+ ", lNvSph=" + lNvSph + ", lNvCyl=" + lNvCyl + ", lNvAxis=" + lNvAxis + ", rAdd=" + rAdd + ", lAdd="
				+ lAdd + ", companyId=" + companyId + ", storeId=" + storeId + ", customerEmail=" + customerEmail
				+ ", customerGstNo=" + customerGstNo + "]";
	}
	
	

}
