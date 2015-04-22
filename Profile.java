import java.util.*;


public class Profile
{
	private String idenifier;
	private LinkedArrayHashMap<Byte[],Integer> ngrams = new LinkedArrayHashMap<Byte[],Integer>();
	private int ngramSize;
	
	
	public Profile(String identifier, int ngramSize)
	{
		this.idenifier = identifier;
		this.ngramSize = ngramSize;
	}
	/**
	 * Builds a profile by traversing the list of bytes and filling a map with n-grams and the number of occurrences
	 * @param bytes
	 */
	public void buildProfile(List<Byte[]> bytes)
	{
		for(int i = 0; i < bytes.size(); i++)
		{
			Byte[] toAdd = bytes.get(i);
			if(!ngrams.containsArrayKey(toAdd))
			{
				ngrams.put(toAdd, 0);
			}else
			{
				
			}
		}
	}
}
