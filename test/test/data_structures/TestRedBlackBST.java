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

	private RedBlackBST arbol = new RedBlackBST();
	private ZonaJSON zonas;

	public void JSONLector() {
		String str = "{\"type\":\"FeatureCollection\",\"features\":[{\"type\":\"Feature\",\"geometry\":{\"type\":\"MultiPolygon\",\"coordinates\":[[[[-74.200295,4.617249],[-74.200285,4.617248],[-74.200277,4.617248],[-74.200257,4.617246] ...]]],\"properties\":{\"cartodb_id\":12,\"scacodigo\":\"004575\",\"scatipo\":0,\"scanombre\":\"LOS LAURELES\",\"shape_leng\":0.02774133557,\"shape_area\":0.00003682838,\"MOVEMENT_ID\":\"1\",\"DISPLAY_NAME\":\"LOS LAURELES, 004575 (1)\"}}";

		JsonParser parser = new JsonParser();
		JsonObject element = (JsonObject)parser.parse(str);

		JsonElement responseWrapper = element.get("properties");
		Gson gson = new Gson();
		String path = "./data/bogota_cadastral.json";
		JsonReader reader = null;
		try {
			reader = new JsonReader(new FileReader(path));
			zonas = gson.fromJson(reader, ZonaJSON[].class);
			arbol.insertar(zonas.getId(), val);
			System.out.println(zonas);
		} catch (Exception e) {
			fail("Fallo la lectura del archivo JSON "+e.getStackTrace()[0]);
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
