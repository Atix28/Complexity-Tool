import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.apache.commons.io.FilenameUtils;

import Cnc.Cnc;
import Cps.Cps_C;
import Cps.Cps_java;
import Cr.Cr_java;
import Cs.Cs_c;
import Cs.Cs_java;
import Ctc.*;
import Tw.Tw_C;
import Tw.Tw_java;
import Ci.*;
import inputValidator.FormatValidator;
import optimization.OptimizeCode;

public class Main {

	public static void main(String[] args) throws IOException {

		ArrayList<String> line = new ArrayList<>();
		String filename = "code.java";
		int count = 0;
		// End Member Declarations

		try {
			InputStream is = new FileInputStream(filename); 
			BufferedReader buf = new BufferedReader(new InputStreamReader(is)); 
			String code = buf.readLine(); 
			StringBuilder sbuilder = new StringBuilder(); 
			while(code != null){ 
				sbuilder.append(code).append("\n"); 
				code = buf.readLine(); } 
			String fileAsString = sbuilder.toString(); 
			OptimizeCode OptCode = new OptimizeCode(fileAsString);
			String optimizedcode = OptCode.RemoveCmts();
			System.out.println("Optimized Code : \n" + optimizedcode);
			
			String[] lines = optimizedcode.split("\\r?\\n");
	        for (String l : lines) {
	            line.add(l);
	        }
			
//			@SuppressWarnings("resource")
//			FileReader file = new FileReader(filename);
//			StringBuffer sb = new StringBuffer();
//			while (file.ready()) {
//				char character = (char) file.read();
//				if (character == '\n') {
//					line.add(sb.toString());
//					sb = new StringBuffer();
//				} else {
//					sb.append(character);
//				}
//
//			}
//			if (sb.length() < 0) {
//				line.add(sb.toString());
//			}
			System.out.println("============ Validating " + line.size() + " Lines of Code ===================");
			FormatValidator validator = new FormatValidator(line);
			if(!validator.runValidator()) {
				System.out.println(validator.getOutput());
				return;
			}
			System.out.println("============ The Result Array Size: " + line.size() + " ===================");

			// If condition series
			for (int i = 0; i < line.size(); i++) {

				System.out.println(line.get(i));
				count = count + 1;

			}
			System.out.println("============== The Count: " + count + " ======================");
			// check for file extension
			String extension = FilenameUtils.getExtension(filename);
			if (extension.matches("java")) {
				System.out.println("-Java File Detected-");
				System.out.println("========== Starting Ctc check ==========");
				Ctc ctc = new Ctc(line);
				ArrayList<Integer> ctc_units = ctc.getCtc();
				for (int i = 0; i < ctc_units.size(); i++) {
					System.out.println("\t" + (1 + i) + " Line has " + ctc_units.get(i) + " Ctc");
				}
				System.out.println("\tTotal Ctc : " + ctc.getTotalCtc());
				System.out.println("========== End of Ctc check ==========");

				System.out.println("========== Starting Cs check ==========");
				Cs_java Cs = new Cs_java(line);
				ArrayList<Integer> Cs_units = Cs.getCs();
				for (int i = 0; i < Cs_units.size(); i++) {
					System.out.println("\t" + (i + 1) + " Line has " + Cs_units.get(i) + " Cs");
				}
				System.out.println("\tTotal Cs : " + Cs.getTotalCs());
				System.out.println("========== End of Cs check ==========");

				System.out.println("========== Starting Cnc check ==========");
				Cnc Cnc = new Cnc(line);
				ArrayList<Integer> Cnc_units = Cnc.getCnc();
				for (int i = 0; i < Cnc_units.size(); i++) {
					System.out.println("\t" + (i + 1) + " Line has " + Cnc_units.get(i) + " Cnc");
				}
				System.out.println("\tTotal Cnc : " + Cnc.getTotalCnc());
				System.out.println("========== End of Cnc check ==========");
				
				System.out.println("========== Starting Ci check ==========");
				Ci_java ci = new Ci_java(line);
				ArrayList<Integer> Ci_units = ci.getCi();
				for (int i = 0; i < Ci_units.size(); i++) {
					System.out.println("\t" + (1 + i) + " Line has " + Ci_units.get(i) + " Ci");
				}
				System.out.println("\tTotal Ci : " + ci.getTotalCi());
				System.out.println("========== End of Ci check ==========");
				
				System.out.println("========== Starting Tw check ==========");
				Tw_java Tw = new Tw_java(line);
				ArrayList<Integer> Tw_units = Tw.getTw();
				for (int i = 0; i < Tw_units.size(); i++) {
					System.out.println("\t" + (1 + i) + " Line has " + Tw_units.get(i) + " Tw");
				}
				System.out.println("\tTotal Tw : " + Tw.getTotalTw() );
				System.out.println("========== End of Tw check ==========");
				
				System.out.println("========== Starting Cps check ==========");
				Cps_java Cps = new Cps_java(line);
				ArrayList<Integer> Cps_units = Cps.getCps();
				for (int i = 0; i < Cps_units.size(); i++) {
					System.out.println("\t" + (1 + i) + " Line has " + Cps_units.get(i) + " Cps");
				}
				System.out.println("\tTotal Cps : " + Cps.getTotalCps() );
				System.out.println("========== End of Cps check ==========");
				System.out.println("========== Starting Cr check ==========");
				Cr_java Cr = new Cr_java(line);
				ArrayList<Integer> Cr_units = Cr.getCr();
				for (int i = 0; i < Cr_units.size(); i++) {
					System.out.println("\t" + (1 + i) + " Line has " + Cr_units.get(i) + " Cr");
				}
				System.out.println("\tTotal Cr : " + Cr.getTotalCr() );
				System.out.println("========== End of Cr check ==========");
				
//				System.out.println("========== Starting Cr check ==========");
//				Cr cr = new Cr(line,Cnc_units);
//				ArrayList <Integer> Cr_units = cr.getCr();
//				for(int i =0; i < Cr_units.size();i++) {
//					System.out.println("\t" + (i + 1) + " Line has " + Cr_units.get(i) + " Cr");
//				}
//				System.out.println("\tTotal Cr : " + cr.getTotalCr());
//				System.out.println("========== End of Cnc check ==========");
				
			} else if (extension.matches("cpp")) {
				System.out.println("C++ File Detected");
				System.out.println("========== Starting Ctc check ==========");
				Ctc ctc = new Ctc(line);
				ArrayList<Integer> ctc_units = ctc.getCtc();
				for (int i = 0; i < ctc_units.size(); i++) {
					System.out.println("\t" + (1 + i) + " Line has " + ctc_units.get(i) + " Ctc");
				}
				System.out.println("\tTotal Ctc : " + ctc.getTotalCtc());
				System.out.println("========== End of Ctc check ==========");

				
				
				
				
				System.out.println("========== Starting Cs check ==========");
				Cs_c Cs = new Cs_c(line);
				ArrayList<Integer> Cs_units = Cs.getCs();
				for (int i = 0; i < Cs_units.size(); i++) {
					System.out.println("\t" + (i + 1) + " Line has " + Cs_units.get(i) + " Cs");
				}
				System.out.println("\tTotal Ctc : " + Cs.getTotalCs());
				System.out.println("========== End of Cs check ==========");

				
				System.out.println("========== Starting Cnc check ==========");
				Cnc Cnc = new Cnc(line);
				ArrayList<Integer> Cnc_units = Cnc.getCnc();
				for (int i = 0; i < Cnc_units.size(); i++) {
					System.out.println("\t" + (i + 1) + " Line has " + Cnc_units.get(i) + " Cnc");
				}
				System.out.println("\tTotal Cnc : " + Cnc.getTotalCnc());
				System.out.println("========== End of Cnc check ==========");
				
				System.out.println("========== Starting Ci check ==========");
				Ci_C ci = new Ci_C(line);
				ArrayList<Integer> Ci_units = ci.getCi();
				for (int i = 0; i < Ci_units.size(); i++) {
					System.out.println("\t" + (1 + i) + " Line has " + Ci_units.get(i) + " Ci");
				}
				System.out.println("\tTotal Ci : " + ci.getTotalCi());
				System.out.println("========== End of Ci check ==========");
				
				System.out.println("========== Starting Tw check ==========");
				Tw_C Tw = new Tw_C(line);
				ArrayList<Integer> Tw_units = Tw.getTw();
				for (int i = 0; i < Tw_units.size(); i++) {
					System.out.println("\t" + (1 + i) + " Line has " + Tw_units.get(i) + " Tw");
				}
				System.out.println("\tTotal Tw : " + Tw.getTotalTw() );
				System.out.println("========== End of Tw check ==========");
				
				System.out.println("========== Starting Cps check ==========");
				Cps_C Cps = new Cps_C(line);
				ArrayList<Integer> Cps_units = Cps.getCps();
				for (int i = 0; i < Cps_units.size(); i++) {
					System.out.println("\t" + (1 + i) + " Line has " + Cps_units.get(i) + " Cps");
				}
				System.out.println("\tTotal Cps : " + Cps.getTotalCps() );
				System.out.println("========== End of Cps check ==========");
				
				
			} else {
				System.out.println("File Extension Not Supported");
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
