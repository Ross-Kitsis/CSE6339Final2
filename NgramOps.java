import java.util.*;

public class NgramOps 
{
	public NgramOps()
	{
		
	}
	/**
	 * 
	 * @param bytes Any array containing the bytes to extract n-grams
	 * @param size The size of the n-grams to extract
	 * @return A list containing byte arrays; each byte array is an extracted n-gram
	 */
	public List<Byte[]> getNgramsFromByteArray(byte[] bytes, int size)
	{
		List<Byte[]> toReturn = new ArrayList<Byte[]>();
		
		for(int start = 0; start < bytes.length - size; start++)
		{
			Byte[] toAdd = new Byte[size];
			for(int i = 0; i < size;i++)
			{
				toAdd[i] = bytes[i + start];
			}
			toReturn.add(toAdd);
		}
		
		return toReturn;
	}
	/**
	 * Extracts byte n-grams from the passed array, converts them to bit string and 
	 * adds them to a list
	 * 
	 * @param bytes An array from where to extract n-grams
	 * @param size The size of the n-grams to extract
	 * @return A list containing the n-grams extracted
	 */
	public List<String> getBitStringNgramsFromByteArray(byte[] bytes, int size)
	{
		List<String> toReturn = new ArrayList<String>();
		StringBuilder sb = new StringBuilder();
		
		for(int start = 0; start < bytes.length - size; start++)
		{
			for(int i = start; i < start + size; i++)
			{
				sb.append(String.format("%8s", Integer.toBinaryString(bytes[i] & 0xFF)).replace(' ', '0'));
			}
			toReturn.add(sb.toString());
			sb.setLength(0);
		}
		
		return toReturn;
	}
	/**
	 * Finds the most related profile in the list of profiles
	 * @param p The profile to compare to
	 * @param q A list of profiles to test against
	 * @return The identifier of the profile most similar
	 */
	public String findProbable(Profile p, List<Profile> q)
	{
		String auth = "";
		double min = Integer.MAX_VALUE;
		double temp;
		
		for(int i = 0; i < q.size(); i++)
		{
			System.out.println("Running with profile: " + q.get(i).getIdenifier());
			temp = runCNG(p,q.get(i));
			if(temp < min)
			{
				min = temp;
				auth = q.get(i).getIdenifier();
			}
		}
		
		return auth + " " + min;
	}
	/**
	 * Return a set containing the union of the 2 passed sets
	 * @param s1 Set 1
	 * @param s2 Set 2
	 * @return A set containing the union of the 2 sets
	 */
	public Set<String> getUnion(Set<String> s1, Set<String> s2)
	{
		Set<String> toReturn = new HashSet<String>();
		toReturn.addAll(s1);
		toReturn.addAll(s2);
		return toReturn;
	}
	/**
	 * Runs the CNG aglorithm on 2 profiles
	 * @param p1 Profile 1
	 * @param p2 Profile 2
	 * @return The dissimilarity between the 2 profiles
	 */
	public double runCNG(Profile p1, Profile p2)
	{
		double toReturn = Integer.MAX_VALUE;
		
		Map<String,Integer> np1 = p1.getNgrams();
		Map<String,Integer> np2 = p2.getNgrams();
		
		Set<String> s1 = np1.keySet();
		Set<String> s2 = np2.keySet();
		
		Set<String> u = getUnion(s1,s2);
		
		Integer vp1;
		Integer vp2;
		
		for(String s:u)
		{
			vp1 = np1.get(s);
			if(vp1 == null)
			{
				vp1 = 0;
			}
			
			vp2 = np2.get(s);
			if(vp2 == null)
			{
				vp2 = 0;
			}
			
			toReturn = toReturn + Math.pow(((2 * (vp1 - (double) vp2))/(double)(vp1+vp2)),2);
		}
		
		return toReturn;
	}
	
}
