package Lab6;
import java.net.InetAddress;
import java.net.UnknownHostException;
public class DNS {
    public static void main(String a[]) {
        try {
            InetAddress host = InetAddress.getByName("157.240.13.35");
            System.out.println(host.getHostName());
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        }
        // The URL for which IP address needs to be fetched
        String s = "www.facebook.com";
        try {
            // Fetch IP address by getByName()
            InetAddress ip = InetAddress.getByName(s);

            // Print the IP address
            System.out.println("Public IP Address of: " + ip.getHostAddress());
        }
        catch (Exception e) {
            // It means the URL is invalid
            System.out.println("Invalid URL");
        }
    }
}
