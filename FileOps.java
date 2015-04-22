import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;


public class FileOps 
{
	
	public FileOps()
	{
		
	}
	/**
	 * 
	 * @param f The file to a byte array
	 * @return An array containing the bytes representing the read file
	 */
	public byte[] readFileToBytes(File f)
	{
		byte[] toReturn = null;
		Path p = Paths.get((f.getPath()));
		try {
			toReturn = Files.readAllBytes(p);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return toReturn;
	}
	/**
	 * Gets the list of files in the passed location
	 * @param Location to search
	 * @return An array of files in the location
	 */
	public File[] getFiles(String location)
	{
		File f = new File(location);
		File[] toReturn = null;
		//Get list of all files
		toReturn = f.listFiles();
		
		//Remove .DS files if present
		for(int i = 0; i < toReturn.length; i++)
		{
			if(toReturn[i].getAbsolutePath().contains(".DS_"))
			{
				toReturn[i] = null;
			}
		}
		
		List<File> bl = new ArrayList<File>();
		for(File fb:toReturn)
		{
			if(fb!=null)
			{
				bl.add(fb);
			}
		}
		
		toReturn = bl.toArray(new File[toReturn.length-1]);
		//.DS files removed
		
		return toReturn;
	}
	
	public String toString()
	{
		return "Test";
	}
}
