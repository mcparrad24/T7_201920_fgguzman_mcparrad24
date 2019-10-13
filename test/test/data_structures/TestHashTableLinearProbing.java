package test.data_structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import com.opencsv.CSVReader;

import model.data_structures.HashTableLinearProbing;
import model.logic.UberTrip;

public class TestHashTableLinearProbing {
	
	private HashTableLinearProbing<String, String> linPr = new HashTableLinearProbing(0);
	
	public void setUp1() {
		CSVReader reader = null;
		String[] header = new String[1];
		UberTrip carga;
		try {
			reader = new CSVReader(new FileReader("./docs/DatosPrueba.csv"));
			header = reader.readNext();
			for (String[] nextLine : reader) {
				carga = new UberTrip(nextLine[0], nextLine[1], nextLine[2], nextLine[3], nextLine[4], 1);
				linPr.put(carga.darLlave(), carga.darValor());
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
		int tam = linPr.darTamano();
		assertEquals("El tamano de la tabla no es correcto", 35, tam);
	}
	
	@Test
	public void testInsertar1() {
		setUp1();
		String[] nuevo = {"4", "58", "10", "1474.35", "483.6", "1414.18", "1.32"};
		UberTrip nuevoDato = new UberTrip(nuevo[0], nuevo[1], nuevo[2], nuevo[3],nuevo[4], 1);
		linPr.put(nuevoDato.darLlave(), nuevoDato.darValor());
		assertEquals("No se agrego el dato correctamente", 36, linPr.darTamano());
	}
	
	@Test
	public void testInsertar2() {
		setUp1();
		boolean siEs = false;
		String[] nuevo = {"4", "58", "10", "1474.35", "483.6", "1414.18", "1.32"};
		UberTrip nuevoDato = new UberTrip(nuevo[0], nuevo[1], nuevo[2], nuevo[3], nuevo[4], 1);
		linPr.put(nuevoDato.darLlave(), nuevoDato.darValor());
		String key = 1+"-"+4+"-"+58;
		String valor = linPr.get(key);
		if (nuevoDato.darValor().equals(valor) && nuevoDato.darLlave().equals(key)) {
			siEs = true;
		}
		assertEquals("El dato no se agrego correctamente", true, siEs);
	}
	
	@Test
	public void testEliminarMax1() {
		setUp1();
		String key = 1+"-"+1141+"-"+416;
		linPr.delete(key);
		assertEquals("No se elimino el dato correctamente", 34, linPr.darTamano());
	}
	
	@Test
	public void testEliminarMax2() {
		setUp1();
		String key = 1+"-"+1141+"-"+416;
		String valor = linPr.delete(key);
		boolean siEs = false;
		if (valor.equals(1+","+1141+","+416+","+3+","+2094.83)) {
			siEs = true;
		}
		assertEquals("No se elimino el dato correctamente", true, siEs);
	}

	@Test
	public void testIsEmpty() {
		setUp1();
		boolean empty = linPr.isEmpty();
		assertEquals("La tabla no esta vacia", false, empty);
	}
	
	@Test
	public void testGet() {
		setUp1();
		String key = 1+"-"+903+"-"+900;
		String value = 1+","+903+","+900+","+2+","+341.25;
		String valorEsp = linPr.get(key);
		boolean siEs = false;
		if (valorEsp.equals(value)) {
			siEs = true;
		}
		assertEquals("No se encontro el dato correctamente", true, siEs);
	}

	@Test
	public void testRehash() {
		setUp1();
		double dado = (1.0 * linPr.darTamano()/64);
		assertEquals("La carga no se disminuye", 0.546875,dado,0.001);
	}
}
