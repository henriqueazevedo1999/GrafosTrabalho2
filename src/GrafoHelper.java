//Henrique Azevedo

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GrafoHelper {
    public static String tipoDoGrafo(int[][] matrizAdjacencia) {
        if (ehNulo(matrizAdjacencia))
            return "Nulo";

        StringBuilder sb = new StringBuilder();
        sb.append(ehNaoDirigido(matrizAdjacencia) ? "Não dirigido;" : "Dirigido;");
        sb.append(ehMultigrafo(matrizAdjacencia) ? "Multigrafo;" : "Simples;");

        if (ehRegular(matrizAdjacencia))
            sb.append("Regular;");
        else if (ehCompleto(matrizAdjacencia))
            sb.append("Completo;");
        else if (ehBipartido(matrizAdjacencia))
            sb.append("Bipartido;");

        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    public static String arestasDoGrafo(int[][] matrizAdjacencia) {
        boolean ehNaoDirigido = ehNaoDirigido(matrizAdjacencia);
        List<String> arestas = new ArrayList<>();
        int soma = 0;

        // Soma todos os elementos da matriz de adjacência
        for (int i = 0; i < matrizAdjacencia.length; i++) {
            int j = ehNaoDirigido ? i + 1 : 0;

            for (; j < matrizAdjacencia[i].length; j++) {
                soma += matrizAdjacencia[i][j];
                if (matrizAdjacencia[i][j] != 0)
                    arestas.add("(" + (i + 1) + ", " + (j + 1) + ")");
            }
        }

        return "Quantidade: " + soma + " - Conjunto: {" + String.join(", ", arestas) + "}";
    }

    public static String grausDoVertice(int[][] matrizAdjacencia) {
        StringBuilder sb = new StringBuilder();
        int[] graus = new int[matrizAdjacencia.length];
        for (int i = 1; i <= matrizAdjacencia.length; i++) {
            graus[i - 1] = getGrauVertice(matrizAdjacencia, i);
            sb.append("V").append(i).append(": ").append(graus[i - 1]).append(", ");
        }

        sb.delete(sb.length() - 2, sb.length());
        Arrays.sort(graus);

        return sb.toString() + " - " + Arrays.toString(graus).replace("[", "(").replace("]", ")");
    }

    private static boolean ehNaoDirigido(int[][] matrizAdjacencia) {
        for (int i = 0; i < matrizAdjacencia.length; i++) {
            for (int j = 0; j < matrizAdjacencia[i].length; j++) {
                if (j <= i)
                    continue;

                if (matrizAdjacencia[i][j] != matrizAdjacencia[j][i])
                    return false;
            }
        }

        return true;
    }

    private static boolean ehMultigrafo(int[][] matrizAdjacencia) {
        for (int i = 0; i < matrizAdjacencia.length; i++) {
            if (matrizAdjacencia[i][i] != 0)
                return true;

            for (int j = 0; j < matrizAdjacencia[i].length; j++) {
                if (matrizAdjacencia[i][j] > 1)
                    return true;
            }
        }

        return false;
    }

    private static boolean ehRegular(int[][] matrizAdjacencia) {
        int grauPrimeiroVertice = getGrauVertice(matrizAdjacencia, 1);
        for (int i = 2; i <= matrizAdjacencia.length; i++) {
            int grauVerticeAtual = getGrauVertice(matrizAdjacencia, i);
            if (grauVerticeAtual != grauPrimeiroVertice)
                return false;
        }

        return true;
    }

    private static boolean ehCompleto(int[][] matrizAdjacencia) {
        for (int i = 0; i < matrizAdjacencia.length; i++) {
            for (int j = 0; j < matrizAdjacencia[i].length; j++) {
                if (i == j) {
                    if (matrizAdjacencia[i][j] != 0)
                        return false;

                    continue;
                }

                if (matrizAdjacencia[i][j] == 0)
                    return false;
            }
        }

        return true;
    }

    private static boolean ehNulo(int[][] matrizAdjacencia) {
        for (int i = 0; i < matrizAdjacencia.length; i++) {
            for (int j = 0; j < matrizAdjacencia[i].length; j++) {
                if (matrizAdjacencia[i][j] != 0)
                    return false;
            }
        }

        return true;
    }

    private static boolean ehBipartido(int[][] matrizAdjacencia) {
        int n = matrizAdjacencia.length;
        int[] cores = new int[n];

        for (int i = 0; i < n; i++) {
            if (cores[i] == 0 && !atribuiCor(matrizAdjacencia, cores, i, 1))
                return false;
        }

        return true;
    }

    private static boolean atribuiCor(int[][] matrizAdjacencia, int[] cores, int vertice, int cor) {
        cores[vertice] = cor;

        for (int i = 0; i < matrizAdjacencia.length; i++) {
            if (matrizAdjacencia[vertice][i] != 0) {
                if (cores[i] == cor)
                    return false;

                if (cores[i] == 0 && !atribuiCor(matrizAdjacencia, cores, i, -cor))
                    return false;
            }
        }

        return true;
    }

    private static int getGrauVertice(int[][] matrizAdjacencia, int vertice) {
        int grauTotal = 0;

        for (int j = 0; j < matrizAdjacencia[vertice - 1].length; j++) {
            grauTotal += matrizAdjacencia[vertice - 1][j];
        }

        if (ehNaoDirigido(matrizAdjacencia)) {
            grauTotal += matrizAdjacencia[vertice - 1][vertice - 1];
            return grauTotal;
        }

        for (int i = 0; i < matrizAdjacencia.length; i++) {
            grauTotal += matrizAdjacencia[i][vertice - 1];
        }

        return grauTotal;
    }
}
