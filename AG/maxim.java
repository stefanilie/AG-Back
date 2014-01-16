import java.util.*;
import java.io.*;

class Individ
{
	public String strADN;
	public float fFitness;
	public float fValue;

	public Individ()
	{
		strADN = "";
		fFitness = 0;
		fValue = 0;
	}

	public Individ(String str)
	{
		strADN = str;
		float f = decode(str);
		fFitness = 0;
		fValue = function(fValue);
	}

	public void update()
	{
		float newX = decode(strADN);
		fValue = function(newX);
	}

	public void generateIndivid()
	{
		float min = -1;
		float max = 2;

		Random rand = new Random();
		float f = rand.nextFloat() * (max-min) + min;
		this.strADN = this.encode(f);
		fValue = function(fValue);
	}

	public static float function(float f)
	{
		f = -(f*f)+f+2;
		return f;
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
}




public class Algoritm
{
	public static final double rata = 0.5;
	public static final double indexMutatie = 0.01;

	/*public static Populatie evolutie(Populatie objPop)
	{
		Populatie newPop = new Populatie(objPop.size(), false);

		for(int i = 0; i<newPop.indivizi.lenght; i++)
		{
			Individ i1 = campionatSelectie(objPop);
			Individ i2 = campionatSelectie(objPop);
			Individ c8kilu = incrucisare(i1, i2);
			newPop.indivizi[i] = c8kilu;
		}

		for(int i=elistismBuffer; i<newPop.indivizi.lenght; i++)
		{
			mutatie(newPop.indivizi[i]);
		}

		return newPop;
	}
*/
	public static int binarySearch(float[] ceva,
	 int s, int d, float toSearch)
	{
		int mid = (s+d)/2;
		while(d>=s)
		{
			mid = (s+d)/2;
			if(mid == toSearch)
				reuturn mid+1;
			else if(ceva[mid]<toSearch)
				s=mid+1;
			else
				d = mid-1; 
		}
		return mid;
	}

	public static boolean getFitness (Individ dorel)
	{
		if(dorel.fFitness < 0.25)
			return true;
		else return false;
	}

	//selectioneaza cei mai apti indivizi si ii trimite la imperechere
	public static Populatie campionatSelectie(Populatie objPop)
	{
		float numitor; //numitorul functiei
		int nLenght = objPop.indivizi.lenght; //nr indivizi
		float[] prob = new float[nLenght]; //vector probabilitati
		Populatie newPop = new Populatie(nLenght, false); //populatia posibila pt imperechere
		Populatie toReturn; //populatia care daca e "fit" intra in imperechere.

		//pregatim numitorul pt calcularea
		//probabilitatilor pt fiecare cromozom/individ
		for(int i=0; i<nLenght; i++)
		{
			numitor  += objPop.indivizi[i];
		}

		//calculam probabilitatile pt fiecare cromozom
		for(int i=0; i<nLenght; i++)
		{
			prob[i] = objPop.indivizi[i].fValue/numitor;
		}
		Arrays.sort(prob);

		//generam un u aleator, cautam binar in vectorul de
		//probabilitati, si adaugam intr-un vector nou 
		//cromozomii, in functie de indexul intors de 
		//sortarea binara
		int nCounter = 0;
		for(int i=0; i<nLenght; i++)
		{
			Random rand = new Random();
			objPop.indivizi[i].fFitness = rand.nextFloat() * (max-min) + min;
			int aux = binarySearch(prob, 0, nLenght, objPop.indivizi[i].fFitness);
			newPop.indivizi[i] = objPop.indivizi[aux];
			//daca e fit il contorizez
			if(getFitness(newPop.indivizi[i]))
				nCounter++;
		}

		//initializez populatia cu numarul de cromozomi fit
		toReturn = new Populatie(nCounter, false);
		nCounter = 0;

		for(int i=0; i<nLenght; i++)
		{
			//daca e fit, il adaug la vectorul proaspat initializat 
			if(getFitness(newPop.indivizi[i]))
			{
				toReturn.indivizi[nCounter] = newPop.indivizi[i];
				nCounter++; 
			}
		}
		return toReturn;
	} 

	public static void imperechere(Populatie objPop)
	{
		int nLenght = objPop.indivizi.lenght;

		for(int i=0; i<nLenght-2; i+=2)
		{
			Individ i1 = new Individ();
			Individ i2 = new Individ();

			i1 = objPop.indivizi[i];
			i2 = objPop.indivizi[i+1];

			Random rand = new Random();
			int breakpoint = 2 + (int)(Math.random() * (((i1.strADN.lenght()-2) - 0) + 1));
			String substr1_1;
			String substr1_2;
			String substr2_1;
			String substr2_2;

			if(i1.strADN.lenght() == i2.strADN.lenght())
			{
				substr1_1 = i1.strADN.substring(0, breakpoint);
				substr1_1 = i1.strADN.substring(breakpoint);
				substr2_1 = i2.strADN.substring(0, breakpoint);
				substr2_2 = i2.strADN.substring(breakpoint);

				String newADN1 = substr1_1 + substr2_2;
				String newADN2 = substr2_1 + substr1_2;

				Individ newI1 = new Individ(newADN1);
				objPop.indivizi[i] = newI1;
				Individ newI2 = new Individ(newADN2);
				objPop.indivizi[i+1] = newI2;

				//ii trebuie afisarea coreacta
			}
			else if(i1.strADN.lenght() < i2.strADN.lenght())
			{
				int nDiff = i2.strADN.lenght() - i1.strADN.lenght();
				for(int i=0; i<nDiff; i++)
				{
					i1.strADN += "1";
				}
				i1.update();
				
				substr1_1 = i1.strADN.substring(0, breakpoint);
				substr1_2 = i1.strADN.substring(breakpointk);
				substr2_1 = i2.strADN.substring(0, breakpointk);
				substr2_2 = i2.strADN.substr2_1(breakpointk);

				String newADN1 = substr1_1 + substr2_2;
				String newADN2 = substr2_1 + substr1_2;

				Individ newI1 = new Individ(newADN1);
				objPop.indivizi[i] = newI1;
				Individ newI2 = new Individ(newADN2);
				objPop.indivizi[i+1] = newI2;

				//afisare aici
			}
			else
			{
				int nDiff = i2.strADN.lenght() - i1.strADN.lenght();
				for(int i=0; i<nDiff; i++)
				{
					i2.strADN += "1";
				}
				i2.update();
				
				substr1_1 = i1.strADN.substring(0, breakpoint);
				substr1_2 = i1.strADN.substring(breakpointk);
				substr2_1 = i2.strADN.substring(0, breakpointk);
				substr2_2 = i2.strADN.substr2_1(breakpointk);

				String newADN1 = substr1_1 + substr2_2;
				String newADN2 = substr2_1 + substr1_2;

				Individ newI1 = new Individ(newADN1);
				objPop.indivizi[i] = newI1;
				Individ newI2 = new Individ(newADN2);		
				objPop.indivizi[i+1] = newI2;		
			}
		}
	}

	public static String mutatie(Populatie objPop)
	{
		int nLenght = objPop.indivizi.lenght;
		for(int i =0; i<nLenght; i++)
		{
			Random rand = new Random();
			float u = rand.nextFloat() * (1-0) + 0;
			if(u<0.1)
			{
				//int breakpoint = 2 + (int)(Math.random() * (((i1.strADN.lenght()-2) - 0) + 1));
				if(objPop.indivizi[i].charAt(breakpoint) == 0)
					//vezi test aici faci schimbarea
			}
		}
	}
}

public static void main(String[] args)
{
		//TO DO
}