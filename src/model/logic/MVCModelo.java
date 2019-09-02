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
		int menor = 99;
		int test = 99;
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
			if(test > mayor) {
				mayor = test;
				zona = cop;
			}
			if(test == 1160) {
				return zona;
			}
		}
		copia = datosW;
		for(int i = 0; i<copia.darTamano(); i++) {
			cop = copia.dequeue();
			test = Integer.parseInt(cop[0]);
			if(test > mayor) {
				mayor = test;
				zona = cop;
			}
			if(test == 1160) {
				return zona;
			}
		}
		copia = datosM;
		for(int i = 0; i<copia.darTamano(); i++) {
			cop = copia.dequeue();
			test = Integer.parseInt(cop[0]);
			if(test > mayor) {
				mayor = test;
				zona = cop;
			}
			if(test == 1160) {
				return zona;
			}
		}
		return zona;
	}
	
	public Queue<String[]> tiempoPromedioDesviacionMes(String zonaOrigen, String zonaDestino, String mes) {
		Queue <String[]> copia = datosM;
		Queue <String[]> zonas = null;
		for (int i = 0; i <= datosM.darTamano()-1; i++) {
			String[] actual = copia.dequeue();
			if (actual[0].equals(zonaOrigen) && actual[1].equals(zonaDestino) && actual[3].equals(mes)) {
				zonas.enqueue(actual);
			}
		}
		return zonas;
	}
	
	public Queue<String[]> tiempoPromedioDesviacionDia(String zonaOrigen, String zonaDestino, String dia) {
		Queue <String[]> copia = datosW;
		Queue <String[]> zonas = null;
		for (int i = 0; i <= datosW.darTamano()-1; i++) {
			String[] actual = copia.dequeue();
			if (actual[0].equals(zonaOrigen) && actual[1].equals(zonaDestino) && actual[3].equals(dia)) {
				zonas.enqueue(actual);
			}
		}
		return zonas;
	}
	
	public Queue<String[]> ordenar(Queue<String[]> datos){
		Queue<String[]> resultado = null;
		int N = datosM.darTamano();
		int h = 1;
		while (h < (N/3)) {
			h = 3*h + 1;
		}
		while (h >= 1) {
			for (int i = h;i < N; i++) {//falta terminar
			}
		}
		return resultado;
	}
	
	public Queue<String[]> tiemposPromediosMes(String mes){
		
	}
	
	public Queue<String[]> tiempoPromedioDia(String dia){
		
	}
	
	public Queue<String[]> tiempoPromedioHora(String hora){
		
	}
	
	public String tiemposZonaRango(String zona, String mes) { //terminar
		Queue <String[]> copia = datosM;
		Queue <String[]> sentido1menor = null;
		Queue <String[]> sentido2menor = null;
		Queue <String[]> sentido1mayor = null;
		Queue <String[]> sentido2mayor = null;
		String mensaje = " ";
		for (int i = 0; i <= datosM.darTamano()-1; i++) {
			String[] actual = copia.dequeue();
			if (actual[0].equals(zona) && actual[1].equals(menorIdentificador()) && actual[3].equals(mes)) {
				sentido1menor.enqueue(actual);
			}
			if (actual[0].equals(menorIdentificador()) && actual[1].equals(zona) && actual[3].equals(mes)) {
				sentido2menor.enqueue(actual);
			}
			if (actual[0].equals(zona) && actual[1].equals(mayorIdentificador()) && actual[3].equals(mes)) {
				sentido1mayor.enqueue(actual);
			}
			if (actual[0].equals(mayorIdentificador()) && actual[1].equals(zona) && actual[3].equals(mes)) {
				sentido2mayor.enqueue(actual);
			}
		}
		int tPromSen1Men = 0;
		int tamS1Me = sentido1menor.darTamano();
		int tPromSen2Men = 0;
		int tamS2Me = sentido2menor.darTamano();
		int tPromSen1May = 0;
		int tamS1Ma = sentido1mayor.darTamano();
		int tPromSen2May = 0;
		int tamS2Ma = sentido2mayor.darTamano();
		for (int i = 0; i <= sentido1menor.darTamano()-1; i++) {
			String [] actual = sentido1menor.dequeue();
			tPromSen1Men = tPromSen1Men + Integer.parseInt(actual[4]);
		}
		tPromSen1Men = tPromSen1Men/(tamS1Me);
		for (int i = 0; i <= sentido2menor.darTamano()-1; i++) {
			String [] actual = sentido2menor.dequeue();
			tPromSen2Men = tPromSen2Men + Integer.parseInt(actual[4]);
		}
		tPromSen2Men = tPromSen2Men/(tamS2Me);
		for (int i = 0; i <= sentido1mayor.darTamano()-1; i++) {
			String [] actual = sentido1mayor.dequeue();
			tPromSen1May = tPromSen1May + Integer.parseInt(actual[4]);
		}
		tPromSen1May = tPromSen1May/(tamS1Ma);
		for (int i = 0; i <= sentido2mayor.darTamano()-1; i++) {
			String [] actual = sentido2mayor.dequeue();
			tPromSen2May = tPromSen2May + Integer.parseInt(actual[4]);
		}
		tPromSen2May = tPromSen2May/(tamS2Ma);
		
		
		return mensaje;
	}
	
}
