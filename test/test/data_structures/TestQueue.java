package test.data_structures;

import static org.junit.Assert.*;

import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;

import model.data_structures.Queue;

import org.junit.Test;

public class TestQueue {

	
	Queue<String[]> datosH = new Queue<>();
	
	public void setUp1() {
		CSVReader reader = null;
		String[] header = new String[1];
		try {
			reader = new CSVReader(new FileReader("./data/datos_de_prueba.csv"));
			header = reader.readNext();
			for (String[] nextLine : reader) {
				datosH.enqueue(nextLine);
			}
		} catch (Exception e) {
			fail("Falló la lectura del archivo csv");
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
	public void testEnqueue() {
		setUp1();
		String[] nuevoDato = new String[]{Integer.toString(5), Integer.toString(7), Integer.toString(3), String.valueOf(1440.57), String.valueOf(198.16), String.valueOf(1029.94), String.valueOf(1.36)};
		datosH.enqueue(nuevoDato);
		assertEquals("El número de datos no es correcto", 11, datosH.darTamano());
	}
	
	@Test
	public void testEnqueue2() {
		setUp1();
		String[] ultimo = null;
		String[] nuevoDato = new String[]{Integer.toString(5), Integer.toString(7), Integer.toString(3), String.valueOf(1440.57), String.valueOf(198.16), String.valueOf(1029.94), String.valueOf(1.36)};
		datosH.enqueue(nuevoDato);
		int tamaño = datosH.darTamano();
		for (int i = 0; i <= (tamaño-1); i++) {
			String [] actual = datosH.dequeue();
			if (actual != null) {
				ultimo = actual;
			}
		}
		boolean siEs = false;
		if ((ultimo[0].equals(Integer.toString(5)))&&(ultimo[1].equals(Integer.toString(7)))&&(ultimo[2].equals(Integer.toString(3)))) {
			siEs = true;
		}
		assertEquals("El último dato no es el correcto", true, siEs);
	}
	
	@Test
	public void testDequeue() {
		setUp1();
		datosH.dequeue();
		assertEquals("El número de datos no es correcto", 9, datosH.darTamano());
	}
	
	@Test
	public void testDequeue2() {
		setUp1();
		String[] eliminado = datosH.dequeue();
		boolean siEs = false;
		if ((eliminado[0].equals(Integer.toString(1))) && (eliminado[1].equals(Integer.toString(4))) && (eliminado[2].equals(Integer.toString(20)))) {
			siEs = true;
		}
		assertEquals("El dato eliminado no es el correcto", true, siEs);
	}
	
	@Test
	public void testDarTamaño() {
		setUp1();
		assertEquals("El tamaño de la lista es incorrecta", 10, datosH.darTamano());
	}
	
	@Test
	public void testIsEmpty() {
		setUp1();
		assertEquals("El arreglo no está vacio", false, datosH.isEmpty());
	}
	
	@Test
	public void testConsultarPrimerElemento() {
		setUp1();
		boolean siEs = false;
		if ((datosH.consultarPrimerElemento()[0].equals(Integer.toString(1))) && (datosH.consultarPrimerElemento()[1].equals(Integer.toString(4))) && (datosH.consultarPrimerElemento()[2].equals(Integer.toString(20)))) {
			siEs = true;
		}
		assertEquals("El primer elemento es incorrecto", true, siEs);
	}
}
