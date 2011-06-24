package com.hexun.eniu.lifetracker.activities.mem;

import java.util.Date;

import com.hexun.eniu.lifetracker.entity.Target;

public class Cache {

	private static int STATE_FULL = 1;
	private static int STATE_EMPTY = 2;

	private static Target serialCurTarget;
	private static int stSerialCurTarget = STATE_EMPTY;

	private static Target concurrentCurTarget;
	private static int stConcurrentCurTarget = STATE_EMPTY;

	public static Target getSerialCurTarget() {
		if (stSerialCurTarget == STATE_EMPTY) {
			return null;
		} else {
			return serialCurTarget;
		}
	}

	public static void setSerialCurTarget(String name, Date created,
			long lasting) {
		if (stSerialCurTarget == STATE_EMPTY && serialCurTarget == null) {
			serialCurTarget = new Target(name, created);
			serialCurTarget.setLasting(lasting);
		} else {
			serialCurTarget.setName(name);
			serialCurTarget.setCreated(created);
			serialCurTarget.setLasting(lasting);
		}
		stSerialCurTarget = STATE_FULL;
	}

	public static Target getConcurrentCurTarget() {
		return concurrentCurTarget;
	}

}
