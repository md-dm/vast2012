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
	private int firstOctect;
	private int secondOctect;
	private int thirdOctect;
	private int fourthOctect;
	private String machineClass;
	private String machineFunction;
	private String bussinesUnit;
	private String facility;

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
		String[] octects = ip.split("\\.");

		return new Machine(ip, Integer.parseInt(octects[0]), Integer.parseInt(octects[1]),
				Integer.parseInt(octects[2]), Integer.parseInt(octects[3]), data[1], data[2],
				data[3], data[4], Double.parseDouble(data[5]), Double.parseDouble(data[6]));
	}

	@PersistenceConstructor
	Machine(String ipAddr, int firstOctect, int secondOctect, int thirdOctect, int fourthOctect,
			String machineClass, String machineFunction, String bussinesUnit, String facility,
			double[] location) {
		super();
		this.ipAddr = ipAddr;
		this.firstOctect = firstOctect;
		this.secondOctect = secondOctect;
		this.thirdOctect = thirdOctect;
		this.fourthOctect = fourthOctect;
		this.machineClass = machineClass;
		this.machineFunction = machineFunction;
		this.bussinesUnit = bussinesUnit;
		this.facility = facility;
		this.location = location;
		this.statusList = new ArrayList<Status>();
	}

	public Machine(String ipAddr, int firstOctect, int secondOctect, int thirdOctect,
			int fourthOctect, String machineClass, String machineFunction, String bussinesUnit,
			String facility, double x, double y) {
		this(ipAddr, firstOctect, secondOctect, thirdOctect, fourthOctect, machineClass,
				machineFunction, bussinesUnit, facility, new double[] { x, y });
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
	 * @return the firstOctect
	 */
	public int getFirstOctect() {
		return firstOctect;
	}

	/**
	 * @param firstOctect
	 *            the firstOctect to set
	 */
	public void setFirstOctect(int firstOctect) {
		this.firstOctect = firstOctect;
	}

	/**
	 * @return the secondOctect
	 */
	public int getSecondOctect() {
		return secondOctect;
	}

	/**
	 * @param secondOctect
	 *            the secondOctect to set
	 */
	public void setSecondOctect(int secondOctect) {
		this.secondOctect = secondOctect;
	}

	/**
	 * @return the thirdOctect
	 */
	public int getThirdOctect() {
		return thirdOctect;
	}

	/**
	 * @param thirdOctect
	 *            the thirdOctect to set
	 */
	public void setThirdOctect(int thirdOctect) {
		this.thirdOctect = thirdOctect;
	}

	/**
	 * @return the fourthOctect
	 */
	public int getFourthOctect() {
		return fourthOctect;
	}

	/**
	 * @param fourthOctect
	 *            the fourthOctect to set
	 */
	public void setFourthOctect(int fourthOctect) {
		this.fourthOctect = fourthOctect;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Machine [id=" + id + ", ipAddr=" + ipAddr + ", firstOctect=" + firstOctect
				+ ", secondOctect=" + secondOctect + ", thirdOctect=" + thirdOctect
				+ ", fourthOctect=" + fourthOctect + ", machineClass=" + machineClass
				+ ", machineFunction=" + machineFunction + ", bussinesUnit=" + bussinesUnit
				+ ", facility=" + facility + ", location=" + Arrays.toString(location)
				+ ", statusList=" + statusList + "]";
	}
}
