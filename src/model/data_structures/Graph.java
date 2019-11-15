package model.data_structures;

import java.util.Iterator;
import model.logic.Vertice;
import model.logic.Arco;
import model.logic.Info;
import model.logic.Queue;

public class Graph <K extends Comparable<K>, Val> implements IGraph<K, Val> {
	//C�digo tomado de: https://github.com/kevin-wayne/algs4/blob/master/src/main/java/edu/princeton/cs/algs4/Graph.java
	//					https://github.com/kevin-wayne/algs4/blob/master/src/main/java/edu/princeton/cs/algs4/DepthFirstPaths.java
	//Copyright 2002-2018, Robert Sedgewick and Kevin Wayne.

	private static final String NEWLINE = System.getProperty("line.separator");

	private final int V;
	private int E;
	private HashTableLinearProbing<K, Vertice> adj;
	private HashTableLinearProbing<K, Boolean> marked;
	private HashTableLinearProbing<K, Integer> cc;
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
		adj = new HashTableLinearProbing(V);
		marked = new HashTableLinearProbing(V);
		cc = new HashTableLinearProbing(V);
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
	public void addEdge(K idVertexIni, K idVertexFin, double cost) {
		E++;
		Vertice v1 = (Vertice) adj.get(idVertexIni);
		Vertice v2 = (Vertice) adj.get(idVertexFin);
		String costoArco = String.valueOf(cost);
		String idv1 = String.valueOf(idVertexIni);
		String idv2 = String.valueOf(idVertexFin);
		Arco nuevo = new Arco(idv1, idv2, costoArco);
		v1.getAdj().enqueue(v2);
		v2.getAdj().enqueue(v1);
		v1.getArcos().enqueue(nuevo);
		v2.getArcos().enqueue(nuevo);
	}

	/**
	 * 
	 *
	 * @param  v one vertex in the edge
	 */
	public void addVertex(K idVertex, Val infoVertex) {
		String id = String.valueOf(idVertex);
		Vertice nuevo = new Vertice(id, (Info)infoVertex);
		adj.put(idVertex, nuevo);
		marked.put(idVertex, false);
	}

	/**
	 * Returns the vertices adjacent to vertex {@code v}.
	 *
	 * @param  v the vertex
	 * @return the vertices adjacent to vertex {@code v}, as an iterable
	 * @throws IllegalArgumentException unless {@code 0 <= v < V}
	 */
	public Iterable<K> adj(K idVertex) {
		Queue<K> ids = new Queue<K>();
		Vertice v1 = (Vertice)adj.get(idVertex);
		Queue<Vertice> adyacentes = v1.getAdj();
		int tam = adyacentes.size();
		for (int i = 0; i < tam; i++) {
			Vertice actual = adyacentes.dequeue();
			K idActual = (K)(Object)actual.getId();
			ids.enqueue(idActual);
			adyacentes.enqueue(actual);
		}
		Queue<K> adya = (Queue<K>) ids;
		return adya;
	}

	/**
	 * Computes the connected components of the undirected graph {@code G}.
	 *
	 * @param G the undirected graph
	 */
	public void CC() {
		Iterator<K> marcados = (Iterator<K>) marked.keys();
		while(marcados.hasNext()) {
			K llave = marcados.next();
			boolean valor = (boolean)marked.get(llave);
			if (!valor) {
				dfs(llave);
				count++;	
			}
		}
	}

	public void dfs(K v) {
		marked.put(v, true);
		Vertice vAct = (Vertice) adj.get(v);
		Queue<Vertice> adyacentes = vAct.getAdj();
		int tam = adyacentes.size();
		for (int i = 0; i < tam; i++) {
			Vertice actual = adyacentes.dequeue();
			boolean valor = marked.get((K) actual);
			if (!valor) {
				K id = (K)(Object)actual.getId();
				dfs(id);
			}
			adyacentes.enqueue(actual);
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
	public Val getInfoVertex(K idVertex) {
		Vertice v1 = (Vertice)adj.get(idVertex);
		Val infoV = (Val)v1.getInfo();
		return infoV;
	}

	@Override
	public void setInfoVertex(K idVertex, Val infoVertex) {
		Vertice v1 =(Vertice)adj.get(idVertex);
		v1.setInfo((Info)infoVertex);

	}

	@Override
	public double getCostArc(K idVertexIni, K idVertexFin) {
		double costo = -1;
		Vertice vIn = (Vertice)adj.get(idVertexIni);
		Queue<Arco> arcos = vIn.getArcos();
		int tam = arcos.size();
		boolean encontrado = false;
		for (int i = 0; i < tam && !encontrado; i++) {
			Arco actual = arcos.dequeue();
			if (actual.getIdInicio() == (Integer)idVertexIni && actual.getIdFinal() == (Integer)idVertexFin) {
				costo =  actual.getCosto();
			}
		}
		return costo;
	}

	@Override
	public void setCostArc(K idVertexIni, K idVertexFin, double cost) {
		Vertice vIn = (Vertice)adj.get(idVertexIni);
		Queue<Arco> arcos = vIn.getArcos();
		int tam = arcos.size();
		boolean encontrado = false;
		for (int i = 0; i < tam && !encontrado; i++) {
			Arco actual = arcos.dequeue();
			if (actual.getIdInicio() == (Integer)idVertexIni && actual.getIdFinal() == (Integer)idVertexFin) {
				actual.setCostArc(cost);
			}
			arcos.enqueue(actual);
		}

	}

	@Override
	public void uncheck() {
		Iterator<Vertice> marca = (Iterator<Vertice>) marked.keys();
		while (marca.hasNext()) {
			Vertice actual = marca.next();
			K id = (K)(Object)actual.getId();
			marked.put(id, false);
		}
	}

	@Override
	public Iterable<K> getCC(K idVertex) {
		Queue<K> queue = new Queue<K>();
		int ccID = cc.get(idVertex);
		Iterator<K> ccs = (Iterator<K>) cc.keys();
		while (ccs.hasNext()) {
			K llave = ccs.next();
			int valorAct = cc.get(llave);
			if(valorAct == ccID){
				queue.enqueue(llave);
			}
		}
		return queue;
	}
	
	public HashTableLinearProbing<K, Boolean> marcados(){
		return marked;
	}
}