package com.sharobi.yewpos.storemgnt.model;

public class PrintBill {
	private int id;
	private String accName;
	private String accNo;
	private String ifscCode;
	private String bankName;
	private String branchName;
	private String electroRefNo;
	private int accDtDisplay;
	private int taxDtDisplay;
	private String tagLine;
	private String printType;
	private String billSize;
	private String noteLine1;
	private String noteLine2;
	private String noteLine3;
	private String noteLine4;
	private String noteLine5;
	private String logoUrl;
	private int companyId;
	private int storeId;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getAccName() {
		return accName;
	}
	public void setAccName(String accName) {
		this.accName = accName;
	}
	public String getAccNo() {
		return accNo;
	}
	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getElectroRefNo() {
		return electroRefNo;
	}
	public void setElectroRefNo(String electroRefNo) {
		this.electroRefNo = electroRefNo;
	}
	public int getAccDtDisplay() {
		return accDtDisplay;
	}
	public void setAccDtDisplay(int accDtDisplay) {
		this.accDtDisplay = accDtDisplay;
	}
	public int getTaxDtDisplay() {
		return taxDtDisplay;
	}
	public void setTaxDtDisplay(int taxDtDisplay) {
		this.taxDtDisplay = taxDtDisplay;
	}
	public String getTagLine() {
		return tagLine;
	}
	public void setTagLine(String tagLine) {
		this.tagLine = tagLine;
	}
	public String getPrintType() {
		return printType;
	}
	public void setPrintType(String printType) {
		this.printType = printType;
	}
	public String getBillSize() {
		return billSize;
	}
	public void setBillSize(String billSize) {
		this.billSize = billSize;
	}
	public String getNoteLine1() {
		return noteLine1;
	}
	public void setNoteLine1(String noteLine1) {
		this.noteLine1 = noteLine1;
	}
	public String getNoteLine2() {
		return noteLine2;
	}
	public void setNoteLine2(String noteLine2) {
		this.noteLine2 = noteLine2;
	}
	public String getNoteLine3() {
		return noteLine3;
	}
	public void setNoteLine3(String noteLine3) {
		this.noteLine3 = noteLine3;
	}
	public String getNoteLine4() {
		return noteLine4;
	}
	public void setNoteLine4(String noteLine4) {
		this.noteLine4 = noteLine4;
	}
	public String getNoteLine5() {
		return noteLine5;
	}
	public void setNoteLine5(String noteLine5) {
		this.noteLine5 = noteLine5;
	}
	public String getLogoUrl() {
		return logoUrl;
	}
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
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
		return "PrintBill [id=" + id + ", accName=" + accName + ", accNo=" + accNo + ", ifscCode=" + ifscCode
				+ ", bankName=" + bankName + ", branchName=" + branchName + ", electroRefNo=" + electroRefNo
				+ ", accDtDisplay=" + accDtDisplay + ", taxDtDisplay=" + taxDtDisplay + ", tagLine=" + tagLine
				+ ", printType=" + printType + ", billSize=" + billSize + ", noteLine1=" + noteLine1 + ", noteLine2="
				+ noteLine2 + ", noteLine3=" + noteLine3 + ", noteLine4=" + noteLine4 + ", noteLine5=" + noteLine5
				+ ", logoUrl=" + logoUrl + ", companyId=" + companyId + ", storeId=" + storeId + "]";
	}
	
	
	
	
}
