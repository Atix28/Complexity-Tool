package Tw;

import java.util.ArrayList;
import Ci.Ci_C;
import Cnc.Cnc_C;
import Ctc.ctc_c;

public class Tw_C {
	
		
	ArrayList<String> lines;
	ArrayList<Integer> TwUnits;
	
	public Tw_C(ArrayList<String> lines) {
		this.lines = lines;
		TwUnits = new ArrayList<Integer>(lines.size());
	}
	
	public void calcTw() {
		ctc_c ctc = new ctc_c(lines);
		Cnc_C Cnc = new Cnc_C(lines);
		Ci_C ci = new Ci_C(lines);
		
		ArrayList<Integer> ctc_units = ctc.getCtc();
		ArrayList<Integer> Cnc_units = Cnc.getCnc();
		ArrayList<Integer> Ci_units = ci.getCi();
	
		
		for (int i = 0; i < ctc_units.size(); i++) {
					int count = 0 ;
					count = ctc_units.get(i) + Cnc_units.get(i) + Ci_units.get(i);
					TwUnits.add(count);					
				}
	}
	
	public ArrayList<Integer> getTw() {
		calcTw();
		return TwUnits;
	}
	
	public int getTotalTw() {
		int total = 0;
	
		for (int l = 0; l < TwUnits.size(); l++) {
			total += TwUnits.get(l);
		}
	
		return total;
	}	

}
