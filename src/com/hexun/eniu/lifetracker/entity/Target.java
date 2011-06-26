package com.hexun.eniu.lifetracker.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Target {
	private Target() {
		tes = new ArrayList<TargetEntry>();
	}

	public Target(String name) {
		this();
		this.name = name;

	}

	public Target(String name, Date created) {
		this();
		this.name = name;
		this.created = created;

	}

	private int id;
	private String name;
	private Date created;
	private long lasting;

	private List<TargetEntry> tes;

	public void addTargetEntry(TargetEntry te) {
		tes.add(te);
	}

	

	public List<TargetEntry> getTes() {
		return tes;
	}

	public void setTes(List<TargetEntry> tes) {
		this.tes = tes;
	}

	public TargetEntry getLastTargetEntry() {
		return tes.get(tes.size() - 1);
	}

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

	// public long getCreateTime() {
	// return createTime;
	// }
	//
	// public void setCreateTime(Long createTime) {
	// this.createTime = createTime;
	// }
	//
	// public List<Long> getStartTime() {
	// return startTime;
	// }
	//
	// public void setStartTime(List<Long> startTime) {
	// this.startTime = startTime;
	// }
	//
	// public List<Long> getEndTime() {
	// return endTime;
	// }
	//
	// public void setEndTime(List<Long> endTime) {
	// this.endTime = endTime;
	// }

}