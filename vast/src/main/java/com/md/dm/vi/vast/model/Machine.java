package com.md.dm.vi.vast.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Machine {

	@Id
	private ObjectId id;

	private String ipAddr;
	private String machineClass;
	private String machineFunction;
	private int bussinesUnit;
	private int facility;

	private double[] location;

	private List<Status> statusList;

	/**
	 * 
	 * @param line
	 * @return
	 */
	public static Machine build(String line) {
		String[] data = line.split(",");
		if (data.length != 7) {
			throw new RuntimeException("Invalid parameters");
		}

		String ip = data[0].trim();

		return new Machine(ip, data[1], data[2], data[3], data[4], Double.parseDouble(data[5]),
				Double.parseDouble(data[6]));
	}

	public Machine(String ipAddr, String machineClass, String machineFunction, String bussinesUnit,
			String facility, double[] location) {
		super();

		this.ipAddr = ipAddr;
		this.machineClass = machineClass;
		this.machineFunction = machineFunction;

		if (bussinesUnit.equals("headquarters")) {
			this.bussinesUnit = 0;
		} else {
			this.bussinesUnit = Integer.valueOf(bussinesUnit.split("-")[1]);
		}

		if (facility.equals("headquarters")) {
			this.facility = 600;
		} else if (facility.contains("datacenter")) {
			this.facility = 500 + Integer.valueOf(facility.split("-")[1]);
		} else if (facility.equals("branch")) {
			this.facility = Integer.valueOf(facility.split("-")[1]);
			;
		}
		this.location = location;
		this.statusList = new ArrayList<Status>();
	}

	public Machine(String ipAddr, String machineClass, String machineFunction, String bussinesUnit,
			String facility, double x, double y) {
		this(ipAddr, machineClass, machineFunction, bussinesUnit, facility, new double[] { x, y });
	}

	@PersistenceConstructor
	Machine(String ipAddr, String machineClass, String machineFunction, int bussinesUnit,
			int facility, double[] location) {
		super();
		this.ipAddr = ipAddr;
		this.machineClass = machineClass;
		this.machineFunction = machineFunction;
		this.bussinesUnit = bussinesUnit;
		this.facility = facility;
		this.location = location;
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
	public int getBussinesUnit() {
		return bussinesUnit;
	}

	/**
	 * @return the facility
	 */
	public int getFacility() {
		return facility;
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

	/**
	 * @return the statusList
	 */
	public List<Status> getStatusList() {
		return statusList;
	}

	/**
	 * @param statusList
	 *            the statusList to set
	 */
	public void setStatusList(List<Status> statusList) {
		this.statusList = statusList;
	}

	/**
	 * @return the id
	 */
	public ObjectId getId() {
		return id;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Machine [id=" + id + ", ipAddr=" + ipAddr + ", machineClass=" + machineClass
				+ ", machineFunction=" + machineFunction + ", bussinesUnit=" + bussinesUnit
				+ ", facility=" + facility + ", location=" + Arrays.toString(location)
				+ ", statusList=" + statusList + "]";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Machine other = (Machine) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
