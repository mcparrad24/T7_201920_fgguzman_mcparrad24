package model.data_structures;

import java.util.Iterator;
import model.logic.Vertice;
import model.logic.Arco;
import model.logic.Queue;

public class Graph <K extends Comparable<K>, Val> implements IGraph<K, Val> {
	//C�digo tomado de: https://github.com/kevin-wayne/algs4/blob/master/src/main/java/edu/princeton/cs/algs4/Graph.java
	//					https://github.com/kevin-wayne/algs4/blob/master/src/main/java/edu/princeton/cs/algs4/DepthFirstPaths.java
	//Copyright 2002-2018, Robert Sedgewick and Kevin Wayne.
	
	private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;
    private int E;
    private Bag<K>[] adj;
    
    private boolean[] marked;   // marked[v] = has vertex v been marked?
    private int[] id;           // id[v] = id of connected component containing v
    private int[] size;         // size[id] = number of vertices in given component
    private int count;          // number of connected components
    
    /**
     * Initializes an empty graph with {@code V} vertices and 0 edges.
     * param V the number of vertices
     *
     * @param  V number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = (Bag<K>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<K>();
        }
    }

    /**
     * Returns the number of vertices in this graph.
     *
     * @return the number of vertices in this graph
     */
    public int V() {
        return V;
    }

    /**
     * Returns the number of edges in this graph.
     *
     * @return the number of edges in this graph
     */
    public int E() {
        return E;
    }

    /**
     * Adds the undirected edge v-w to this graph.
     *
     * @param  v one vertex in the edge
     * @param  w the other vertex in the edge
     * @throws IllegalArgumentException unless both {@code 0 <= v < V} and {@code 0 <= w < V}
     */
    public void addEdge(int idVertexIni, int idVertexFin, double cost) {
        E++;
        adj[idVertexIni].add(idVertexFin);
        adj[idVertexFin].add(idVertexIni);
        Arco nuevo = new Arco(idVertexIni, idVertexFin, cost);
    }


    /**
     * Returns the vertices adjacent to vertex {@code v}.
     *
     * @param  v the vertex
     * @return the vertices adjacent to vertex {@code v}, as an iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<K> adj(int idVertex) {
        return adj[idVertex];
    }
    
    /**
     * Computes the connected components of the undirected graph {@code G}.
     *
     * @param G the undirected graph
     */
    public void CC() {
        marked = new boolean[V()];
        id = new int[V()];
        size = new int[V()];
        for (int v = 0; v < V(); v++) {
            if (!marked[v]) {
                dfs(v);
                count++;
            }
        }
    }
    
    private void dfs(K v) {
        marked[v] = true;
        id[v] = count;
        size[count]++;
        for (int w : adj(v)) {
            if (!marked[w]) {
                dfs(w);
            }
        }
    }
    
    /**
     * Returns the number of connected components in the graph {@code G}.
     *
     * @return the number of connected components in the graph {@code G}
     */
    public int ccn() {
        return count;
    }

	@Override
	public Val getInfoVertex(Key idVertex) {
		return idVertex.getInfo();
	}

	@Override
	public void setInfoVertex(Key idVertex, Value infoVertex) {
		idVertex.setInfo(infoVertex);
		
	}

	@Override
	public double getCostArc(Key idVertexIni, Key idVertexFin) {
		
		return 0;
	}

	@Override
	public void setCostArc(Key idVertexIni, Key idVertexFin, double cost) {
		
		
	}

	@Override
	public void uncheck() {
		marked[v] = false;
        id[v] = count;
        size[count]++;
        for (int w : adj(v)) {
            if (marked[w]) {
                uncheck(w);
            }
        }
	}

	@Override
	public Iterable<Key> getCC(Key idVertex) {
		//Preguntar
		return null;
	}
}