import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.io.FilenameUtils;
import Ctc.*;

public class Main {

	public static void main(String[] args) throws IOException {
		
		ArrayList<String> line = new ArrayList<>();
		String filename = "code.java";
		int count = 0;
		//End Member Declarations
		
				
		try {
			@SuppressWarnings("resource")
			FileReader file = new FileReader(filename);
			StringBuffer sb = new StringBuffer();
			while(file.ready()) 
			{
				char character = (char) file.read();
		        if (character == '\n') {
		            line.add(sb.toString());
		            sb = new StringBuffer();
		        } 
		        else
		        {
		            sb.append(character);
		        }
				
			}
			if (sb.length() < 0) {
		        line.add(sb.toString());
		    }
			System.out.println("============ The Result Array Size: "+line.size()+" ===================");
			
			//If condition series
			for(int i=0; i < line.size(); i++) 
			{
				
				System.out.println(line.get(i));
				count = count +1;
				
				
			}
			System.out.println("============== The Count: "+count+" ======================");
			//check for file extension
			String extension = FilenameUtils.getExtension(filename); 
			if (extension.matches("java")) 
			{
				System.out.println("-Java File Detected-");
				ctc_java ctc = new ctc_java(line);
				ArrayList <Integer> ctc_units = ctc.getCtc();
				for(int i=0;i<ctc_units.size();i++) {
					System.out.println(i+1 + " Line has " + ctc_units.get(i) + " if conditons");
				}
			}
			else if(extension.matches("cpp")) 
			{
				System.out.println("C++ File Detected");
			}
			else 
			{
				System.out.println("File Extension Not Supported");
			}
				
			
			
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		

	}

}
