import java.io.*;
import java.util.*;



public class Driver 
{
	public static void main(String[] args)
	{
		//Parameters
		int ngramSize = 2;
		double profileLengthFactor = 0.2;
		
		//File locations for Marvin Gaye, Robin Thicke and Pharrel Williams
		String mGayeLocation = "./src/mgaye/lyrics";
		String rThickeLocation = "./src/rthicke/lyrics";
		String pWilliamsLocation = "./src/pwilliams/lyrics";
		String originalLocation = "./src/original/lyrics";
		String infringeLocation = "./src/infringe/lyrics";
		
		//Initialize file operations
		FileOps f = new FileOps();
		
		//Initialize n-gram operations
		NgramOps n = new NgramOps();
		
		//Initialize List of profiles
		List<Profile> profiles = new ArrayList<Profile>();
		
		for(; ngramSize < 3 ; ngramSize ++)
		{
			profiles.add(buildProfile("original", ngramSize, originalLocation));
			profiles.add(buildProfile("infringe", ngramSize, infringeLocation));
			profiles.add(buildProfile("rthicke", ngramSize, rThickeLocation));
			profiles.add(buildProfile("pwilliams", ngramSize, pWilliamsLocation));
			profiles.add(buildProfile("mgaye", ngramSize, mGayeLocation));
			
			for(int i = 1; i < 2; i++)
			{
				int profileLength =(int) (profiles.get(0).getNgrams().size() * (profileLengthFactor * i));
				adjustProfileLengths(profiles, profileLength);
				System.out.println("Length " + profileLength);

				Profile original = profiles.get(0);
				Profile infringe = profiles.get(1);
				Profile rthicke = profiles.get(2);
				Profile pwilliams = profiles.get(3);
				Profile mgaye = profiles.get(4);

				System.out.println(original.getNgrams().size());


				double cng = n.runCNG(original, pwilliams);
				System.out.println(cng);
			}

		}
		/*
		Profile p = profiles.get(4);
		Map<String,Integer> m = p.getNgrams();
		for(String s:m.keySet())
		{
			System.out.println(s + "    " + m.get(s));
		}
		System.out.println(n.findProbable(p, profiles));
		*/
	}
	/**
	 * Creates a new profile based on the passed arguments
	 * @param identifier The identifier of the profile
	 * @param ngramSize The size of the ngrams
	 * @param fileLocation The location of the files to extract ngrams
	 * @param profileLength The length of the profile
	 * @return A profile containing the L most common ngrams
	 */
	public static Profile buildProfile(String identifier, int ngramSize, String fileLocation)
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
		//p.setProfileLength(profileLength);
		
		return p;
	}
	public static void adjustProfileLengths(List<Profile> profiles, int profileLength)
	{
		for(Profile p:profiles)
		{
			p.setProfileLength(profileLength);
		}
	}
}
