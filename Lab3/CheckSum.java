
import java.util.Random;
import java.util.Scanner;

public class CheckSum{
    static int[] sum(int checksum[],int a[][],int l)
    {
        int carry;int temp;
        for(int i=0;i<l;i++)
        {	carry=0;
            for(int j=6;j>=0;j--)
            {
                temp=(checksum[j]+a[i][j]+carry)/2;
                checksum[j]=(checksum[j]+a[i][j]+carry)%2;
                carry=temp;
            }
            if(carry==1)
            {
                for(int j=6;j>=0;j--)
                {
                    temp=(checksum[j]+carry)/2;
                    checksum[j]=(checksum[j]+carry)%2;
                    carry=temp;
                }
            }
        }
        for(int i=0;i<=6;i++)
            checksum[i]=(checksum[i]+1)%2; //flipping the checksum
        return checksum;
    }
    public static void main(String args[])
    {
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter the data:");
        String s=sc.next();
        sc.close();
        int a[][]=new int[s.length()][7];
        int checksum[]=new int[7];
        for(int i=0;i<s.length();i++)
        {
            int n=(int)s.charAt(i);	//ASCII VALUE
            for(int j=6;j>=0;j--)
            {
                a[i][j]=n%2;
                n=n/2;
            }
        }
        for(int i=0;i<7;i++)
            checksum[i]=0;//checksum contains first row
        checksum=sum(checksum,a,s.length());
        System.out.print("The checksum at sender is: ");
        for(int i=0;i<7;i++)
            System.out.print(checksum[i]);
        System.out.println();
        Random r=new Random();
        int turn=r.nextInt(2);
        if(turn==1)//generate random error
        {
            int row= r.nextInt(s.length());
            int col=r.nextInt(7);
            a[row][col]=(a[row][col]+1)%2;	//flipping only a single bit
        }
        checksum=sum(checksum,a,s.length());
        System.out.print("The Sum at Receiver  : ");
        int count=0;
        for(int i=0;i<7;i++)
        {
            if(checksum[i]==0)
                count++;
            System.out.print(checksum[i]);
        }
        if(count==7)
            System.out.println("\nNo error ");
        else System.out.println("\nError ");
    }
}
