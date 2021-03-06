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
public class CountryMaster implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String shortname;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "CountryMaster [id=" + id + ", shortname=" + shortname
				+ ", name=" + name + "]";
	}

}
