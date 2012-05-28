package com.md.dm.vi.vast.d3.valueobject;

public class GroupVO {
	
	private String bussinesUnit;
	private String machineClass;
	private String machineFunction;
	private int count;
	
	public GroupVO(String bussinesUnit, String machineClass, String machineFunction, int count) {
		super();
		this.bussinesUnit = bussinesUnit;
		this.machineClass = machineClass;
		this.machineFunction = machineFunction;
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

	public String getMachineFunction() {
		return machineFunction;
	}
	
	
	
	

}
