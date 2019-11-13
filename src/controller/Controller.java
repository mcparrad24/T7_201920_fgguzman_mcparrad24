package controller;

import java.util.*;

//import model.data_structures.Queue;
import model.logic.MVCModelo;
import view.MVCView;
import model.logic.Queue;

public class Controller {

	/* Instancia del Modelo */
	private MVCModelo modelo;

	/* Instancia de la Vista */
	private MVCView view;
	
	private Queue<Integer> llaves = new Queue<>();
	private Queue<String> vals = new Queue<>();

	/**
	 * Crear la vista y el modelo del proyecto
	 */
	public Controller() {
		view = new MVCView();
		modelo = new MVCModelo();
	}

	public void run() {
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		String dato = "";
		String[] datos;

		while (!fin) {
			view.printMenu();

			int option = lector.nextInt();
			switch (option) {
			case 1:
				modelo.TXTLector();
				System.out.println("Total zonas: " + modelo.totalZonas() + "\n---------");
				datos = modelo.valoresMinMax();
				System.out.println("Valor maximo: " + datos[0] + "\n---------");
				System.out.println("Valor minimo: " + datos[1] + "\n---------");	
				break;
			case 2:
				System.out.println("--------- \nConsultar zona por id (e.g. 5)");
				dato = lector.next();
				String value = modelo.zonaID(Integer.parseInt(dato));
				if (value == null) {
					System.out.println("No existe la zona con el id dado");
				}
				else {
				String [] valores = value.split(",");
				System.out.println("Nombre de la zona: " + valores[0] + "\n");
				System.out.println("Peimetro de la zona: " + valores[1] + "\n");
				System.out.println("Area de la zona: " + valores[2] + "\n");
				System.out.println("Numero de puntos que definen el perimetro de la zona: " + valores[3] + "\n");
				}
				
				break;
			case 3:
				System.out.println("--------- \nConsultar zonas por id en un rango (e.g. 5,100)");
				dato = lector.next();
				datos = dato.split(",");
				llaves = modelo.idRangoEspecificoLlaves(Integer.parseInt(datos[0]), Integer.parseInt(datos[1]));
				vals = modelo.idRangoEspecificoValores(Integer.parseInt(datos[0]), Integer.parseInt(datos[1]));
				if (llaves == null) {
					System.out.println("No existen zonas que pertenezcan al rango de ID's dados por parametro");
				}
				else {
					int tam = llaves.size();
					for (int i = 0; i < tam; i++) {
						int llave = (int) llaves.dequeue();
						System.out.println("Movement ID: " + llave + "\n");
						String valor = vals.dequeue();
						String valores[] = valor.split(",");
						System.out.println("Nombre de la zona: " + valores[0] + "\n");
						System.out.println("Perimetro de la zona: " + valores[1] + "\n");
						System.out.println("Area de la zona: " + valores[2] + "\n");
						System.out.println("Numero de puntos que definen el perimetro de la zona: " + valores[3] + "\n");
					}
				}
				break;
			case 4:
				System.out.println("--------- \n Hasta pronto !! \n---------");
				lector.close();
				fin = true;
				break;
			default:
				System.out.println("--------- \n Opcion Invalida !! \n---------");
				break;
			}
		}

	}
}
