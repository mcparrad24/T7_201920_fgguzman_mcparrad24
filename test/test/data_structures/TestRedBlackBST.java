package test.data_structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.junit.Test;

import com.opencsv.CSVReader;

import model.data_structures.RedBlackBST;
import model.logic.ZonaJSON;

public class TestRedBlackBST {

	private RedBlackBST<Integer, String> arbol = new RedBlackBST<Integer, String>();

	public void setUp1() {
			CSVReader reader = null;
			String archivo1 = "./docs/DatosPrueba.csv";
			ZonaJSON carga = null;
			int i = 4;
			try {
				reader = new CSVReader(new FileReader(archivo1));
				reader.readNext();
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
		setUp1();
		int a = 2;
		boolean siEs = false;
		if (a == arbol.altura()) {
			siEs = true;
		}
		assertEquals("No tiene la altura correcta", true, siEs);
	}

	@Test
	public void testAlturaLlave() {
		setUp1();
		int a = 1;
		boolean siEs = false;
		if (a == arbol.alturaLlave(2)) {
			siEs = true;
		}
		assertEquals("La llave no tiene la altura correcta"+arbol.alturaLlave(2), true, siEs);
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
		setUp1();
		int keyEsp = 1;
		String valueEsp = "LOS LAURELES" + "," + "0.02774133557" + "," + "0.00003682838" + "," + 4;
		int key = arbol.min();
		String value = arbol.get(key);
		boolean siEs = false;
		if(keyEsp == key && valueEsp.equals(value)) {
			siEs = true;
		}
		assertEquals("La llave dada no es la correcta", true, siEs);
	}

	@Test
	public void testLlaveMax() {
		setUp1();
		int keyEsp = 10;
		String valueEsp = "ARBORIZADORA ALTA" + "," + "0.02491132946" + "," + "0.00001679339" + "," + 13;
		int key = arbol.max();
		String value = arbol.get(key);
		boolean siEs = false;
		if(keyEsp == key && valueEsp.equals(value)) {
			siEs = true;
		}
		assertEquals("La llave dada no es la correcta", true, siEs);
	}

	@Test
	public void testCheck() {
		setUp1();
		boolean siEs = false;
		if(arbol.check()) {
			siEs = true;
		}
		assertEquals("No tiene las propiedades de arbol rojo negro", true, siEs);
	}

	@Test
	public void testLlavesRango() {
		setUp1();
		int keyEsp1 = 4;
		int keyEsp2 = 5;
		int keyEsp3 = 6;
		String valEsp1 = arbol.get(keyEsp1);
		String valEsp2 = arbol.get(keyEsp2);
		String valEsp3 = arbol.get(keyEsp3);
		int key2 = 0;
		int key3 = 0;
		Iterator<Integer> llavesIt = arbol.keysRango(4, 6);
		int key = (int) llavesIt.next();
		int key1 = key;
		int i = 0;
		while (llavesIt.hasNext()) {
			key = (int) llavesIt.next();
			if (i == 0) {
				key2 = key;
			}
			else if (i == 1) {
				key3 = key;
			}
			i++;
		}
		String val1 = arbol.get(key1);
		String val2 = arbol.get(key2);
		String val3 = arbol.get(key3);
		boolean siEs = false;
		if (keyEsp1 == key1 && keyEsp2 == key2 && keyEsp3 == key3 && valEsp1.equals(val1) && valEsp2.equals(val2) && valEsp3.equals(val3)) {
			siEs = true;
		}
		assertEquals("Las llaves del rango no son las correctas", true, siEs);
	}

	@Test
	public void testValoresRango() {
		setUp1();
		int key1 = 4;
		int key2 = 5;
		int key3 = 6;
		String valEsp1 = arbol.get(key1);
		String valEsp2 = arbol.get(key2);
		String valEsp3 = arbol.get(key3);
		String val2 = "";
		String val3 = "";
		Iterator<String> valsIt = arbol.valuesRango(4, 6);
		String val = (String) valsIt.next();
		String val1 = val;
		int i = 0;
		while (valsIt.hasNext()) {
			val = (String) valsIt.next();
			if (i == 0) {
				val2 = val;
			}
			else if (i == 1) {
				val3 = val;
			}
			i++;
		}
		boolean siEs = false;
		if (valEsp1.equals(val1) && valEsp2.equals(val2) && valEsp3.equals(val3)) {
			siEs = true;
		}
		assertEquals("Los valores del rango no son los correctos", true, siEs);
	}
}

