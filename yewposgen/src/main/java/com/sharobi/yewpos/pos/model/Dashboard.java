/**
 *
 */
package com.sharobi.yewpos.pos.model;

/**
 * @author SK A SIDDIK
 *
 *         Jun 25, 2018
 */
public class Dashboard {

	 private int totalCustomer;
	 private int totalProduct;
	 private int totalVendor;
     private double saleTotalAmount;
     private double purchaseTotalAmount;
     private double saleReturnTotalAmount;
     private double purchaseReturnTotalAmount;
     private double valuation;
     private String stockqty;
     private double cashInHandCr;
     private double cashInHandDr;
     private double BankAmtCr;
     private double BankAmtDr;
     private int totalPurchase;
     private int totalSale;
     private int totalPurchaseReturn;
     private int totalSaleReturn;
     private int totalPurchaseInv;
     private int totalPurchaseOrder;
     private double totalSaleCashAmount;
     private double totalSaleCardAmount;
     private double totalSaleCreditAmount;
     private double purchaseOrderTotalAmount;
     private double purchaseInvTotalAmount;
     private double taxAndDutiesCr;
     private double taxAndDutiesDr;
     private double profitAndLoss;

     public Dashboard() {
		// TODO Auto-generated constructor stub
	}
	public int getTotalCustomer() {
		return totalCustomer;
	}
	public void setTotalCustomer(int totalCustomer) {
		this.totalCustomer = totalCustomer;
	}
	public int getTotalProduct() {
		return totalProduct;
	}
	public void setTotalProduct(int totalProduct) {
		this.totalProduct = totalProduct;
	}
	public int getTotalVendor() {
		return totalVendor;
	}
	public void setTotalVendor(int totalVendor) {
		this.totalVendor = totalVendor;
	}
	public double getSaleTotalAmount() {
		return saleTotalAmount;
	}
	public void setSaleTotalAmount(double saleTotalAmount) {
		this.saleTotalAmount = saleTotalAmount;
	}
	public double getPurchaseTotalAmount() {
		return purchaseTotalAmount;
	}
	public void setPurchaseTotalAmount(double purchaseTotalAmount) {
		this.purchaseTotalAmount = purchaseTotalAmount;
	}
	public double getSaleReturnTotalAmount() {
		return saleReturnTotalAmount;
	}
	public void setSaleReturnTotalAmount(double saleReturnTotalAmount) {
		this.saleReturnTotalAmount = saleReturnTotalAmount;
	}
	public double getPurchaseReturnTotalAmount() {
		return purchaseReturnTotalAmount;
	}
	public void setPurchaseReturnTotalAmount(double purchaseReturnTotalAmount) {
		this.purchaseReturnTotalAmount = purchaseReturnTotalAmount;
	}
	public double getValuation() {
		return valuation;
	}
	public void setValuation(double valuation) {
		this.valuation = valuation;
	}
	public String getStockqty() {
		return stockqty;
	}
	public void setStockqty(String stockqty) {
		this.stockqty = stockqty;
	}
	public double getCashInHandCr() {
		return cashInHandCr;
	}
	public void setCashInHandCr(double cashInHandCr) {
		this.cashInHandCr = cashInHandCr;
	}
	public double getCashInHandDr() {
		return cashInHandDr;
	}
	public void setCashInHandDr(double cashInHandDr) {
		this.cashInHandDr = cashInHandDr;
	}
	public double getBankAmtCr() {
		return BankAmtCr;
	}
	public void setBankAmtCr(double bankAmtCr) {
		BankAmtCr = bankAmtCr;
	}
	public double getBankAmtDr() {
		return BankAmtDr;
	}
	public void setBankAmtDr(double bankAmtDr) {
		BankAmtDr = bankAmtDr;
	}


