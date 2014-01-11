import java.util.*;

class test
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		/*float f = sc.nextFloat();
		int bits = Float.floatToIntBits(f);
		String bytearr = Integer.toBinaryString(bits);
		
		int nBits = Integer.parseInt(bytearr, 2);
		float toReturn = Float.intBitsToFloat(nBits);
*/
		String bytearr = sc.nextLine();
		System.out.println("before\n"+bytearr);

		if(bytearr.charAt(1) == 0)
		{
			char[] aux = bytearr.toCharArray();
			aux[1] = '1';
			bytearr = String.valueOf(aux);
		}
		else
		{
			char[] aux = bytearr.toCharArray();
			aux[1] = '0';
			bytearr = String.valueOf(aux);
		}
		System.out.println("after\n"+bytearr);

	/*	System.out.println(f+"->"+
			bits+"->"+bytearr+"->"+bytearr.length() +
			"->"+ nBits+"->"+toReturn+" \nchar at 9"
			+bytearr.charAt(10));
	/*	float min = -1.00f;
		float max = 2.00f;

		for(int i =0; i<max*10; i++)
		{
			Random rand = new Random();
			float finalx = rand.nextFloat() * (max-min) + min;
			System.out.println(finalx);
		}*/
	}
}