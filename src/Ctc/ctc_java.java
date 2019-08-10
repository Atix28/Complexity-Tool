package Ctc;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ctc_java {
	
	ArrayList <String> lines;
	ArrayList <Integer> ctc_units;
	
	//expressions for checking if conditions
	String if_regex = "((?<!\\S)(if\\s*\\())";
	//expressions for checking logical conditions
	String logical_regex = "(\\|\\||\\&\\&)";
	//expressions for checking bitwise conditions
	String bitwise_regex = "(?<!\\|)(?<!\\&)(\\||\\&)(?!\\|)(?!\\&)";
	//expressions for checking iterative control structure
	String iterative_regex = "((?<!\\S)(while\\s*\\(.*\\)\\s*(?!\\s*;)|do\\s*\\{|do\\s*(?!.))|for\\s*\\()";
	//expressions for checking catch statements
	String chatch_regex = "((?<!\\S)(catch\\s*\\())";
	//expressions for checking switch case conditions
	String case_regex = "((?<!\\S)(case\\s+.*\\:|default\\s*\\:))";

	public ctc_java(ArrayList <String> lines) {
		this.lines = lines;
		ctc_units = new ArrayList<Integer>(lines.size());
	}
	
	public int if_count(String line) {
		
		Pattern pattern = Pattern.compile(if_regex);
		Matcher matcher = pattern.matcher(line);
		
		int count = 0;
		while(matcher.find()) {
			count++;
		}
		return count;
	}
	
	public int logical_count (String line) {
		
		Pattern pattern = Pattern.compile(logical_regex);
		Matcher matcher = pattern.matcher(line);
		
		int count = 0;
		while(matcher.find()) {
			count++;
		}
		return count;
	}
	
	public int bitwise_count (String line) {
		
		Pattern pattern = Pattern.compile(bitwise_regex);
		Matcher matcher = pattern.matcher(line);
		
		int count = 0;
		while(matcher.find()) {
			count++;
		}
		return count;
	}
	
	public void addtoArray() {
		for(int i=0; i<lines.size();i++) {
			
			int if_count = if_count(lines.get(i));
			int logical_count = logical_count(lines.get(i));
			int bitwise_count = bitwise_count(lines.get(i));
			
			ctc_units.add(if_count + logical_count + bitwise_count);
		}
	}
	
	public ArrayList<Integer> getCtc() {
		addtoArray();
		return ctc_units;
	}
	
}
