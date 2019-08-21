package Ci;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Measuring complexity due to inheritance
public class Ci_java {

    ArrayList<String> lines;
    ArrayList<Integer> Ci_units; 

    String extend_regex = "\\b(class)(\\s+\\w+\\s+)(extends)";
    String noinheritance_regex = "\\b(class)(\\s+\\w+\\s+)(\\{)";

    public Ci_java(ArrayList<String> lines){
        this.lines = lines;
        Ci_units = new ArrayList<Integer>(lines.size());
    }

    //get CCi due to extends
    public int noinheritance_count(String line){

        Pattern pattern_check = Pattern.compile(noinheritance_regex);
        Matcher matcher_check = pattern_check.matcher(line);
        int count = 10;
//		while (matcher_check.find()) {
//			count += 2;
//		}
        
        if(matcher_check.find()) {
        	count += 2;
        }
		return count;
    }

    //calculate Ci
    public void addtoArray() {
		for (int i = 0; i < lines.size(); i++) {
		int noinheritance_count = noinheritance_count(lines.get(i));
		
		Ci_units.add(noinheritance_count);
		
		}

    }
    
    //get Ci output as an array
    public ArrayList<Integer> getCi() {
    	addtoArray();
    	return Ci_units;
		
	}
    
    public int getTotalCi() {
    	int total = 0;
    	
    	for(int i = 0; i < Ci_units.size(); i++) {
			total += Ci_units.get(i);
		}

		return total;
	}
   
}
