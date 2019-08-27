package model.logic;

import model.data_structures.Queue;
import model.data_structures.IQueue;
import model.data_structures.Stack;
import model.data_structures.IStack;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

 import com.opencsv.CSVReader;
/**
 * Definicion del modelo del mundo
 *
 */
public class MVCModelo {
	/**
	 * Atributos del modelo del mundo
	 */
	private IQueue datosQ;
	private IStack datosS;
	
	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public void CVSLector() {
		CSVReader reader = null;
		try {

			reader = new CSVReader(new FileReader("./data/bogota-cadastral-2018-2-All-MonthlyAggregate.csv"));
			for (String[] nextLine : reader) {
				//System.out.println("col1: " + nextLine[0] + ", col2: " + nextLine[1]);
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}
	}

	/**
	 * Servicio de consulta de numero de elementos presentes en el modelo
	 * 
	 * @return numero de elementos presentes en el modelo
	 */
	public int darTamano() {
		return datos.darTamano();
	}

	/**
	 * Requerimiento de agregar dato
	 * 
	 * @param dato
	 */
	public void agregar(String dato) {
		datos.agregar(dato);
	}

	/**
	 * Requerimiento buscar dato
	 * 
	 * @param dato
	 *            Dato a buscar
	 * @return dato encontrado
	 */
	public String buscar(String dato) {
		return datos.buscar(dato);
	}

	/**
	 * Requerimiento eliminar dato
	 * 
	 * @param dato
	 *            Dato a eliminar
	 * @return dato eliminado
	 */
	public String eliminar(String dato) {
		return datos.eliminar(dato);
	}

}
