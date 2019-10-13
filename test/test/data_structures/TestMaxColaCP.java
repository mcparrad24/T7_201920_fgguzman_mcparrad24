package test.data_structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileReader;
import java.io.IOException;

import org.junit.Test;

import com.opencsv.CSVReader;

import model.data_structures.MaxColaCP;
import model.logic.TravelTime;

public class TestMaxColaCP {
	
	private MaxColaCP<TravelTime> datosCola = new MaxColaCP<>();

	public void setUp1() {
		CSVReader reader = null;
		String[] header = new String[1];
		TravelTime carga;
		try {
			reader = new CSVReader(new FileReader("./docs/Muestra_de_datos.csv"));
			header = reader.readNext();
			for (String[] nextLine : reader) {
				carga = new TravelTime(nextLine);
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
		assertEquals("El tamaño de la cola no es correcto", 30, tam);
	}
	
	@Test
	public void testInsertar1() {
		setUp1();
		String[] nuevo = {"4", "58", "10", "1474.35", "483.6", "1414.18", "1.32"};
		TravelTime nuevoDato = new TravelTime(nuevo);
		datosCola.insertar(nuevoDato);
		assertEquals("No se agrego el dato correctamente", 31, datosCola.darTamano());
	}
	
	@Test
	public void testInsertar2() {
		setUp1();
		boolean siEs = false;
		String[] nuevo = {"4", "58", "10", "1474.35", "483.6", "1414.18", "1.32"};
		TravelTime nuevoDato = new TravelTime(nuevo);
		datosCola.insertar(nuevoDato);
		if (nuevoDato.darIdOrigen().equals(datosCola.getLast().darIdOrigen()) && nuevoDato.darIdDestino().equals(datosCola.getLast().darIdDestino()) && nuevoDato.darHoraPromedio().equals(datosCola.getLast().darHoraPromedio()) && nuevoDato.darTiempoPromedio() == datosCola.getLast().darTiempoPromedio()) {
			siEs = true;
		}
		assertEquals("El dato no se agrego correctamente", true, siEs);
	}
	
	@Test
	public void testEliminarMax1() {
		setUp1();
		datosCola.eliminarMax();
		assertEquals("No se elimino el dato correctamente", 29, datosCola.darTamano());
	}
	
	@Test
	public void testEliminarMax2() {
		setUp1();
		TravelTime eliminado = datosCola.eliminarMax();
		boolean siEs = false;
		if (eliminado.darIdOrigen().equals("1") && eliminado.darIdDestino().equals("69") && eliminado.darHoraPromedio().equals("12") && eliminado.darTiempoPromedio() == 3439.46) {
			siEs = true;
		}
		assertEquals("No se elimino el dato correctamente", true, siEs);
	}
	
	@Test
	public void testEliminarMax3() {
		setUp1();
		datosCola.eliminarMax();
		boolean siEs = false;
		TravelTime max = datosCola.getMax();
		if (max.darIdOrigen().equals("1") && max.darIdDestino().equals("45") && max.darHoraPromedio().equals("6") && max.darTiempoPromedio() == 3428.65) {
			siEs = true;
		}
		assertEquals("No se elimino el dato correctamente, ni se reemplazo el elemento mayor correctamente", true, siEs);
	}
	
	@Test
	public void testGetMax() {
		setUp1();
		TravelTime max = datosCola.getMax();
		boolean siEs = false;
		System.out.println(max.darIdOrigen());
		System.out.println(max.darIdDestino());
		System.out.println(max.darHoraPromedio());
		System.out.println(max.darTiempoPromedio());
		if ((max.darIdOrigen().equals("1")) && (max.darIdDestino().equals("69")) && (max.darHoraPromedio().equals("12")) && (String.valueOf(max.darTiempoPromedio()).equals("3439.46"))) {
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
