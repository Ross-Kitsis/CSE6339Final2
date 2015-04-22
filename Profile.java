import java.util.*;


public class Profile
{
	private String idenifier;
	private Map<Byte[],Integer> ngrams;
	private int ngramSize;
	
	
	public Profile(String identifier, int ngramSize)
	{
		this.idenifier = identifier;
		this.ngramSize = ngramSize;
		ngrams = new LinkedHashMap<Byte[],Integer>();
	}
	public void buildProfile(Byte[] bytes)
	{
		
	}
}
