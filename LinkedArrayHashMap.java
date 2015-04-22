import java.util.*;


public class LinkedArrayHashMap<K,V> extends LinkedHashMap<K,V>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * LinkedArrayHashMap overriding normal LinkedHashMap
	 */
	public LinkedArrayHashMap()
	{
		super();
	}

	/**
	 * Overrides the default contains value to use the Arrays class to find equality between array objects
	 */
	public boolean containsArrayKey(Object[] value) 
	{
		System.out.println("Got here");
		Set<K> keys = this.keySet();
		Iterator<K> it = keys.iterator();
		while(it.hasNext())
		{
			if(Arrays.deepEquals(value, (Object[])it.next()))
			{
				return true;
			}
		}
		return false;
	}
	
}
