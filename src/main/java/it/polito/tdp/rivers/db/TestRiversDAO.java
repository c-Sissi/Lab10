package it.polito.tdp.rivers.db;

import java.util.*;

import it.polito.tdp.rivers.model.River;

public class TestRiversDAO {
	
	public static void main(String[] args) {
		Map <Integer, River> map = new HashMap<Integer,River>();
		RiversDAO dao = new RiversDAO();
		System.out.println(dao.getAllRivers(map));
		for(River r: map.values()) {
			System.out.println(dao.getMeasurment(r).toString()) ;
		}
	}

}
