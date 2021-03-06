import java.io.*;
import java.util.*;



public class Driver 
{
	public static void main(String[] args)
	{
		//Parameters
		int ngramSize = 3;
		double profileLengthFactor = 0.2;
		
		/*
		
		//File locations for Marvin Gaye, Robin Thicke and Pharrel Williams (Lyrics)
		String mGayeLocation = "./src/mgaye/lyrics";
		String rThickeLocation = "./src/rthicke/lyrics";
		String pWilliamsLocation = "./src/pwilliams/lyrics";
		String originalLocation = "./src/original/lyrics";
		String infringeLocation = "./src/infringe/lyrics";
		
		*/
		
		//File locations for Marvin Gaye, Robin Thicke and Pharrel Williams (Music)
		//String mGayeLocation = "./src/mgaye/lyrics";
		String rThickeLocation = "/Users/rkitsis/Documents/CSE6339/Final/music/rthicke";
		String pWilliamsLocation = "/Users/rkitsis/Documents/CSE6339/Final/music/pwilliams";
		String originalLocation = "/Users/rkitsis/Documents/CSE6339/Final/music/original";
		String infringeLocation = "/Users/rkitsis/Documents/CSE6339/Final/music/infringe";
		
		
		//Initialize file operations
		FileOps f = new FileOps();
		
		//Initialize n-gram operations
		NgramOps n = new NgramOps();
		
		//Initialize List of profiles
		List<Profile> profiles = new ArrayList<Profile>();
		
		for(; ngramSize < 8 ; ngramSize ++)
		{
//			profiles.add(buildProfile("original", ngramSize, originalLocation));
//			profiles.add(buildProfile("infringe", ngramSize, infringeLocation));
//			profiles.add(buildProfile("rthicke", ngramSize, rThickeLocation));
//			profiles.add(buildProfile("pwilliams", ngramSize, pWilliamsLocation));
//			profiles.add(buildProfile("mgaye", ngramSize, mGayeLocation));
			
			profiles.add(buildProfileMap("original", ngramSize, originalLocation));
			profiles.add(buildProfileMap("infringe", ngramSize, infringeLocation));
			profiles.add(buildProfileMap("rthicke", ngramSize, rThickeLocation));
			profiles.add(buildProfileMap("pwilliams", ngramSize, pWilliamsLocation));
			
			
			
			for(int i = 5; i > 0; i--)
			{
//				int minLength = getMinProfileLength(profiles);
//				double factor = profileLengthFactor * i;
				
				int profileLength = 500 * i;
//				int profileLength = (int) (minLength * factor);
				
				adjustProfileLengths(profiles, profileLength);
				System.out.println("Profiles Built for " + ngramSize + " and length " + profileLength);
				//System.out.println("Length " + profileLength);
				Profile original = profiles.get(0);
				Profile infringe = profiles.get(1);
				Profile rthicke = profiles.get(2);
				Profile pwilliams = profiles.get(3);
//				Profile mgaye = profiles.get(4);

				//System.out.println(original.getNgrams().size());
				
				System.out.println("-------------------------------");
				
				//System.out.print(minLength+ "   ");
				//System.out.println(factor * i);
				
				System.out.println("ngram size: " + ngramSize);
				System.out.println("profile factor " + profileLengthFactor * i);
				System.out.println("Profile length  " + profileLength);

				System.out.println();
				
				System.out.println("CNG RThicke -- BL  " + n.runCNG(infringe, rthicke));
				System.out.println("CNG PWilliams -- BL  " + n.runCNG(infringe, pwilliams));
				System.out.println("CNG Original -- BL  " + n.runCNG(infringe, original));
//				System.out.println("CNG Original -- MGaye   " + n.runCNG(original, mgaye));
				
				/*
				double cng = n.runCNG(original, pwilliams);
				System.out.println(cng);*/
				
			}
			//Scanner input = new Scanner(System.in);
			//input.next();
			profiles.clear();

		}
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
			System.out.println(file.getAbsolutePath());
			bytes = fileops.readFileToBytes(file);
			byteString = nops.getBitStringNgramsFromByteArray(bytes, ngramSize);
			p.addToProfile(byteString);
		}
		//p.setProfileLength(profileLength);
		
		return p;
	}
	public static Profile buildProfileMap(String identifier, int ngramSize, String fileLocation)
	{
		Profile p = new Profile(identifier,ngramSize);
		FileOps fileops = new FileOps();
		NgramOps nops = new NgramOps();
		
		//Get files in the passed location
		File[] f = fileops.getFiles(fileLocation);
		
		byte[] bytes;
		Map<String,Integer> byteString;
		for(File file:f)
		{
			//System.out.println(file.getAbsolutePath());
			bytes = fileops.readFileToBytes(file);
			byteString = nops.getBitStringNgramsFromByteArrayMap(bytes, ngramSize);
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
	public static int getMinProfileLength(List<Profile> profiles)
	{
		int min = Integer.MAX_VALUE;
		for(int i = 0; i < profiles.size(); i++)
		{
			if(profiles.get(i).getNgrams().size() < min)
			{
				min = profiles.get(i).getNgrams().size();
			}
		}
		return min;
	}
}
