//Henrique Azevedo

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Graph {
    private Set<Node> nodes = new HashSet<>();
    private int V; // No. of vertices
    private LinkedList<Integer> adj[];

    public Graph() {
    }

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

    public void addEdge(int v, int w) {
        adj[v].add(w);
    }

    private void getVertices(int v, boolean visited[], List<Integer> vertices) {
        visited[v] = true;
        vertices.add(v);

        int n;

        Iterator<Integer> i = adj[v].iterator();
        while (i.hasNext()) {
            n = i.next();
            if (!visited[n])
                getVertices(n, visited, vertices);
        }
    }

    Graph getTranspose() {
        Graph g = new Graph(V);
        for (int v = 0; v < V; v++) {
            Iterator<Integer> i = adj[v].listIterator();
            while (i.hasNext())
                g.adj[i.next()].add(v);
        }
        return g;
    }

    void fillOrder(int v, boolean visited[], Stack<Integer> stack) {
        visited[v] = true;

        Iterator<Integer> i = adj[v].iterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n])
                fillOrder(n, visited, stack);
        }

        stack.push(v);
    }

    public List<List<Integer>> calculateSCCs() {
        Stack<Integer> stack = new Stack<Integer>();

        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; i++)
            visited[i] = false;

        for (int i = 0; i < V; i++)
            if (visited[i] == false)
                fillOrder(i, visited, stack);

        Graph gr = getTranspose();

        for (int i = 0; i < V; i++)
            visited[i] = false;

        List<List<Integer>> sccs = new LinkedList<List<Integer>>();

        while (!stack.empty()) {
            int v = (int) stack.pop();
            if (!visited[v]) {
                List<Integer> vertices = new LinkedList<Integer>();
                gr.getVertices(v, visited, vertices);
                sccs.add(vertices);
            }
        }

        return sccs;
    }
}