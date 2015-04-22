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
	public List<Byte[]> getNgramsFromByteArray(Byte[] bytes, int size)
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
	
}
