package Lab3;
import java.util.Random;
import java.util.Scanner;
public class CRC {
    public static int[] division(int data[],String key,int l)
    {
        int keylen=key.length();
        int divisor[]=new int[keylen];
        for(int i=0;i<keylen;i++)
        {
            if(key.charAt(i)=='1')
                divisor[i]=1;
            else divisor[i]=0;
        }
        for(int i=0;i<l;i++)
        {
            if(data[i]==0)
                continue;
            for(int j=0;j<keylen;j++)
            {
                if(divisor[j]==data[j+i])
                    data[j+i]=0;
                else data[j+i]=1;
            }
        }
        int j=0;
        int remainder[]=new int[keylen-1];
        for(int i=l;i<l+keylen-1;i++)
        {
            remainder[j]=data[i];
            j++;
        }
        return remainder;
    }
    public static void main(String args[])
    {
        Scanner s=new Scanner(System.in);
        System.out.println("Enter the data:");
        String s1=s.next();
        System.out.println("Enter the Generator key in bits:");
        String key=s.next();
        s.close();
        String data="";
        int arr[]=new int[7];
        for(int i=0;i<s1.length();i++)
        {
            int n=(int)s1.charAt(i);
            for(int j=0;j<7;j++)
            {
                arr[6-j]=(n)%2;	//This array has data in bits
                n=n/2;
            }
            for(int k=0;k<7;k++)
            {
                data= data+(Integer.toString(arr[k]));	//data string contains all bits.
            }
        }
        int l=data.length();
        System.out.print("Data is :");
        for(int i=0;i<l;i++)
        {
            System.out.print(data.charAt(i));
        }
        System.out.println();
        int keylen=key.length();
        int data1[]=new int[l+keylen-1];//original data
        int data2[]=new int[l+keylen-1];//copy of data

        for(int i=0;i<l;i++)		//converting the string into array
        {
            if(data.charAt(i)=='1')
                data1[i]=1;
            else data1[i]=0;
        }
        for(int i=l;i<l+keylen-1;i++)		//adding keylength -1 0's to the end of data
            data1[i]=0;
        for(int i=0;i<l+keylen-1;i++)
            data2[i]=data1[i];

        int remainder[]=new int[keylen-1];
        remainder=division(data1,key,l);

        for(int i=l;i<l+keylen-1;i++)
        {
            data2[i]=remainder[i-l];		//data2 contains the original data + the redundant bits of the remainder
        }
        System.out.print("Data sent  is: ");
        for(int i=0;i<l+keylen-1;i++)
            System.out.print(data2[i]);
        System.out.println();

        Random r=new Random();
        int turn=r.nextInt(2);
        if(turn==1)//generate random error
        {
            int n= r.nextInt(l+keylen-1);
            data2[n]=(data2[n]+1)%2; //flipping a single bit
        }
        System.out.print("Data recieved is:");
        for(int i=0;i<l+keylen-1;i++)
            System.out.print(data2[i]);
        System.out.println();
        remainder=division(data2,key,l);
        int count=0;
        System.out.print("Remainder at the receiver end is :");
        for(int i=0;i<keylen-1;i++)
        {
            if(remainder[i]==0)
                count++;
            System.out.print(remainder[i]);
        }
        System.out.println();
        if(count==keylen-1)
            System.out.println("Data is received correctly");
        else System.out.println("Data is Corrupted");
    }
}
