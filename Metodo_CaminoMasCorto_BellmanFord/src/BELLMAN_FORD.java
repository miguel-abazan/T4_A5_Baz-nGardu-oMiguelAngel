import java.util.*;
import java.lang.*;
import java.io.*;

//geeksforgeeks
class Graph {
	// Una clase para representar una arista ponderada en el gráfico
	class Edge {
		int src, dest, weight;

		Edge() {
			src = dest = weight = 0;
		}
	};

	int V, E;
	Edge edge[];

	// Crea un gráfico con vértices V y bordes E
	Graph(int v, int e) {
		V = v;
		E = e;
		edge = new Edge[e];
		for (int i = 0; i < e; ++i)
			edge[i] = new Edge();
	}

	void BellmanFord(Graph graph, int src) {
		int V = graph.V, E = graph.E;
		int dist[] = new int[V];

		// Paso 1: Inicializa distancias desde src a todas las demás
		// vértices como INFINITO
		for (int i = 0; i < V; ++i)
			dist[i] = Integer.MAX_VALUE;
		dist[src] = 0;

		// Paso 2: relaja todos los bordes | V | - 1 vez. Un simple
		// ruta más corta desde src a cualquier otra lata de vértices
		// tener como máximo | V | - 1 bordes
		for (int i = 1; i < V; ++i) {
			for (int j = 0; j < E; ++j) {
				int u = graph.edge[j].src;
				int v = graph.edge[j].dest;
				int weight = graph.edge[j].weight;
				if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v])
					dist[v] = dist[u] + weight;
			}
		}

		// Paso 3: verifica los ciclos de peso negativo. Lo anterior
		// el paso garantiza distancias más cortas si el gráfico no lo hace
		// contiene ciclo de peso negativo. Si nos ponemos más cortos
		// ruta, entonces hay un ciclo.
		for (int j = 0; j < E; ++j) {
			int u = graph.edge[j].src;
			int v = graph.edge[j].dest;
			int weight = graph.edge[j].weight;
			if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
				System.out.println("Graph contains negative weight cycle");
				return;
			}
		}
		printArr(dist, V);
	}

	void printArr(int dist[], int V) {
		System.out.println("Vertex Distance from Source");
		for (int i = 0; i < V; ++i)
			System.out.println(i + "\t\t" + dist[i]);
	}

	public static void main(String[] args) {
		int V = 5; // Numeros de vertices en el grafo
		int E = 8; // Numeros de bordes en el grafo

		Graph graph = new Graph(V, E);

		// añadir borde de 0 - 1 (o A - B en la figura)
		graph.edge[0].src = 0;
		graph.edge[0].dest = 1;
		graph.edge[0].weight = -1;

		graph.edge[1].src = 0;
		graph.edge[1].dest = 2;
		graph.edge[1].weight = 4;

		graph.edge[2].src = 1;
		graph.edge[2].dest = 2;
		graph.edge[2].weight = 3;

		graph.edge[3].src = 1;
		graph.edge[3].dest = 3;
		graph.edge[3].weight = 2;

		graph.edge[4].src = 1;
		graph.edge[4].dest = 4;
		graph.edge[4].weight = 2;

		graph.edge[5].src = 3;
		graph.edge[5].dest = 2;
		graph.edge[5].weight = 5;

		graph.edge[6].src = 3;
		graph.edge[6].dest = 1;
		graph.edge[6].weight = 1;

		graph.edge[7].src = 4;
		graph.edge[7].dest = 3;
		graph.edge[7].weight = -3;

		graph.BellmanFord(graph, 0);
	}
}