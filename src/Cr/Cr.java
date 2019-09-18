package Cr;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Measuring complexity due to Recursions
public class Cr {
	
	ArrayList<String> lines;
	ArrayList<Integer> Cr;

	String word = "";
	// Expression for finding function
	String function_regex = "(\\w+\\s+)(\\w+\\s*)\\((.*?)\\)\\s*\\{";
	// Expression for finding recursive
	String rec_regex = "\\b("+word+")\\b\\s*\\((.*?)\\)";

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
						word = matcher.group(2);
						startLine = i;
						bracket++;
						continue;
					}
					
					// if function founds
					if (!word.matches("")) {

						Pattern bracketOpenP = Pattern.compile(openBrackets);
						Matcher bracketOpenM = bracketOpenP.matcher(lines.get(i));

						Pattern bracketCloseP = Pattern.compile(closeBrackets);
						Matcher bracketCloseM = bracketCloseP.matcher(lines.get(i));
						
						// count brackets
						while (bracketOpenM.find()) {
							bracket++;
						}
						while (bracketCloseM.find()) {
							bracket--;
						}
						if (bracket == 1) {

							Pattern recP = Pattern.compile(rec_regex);
							Matcher recM = recP.matcher(lines.get(i));
							
							// find if recursive
							if (recM.find()) {
								endLine = i;
								for (int j = startLine; j <= endLine; j++) {
									int newCr = Cr.get(j) * 2;
									Cr.set(j, newCr);
								}
								bracket = 0;
								word = "";
							}
						} else if (bracket < 1) {
							bracket = 0;
							startLine = 0;
							endLine = 0;
							word = "";
						}
				}

			}
	}
	
	// output array of Cr
		public ArrayList<Integer> getCr() {
			calculateCr();
			return Cr;
		}
		
		public int getTotalCr() {
			int total = 0;

			for (int i = 0; i < Cr.size(); i++) {
				total += Cr.get(i);
			}

			return total;
		}
}
