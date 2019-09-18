package model.logic;

import model.data_structures.Queue;

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
	 * Los siguientes metodos buscan el tamano (total) de viajes segun el periodo de
	 * tiempo
	 * 
	 * @return El total (tamano)
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
	 * Los siguientes metodos buscan la zona deseada
	 * 
	 * @return la zona menor o mayor
	 */
	public String[] menorIdentificador() {
		String[] zona = null;
		String[] cop = null;
		Queue<String[]> copia = datosH;
		int tam = copia.darTamano();
		int menor = 99;
		int test = 99;
		for (int i = 0; i < tam; i++) {
			cop = copia.dequeue();
			test = Integer.parseInt(cop[0]);
			if (test < menor) {
				menor = test;
				zona = cop;
			}
			if (test == 1) {
				return zona;
			}
		}
		copia = datosW;
		int tam2 = copia.darTamano();
		for (int i = 0; i < tam2; i++) {
			cop = copia.dequeue();
			test = Integer.parseInt(cop[0]);
			if (test < menor) {
				menor = test;
				zona = cop;
			}
			if (test == 1) {
				return zona;
			}
		}
		copia = datosM;
		int tam3 = copia.darTamano();
		for (int i = 0; i < tam3; i++) {
			cop = copia.dequeue();
			test = Integer.parseInt(cop[0]);
			if (test < menor) {
				menor = test;
				zona = cop;
			}
			if (test == 1) {
				return zona;
			}
		}
		return zona;
	}

	public String[] mayorIdentificador() {
		String[] zona = null;
		String[] cop = null;
		Queue<String[]> copia = datosH;
		int tam = copia.darTamano();
		int mayor = 0;
		int test = 0;
		for (int i = 0; i < tam; i++) {
			cop = copia.dequeue();
			test = Integer.parseInt(cop[0]);
			if (test > mayor) {
				mayor = test;
				zona = cop;
			}
			if (test == 1160) {
				return zona;
			}
		}
		copia = datosW;
		int tam2 = copia.darTamano();
		for (int i = 0; i < tam2; i++) {
			cop = copia.dequeue();
			test = Integer.parseInt(cop[0]);
			if (test > mayor) {
				mayor = test;
				zona = cop;
			}
			if (test == 1160) {
				return zona;
			}
		}
		copia = datosM;
		int tam3 = copia.darTamano();
		for (int i = 0; i < tam3; i++) {
			cop = copia.dequeue();
			test = Integer.parseInt(cop[0]);
			if (test > mayor) {
				mayor = test;
				zona = cop;
			}
			if (test == 1160) {
				return zona;
			}
		}
		return zona;
	}

	// PARTE A
	/**
	 * 1A Consultar el tiempo promedio de viaje y su desviacion estandar de los
	 * viajes entre una zona de origen y una zona destino para un mes dado.
	 * @param zonaOrigen ID de la zona de origen
	 * zonaDestino ID de la zona destino
	 * mes Número que representa el mes del año
	 * @return String de tiempo promedio y desviaci�n est�ndar
	 */
	public String tiempoPromedioDesviacionMes(String zonaOrigen, String zonaDestino, String mes) {
		String rta = "";
		int tam = datosM.darTamano();
		Queue<String[]> copia = datosM;
		Queue<String[]> zonas = new Queue<String[]>();
		for (int i = 0; i <= tam - 1; i++) {
			String[] actual = copia.dequeue();
			if (actual[0].equals(zonaOrigen) && actual[1].equals(zonaDestino) && actual[3].equals(mes)) {
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
	 * 2A Consultar la informacion de los N viajes con mayor tiempo promedio para un
	 * mes dado.
	 * @param N Numero de viajes a consultar
	 * mes Numero que representa el mes del ano
	 * @return Queue de los viajes ordenados
	 */
	public Queue<String[]> tiempoPromViajesMes(String N, String mes) {
		int tam = datosM.darTamano();
		Queue<String[]> viajesMes = new Queue<String[]>();
		for (int i = 0; i < tam; i++) {
			String[] actual = datosM.dequeue();
			if (Integer.parseInt(actual[2]) == Integer.parseInt(mes)) {
				viajesMes.enqueue(actual);
			}
		}
		Queue<String[]> ordenados = ordenarViajesQuickSort(viajesMes);
		Queue<String[]> nViajes = new Queue<String[]>();
		for (int i = 0; i < Integer.parseInt(N); i++) {
			nViajes.enqueue(ordenados.dequeue());
		}
		return nViajes;
	}
	/**
	 * 3A Comparar los tiempos promedios de los viajes para una zona dada contra
	 * cada zona X en un rango de zonas dado [Zona menor, Zona Mayor] en ambos
	 * sentidos (zona dada �zona X vs. zona X �zona dada) para un mes dado. Mostrar
	 * los resultados de comparaci�n con cada zona X en una l�nea de la siguiente
	 * forma: <tiempo promedio> de <Zona dada> a <Zona X> vs <tiempo promedio> de
	 * <Zona X> a <Zona dada> Si no hay viajes en alg�n sentido o en ambos entre la
	 * zona dada y una zona X debe reemplazarse <tiempo promedio> por "No hay
	 * viajes" (en el sentido respectivo). Los resultados deben estar ordenados
	 * ascendentemente por el identificador de la zona X en el rango dado.
	 * 
	 * @return 
	 */

	// PARTE B
	/**
	 * 1B Consultar el tiempo promedio de viaje y su desviaci�n est�ndar de los
	 * viajes entre una zona de origen y una zona destino para un d�a dado de la
	 * semana.
	 * @param zonaOrigen ID de la zona de origen
	 * zonaDestino ID de la zona destino
	 * dia Numero que representa el dia de la semana
	 * @return String de tiempo promedio y desviaci�n est�ndar
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
	 * 2B Consultar la informaci�n de los N viajes con mayor tiempo promedio para un
	 * d�a dado.
	 * @param N Numero de viajes a consultar
	 * dia Numero que representa el dia de la semana
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
	 * 3A Comparar los tiempos promedios de los viajes para una zona dada contra
	 * cada zona X en un rango de zonas dado [Zona menor, Zona Mayor] en ambos
	 * sentidos (zona dada - zona X vs. zona X - zona dada) para un dia dado. Mostrar
	 * los resultados de comparacion con cada zona X en una linea de la siguiente
	 * forma: <tiempo promedio> de <Zona dada> a <Zona X> vs <tiempo promedio> de
	 * <Zona X> a <Zona dada> Si no hay viajes en algun sentido o en ambos entre la
	 * zona dada y una zona X debe reemplazarse <tiempo promedio> por "No hay
	 * viajes" (en el sentido respectivo). Los resultados deben estar ordenados
	 * ascendentemente por el identificador de la zona X en el rango dado.
	 * 
	 * @return
	 */
	public Queue<String[]> tiempoPromRangoDia(String zonaDada, String zonaMenor, String zonaMayor, String dia){
		int tam = datosW.darTamano();
		Queue<String[]> viajesDia = new Queue<String[]>();
		for (int i = 0; i < tam; i++) {
			String[] actual = datosW.dequeue();
			if ((Integer.parseInt(actual[0]) > Integer.parseInt(zonaMenor)) && (Integer.parseInt(actual[0]) < Integer.parseInt(zonaMayor))&&((Integer.parseInt(actual[1]) > Integer.parseInt(zonaMenor)) && (Integer.parseInt(actual[1]) < Integer.parseInt(zonaMayor)))&&(Integer.parseInt(actual[2]) == Integer.parseInt(dia))) {
				viajesDia.enqueue(actual);
			}
		}
		Queue<String[]> ordenados = ordenarViajesQuickSort(viajesDia);
		return ordenados;
	}

	// PARTE C
	/**
	 * 1C Consultar los viajes entre una zona de origen y una zona destino en una
	 * franja horaria (hora inicial - hora final) dada. La franja horaria se define
	 * con horas enteras. Mostrar los viajes indicando el tiempo promedio de viaje y
	 * su desviacion estandar para cada hora entera iniciando en la hora inicial y
	 * terminando en la hora final.
	 * 
	 * @return
	 */

	/**
	 * 2C Consultar la informaci�n de los N viajes con mayor tiempo promedio para una
	 * hora dada.
	 * @param N Numero de viajes a consultar
	 * hora Hora del dia
	 * @return Queue de los viajes ordenados
	 */
	public Queue<String[]> tiempoPromViajesHora(String N, String hora) {
		return tPromViaHora(N, hora, datosH);
	}
	
	public Queue<String[]> tPromViaHora(String N, String hora, Queue<String[]> datos){
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
	 * @param zonaOrigen ID de la zona de origen
	 * zonaDestino ID de la zona destino
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
	 * @param datos Queue de los datos a ordenar
	 * @return Queue con los datos ordenados
	 */
	public Queue<String[]> ordenarViajesQuickSort(Queue<String[]> datos) {
		int izq = 0;
		int der = datos.darTamano() - 1;
		int pivote = quicksort(datos, izq, der);
		if (izq < pivote - 1 && pivote + 1 > der) {
			quicksort(datos, izq, pivote - 1);
			quicksort(datos, pivote + 1, der);
		}
		return datos;
	}

	/**
	 * Ordena los viajes del queue de forma recursiva.
	 * @param datos Queue de los datos a ordenar
	 * izq Numero que representa la posicion del dato en la izquierda del queue
	 * der Numero que representa la posicion del dato en la derecha del queue
	 * @return
	 */
	public int quicksort(Queue<String[]> datos, int izq, int der) {
		String[] pivote = datos.darElemento(izq);
		double horaP = Double.parseDouble(pivote[3]);
		int i = izq-1;
		String[] aux = new String[datos.darElemento(izq).length];
		for (int j = izq; j < der; j++) {
			String[] eleJ = datos.darElemento(j);
			if (Double.parseDouble(eleJ[3]) > horaP) {
				i++;
				aux = datos.darElemento(i);
				for (int k = 0; k < datos.darElemento(i).length; k++) {
					datos.darElemento(i)[k] = datos.darElemento(j)[k];
				}
				for (int k = 0; k < datos.darElemento(i).length; k++) {
					datos.darElemento(j)[k] = aux[k];
				}
			}
		}
		aux = datos.darElemento(i + 1);
		for (int k = 0; k < datos.darElemento(i).length; k++) {
			datos.darElemento(i + 1)[k] = datos.darElemento(der)[k];
		}
		for (int k = 0; k < datos.darElemento(i).length; k++) {
			datos.darElemento(der)[k] = aux[k];
		}
		return i + 1;
	}

	/**
	 * Calcula el tiempo promedio de los viajes del queue dado por parametro
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
	 * Calcula la desviacion estandar promedio de los viajes del queue dado por parametro
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
