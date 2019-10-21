package test.data_structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.opencsv.CSVReader;

import model.data_structures.RedBlackBST;
import model.logic.ZonaJSON;

public class TestRedBlackBST {

	private RedBlackBST<Integer, String> arbol = new RedBlackBST<Integer, String>();
	private ZonaJSON zonas;

	public void setUp1() {
			CSVReader reader = null;
			String archivo1 = "./docs/DatosPrueba.csv";
			String[] header = new String[1];
			ZonaJSON carga = null;
			int i = 4;
			try {
				reader = new CSVReader(new FileReader(archivo1));
				header = reader.readNext();
				for (String[] nextLine : reader) {
					carga = new ZonaJSON(nextLine[0], nextLine[1], nextLine[2], nextLine[3]);
					carga.setPtosGeo(i);
					arbol.insertar(carga.getId(), carga.getValor());
					i++;
				}
		} catch (Exception e) {
			fail("Fallo la lectura del archivo CSV " + e.getStackTrace()[0]);
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
		assertEquals("El tamano de la tabla no es correcto", 10, tam);
	}

	@Test
	public void testInsertar1() {
		setUp1();
		String[] nuevo = { "1077", "EL DORADO", "0.05429429289", "0.00010996928"};
		ZonaJSON nuevoDato = new ZonaJSON(nuevo[0], nuevo[1], nuevo[2], nuevo[3]);
		nuevoDato.setPtosGeo(6);
		arbol.insertar(nuevoDato.getId(), nuevoDato.getValor());
		assertEquals("No se agrego el dato correctamente", 11, arbol.darTamano());
	}

	@Test
	public void testInsertar2() {
		setUp1();
		boolean siEs = false;
		String[] nuevo = { "1077", "EL DORADO", "0.05429429289", "0.00010996928"};
		ZonaJSON nuevoDato = new ZonaJSON(nuevo[0], nuevo[1], nuevo[2], nuevo[3]);
		nuevoDato.setPtosGeo(6);
		arbol.insertar(nuevoDato.getId(), nuevoDato.getValor());
		int key = 1077;
		String valor = "EL DORADO" + "," + "0.05429429289" + "," + "0.00010996928" + "," + 6;
		if (nuevoDato.getValor().equals(valor) && nuevoDato.getId() == key) {
			siEs = true;
		}
		assertEquals("El dato no se agrego correctamente", true, siEs);
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
		int key = 1;
		String value = "LOS LAURELES" + "," + "0.02774133557" + "," + "0.00003682838" + "," + 4;
		String valorEsp = (String) arbol.get(key);
		boolean siEs = false;
		if (valorEsp.equals(value)) {
			siEs = true;
		}
		assertEquals("No se encontro el dato correctamente", true, siEs);
	}

	@Test
	public void testAltura() {

	}

	@Test
	public void testAlturaLlave() {

	}

	@Test
	public void testContains() {
		setUp1();
		int key = 8;
		boolean contiene = arbol.contains(key);
		assertEquals("No se encontro el dato correctamente", true, contiene);
	}

	@Test
	public void testLlaveMin() {

	}

	@Test
	public void testLlaveMax() {

	}

	@Test
	public void testCheck() {

	}

	@Test
	public void testLlavesRango() {

	}

	@Test
	public void testValoresRango() {

	}
}
