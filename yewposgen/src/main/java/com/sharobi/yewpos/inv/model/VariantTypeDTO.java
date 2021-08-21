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
public class VariantTypeDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private int variantTypeId;
    private String variantTypeName;
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
		return "VariantTypeDTO [variantTypeId=" + variantTypeId
				+ ", variantTypeName=" + variantTypeName + "]";
	}
    
    
}
