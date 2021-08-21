package com.sharobi.yewpos.inv.model;

import java.io.Serializable;
import java.util.Date;

public class StockTransferDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private int transId;
	private String stockTransNo;
	private String stockTransDate;
	private double sendGrossAmount;
	private double recvdGrossAmount;
	private double transitGrossAmount;
	private double sendDiscAmount;
	private double recvdDiscAmount;
	private double transitDiscAmount;
	private double sendTaxAmount;
	private double recvdTaxAmount;
	private double transitTaxAmount;
	private double sendNetAmount;
	private double recvdNetAmount;
	private double transitNetAmount;
	private double sendRoundoff;
	private double recvdRoundoff;
	private double transitRoundoff;
	private double sendTotalMrp;
	private double recvdTotalMrp;
	private double transitTotalMrp;
    private int sendIsPosted;
    private int recvIsPosted;
	private int isDeleted;
	private String dueDate;
	private String sendRemarks;
	private String receiveRemarks;
	private String dispatchStatus;
	private String receiveStatus;
	private String receivedDate;
	private int receivedBy;
	private int fromStoreId;
	private String fromStoreName;
	private int toStoreId;
	
	private String toStoreName;
	public int getTransId() {
		return transId;
	}
	public void setTransId(int transId) {
		this.transId = transId;
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
	public double getSendGrossAmount() {
		return sendGrossAmount;
	}
	public void setSendGrossAmount(double sendGrossAmount) {
		this.sendGrossAmount = sendGrossAmount;
	}
	public double getRecvdGrossAmount() {
		return recvdGrossAmount;
	}
	public void setRecvdGrossAmount(double recvdGrossAmount) {
		this.recvdGrossAmount = recvdGrossAmount;
	}
	public double getTransitGrossAmount() {
		return transitGrossAmount;
	}
	public void setTransitGrossAmount(double transitGrossAmount) {
		this.transitGrossAmount = transitGrossAmount;
	}
	public double getSendDiscAmount() {
		return sendDiscAmount;
	}
	public void setSendDiscAmount(double sendDiscAmount) {
		this.sendDiscAmount = sendDiscAmount;
	}
	public double getRecvdDiscAmount() {
		return recvdDiscAmount;
	}
	public void setRecvdDiscAmount(double recvdDiscAmount) {
		this.recvdDiscAmount = recvdDiscAmount;
	}
	public double getTransitDiscAmount() {
		return transitDiscAmount;
	}
	public void setTransitDiscAmount(double transitDiscAmount) {
		this.transitDiscAmount = transitDiscAmount;
	}
	public double getSendTaxAmount() {
		return sendTaxAmount;
	}
	public void setSendTaxAmount(double sendTaxAmount) {
		this.sendTaxAmount = sendTaxAmount;
	}
	public double getRecvdTaxAmount() {
		return recvdTaxAmount;
	}
	public void setRecvdTaxAmount(double recvdTaxAmount) {
		this.recvdTaxAmount = recvdTaxAmount;
	}
	public double getTransitTaxAmount() {
		return transitTaxAmount;
	}
	public void setTransitTaxAmount(double transitTaxAmount) {
		this.transitTaxAmount = transitTaxAmount;
	}
	public double getSendNetAmount() {
		return sendNetAmount;
	}
	public void setSendNetAmount(double sendNetAmount) {
		this.sendNetAmount = sendNetAmount;
	}
	public double getRecvdNetAmount() {
		return recvdNetAmount;
	}
	public void setRecvdNetAmount(double recvdNetAmount) {
		this.recvdNetAmount = recvdNetAmount;
	}
	public double getTransitNetAmount() {
		return transitNetAmount;
	}
	public void setTransitNetAmount(double transitNetAmount) {
		this.transitNetAmount = transitNetAmount;
	}
	public double getSendRoundoff() {
		return sendRoundoff;
	}
	public void setSendRoundoff(double sendRoundoff) {
		this.sendRoundoff = sendRoundoff;
	}
	public double getRecvdRoundoff() {
		return recvdRoundoff;
	}
	public void setRecvdRoundoff(double recvdRoundoff) {
		this.recvdRoundoff = recvdRoundoff;
	}
	public double getTransitRoundoff() {
		return transitRoundoff;
	}
	public void setTransitRoundoff(double transitRoundoff) {
		this.transitRoundoff = transitRoundoff;
	}
	public double getSendTotalMrp() {
		return sendTotalMrp;
	}
	public void setSendTotalMrp(double sendTotalMrp) {
		this.sendTotalMrp = sendTotalMrp;
	}
	public double getRecvdTotalMrp() {
		return recvdTotalMrp;
	}
	public void setRecvdTotalMrp(double recvdTotalMrp) {
		this.recvdTotalMrp = recvdTotalMrp;
	}
	public double getTransitTotalMrp() {
		return transitTotalMrp;
	}
	public void setTransitTotalMrp(double transitTotalMrp) {
		this.transitTotalMrp = transitTotalMrp;
	}
	public int getSendIsPosted() {
		return sendIsPosted;
	}
	public void setSendIsPosted(int sendIsPosted) {
		this.sendIsPosted = sendIsPosted;
	}
	public int getRecvIsPosted() {
		return recvIsPosted;
	}
	public void setRecvIsPosted(int recvIsPosted) {
		this.recvIsPosted = recvIsPosted;
	}
	public int getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(int isDeleted) {
		this.isDeleted = isDeleted;
	}
	public String getDueDate() {
		return dueDate;
	}
	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}
	public String getSendRemarks() {
		return sendRemarks;
	}
	public void setSendRemarks(String sendRemarks) {
		this.sendRemarks = sendRemarks;
	}
	public String getReceiveRemarks() {
		return receiveRemarks;
	}
	public void setReceiveRemarks(String receiveRemarks) {
		this.receiveRemarks = receiveRemarks;
	}
	public String getDispatchStatus() {
		return dispatchStatus;
	}
	public void setDispatchStatus(String dispatchStatus) {
		this.dispatchStatus = dispatchStatus;
	}
	public String getReceiveStatus() {
		return receiveStatus;
	}
	public void setReceiveStatus(String receiveStatus) {
		this.receiveStatus = receiveStatus;
	}
	public String getReceivedDate() {
		return receivedDate;
	}
	public void setReceivedDate(String receivedDate) {
		this.receivedDate = receivedDate;
	}
	public int getReceivedBy() {
		return receivedBy;
	}
	public void setReceivedBy(int receivedBy) {
		this.receivedBy = receivedBy;
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
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "StockTransferDTO [transId=" + transId + ", stockTransNo=" + stockTransNo + ", stockTransDate="
				+ stockTransDate + ", sendGrossAmount=" + sendGrossAmount + ", recvdGrossAmount=" + recvdGrossAmount
				+ ", transitGrossAmount=" + transitGrossAmount + ", sendDiscAmount=" + sendDiscAmount
				+ ", recvdDiscAmount=" + recvdDiscAmount + ", transitDiscAmount=" + transitDiscAmount
				+ ", sendTaxAmount=" + sendTaxAmount + ", recvdTaxAmount=" + recvdTaxAmount + ", transitTaxAmount="
				+ transitTaxAmount + ", sendNetAmount=" + sendNetAmount + ", recvdNetAmount=" + recvdNetAmount
				+ ", transitNetAmount=" + transitNetAmount + ", sendRoundoff=" + sendRoundoff + ", recvdRoundoff="
				+ recvdRoundoff + ", transitRoundoff=" + transitRoundoff + ", sendTotalMrp=" + sendTotalMrp
				+ ", recvdTotalMrp=" + recvdTotalMrp + ", transitTotalMrp=" + transitTotalMrp + ", sendIsPosted="
				+ sendIsPosted + ", recvIsPosted=" + recvIsPosted + ", isDeleted=" + isDeleted + ", dueDate=" + dueDate
				+ ", sendRemarks=" + sendRemarks + ", receiveRemarks=" + receiveRemarks + ", dispatchStatus="
				+ dispatchStatus + ", receiveStatus=" + receiveStatus + ", receivedDate=" + receivedDate
				+ ", receivedBy=" + receivedBy + ", fromStoreId=" + fromStoreId + ", fromStoreName=" + fromStoreName
				+ ", toStoreId=" + toStoreId + ", toStoreName=" + toStoreName + "]";
	}
	
	

	}