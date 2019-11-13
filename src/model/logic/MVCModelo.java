package model.logic;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Iterator;

import model.data_structures.Graph;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

/**
 * Definicion del modelo del mundo
 *
 */
public class MVCModelo {
	private ZonaJSON zonas;
	private Vertice vertices;
	private Arco arcos;
	private Info infos;
	//private Graph<Integer,String> datos = new Graph<>();
	private Queue<Integer> keys = new Queue<>();
	private Queue<String> values = new Queue<>();
	// CARGA DE INFORMACION
	/**
	 * Lector de los archivos de texto
	 */
	public void TXTLector() {
		FileReader fr = null;
		BufferedReader br = null;
		String st;
		String[] a = new String[10];
		int i,j=0;
		try {
			fr = new FileReader("./data/bogota_arcos.txt");
			br = new BufferedReader(fr);
			br.readLine();
			while ((st = br.readLine()) != null) {
				a = st.split(" ");
				st = a[0];
				for (i = 1; i<a.length;i++) {
					System.out.println(Arrays.toString(a));
					arcos = new Arco(st,a[i],"0");
					System.out.println(arcos.toString());
				}
				//datosMallas.put(mallas.getNodo(), mallas.getCoordenadas());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			fr = new FileReader("./data/bogota_vertices.txt");
			br = new BufferedReader(fr);
			br.readLine();
			while ((st = br.readLine()) != null) {
				a = st.split(";");
				infos = new Info(a[1], a[2], a[3]);
				vertices = new Vertice(a[0], infos);
				//datosMallas.put(mallas.getNodo(), mallas.getCoordenadas());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public int totalZonas() {
		return datos.darTamano();
	}
	
	public String[] valoresMinMax() {
		String[] rtn = new String[2];
		rtn[0] = datos.max().toString();
		rtn[1] = datos.min().toString();
		return rtn;
	}
	
	public String zonaID(int movID) {
		String valor = datos.get(movID);
		return valor;
	}
	
	public Queue<Integer> idRangoEspecificoLlaves(int movIDIn, int movIDMax) {
		Iterator<Integer> llaves = datos.keysRango(movIDIn, (movIDMax+1));
		int key = (int) llaves.next();
		while (llaves.hasNext()) {
			keys.enqueue(key);
			key = (int) llaves.next();
		}
		return keys;
	}
	
	public Queue<String> idRangoEspecificoValores(int movIDIn, int movIDMax) {
		Iterator<String> valores = datos.valuesRango(movIDIn, (movIDMax+1));
		String valor = (String) valores.next();
		while (valores.hasNext()) {
			values.enqueue(valor);
			valor = (String) valores.next();
		}
		return values;
	}
}
