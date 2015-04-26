import java.io.*;
import java.util.*;



public class Driver 
{
	public static void main(String[] args)
	{
		//Parameters
		int ngramSize = 2;
		int profileLength = 100;
		
		//File locations for Marvin Gaye, Robin Thicke and Pharrel Williams
		String mGayeLocation = "";
		String rThickeLocation = "";
		String pWilliamsLocation = "";
		String originalLocation = "./src/original/lyrics";
		String infringeLocation = "";
		
		//Initialize file operations
		FileOps f = new FileOps();
		
		//Initialize n-gram operations
		NgramOps n = new NgramOps();
		
		File[] files = f.getFiles(originalLocation);
		try {
			System.out.println(files[0].getCanonicalPath());
			byte[] bytes = f.readFileToBytes(files[0]);
			
			List<String> bs = n.getBitStringNgramsFromByteArray(bytes, ngramSize);
			/*
			for(int i= 0; i < bytes.length; i++)
			{
				System.out.println(String.format("%8s", Integer.toBinaryString(bytes[i] & 0xFF)).replace(' ', '0'));
			}*/
			
			Profile p = new Profile("originalLyrics", ngramSize);
			p.addToProfile(bs);
			
			p.setProfileLength(profileLength);
			
			Map<String,Integer> temp = p.getNgrams();
			
			
			Set<String> k = temp.keySet();
			
			for(String s:k)
			{
				System.out.println(s + "   " + temp.get(s));
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
