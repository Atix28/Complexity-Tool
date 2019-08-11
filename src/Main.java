import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.io.FilenameUtils;

import Cnc.Cnc_java;
import Cr.Cr;
import Cs.Cs_c;
import Cs.Cs_java;
import Ctc.*;

public class Main {

	public static void main(String[] args) throws IOException {

		ArrayList<String> line = new ArrayList<>();
		String filename = "code.java";
		int count = 0;
		// End Member Declarations

		try {
			@SuppressWarnings("resource")
			FileReader file = new FileReader(filename);
			StringBuffer sb = new StringBuffer();
			while (file.ready()) {
				char character = (char) file.read();
				if (character == '\n') {
					line.add(sb.toString());
					sb = new StringBuffer();
				} else {
					sb.append(character);
				}

			}
			if (sb.length() < 0) {
				line.add(sb.toString());
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
				ctc_java ctc = new ctc_java(line);
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
				System.out.println("\tTotal Ctc : " + Cs.getTotalCs());
				System.out.println("========== End of Cs check ==========");

				System.out.println("========== Starting Cnc check ==========");
				Cnc_java Cnc = new Cnc_java(line);
				ArrayList<Integer> Cnc_units = Cnc.getCnc();
				for (int i = 0; i < Cnc_units.size(); i++) {
					System.out.println("\t" + (i + 1) + " Line has " + Cnc_units.get(i) + " Cnc");
				}
				System.out.println("\tTotal Cnc : " + Cnc.getTotalCnc());
				System.out.println("========== End of Cnc check ==========");
				/*
				System.out.println("========== Starting Cr check ==========");
				Cr cr = new Cr(line,Cnc_units);
				ArrayList <Integer> Cr_units = cr.getCr();
				for(int i =0; i < Cr_units.size();i++) {
					System.out.println("\t" + (i + 1) + " Line has " + Cr_units.get(i) + " Cr");
				}
				System.out.println("\tTotal Cr : " + cr.getTotalCr());
				System.out.println("========== End of Cnc check ==========");
				*/
			} else if (extension.matches("cpp")) {
				System.out.println("C++ File Detected");
				System.out.println("========== Starting Cs check ==========");
				Cs_c Cs = new Cs_c(line);
				ArrayList<Integer> Cs_units = Cs.getCs();
				for (int i = 0; i < Cs_units.size(); i++) {
					System.out.println("\t" + (i + 1) + " Line has " + Cs_units.get(i) + " Cs");
				}
				System.out.println("\tTotal Ctc : " + Cs.getTotalCs());
				System.out.println("========== End of Cs check ==========");

			} else {
				System.out.println("File Extension Not Supported");
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
