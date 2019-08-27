package model.logic;

import model.data_structures.Queue;
import model.data_structures.Stack;

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
	private Queue<String[]> datosQ = new Queue<>();
	private Stack<String[]> datosS = new Stack<>();
	/**
	 * Constructor del modelo del mundo con capacidad predefinida
	 */
	public int CVSLector() {
		CSVReader reader = null;
		int total = 0;
		try {
			reader = new CSVReader(new FileReader("./data/bogota-cadastral-2018-2-All-MonthlyAggregate.csv"));
			for (String[] nextLine : reader) {
				datosQ.enqueue(nextLine);
				datosS.push(nextLine);
				total++;
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
		return total;
	}
	
	public int totalViajesTrimestre() {
		int totalviajes = this.CVSLector();
		return totalviajes;
	}

}
