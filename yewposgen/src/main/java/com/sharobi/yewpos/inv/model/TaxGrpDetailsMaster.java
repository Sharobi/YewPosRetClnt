/**
 * 
 */
package com.sharobi.yewpos.inv.model;

import java.io.Serializable;

/**
 * @author habib
 *
 */
public class TaxGrpDetailsMaster implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
    private int parentId;
    private double percentage;
    private String description;
    private int taxId;
    private String taxMode;
    private String taxName;
    
	public String getTaxName() {
		return taxName;
	}
	public void setTaxName(String taxName) {
		this.taxName = taxName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getParentId() {
		return parentId;
	}
	public void setParentId(int parentId) {
		this.parentId = parentId;
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
	public int getTaxId() {
		return taxId;
	}
	public void setTaxId(int taxId) {
		this.taxId = taxId;
	}
	public String getTaxMode() {
		return taxMode;
	}
	public void setTaxMode(String taxMode) {
		this.taxMode = taxMode;
	}
	@Override
	public String toString() {
		return "TaxGrpDetailsMaster [id=" + id + ", parentId=" + parentId + ", percentage=" + percentage
				+ ", description=" + description + ", taxId=" + taxId + ", taxMode=" + taxMode + ", taxName=" + taxName
				+ "]";
	}
	
    
    

}
