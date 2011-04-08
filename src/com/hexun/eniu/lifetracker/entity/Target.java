package com.hexun.eniu.lifetracker.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Target {

	public Target(String name) {
		this.name = name;
		this.state = STATE_CREATED;
		startTime = new ArrayList<Date>();
		endTime = new ArrayList<Date>();
	}

	public static final int STATE_CREATED = 0;
	public static final int STATE_RUNNING = 1;
	public static final int STATE_PAUSED = 2;
	public static final int STATE_ENDED = 2;

	private String name;
	private int state; //

	private Date createTime;
	private List<Date> startTime;
	private List<Date> endTime;

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	// private Date accomplishTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public List<Date> getStartTime() {
		return startTime;
	}

	public void setStartTime(List<Date> startTime) {
		this.startTime = startTime;
	}

	public List<Date> getEndTime() {
		return endTime;
	}

	public void setEndTime(List<Date> endTime) {
		this.endTime = endTime;
	}

}