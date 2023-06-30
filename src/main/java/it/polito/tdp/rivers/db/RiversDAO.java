package it.polito.tdp.rivers.db;

import java.util.*;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.Measurments;
import it.polito.tdp.rivers.model.River;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RiversDAO {

	public List<River> getAllRivers(Map<Integer,River> idMapRiver) {
		
		final String sql = "SELECT id, name FROM river";

		List<River> rivers = new LinkedList<River>();

		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();

			while (res.next()) {
				River r = new River(res.getInt("id"), res.getString("name"));
				idMapRiver.put(r.getId(), r) ;
				rivers.add(r) ;
				
			}

			conn.close();
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");
		}

		return rivers;
	}
	
	public Map<Integer,Measurments> getMeasurment(River r) {
		
		Map<Integer,Measurments> allMes = new HashMap<Integer,Measurments> () ;
		String sql = "Select MIN(flow.day), MAX(flow.day), COUNT(flow.id), AVG(flow.flow) "
				+ "From flow "
				+ "Where flow.river = ? " ;
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, r.getId());
			ResultSet res = st.executeQuery();
			
			res.first();
			Measurments mes = new Measurments(r, res.getDate(1), res.getDate(2),res.getInt(3), res.getDouble(4)) ;
			
			allMes.put(r.getId(),mes) ;
			

			conn.close();
			
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");

		}
		return allMes ;
	}
	
	public List<Flow> getAllFLow(River r){
		
		List<Flow> allFlow = new ArrayList<Flow>() ;
		
		String sql = "Select * "
				+ "from river, flow "
				+ "where river.id = ? and river.id = flow.river ";
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, r.getId());
			ResultSet res = st.executeQuery();
			
			while(res.next()) {
				Flow f = new Flow (res.getDate(2), res.getDouble(3), r) ;
				allFlow.add(f) ;
			}
			
			conn.close();
			
			
		} catch (SQLException e) {
			//e.printStackTrace();
			throw new RuntimeException("SQL Error");

		}
		
		return allFlow ;
		
	}
}
