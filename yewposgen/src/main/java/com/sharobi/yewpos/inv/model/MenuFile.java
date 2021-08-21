package com.sharobi.yewpos.inv.model;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class MenuFile implements Serializable{

	private static final long serialVersionUID = 1L;
	private String fileName;
	private byte[] data;
	private int storeId;
	private String bytes;
	private MultipartFile file;
	public MultipartFile getFile() {
		return file;
	}
	public void setFile(MultipartFile file) {
		this.file = file;
	}
	public String getBytes() {
		return bytes;
	}
	public void setBytes(String bytes) {
		this.bytes = bytes;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public byte[] getData() {
		return data;
	}
	public void setData(byte[] data) {
		this.data = data;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	
}
