package model.logic;

import model.data_structures.Queue;
import model.data_structures.Stack;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import com.opencsv.CSVReader;

/**
 * Definicion del modelo del mundo
 *
 */
public class MVCModelo {
	private Queue<String[]> datosH = new Queue<>();
	private Queue<String[]> datosM = new Queue<>();
	private Queue<String[]> datosW = new Queue<>();

	/**
	 * Lector de los archivos de excel
	 * @param n�mero de trimestre deseado
	 */
	public void CVSLector(int num) {
		CSVReader reader = null;
		String archivoH = "./data/bogota-cadastral-2018-"+num+"-All-HourlyAggregate.csv";
		String archivoM = "./data/bogota-cadastral-2018-"+num+"-All-MonthlyAggregate.csv";
		String archivoW = "./data/bogota-cadastral-2018-"+num+"-WeeklyAggregate.csv";
		String[] header = new String[1];
		try {
			reader = new CSVReader(new FileReader(archivoH));
			header = reader.readNext();
			for (String[] nextLine : reader) {
				datosH.enqueue(nextLine);
			}
			reader = new CSVReader(new FileReader(archivoM));
			header = reader.readNext();
			for (String[] nextLine : reader) {
				datosM.enqueue(nextLine);
			}
			reader = new CSVReader(new FileReader(archivoW));
			header = reader.readNext();
			for (String[] nextLine : reader) {
				datosW.enqueue(nextLine);
			}
		} catch (Exception e) {
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
	 * Los siguientes m�todos buscan el tama�o (total) de viajes seg�n el periodo de tiempo
	 * @return El total (tama�o)
	 */
	public int totalHora() {
		int total = datosH.darTamano();
		return total;
	}
	public int totalSemana() {
		int total = datosW.darTamano();
		return total;
	}
	public int totalMes() {
		int total = datosM.darTamano();
		return total;
	}
	/**
	 * Los siguientes m�todos buscan la zona deseada
	 * @return la zona menor o mayor
	 */
	public String[] menorIdentificador() {
		String[] zona = null;
		String[] cop = null;
		Queue<String[]> copia = datosH;
		int menor = 0;
		int test = 0;
		for(int i = 0; i<copia.darTamano(); i++) {
			cop = copia.dequeue();
			test = Integer.parseInt(cop[0]);
			if(test < menor) {
				menor = test;
				zona = cop;
			}
			if(test == 1) {
				return zona;
			}
		}
		copia = datosW;
		for(int i = 0; i<copia.darTamano(); i++) {
			cop = copia.dequeue();
			test = Integer.parseInt(cop[0]);
			if(test < menor) {
				menor = test;
				zona = cop;
			}
			if(test == 1) {
				return zona;
			}
		}
		copia = datosM;
		for(int i = 0; i<copia.darTamano(); i++) {
			cop = copia.dequeue();
			test = Integer.parseInt(cop[0]);
			if(test < menor) {
				menor = test;
				zona = cop;
			}
			if(test == 1) {
				return zona;
			}
		}
		return zona;
	}
	public String[] mayorIdentificador() {
		String[] zona = null;
		String[] cop = null;
		Queue<String[]> copia = datosH;
		int mayor = 0;
		int test = 0;
		for(int i = 0; i<copia.darTamano(); i++) {
			cop = copia.dequeue();
			test = Integer.parseInt(cop[0]);
			if(test < mayor) {
				mayor = test;
				zona = cop;
			}
		}
		copia = datosW;
		for(int i = 0; i<copia.darTamano(); i++) {
			cop = copia.dequeue();
			test = Integer.parseInt(cop[0]);
			if(test < mayor) {
				mayor = test;
				zona = cop;
			}
		}
		copia = datosM;
		for(int i = 0; i<copia.darTamano(); i++) {
			cop = copia.dequeue();
			test = Integer.parseInt(cop[0]);
			if(test < mayor) {
				mayor = test;
				zona = cop;
			}
		}
		return zona;
	}
}
