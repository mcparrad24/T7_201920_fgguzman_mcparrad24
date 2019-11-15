package model.logic;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
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
	private Graph<Integer, String> datos = new Graph<>(1);
	private Queue<Integer> keys = new Queue<>();
	private Queue<String> values = new Queue<>();
	private Arco[] datosArc = new Arco[528045];
	private Vertice[] datosVer = new Vertice[225895];

	// CARGA DE INFORMACION
	/**
	 * Lector de los archivos de texto
	 */
	public void TXTLector() {
		FileReader fr = null;
		BufferedReader br = null;
		String st;
		String[] a = new String[10];
		int i, j = 0;
		try {
			fr = new FileReader("./data/bogota_vertices.txt");
			br = new BufferedReader(fr);
			br.readLine();
			while ((st = br.readLine()) != null) {
				a = st.split(";");
				infos = new Info(a[1], a[2], a[3]);
				vertices = new Vertice(a[0], infos);
				datosVer[j++] = vertices;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			fr = new FileReader("./data/bogota_arcos.txt");
			br = new BufferedReader(fr);
			while ((st = br.readLine()) != null) {
				a = st.split(" ");
				j = 0;
				st = a[0];
				for (i = 1; i < a.length; i++) {
					arcos = new Arco(st, a[i], "0");
					datosArc[j++] = arcos;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public int totalVertice() {
		return datosVer.length;
	}

	public int totalArcos() {
		return datosArc.length;
	}

	public void generarHTML() {
		int a = 0xFF0000,b = 0x50;
		File file = new File("./data/Mapa.html");
		int i = 0;
		try {
		// creates the file
		file.createNewFile();
		// creates a FileWriter Object
		FileWriter writer = new FileWriter(file);
		// Writes the content to the file
		writer.write("<!DOCTYPE html>\r\n" + 
				"<html>\r\n" + 
				"\r\n" + 
				"<head>\r\n" + 
				"  <meta name=\"viewport\" content=\"initial-scale=1.0, user-scalable=no\">\r\n" + 
				"  <meta charset=\"utf-8\">\r\n" + 
				"  <title>Simple Polylines</title>\r\n" + 
				"  <style>\r\n" + 
				"    #map {\r\n" + 
				"      height: 100%;\r\n" + 
				"    }\r\n" + 
				"    html,\r\n" + 
				"    body {\r\n" + 
				"      height: 100%;\r\n" + 
				"      margin: 0;\r\n" + 
				"      padding: 0;\r\n" + 
				"    }\r\n" + 
				"  </style>\r\n" + 
				"</head>\r\n" + 
				"<body>\r\n" + 
				"  <div id=\"map\"></div>\r\n" + 
				"  <script>\r\n" + 
				"    function initMap() {\r\n" + 
				"      var map = new google.maps.Map(document.getElementById('map'), {\r\n" + 
				"        zoom: 4.5,\r\n" + 
				"        center: {\r\n" + 
				"          lat: 4.585891,\r\n" + 
				"          lng: -74.046699\r\n" + 
				"        },\r\n" + 
				"        mapTypeId: 'roadmap'\r\n" + 
				"      });\r\n" + 
				"	var line;\r\n" + 
				"        var path;\r\n" + 
				"	var GrafoC;\r\n"
				+ "   var Grafo;\r\n"); 
		while(i<10000) {
			String hex = "";
			String wr = escribirParte(datosVer[i]);
			writer.write("          GrafoC "+wr);
			i++;
			System.out.println(i);
			writer.write("     Grafo = new google.maps.Polyline({\r\n" + 
					"          path: GrafoC,\r\n" + 
					"          geodesic: true,\r\n"+
					"          strokeColor: '#");
			a = a + b;
			hex = Integer.toHexString(a);
			writer.write(hex+"',\r\n" + 
					"          strokeOpacity: 1.0,\r\n" + 
					"          strokeWeight: 2\r\n" + 
					"        });\r\n" + 
					"        Grafo.setMap(map);\r\n");
		}
		writer.write( 
				"    }\r\n" + 
				"  </script>\r\n" + 
				"  <script async defer src=\"https://maps.googleapis.com/maps/api/js?key=&callback=initMap\">\r\n" + 
				"  </script>\r\n" + 
				"</body>\r\n" + 
				"\r\n" + 
				"</html>");
		writer.flush();
		writer.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private String escribirParte(Vertice V) {
		int i,j = 0,k;
		String wr = "= [{lat: "+V.getInfo().getLat()+",lng: "+V.getInfo().getLng()+"},\r\n";
		for(i = 0; j<4; i++) {
			if((datosArc[i].getIdInicio() == V.getId()) || (datosArc[i].getIdInicio() > V.getId())) {
				if(datosArc[i].getIdInicio() > 225895)
					k = 225894;
				else k = datosArc[i].getIdInicio();
				for(; k>0; k--) {
					if(datosVer[k] != null && datosVer[k].getId() == datosArc[i].getIdInicio()) {
						wr = wr + "{lat: "+datosVer[k].getInfo().getLat()+",lng: "+datosVer[k].getInfo().getLng()+"},\r\n";
						break;
					}
				}
				j++;
			}
		}
		wr = wr + "];\r\n";
		return wr;
	}
}
