package com.hexun.eniu.lifetracker.activities.mem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.hexun.eniu.lifetracker.entity.Target;
import com.hexun.eniu.lifetracker.entity.TargetEntry;

public class Cache {

	private static int STATE_FULL = 1;
	private static int STATE_EMPTY = 2;

	private static Target serialTarget;
	private static int stSerialTarget = STATE_EMPTY;

	private static List<Target> concurrentTargetList;
	// private static int stconcurrentTargetList = STATE_EMPTY;

	static {
		serialTarget = new Target("_NoName");
		concurrentTargetList = new ArrayList<Target>();
	}

	// ---------------------------- condition serial -------
	public static Target getSerialTarget() {
		if (stSerialTarget == STATE_EMPTY) {
			return null;
		} else {
			return serialTarget;
		}
	}

	public static void setSerialTarget(String name, Date created, long lasting) {
		serialTarget.setName(name);
		serialTarget.setCreated(created);
		serialTarget.setLasting(lasting);
		stSerialTarget = STATE_FULL;
	}

	public static void clearSerialTarget() {
		stSerialTarget = STATE_EMPTY;
	}

	// ---------------------------- condition concurrent -------
	public static void addConcurrentTarget(Target t) {
		concurrentTargetList.add(t);
	}
	
	public static void addConcurrentTargetEntry(int tuiid, TargetEntry t) {
		concurrentTargetList.get(tuiid);
	}
}
