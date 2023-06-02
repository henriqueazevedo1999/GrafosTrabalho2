//Henrique Azevedo

public class App {
    public static void main(String[] args) {
        int[][] matrizAdjacencia = new int[][]{
            {1,0,1},
            {1,1,1},
            {1,1,0}
        };

        System.out.println("Tipo: " + GrafoHelper.tipoDoGrafo(matrizAdjacencia));
        System.out.println("Arestas: " + GrafoHelper.arestasDoGrafo(matrizAdjacencia));
        System.out.println("Graus: " + GrafoHelper.grausDoVertice(matrizAdjacencia));
    }
}
