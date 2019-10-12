package model.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import com.opencsv.CSVReader;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

/**
 * Definicion del modelo del mundo
 *
 */
public class MVCModelo {
	private UberTrip viajesH, viajesM, viajesW;
	private ZonaJSON zonas;
	private MallaVial mallas;

	// CARGA DE INFORMACION
	/**
	 * Lector de los archivos de excel
	 * 
	 * @param numero de trimestre deseado
	 */
	public void CSVLector(int num) {
		CSVReader reader = null;
		String archivoH = "./data/bogota-cadastral-2018-" + num + "-All-HourlyAggregate.csv";
		String archivoM = "./data/bogota-cadastral-2018-" + num + "-All-MonthlyAggregate.csv";
		String archivoW = "./data/bogota-cadastral-2018-" + num + "-WeeklyAggregate.csv";
		String[] header = new String[1];
		try {
			reader = new CSVReader(new FileReader(archivoH));
			header = reader.readNext();
			for (String[] nextLine : reader) {
				viajesH = new UberTrip(nextLine[0], nextLine[1], nextLine[2], nextLine[3], nextLine[4], num);
			}
			reader = new CSVReader(new FileReader(archivoM));
			header = reader.readNext();
			for (String[] nextLine : reader) {
				viajesM = new UberTrip(nextLine[0], nextLine[1], nextLine[2], nextLine[3], nextLine[4], num);
			}
			reader = new CSVReader(new FileReader(archivoW));
			header = reader.readNext();
			for (String[] nextLine : reader) {
				viajesW = new UberTrip(nextLine[0], nextLine[1], nextLine[2], nextLine[3], nextLine[4], num);
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

	public void JSONLector() {
		Gson gson = new Gson();
		String path = "./data/bogota_cadastral.json";
		JsonReader reader;
		try {
			reader = new JsonReader(new FileReader(path));
			zonas = gson.fromJson(reader, ZonaJSON.class);
			System.out.println(zonas);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void TXTLector() {
		FileReader fr = null;
		BufferedReader br = null;
		try {
			fr = new FileReader("./data/Nodes_of_red_vial-wgs84_shp.txt");
			br = new BufferedReader(fr);
			String st;
			String[] a = new String[3];
			while ((st = br.readLine()) != null) {
				a = st.split(",");
				mallas = new MallaVial(a[0], a[1], a[2]);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * Total de viajes CSV, zonas JSON y nodos TXT
	 * 
	 * @return El total (tamano)
	 */
	public int totalHora() {
		int total = 0;
		return total;
	}

	public int totalSemana() {
		int total = 0;
		return total;
	}

	public int totalMes() {
		int total = 0;
		return total;
	}
	
	public int totalZonas() {
		int total = 0;
		return total;
	}
	
	public int totalNodos() {
		int total = 0;
		return total;
	}

	// PARTE A
	/**
	 * 1A-Obtener las N letras más frecuentes por las que comienza el nombre de una zona.
	 * N es un dato de entrada.El resultado debe aparecer de mayor a menor. 
	 * Para cada letra se debe imprimir la letra y el nombre de las zonas que comienzan por esa letra.
	 * 
	 * @param N numero de letras más frecuentes.
	 * @return ----- ordenado de las N letras más frecuentes
	 */
	public String letrasMasFrecuentes(int N) {
		String rta = "";

		return rta;
	}

	/**
	 *2A-Buscar los nodos que delimitan las zonas por Localización Geográfica (latitud, longitud).
	 *Dadas una latitud y una longitud, se deben mostrar todos los nodos en la frontera de las zonas que
	 *tengan la misma latitud y longitud truncando a las primeras 3 cifras decimales.
	 * 
	 * @param lat latitud dada, lon longitud dada
	 * @return ----- de los nodos que pertenecen
	 */
	public int buscarLatitudLongitud(double lat, double lon) {
		int tam = 0;

		return tam;
	}

	/**
	 * Buscar los tiempos promedio de viaje que están en un rango y que son del primer trimestre del 2018.
	 * Dado un rango de tiempos promedio de viaje en segundos [limite_bajo,limite_alto],
	 * retornar los viajes cuyo tiempo promedio mensual esté en ese rango.
	 * Se debe mostrar únicamente N viajes ordenados por zona de origen y zona de destino. 
	 * Por cada viaje se  debe mostrar su zona de origen, zona de destino, mes y tiempo promedio mensual del viaje.
	 * 
	 * @param El minimo y maximo para buscar
	 * @return ------- de los viajes
	 */
	public int tiempoPromRangoMes(int min, int max) {
		int tam = 0;

		return tam;
	}

	// PARTE B
	/**
	 * 1B Consultar el tiempo promedio de viaje y su desviacion estandar de los
	 * viajes entre una zona de origen y una zona destino para un dia dado de la
	 * semana.
	 * 
	 * @param zonaOrigen ID de la zona de origen zonaDestino ID de la zona destino
	 *                   dia Numero que representa el dia de la semana
	 * @return String de tiempo promedio y desviacion estandar
	 */
	public String tiempoPromedioDesviacionDia(String zonaOrigen, String zonaDestino, String dia) {
		String rta = "";
		Queue<String[]> copia = datosW;
		Queue<String[]> zonas = new Queue<String[]>();
		for (int i = 0; i <= datosW.darTamano() - 1; i++) {
			String[] actual = copia.dequeue();
			if (actual[0].equals(zonaOrigen) && actual[1].equals(zonaDestino) && actual[3].equals(dia)) {
				zonas.enqueue(actual);
			}
		}
		if (zonas.isEmpty()) {
			rta = null;
		} else {
			double tPromedio = tiempoPromedio(zonas);
			double desvEstPromedio = desviacionEstandarPromedio(zonas);
			rta = tPromedio + ", " + desvEstPromedio;
		}
		return rta;
	}

	/**
	 * 2B Consultar la informacion de los N viajes con mayor tiempo promedio para un
	 * dia dado.
	 * 
	 * @param N Numero de viajes a consultar dia Numero que representa el dia de la
	 *          semana
	 * @return Queue de los viajes ordenados
	 */
	public Queue<String[]> tiempoPromViajesDia(String N, String dia) {
		int tam = datosW.darTamano();
		Queue<String[]> viajesDia = new Queue<String[]>();
		for (int i = 0; i < tam; i++) {
			String[] actual = datosW.dequeue();
			if (Integer.parseInt(actual[2]) == Integer.parseInt(dia)) {
				viajesDia.enqueue(actual);
			}
		}
		Queue<String[]> ordenados = ordenarViajesQuickSort(viajesDia);
		Queue<String[]> nViajes = new Queue<String[]>();
		for (int i = 0; i < Integer.parseInt(N); i++) {
			nViajes.enqueue(ordenados.dequeue());
		}
		return nViajes;
	}

	/**
	 * 3B Comparar los tiempos promedios de los viajes para una zona dada contra
	 * cada zona X en un rango de zonas dado [Zona menor, Zona Mayor] en ambos
	 * sentidos (zona dada - zona X vs. zona X - zona dada) para un dia dado. Los
	 * resultados deben estar ordenados ascendentemente por el identificador de la
	 * zona X en el rango dado.
	 * 
	 * @return Los datos ordenados en el rango deseado y el mes dado
	 */
	public Queue<String[]> tiempoPromRangoDia(String zonaDada, String zonaMenor, String zonaMayor, String dia) {
		int tam = datosW.darTamano();
		Queue<String[]> viajesDia = new Queue<String[]>();
		for (int i = 0; i < tam; i++) {
			String[] actual = datosW.dequeue();
			if ((Integer.parseInt(actual[0]) > Integer.parseInt(zonaMenor))
					&& (Integer.parseInt(actual[0]) < Integer.parseInt(zonaMayor))
					&& ((Integer.parseInt(actual[1]) > Integer.parseInt(zonaMenor))
							&& (Integer.parseInt(actual[1]) < Integer.parseInt(zonaMayor)))
					&& (actual[2].equals(dia))) {
				viajesDia.enqueue(actual);
			}
		}
		Queue<String[]> ordenados = ordenarViajesQuickSort(viajesDia);
		return ordenados;
	}

	// PARTE C
	/**
	 * 1C Consultar los viajes entre una zona de origen y una zona destino en una
	 * franja horaria (hora inicial - hora final) dada.
	 * 
	 * @return Queue con los viajes que cumplen el rango, el origen y el destino;
	 *         ordenados
	 */
	public Queue<String[]> viajesFranja(String origen, String destino, String horaIn, String horaFin) {
		int tam = datosW.darTamano();
		Queue<String[]> viajesHora = new Queue<String[]>();
		for (int i = 0; i < tam; i++) {
			String[] actual = datosH.dequeue();
			if ((Integer.parseInt(actual[2]) > Integer.parseInt(horaIn))
					&& (Integer.parseInt(actual[2]) < Integer.parseInt(horaFin)) && (actual[0].equals(origen))
					&& (actual[1].equals(destino))) {
				viajesHora.enqueue(actual);
			}
		}
		Queue<String[]> ordenados = ordenarViajesQuickSort(viajesHora);
		return ordenados;
	}

	/**
	 * 2C Consultar la informacion de los N viajes con mayor tiempo promedio para
	 * una hora dada.
	 * 
	 * @param N Numero de viajes a consultar hora Hora del dia
	 * @return Queue de los viajes ordenados
	 */
	public Queue<String[]> tiempoPromViajesHora(String N, String hora) {
		return tPromViaHora(N, hora, datosH);
	}

	public Queue<String[]> tPromViaHora(String N, String hora, Queue<String[]> datos) {
		int tam = datos.darTamano();
		int horas = Integer.parseInt(hora);
		Queue<String[]> viajesHora = new Queue<String[]>();
		for (int i = 0; i < tam; i++) {
			String[] actual = datos.dequeue();
			if (Integer.parseInt(actual[2]) == horas) {
				viajesHora.enqueue(actual);
			}
		}
		Queue<String[]> ordenados = ordenarViajesQuickSort(viajesHora);
		Queue<String[]> nViajes = new Queue<String[]>();
		for (int i = 0; i < Integer.parseInt(N); i++) {
			nViajes.enqueue(ordenados.dequeue());
		}
		return nViajes;
	}

	/**
	 * 3C Generar una grafica ASCII que muestre el tiempo promedio de los viajes
	 * entre una zona origen y una zona destino para cada hora del dia. Cada * en la
	 * grafica corresponde a 1 minuto. Un tiempo promedio se aproxima a los minutos
	 * mas cercanos.
	 * 
	 * @param zonaOrigen ID de la zona de origen zonaDestino ID de la zona destino
	 * @return Arreglo con los viajes
	 */
	public String[] graficaASCII(String zonaOrigen, String zonaDestino) {
		int minutos;
		double tiempo;
		String[] viajesAscii = new String[24];
		String aster = "";
		Queue<String[]> copia = datosH;
		Queue<String[]> zonas = new Queue<String[]>();
		for (int i = 0; i <= copia.darTamano() - 1; i++) {
			String[] actual = copia.dequeue();
			if (actual[0].equals(zonaOrigen) && actual[1].equals(zonaDestino)) {
				zonas.enqueue(actual);
			}
		}
		Queue<String[]> ascii = zonas;
		for (int i = 0; i <= ascii.darTamano() - 1 && ascii != null; i++) {
			String[] actual = ascii.dequeue();
			for (int hor = 0; hor <= 23; hor++) {
				if (actual[2].equals(String.valueOf(hor))) {
					tiempo = Double.parseDouble(actual[3]);
					minutos = (int) tiempo / 60;
					for (int j = 0; j < minutos; j++) {
						String a = "*";
						aster = aster + a;
					}
					viajesAscii[hor] = "";
					viajesAscii[hor] = viajesAscii[hor] + aster;
				}
			}
		}
		aster = "hora sin viajes";
		for (int i = 0; i < viajesAscii.length; i++) {
			if (viajesAscii[i] == null) {
				viajesAscii[i] = aster;
			}
		}
		return viajesAscii;
	}

	// METODOS UTILES
	/**
	 * Ordena los viajes del queue de arreglos de string dado por parametro.
	 * 
	 * @param datos Queue de los datos a ordenar
	 * @return Queue con los datos ordenados
	 */
	public TravelTime[] ordenarViajesQuickSort(TravelTime[] clus) {
		int izq = 0;
		int der = clus.length - 1;
		int pivote = quicksort(clus, izq, der);
		if(izq < pivote-1 && pivote+1 > der) {
			quicksort(clus, izq, pivote-1);
			quicksort(clus, pivote+1, der);
		}	
		return clus;
	}
	/**
	 * Ordena los viajes del queue de forma recursiva.
	 * 
	 * @param datos Queue de los datos a ordenar izq Numero que representa la
	 *              posicion del dato en la izquierda del queue der Numero que
	 *              representa la posicion del dato en la derecha del queue
	 * @return
	 */
	public int quicksort(TravelTime[] clus, int izq, int der) {
		String[] au = {"0", "0", "0", "0", "0"};
		TravelTime pivote = (TravelTime) clus[izq];
		int i = izq;
		TravelTime aux = new TravelTime(au, 1);
		for (int j = izq; j < der; j++) {
			if (clus[j].compareTo(pivote) == -1) {
				i++;
				aux = clus[i];
				clus[i] = clus[j];
				clus[j] = aux;
			}
		}
		aux = clus[i+1];
		clus[i+1] = clus[der];
		clus[der] = aux;
		return i+1;
	}

	/**
	 * Calcula el tiempo promedio de los viajes del queue dado por parametro
	 * 
	 * @param datos Queue con los datos
	 * @return El tiempo promedio de los datos del queue.
	 */
	public double tiempoPromedio(Queue<String[]> datos) {
		int tam = datos.darTamano();
		double tiempo = 0;
		double tPromedio = 0;
		for (int i = 0; i <= tam - 1; i++) {
			String[] actual = datos.dequeue();
			tiempo = tiempo * Double.valueOf(actual[3]);
		}
		tPromedio = (tiempo / tam);
		return tPromedio;
	}

	/**
	 * Calcula la desviacion estandar promedio de los viajes del queue dado por
	 * parametro
	 * 
	 * @param datos Queue con los datos
	 * @return La desviacion estandar promedio de los datos del queue.
	 */
	public double desviacionEstandarPromedio(Queue<String[]> datos) {
		int tam = datos.darTamano();
		double desvEst = 0;
		double desvEstPromedio = 0;
		for (int i = 0; i <= tam - 1; i++) {
			String[] actual = datos.dequeue();
			desvEst = desvEst * Double.valueOf(actual[4]);
		}
		desvEstPromedio = (desvEst / tam);
		return desvEstPromedio;
	}
}
