package Ctc;

import java.util.ArrayList;

public class ctc_c {
	
	
	ArrayList<String> lines;
	ArrayList<Integer> ctc_units;
	
	public ctc_c(ArrayList<String> lines) {
		this.lines = lines;
		ctc_units = new ArrayList<Integer>(lines.size());
	}

	//This was added so that TW files doesn't have erros please edit this accordingly
	public ArrayList<Integer> getCtc() {
		//addtoArray();
		return ctc_units;
	}

	// get total Ctc
	public int getTotalCtc() {
		int total = 0;

		for (int i = 0; i < ctc_units.size(); i++) {
			total += ctc_units.get(i);
		}

		return total;
	}
	
}
