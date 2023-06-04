//Henrique Azevedo

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.Stack;

public class Graph {
    private Set<Node> nodes = new HashSet<>();
    private int V; // No. of vertices
    private LinkedList<Integer> adj[]; // Adjacency List

    public Graph() {}

    Graph(int v) {
        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adj[i] = new LinkedList();
    }

    public Set<Node> getNodes() {
        return nodes;
    }

    public void addNode(Node nodeA) {
        nodes.add(nodeA);
    }

    // Function to add an edge into the graph
    public void addEdge(int v, int w) {
        adj[v].add(w);
    }

    // A recursive function to print DFS starting from v
    private void DFSUtil(int v, boolean visited[]) {
        // Mark the current node as visited and print it
        visited[v] = true;
        System.out.print(v + " ");

        int n;

        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> i = adj[v].iterator();
        while (i.hasNext()) {
            n = i.next();
            if (!visited[n])
                DFSUtil(n, visited);
        }
    }

    // Function that returns reverse (or transpose) of this graph
    Graph getTranspose() {
        Graph g = new Graph(V);
        for (int v = 0; v < V; v++) {
            // Recur for all the vertices adjacent to this vertex
            Iterator<Integer> i = adj[v].listIterator();
            while (i.hasNext())
                g.adj[i.next()].add(v);
        }
        return g;
    }

    void fillOrder(int v, boolean visited[], Stack<Integer> stack) {
        // Mark the current node as visited and print it
        visited[v] = true;

        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> i = adj[v].iterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n])
                fillOrder(n, visited, stack);
        }

        // All vertices reachable from v are processed by now,
        // push v to Stack
        stack.push(v);
    }

    // The main function that finds and prints all strongly
    // connected components
    public int[][] getSCCs() {
        Stack<Integer> stack = new Stack<Integer>();

        // Mark all the vertices as not visited (For first DFS)
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; i++)
            visited[i] = false;

        // Fill vertices in stack according to their finishing
        // times
        for (int i = 0; i < V; i++)
            if (visited[i] == false)
                fillOrder(i, visited, stack);

        // Create a reversed graph
        Graph gr = getTranspose();

        // Mark all the vertices as not visited (For second DFS)
        for (int i = 0; i < V; i++)
            visited[i] = false;

        // Now process all vertices in order defined by Stack
        while (!stack.empty()) {
            // Pop a vertex from stack
            int v = (int) stack.pop();

            // Print Strongly connected component of the popped vertex
            if (!visited[v]) {
                gr.DFSUtil(v, visited);
            }
        }
    }
}