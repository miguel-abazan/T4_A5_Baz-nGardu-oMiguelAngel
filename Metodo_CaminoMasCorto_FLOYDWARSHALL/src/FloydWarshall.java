import java.util.*;
import java.lang.*;
import java.io.*;

class AllPairShortestPath {
	final static int INF = 99999, V = 4;


	void floydWarshall(int graph[][]) {
		int dist[][] = new int[V][V];
		int i, j, k;
		/*
		 * Inicializa la matriz de soluci�n igual que la matriz de gr�fico de entrada. O
		 * podemos decir los valores iniciales de distancias m�s cortas se basan en
		 * caminos m�s cortos sin considerar intermedios v�rtice.
		 */
		for (i = 0; i < V; i++)
			for (j = 0; j < V; j++)
				dist[i][j] = graph[i][j];
		/*
		 * Agregar todos los v�rtices uno por uno al conjunto de intermedios v�rtices
		 * ---> Antes de comenzar una iteraci�n, tenemos el m�s corto distancias entre
		 * todos los pares de v�rtices de manera que las distancias m�s cortas
		 * consideran solo los v�rtices en establece {0, 1, 2, .. k-1} como v�rtices
		 * intermedios. ----> Despu�s del final de una iteraci�n, el v�rtice no. k se
		 * agrega al conjunto de v�rtices intermedios y al conjunto se convierte en {0,
		 * 1, 2, .. k}
		 */
		for (k = 0; k < V; k++) {
			// Elige todos los v�rtices como fuente uno por uno
			for (i = 0; i < V; i++) {
				// Elija todos los v�rtices como destino para el
				// fuente elegida arriba
				for (j = 0; j < V; j++) {
					// Si el v�rtice k est� en el camino m�s corto desde
					// i para j, luego actualice el valor de dist[i][j]
					if (dist[i][k] + dist[k][j] < dist[i][j])
						dist[i][j] = dist[i][k] + dist[k][j];
				}
			}
		}
		// Imprime la matriz de distancia m�s corta
		printSolution(dist);
	}

	void printSolution(int dist[][]) {
		System.out
				.println("\r\n" + "La siguiente matriz muestra la mas corta " + "distancia entre cada par de v�rtices");
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
	// Programa de controlador para probar la funci�n anterior
	public static void main(String[] args) {
		int graph[][] = { { 0, 5, INF, 10 }, { INF, 0, 3, INF }, { INF, INF, 0, 1 }, { INF, INF, INF, 0 } };
		AllPairShortestPath a = new AllPairShortestPath();
		// Imprime la solucion
		a.floydWarshall(graph);
	}
}