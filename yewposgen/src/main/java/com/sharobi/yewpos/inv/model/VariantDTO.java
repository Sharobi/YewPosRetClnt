/**
 * 
 */
package com.sharobi.yewpos.inv.model;

import java.io.Serializable;

/**
 * @author Manodip Jana
 *
 * 
 */
public class VariantDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	private int id;
    private String value;
    private int variantTypeId;
    private String variantTypeName;
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}
	/**
	 * @return the variantTypeId
	 */
	public int getVariantTypeId() {
		return variantTypeId;
	}
	/**
	 * @param variantTypeId the variantTypeId to set
	 */
	public void setVariantTypeId(int variantTypeId) {
		this.variantTypeId = variantTypeId;
	}
	/**
	 * @return the variantTypeName
	 */
	public String getVariantTypeName() {
		return variantTypeName;
	}
	/**
	 * @param variantTypeName the variantTypeName to set
	 */
	public void setVariantTypeName(String variantTypeName) {
		this.variantTypeName = variantTypeName;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "VariantDTO [id=" + id + ", value=" + value + ", variantTypeId="
				+ variantTypeId + ", variantTypeName=" + variantTypeName + "]";
	}
    
}
