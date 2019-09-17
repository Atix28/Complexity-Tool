package Ci;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ci_C {
	
	ArrayList<String> lines;
	ArrayList<Integer> ci_units;
	int brackets=0;
	int count=0;
	int addition = 1;
	int lastcount =0;
	
    //expressions for checking non inheritance
    String noinheritance_regex = "\\b(class)(\\s+\\w+\\s*)(\\{)";
    String inheritance_regex = "\\b(class)(\\s+\\w+\\s*)(\\:+\\s*)(class)(\\s+\\w+\\s*)(\\{)";
    // Expression for single bracket or empty line
 	String singleline_regex = "^(\\s*\\}\\s*)|^(\\s*)$";
 	// Expression for brackets
 	String open_brackets = "\\{";
 	String close_brackets = "\\}";
	
	
	public Ci_C(ArrayList<String> lines) {
		this.lines = lines;
		ci_units = new ArrayList<Integer>(lines.size());		
	}
 	
 	public void addBracket() {
		brackets++;
	}

	public void removeBracket() {
		if (brackets > 0) {
			brackets--;
		}else {
			count -= lastcount;
		}
	}
 	
 	public void inheritance_count() {
 		
 		Pattern normal_pattern = Pattern.compile(noinheritance_regex);
 		Pattern extended_pattern = Pattern.compile(inheritance_regex);
		Pattern single = Pattern.compile(singleline_regex);
		Pattern close_p = Pattern.compile(close_brackets);
		Pattern open_p = Pattern.compile(open_brackets);
		
		
 		//check lines 1 by 1
 		for (int i = 0; i < lines.size(); i++) {
			
			String line = lines.get(i);
			Matcher Nmatcher = normal_pattern.matcher(line);
			Matcher Ematcher = extended_pattern.matcher(line);
			Matcher singleM = single.matcher(line);
			
			if(brackets==0) {
			if(Nmatcher.find()) {
				count += 1;
				lastcount = 1;
				ci_units.add(count + addition);
				continue;
			} else if(Ematcher.find()) {
				count += 2;
				lastcount =2;
				ci_units.add(count + addition);
				continue;
			}
			}
			Matcher openBrackets = open_p.matcher(line);
			while(openBrackets.find()) addBracket();
			Matcher closeBrackets = close_p.matcher(line);
			while(closeBrackets.find()) removeBracket();
			if(singleM.find()){
				ci_units.add(0);
				continue;
			}
			ci_units.add(count + addition);
 		}
       
 	}
 	
 	
	public ArrayList<Integer> getCi() {
		inheritance_count();
		return ci_units;
	}

	public int getTotalCi() {
		int total = 0;

		for (int i = 0; i < ci_units.size(); i++) {
			total += ci_units.get(i);
		}

		return total;
	}
		
	
}