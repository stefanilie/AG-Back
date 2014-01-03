import java.util.*;
import java.io.*;

class Backtrack
{
  static int[] stack;
  static int[] array;
  static int k, n, nArrayLimit, m, iterator;

  public static void init()
  {
  	stack = new int[n];
  }
  
  /*
   * Verific daca indicele elemetului din stack e mai mic
   * ca cel al limitei, si adaug element nou la stack.
   */
  public static boolean succ()
  {
    if(iterator < nArrayLimit && k <= n)
    {
      stack[k-1] = array[iterator];
   //   System.out.print("\nE in succ cu iterator=" + iterator +
   //   	" si array[iterator] = " + array[iterator] + " si k= " + k);
      iterator++;
      return true;
    }
   /* else if(iterator >= nArrayLimit)
    {
    	System.out.print("\nsucc: iterator>= nArrayLimit"); 
    }
    else if(k>=n)
    {
    	System.out.print("\nsucc: k>n");
    }
    else if(iterator>=nArrayLimit && k>n)
    {
    	System.out.print("\nsucc: iterator>=nArrayLimit&&k>n");
    }
    System.out.print("\nsucc:Returnez false din default!");*/
    return false;
  }
  
  public static boolean valid()
  {
  	if(k != 1)
  	{
	  	for (int i=0; i<k-1; i++)
	  		for(int j=i+1; j<k; j++)
	  		{
			    if(stack[i]==stack[j])
			    {
			    //  System.out.print("\nvalid:Si a returnat false!");
			  	  return false;
			    }
			  	else 
			  	{
			  	//  System.out.print("\nvalid:Si a returnat true!");
			  	  return true;
			  	}
			}
  	}
  	else return true;
  	return false;
  }

  public static int solutie()
  {
  	int suma = 0;
    for(int i=0; i<k; i++)
    {
    	suma += stack[i];
    }
  /*  System.out.print("\n\nPentru n=" + n + " suma pentru multimea "+
    	"S={");
    for(int j=0; j<k; j++)
    {
    	System.out.print(stack[j] + " ");
    }
    System.out.print("} este " + suma);*/
    if(suma==m && k==n)
      return 1;
    else
      return 0;
  }

  public static void tiparire()
  {
    System.out.println("\n!\n!\nZa finisd product:");
    for (int i=0; i<k; i++)
      System.out.println(stack[i] + "");
  	System.out.println("\n!\n!\n");
  }

  public static void back(int stackLimit)
  {
    boolean as; k=1; n = stackLimit; iterator = 0;
  	//System.out.println("\n\n!!!Am intrat in back cu n=" + n
  	//	+"\n\n");
    init();
    while(k>0 && k < nArrayLimit)
    {
      do
      {}while((as=succ()) && !(valid()));
      if(as==true)
      {
        if(solutie()==1) 
          tiparire();
        else
        {
          k++;
      //    System.out.print("\nAre succ, e valid"+
        //  	", dar nu are sol. Am crescut pe k, acum =" + k);
        }
      }
      else 
      {
      	k--;
      	//System.out.print("\nsucc->false->Am scazut pe k, = " + k);
      	if(iterator == nArrayLimit && n != 1 && k >= 2)
      	{
      		for(int b=0; b<nArrayLimit; b++)
      		{
      			if(stack[k-2] == array[b])
      			{
      				iterator=b+1;
      	//			System.out.print("\niterator==nArrayLimit->"+
      	//				"b= " + b+1);
      				b=nArrayLimit+5;//pentru ca filme
      				for(int g=k-1; g<n; g++)
      				{
      					stack[g] = g-5423; //pentru ca automobile
      				}
      				k--;

//cand ajunge aici, trebuie sa inlocuiasca pe stack[k-2] cu
//urmatorul din lista si sa stearga tot ce urmeaza dupa el;
      			}
      		}
      //		iterator = Arrays.asList(array).indexOf(stack[k]) + 1;
      //		System.out.print("\niterator==nArrayLimit->"+
      //			"Arrays.asList(stack).indexOf(stack[k]) = " + 
      //			Arrays.asList(array).indexOf(stack[k])+1);
      	}
      }
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

	  	for(i=1; i<nArrayLimit; i++)
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


//trebuie sa il reinitializez pe iterator ca atunci cand raman 
//fara elemente care le pot adauga din array, dar mai sunt 
//cazuri posibile, sa poata sa refreseze stackul