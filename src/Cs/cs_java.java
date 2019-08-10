package Cs;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class cs_java {
	ArrayList<String> lines;
	ArrayList<Integer> CsUnit;

	// expressions for checking if conditions
	String keyword_regx = "\\b(?!public |static |else |else\\{|try |try\\{|return )[\\d\\w]+";
	// Expression for checking the special words throw,throws,new,delete
	String special_reg = "\\b(new |delete |throw |throws )";

	public cs_java(ArrayList<String> lines) {
		this.lines = lines;
		CsUnit = new ArrayList<Integer>(lines.size());
	}

	//this method will count the keywords in the code 
	public int keyword_count(String line) {

		Pattern pattern = Pattern.compile(keyword_regx);
		Matcher matcher = pattern.matcher(line);

		int count = 0;
		while (matcher.find()) {
			count++;
		}
		
		return count;
	}
	public int special_count(String line) {

		Pattern pattern = Pattern.compile(special_reg);
		Matcher matcher = pattern.matcher(line);

		int count = 0;
		while (matcher.find()) {
			count = count + 2;
			
		}
		System.out.println(count);
		return count;
	}
	

	public void addtoArray() {
		for (int i = 0; i < lines.size(); i++) {
			int keyword_count = keyword_count(lines.get(i));
			int special_count = special_count(lines.get(i));
			CsUnit.add(keyword_count + special_count);
			
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
