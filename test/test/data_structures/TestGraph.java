package test.data_structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.junit.Test;

import com.opencsv.CSVReader;

import model.data_structures.Graph;
import model.data_structures.HashTableLinearProbing;
import model.logic.Info;
import model.logic.Queue;
import model.logic.Vertice;

public class TestGraph {

	private Graph<Integer, Info> Grafo = new Graph(10);

	public void setUp1() {
		Info info1 = new Info("-74.08921298299998", "4.582989396000016", "275");
		int id1 = 0;
		Grafo.addVertex(id1, info1);
		Info info2 = new Info("-74.09175497299998", "4.5795689170000164", "684");
		int id2 = 1;
		Grafo.addVertex(id2, info2);
		Info info3 = new Info("-74.08656902000001", "4.60729538999999", "35");
		int id3 = 2;
		Info info4 = new Info("-74.07312877999998", "4.648768679999988", "1035");
		int id4 = 3;
		Info info5 = new Info("-74.05100413999997", "4.724598160000029", "96");
		int id5 = 4;
		Info info6 = new Info("-74.20408764000003", "4.610146419999976", "1");
		int id6 = 5;
		Info info7 = new Info("-74.16112719", "4.615544890000023", "1099");
		int id7 = 6;
		Info info8 = new Info("-74.06925505999997", "4.674469439999996", "833");
		int id8 = 7;
		Info info9 = new Info("-74.08833413000002", "4.619344660000023", "104");
		int id9 = 8;
		Info info10 = new Info("-74.16485762000002", "4.4879909200000165", "37");
		int id10 = 9;
		
		Grafo.addVertex(id3, info3);
		Grafo.addVertex(id4, info4);
		Grafo.addVertex(id5, info5);
		Grafo.addVertex(id6, info6);
		Grafo.addVertex(id7, info7);
		Grafo.addVertex(id8, info8);
		Grafo.addVertex(id9, info9);
		Grafo.addVertex(id10, info10);
		
		Grafo.addEdge(id1, id2, 10);
		Grafo.addEdge(id2, id4, 5);
		Grafo.addEdge(id5, id8, 8);
		Grafo.addEdge(id8, id10, 13);
		Grafo.addEdge(id1, id5, 6);
	}

	@Test
	public void testV() {
		setUp1();
		int tam = Grafo.V();
		assertEquals("El tamano del grafo no es correcto", 10, tam);
	}
	
	@Test
	public void testE() {
		setUp1();
		int tam = Grafo.E();
		assertEquals("El tamano del grafo no es correcto", 5, tam);
	}

	@Test
	public void testAddVertex() {
		setUp1();
		boolean siEs = false;
		Info info11 = new Info("-74.03610183", "4.610830000000021", "7");
		Vertice v11 = new Vertice("10", info11);
		Grafo.addVertex(v11.getId(), v11.getInfo());
		int key = 10;
		Info valor = info11;
		if (v11.getInfo().equals(valor) && v11.getId() == key) {
			siEs = true;
		}
		assertEquals("El vertice no se agrego correctamente", true, siEs);
	}
	
	@Test
	public void testAddArc1() {
		setUp1();
		Grafo.addEdge(9, 3, 11);
		assertEquals("No se agrego el arco correctamente", 6, Grafo.E());
	}
	
	@Test
	public void testGetInfoVertex() {
		setUp1();
		boolean siEs = false;
		Info info = new Info("-74.08921298299998", "4.582989396000016", "275");
		Info infoComparar = (Info) Grafo.getInfoVertex(0);
		if (info.getLng() == infoComparar.getLng() && info.getLat() == infoComparar.getLat() && info.getMOVEMENT_ID() == infoComparar.getMOVEMENT_ID()) {
			siEs = true;
		}
		assertEquals("La información del vertice es incorrecta", true, siEs);
	}
	
	@Test
	public void testSetInfoVertex() {
		setUp1();
		Info info = new Info("-74.12957995", "4.425596960000006", "42");
		Grafo.setInfoVertex(0, info);
		Info infoAct = (Info) Grafo.getInfoVertex(0);
		assertEquals("La información del vertice no se actualizo correctamente", info, infoAct);
	}
	
	@Test
	public void testGetCostArc() {
		setUp1();
		boolean siEs = false;
		double costoReal = Grafo.getCostArc(4, 7);
		if (costoReal == 8) {
			siEs = true;
		}
		assertEquals("El costo del arco es incorrecto", true, siEs);
	}
	
	@Test
	public void testSetCostArc() {
		setUp1();
		boolean siEs = false;
		Grafo.setCostArc(4, 7, 10);
		double costo = Grafo.getCostArc(4, 7);
		if (costo == 10) {
			siEs = true;
		}
		assertEquals("El costo del arco no se actualizo correctamente", true, siEs);
	}
	
	@Test
	public void testAdj() {
		setUp1();
		boolean igual = true;
		Iterator it = (Iterator) Grafo.adj(0);
		int idActual = (int) it.next();
		while (it.hasNext()) {
			if (idActual != 1 && idActual != 4) {
				igual = false;
			}
			idActual = (int) it.next();
		}
		assertEquals("Los ID's de los vertices adyacentes no son los correctos", true, igual);
	}
	
	@Test
	public void testUncheck() {
		setUp1();
		boolean siEs = true;
		Grafo.dfs(1);
		Grafo.uncheck();
		HashTableLinearProbing<Integer, Boolean> tabla = Grafo.marcados();
		Iterator<Integer> llaves = (Iterator<Integer>) tabla.keys();
		int llaveAct = llaves.next();
		while (llaves.hasNext()) {
			boolean valor = tabla.get(llaveAct);
			if (valor) {
				siEs = false;
			}
			
		}
		assertEquals("No se desmarcaron los vertices correctamente", true, siEs);
	}
	
	@Test
	public void testDfs() {
		boolean siEs = true;
		Grafo.dfs(1);
		HashTableLinearProbing<Integer, Boolean> tabla = Grafo.marcados();
		Iterator<Integer> llaves = (Iterator<Integer>) tabla.keys();
		int llaveAct = llaves.next();
		while (llaves.hasNext()) {
			boolean valor = tabla.get(llaveAct);
			if (llaveAct == 0 || llaveAct == 1 || llaveAct == 3 || llaveAct == 4 || llaveAct == 7 || llaveAct == 9) {
				if (!valor) {
					siEs = false;
				}
			}
			
		}
		assertEquals("DFS no funciona correctamente", true, siEs);
	}
	
	@Test
	public void testCcn() {
		setUp1();
		int numReal = 5;
		assertEquals("El numero de componentes conexos es incorrecto", numReal, Grafo.ccn());
	}
	
	@Test
	public void testGetCC() {
		setUp1();
		Grafo.getCC(0);
	}
}

