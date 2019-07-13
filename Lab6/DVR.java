package Lab9;
import java.util.Scanner;
public class DVR
{
    public static void main(String args[])
    {
        System.out.println("Enter no of routers \t");
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        int adj[][] = new int[n][n];
        System.out.println("Enter undirected weighted adjacency matrix");
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                String input = sc.next();
                if(input.equals("INF"))  adj[i][j] = 99999999;	//or use Integer.Maxvalue
                else adj[i][j] = Integer.parseInt(input);
            }
        } sc.close();
        int routing_table[][][]=new int[n][n][3];	//3 columns Destination,Distance,Next Hop
        int previous[][] = new int[n][n];
        for(int i=0;i<n;i++){				//Setting the initial Routing table of each router
            for(int j=0;j<n;j++) {
                routing_table[i][j][0] = j;
                routing_table[i][j][1] = adj[i][j];
                previous[i][j] = adj[i][j];
                if(adj[i][j]!=99999999)
                    routing_table[i][j][2] = j;
                else
                    routing_table[i][j][2] = -1;
            }
        }
        for(int a0=0;a0<n-2;a0++)
        {
            for(int i=0;i<n;i++)		//Let A
            {
                for(int j=0;j<n;j++)	//find the distances from A to A,B,C,D
                {
                    if(i==j)
                    {
                        routing_table[i][j][1] = 0;
                        routing_table[i][j][2] = i;
                        continue;
                    }
                    //if i!=j Then to go from i to j we will go using their neighbors
                    int min_index=-1;	//Next_hop index
                    int min=99999999;
                    for(int k=0;k<n;k++)
                    {	//We use neighbors distance to reach j from i
                        if(i==k) continue;
                        if(adj[i][k]==99999999) continue;	//for non-neighbors continue
                        if(min>adj[i][k]+previous[k][j])
                        {
                            min = adj[i][k] + previous[k][j];
                            min_index = k;
                        }
                    }
                    routing_table[i][j][1] = min;
                    routing_table[i][j][2] = min_index;
                }
            }
            for(int i=0;i<n;i++)
            {
                for(int j=0;j<n;j++)
                {
                    previous[i][j]=routing_table[i][j][1];
                }
            }
        }
        System.out.println("The following are the routing tables at each node");
        for(int i=0;i<n;i++)
        {
            System.out.println("Routing table at \t"+(char)(i+65)+" :");
            System.out.println("Destination\tDistance\tNext Hop");
            for(int j=0;j<n;j++){
                System.out.println("\t"+(char)(routing_table[i][j][0]+65)
                        +"\t\t\t"+routing_table[i][j][1]
                        +"\t\t\t"+(char)(routing_table[i][j][2]+65));
            }
            System.out.println("\n\n\n");
        }
    }
}