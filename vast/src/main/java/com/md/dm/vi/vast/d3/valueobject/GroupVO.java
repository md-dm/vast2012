package com.md.dm.vi.vast.d3.valueobject;

public class GroupVO {
	
	private String bussinesUnit;
	private String facility;
	private int count;
	
	public GroupVO(String bussinesUnit, String facility, int count) {
		super();
		this.bussinesUnit = bussinesUnit;
		this.facility = facility;
		this.count = count;
	}

	public String getBussinesUnit() {
		return bussinesUnit;
	}
	
	public int getCount() {
		return count;
	}

	public String getFacility() {
		return facility;
	}
	
	
	
	

}
