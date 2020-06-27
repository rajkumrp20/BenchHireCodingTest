package com.bean;

import org.json.JSONObject;

public class Cycleprop {
	private JSONObject cycledata;
	private int count;
	private String Msg;

	public Cycleprop(JSONObject cycledata, int i, String Msg) {
		this.cycledata = cycledata;
		this.count = i;
	}

	public JSONObject getCycleprop() {
		return cycledata;
	}

	public int getCount() {
		return count;
	}

	public String getMsg() {
		return Msg;
	}

}
