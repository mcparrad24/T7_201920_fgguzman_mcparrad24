package model.data_structures;

import java.util.Iterator;

public interface IGraph <K, V> {
	
	int V();
	
	int E();
	
	void addEdge(K idVertexIni, K idVertexFin, double cost);
	
	V getInfoVertex(K idVertex);
	
	void setInfoVertex(K idVertex, V infoVertex);
	
	double getCostArc(K idVertexIni, K idVertexFin);
	
	void setCostArc(K idVertexIni, K idVertexFin, double cost);
	
	void addVertex(K idVertex, V infoVertex);
	
	Iterable<K> adj(K idVertex);
	
	void uncheck();
	
	void dfs(K s);
	
	int ccn();
	
	Iterable<K> getCC(K idVertex);
	
}
