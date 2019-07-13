package Lab10;
import java.util.Scanner;
public class LSR
{
    public static int minDistance(int dist[], Boolean sptSet[],int V)
    {
        int min = Integer.MAX_VALUE, min_index=-1;
        for (int v = 0; v < V; v++)
        {
            if (sptSet[v] == false && dist[v] <= min)
            {
                min = dist[v];
                min_index = v;
            }
        }
        return min_index;
    }
    public static void dijkstra(int graph[][], int src,int V)
    {   //V is n total vertices
        int dist[] = new int[V]; // distance will hold the shortest distance from source to i
        Boolean sptSet[] = new Boolean[V];
        for (int i = 0; i < V; i++)
        {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }
        dist[src] = 0;
        for (int count = 0; count < V-1; count++)        // Finding the shortest path for all vertices
        {
            int u = minDistance(dist, sptSet,V);
            sptSet[u] = true;
            for (int v = 0; v < V; v++)
                if (!sptSet[v] && graph[u][v]!=0 && dist[u] != Integer.MAX_VALUE && dist[u]+graph[u][v] < dist[v])
                    dist[v] = dist[u] + graph[u][v];
        }
        System.out.println("Vertex   Distance from Source");
        for (int i = 0; i < V; i++)
            System.out.println(i+" \t "+dist[i]);
    }
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
        for(int i=0;i<n;i++)		//for all the routers apply dijkstra
        {
            System.out.println("Router "+i);
            dijkstra(adj, i,n);
        }
    }
}