	public int getTotalPurchase() {
		return totalPurchase;
	}
	public void setTotalPurchase(int totalPurchase) {
		this.totalPurchase = totalPurchase;
	}
	public int getTotalSale() {
		return totalSale;
	}
	public void setTotalSale(int totalSale) {
		this.totalSale = totalSale;
	}
	public int getTotalPurchaseReturn() {
		return totalPurchaseReturn;
	}
	public void setTotalPurchaseReturn(int totalPurchaseReturn) {
		this.totalPurchaseReturn = totalPurchaseReturn;
	}
	public int getTotalSaleReturn() {
		return totalSaleReturn;
	}
	public void setTotalSaleReturn(int totalSaleReturn) {
		this.totalSaleReturn = totalSaleReturn;
	}
	public int getTotalPurchaseInv() {
		return totalPurchaseInv;
	}
	public void setTotalPurchaseInv(int totalPurchaseInv) {
		this.totalPurchaseInv = totalPurchaseInv;
	}
	public int getTotalPurchaseOrder() {
		return totalPurchaseOrder;
	}
	public void setTotalPurchaseOrder(int totalPurchaseOrder) {
		this.totalPurchaseOrder = totalPurchaseOrder;
	}


	public double getTotalSaleCashAmount() {
		return totalSaleCashAmount;
	}
	public void setTotalSaleCashAmount(double totalSaleCashAmount) {
		this.totalSaleCashAmount = totalSaleCashAmount;
	}
	public double getTotalSaleCardAmount() {
		return totalSaleCardAmount;
	}
	public void setTotalSaleCardAmount(double totalSaleCardAmount) {
		this.totalSaleCardAmount = totalSaleCardAmount;
	}
	public double getTotalSaleCreditAmount() {
		return totalSaleCreditAmount;
	}
	public void setTotalSaleCreditAmount(double totalSaleCreditAmount) {
		this.totalSaleCreditAmount = totalSaleCreditAmount;
	}
	public double getPurchaseOrderTotalAmount() {
		return purchaseOrderTotalAmount;
	}
	public void setPurchaseOrderTotalAmount(double purchaseOrderTotalAmount) {
		this.purchaseOrderTotalAmount = purchaseOrderTotalAmount;
	}
	public double getPurchaseInvTotalAmount() {
		return purchaseInvTotalAmount;
	}
	public void setPurchaseInvTotalAmount(double purchaseInvTotalAmount) {
		this.purchaseInvTotalAmount = purchaseInvTotalAmount;
	}


	public double getTaxAndDutiesCr() {
		return taxAndDutiesCr;
	}
	public void setTaxAndDutiesCr(double taxAndDutiesCr) {
		this.taxAndDutiesCr = taxAndDutiesCr;
	}
	public double getTaxAndDutiesDr() {
		return taxAndDutiesDr;
	}
	public void setTaxAndDutiesDr(double taxAndDutiesDr) {
		this.taxAndDutiesDr = taxAndDutiesDr;
	}

	public double getProfitAndLoss() {
		return profitAndLoss;
	}
	public void setProfitAndLoss(double profitAndLoss) {
		this.profitAndLoss = profitAndLoss;
	}
	@Override
	public String toString() {
		return "Dashboard [totalCustomer=" + totalCustomer + ", totalProduct=" + totalProduct + ", totalVendor="
				+ totalVendor + ", saleTotalAmount=" + saleTotalAmount + ", purchaseTotalAmount=" + purchaseTotalAmount
				+ ", saleReturnTotalAmount=" + saleReturnTotalAmount + ", purchaseReturnTotalAmount="
				+ purchaseReturnTotalAmount + ", valuation=" + valuation + ", stockqty=" + stockqty + ", cashInHandCr="
				+ cashInHandCr + ", cashInHandDr=" + cashInHandDr + ", BankAmtCr=" + BankAmtCr + ", BankAmtDr="
				+ BankAmtDr + ", totalPurchase=" + totalPurchase + ", totalSale=" + totalSale + ", totalPurchaseReturn="
				+ totalPurchaseReturn + ", totalSaleReturn=" + totalSaleReturn + ", totalPurchaseInv="
				+ totalPurchaseInv + ", totalPurchaseOrder=" + totalPurchaseOrder + ", totalSaleCashAmount="
				+ totalSaleCashAmount + ", totalSaleCardAmount=" + totalSaleCardAmount + ", totalSaleCreditAmount="
				+ totalSaleCreditAmount + ", purchaseOrderTotalAmount=" + purchaseOrderTotalAmount
				+ ", purchaseInvTotalAmount=" + purchaseInvTotalAmount + ", taxAndDutiesCr=" + taxAndDutiesCr
				+ ", taxAndDutiesDr=" + taxAndDutiesDr + ", profitAndLoss=" + profitAndLoss + "]";
	}






}
