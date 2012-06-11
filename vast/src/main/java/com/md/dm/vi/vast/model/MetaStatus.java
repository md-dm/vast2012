/**
 * 
 */
package com.md.dm.vi.vast.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author diego
 * 
 */
@Document
public class MetaStatus {

	private static DateFormat formatter = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");
	@Id
	private ObjectId id;

	private long tkey;
	private String ipAddr;
	private int firstOctect;
	private int secondOctect;
	private int thirdOctect;
	private int fourthOctect;
	private Date healthTime;
	private int numConnections;
	private int policyStatus;
	private int activityFlag;

	/**
	 * 2448777,172.1.78.10,2012-02-02 14:00:00,83,1,1
	 * 
	 * @param line
	 * @return
	 */
	public static MetaStatus build(String line) throws Exception {

		String[] data = line.split(",");

		if (data.length != 6) {
			throw new RuntimeException("Invalid parameters");
		}
		String ip = data[1].trim();
		String[] octects = ip.split("\\.");
		return new MetaStatus(Long.parseLong(data[0]), ip,
				Integer.parseInt(octects[0]), Integer.parseInt(octects[1]),
				Integer.parseInt(octects[2]), Integer.parseInt(octects[3]),
				formatter.parse(data[2].trim()), Integer.parseInt(data[3]),
				Integer.parseInt(data[4]), Integer.parseInt(data[5]));
	}

	public MetaStatus(long tkey, String ipAddr, int firstOctect,
			int secondOctect, int thirdOctect, int fourthOctect,
			Date healthTime, int numConnections, int policyStatus,
			int activityFlag) {
		super();
		this.tkey = tkey;
		this.ipAddr = ipAddr;
		this.firstOctect = firstOctect;
		this.secondOctect = secondOctect;
		this.thirdOctect = thirdOctect;
		this.fourthOctect = fourthOctect;
		this.healthTime = healthTime;
		this.numConnections = numConnections;
		this.policyStatus = policyStatus;
		this.activityFlag = activityFlag;
	}

	/**
	 * @return the formatter
	 */
	public static DateFormat getFormatter() {
		return formatter;
	}

	/**
	 * @param formatter
	 *            the formatter to set
	 */
	public static void setFormatter(DateFormat formatter) {
		MetaStatus.formatter = formatter;
	}

	/**
	 * @return the tkey
	 */
	public long getTkey() {
		return tkey;
	}

	/**
	 * @param tkey
	 *            the tkey to set
	 */
	public void setTkey(long tkey) {
		this.tkey = tkey;
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
	 * @return the healthTime
	 */
	public Date getHealthTime() {
		return healthTime;
	}

	/**
	 * @param healthTime
	 *            the healthTime to set
	 */
	public void setHealthTime(Date healthTime) {
		this.healthTime = healthTime;
	}

	/**
	 * @return the numConnections
	 */
	public int getNumConnections() {
		return numConnections;
	}

	/**
	 * @param numConnections
	 *            the numConnections to set
	 */
	public void setNumConnections(int numConnections) {
		this.numConnections = numConnections;
	}

	/**
	 * @return the policyStatus
	 */
	public int getPolicyStatus() {
		return policyStatus;
	}

	/**
	 * @param policyStatus
	 *            the policyStatus to set
	 */
	public void setPolicyStatus(int policyStatus) {
		this.policyStatus = policyStatus;
	}

	/**
	 * @return the activityFlag
	 */
	public int getActivityFlag() {
		return activityFlag;
	}

	/**
	 * @param activityFlag
	 *            the activityFlag to set
	 */
	public void setActivityFlag(int activityFlag) {
		this.activityFlag = activityFlag;
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
		return "MetaStatus [id=" + id + ", tkey=" + tkey + ", ipAddr=" + ipAddr
				+ ", firstOctect=" + firstOctect + ", secondOctect="
				+ secondOctect + ", thirdOctect=" + thirdOctect
				+ ", fourthOctect=" + fourthOctect + ", healthTime="
				+ healthTime + ", numConnections=" + numConnections
				+ ", policyStatus=" + policyStatus + ", activityFlag="
				+ activityFlag + "]";
	}

}
