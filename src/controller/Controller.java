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
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
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
