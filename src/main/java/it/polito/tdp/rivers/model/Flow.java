package it.polito.tdp.rivers.model;

import java.util.Date;
import java.time.LocalDate;

public class Flow {
	private Date day;
	private double flow;
	private River river;

	public Flow(Date day, double flow, River river) {
		this.day = day;
		this.flow = flow;
		this.river = river;
	}

	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	public double getFlow() {
		return flow;
	}

	public void setFlow(double flow) {
		this.flow = flow;
	}

	@Override
	public String toString() {
		return "Flow [day=" + day + ", flow=" + flow + ", river=" + river + "]";
	}

	
}
