import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class MyClient1 {
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
            }
            System.out.println("Client Stopped");
            socket.close();
            din.close();
            dout.close();
            sc.close();
        }
    }