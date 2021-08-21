/**
 * 
 */
package com.sharobi.yewpos.inv.model;

import java.io.Serializable;

/**
 * @author Manodip Jana
 */
public class CustomerType implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private int id;
	private String typeName;
	
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CustomerType [id=" + id + ", typeName=" + typeName + "]";
	}
	
}
