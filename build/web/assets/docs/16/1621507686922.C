#include<stdio.h>
#include<conio.h>
int main()

{
    int n,i,j;
    printf("enter any number:");
    scanf("%d\n",&n);

    i=1;
    j=1;
    while(i<=n)
    {
        printf("%d\n",j);
        j=j+i;
        i++;

    }

    return 0;

}

