/**
 * 
 */
package com.md.dm.vi.vast.model;

import java.util.Arrays;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author diego
 * 
 */
@Document
public class Meta {

	@Id
	private ObjectId id;

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

	public ObjectId getId() {
		return id;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {

		if (this == obj) {
			return true;
		}

		if (obj == null || !getClass().equals(obj.getClass())) {
			return false;
		}

		Meta that = (Meta) obj;

		return id == null ? false : this.id.equals(that.id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {

		return 17 + (id == null ? 0 : id.hashCode());
	}

}
