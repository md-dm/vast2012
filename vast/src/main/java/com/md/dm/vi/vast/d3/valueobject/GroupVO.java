package com.md.dm.vi.vast.d3.valueobject;

public class GroupVO {
	
	private String bussinesUnit;
	private String machineClass;
	private int count;
	
	public GroupVO(String bussinesUnit, String machineClass, int count) {
		super();
		this.bussinesUnit = bussinesUnit;
		this.machineClass = machineClass;
		this.count = count;
	}

	public String getBussinesUnit() {
		return bussinesUnit;
	}
	
	public int getCount() {
		return count;
	}

	public String getMachineClass() {
		return machineClass;
	}
	
	
	
	

}
