/**
 * 
 */
package com.sharobi.yewpos.inv.model;

/**
 * @author SIDDIK
 *
 * Nov 3, 2017
 */
public class TaxTypeDTO {
	private int taxTypeId;
	private String taxTypeName;
	
	/**
	 * 
	 */
	public TaxTypeDTO() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @return the taxTypeId
	 */
	public int getTaxTypeId() {
		return taxTypeId;
	}

	/**
	 * @param taxTypeId the taxTypeId to set
	 */
	public void setTaxTypeId(int taxTypeId) {
		this.taxTypeId = taxTypeId;
	}

	/**
	 * @return the taxTypeName
	 */
	public String getTaxTypeName() {
		return taxTypeName;
	}

	/**
	 * @param taxTypeName the taxTypeName to set
	 */
	public void setTaxTypeName(String taxTypeName) {
		this.taxTypeName = taxTypeName;
	}

	/**
	 * @param taxTypeId
	 * @param taxTypeName
	 */
	public TaxTypeDTO(int taxTypeId, String taxTypeName) {
		super();
		this.taxTypeId = taxTypeId;
		this.taxTypeName = taxTypeName;
	}
	
	
	
	
}
