package controller;

import java.util.Scanner;

import model.logic.MVCModelo;
import view.MVCView;

public class Controller {

	/* Instancia del Modelo */
	private MVCModelo modelo;

	/* Instancia de la Vista */
	private MVCView view;

	/**
	 * Crear la vista y el modelo del proyecto
	 * 
	 * @param capacidad tamaNo inicial del arreglo
	 */
	public Controller() {
		view = new MVCView();
		modelo = new MVCModelo();
	}

	public void run() {
		Scanner lector = new Scanner(System.in);
		boolean fin = false;
		int num = 0;
		String dato = "";
		String respuesta = "";

		while (!fin) {
			view.printMenu();

			int option = lector.nextInt();
			switch (option) {
			case 1:
				System.out.println("--------- \nRealizar carga de datos");
				num = modelo.totalViajesTrimestre();
				System.out.println("Viajes totales: " + num + "\n---------");
				System.out.println("Primer viaje del trimestre: " + modelo.darTamano() + "\n---------");
				System.out.println("Último viaje del trimestre: " + modelo.darTamano() + "\n---------");
				break;

			case 2:
				System.out.println("--------- \nConsulta del cluster más grande");
				dato = lector.next();
				modelo.agregar(dato);
				System.out.println("Dato agregado");
				System.out.println("Numero actual de elementos " + modelo.darTamano() + "\n---------");
				break;

			case 3:
				System.out.println("--------- \nConsultar un número de viajes en un hora dada");
				dato = lector.next();
				respuesta = modelo.buscar(dato);
				if (respuesta != null) {
					System.out.println("Dato encontrado: " + respuesta);
				} else {
					System.out.println("Dato NO encontrado");
				}
				System.out.println("Numero actual de elementos " + modelo.darTamano() + "\n---------");
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
