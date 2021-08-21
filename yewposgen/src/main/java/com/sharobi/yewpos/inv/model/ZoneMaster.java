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
public class ZoneMaster implements Serializable{
	
	private int id;
    private String name;
    private String districName;
    private int cityId;
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
	 * @return the districName
	 */
	public String getDistricName() {
		return districName;
	}
	/**
	 * @param districName the districName to set
	 */
	public void setDistricName(String districName) {
		this.districName = districName;
	}
	/**
	 * @return the cityId
	 */
	public int getCityId() {
		return cityId;
	}
	/**
	 * @param cityId the cityId to set
	 */
	public void setCityId(int cityId) {
		this.cityId = cityId;
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
		return "ZoneMaster [id=" + id + ", name=" + name + ", districName=" + districName + ", cityId=" + cityId
				+ ", companyId=" + companyId + ", storeId=" + storeId + "]";
	}



}
