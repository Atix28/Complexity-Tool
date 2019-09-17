package Ci;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Measuring complexity due to inheritance
public class Ci_java {
	
	ArrayList<String> lines;
	ArrayList<Integer> ci_units;
	int count = 0;
	
	
    //expressions for checking non inheritance
    String noextend_regex = "\\b(class)(\\s+\\w+\\s+)(\\{)";
    //expressions for checking extend condition
    String extend_regex = "\\b(class)(\\s+\\w+\\s+)(extends)";
    // Expression for single bracket or empty line
 	String singleline_regex = "^(\\s*\\}\\s*)|^(\\s*)$";
 	// Expression for brackets
 	String open_brackets = "\\{";
 	String close_brackets = "\\}";
 	
 	
 	public Ci_java(ArrayList<String> lines) {
		this.lines = lines;
		ci_units = new ArrayList<Integer>(lines.size());
	}
 	
 	public int inheritance_count(String line) {
 		
       Pattern pattern_check = Pattern.compile(noextend_regex);
       Matcher matcher_check = pattern_check.matcher(line);
        
       Pattern pattern_extend = Pattern.compile(extend_regex);
       Matcher matcher_extend = pattern_extend.matcher(line);
        
       Pattern patter_singleline = Pattern.compile(singleline_regex);
       Matcher matcher_singleline = patter_singleline.matcher(line);

       Pattern patter_openBrackets = Pattern.compile(open_brackets);
       Matcher matcher_openBrackets = patter_singleline.matcher(line);

       Pattern patter_closeBrackets = Pattern.compile(close_brackets);
       Matcher matcher_closeBrackets = patter_singleline.matcher(line);
       
       int increment = 1;
       if(matcher_check.find()) {
    	   count += increment;
       }
       else if(matcher_singleline.find() || matcher_openBrackets.find() || matcher_closeBrackets.find()) {
    	   count = 0;
       }
       return count;
       
 	}
 	
 	
	public void addtoArray() {
		for (int i = 0; i < lines.size(); i++) {

			// get Ci for each line
			int inheritance_count = inheritance_count(lines.get(i));

			// get total count for Ci
			ci_units.add(inheritance_count);
		}
	}
 	
 	
	public ArrayList<Integer> getCi() {
		addtoArray();
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