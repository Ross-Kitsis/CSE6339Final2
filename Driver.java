import java.io.UnsupportedEncodingException;




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
		byte[] test3 = {0x61,0x61,0x10};
		
		String s = "a";
		try {
			s = new String(test3,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(s);
	}
}
