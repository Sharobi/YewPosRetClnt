package com.sharobi.yewpos.proc.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class PurchaseInvHeaderDTO implements Serializable{
		
		private static final long serialVersionUID = 1L;
		
		
		private int id;

		private String invNo;

		private String invDate;

		private int poOrderId;

		private int purchaseReturnId;

		private int invMode;

		private int distributorId;

		private double grossAmount;

		private double edAmount;

		private double discAmount;

		private double taxAmount;

		private double vatAmount;

		private double adjAmount;

		private double netAmount;

		private double lotAdjAmount;

		private double roundoff;

		private double cst;

		private double vatDiff;

		private String remarks;

		private int isPosted; // changed

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

		private String qryCondition;

		private String invModeName;

		private String distName;

		private double totalMrp;

		private String finyrCode;

		private String billNo;

		private double specDiscPer;

		private double specDiscAmount;

		private double distDiscPer;

		private List<PurchaseDetails> purchaseDetails;

		private int paymentId;

		private double advAmount;

		private double distributorBillAmount;

		private double otherAdjAmount;

		List<PurchaseReturn> purchaseReturns;

		private double expiryAdjAmount;

		private List<ExpiryPurchaseMapping> expiryReturns;
		private String dueDate;

		private String purchaseOrderInvNo;

		private String billDate;

		/*
		 * for new add
		 */
		private int duties_ledger_id;
		private int round_ledger_id;
		private int purchase_ledger_id;
		private int discount_ledger_id;
		private int credior_ledger_id;
		private int lotadjas_ledger_id;
		private String qs;
		private int is_account;
		
		private String purchaseIDList;
		private int isDirect;
		
		private int purchaseId;
		
		private String distributorName;
		
		private int purchaseInvId;
		
		private double other_adj_amount;
		
		private int isFromInv;
		
		private int isInvMade;
		
		
		
		//new required fields
		private int purchaseOrderId;

		private String invType;

		private double distributorDiscPer;

		private int status;
		

		public PurchaseInvHeaderDTO() {
			// TODO Auto-generated constructor stub
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



		public int getPoOrderId() {
			return poOrderId;
		}



		public void setPoOrderId(int poOrderId) {
			this.poOrderId = poOrderId;
		}



		public int getPurchaseReturnId() {
			return purchaseReturnId;
		}



		public void setPurchaseReturnId(int purchaseReturnId) {
			this.purchaseReturnId = purchaseReturnId;
		}



		public int getInvMode() {
			return invMode;
		}



		public void setInvMode(int invMode) {
			this.invMode = invMode;
		}



		public int getDistributorId() {
			return distributorId;
		}



		public void setDistributorId(int distributorId) {
			this.distributorId = distributorId;
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



		public double getLotAdjAmount() {
			return lotAdjAmount;
		}



		public void setLotAdjAmount(double lotAdjAmount) {
			this.lotAdjAmount = lotAdjAmount;
		}



		public double getRoundoff() {
			return roundoff;
		}



		public void setRoundoff(double roundoff) {
			this.roundoff = roundoff;
		}



		public double getCst() {
			return cst;
		}



		public void setCst(double cst) {
			this.cst = cst;
		}



		public double getVatDiff() {
			return vatDiff;
		}



		public void setVatDiff(double vatDiff) {
			this.vatDiff = vatDiff;
		}



		public String getRemarks() {
			return remarks;
		}



		public void setRemarks(String remarks) {
			this.remarks = remarks;
		}



		public int getIsPosted() {
			return isPosted;
		}



		public void setIsPosted(int isPosted) {
			this.isPosted = isPosted;
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



		public String getQryCondition() {
			return qryCondition;
		}



		public void setQryCondition(String qryCondition) {
			this.qryCondition = qryCondition;
		}



		public String getInvModeName() {
			return invModeName;
		}



		public void setInvModeName(String invModeName) {
			this.invModeName = invModeName;
		}



		public String getDistName() {
			return distName;
		}



		public void setDistName(String distName) {
			this.distName = distName;
		}



		public double getTotalMrp() {
			return totalMrp;
		}



		public void setTotalMrp(double totalMrp) {
			this.totalMrp = totalMrp;
		}



		public String getFinyrCode() {
			return finyrCode;
		}



		public void setFinyrCode(String finyrCode) {
			this.finyrCode = finyrCode;
		}



		public String getBillNo() {
			return billNo;
		}



		public void setBillNo(String billNo) {
			this.billNo = billNo;
		}



		public double getSpecDiscPer() {
			return specDiscPer;
		}



		public void setSpecDiscPer(double specDiscPer) {
			this.specDiscPer = specDiscPer;
		}



		public double getSpecDiscAmount() {
			return specDiscAmount;
		}



		public void setSpecDiscAmount(double specDiscAmount) {
			this.specDiscAmount = specDiscAmount;
		}



		public double getDistDiscPer() {
			return distDiscPer;
		}



		public void setDistDiscPer(double distDiscPer) {
			this.distDiscPer = distDiscPer;
		}



		public List<PurchaseDetails> getPurchaseDetails() {
			return purchaseDetails;
		}



		public void setPurchaseDetails(List<PurchaseDetails> purchaseDetails) {
			this.purchaseDetails = purchaseDetails;
		}



		public int getPaymentId() {
			return paymentId;
		}



		public void setPaymentId(int paymentId) {
			this.paymentId = paymentId;
		}



		public double getAdvAmount() {
			return advAmount;
		}



		public void setAdvAmount(double advAmount) {
			this.advAmount = advAmount;
		}



		public double getDistributorBillAmount() {
			return distributorBillAmount;
		}



		public void setDistributorBillAmount(double distributorBillAmount) {
			this.distributorBillAmount = distributorBillAmount;
		}



		public double getOtherAdjAmount() {
			return otherAdjAmount;
		}



		public void setOtherAdjAmount(double otherAdjAmount) {
			this.otherAdjAmount = otherAdjAmount;
		}



		public List<PurchaseReturn> getPurchaseReturns() {
			return purchaseReturns;
		}



		public void setPurchaseReturns(List<PurchaseReturn> purchaseReturns) {
			this.purchaseReturns = purchaseReturns;
		}



		public double getExpiryAdjAmount() {
			return expiryAdjAmount;
		}



		public void setExpiryAdjAmount(double expiryAdjAmount) {
			this.expiryAdjAmount = expiryAdjAmount;
		}



		public List<ExpiryPurchaseMapping> getExpiryReturns() {
			return expiryReturns;
		}



		public void setExpiryReturns(List<ExpiryPurchaseMapping> expiryReturns) {
			this.expiryReturns = expiryReturns;
		}



		public String getDueDate() {
			return dueDate;
		}



		public void setDueDate(String dueDate) {
			this.dueDate = dueDate;
		}



		public String getPurchaseOrderInvNo() {
			return purchaseOrderInvNo;
		}



		public void setPurchaseOrderInvNo(String purchaseOrderInvNo) {
			this.purchaseOrderInvNo = purchaseOrderInvNo;
		}



		public String getBillDate() {
			return billDate;
		}



		public void setBillDate(String billDate) {
			this.billDate = billDate;
		}



		public int getDuties_ledger_id() {
			return duties_ledger_id;
		}



		public void setDuties_ledger_id(int duties_ledger_id) {
			this.duties_ledger_id = duties_ledger_id;
		}



		public int getRound_ledger_id() {
			return round_ledger_id;
		}



		public void setRound_ledger_id(int round_ledger_id) {
			this.round_ledger_id = round_ledger_id;
		}



		public int getPurchase_ledger_id() {
			return purchase_ledger_id;
		}



		public void setPurchase_ledger_id(int purchase_ledger_id) {
			this.purchase_ledger_id = purchase_ledger_id;
		}



		public int getDiscount_ledger_id() {
			return discount_ledger_id;
		}



		public void setDiscount_ledger_id(int discount_ledger_id) {
			this.discount_ledger_id = discount_ledger_id;
		}



		public int getCredior_ledger_id() {
			return credior_ledger_id;
		}



		public void setCredior_ledger_id(int credior_ledger_id) {
			this.credior_ledger_id = credior_ledger_id;
		}



		public int getLotadjas_ledger_id() {
			return lotadjas_ledger_id;
		}



		public void setLotadjas_ledger_id(int lotadjas_ledger_id) {
			this.lotadjas_ledger_id = lotadjas_ledger_id;
		}



		public String getQs() {
			return qs;
		}



		public void setQs(String qs) {
			this.qs = qs;
		}



		public int getIs_account() {
			return is_account;
		}



		public void setIs_account(int is_account) {
			this.is_account = is_account;
		}



		public String getPurchaseIDList() {
			return purchaseIDList;
		}



		public void setPurchaseIDList(String purchaseIDList) {
			this.purchaseIDList = purchaseIDList;
		}



		public int getIsDirect() {
			return isDirect;
		}



		public void setIsDirect(int isDirect) {
			this.isDirect = isDirect;
		}



		public int getPurchaseId() {
			return purchaseId;
		}



		public void setPurchaseId(int purchaseId) {
			this.purchaseId = purchaseId;
		}



		public String getDistributorName() {
			return distributorName;
		}



		public void setDistributorName(String distributorName) {
			this.distributorName = distributorName;
		}



		public int getPurchaseInvId() {
			return purchaseInvId;
		}



		public void setPurchaseInvId(int purchaseInvId) {
			this.purchaseInvId = purchaseInvId;
		}



		public double getOther_adj_amount() {
			return other_adj_amount;
		}



		public void setOther_adj_amount(double other_adj_amount) {
			this.other_adj_amount = other_adj_amount;
		}



		public int getIsFromInv() {
			return isFromInv;
		}



		public void setIsFromInv(int isFromInv) {
			this.isFromInv = isFromInv;
		}



		public int getIsInvMade() {
			return isInvMade;
		}



		public void setIsInvMade(int isInvMade) {
			this.isInvMade = isInvMade;
		}



		public int getPurchaseOrderId() {
			return purchaseOrderId;
		}



		public void setPurchaseOrderId(int purchaseOrderId) {
			this.purchaseOrderId = purchaseOrderId;
		}



		public String getInvType() {
			return invType;
		}



		public void setInvType(String invType) {
			this.invType = invType;
		}



		public double getDistributorDiscPer() {
			return distributorDiscPer;
		}



		public void setDistributorDiscPer(double distributorDiscPer) {
			this.distributorDiscPer = distributorDiscPer;
		}



		public int getStatus() {
			return status;
		}



		public void setStatus(int status) {
			this.status = status;
		}



		@Override
		public String toString() {
			return "PurchaseInvHeaderDTO [id=" + id + ", invNo=" + invNo + ", invDate=" + invDate + ", poOrderId="
					+ poOrderId + ", purchaseReturnId=" + purchaseReturnId + ", invMode=" + invMode + ", distributorId="
					+ distributorId + ", grossAmount=" + grossAmount + ", edAmount=" + edAmount + ", discAmount="
					+ discAmount + ", taxAmount=" + taxAmount + ", vatAmount=" + vatAmount + ", adjAmount=" + adjAmount
					+ ", netAmount=" + netAmount + ", lotAdjAmount=" + lotAdjAmount + ", roundoff=" + roundoff
					+ ", cst=" + cst + ", vatDiff=" + vatDiff + ", remarks=" + remarks + ", isPosted=" + isPosted
					+ ", pstId=" + pstId + ", pstNo=" + pstNo + ", finyrId=" + finyrId + ", storeId=" + storeId
					+ ", companyId=" + companyId + ", isDeleted=" + isDeleted + ", createdBy=" + createdBy
					+ ", createdDate=" + createdDate + ", updatedBy=" + updatedBy + ", updatedDate=" + updatedDate
					+ ", lang=" + lang + ", qryCondition=" + qryCondition + ", invModeName=" + invModeName
					+ ", distName=" + distName + ", totalMrp=" + totalMrp + ", finyrCode=" + finyrCode + ", billNo="
					+ billNo + ", specDiscPer=" + specDiscPer + ", specDiscAmount=" + specDiscAmount + ", distDiscPer="
					+ distDiscPer + ", purchaseDetails=" + purchaseDetails + ", paymentId=" + paymentId + ", advAmount="
					+ advAmount + ", distributorBillAmount=" + distributorBillAmount + ", otherAdjAmount="
					+ otherAdjAmount + ", purchaseReturns=" + purchaseReturns + ", expiryAdjAmount=" + expiryAdjAmount
					+ ", expiryReturns=" + expiryReturns + ", dueDate=" + dueDate + ", purchaseOrderInvNo="
					+ purchaseOrderInvNo + ", billDate=" + billDate + ", duties_ledger_id=" + duties_ledger_id
					+ ", round_ledger_id=" + round_ledger_id + ", purchase_ledger_id=" + purchase_ledger_id
					+ ", discount_ledger_id=" + discount_ledger_id + ", credior_ledger_id=" + credior_ledger_id
					+ ", lotadjas_ledger_id=" + lotadjas_ledger_id + ", qs=" + qs + ", is_account=" + is_account
					+ ", purchaseIDList=" + purchaseIDList + ", isDirect=" + isDirect + ", purchaseId=" + purchaseId
					+ ", distributorName=" + distributorName + ", purchaseInvId=" + purchaseInvId
					+ ", other_adj_amount=" + other_adj_amount + ", isFromInv=" + isFromInv + ", isInvMade=" + isInvMade
					+ ", purchaseOrderId=" + purchaseOrderId + ", invType=" + invType + ", distributorDiscPer="
					+ distributorDiscPer + ", status=" + status + "]";
		}


	}
