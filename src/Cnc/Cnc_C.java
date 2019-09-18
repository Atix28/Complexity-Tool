package Cnc;

import java.util.ArrayList;

public class Cnc_C {

	//This was added so that TW files doesn't have errors please edit this accordingly

	
	ArrayList<String> lines;
	ArrayList<Integer> CncUnits;
	
	
	public Cnc_C(ArrayList<String> lines) {
		this.lines = lines;
		CncUnits = new ArrayList<Integer>(lines.size());
	}
	
	public ArrayList<Integer> getCnc() {
		//bracket_checker();
		return CncUnits;
	}

	public int getTotalCnc() {
		int total = 0;

		for (int i = 0; i < CncUnits.size(); i++) {
			total += CncUnits.get(i);
		}

		return total;
	}
	
}
