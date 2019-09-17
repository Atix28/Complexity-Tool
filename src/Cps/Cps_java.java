package Cps;

import java.util.ArrayList;

import Cs.Cs_java;
import Tw.Tw_java;

public class Cps_java {
	
	ArrayList<String> lines;
	ArrayList<Integer> CpsUnits;
	
	public Cps_java(ArrayList<String> lines) {
		this.lines = lines;
		CpsUnits = new ArrayList<Integer>(lines.size());		
	}
	
	public void calcCps() {
		Cs_java Cs = new Cs_java(lines);
		Tw_java Tw = new Tw_java(lines);
		
		ArrayList<Integer> Cs_units = Cs.getCs();
		ArrayList<Integer> Tw_units = Tw.getTw();

		for (int i = 0; i < Cs_units.size(); i++) {
			int count = 0;
			count = Cs_units.get(i) * Tw_units.get(i);
			CpsUnits.add(count);
		}			
	}
	
	public ArrayList<Integer> getCps() {
		calcCps();
		return CpsUnits;
	}
	
	public int getTotalCps() {
		int total = 0;

		for (int l = 0; l < CpsUnits.size(); l++) {
			total += CpsUnits.get(l);
		}

		return total;
	}
}
