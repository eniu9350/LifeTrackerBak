package com.hexun.eniu.lifetracker.entity;


public class TargetEntry {
	private int id;
	private int tid;
	private long startTime;
	private long stopTime;
	private boolean bFirst;
	private boolean bLast;

	public TargetEntry() {
		bFirst = false;
		bLast = false;
	}

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

	public long getStartTime() {
		return startTime;
	}

	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}

	public long getStopTime() {
		return stopTime;
	}

	public void setStopTime(long stopTime) {
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
