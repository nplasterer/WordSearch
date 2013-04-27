package WordSearch;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileUtilities {
	
	public static String getDataDirectoryFullPath(String dataDirectory) throws IOException {
		String currentPath = new java.io.File(".").getCanonicalPath(); // Get current path
		return currentPath + File.separatorChar + dataDirectory;  // Add on name of data directory
	}
	
	public static String getFileFullPath(String dataDirectory, String fileName) throws IOException {
		String currentPath = new java.io.File(".").getCanonicalPath() + File.separatorChar + dataDirectory;
		return currentPath + File.separatorChar + fileName ;  
	}
	
	public static ArrayList<String> getListOfLinesFromFile(String dataDirectory, String inputFile) {
		FileReader fileReader = null;
		String dataPath = "";
		ArrayList<String> result = new ArrayList<String>();
		
		try {
			dataPath =  getDataDirectoryFullPath(dataDirectory);
			
			fileReader = new FileReader(dataPath + File.separatorChar + inputFile);
		} 	catch (FileNotFoundException e) {
			System.out.println("Unable to load file: " + dataPath + File.separatorChar + inputFile);
			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Unable to get path: " + dataPath + File.separatorChar + inputFile);
			System.exit(1);
		}

		Scanner in = new Scanner(fileReader);
		
		String line = " ";
		while (in.hasNext()) {
			line = in.nextLine();
			result.add(line);			
		}
		
		return result;		
	}
	
	public static ArrayList<String> getListOfFiles(String dataDirectory, String onlyLookAtExtension, boolean removeExtension) {		
		ArrayList<String> results = new ArrayList<String>();
		String dataPath = "";
				
		try {
			dataPath = getDataDirectoryFullPath(dataDirectory); // Get data path

			// This snippet was inspired by Sean Kleinjung and follows 
			//  closely to his code, with some code added to see if the user wanted an extension.
			//  From http://stackoverflow.com/questions/5694385/getting-the-filenames-of-all-files-in-a-folder
			File[] files = new File(dataPath).listFiles();

			for (File file : files) {
			    if (file.isFile()) {
			    	if (removeExtension && onlyLookAtExtension != "") { 
			    		if (file.getName().substring(file.getName().lastIndexOf(onlyLookAtExtension)).equalsIgnoreCase(onlyLookAtExtension)) {
			    			results.add(file.getName().substring(0,file.getName().lastIndexOf(onlyLookAtExtension)));
			    		}
			    	} else {
			    		results.add(file.getName());
			    	}
			    }
			}
			//End snippet
			return results;			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Unable to find path.");
			System.exit(2);
		}
		return null;
	}
	
	public static ArrayList<String> getListOfFilesByCategory(String dataDirectory, String category) {		
		ArrayList<String> results = new ArrayList<String>();
		String dataPath = "";
				
		try {
			dataPath = getDataDirectoryFullPath(dataDirectory); // Get data path

			// This snippet was inspired by Sean Kleinjung and follows 
			//  closely to his code, with some code added to see if the user wanted an extension.
			//  From http://stackoverflow.com/questions/5694385/getting-the-filenames-of-all-files-in-a-folder
			File[] files = new File(dataPath).listFiles();

			for (File file : files) {
			    if (file.isFile()) {
			    	if (file.getName().contains("."+category+".")) { 
			    	 		results.add(file.getName());
			    	}
			    }
			}
			//End snippet
			return results;			
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Unable to find path.");
			System.exit(2);
		}
		return null;
	}

}
