package com.hexun.eniu.lifetracker.entity;

import java.util.Date;

public class Target {

	public Target(String name) {
		this.name = name;
//		this.state = STATE_CREATED;
//		startTime = new ArrayList<Long>();
//		endTime = new ArrayList<Long>();
	}
	public Target(String name, Date created) {
		this.name = name;
		this.created = created;
		
//		this.state = STATE_CREATED;
//		startTime = new ArrayList<Long>();
//		endTime = new ArrayList<Long>();
	}

//	public static final int STATE_CREATED = 0;
//	public static final int STATE_RUNNING = 1;
//	public static final int STATE_PAUSED = 2;
//	public static final int STATE_ENDED = 2;

	private int id;
	private String name;
	private Date created;
	private long lasting;
	
	
//	private int state; //

//	private Long createTime;
//	private List<Long> startTime;
//	private List<Long> endTime;
//
//	public int getState() {
//		return state;
//	}
//
//	public void setState(int state) {
//		this.state = state;
//	}

	// private Date accomplishTime;

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getLasting() {
		return lasting;
	}
	public void setLasting(long lasting) {
		this.lasting = lasting;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

//	public long getCreateTime() {
//		return createTime;
//	}
//
//	public void setCreateTime(Long createTime) {
//		this.createTime = createTime;
//	}
//
//	public List<Long> getStartTime() {
//		return startTime;
//	}
//
//	public void setStartTime(List<Long> startTime) {
//		this.startTime = startTime;
//	}
//
//	public List<Long> getEndTime() {
//		return endTime;
//	}
//
//	public void setEndTime(List<Long> endTime) {
//		this.endTime = endTime;
//	}

}