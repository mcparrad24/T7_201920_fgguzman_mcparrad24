package test.data_structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import com.opencsv.CSVReader;

import model.data_structures.MaxColaCP;
import model.logic.UberTrip;

public class TestMaxColaCP {
	
	private MaxColaCP<UberTrip> datosCola = new MaxColaCP<>();

	public void setUp1() {
		CSVReader reader = null;
		String[] header = new String[1];
		UberTrip carga;
		try {
			reader = new CSVReader(new FileReader("./docs/DatosPrueba.csv"));
			header = reader.readNext();
			for (String[] nextLine : reader) {
				carga = new UberTrip(nextLine[0], nextLine[1], nextLine[2], nextLine[3], nextLine[4], 1);
				datosCola.insertar(carga);
			}

		} catch (Exception e) {
			fail("Fallo la lectura del archivo csv");
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
		int tam = datosCola.darTamano();
		assertEquals("El tamaño de la cola no es correcto", 35, tam);
	}
	
	@Test
	public void testInsertar1() {
		setUp1();
		String[] nuevo = {"4", "58", "10", "1474.35", "483.6", "1414.18", "1.32"};
		UberTrip nuevoDato = new UberTrip(nuevo[0], nuevo[1], nuevo[2], nuevo[3], nuevo[4], 1);
		datosCola.insertar(nuevoDato);
		assertEquals("No se agrego el dato correctamente", 36, datosCola.darTamano());
	}
	
	@Test
	public void testInsertar2() {
		setUp1();
		boolean siEs = false;
		String[] nuevo = {"4", "58", "10", "1474.35", "483.6", "1414.18", "1.32"};
		UberTrip nuevoDato = new UberTrip(nuevo[0], nuevo[1], nuevo[2], nuevo[3], nuevo[4], 1);
		datosCola.insertar(nuevoDato);
		if (nuevoDato.darIdOrigen() == datosCola.getLast().darIdOrigen() && nuevoDato.darIdDestino() == datosCola.getLast().darIdDestino() && nuevoDato.darHMD() == datosCola.getLast().darHMD() && nuevoDato.darTiempoPromedio() == datosCola.getLast().darTiempoPromedio()) {
			siEs = true;
		}
		assertEquals("El dato no se agrego correctamente", true, siEs);
	}
	
	@Test
	public void testEliminarMax1() {
		setUp1();
		datosCola.eliminarMax();
		assertEquals("No se elimino el dato correctamente", 34, datosCola.darTamano());
	}
	
	@Test
	public void testEliminarMax2() {
		setUp1();
		UberTrip eliminado = datosCola.eliminarMax();
		boolean siEs = false;
		if (eliminado.darIdOrigen() == 963 && eliminado.darIdDestino() == 300 && eliminado.darHMD() == 2 && eliminado.darTiempoPromedio() == 3406.06) {
			siEs = true;
		}
		assertEquals("No se elimino el dato correctamente", true, siEs);
	}
	
	@Test
	public void testEliminarMax3() {
		setUp1();
		datosCola.eliminarMax();
		boolean siEs = false;
		UberTrip max = datosCola.getMax();
		System.out.println(max.darIdOrigen());
		System.out.println(max.darIdDestino());
		System.out.println(max.darHMD());
		System.out.println(max.darTiempoPromedio());
		if (max.darIdOrigen() == 161 && max.darIdDestino() == 493 && max.darHMD() == 3 && max.darTiempoPromedio() == 2611.77) {
			siEs = true;
		}
		assertEquals("No se elimino el dato correctamente, ni se reemplazo el elemento mayor correctamente", true, siEs);
	}
	
	@Test
	public void testGetMax() {
		setUp1();
		UberTrip max = datosCola.getMax();
		boolean siEs = false;
		if ((max.darIdOrigen() == 963) && (max.darIdDestino() == 300) && (max.darHMD() == 2) && (String.valueOf(max.darTiempoPromedio()).equals("3406.06"))) {
			siEs = true;
		}
		assertEquals("No se dió el elemento máximo correcto", true, siEs);
	}

	@Test
	public void testIsEmpty() {
		setUp1();
		boolean empty = datosCola.isEmpty();
		assertEquals("La cola no esta vacia", false, empty);
	}
}
