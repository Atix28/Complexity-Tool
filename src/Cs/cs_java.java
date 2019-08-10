package Cs;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class cs_java {
	ArrayList<String> lines;
	ArrayList<Integer> CsUnit;

	// expressions for checking if conditions
	String keyword_regx = "\\b(?!public |static |else |else\\{|try |try\\{|return )[\\d\\w]+";

	public cs_java(ArrayList<String> lines) {
		this.lines = lines;
		CsUnit = new ArrayList<Integer>(lines.size());
	}

	public int if_count(String line) {

		Pattern pattern = Pattern.compile(keyword_regx);
		Matcher matcher = pattern.matcher(line);

		int count = 0;
		while (matcher.find()) {
			count++;
		}
		return count;
	}

	public void addtoArray() {
		for (int i = 0; i < lines.size(); i++) {
			CsUnit.add(if_count(lines.get(i)));
		}
	}

	public ArrayList<Integer> getCs() {
		addtoArray();
		return CsUnit;
	}

	// get total cs
	public int getTotalCs() {
		int total = 0;

		for (int i = 0; i < CsUnit.size(); i++) {
			total += CsUnit.get(i);
		}

		return total;
	}
}
