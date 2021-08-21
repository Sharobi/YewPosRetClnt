/**
 * 
 */
package com.sharobi.yewpos.inv.model;

import java.io.Serializable;

/**
 * @author Manodip Jana
 *
 * 14-Dec-2017
 */
public class LocationDTO implements Serializable{
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private int isDefault;
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
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the isDefault
	 */
	public int getIsDefault() {
		return isDefault;
	}
	/**
	 * @param isDefault the isDefault to set
	 */
	public void setIsDefault(int isDefault) {
		this.isDefault = isDefault;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LocationDTO [id=" + id + ", name=" + name + ", isDefault="
				+ isDefault + "]";
	}
	
}
