package inputValidator;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatValidator {

	ArrayList<String> line = new ArrayList<>();

	// if condition existence check
	String if_regex = "\\b(if\\s*\\()";
	// if condition checker
	String if_format = "\\b(if\\s*\\(.+\\)\\s*\\{)";

	public FormatValidator(ArrayList<String> line) {
		this.line = line;
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

	// method for create an output exception
	public String makeException(int line, String exception) {
		switch (exception) {
		case "if":
			String if_ex = "Error in if Format in line " + line
					+ ". use following format.\nif (condition) {\nStatements }";
			return if_ex;
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
				String err = makeException(i + 1, exception);
				System.out.println(err);
				return false;
			}
			// create methods above and add more validations here ! - Sachira
		}
		String Clear = "File Validated ! No errors in the required format.";
		System.out.println(Clear);
		return true;
	}
}
