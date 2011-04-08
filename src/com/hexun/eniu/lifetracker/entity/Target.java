package com.hexun.eniu.lifetracker.entity;

import java.util.Date;
import java.util.List;

public class Target {

	public Target(String name) {
		this.name = name;
	}

	private String name;
	private Date beginTime;
	private List<Date> startTime;
	private List<Date> endTime;
	private Date accomplishTime;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
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

	public Date getAccomplishTime() {
		return accomplishTime;
	}

	public void setAccomplishTime(Date accomplishTime) {
		this.accomplishTime = accomplishTime;
	}

}