/**
 * 
 */
package com.sharobi.yewpos.inv.model;

import java.io.Serializable;

/**
 * @author Manodip Jana
 *
 * 12-Dec-2017
 */
public class ControlAccessItemWiseDTO implements Serializable{

	private static final long serialVersionUID = 1L;
    private int stockRequired;
    private int batchWiseStock;
    private int expiryDateRequired;
    private int expiryMonthRequired;
    private int dualStockRequired;
    private int locationRequired;
    private int priceListRequired;
    private int sizeWiseStockRequired;
    private int colourWiseStockRequired;
    private int warrantyRequired;
    private int warrantyMonth;
    private int mrpRequired;
    private int serialNoRequired;
    private int serialNoSuffRequired;
    private int serialNoPrefRequired;
    private int rateOn;
    private String serialNoType;
	/**
	 * @return the stockRequired
	 */
	public int getStockRequired() {
		return stockRequired;
	}
	/**
	 * @param stockRequired the stockRequired to set
	 */
	public void setStockRequired(int stockRequired) {
		this.stockRequired = stockRequired;
	}
	/**
	 * @return the batchWiseStock
	 */
	public int getBatchWiseStock() {
		return batchWiseStock;
	}
	/**
	 * @param batchWiseStock the batchWiseStock to set
	 */
	public void setBatchWiseStock(int batchWiseStock) {
		this.batchWiseStock = batchWiseStock;
	}
	/**
	 * @return the expiryDateRequired
	 */
	public int getExpiryDateRequired() {
		return expiryDateRequired;
	}
	/**
	 * @param expiryDateRequired the expiryDateRequired to set
	 */
	public void setExpiryDateRequired(int expiryDateRequired) {
		this.expiryDateRequired = expiryDateRequired;
	}
	/**
	 * @return the expiryMonthRequired
	 */
	public int getExpiryMonthRequired() {
		return expiryMonthRequired;
	}
	/**
	 * @param expiryMonthRequired the expiryMonthRequired to set
	 */
	public void setExpiryMonthRequired(int expiryMonthRequired) {
		this.expiryMonthRequired = expiryMonthRequired;
	}
	/**
	 * @return the dualStockRequired
	 */
	public int getDualStockRequired() {
		return dualStockRequired;
	}
	/**
	 * @param dualStockRequired the dualStockRequired to set
	 */
	public void setDualStockRequired(int dualStockRequired) {
		this.dualStockRequired = dualStockRequired;
	}
	/**
	 * @return the locationRequired
	 */
	public int getLocationRequired() {
		return locationRequired;
	}
	/**
	 * @param locationRequired the locationRequired to set
	 */
	public void setLocationRequired(int locationRequired) {
		this.locationRequired = locationRequired;
	}
	/**
	 * @return the priceListRequired
	 */
	public int getPriceListRequired() {
		return priceListRequired;
	}
	/**
	 * @param priceListRequired the priceListRequired to set
	 */
	public void setPriceListRequired(int priceListRequired) {
		this.priceListRequired = priceListRequired;
	}
	/**
	 * @return the sizeWiseStockRequired
	 */
	public int getSizeWiseStockRequired() {
		return sizeWiseStockRequired;
	}
	/**
	 * @param sizeWiseStockRequired the sizeWiseStockRequired to set
	 */
	public void setSizeWiseStockRequired(int sizeWiseStockRequired) {
		this.sizeWiseStockRequired = sizeWiseStockRequired;
	}
	/**
	 * @return the colourWiseStockRequired
	 */
	public int getColourWiseStockRequired() {
		return colourWiseStockRequired;
	}
	/**
	 * @param colourWiseStockRequired the colourWiseStockRequired to set
	 */
	public void setColourWiseStockRequired(int colourWiseStockRequired) {
		this.colourWiseStockRequired = colourWiseStockRequired;
	}
	/**
	 * @return the warrantyRequired
	 */
	public int getWarrantyRequired() {
		return warrantyRequired;
	}
	/**
	 * @param warrantyRequired the warrantyRequired to set
	 */
	public void setWarrantyRequired(int warrantyRequired) {
		this.warrantyRequired = warrantyRequired;
	}
	/**
	 * @return the warrantyMonth
	 */
	public int getWarrantyMonth() {
		return warrantyMonth;
	}
	/**
	 * @param warrantyMonth the warrantyMonth to set
	 */
	public void setWarrantyMonth(int warrantyMonth) {
		this.warrantyMonth = warrantyMonth;
	}
	/**
	 * @return the mrpRequired
	 */
	public int getMrpRequired() {
		return mrpRequired;
	}
	/**
	 * @param mrpRequired the mrpRequired to set
	 */
	public void setMrpRequired(int mrpRequired) {
		this.mrpRequired = mrpRequired;
	}
	/**
	 * @return the serialNoRequired
	 */
	public int getSerialNoRequired() {
		return serialNoRequired;
	}
	/**
	 * @param serialNoRequired the serialNoRequired to set
	 */
	public void setSerialNoRequired(int serialNoRequired) {
		this.serialNoRequired = serialNoRequired;
	}
	/**
	 * @return the serialNoSuffRequired
	 */
	public int getSerialNoSuffRequired() {
		return serialNoSuffRequired;
	}
	/**
	 * @param serialNoSuffRequired the serialNoSuffRequired to set
	 */
	public void setSerialNoSuffRequired(int serialNoSuffRequired) {
		this.serialNoSuffRequired = serialNoSuffRequired;
	}
	/**
	 * @return the serialNoPrefRequired
	 */
	public int getSerialNoPrefRequired() {
		return serialNoPrefRequired;
	}
	/**
	 * @param serialNoPrefRequired the serialNoPrefRequired to set
	 */
	public void setSerialNoPrefRequired(int serialNoPrefRequired) {
		this.serialNoPrefRequired = serialNoPrefRequired;
	}
	/**
	 * @return the rateOn
	 */
	public int getRateOn() {
		return rateOn;
	}
	/**
	 * @param rateOn the rateOn to set
	 */
	public void setRateOn(int rateOn) {
		this.rateOn = rateOn;
	}
	
	/**
	 * @return the serialNoType
	 */
	public String getSerialNoType() {
		return serialNoType;
	}
	/**
	 * @param serialNoType the serialNoType to set
	 */
	public void setSerialNoType(String serialNoType) {
		this.serialNoType = serialNoType;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ControlAccessItemWiseDTO [stockRequired=" + stockRequired
				+ ", batchWiseStock=" + batchWiseStock
				+ ", expiryDateRequired=" + expiryDateRequired
				+ ", expiryMonthRequired=" + expiryMonthRequired
				+ ", dualStockRequired=" + dualStockRequired
				+ ", locationRequired=" + locationRequired
				+ ", priceListRequired=" + priceListRequired
				+ ", sizeWiseStockRequired=" + sizeWiseStockRequired
				+ ", colourWiseStockRequired=" + colourWiseStockRequired
				+ ", warrantyRequired=" + warrantyRequired + ", warrantyMonth="
				+ warrantyMonth + ", mrpRequired=" + mrpRequired
				+ ", serialNoRequired=" + serialNoRequired
				+ ", serialNoSuffRequired=" + serialNoSuffRequired
				+ ", serialNoPrefRequired=" + serialNoPrefRequired
				+ ", rateOn=" + rateOn + ", serialNoType=" + serialNoType + "]";
	}
    
}
