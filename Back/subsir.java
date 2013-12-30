import java.util.*;
import java.io.*;

class Backtrack
{
  static int[] stack;
  static int[] array;
  static int k, n, nArrayLimit, m;

  public static void init()
  {
  	stack = new int[n];
  }
  
  /*
   * Verific daca indicele elemetului din stack e mai mic
   * ca cel al limitei, si adaug element nou la stack.
   */
  public static boolean succ(int altceva)
  {
    if(k <= n)
    {
      stack[k-1] = array[altceva];
      return true;
    }
    else return false;
  }
  
  public static boolean valid()
  {
  	int suma = 0;
    for(int i=1; i<k; i++)
    {
    	suma += stack[i];
    }
    if(suma == m)
  	  return true;
  	else 
  	  return false;
  }

  public static int solutie()
  {
    if(k==n)
      return 1;
    else
      return 0;
  }

  public static void tiparire()
  {
    System.out.println("\n\n\nZa finisd product:");
    for (int i=0; i<=k; i++)
      System.out.println(stack[i]);
  	System.out.println("\n\n\n");
  }

  public static void back(int stackLimit)
  {
    boolean as; k=1; n = stackLimit;
    init();
    while(k>0 && k< nArrayLimit)
    {
      do
      {}while((as=succ(k)) && !(valid()));
      if(as==true)
      {
        if(solutie()==1) 
          tiparire();
        else
        {
          k++;
        }
      }
      else k--;
    }
  }

  public static void main(String[] args)
  {
  	try
  	{
  		File file = new File("intrare.txt");
	  	Scanner sc = new Scanner(file);
	  	int i = 0;
	    nArrayLimit = sc.nextInt();
	  	array = new int[nArrayLimit];
	  	m = sc.nextInt();
	  	while (sc.hasNext() && i<nArrayLimit)
	  	{
	  		array[i] = sc.nextInt();
	  		i++;
	  	}

	  	for(i=0; i<nArrayLimit-1; i++)
	  	{
	  		back(i);
	  	}
	//  	back();
  	}
  	catch (InputMismatchException e) 
  	{
 	   System.out.print(e.getMessage()); //try to find out specific reason.
	}
	catch (Exception ex)
  	{
  		ex.printStackTrace(System.out);
  	}
  }
}
//un algoritm care sa aiba un for de la 1 la n-1 unde n-1 e numaru'
//de elemente din multime

//sa faca toate submultimile posibile cu i elemente
//sa le adauge intru-un fisier
//cu propper encoding
