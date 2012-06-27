/**
 * 
 */
package com.md.dm.vi.vast.model;

import java.util.Date;

/**
 * @author diego
 * 
 */
public class Status {

	private long tkey;
	private Date healthTime;
	private int numConnections;
	private int policyStatus;
	private int activityFlag;
	private Date fixedHealthTime;

	public Status(long tkey, Date healthTime, int numConnections, int policyStatus, int activityFlag, Date fixedHealthTime) {
		super();
		this.tkey = tkey;
		this.healthTime = healthTime;
		this.numConnections = numConnections;
		this.policyStatus = policyStatus;
		this.activityFlag = activityFlag;
		this.fixedHealthTime = fixedHealthTime;
	}

	public Date getFixedHealthTime() {
		return fixedHealthTime;
	}

	public void setFixedHealthTime(Date fixedHealthTime) {
		this.fixedHealthTime = fixedHealthTime;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (tkey ^ (tkey >>> 32));
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
		Status other = (Status) obj;
		if (tkey != other.tkey)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Status [tkey=" + tkey + ", healthTime=" + healthTime
				+ ", numConnections=" + numConnections + ", policyStatus="
				+ policyStatus + ", activityFlag=" + activityFlag
				+ ", fixedHealthTime=" + fixedHealthTime + "]";
	}
}
