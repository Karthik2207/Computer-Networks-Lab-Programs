import java.io.*;
import java.net.Socket;
import java.util.Scanner;
public class MyClient
{
    public static void main(String args[]) throws Exception
    {
        Socket socket =new Socket("localhost",5000);
        System.out.println("Client Connected: ");
        DataInputStream din=new DataInputStream(socket.getInputStream());
        DataOutputStream dout=new DataOutputStream(socket.getOutputStream());
        Scanner sc=new Scanner(System.in);
        String s="";
        while(!s.equals("stop"))
        {
            s=sc.nextLine();
            dout.writeUTF(s);
            dout.flush();
            s=din.readUTF();
            System.out.println(s);
        }
        System.out.println("Client Stopped");
        socket.close();
        din.close();
        dout.close();
        sc.close();
    }
}
