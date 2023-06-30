package it.polito.tdp.rivers.model;

public class SimulationResult {
	double avgC ;
    int numGiorni ;
	
	public SimulationResult(double avgC, int numGiorni) {
		this.avgC = avgC;
		this.numGiorni = numGiorni;
	}

	public double getAvgC() {
		return avgC;
	}

	public void setAvgC(double avgC) {
		this.avgC = avgC;
	}

	public int getNumGiorni() {
		return numGiorni;
	}

	public void setNumGiorni(int numGiorni) {
		this.numGiorni = numGiorni;
	}
	

}
