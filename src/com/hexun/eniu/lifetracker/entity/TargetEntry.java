package com.hexun.eniu.lifetracker.entity;

import java.util.Date;

public class TargetEntry {
	private int id;
	private int tid;
	private Date startTime;
	private Date stopTime;
	private boolean bFirst;
	private boolean bLast;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTid() {
		return tid;
	}

	public void setTid(int tid) {
		this.tid = tid;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getStopTime() {
		return stopTime;
	}

	public void setStopTime(Date stopTime) {
		this.stopTime = stopTime;
	}

	public boolean isbFirst() {
		return bFirst;
	}

	public void setbFirst(boolean bFirst) {
		this.bFirst = bFirst;
	}

	public boolean isbLast() {
		return bLast;
	}

	public void setbLast(boolean bLast) {
		this.bLast = bLast;
	}

}
