package test.data_structures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;

import model.data_structures.Queue;

public class TestQueue {

	Queue<String[]> datosH = new Queue<>();
	
	public void setUp1() {
		String[] header = new String[1];
		CSVReader reader = null;
		String archivoH = "./data/Prueba.csv";
		try {
			reader = new CSVReader(new FileReader(archivoH));
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
	
	public void testEnqueue() {
		setUp1();
		String[] nuevoDato = new String[]{Integer.toString(5), Integer.toString(7), Integer.toString(3), String.valueOf(1440.57), String.valueOf(198.16), String.valueOf(1029.94), String.valueOf(1.36)};
		datosH.enqueue(nuevoDato);
		assertEquals("El número de datos no es correcto", 11, datosH.darTamano());
	}
	
	public void testEnqueue2() {
		setUp1();
		String[] nuevoDato = new String[]{Integer.toString(5), Integer.toString(7), Integer.toString(3), String.valueOf(1440.57), String.valueOf(198.16), String.valueOf(1029.94), String.valueOf(1.36)};
		String[] ultimo = null;
		datosH.enqueue(nuevoDato);
		Queue<String[]> copia = datosH;
		for (int i = 0; i <= datosH.darTamano()-1; i++) {
			String [] actual = copia.dequeue();
			if (actual != null) {
				ultimo = actual;
			}
		}
		assertEquals("El último dato no es el correcto", nuevoDato, ultimo);
	}
	
	public void testDequeue() {
		setUp1();
		datosH.dequeue();
		assertEquals("El número de datos no es correcto", 9, datosH.darTamano());
	}
	
	public void testDequeue2() {
		setUp1();
		String[] eliminado = datosH.dequeue();
		boolean siEs = false;
		if ((Integer.parseInt(eliminado[0]) == 4) &&(Integer.parseInt(eliminado[1]) == 5) && (Integer.parseInt(eliminado[2]) == 13)) {
			siEs = true;
		}
		assertEquals("El dato eliminado no es el correcto", true, siEs);
	}
	
	public void testDarTamaño() {
		setUp1();
		assertEquals("El tamaño de la lista es incorrecta", 10, datosH.darTamano());
	}
	
	public void testIsEmpty() {
		setUp1();
		assertEquals("El arreglo no está vacio", false, datosH.isEmpty());
	}
	
	public void testConsultarPrimerElemento() {
		setUp1();
		boolean siEs = false;
		if ((Integer.parseInt(datosH.consultarPrimerElemento()[0]) == 4) && (Integer.parseInt(datosH.consultarPrimerElemento()[1]) == 5) && (Integer.parseInt(datosH.consultarPrimerElemento()[2]) == 13)) {
			siEs = true;
		}
		assertEquals("El primer elemento es incorrecto", true, siEs);
	}
}
