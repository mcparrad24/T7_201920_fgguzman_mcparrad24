package controller;

import java.util.*;

//import model.data_structures.Queue;
import model.logic.MVCModelo;
import view.MVCView;

public class Controller {

	/* Instancia del Modelo */
	private MVCModelo modelo;

	/* Instancia de la Vista */
	private MVCView view;

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
				modelo.JSONLector();
				System.out.println("Total zonas: " + modelo.totalZonas() + "\n---------");
				datos = modelo.valoresMinMax();
				System.out.println("Valor minimo: " + datos[0] + "\n---------");
				System.out.println("Valor maximo: " + datos[1] + "\n---------");	
				break;
			case 2:
				System.out.println("--------- \nConsultar zona por id (e.g. 5)");
				dato = lector.next();
				break;
			case 3:
				System.out.println("--------- \nConsultar zonas por id en un rango (e.g. 5,100)");
				dato = lector.next();
				datos = dato.split(",");
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
