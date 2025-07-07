/*f) Write a JAVA Program to find a permutation of the vertices (topological order) which
corresponds to the order defined by all edges of the graph
Topological sorting for Directed Acyclic Graph (DAG) is a linear ordering of vertices such that for every
directed edge u v, vertex u comes before v in the ordering. Topological Sorting for a graph is not possible
if the graph is not a DAG.
For example, a topological sorting of the following graph is “5 4 2 3 1 0”. There can be more than one
topological sorting for a graph. For example, another topological sorting of the following graph is “4 5
2 3 1 0”. The first vertex in topological sorting is always a vertex with in degree as 0 (a vertex with no
incoming edges) */

import java.io.*;
import java.util.*;

class TopologicalSort {
    private int V;
    List<ArrayList<Integer>> adj;

    TopologicalSort(int v) {
        V = v;
        adj = new ArrayList<ArrayList<Integer>>(v);
        for (int i = 0; i < v; ++i)
            adj.add(new ArrayList<Integer>());
    }
    void addEdge(int v, int w) {
        adj.get(v).add(w);
    }
    void topologicalSort() {
        int[] indegree = new int[V];
        for (int u = 0; u < V; u++) {
            for (int v : adj.get(u)) {
                indegree[v]++;
            }
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (indegree[i] == 0)
                queue.add(i);
        }
        int cnt = 0;
        List<Integer> topoOrder = new ArrayList<>();
        while (!queue.isEmpty()) {
            int u = queue.poll();
            topoOrder.add(u);
            for (int v : adj.get(u)) {
                if (--indegree[v] == 0)
                    queue.add(v);
            }
            cnt++;
        }
        if (cnt != V) {
            System.out.println("There exists a cycle in the graph");
            return;
        }
        for (int node : topoOrder)
            System.out.print(node + " ");
        System.out.println();
    }

    // Driver code
    public static void main(String args[]) {
        TopologicalSort g = new TopologicalSort(6);
        g.addEdge(5, 2);
        g.addEdge(5, 0);
        g.addEdge(4, 0);
        g.addEdge(4, 1);
        g.addEdge(2, 3);
        g.addEdge(3, 1);
        System.out.println("Following is a Topological sort of the given graph");
        g.topologicalSort();
    }
}