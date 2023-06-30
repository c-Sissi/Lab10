package it.polito.tdp.rivers.model;

import java.time.LocalDate;
import java.util.Date;

public class Measurments {

	private River river ;
	private Date fist ;
	private Date last ;
	private int numMisure ;
	private double avgMisure ;
	
	public Measurments(River river, Date fist, Date last, int numMisure, double avgMisure) {
		this.river = river;
		this.fist = fist;
		this.last = last;
		this.numMisure = numMisure;
		this.avgMisure = avgMisure;
	}

	public River getRiver() {
		return river;
	}

	public Date getFist() {
		return fist;
	}

	public Date getLast() {
		return last;
	}

	public int getNumMisure() {
		return numMisure;
	}

	public double getAvgMisure() {
		return avgMisure;
	}
	
	
	
	
}

