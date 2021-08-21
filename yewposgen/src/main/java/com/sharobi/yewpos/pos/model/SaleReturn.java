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
public class SaleReturn implements Serializable {

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
    private double netAmount;
    private double roundoff;
    private String remarks;
    private int pstId;
    private String pstNo;
    private int finyrId;
    private String finyrCode;
    private int storeId;
    private int companyId;
    private int isDeleted;
    private int isposted;
    private int createdBy;
    private Date createdDate;
    private int updatedBy;
    private Date updatedDate;
    private String lang;
    private List<SaleReturnDetails> saleReturnDetails;
    private double adjAmount;
	private double specialDiscPer;
	private double specialDiscAmount;

	private int duties_ledger_id;
	private int round_ledger_id;
	private int sales_ledger_id;
	private int debitor_ledger_id;
    private int lotAdjAmountId;
	private String qs;

	private double sale_account_credit_amt;
	private double debitor_credit_amt;
	private int is_account;

    private int itemdualstock;
	private double lotAdjAmount;




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
	 * @return the sale_account_credit_amt
	 */
	public double getSale_account_credit_amt() {
		return sale_account_credit_amt;
	}
	/**
	 * @param sale_account_credit_amt the sale_account_credit_amt to set
	 */
	public void setSale_account_credit_amt(double sale_account_credit_amt) {
		this.sale_account_credit_amt = sale_account_credit_amt;
	}
	/**
	 * @return the debitor_credit_amt
	 */
	public double getDebitor_credit_amt() {
		return debitor_credit_amt;
	}
	/**
	 * @param debitor_credit_amt the debitor_credit_amt to set
	 */
	public void setDebitor_credit_amt(double debitor_credit_amt) {
		this.debitor_credit_amt = debitor_credit_amt;
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
	 * @return the sales_ledger_id
	 */
	public int getSales_ledger_id() {
		return sales_ledger_id;
	}
	/**
	 * @param sales_ledger_id the sales_ledger_id to set
	 */
	public void setSales_ledger_id(int sales_ledger_id) {
		this.sales_ledger_id = sales_ledger_id;
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

	public String getFinyrCode() {
		return finyrCode;
	}
	public void setFinyrCode(String finyrCode) {
		this.finyrCode = finyrCode;
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
	public int getIsposted() {
		return isposted;
	}
	public void setIsposted(int isposted) {
		this.isposted = isposted;
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
	public List<SaleReturnDetails> getSaleReturnDetails() {
		return saleReturnDetails;
	}
	public void setSaleReturnDetails(List<SaleReturnDetails> saleReturnDetails) {
		this.saleReturnDetails = saleReturnDetails;
	}
	public double getAdjAmount() {
		return adjAmount;
	}
	public void setAdjAmount(double adjAmount) {
		this.adjAmount = adjAmount;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SaleReturn [id=" + id + ", invNo=" + invNo + ", invDate=" + invDate + ", invTime=" + invTime
				+ ", customerId=" + customerId + ", customerName=" + customerName + ", customerAddress="
				+ customerAddress + ", customerPhone=" + customerPhone + ", doctorId=" + doctorId + ", doctorName="
				+ doctorName + ", invMode=" + invMode + ", grossAmount=" + grossAmount + ", edAmount=" + edAmount
				+ ", discAmount=" + discAmount + ", taxAmount=" + taxAmount + ", vatAmount=" + vatAmount
				+ ", netAmount=" + netAmount + ", roundoff=" + roundoff + ", remarks=" + remarks + ", pstId=" + pstId
				+ ", pstNo=" + pstNo + ", finyrId=" + finyrId + ", finyrCode=" + finyrCode + ", storeId=" + storeId
				+ ", companyId=" + companyId + ", isDeleted=" + isDeleted + ", isposted=" + isposted + ", createdBy="
				+ createdBy + ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate="
				+ updatedDate + ", lang=" + lang + ", saleReturnDetails=" + saleReturnDetails + ", adjAmount="
				+ adjAmount + ", specialDiscPer=" + specialDiscPer + ", specialDiscAmount=" + specialDiscAmount
				+ ", duties_ledger_id=" + duties_ledger_id + ", round_ledger_id=" + round_ledger_id
				+ ", sales_ledger_id=" + sales_ledger_id + ", debitor_ledger_id=" + debitor_ledger_id
				+ ", lotAdjAmountId=" + lotAdjAmountId + ", qs=" + qs + ", sale_account_credit_amt="
				+ sale_account_credit_amt + ", debitor_credit_amt=" + debitor_credit_amt + ", is_account=" + is_account
				+ ", itemdualstock=" + itemdualstock + ", lotAdjAmount=" + lotAdjAmount + "]";
	}



}