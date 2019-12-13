import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;


public class DIJKSTRA {
	private static int matrix[][];
	private static int maxvertices;
	private static int maxaristas;
	private static int aristas;

	public static void crearGrafo(int nVertices, int nAristas) {
		maxvertices = nVertices;
		maxaristas = nAristas;
		aristas = 0;
		matrix = new int[maxvertices][maxvertices];
	}

	public static void insertaArista(int v1, int v2, int dist) {
		if (v1 >= maxvertices || v2 >= maxvertices) {
			throw new ArrayIndexOutOfBoundsException(
					"Vertices inválidos, fuera de rango" + "nRango de vertices: 0 - " + (maxvertices - 1));
		} else if (aristas == maxaristas) {
			throw new UnsupportedOperationException("No se puede añadir más aristas");
		} else {
			matrix[v1][v2] = dist;
			matrix[v2][v1] = dist;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String temp[] = br.readLine().split(" ");
		int vertices = Integer.parseInt(temp[0]);
		int aristas = Integer.parseInt(temp[1]);
// SE CREA EL GRAFO Y SE LLENA
		crearGrafo(vertices, aristas);
		for (int i = 0; i < vertices; i++) {
			String temp2[] = br.readLine().split(" ");
			insertaArista(Integer.parseInt(temp2[0]), Integer.parseInt(temp2[1]), Integer.parseInt(temp2[2]));
		}

//SE CALCULA LA DISTANCIA MINIMA ENTRE EL NODO 0 Y EL 2
		System.out.println(dijkistra(0)[2]);
	}

	// CALCULA LA DISTANCIA MAS CORTA
	public static int[] dijkistra(int inicio) {
		int[] distancia = new int[maxvertices];
		int[] padre = new int[maxvertices];
		boolean[] visto = new boolean[maxvertices];
		for (int i = 0; i < maxvertices; i++) {
			distancia[i] = 1200000000;
			padre[i] = -1;
			visto[i] = false;
		}
		distancia[inicio] = 0;
		PriorityQueue<Integer> pila = new PriorityQueue<>();
		pila.add(distancia[inicio]);
		while (!pila.isEmpty()) {
			int u = pila.poll();
			visto[u] = true;
			for (int i = 0; i < maxvertices; i++) {
				if (matrix[u][i] != 0) {
					// AQUI SE EDITA PARA QUE SE INCLUYA EL PARAMETRO QUE ES UN ARREGLO DE STRING
					if (distancia[i] > distancia[u] + matrix[u][i]) {
						distancia[i] = distancia[u] + matrix[u][i];
						padre[i] = u;
						pila.add(i);
					}
				}
			}
		}
		return distancia;
	}
}