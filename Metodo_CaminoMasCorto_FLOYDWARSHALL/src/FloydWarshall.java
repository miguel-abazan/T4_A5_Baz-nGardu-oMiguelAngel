import java.util.*;
import java.lang.*;
import java.io.*;

class AllPairShortestPath {
	final static int INF = 99999, V = 4;


	void floydWarshall(int graph[][]) {
		int dist[][] = new int[V][V];
		int i, j, k;
		/*
		 * Inicializa la matriz de solución igual que la matriz de gráfico de entrada. O
		 * podemos decir los valores iniciales de distancias más cortas se basan en
		 * caminos más cortos sin considerar intermedios vértice.
		 */
		for (i = 0; i < V; i++)
			for (j = 0; j < V; j++)
				dist[i][j] = graph[i][j];
		/*
		 * Agregar todos los vértices uno por uno al conjunto de intermedios vértices
		 * ---> Antes de comenzar una iteración, tenemos el más corto distancias entre
		 * todos los pares de vértices de manera que las distancias más cortas
		 * consideran solo los vértices en establece {0, 1, 2, .. k-1} como vértices
		 * intermedios. ----> Después del final de una iteración, el vértice no. k se
		 * agrega al conjunto de vértices intermedios y al conjunto se convierte en {0,
		 * 1, 2, .. k}
		 */
		for (k = 0; k < V; k++) {
			// Elige todos los vértices como fuente uno por uno
			for (i = 0; i < V; i++) {
				// Elija todos los vértices como destino para el
				// fuente elegida arriba
				for (j = 0; j < V; j++) {
					// Si el vértice k está en el camino más corto desde
					// i para j, luego actualice el valor de dist[i][j]
					if (dist[i][k] + dist[k][j] < dist[i][j])
						dist[i][j] = dist[i][k] + dist[k][j];
				}
			}
		}
		// Imprime la matriz de distancia más corta
		printSolution(dist);
	}

	void printSolution(int dist[][]) {
		System.out
				.println("\r\n" + "La siguiente matriz muestra la mas corta " + "distancia entre cada par de vértices");
		for (int i = 0; i < V; ++i) {
			for (int j = 0; j < V; ++j) {
				if (dist[i][j] == INF)
					System.out.print("INF ");
				else
					System.out.print(dist[i][j] + " ");
			}
			System.out.println();
		}
	}
	// Programa de controlador para probar la función anterior
	public static void main(String[] args) {
		int graph[][] = { { 0, 5, INF, 10 }, { INF, 0, 3, INF }, { INF, INF, 0, 1 }, { INF, INF, INF, 0 } };
		AllPairShortestPath a = new AllPairShortestPath();
		// Imprime la solucion
		a.floydWarshall(graph);
	}
}