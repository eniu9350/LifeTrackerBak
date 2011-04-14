package com.hexun.eniu.lifetracker.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Target {

	public Target(String name) {
		this.name = name;
		this.state = STATE_CREATED;
		startTime = new ArrayList<Long>();
		endTime = new ArrayList<Long>();
	}

	public static final int STATE_CREATED = 0;
	public static final int STATE_RUNNING = 1;
	public static final int STATE_PAUSED = 2;
	public static final int STATE_ENDED = 2;

	private String name;
	private int state; //

	private Long createTime;
	private List<Long> startTime;
	private List<Long> endTime;

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

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	public List<Long> getStartTime() {
		return startTime;
	}

	public void setStartTime(List<Long> startTime) {
		this.startTime = startTime;
	}

	public List<Long> getEndTime() {
		return endTime;
	}

	public void setEndTime(List<Long> endTime) {
		this.endTime = endTime;
	}

}