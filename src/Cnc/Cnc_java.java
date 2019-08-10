package Cnc;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Cnc_java {

	ArrayList<String> lines;
	ArrayList<Integer> CncUnits;
	Integer brackets = 0;

	// Expression for control structures with open brackets
	String bracket_regex = "\\b((if|while|for|do)(\\s+|\\().*\\{)";
	// Expression for single bracket or empty line
	String singleline_regex = "^(\\s*\\}\\s*)|^(\\s*)$";
	// Expression for brackets
	String open_brackets = "\\{";
	String close_brackets = "\\}";

	public Cnc_java(ArrayList<String> lines) {
		this.lines = lines;
		CncUnits = new ArrayList<Integer>(lines.size());
	}

	public void addBracket() {
		brackets++;
	}

	public void removBracket() {
		if (brackets > 0) {
			brackets--;
		}
	}

	public void bracket_checker() {

		Pattern pattern = Pattern.compile(bracket_regex);
		Pattern single = Pattern.compile(singleline_regex);
		Pattern close_p = Pattern.compile(close_brackets);

		// Check each line for Cnc
		for (int i = 0; i < lines.size(); i++) {
			int count = 0;
			String line = lines.get(i);
			// check for conditions and loops
			Matcher matcher = pattern.matcher(line);
			while (matcher.find()) {
				addBracket();
			}
			count = brackets;
			// check for close brackets
			Matcher close_m = close_p.matcher(line);
			while (close_m.find()) {
				removBracket();
			}
			// check for lines with brackets or empty line
			Matcher singleline = single.matcher(line);
			if (singleline.find())
				count = 0;
			CncUnits.add(count);
		}
	}

	public ArrayList<Integer> getCnc() {
		bracket_checker();
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
