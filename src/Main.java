import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.commons.io.FilenameUtils;

public class Main {

	public static void main(String[] args) throws IOException {
		
		ArrayList<String> result = new ArrayList<>();
		String filename = "code.java";
		int count = 0;
		
		try {
			@SuppressWarnings("resource")
			FileReader file = new FileReader(filename);
			StringBuffer sb = new StringBuffer();
			while(file.ready()) 
			{
				char character = (char) file.read();
		        if (character == '\n') {
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
			System.out.println("============ The Result Array Size: "+result.size()+" ===================");
			
			//If condition series
			for(int i=0; i < result.size(); i++) 
			{
				
				System.out.println(result.get(i));
				count = count +1;
				
				
			}
			System.out.println("============== The Count: "+count+" ======================");
			//check for file extension
			String extension = FilenameUtils.getExtension(filename); 
			if( extension == "java" ) 
			{
				
			}
			else if(extension == "cpp") 
			{
				
			}
			
			
			
			
			
			
			
			
			
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		
		

	}

}
