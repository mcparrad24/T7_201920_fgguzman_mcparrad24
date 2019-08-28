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
			//reader = new CSVReader(new FileReader("./data/bogota-cadastral-2018-1-All-HourlyAggregate.csv"));
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
	
	public Queue<String[]> cluster(int hora){
		Queue<String[]> clusterQ = new Queue<>();
		String[] comp = datosQ.dequeue();
		do{
			comp = datosQ.dequeue();
			System.out.println(comp);
		} while(Integer.parseInt(comp[2]) > hora);
		
		for (int i = 0; i<datosQ.darTamano(); i++) {
			if(Integer.parseInt(comp[2]) <= Integer.parseInt(datosQ.dequeue()[2])) {
				clusterQ.enqueue(comp);
				comp = datosQ.dequeue();
			}
		}
		
		return clusterQ;
	}
	
	public Queue<String[]> viajesNH(int N, int hora){
		Stack<String[]> clusterS = new Stack<>();
		String[] comp = datosS.pop();
		clusterS.push(comp);
		Queue<String[]> clusterQ = new Queue<>();
		String[] comp2 = clusterS.pop();
		clusterQ.enqueue(comp2);
		return clusterQ;
		
	}
}
