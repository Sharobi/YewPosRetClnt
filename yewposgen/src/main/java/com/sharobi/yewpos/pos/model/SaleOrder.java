/**
 * 
 */
package com.sharobi.yewpos.pos.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class SaleOrder implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int id;

    private String invNo;

    private String invDate;

    private String dueDate;

    private String invType;

    private int customerId;
	
	private String customerName;
	private String customerAddress;
	private String customerPhone;

	private double customerDiscPer;

    private double grossAmount;

    private double discAmount;

    private double advAmount;
    
    private double adjAmount;

    private double taxAmount;

    private double roundoff;

    private double netAmount;

    private double totalMrp;

    private String remarks;

    private int isCanceled;

    private int isDeleted;

    private int status;

    private int finyrId;
    
    private String finyrCode;

    private int storeId;

    private int companyId;

    private int createdBy;

    private Date createdDate;

    private int updatedBy;

    private Date updatedDate;
    
    private String lang;
    
    private List<SaleOrderDetails> saleOrderDetails;
    
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
	
	private int doctorId;
	private String doctorName;
	
    
    
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
	 * @return the finyrCode
	 */
	public String getFinyrCode() {
		return finyrCode;
	}

	/**
	 * @param finyrCode the finyrCode to set
	 */
	public void setFinyrCode(String finyrCode) {
		this.finyrCode = finyrCode;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
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
	 * @return the saleOrderDetails
	 */
	public List<SaleOrderDetails> getSaleOrderDetails() {
		return saleOrderDetails;
	}

	/**
	 * @param saleOrderDetails the saleOrderDetails to set
	 */
	public void setSaleOrderDetails(List<SaleOrderDetails> saleOrderDetails) {
		this.saleOrderDetails = saleOrderDetails;
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
	 * @return the isCanceled
	 */
	public int getIsCanceled() {
		return isCanceled;
	}

	/**
	 * @param isCanceled the isCanceled to set
	 */
	public void setIsCanceled(int isCanceled) {
		this.isCanceled = isCanceled;
	}

	/**
	 * @return the isDeleted
	 */
	public int getIsDeleted() {
		return isDeleted;
	}

	/**
	 * @param isDeleted the isDeleted to set
	 */
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
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
	 * @return the finyrId
	 */
	public int getFinyrId() {
		return finyrId;
	}

	/**
	 * @param finyrId the finyrId to set
	 */
	public void setFinyrId(int finyrId) {
		this.finyrId = finyrId;
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
	 * @return the createdBy
	 */
	public int getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(int createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the createdDate
	 */
	public Date getCreatedDate() {
		return createdDate;
	}

	/**
	 * @param createdDate the createdDate to set
	 */
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * @return the updatedBy
	 */
	public int getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(int updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * @return the updatedDate
	 */
	public Date getUpdatedDate() {
		return updatedDate;
	}

	/**
	 * @param updatedDate the updatedDate to set
	 */
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * @return the lang
	 */
	public String getLang() {
		return lang;
	}

	/**
	 * @param lang the lang to set
	 */
	public void setLang(String lang) {
		this.lang = lang;
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

	@Override
	public String toString() {
		return "SaleOrder [id=" + id + ", invNo=" + invNo + ", invDate=" + invDate + ", dueDate=" + dueDate
				+ ", invType=" + invType + ", customerId=" + customerId + ", customerName=" + customerName
				+ ", customerAddress=" + customerAddress + ", customerPhone=" + customerPhone + ", customerDiscPer="
				+ customerDiscPer + ", grossAmount=" + grossAmount + ", discAmount=" + discAmount + ", advAmount="
				+ advAmount + ", adjAmount=" + adjAmount + ", taxAmount=" + taxAmount + ", roundoff=" + roundoff
				+ ", netAmount=" + netAmount + ", totalMrp=" + totalMrp + ", remarks=" + remarks + ", isCanceled="
				+ isCanceled + ", isDeleted=" + isDeleted + ", status=" + status + ", finyrId=" + finyrId
				+ ", finyrCode=" + finyrCode + ", storeId=" + storeId + ", companyId=" + companyId + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate="
				+ updatedDate + ", lang=" + lang + ", saleOrderDetails=" + saleOrderDetails + ", isPosted=" + isPosted
				+ ", rDvSph=" + rDvSph + ", rDvCyl=" + rDvCyl + ", rDvAxis=" + rDvAxis + ", lDvSph=" + lDvSph
				+ ", lDvCyl=" + lDvCyl + ", lDvAxis=" + lDvAxis + ", rNvSph=" + rNvSph + ", rNvCyl=" + rNvCyl
				+ ", rNvAxis=" + rNvAxis + ", lNvSph=" + lNvSph + ", lNvCyl=" + lNvCyl + ", lNvAxis=" + lNvAxis
				+ ", rAdd=" + rAdd + ", lAdd=" + lAdd + ", doctorId=" + doctorId + ", doctorName=" + doctorName + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	

}
