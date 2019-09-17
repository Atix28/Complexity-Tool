package Cps;

import java.util.ArrayList;

import Cs.Cs_c;
import Tw.Tw_C;

public class Cps_C {
	
	ArrayList<String> lines;
	ArrayList<Integer> CpsUnits;
	
	public Cps_C(ArrayList<String> lines) {
		this.lines = lines;
		CpsUnits = new ArrayList<Integer>(lines.size());		
	}
	
	public void calcCps() {
		Cs_c Cs = new Cs_c(lines);
		Tw_C Tw = new Tw_C(lines);
		
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
