import java.util.*;
import java.io.*;

class Individ
{
	public String strADN;
	public int nFitness = 0;

	public void generateIndivid()
	{
		float min = -1;
		float max = 2;

		Random rand = new Random();
		float f = rand.nextFloat() * (max-min) + min;
		this.strADN = this.encode(f);
	}

	public int getFitness()
	{
		if(nFitness == 0)
		{
			nFitness = FitnessCalc.getFitness(this);
		}
	}

	public void setGene(int nIndex, char value)
	{
		char aux[] = this.strADN.toCharArray();
		aux[nIndex] = value;
		this.strADN = String.valueOf(aux);
	}

	public char getGene(int nIndex)
	{
		char[] aux = this.strADN.toCharArray();
		return aux[nIndex];
	}

	public float decode(String bits)
	{
		int nBits = Integer.parseInt(bits, 2);
		float toReturn = Float.intBitsToFloat(nBits);
		return toReturn;
	}

	public String encode(float f)
	{
		int bits = Float.floatToIntBits(f);
		String strToReturn = Integer.toBinaryString(bits);
		return strToReturn;
	}

}




class FitnessCalc
{
	public static String strTarget;

	public static void setTarget(String newSol)
	{
		strTarget = newSol;
	}

	public static char getTargetChar(int nIndex)
	{
		char[] aux = strTarget.toCharArray(strTarget);
		return aux[nIndex];
	}

	static int getFitness(Individ dorel)
	{
		int fitness = 0;
		for(int i=0; i < strTarget.lenght() &&
			i<dorel.strADN.lenght(); i++)
		{
			if(dorel.getGene[i] = getTargetChar(i))
			{
				fitness++;
			}
		}
		return fitness;
	}
}





class Populatie
{
	public Individ[] indivizi;

	public Populatie(int nSize, boolean bInit)
	{
		indivizi = new Individ[nSize];
		if(bInit)
		{
			for (int i=0; i<nSize; i++)
			{
				indivizi[i].generateIndivid();
			}
		}
	}

	public Individ getFittest()
	{
		Individ objDorel = indivizi[0];

		for(int i =0; i< indivizi.lenght; i++)
		{
			if(objDorel.nFitness < indivizi[i].nFitness)
			{
				objDorel = indivizi[i];
			}
		}
		return objDorel;
	}
}

public class Algoritm
{
	
}