import java.util.Random;
import java.util.Scanner;

public class HammingCode {
    public static void main(String args[])
    {
        Scanner sc =new Scanner(System.in);
        System.out.println("Enter the data ");//Eg "karthik"
        String s=sc.next();
        sc.close();
        int m1=s.length();
        int m=m1*7;	//data m bits
        int r=0;
        while(r>=0)
        {
            if((Math.pow(2,r))>=(r+m+1))
                break;
            else r++;
        }		//got r value
        int l=r+m+1;
        int arr[]=new int[l];	//To store data and redundant bits
        int arr1[]=new int[m+1];	//To store the data in bits
        int a=0;
        for(int i=0;i<s.length();i++)
        {
            int n=(int)s.charAt(i);		//ASCII value a=97 1100001
            a=a+7;
            for(int k=0;k<7;k++)
            {
                arr1[a-k]=n%2;
                n=n/2;
            }
        }		//storing binary data in arr1[]
        System.out.print("Array1: ");
        for(int i=1;i<m+1;i++)
            System.out.print(arr1[i]);
        System.out.println();
        int k=1;int j=0;
        for(int i=1;i<l;i++)
        {
            if(i==(Math.pow(2,j)))
            {
                j++;
                arr[i]=0;
            }
            else
            {
                arr[i]=arr1[k++];
            }
        }
        System.out.print("Array: ");
        for(int i=1;i<l;i++)
        {
            System.out.print(arr[i]);
        }
        System.out.println();
        j=0;int parity;
        //to place r1,r2,r3 redundant values in array we need to check the parity
        for(int i=1;i<l;i++)
        {
            if(i==(Math.pow(2,j)))	//at 2^ we have redundant bits
            {
                k=i;
                j++;parity=0;
                while(k<l)
                {						//r1 needs 1,3,5,7 and r2 needs 2,3,6,7 ...
                    if(((k/i)%2)==1)
                    {
                        parity=parity+arr[k];
                    }
                    k++;
                }
                if(parity%2 ==0)
                    arr[i]=0;
                else arr[i]=1;
            }
        }
        System.out.println("The data sent from the sender");
        for(int i=1;i<l;i++)
        {
            System.out.print(arr[i]);
        }
        System.out.println();
        Random rand=new Random();
        int n=rand.nextInt(2);
        if(n==1) 	//generate error
        {
            int i=rand.nextInt(l);
            arr[i]=(arr[i]+1)%2; 	//flipping the bit
        }

        System.out.println("The data received ");
        for(int i=1;i<l;i++)
        {
            System.out.print(arr[i]);
        }
        System.out.println();
        //check if the bits are received correctly or not
        int count=0;int b[]=new int[r];
        int count1=0;j=0;
        for(int i=1;i<l;i++)
        {
            if(i==(Math.pow(2,j)))	//at 2^ we have redundant bits
            {
                k=i;
                j++;parity=0;
                while(k<l)
                {						//r1 needs 1,3,5,7 and r2 needs 2,3,6,7 ...
                    if(((k/i)%2)==1)
                    {
                        parity=parity+arr[k];
                    }
                    k++;
                }
                if((parity%2)==0)
                {
                    b[count]=0;
                    count1++;
                }
                else b[count]=1;
                count++;
            }
        }
        int pos=0;j=0;
        for(int i=0;i<r;i++)
            pos= pos+ b[i]*(int)(Math.pow(2,j++));

        if(count1==r)//	if nob of even parity's are equal to redundant bits
            System.out.println("No error in bits");
        else
            System.out.println("Error at bit no "+pos);
    }
}
