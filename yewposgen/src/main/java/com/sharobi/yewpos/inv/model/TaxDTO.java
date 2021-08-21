/**
 * 
 */
package com.sharobi.yewpos.inv.model;

/**
 * @author habib
 *
 */
public class TaxDTO {
	
	private int taxId;
    private String taxName;
    private double percentage;
    private String description;
    private int isGroup;
    private String taxMode;
    private String taxLabel;
	public int getTaxId() {
		return taxId;
	}
	public void setTaxId(int taxId) {
		this.taxId = taxId;
	}
	public String getTaxName() {
		return taxName;
	}
	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}
	public double getPercentage() {
		return percentage;
	}
	public void setPercentage(double percentage) {
		this.percentage = percentage;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getIsGroup() {
		return isGroup;
	}
	public void setIsGroup(int isGroup) {
		this.isGroup = isGroup;
	}
	public String getTaxMode() {
		return taxMode;
	}
	public void setTaxMode(String taxMode) {
		this.taxMode = taxMode;
	}
	public String getTaxLabel() {
		return taxLabel;
	}
	public void setTaxLabel(String taxLabel) {
		this.taxLabel = taxLabel;
	}
	@Override
	public String toString() {
		return "TaxDTO [taxId=" + taxId + ", taxName=" + taxName
				+ ", percentage=" + percentage + ", description=" + description
				+ ", isGroup=" + isGroup + ", taxMode=" + taxMode
				+ ", taxLabel=" + taxLabel + "]";
	}
    
    

}
