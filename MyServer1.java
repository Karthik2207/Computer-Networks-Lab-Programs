import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MyServer1
{
    public static void main(String args[]) throws Exception
    {
        ServerSocket server=new ServerSocket(5000);
        System.out.println("Server Started");
        System.out.println("Waiting for Client");
        Socket socket = server.accept();
        System.out.println("Client Connected: ");
        DataInputStream din=new DataInputStream(socket.getInputStream());
        DataOutputStream dout=new DataOutputStream(socket.getOutputStream());
        String s="";
        while(!s.equals("stop"))
        {
            s=din.readUTF();
            System.out.println(s);
        }
        System.out.print("Terminating Server");
        server.close();
        socket.close();
        din.close();
        dout.close();
    }
}
