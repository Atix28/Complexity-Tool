package Ctc;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ctc_java {
	
	ArrayList <String> lines;
	ArrayList <Integer> ctc_units;
	
	//expressions for checking Control structures
	String if_regx = "/(if |if\\()/g";

	public ctc_java(ArrayList <String> lines) {
		this.lines = lines;
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
	
}
