package Lab3;
import java.util.Random;
import java.util.Scanner;
public class Parity
{
    public static void intermediate(int arr[])
    {
        Random r=new Random();
        int rand=r.nextInt(7);  //randomly get the index to induce error
        arr[rand]=(arr[rand]+1)%2;
    }
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the word");
        String s=sc.next();
        sc.close();
        int l=s.length();
        int arr[]=new int[8];   //stores the character in bits
        for(int i=0;i<l;i++)
        {
            int parity=0;
            int n=(int)s.charAt(i);
            for(int j=0;j<=6;j++)
            {
                arr[6-j]=n%2;
                n=n/2;
            }
            for(int j=0;j<=6;j++)
            {
                if(arr[j]==1)
                    parity=parity+1;
            }
            if(parity%2==0)
                arr[7]=0;
            else arr[7]=1;
            Random r=new Random();
            int rand=r.nextInt(2);
            int sum;
            if(rand==1)
                intermediate(arr);
            sum=0;
            for(int k=0;k<7;k++)
                sum=sum+arr[k];
            sum=sum%2;
            if(arr[7]!=sum)//Parity are not matching i.e if we have error
            {
                sum=0;
                for(int j=6;j>=0;j--)
                {
                    sum=sum+ arr[j]*((int)(Math.pow(2,arr[j])));
                }
                System.out.print((char)sum+ ":Error "+"\t");
            }
            else System.out.print(s.charAt(i)+"\t");
        }
    }
}
