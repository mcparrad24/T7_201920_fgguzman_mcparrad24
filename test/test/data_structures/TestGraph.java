package test.data_structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.junit.Test;

import com.opencsv.CSVReader;

import model.data_structures.Graph;
import model.logic.Info;
import model.logic.Vertice;
import model.logic.ZonaJSON;

public class TestGraph {

	private Graph Grafo = new Graph(10);

	public void setUp1() {
		Info info1 = new Info("-74.08921298299998", "4.582989396000016", "275");
		Vertice v1 = new Vertice("0", info1);
		Info info2 = new Info("-74.09175497299998", "4.5795689170000164", "684");
		Vertice v2 = new Vertice("1", info2);
		Info info3 = new Info("-74.08656902000001", "4.60729538999999", "35");
		Vertice v3 = new Vertice("2", info3);
		Info info4 = new Info("-74.07312877999998", "4.648768679999988", "1035");
		Vertice v4 = new Vertice("3", info4);
		Info info5 = new Info("-74.05100413999997", "4.724598160000029", "96");
		Vertice v5 = new Vertice("4", info5);
		Info info6 = new Info("-74.20408764000003", "4.610146419999976", "1");
		Vertice v6 = new Vertice("5", info6);
		Info info7 = new Info("-74.16112719", "4.615544890000023", "1099");
		Vertice v7 = new Vertice("6", info7);
		Info info8 = new Info("-74.06925505999997", "4.674469439999996", "833");
		Vertice v8 = new Vertice("7", info8);
		Info info9 = new Info("-74.08833413000002", "4.619344660000023", "104");
		Vertice v9 = new Vertice("8", info9);
		Info info10 = new Info("-74.16485762000002", "4.4879909200000165", "37");
		Vertice v10 = new Vertice("9", info10);
		
		Grafo.addVertex(v1.getId(), v1.getInfo());
		Grafo.addVertex(v2.getId(), v2.getInfo());
		Grafo.addVertex(v3.getId(), v3.getInfo());
		Grafo.addVertex(v4.getId(), v4.getInfo());
		Grafo.addVertex(v5.getId(), v5.getInfo());
		Grafo.addVertex(v6.getId(), v6.getInfo());
		Grafo.addVertex(v7.getId(), v7.getInfo());
		Grafo.addVertex(v8.getId(), v8.getInfo());
		Grafo.addVertex(v9.getId(), v9.getInfo());
		Grafo.addVertex(v10.getId(), v10.getInfo());
		
		Grafo.addEdge(v1.getId(), v2.getId(), 10);
		Grafo.addEdge(v2.getId(), v4.getId(), 5);
		Grafo.addEdge(v5.getId(), v8.getId(), 8);
		Grafo.addEdge(v8.getId(), v10.getId(), 13);
		Grafo.addEdge(v1.getId(), v5.getId(), 6);
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
	public void testAddVertex1() {
		setUp1();
		Info info11 = new Info("-74.03610183", "4.610830000000021", "7");
		Vertice v11 = new Vertice("11", info11);
		Grafo.addVertex(v11.getId(), v11.getInfo());
		assertEquals("No se agrego el vertice correctamente", 11, Grafo.V());
	}

	@Test
	public void testAddVertex2() {
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
		Info info = new Info("-74.08921298299998", "4.582989396000016", "275");
		Info infoComparar = (Info) Grafo.getInfoVertex(0);
		assertEquals("La información del vertice es incorrecta", info, infoComparar);
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
		double costoReal = Grafo.getCostArc(4, 7);
		assertEquals("El costo del arco es incorrecto", 8, costoReal);
	}
	
	@Test
	public void testSetCostArc() {
		setUp1();
		double nuevoCosto = 10;
		Grafo.setCostArc(4, 7, nuevoCosto);
		assertEquals("El costo del arco no se actualizo correctamente", nuevoCosto, Grafo.getCostArc(4, 7));
	}
	
	@Test
	public void testAdj() {
	
	}
	
	@Test
	public void testUncheck() {
		
	}
	
	@Test
	public void testDfs() {
		
	}
	
	@Test
	public void testCcn() {
		
	}
	
	@Test
	public void testGetCC() {
		
	}
}

