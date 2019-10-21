package model.logic;

import java.io.FileReader;

import model.data_structures.RedBlackBST;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

/**
 * Definicion del modelo del mundo
 *
 */
public class MVCModelo {
	private ZonaJSON zonas;
	private RedBlackBST<Integer,String> datos = new RedBlackBST<>();
	// CARGA DE INFORMACION
	/**
	 * Lector de los archivos de JSON
	 */

	public void JSONLector() {
		Gson gson = new Gson();
		String path = "./data/bogota_cadastral.json";
		JsonReader reader;
		int i = 0, pts;
		try {
			reader = new JsonReader(new FileReader(path));
			JsonElement elem = JsonParser.parseReader(reader);
			JsonElement e = elem.getAsJsonObject().get("features").getAsJsonArray().get(0).getAsJsonObject().get("properties");
			while(i < elem.getAsJsonObject().get("features").getAsJsonArray().size()) {
				e = elem.getAsJsonObject().get("features").getAsJsonArray().get(i).getAsJsonObject().get("properties");
				pts = elem.getAsJsonObject().get("features").getAsJsonArray().get(0).getAsJsonObject().get("geometry").getAsJsonObject().get("coordinates").getAsJsonArray().size();
				zonas = gson.fromJson(e, ZonaJSON.class);
				zonas.setPtosGeo(pts);
				datos.insertar(zonas.getId(),zonas.getValor());
				i++;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public int totalZonas() {
		int total = datos.darTamano();
		return total;
	}
	public String[] valoresMinMax() {
		String[] rtn = new String[2];
		rtn[0] = datos.max().toString();
		rtn[1] = datos.min().toString();
		return rtn;
	}

	
}
