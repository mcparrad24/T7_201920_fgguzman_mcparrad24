package model.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import com.opencsv.CSVReader;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;

/**
 * Definicion del modelo del mundo
 *
 */
public class MVCModelo {
	private ZonaJSON zonas;
	// CARGA DE INFORMACION
	/**
	 * Lector de los archivos de JSON
	 */

	public void JSONLector() {
		Gson gson = new Gson();
		String path = "./data/bogota_cadastral.json";
		JsonReader reader;
		int i = 0;
		try {
			reader = new JsonReader(new FileReader(path));
			JsonElement elem = JsonParser.parseReader(reader);
			JsonElement e = elem.getAsJsonObject().get("features").getAsJsonArray().get(0).getAsJsonObject().get("properties");
			while(i < elem.getAsJsonObject().get("features").getAsJsonArray().size()) {
				e = elem.getAsJsonObject().get("features").getAsJsonArray().get(i).getAsJsonObject().get("properties");
				zonas = gson.fromJson(e, ZonaJSON.class);
				i++;
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
	 * 1A-Obtener las N letras mï¿½s frecuentes por las que comienza el nombre de una zona.
	 * N es un dato de entrada.El resultado debe aparecer de mayor a menor. 
	 * Para cada letra se debe imprimir la letra y el nombre de las zonas que comienzan por esa letra.
	 * 
	 * @param N numero de letras mï¿½s frecuentes.
	 * @return arreglo ordenado de las N letras mï¿½s frecuentes
	 */
	public String[] letrasMasFrecuentes(int N) {
		String rta[] = null;

		return rta;
	}

	/**
	 *2A-Buscar los nodos que delimitan las zonas por Localizaciï¿½n Geogrï¿½fica (latitud, longitud).
	 *Dadas una latitud y una longitud, se deben mostrar todos los nodos en la frontera de las zonas que
	 *tengan la misma latitud y longitud truncando a las primeras 3 cifras decimales.
	 * 
	 * @param lat latitud dada, lon longitud dada
	 * @return arreglo de los nodos que pertenecen
	 */
	public String[] buscarLatitudLongitud(double lat, double lon) {
		String rta[] = null;

		return rta;
	}

	/**
	 * Buscar los tiempos promedio de viaje que estï¿½n en un rango y que son del primer trimestre del 2018.
	 * Dado un rango de tiempos promedio de viaje en segundos [limite_bajo,limite_alto],
	 * retornar los viajes cuyo tiempo promedio mensual estï¿½ en ese rango.
	 * Se debe mostrar ï¿½nicamente N viajes ordenados por zona de origen y zona de destino. 
	 * Por cada viaje se  debe mostrar su zona de origen, zona de destino, mes y tiempo promedio mensual del viaje.
	 * 
	 * @param El minimo y maximo para buscar
	 * @return arreglo de los viajes
	 */
	public String[] tiempoPromRangoMes(int min, int max) {
		String rta[] = null;

		return rta;
	}

	// PARTE B
	/**
	 * 1B Buscar los N zonas que están más al norte.
	 * Una zona A esta más al Norte que una zona B si algún punto de la frontera de A 
	 * está más al norte que todos los puntos de la frontera de B.
	 * 
	 * @param N viajes mas al norte
	 * @return arreglo zonas ordenadas  desde  las  que  estén  más  al  norte. 
	 */
	public String[] viajesAlNorte(int N) {
		String rta[] = null;

		return rta;
	}

	/**
	 * 2B Buscar nodos de la malla vial por Localización Geográfica (latitud, longitud).
	 * Dado una latitud y una longitud, se deben mostrar todos los nodos que tengan esas
	 * mismas latitud y longitud truncando a 2 cifras decimales. 
	 * 
	 * @param lat latitud dada, lon longitud dada
	 * @return arreglo de los nodos que pertenecen
	 */
	public String[] mismaLatitudLongitud(double lat, double lon) {
		String rta[] = null;

		return rta;
	}

	/**
	 * 3B Buscar los tiempos de espera que tienen una desviación estándar 
	 * en un rango dado y que son del primer trimestre del 2018.
	 * Dado un rango de desviaciones estándares [limite_bajo,limite_alto] 
	 * retornar los viajes cuya desviación estándar mensual este en ese rango.
	 * Se debe mostrar únicamente N viajes ordenados por zona de origen y zona de destino.
	 * De cada viaje se debe mostrar la zona de origen, zona de destino, mes y la desviación estándar del viaje.
	 * @param El minimo y maximo para buscar
	 * @return arreglo de los viajes
	 */
	public String[] desviacionEstandarRangoMes(int min, int max) {
		String rta[] = null;

		return rta;
	}

	// PARTE C
	/**
	 * 1C Retornar todos los  tiempos  de  viaje promedio  que  salen  de una  zona  dada 
	 * y  a una hora dada.Dados el Id de una zona de salida y una hora que son ingresados
	 * por el usuario, retornar los tiempos de viaje promedio con esas características.
	 * Se debe mostrar la zona de origen, zona de destino, hora y tiempo promedio de cada viaje
	 * 
	 * @param inicio de zona de salida y h hora
	 * @return arreglo de los viajes
	 */
	public String[] viajesFranja(String inicio, String h) {
		String rta[] = null;

		return rta;
	}

	/**
	 * 2C Retornar todos  los  tiempos  de viaje que  llegan  de una  zona  dada  y  en  un  rango de horas.
	 * mostrar todos los tiempos de viaje promedio  que cumplan esos criterios. 
	 * Se debe mostrar la zona de origen, zona de destino, hora y tiempo promedio de cada viaje. 
	 * 
	 * @param destino zona de llegada, y rango de horas
	 * @return Arreglo con los viajes
	 */
	public String[] tiempoPromViajesHora(String destino, String horainicial, String horafinal) {
		String rta[] = null;

		return rta;
	}

	/**
	 * 3C Obtener las N zonas priorizadas por la mayor cantidad de nodos que definen su frontera.
	 * El valor N es un dato de entrada.Por cada zona se debe mostrar el nombre de la zona y el número de nodos que definen su frontera.
	 * 
	 * @param N numero de datos
	 * @return Arreglo con los viajes
	 */
	public String[] zonasFronterizas(String N) {
		String rta[] = null;

		return rta;
	}
	
	/**
	 * 4C Gráfica  ASCII -Porcentaje  de  datos  faltantes para  el  primer  semestre  2018.
	 * Crear una gráfica que muestre por cada zona de origen que porcentaje de datos faltan 
	 * (un dato faltante indica que no hubo ningún viaje desde la zona de origen a la zona 
	 * destino a una hora dada en un trimestre dado) 
	 * 
	 * @param inicial la zona de origen, destino la zona final, hora la hora dada, trimestre el trimestre
	 * @return Arreglo con los viajes
	 */
	public String[] graficaASCII(String inicial, String destino, String hora, String trimestre) {
		String rta[] = null;

		return rta;
	}

	// METODOS UTILES
	/**
	 * Ordena los viajes del queue de arreglos de string dado por parametro.
	 * 
	 * @param datos Queue de los datos a ordenar
	 * @return Queue con los datos ordenados

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
	*/
}
