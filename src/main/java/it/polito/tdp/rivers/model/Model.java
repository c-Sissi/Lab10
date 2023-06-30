package it.polito.tdp.rivers.model;


import java.util.*;
import it.polito.tdp.rivers.db.RiversDAO;

public class Model {

	private RiversDAO dao ;
	private Map<Integer,River> idMapRiver ;
	
	private PriorityQueue<Flow> queue ;
	
	public Model() {
		this.dao = new RiversDAO () ;
		this.idMapRiver = new HashMap<Integer, River> () ;
	}
	
	public List<String> getAllRivers (){
		List<String> elencoFiumi = new ArrayList<String>();
		for(River r: this.dao.getAllRivers(idMapRiver)) {
			elencoFiumi.add(r.getName()) ;
		}
		return elencoFiumi ;
	}
	
	public Collection<River> mappaFiumi(){
		return this.idMapRiver.values();
	}
	
	public Map<Integer,Measurments> getAllMeasurments(River r){
		Map<Integer,Measurments> allMes = new HashMap<Integer,Measurments> (this.dao.getMeasurment(r)) ;
		for(Measurments mes:  getAllMeasurments(r).values()) {
			r.setFlowAvg(mes.getAvgMisure());
		}
		return allMes ;
		
	}
	
	public List<Flow> getAllFlows(River r){
		return this.dao.getAllFLow(r) ;
		
	}
	
	
	public SimulationResult simulate(River r, double k) {
		
//		INIZIALIZZO LA CODA
		this.queue = new PriorityQueue<Flow> () ;
		this.queue.addAll(r.getFlows());
		
		List<Double> capacity = new ArrayList<Double> () ;
		
		double Q = k * 30 * this.convertM3SecToM3Day(r.getFlowAvg()) ;
		double C = Q / 2 ;
		double fOutMin = this.convertM3SecToM3Day(0.8 * r.getFlowAvg()) ;
		int numGiorni = 0 ;
		
		System.out.println("Q: " + Q);
		
		Flow flow ;
		while((flow = this.queue.poll()) != null ) {
			System.out.println("Date: " + flow.getDay()) ;
			
			double fOut = fOutMin ;
			
//			C'E' IL 5% DI POSSIBILITA' CHE F.OUT > 10*F.OUT.MIN
			if(Math.random() > 0.95) {
				fOut = 10 * fOutMin;
				System.out.println("fOut: 10 volte fOutMin") ;
			}
			
			System.out.println("fOut: "+ fOut);
			System.out.println("fIn: " + this.convertM3SecToM3Day(flow.getFlow())) ;
			
// 			AGGIUNGO fIn A C
			C += convertM3SecToM3Day(flow.getFlow());

// 			SE C E' MAGGIORE DELLA CAPACITA' MASSIMA
			if (C > Q) {
// 			TRACIMAZIONE -> LA QT IN ECCESSO ESCE
				
				C = Q;
			}

			if (C < fOut) {
// 			NON RIESCO A GARANTIRE LA QT MINIMA
				numGiorni++;
// 			IL BACINO COMUNQUE SI SVUOTA
				C = 0;
			} 
			else {
//			FACCIO USCIRE LA QT GIORNALIERA
				C -= fOut;
			}

			System.out.println("C: " + C + "'\n");

// 			TENGO UNA LISTA DELLE CAPACITA' GIORNALIERE DEL BACINO
			capacity.add(C);
		}
					

// 		CALCOLO LA MEDIA DELLE CAPACITA'
		double CAvg = 0;
		for (Double d : capacity) {
			CAvg += d;
		}
		CAvg = CAvg / capacity.size();
		return new SimulationResult(CAvg, numGiorni);
	}

	
	public double convertM3SecToM3Day(double input) {
		return input * 60 * 60 * 24;
	}

	public double convertM3DayToM3Sec(double input) {
		return input / 60 / 60 / 24;
	}
}
