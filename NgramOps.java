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
			for(int i = 0; i < size; i++)
			{
				sb.append(String.format("%8s", Integer.toBinaryString(bytes[i] & 0xFF)).replace(' ', '0'));
			}
			toReturn.add(sb.toString());
			sb.setLength(0);
		}
		
		return toReturn;
	}
	public String findProbable(Profile p, List<Profile> q)
	{
		String auth = "";
		return auth;
	}
	
}
