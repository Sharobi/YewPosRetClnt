package com.sharobi.yewpos.inv.model;

import java.io.Serializable;

public class ReturnReasonTypeMaster implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
    private String typeName;
    private String typeTag;
    private String remarks;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeTag() {
		return typeTag;
	}
	public void setTypeTag(String typeTag) {
		this.typeTag = typeTag;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	@Override
	public String toString() {
		return "ReturnReasonTypeMaster [id=" + id + ", typeName=" + typeName + ", typeTag=" + typeTag + ", remarks="
				+ remarks + "]";
	}

}
