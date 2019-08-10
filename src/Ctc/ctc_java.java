package Ctc;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ctc_java {
	
	ArrayList <String> lines;
	ArrayList <Integer> ctc_units;
	
	//expressions for checking if conditions
	String if_regx = "(^if\\s|^if\\(|\\sif |\\sif\\()";

	public ctc_java(ArrayList <String> lines) {
		this.lines = lines;
		ctc_units = new ArrayList<Integer>(lines.size());
	}
	
	public int if_count(String line) {
		
		Pattern pattern = Pattern.compile(if_regx);
		Matcher matcher = pattern.matcher(line);
		
		int count = 0;
		while(matcher.find()) {
			count++;
		}
		return count;
	}
	
	public void addtoArray() {
		for(int i=0; i<lines.size();i++) {
			ctc_units.add(if_count(lines.get(i)));
		}
	}
	
	public ArrayList<Integer> getCtc() {
		addtoArray();
		return ctc_units;
	}
	
}
