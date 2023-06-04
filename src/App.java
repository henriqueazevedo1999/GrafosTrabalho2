//Henrique Azevedo

public class App {
    public static void main(String[] args) {
        execicio2();
    }

    public static void execicio1() {
        String[][] input = {
                { "7" },
                { "F", "A" },
                { "0", "I", "I", "I", "I", "I", "I" },
                { "I", "0", "1", "I", "I", "I", "I" },
                { "4", "I", "0", "I", "I", "I", "I" },
                { "I", "3", "I", "0", "1", "I", "I" },
                { "I", "I", "2", "I", "0", "I", "I" },
                { "I", "I", "I", "3", "I", "0", "2" },
                { "I", "I", "I", "I", "5", "I", "0" }
        };

        int numeroVertices = Integer.parseInt(input[0][0]);

        Node[] nodes = new Node[numeroVertices];
        Graph graph = new Graph();

        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(idxToChar(i));
            graph.addNode(nodes[i]);
        }

        String origem = input[1][0];
        String destino = input[1][1];
        Node nodeOrigem = nodes[alphabetToIdx(origem)];
        Node nodeDestino = nodes[alphabetToIdx(destino)];

        for (int i = 0; i < nodes.length; i++) {
            String[] arestas = input[i + 2];
            for (int j = 0; j < arestas.length; j++) {
                if (j == i || arestas[j].equals("I"))
                    continue;

                nodes[i].addDestination(nodes[j], Integer.parseInt(arestas[j]));
            }
        }

        graph = Dijkstra.calculateShortestPathFromSource(graph, nodeOrigem);

        System.out.println("Caminho mínimo: " + nodeDestino.printShortestPath());
        System.out.println("Custo: " + nodeDestino.getDistance());
    }

    private static void execicio2() {
        String[][] input = {
                { "1" },
                { "10", "15" },
                { "A", "C" },
                { "B", "A" },
                { "C", "B" },
                { "C", "F" },
                { "D", "A" },
                { "D", "C" },
                { "E", "C" },
                { "E", "D" },
                { "F", "B" },
                { "F", "G" },
                { "F", "H" },
                { "H", "G" },
                { "H", "I" },
                { "I", "J" },
                { "J", "H" }
        };

        int qtdTestes = Integer.parseInt(input[0][0]);
        for (int idxTeste = 0; idxTeste < qtdTestes; idxTeste++) {
            int qtdVertices = Integer.parseInt(input[1][0]);
            int qtdArestas = Integer.parseInt(input[1][1]);

            Graph g = new Graph(qtdVertices);
            for (int idxAresta = 0; idxAresta < qtdArestas; idxAresta++) {
                int v = alphabetToIdx(input[idxAresta + 2][0]);
                int w = alphabetToIdx(input[idxAresta + 2][1]);
                g.addEdge(v, w);
            }

            System.out.println("#" + (idxTeste + 1));
            g.printSCCs();
        }
    }

    private static String idxToChar(int i) {
        return i > -1 && i < 27 ? String.valueOf((char) (i + 'A')) : null;
    }

    private static int alphabetToIdx(String c) {
        return c.charAt(0) - 'A';
    }

    private static int charToInt(char c) {
        return c - '0';
    }
}
