


public class Driver 
{
	public static void main(String[] args)
	{
		//Parameters
		int ngramSize = 2;
		int profileLength = 1500;
		
		//File locations for Marvin Gaye, Robin Thicke and Pharrel Williams
		String mGayeLocation = "";
		String rThickeLocation = "";
		String pWilliamsLocation = "";
		String originalLocation = "";
		String infringLocation = "";
		
		//Initialize file operations
		FileOps f = new FileOps();
		
		//Initialize n-gram operations
		NgramOps n = new NgramOps();
		
		//Setup 
		LinkedArrayHashMap<Byte[],Integer> m = new LinkedArrayHashMap<Byte[],Integer>();
		Byte[] test = {0x8,0x9,0x10};
		Byte[] test2 = {0x10,0x11};
		Byte[] test3 = {0x8,0x9,0x10};
		m.put(test, 0);
		System.out.println(m.containsArrayKey(test2));
	}
}
