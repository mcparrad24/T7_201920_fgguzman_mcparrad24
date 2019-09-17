package test.logic;

import static org.junit.Assert.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import model.data_structures.Queue;
import model.logic.MVCModelo;

import org.junit.Before;
import org.junit.Test;

import com.opencsv.CSVReader;

public class TestMVCModelo {
	
	private MVCModelo modelo = new MVCModelo();
	Queue<String[]> datosH = new Queue<>();
	Queue<String[]> datosHDes = new Queue<>();

	public void setUp1() {
		CSVReader reader = null;
		String[] header = new String[1];
		try {
			reader = new CSVReader(new FileReader("./data/datos_de_prueba_2.csv"));
			header = reader.readNext();
			for (String[] nextLine : reader) {
				datosH.enqueue(nextLine);
			}
		} catch (Exception e) {
			fail("Fallo la lectura del archivo csv");
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					fail("No se pudo cerrar el lector");
				}	
			}
		}
	}
	
	public void setUp2() {
		CSVReader reader = null;
		String[] header = new String[1];
		try {
			reader = new CSVReader(new FileReader("./data/datos_de_prueba_2_desc.csv"));
			header = reader.readNext();
			for (String[] nextLine : reader) {
				datosHDes.enqueue(nextLine);
			}
		} catch (Exception e) {
			fail("Fallo la lectura del archivo csv");
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					fail("No se pudo cerrar el lector");
				}	
			}
		}
	}
	@Test
	public void testTotalHora() {
		setUp1();
		int total = datosH.darTamano();
		assertEquals("El numero de datos no es correcto", 14, total);
	}

	@Test
	public void testTiempoPromViajesHora() {
		setUp1();
		Queue<String[]> retornados = modelo.tiempoPromViajesHora("4", "3");
		String[] prim = {"6", "30", "3", "1917.27", "404.69", "1873.46", "1.24"};
		String[] seg = {"3", "60", "3", "1321.8", "220.58", "1303.62", "1.18"};
		String[] ter = {"1", "80", "3", "1138.68", "538.33", "1035.88", "1.52"};
		String[] cuar = {"6", "4", "3", "936.58", "374.58", "878.44", "1.41"};
		boolean siEs = false;
		boolean pr = false;
		boolean se = false;
		boolean te = false;
		boolean cu = false;
		if (retornados.darElemento(0)[0].equals(prim[0]) && (retornados.darElemento(0)[1].equals(prim[1])) && (retornados.darElemento(0)[2].equals(prim[2]))){
			pr = true;
		}
		if (retornados.darElemento(1)[0].equals(seg[0]) && (retornados.darElemento(1)[1].equals(seg[1])) && (retornados.darElemento(1)[2].equals(seg[2]))){
			se = true;
		}
		if (retornados.darElemento(2)[0].equals(ter[0]) && (retornados.darElemento(2)[1].equals(ter[1])) && (retornados.darElemento(2)[2].equals(ter[2]))){
			te = true;
		}
		if (retornados.darElemento(3)[0].equals(cuar[0]) && (retornados.darElemento(3)[1].equals(cuar[1])) && (retornados.darElemento(3)[2].equals(cuar[2]))){
			cu = true;
		}
		if (pr && se && te && cu){
			siEs = true;
		}
		assertEquals("Los viajes no fueron retornados correctamente", true, siEs);
	}
	
	@Test
	public void testOrdenarViajesQuickSort() {
		setUp1();
		setUp2();
		Queue<String[]> ordenado = modelo.ordenarViajesQuickSort(datosH);
		int tam = ordenado.darTamano();
		boolean estaBien = false;
		int correctos = 0;
		for (int i = 0; i < 14; i++) {
			if (ordenado.darElemento(i).equals(datosHDes.darElemento(i))){
				correctos++;
			}
		}
		if (correctos == tam) {
			estaBien = true;
		}
		assertEquals("El arreglo no se ordeno correctamente", true, estaBien);
	}
}
