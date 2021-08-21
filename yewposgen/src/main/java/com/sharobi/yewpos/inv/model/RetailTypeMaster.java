/**
 * 
 */
package com.sharobi.yewpos.inv.model;

import java.io.Serializable;

/**
 * @author Manodip Jana
 *
 */
public class RetailTypeMaster implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String typeName;
	private String typeTag;
	
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
	 * @return the typeName
	 */
	public String getTypeName() {
		return typeName;
	}
	/**
	 * @param typeName the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	/**
	 * @return the typeTag
	 */
	public String getTypeTag() {
		return typeTag;
	}
	/**
	 * @param typeTag the typeTag to set
	 */
	public void setTypeTag(String typeTag) {
		this.typeTag = typeTag;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RetailTypeMaster [id=" + id + ", typeName=" + typeName
				+ ", typeTag=" + typeTag + "]";
	}
	
}
