package Cr;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cr {
	
	ArrayList<String> lines;
	ArrayList<Integer> Cr;

	String word = "";
	// Expression for finding function
	String function_regex = "(public|private)(\\s+\\w+\\s+)(\\w+\\s*)\\(";
	// Expression for finding recursive
	String rec_regex = "\\b(return\\s+(" + word + "))";

	String openBrackets = "\\{";
	String closeBrackets = "\\}";

	// initialize which lines are in recursive
	int startLine = 0;
	int endLine = 0;
	
	public Cr(ArrayList<String> lines, ArrayList<Integer> cps) {
		this.lines = lines;
		Cr = cps;
	}
	
	public void calculateCr() {

		// "{}" Bracket count
				int bracket = 0;

				for (int i = 0; i < lines.size(); i++) {

					Pattern pattern = Pattern.compile(function_regex);
					Matcher matcher = pattern.matcher(lines.get(i));
	
					// check for function name
					if (matcher.find()) {
						word = matcher.group(3);
						startLine = i;
					}
				}

}
}
