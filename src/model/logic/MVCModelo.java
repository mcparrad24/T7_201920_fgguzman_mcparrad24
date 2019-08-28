package model.logic;

import model.data_structures.Queue;
import model.data_structures.Stack;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

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
			// reader = new CSVReader(new
			// FileReader("./data/bogota-cadastral-2018-1-All-HourlyAggregate.csv"));
			reader = new CSVReader(new FileReader("./data/datos.csv"));
			for (String[] nextLine : reader) {
				datosQ.enqueue(nextLine);
				datosS.push(nextLine);
				System.out.println(total);
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

	public String[] primerElemento() {
		return datosQ.consultarPrimerElemento();
	}

	public String[] ultimoElemento() {
		return datosS.consultarElementoTope();
	}

	public Queue<String[]> cluster(int hora) {
		Queue<String[]> clusterQ = new Queue<>();
		int a = 0, b = 1;
		Queue<String[]> clusterQ1 = new Queue<>();
		String[] comp = datosQ.dequeue(), cam;
		do {
			comp = datosQ.dequeue();
			a++;
		} while (Integer.parseInt(comp[2]) < hora);

		do {
			for (int i = 0; i < datosQ.darTamano(); i++) {
				cam = datosQ.dequeue();
				if ((cam != null) && (Integer.parseInt(comp[2]) <= Integer.parseInt(cam[2]))) {
					clusterQ.enqueue(comp);
					comp = cam;
					a++;
				} else {
					comp = datosQ.dequeue();
					break;
				}
			}
			for (int i = 0; i < datosQ.darTamano(); i++) {
				cam = datosQ.dequeue();
				if ((comp != null) && (Integer.parseInt(comp[2]) <= Integer.parseInt(cam[2]))) {
					clusterQ1.enqueue(comp);
					comp = cam;
					b++;
				} else {
					break;
				}
			}
			if (b >= a) {
				a = 0;
				b = 0;
				clusterQ = null;
				clusterQ = new Queue<>();
				clusterQ = clusterQ1;
				clusterQ1 = null;
				clusterQ1 = new Queue<>();
				comp = datosQ.dequeue();
			}

		} while (comp != null);

		return clusterQ;
	}

	public Queue<String[]> viajesNH(int N, String hora) {
		Stack<String[]> clusterS = new Stack<>();
		Stack<String[]> clusterS2 = new Stack<>();
		String[] comp = datosS.pop();
		int n = 0;
		while ((n < N) || (comp != null)) {
			System.out.println(comp);
			if (comp[2].equals(hora)) {
				clusterS.push(comp);
				n++;
				clusterS2.push(clusterS.pop());
				
			}
			comp = datosS.pop();
		}
		Queue<String[]> clusterQ = new Queue<>();
		for (int i = 0; i < clusterS2.darTamano() - 1; i++) {
			clusterQ.enqueue(clusterS2.pop());
		}
		return clusterQ;

	}
}
