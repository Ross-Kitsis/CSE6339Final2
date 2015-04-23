import java.util.*;


public class Profile
{
	private String idenifier;
	private Map<String,Integer> ngrams = new LinkedHashMap<String,Integer>();
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
	public void addToProfile(List<String> allNgrams)
	{
		String toAdd;
		int n;
		for(int i = 0; i < ngrams.size(); i++)
		{
			toAdd = allNgrams.get(i);
			if(ngrams.containsKey(toAdd))
			{
				n = ngrams.get(i);
				ngrams.put(toAdd, n+1);
			}else
			{
				ngrams.put(toAdd, 1);
			}
		}
	}
	public String getIdenifier() {
		return idenifier;
	}
	public Map<String, Integer> getNgrams() {
		return ngrams;
	}
	public int getNgramSize() {
		return ngramSize;
	}
	/**
	 * Sets a profile to the Lth most popular ngrams
	 * @param length The number of n-grams to include
	 */
	public void setProfileLength(int length)
	{
		if(ngrams.size() < length)
		{
			return;
		}
		
		Map<String,Integer> temp = new LinkedHashMap<String,Integer>();
		
		int max = Integer.MIN_VALUE;
		String key = null;
		int v;
		
		for(int i = 0; i < length; i++)
		{
			Set<String> keys = ngrams.keySet();
			for(String s:keys)
			{
				v = ngrams.get(s);
				if(v > max)
				{
					max = v;
					key = s;
				}
			}
			temp.put(key, max);
			ngrams.remove(key);
			max = Integer.MAX_VALUE;
		}
		
		ngrams = temp;
	}
}
