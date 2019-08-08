import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {

	public static void main(String[] args) throws IOException {
		ArrayList<String> result = new ArrayList<>();
		String filename = "code.java";
		try {
			@SuppressWarnings("resource")
			FileReader file = new FileReader(filename);
			StringBuffer sb = new StringBuffer();
			while(file.ready()) 
			{
				char character = (char) file.read();
		        if (character == ' ') {
		            result.add(sb.toString());
		            sb = new StringBuffer();
		        } 
		        else
		        {
		            sb.append(character);
		        }
				
			}
			if (sb.length() < 0) {
		        result.add(sb.toString());
		    }
			
			//If condition series
			for(int i=0; i < result.size(); i++) 
			{
				System.out.println(result.get(i)+"/");
			}
			
			
			
			
			
			
			
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		

	}

}
