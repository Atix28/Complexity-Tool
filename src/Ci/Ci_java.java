package Ci;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Measuring complexity due to inheritance
public class Ci_java {

    ArrayList<String> lines;
    ArrayList<Integer> Ci_units; 

    String extend_regex = "\\b(class)(\\s+\\w+\\s+)(extends)";

    public Ci_java(ArrayList<String> lines){
        this.lines = lines;
        Ci_units = new ArrayList<Integer>(lines.size());
    }

    //get CCi due to extends
    public int extend_count(String line){

        Pattern pattern_check = Pattern.compile(extend_regex);
        Matcher matcher_check = pattern_check.matcher(line);

        int count = 0;
		while (matcher_check.find()) {
			count += 2;
		}
		return count;
    }

}
