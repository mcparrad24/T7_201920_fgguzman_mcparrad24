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
		Gson gson = new Gson();
		String path = "./data/bogota_cadastral.json";
		JsonReader reader = null;
		int i = 0, pts;
		try {
			reader = new JsonReader(new FileReader(path));
			JsonElement elem = JsonParser.parseReader(reader);
			JsonElement e = elem.getAsJsonObject().get("features").getAsJsonArray().get(0).getAsJsonObject().get("properties");
			while(i < elem.getAsJsonObject().get("features").getAsJsonArray().size()) {
				e = elem.getAsJsonObject().get("features").getAsJsonArray().get(i).getAsJsonObject().get("properties");
				pts = elem.getAsJsonObject().get("features").getAsJsonArray().get(0).getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray().size();
				zonas = gson.fromJson(e, ZonaJSON.class);
				zonas.setPtosGeo(pts);
				arbol.insertar(zonas.getId(),zonas.getValor());
				i++;
			}
		} catch (Exception e) {
			fail("Fallo la lectura del archivo JSON " + e.getStackTrace()[0]);
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
		String[] nuevo = { "4", "58", "10", "1474.35", "483.6", "1414.18", "1.32" };
		UberTrip nuevoDato = new UberTrip(nuevo[0], nuevo[1], nuevo[2], nuevo[3], nuevo[4], 1);
		arbol.insertar(nuevoDato.darLlave(), nuevoDato.darValor());
		assertEquals("No se agrego el dato correctamente", 36, arbol.darTamano());
	}

	@Test
	public void testInsertar2() {
		setUp1();
		boolean siEs = false;
		String[] nuevo = { "4", "58", "10", "1474.35", "483.6", "1414.18", "1.32" };
		UberTrip nuevoDato = new UberTrip(nuevo[0], nuevo[1], nuevo[2], nuevo[3], nuevo[4], 1);
		arbol.insertar(nuevoDato.darLlave(), nuevoDato.darValor());
		String key = 1 + "-" + 4 + "-" + 58;
		String valor = (String) arbol.get(key);
		if (nuevoDato.darValor().equals(valor) && nuevoDato.darLlave().equals(key)) {
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
		String key = 1 + "-" + 903 + "-" + 900;
		String value = 1 + "," + 903 + "," + 900 + "," + 2 + "," + 341.25;
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
