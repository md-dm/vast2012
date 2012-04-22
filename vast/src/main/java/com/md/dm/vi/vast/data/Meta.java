/**
 * 
 */
package com.md.dm.vi.vast.data;

import java.util.Arrays;

import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author diego
 * 
 */
@Document(collection = "meta")
public class Meta {

	private String ipAddr;
	private String machineClass;
	private String machineFunction;
	private String bussinesUnit;
	private String facility;

	private double[] location;

	public static Meta build(String line) {
		String[] data = line.split(",");
		if (data.length != 7) {
			throw new RuntimeException("Invalid parameters");
		}
		return new Meta(data[0], data[1], data[2], data[3], data[4],
				Double.parseDouble(data[5]), Double.parseDouble(data[6]));
	}

	@PersistenceConstructor
	Meta(String ipAddr, String machineClass, String machineFunction,
			String bussinesUnit, String facility, double[] location) {
		super();
		this.ipAddr = ipAddr;
		this.machineClass = machineClass;
		this.machineFunction = machineFunction;
		this.bussinesUnit = bussinesUnit;
		this.facility = facility;
		this.location = location;
	}

	public Meta(String ipAddr, String machineClass, String machineFunction,
			String bussinesUnit, String facility, double x, double y) {
		super();
		this.ipAddr = ipAddr;
		this.machineClass = machineClass;
		this.machineFunction = machineFunction;
		this.bussinesUnit = bussinesUnit;
		this.facility = facility;
		this.location = new double[] { x, y };
	}

	/**
	 * @return the ipAddr
	 */
	public String getIpAddr() {
		return ipAddr;
	}

	/**
	 * @param ipAddr
	 *            the ipAddr to set
	 */
	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	/**
	 * @return the machineClass
	 */
	public String getMachineClass() {
		return machineClass;
	}

	/**
	 * @param machineClass
	 *            the machineClass to set
	 */
	public void setMachineClass(String machineClass) {
		this.machineClass = machineClass;
	}

	/**
	 * @return the machineFunction
	 */
	public String getMachineFunction() {
		return machineFunction;
	}

	/**
	 * @param machineFunction
	 *            the machineFunction to set
	 */
	public void setMachineFunction(String machineFunction) {
		this.machineFunction = machineFunction;
	}

	/**
	 * @return the bussinesUnit
	 */
	public String getBussinesUnit() {
		return bussinesUnit;
	}

	/**
	 * @param bussinesUnit
	 *            the bussinesUnit to set
	 */
	public void setBussinesUnit(String bussinesUnit) {
		this.bussinesUnit = bussinesUnit;
	}

	/**
	 * @return the facility
	 */
	public String getFacility() {
		return facility;
	}

	/**
	 * @param facility
	 *            the facility to set
	 */
	public void setFacility(String facility) {
		this.facility = facility;
	}

	/**
	 * @return the location
	 */
	public double[] getLocation() {
		return location;
	}

	/**
	 * @param location
	 *            the location to set
	 */
	public void setLocation(double[] location) {
		this.location = location;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Meta [ipAddr=");
		builder.append(ipAddr);
		builder.append(", machineClass=");
		builder.append(machineClass);
		builder.append(", machineFunction=");
		builder.append(machineFunction);
		builder.append(", bussinesUnit=");
		builder.append(bussinesUnit);
		builder.append(", facility=");
		builder.append(facility);
		builder.append(", location=");
		builder.append(Arrays.toString(location));
		builder.append("]");
		return builder.toString();
	}

}
