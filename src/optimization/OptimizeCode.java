package optimization;

import java.util.ArrayList;

public class OptimizeCode {
	
	String OriginalCode;
	ArrayList<String> Output;
	
	//Expressions for comments
	String fullCmt = "(\\/\\*[\\s\\S]*?\\*\\/)|((\\s*)\\/\\/.*)";
	
	
	public OptimizeCode(String code){
		OriginalCode = code;
	}
	
	public String RemoveCmts() {
		String out;
		
		out = OriginalCode.replaceAll(fullCmt, "");
		int f = out.indexOf("public");
		String ret = out.substring(f);
		
		return ret;
	}
	
	
}
