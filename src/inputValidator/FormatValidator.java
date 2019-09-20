package inputValidator;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatValidator {

	ArrayList<String> line = new ArrayList<>();
        String output;
	// if condition existence check
	String if_regex = "\\b(if\\s*\\()";
	// if condition checker
	String if_format = "\\b(if\\s*\\(.+\\)\\s*\\{)";
	// while condition existence check
	String while_regex = "\\b(while\\s*\\()";
	// while condition checker
	String while_format = "\\b(while\\s*\\(.+\\)\\s*(\\{|\\;))";
	// for condition existence check
	String for_regex = "\\b(for\\s*\\()";
	// for condition checker
	String for_format = "\\b(for\\s*\\(.+\\)\\s*\\{)";
	
	//ignore lines till class
	String ignore_regex = "\\b(class)\\b\\s+\\w+";

	public FormatValidator(ArrayList<String> line) {
		this.line = line;
	}
	
	public int ignoreTill() {
		int lineno = 0;
		Pattern pattern = Pattern.compile(if_regex);
		for (int i = 0; i < line.size(); i++) {
		Matcher matcher = pattern.matcher(line.get(i));
		if(matcher.find()) {
			return i;
		}
		}
		
		return lineno;
	}

	// if condition validate method
	public boolean ifCondtionValidator(String line) {

		Pattern if_pattern = Pattern.compile(if_regex);
		Pattern format_pattern = Pattern.compile(if_format);

		Matcher if_matcher = if_pattern.matcher(line);
		Matcher format_matcher = format_pattern.matcher(line);

		// checks for if condition and if found then check for the format
		if (if_matcher.find()) {
			if (format_matcher.find()) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}
	public boolean forCondtionValidator(String line) {

		Pattern for_pattern = Pattern.compile(for_regex);
		Pattern format_pattern = Pattern.compile(for_format);

		Matcher if_matcher = for_pattern.matcher(line);
		Matcher format_matcher = format_pattern.matcher(line);

		// checks for if condition and if found then check for the format
		if (if_matcher.find()) {
			if (format_matcher.find()) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}
	public boolean whileCondtionValidator(String line) {

		Pattern while_pattern = Pattern.compile(while_regex);
		Pattern format_pattern = Pattern.compile(while_format);

		Matcher if_matcher = while_pattern.matcher(line);
		Matcher format_matcher = format_pattern.matcher(line);

		// checks for if condition and if found then check for the format
		if (if_matcher.find()) {
			if (format_matcher.find()) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	// method for create an output exception
	public String makeException(int line, String exception) {
		switch (exception) {
		case "if":
			String if_ex = "Error in if Format in line " + line
					+ ". use the following format.\nif (condition) {\nStatements }";
			return if_ex;
		case "while":
			String while_ex = "Error in while Format in line " + line
					+ ". use the following format.\nwhile (condition) {\nStatements }";
			return while_ex;
		case "for":
			String for_ex = "Error in for Format in line " + line
					+ ". use the following format.\nfor (condition) {\nStatements }";
			return for_ex;
		default:
			String def_ex = "Unknown Error in line " + line;
			return def_ex;
		}
	}

	// main method of the class
	public Boolean runValidator() {

		// checks line by line
		for (int i = 0; i < line.size(); i++) {
			
			if (!ifCondtionValidator(line.get(i))) {
				String exception = "if";
				output = makeException(i + 1, exception);
				return false;
			}
			if (!forCondtionValidator(line.get(i))) {
				String exception = "for";
				output = makeException(i + 1, exception);
				return false;
			}
			if (!whileCondtionValidator(line.get(i))) {
				String exception = "while";
				output = makeException(i + 1, exception);
				return false;
			}
			// create methods above and add more validations here ! - Sachira
		}
                output = "File Validated ! No errors in the required format.";
		return true;
	}
        
        public String getOutput(){
            return output;
        }
}
