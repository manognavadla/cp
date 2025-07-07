/*) Write a JAVA Program to find all the articulation points of a graph
We are given an undirected graph. An articulation point (or cut vertex) is defined as a vertex which, when
removed along with associated edges, makes the graph disconnected (or more precisely, increases the
number of connected components in the graph). The task is to find all articulation points in the given
graph. */
import java.util.*;

class ArticulationPoint {
    static int time;

    static void addEdge(ArrayList<ArrayList<Integer>> adj, int u, int v) {
        adj.get(u).add(v);
        adj.get(v).add(u);
    }

    static void APUtil(ArrayList<ArrayList<Integer>> adj, int u, boolean visited[],int disc[], int low[], int parent, boolean isAP[]) {
        int children = 0;
        visited[u] = true;
        disc[u] = low[u] = ++time;
        for (Integer v : adj.get(u)) {
            if (!visited[v]) {
                children++;
                APUtil(adj, v, visited, disc, low, u, isAP);
                low[u] = Math.min(low[u], low[v]);
                if (parent != -1 && low[v] >= disc[u])
                    isAP[u] = true; 
            }
            else if (v != parent) {
                low[u] = Math.min(low[u], disc[v]);
            }
        }
        if (parent == -1 && children > 1)
            isAP[u] = true;
    }

    static void AP(ArrayList<ArrayList<Integer>> adj, int V) {
        boolean[] visited = new boolean[V];
        int[] disc = new int[V];
        int[] low = new int[V];
        boolean[] isAP = new boolean[V];
        time = 0;
        for (int u = 0; u < V; u++) {
            if (!visited[u])
                APUtil(adj, u, visited, disc, low, -1, isAP);
        } 
        for (int u = 0; u < V; u++) {
            if (isAP[u])
                System.out.print(u + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter number of vertices:");
        int V = sc.nextInt();

        System.out.println("Enter number of edges:");
        int e = sc.nextInt();

        ArticulationPoint g = new ArticulationPoint();
        ArrayList<ArrayList<Integer>> adj1 = new ArrayList<>(V);
        for (int i = 0; i < V; i++) {
            adj1.add(new ArrayList<>());
        }

        System.out.println("Enter edges (u v):");
        for (int i = 0; i < e; i++) {
            int end1 = sc.nextInt();
            int end2 = sc.nextInt();
            addEdge(adj1, end1, end2);
        }

        System.out.println("Articulation points in the graph:");
        AP(adj1, V);
    }
}
