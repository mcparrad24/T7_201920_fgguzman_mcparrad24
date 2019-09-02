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
						"Ultimo viaje del trimestre: " + Arrays.toString(modelo.menorIdentificador()) + "\n---------");
				System.out.println(
						"Ultimo viaje del trimestre: " + Arrays.toString(modelo.mayorIdentificador()) + "\n---------");
				break;

			case 2:
				System.out.println("--------- \nDar una hora entera de [0-23] (e.g., 1)");
				dato = lector.next();
				clus = modelo.cluster(Integer.parseInt(dato));
				System.out.println("Cluster de elementos mas grande: " + clus.darTamano());
				for (int i = 0; i < clus.darTamano(); i++) {
					System.out.println(Arrays.toString(clus.dequeue()) + "\n---------");
				}
				break;

			case 3:
				System.out.println(
						"--------- \nConsultar ultimos viajes en un hora dada \nDar el numero del viajes seguido por una coma y una hora entera de [0-23] (e.g., 1, 18):");
				dato = lector.next();
				datos = dato.split(",");
				N = datos[0];
				hora = datos[1];
				clus = modelo.viajesNH(Integer.parseInt(N), hora);
				System.out.println("Ultimos viajes de la hora: " + hora);
				j = clus.darTamano();
				for (int i = 0; i < j; i++) {
					System.out.println(Arrays.toString(clus.dequeue()) + "\n---------");
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
