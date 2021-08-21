/**
 * 
 */
package com.sharobi.yewpos.inv.model;

import java.io.Serializable;

/**
 * @author Arunima Roy
 *
 * Nov 9, 2017
 */
public class AreaMaster implements Serializable{

	private int id;
    private String name;
    private int zoneId;
    private int companyId;
    private int storeId;
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
	 * @return the zoneId
	 */
	public int getZoneId() {
		return zoneId;
	}
	/**
	 * @param zoneId the zoneId to set
	 */
	public void setZoneId(int zoneId) {
		this.zoneId = zoneId;
	}
	/**
	 * @return the companyId
	 */
	public int getCompanyId() {
		return companyId;
	}
	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	/**
	 * @return the storeId
	 */
	public int getStoreId() {
		return storeId;
	}
	/**
	 * @param storeId the storeId to set
	 */
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AreaMaster [id=" + id + ", name=" + name + ", zoneId=" + zoneId + ", companyId=" + companyId
				+ ", storeId=" + storeId + "]";
	} 

}
