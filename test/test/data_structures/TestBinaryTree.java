package test.data_structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import com.opencsv.CSVReader;

import model.data_structures.BinaryTree;
import model.logic.UberTrip;

public class TestBinaryTree {
	
	private BinaryTree arbol = new BinaryTree();
	
	public void setUp1() {
		CSVReader reader = null;
		String[] header = new String[1];
		UberTrip carga;
		try {
			reader = new CSVReader(new FileReader("./docs/DatosPrueba.csv"));
			header = reader.readNext();
			for (String[] nextLine : reader) {
				carga = new UberTrip(nextLine[0], nextLine[1], nextLine[2], nextLine[3], nextLine[4], 1);
				arbol.insertar(carga.darLlave(), carga.darValor());
			}

		} catch (Exception e) {
			fail("Fallo la lectura del archivo csv "+e.getStackTrace()[0]);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					fail("No se pudo cerrar el lector");
				}	
			}
		}
	}
	
	@Test
	public void testDarTamano() {
		setUp1();
		int tam = arbol.darTamano();
		assertEquals("El tamano de la tabla no es correcto", 35, tam);
	}
	
	@Test
	public void testInsertar1() {
		setUp1();
		String[] nuevo = {"4", "58", "10", "1474.35", "483.6", "1414.18", "1.32"};
		UberTrip nuevoDato = new UberTrip(nuevo[0], nuevo[1], nuevo[2], nuevo[3],nuevo[4], 1);
		arbol.insertar(nuevoDato.darLlave(), nuevoDato.darValor());
		assertEquals("No se agrego el dato correctamente", 36, arbol.darTamano());
	}
	
	@Test
	public void testInsertar2() {
		setUp1();
		boolean siEs = false;
		String[] nuevo = {"4", "58", "10", "1474.35", "483.6", "1414.18", "1.32"};
		UberTrip nuevoDato = new UberTrip(nuevo[0], nuevo[1], nuevo[2], nuevo[3], nuevo[4], 1);
		arbol.insertar(nuevoDato.darLlave(), nuevoDato.darValor());
		String key = 1+"-"+4+"-"+58;
		String valor = (String) arbol.get(key);
		if (nuevoDato.darValor().equals(valor) && nuevoDato.darLlave().equals(key)) {
			siEs = true;
		}
		assertEquals("El dato no se agrego correctamente", true, siEs);
	}
	
	@Test
	public void testEliminar() {
		setUp1();
		String key = 1+"-"+1141+"-"+416;
		arbol.eliminar(key);
		assertEquals("No se elimino el dato correctamente", 34, arbol.darTamano());
	}

	@Test
	public void testIsEmpty() {
		setUp1();
		boolean empty = arbol.isEmpty();
		assertEquals("La tabla no esta vacia", false, empty);
	}
	
	@Test
	public void testGet() {
		setUp1();
		String key = 1+"-"+903+"-"+900;
		String value = 1+","+903+","+900+","+2+","+341.25;
		String valorEsp = (String) arbol.get(key);
		boolean siEs = false;
		if (valorEsp.equals(value)) {
			siEs = true;
		}
		assertEquals("No se encontro el dato correctamente", true, siEs);
	}
}
