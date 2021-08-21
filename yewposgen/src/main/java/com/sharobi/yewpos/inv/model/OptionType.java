/**
 * 
 */
package com.sharobi.yewpos.inv.model;

/**
 * @author Manodip Jana
 *
 * Nov 6, 2017
 */
public class OptionType {
 private int optionid;
private String optionname;
/**
 * 
 */
public OptionType() {
	// TODO Auto-generated constructor stub
}
/**
 * @return the optionid
 */
public int getOptionid() {
	return optionid;
}
/**
 * @param optionid the optionid to set
 */
public void setOptionid(int optionid) {
	this.optionid = optionid;
}
/**
 * @return the optionname
 */
public String getOptionname() {
	return optionname;
}
/**
 * @param optionname the optionname to set
 */
public void setOptionname(String optionname) {
	this.optionname = optionname;
}
/**
 * @param optionid
 * @param optionname
 */
public OptionType(int optionid, String optionname) {
	super();
	this.optionid = optionid;
	this.optionname = optionname;
}
 

}
