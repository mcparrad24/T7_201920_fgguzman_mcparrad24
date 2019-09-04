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
				System.out.println("--------- \nDar el n�mero del trimestre deseado");
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
			case 2:
				System.out.println("--------- \nHacer la consulta con:");
				System.out.println("1. Mes");
				System.out.println("2. Dia de la semana");
				int eleccion = lector.nextInt();
				if (eleccion == 1) {
					System.out.println("--------- \nDar el ID en la zona de origen, el ID de la zona destino y el numero del mes (eg. 955,206,2");
				}
				else if (eleccion == 2) {
					System.out.println("--------- \nDar el ID en la zona de origen, el ID de la zona destino y el numero del dia de la semana, siendo 1 el domingo (eg. 955,206,2");
				}
				break;
			case 3:
				System.out.println("--------- \nHacer la consulta con:");
				System.out.println("1. Mes");
				System.out.println("2. Dia de la semana");
				System.out.println("3. Hora del dia");
				int election = lector.nextInt();
				if (election == 1) {
					System.out.println("--------- \nDar el numero de viajes que desea consultar y el numero del mes (eg. 10,2");
				}
				else if (election == 2) {
					System.out.println("--------- \nDar el numero de viajes que desea consultar y el numero del dia de la semana, siendo 1 el domingo (eg. 10,2");
				}
				else if (election == 3) {
					System.out.println("--------- \nDar el numero de viajes que desea consultar y el numero de la hora del dia (eg. 10,2");
				}
				break;
			case 4:
				System.out.println("--------- \nHacer la consulta con:");
				System.out.println("1. Mes");
				System.out.println("2. Dia de la semana");
				int seleccion = lector.nextInt();
				if (seleccion == 1) {
					System.out.println("--------- \nDar el ID de la zona y el numero del mes (eg. 110,2");
				}
				else if (seleccion == 2) {
					System.out.println("--------- \nDar el ID de la zona y el numero del dia de la semana, siendo 1 el domingo (eg. 110,2");
				}
				break;
			case 5:
				System.out.println("--------- \nDar el ID de la zona de origen, el ID de la zona destino y la franja horaria entre parentesis (eg.955,206,(2-5)");
				break;
			case 6:
				System.out.println("--------- \nDar el ID de la zona de origen y el ID de la zona destino");
			case 7:
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
