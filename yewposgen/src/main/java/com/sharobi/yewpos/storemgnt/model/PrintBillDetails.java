package com.sharobi.yewpos.storemgnt.model;

public class PrintBillDetails {
	private int id;
	private int printBillId;
	private String billType;
	private String pageUrl;
	private String billHeader;
	private String billFooter;
	private String billText;
	private int companyId;
	private int storeId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getPrintBillId() {
		return printBillId;
	}
	public void setPrintBillId(int printBillId) {
		this.printBillId = printBillId;
	}
	public String getBillType() {
		return billType;
	}
	public void setBillType(String billType) {
		this.billType = billType;
	}
	public String getPageUrl() {
		return pageUrl;
	}
	public void setPageUrl(String pageUrl) {
		this.pageUrl = pageUrl;
	}
	public String getBillHeader() {
		return billHeader;
	}
	public void setBillHeader(String billHeader) {
		this.billHeader = billHeader;
	}
	public String getBillFooter() {
		return billFooter;
	}
	public void setBillFooter(String billFooter) {
		this.billFooter = billFooter;
	}
	public String getBillText() {
		return billText;
	}
	public void setBillText(String billText) {
		this.billText = billText;
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
	@Override
	public String toString() {
		return "PrintBillDetails [id=" + id + ", printBillId=" + printBillId + ", billType=" + billType + ", pageUrl="
				+ pageUrl + ", billHeader=" + billHeader + ", billFooter=" + billFooter + ", billText=" + billText
				+ ", companyId=" + companyId + ", storeId=" + storeId + "]";
	}
	
	
	
}
