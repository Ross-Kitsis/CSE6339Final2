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
		
		//Initialize List of profiles
		List<Profile> profiles = new ArrayList<Profile>();
		
		profiles.add(buildProfile("original", ngramSize, originalLocation, profileLength));
		
		Profile p = profiles.get(0);
		Map<String,Integer> m = p.getNgrams();
		for(String s:m.keySet())
		{
			System.out.println(s + "    " + m.get(s));
		}
		System.out.println(n.findProbable(p, profiles));
		/*
		File[] files = f.getFiles(originalLocation);
		try {
			System.out.println(files[0].getCanonicalPath());
			byte[] bytes = f.readFileToBytes(files[0]);
			
			List<String> bs = n.getBitStringNgramsFromByteArray(bytes, ngramSize);
			/*
			for(int i= 0; i < bytes.length; i++)
			{
				System.out.println(String.format("%8s", Integer.toBinaryString(bytes[i] & 0xFF)).replace(' ', '0'));
			}///////
			
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
		}*/
	}
	/**
	 * Creates a new profile based on the passed arguments
	 * @param identifier The identifier of the profile
	 * @param ngramSize The size of the ngrams
	 * @param fileLocation The location of the files to extract ngrams
	 * @param profileLength The length of the profile
	 * @return A profile containing the L most common ngrams
	 */
	public static Profile buildProfile(String identifier, int ngramSize, String fileLocation, int profileLength)
	{
		Profile p = new Profile(identifier,ngramSize);
		FileOps fileops = new FileOps();
		NgramOps nops = new NgramOps();
		
		//Get files in the passed location
		File[] f = fileops.getFiles(fileLocation);
		
		byte[] bytes;
		List<String> byteString;
		for(File file:f)
		{
			bytes = fileops.readFileToBytes(file);
			byteString = nops.getBitStringNgramsFromByteArray(bytes, ngramSize);
			p.addToProfile(byteString);
		}
		p.setProfileLength(profileLength);
		
		return p;
	}
}
