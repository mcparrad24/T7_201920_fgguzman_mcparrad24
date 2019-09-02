package controller;

import java.util.*;

import model.data_structures.Queue;
import model.logic.MVCModelo;
import view.MVCView;

public class Controller {

	/* Instancia del Modelo */
	private MVCModelo modelo;

	/* Instancia de la Vista */
	private MVCView view;

	private Queue<String[]> clus = new Queue<>();

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
		int num = 0, j = 0;
		String dato = "";
		String[] datos;
		String hora = "", N = "";

		while (!fin) {
			view.printMenu();

			int option = lector.nextInt();
			switch (option) {
			case 1:
				System.out.println("--------- \nDar el número del trimestre deseado");
				dato = lector.next();
				modelo.CVSLector(Integer.parseInt(dato));
				System.out.println(
						"Total viajes por hora: " + modelo.totalHora() + "\n---------");
				System.out.println(
						"Total viajes por semana: " + modelo.totalSemana() + "\n---------");
				System.out.println(
						"Total viajes por mes: " + modelo.totalMes() + "\n---------");
				System.out.println(
						"Zona con menor identificador: " + Arrays.toString(modelo.menorIdentificador()) + "\n---------");
				System.out.println(
						"Zona con mayor identificador: " + Arrays.toString(modelo.mayorIdentificador()) + "\n---------");
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
