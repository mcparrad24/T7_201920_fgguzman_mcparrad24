package model.logic;

import model.data_structures.Queue;

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

	// CARGA DE INFORMACIÓN
	/**
	 * Lector de los archivos de excel
	 * 
	 * @param numero de trimestre deseado
	 */
	public void CVSLector(int num) {
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
		int menor = 99;
		int test = 99;
		for (int i = 0; i < copia.darTamano(); i++) {
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
		for (int i = 0; i < copia.darTamano(); i++) {
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
		for (int i = 0; i < copia.darTamano(); i++) {
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
		int mayor = 0;
		int test = 0;
		for (int i = 0; i < copia.darTamano(); i++) {
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
		for (int i = 0; i < copia.darTamano(); i++) {
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
		for (int i = 0; i < copia.darTamano(); i++) {
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
	 * 1A Consultar el tiempo promedio de viaje y su desviación estándar de los
	 * viajes entre una zona de origen y una zona destino para un mes dado.
	 * 
	 * @return String de tiempo promedio y desviación estándar
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
	 * 2A Consultar la información de los N viajes con mayor tiempo promedio para un
	 * mes dado.
	 * 
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
	 * sentidos (zona dada –zona X vs. zona X –zona dada) para un mes dado. Mostrar
	 * los resultados de comparación con cada zona X en una línea de la siguiente
	 * forma: <tiempo promedio> de <Zona dada> a <Zona X> vs <tiempo promedio> de
	 * <Zona X> a <Zona dada> Si no hay viajes en algún sentido o en ambos entre la
	 * zona dada y una zona X debe reemplazarse <tiempo promedio> por "No hay
	 * viajes" (en el sentido respectivo). Los resultados deben estar ordenados
	 * ascendentemente por el identificador de la zona X en el rango dado.
	 * 
	 * @return
	 */

	// PARTE B
	/**
	 * 1B Consultar el tiempo promedio de viaje y su desviación estándar de los
	 * viajes entre una zona de origen y una zona destino para un día dado de la
	 * semana.
	 * 
	 * @return String de tiempo promedio y desviación estándar
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
	 * 2B Consultar la información de los N viajes con mayor tiempo promedio para un
	 * día dado.
	 * 
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
	 * sentidos (zona dada –zona X vs. zona X –zona dada) para un día dado. Mostrar
	 * los resultados de comparación con cada zona X en una línea de la siguiente
	 * forma: <tiempo promedio> de <Zona dada> a <Zona X> vs <tiempo promedio> de
	 * <Zona X> a <Zona dada> Si no hay viajes en algún sentido o en ambos entre la
	 * zona dada y una zona X debe reemplazarse <tiempo promedio> por "No hay
	 * viajes" (en el sentido respectivo). Los resultados deben estar ordenados
	 * ascendentemente por el identificador de la zona X en el rango dado.
	 * 
	 * @return
	 */

	// PARTE C
	/**
	 * 1C Consultar los viajes entre una zona de origen y una zona destino en una
	 * franja horaria (hora inicial –hora final) dada. La franja horaria se define
	 * con horas enteras. Mostrar los viajes indicando el tiempo promedio de viaje y
	 * su desviación estándar para cada hora entera iniciando en la hora inicial y
	 * terminando en la hora final.
	 * 
	 * @return
	 */

	/**
	 * 2C Consultar la información de los N viajes con mayor tiempo promedio para un
	 * día dado.
	 * 
	 * @return Queue de los viajes ordenados
	 */
	public Queue<String[]> tiempoPromViajesHora(String N, String hora) {
		int tam = datosH.darTamano();
		Queue<String[]> viajesHora = new Queue<String[]>();
		for (int i = 0; i < tam; i++) {
			String[] actual = datosH.dequeue();
			if (Integer.parseInt(actual[2]) == Integer.parseInt(hora)) {
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
	 * 32C Generar una gráfica ASCII que muestre el tiempo promedio de los viajes
	 * entre una zona origen y una zona destino para cada hora del día. Cada * en la
	 * gráfica corresponde a 1 minuto. Un tiempo promedio se aproxima a los minutos
	 * más cercanos.
	 * 
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

	// MÉTODOS ÚTILES
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

	public int quicksort(Queue<String[]> datos, int izq, int der) {
		String[] pivote = datos.darElemento(izq);
		int i = izq - 1;
		String[] aux = new String[datos.darElemento(izq).length];
		for (int j = izq; j < der; j++) {
			if (Integer.parseInt(datos.darElemento(j)[3]) > Integer.parseInt(pivote[3])) {
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
