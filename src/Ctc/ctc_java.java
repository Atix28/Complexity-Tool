package Ctc;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ctc_java {

	ArrayList<String> lines;
	ArrayList<Integer> ctc_units;

	// expressions for checking if conditions
	String if_regex = "((?<!\\S)(if\\s*\\())";
	// expressions for checking logical conditions
	String logical_regex = "(\\|\\||\\&\\&)";
	// expressions for checking bitwise conditions
	String bitwise_regex = "(?<!\\|)(?<!\\&)(\\||\\&)(?!\\|)(?!\\&)";
	// expressions for checking iterative control structure
	String iterative_regex = "((?<!\\S)(while\\s*\\(.*\\)\\s*(?!\\s*;)|do\\s*\\{|do\\s*(?!.))|for\\s*\\()";
	// expressions for checking catch statements
	String catch_regex = "((?<!\\S)(catch\\s*\\())";
	// expressions for checking switch case conditions
	String case_regex = "((?<!\\S)(case\\s+.*\\:|default\\s*\\:))";
	// expressions for checking conditions of loop
	String loop_group_regex = "\\b(while|for)(\\s*\\(\\s*)(.+)(\\s*\\))";

	public ctc_java(ArrayList<String> lines) {
		this.lines = lines;
		ctc_units = new ArrayList<Integer>(lines.size());
	}

	// get Ctc due to if conditions
	public int if_count(String line) {

		Pattern pattern = Pattern.compile(if_regex);
		Matcher matcher = pattern.matcher(line);

		int count = 0;
		while (matcher.find()) {
			count++;
		}
		return count;
	}

	// get Ctc due to logical conditions
	public int logical_count(String line) {

		// check for conditions inside loops
		Pattern pattern_check = Pattern.compile(loop_group_regex);
		Matcher matcher_check = pattern_check.matcher(line);

		int increment = 1;

		if (matcher_check.find()) {
			// conditions inside a loop
			line = matcher_check.group(3);
			increment = 2;
		}

		Pattern pattern = Pattern.compile(logical_regex);
		Matcher matcher = pattern.matcher(line);

		int count = 0;
		while (matcher.find()) {
			count += increment;
		}
		return count;
	}

	// get Ctc due to bitwise conditions
	public int bitwise_count(String line) {

		// check for conditions inside loops
		Pattern pattern_check = Pattern.compile(loop_group_regex);
		Matcher matcher_check = pattern_check.matcher(line);

		int increment = 1;

		if (matcher_check.find()) {
			// conditions inside a loop
			line = matcher_check.group(3);
			increment = 2;
		}
		
		Pattern pattern = Pattern.compile(bitwise_regex);
		Matcher matcher = pattern.matcher(line);

		int count = 0;
		while (matcher.find()) {
			count += increment;
		}
		return count;
	}

	// get Ctc due to iterative statements
	public int iterative_count(String line) {

		Pattern pattern = Pattern.compile(iterative_regex);
		Matcher matcher = pattern.matcher(line);

		int count = 0;
		while (matcher.find()) {
			count += 2;
		}
		return count;
	}

	// get Ctc due to catch statements
	public int catch_count(String line) {

		Pattern pattern = Pattern.compile(catch_regex);
		Matcher matcher = pattern.matcher(line);

		int count = 0;
		while (matcher.find()) {
			count++;
		}
		return count;
	}

	// get Ctc due to case statements
	public int case_count(String line) {

		Pattern pattern = Pattern.compile(case_regex);
		Matcher matcher = pattern.matcher(line);

		int count = 0;
		while (matcher.find()) {
			count++;
		}
		return count;
	}

	// calculate ctc
	public void addtoArray() {
		for (int i = 0; i < lines.size(); i++) {

			// get Ctc for each line
			int if_count = if_count(lines.get(i));
			int logical_count = logical_count(lines.get(i));
			int bitwise_count = bitwise_count(lines.get(i));
			int iterative_count = iterative_count(lines.get(i));
			int catch_count = catch_count(lines.get(i));
			int case_count = case_count(lines.get(i));

			// get total count for Ctc
			ctc_units.add(if_count + logical_count + bitwise_count + iterative_count + catch_count + case_count);
		}
	}

	// get Ctc output as an array
	public ArrayList<Integer> getCtc() {
		addtoArray();
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
