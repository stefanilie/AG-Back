#include <stdlib.h>
#include <stdio.h>
#include <string.h>

int main(int argc, char* argv[])
{
  int a[100], n, i, j;
  char *moloz, *endptr;

  if(argc != 2){
    fprintf(stderr, "Utilizare: %s numarul de elemente ale listei.\n ", argv[0]);
    return 1;
  }
  
  moloz=argv[1];
  
  n=strtol(moloz, &endptr, 10);

  for (i=0; i<n; i++)
  {
    printf("Numarul %d din lista: ", i);
    scanf("%d", &a[i]);
  }

  for (i=0; i<n; i++)
  {
    j += a[i];
  }
  j = j/n;

}
