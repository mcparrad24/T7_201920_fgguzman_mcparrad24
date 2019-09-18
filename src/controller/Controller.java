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
		String trimestre = "5";
		String[] datos;
		String hora = "", N = "";

		while (!fin) {
			view.printMenu();

			int option = lector.nextInt();
			switch (option) {
			case 1:
				System.out.println("--------- \nDar el numero del trimestre deseado");
				trimestre = lector.next();
				modelo.CSVLector(Integer.parseInt(trimestre));
				System.out.println("Total viajes por hora: " + modelo.totalHora() + "\n---------");
				System.out.println("Total viajes por semana: " + modelo.totalSemana() + "\n---------");
				System.out.println("Total viajes por mes: " + modelo.totalMes() + "\n---------");
				System.out.println("Zona con menor identificador: " + Arrays.toString(modelo.menorIdentificador())
						+ "\n---------");
				System.out.println("Zona con mayor identificador: " + Arrays.toString(modelo.mayorIdentificador())
						+ "\n---------");
				break;
			case 2:
				System.out.println("--------- \nHacer la consulta con:");
				System.out.println("1. Mes");
				System.out.println("2. Dia de la semana");
				int eleccion = lector.nextInt();
				if (eleccion == 1) {
					System.out.println(
							"--------- \nDar el ID en la zona de origen, el ID de la zona destino y el numero del mes (eg. 955,206,2)");
					String rta = lector.next();
					String[] datosMes = rta.split(",");
					String zonaO = datosMes[0];
					String zonaD = datosMes[1];
					String mes = datosMes[2];
					String ans = modelo.tiempoPromedioDesviacionMes(zonaO, zonaD, mes);
					if (ans == null) {
						System.out.println("No existen viajes desde la zona de origen con ID " + zonaO
								+ " hasta la zona destino con ID " + zonaD
								+ " en el mes dado identificado con el numero " + mes);
					} else {
						String[] promedio = ans.split(",");
						String tP = promedio[0];
						String dE = promedio[1];
						System.out.println("El tiempo promedio de los viajes desde la zona de origen con ID " + zonaO
								+ " hasta la zona destino con ID " + zonaD
								+ " en el mes dado identificado con el numero " + mes + " es: " + tP + "\n---------");
						System.out
								.println("La desviacion estandar promedio de los viajes desde la zona de origen con ID "
										+ zonaO + " hasta la zona destino con ID " + zonaD
										+ " en el mes dado identificado con el numero " + mes + " es: " + dE
										+ "\n---------");
					}
				} else if (eleccion == 2) {
					System.out.println(
							"--------- \nDar el ID en la zona de origen, el ID de la zona destino y el numero del dia de la semana, siendo 1 el domingo (eg. 955,206,2)");
					String rta = lector.next();
					String[] datosDia = rta.split(",");
					String zonaO = datosDia[0];
					String zonaD = datosDia[1];
					String dia = datosDia[2];
					String ans = modelo.tiempoPromedioDesviacionDia(zonaO, zonaD, dia);
					if (ans == null) {
						System.out.println("No existen viajes desde la zona de origen con ID " + zonaO
								+ " hasta la zona destino con ID " + zonaD
								+ " en el dia de la semana dado identificado con el numero " + dia);
					} else {
						String[] promedio = ans.split(",");
						String tP = promedio[0];
						String dE = promedio[1];
						System.out.println("El tiempo promedio de los viajes desde la zona de origen con ID " + zonaO
								+ " hasta la zona destino con ID " + zonaD
								+ " en el dia de la semana dado identificado con el numero " + dia + " es: " + tP
								+ "\n---------");
						System.out
								.println("La desviacion estandar promedio de los viajes desde la zona de origen con ID "
										+ zonaO + " hasta la zona destino con ID " + zonaD
										+ " en el dia de la semana dado identificado con el numero " + dia + " es: "
										+ dE + "\n---------");
					}
				}
				break;
			case 3:
				System.out.println("--------- \nHacer la consulta con:");
				System.out.println("1. Mes");
				System.out.println("2. Dia de la semana");
				System.out.println("3. Hora del dia");
				int election = lector.nextInt();
				if (election == 1) {
					System.out.println(
							"--------- \nDar el numero de viajes que desea consultar y el numero del mes (eg. 10,2)");
					String nYM = lector.next();
					String[] ans = nYM.split(",");
					clus = modelo.tiempoPromViajesMes(ans[0], ans[1]);
					System.out.println("Primeros " + ans[0] + " viajes: \n");
					for (int i = 0; i < Integer.parseInt(ans[0]); i++) {
						String[] actual = clus.dequeue();
						System.out.println(actual[0] + actual[1] + actual[2] + actual[3] + "\n---------");
					}
				} else if (election == 2) {
					System.out.println(
							"--------- \nDar el numero de viajes que desea consultar y el numero del dia de la semana, siendo 1 el domingo (eg. 10,2)");
					String nYD = lector.next();
					String[] ans = nYD.split(",");
					clus = modelo.tiempoPromViajesDia(ans[0], ans[1]);
					System.out.println("Primeros " + ans[0] + " viajes: \n");
					for (int i = 0; i < Integer.parseInt(ans[0]); i++) {
						String[] actual = clus.dequeue();
						System.out.println(actual[0] + actual[1] + actual[2] + actual[3] + "\n---------");
					}
				} else if (election == 3) {
					System.out.println(
							"--------- \nDar el numero de viajes que desea consultar y el numero de la hora del dia (eg. 10,2)");
					String nYH = lector.next();
					String[] ans = nYH.split(",");
					clus = modelo.tiempoPromViajesHora(ans[0], ans[1]);
					System.out.println("Primeros " + ans[0] + " viajes: \n");
					for (int i = 0; i < Integer.parseInt(ans[0]); i++) {
						String[] actual = clus.dequeue();
						System.out.println(actual[0] + actual[1] + actual[2] + actual[3] + "\n---------");
					}
				}
				break;
			case 4:
				System.out.println("--------- \nHacer la consulta con:");
				System.out.println("1. Mes");
				System.out.println("2. Dia de la semana");
				int seleccion = lector.nextInt();
				if (seleccion == 1) {
					System.out.println(
							"--------- \nDar el ID en la zona deseada, el ID de la zona menor, el ID de la zona mayor y el numero del mes (eg. 153,4,206,8)");
					String rta = lector.next();
					String[] datosMes = rta.split(",");
					String zonaD = datosMes[0];
					String zonaMe = datosMes[1];
					String zonaMa = datosMes[2];
					String mes = datosMes[3];
					clus = modelo.tiempoPromRangoMes(zonaD, zonaMe, zonaMa, mes);
					if (clus == null) {
						System.out.println("No existen viajes de o hacia la zona" + zonaD + " entre el rango " + zonaMe
								+ "-" + zonaMa + " en el mes dado identificado con el numero " + mes);
					} else {
						for (int i = 0; i < clus.darTamano(); i++) {
							String[] actual = clus.darElemento(i);
							String t1 = "", t2 = "";
							if (actual[0].equals(zonaD)) {
								t1 = actual[3];
							} else {
								t1 = "No hay viajes";
							}
							if (actual[1].equals(zonaD)) {
								t2 = actual[3];
							} else {
								t2 = "No hay viajes";
							}
							System.out.println(t1 + " de " + zonaD + " a " + actual[1] + " vs " + t2 + " de "
									+ actual[0] + " a " + zonaD);
						}
					}
				} else if (seleccion == 2) {
					System.out.println(
							"--------- \nDar el ID en la zona deseada, el ID de la zona menor, el ID de la zona mayor y el numero del dia de la semana, siendo 1 el domingo (eg. 153,4,206,5)");
					String rta = lector.next();
					String[] datosDia = rta.split(",");
					String zonaD = datosDia[0];
					String zonaMe = datosDia[1];
					String zonaMa = datosDia[2];
					String dia = datosDia[3];
					clus = modelo.tiempoPromRangoDia(zonaD, zonaMe, zonaMa, dia);
					if (clus == null) {
						System.out.println("No existen viajes de o hacia la zona" + zonaD + " entre el rango " + zonaMe
								+ "-" + zonaMa + " en el mes dado identificado con el numero " + dia);
					} else {
						for (int i = 0; i < clus.darTamano(); i++) {
							String[] actual = clus.darElemento(i);
							String t1 = "", t2 = "";
							if (actual[0].equals(zonaD)) {
								t1 = actual[3];
							} else {
								t1 = "No hay viajes";
							}
							if (actual[1].equals(zonaD)) {
								t2 = actual[3];
							} else {
								t2 = "No hay viajes";
							}
							System.out.println(t1 + " de " + zonaD + " a " + actual[1] + " vs " + t2 + " de "
									+ actual[0] + " a " + zonaD);
						}
					}
				}
				break;
			case 5:
				System.out.println(
						"--------- \nDar el ID de la zona de origen, el ID de la zona destino y la franja horaria separada por comas (eg.955,206,2,5)");
				String rta = lector.next();
				String[] datosDia = rta.split(",");
				String zonaO = datosDia[0];
				String zonaD = datosDia[1];
				String hora1 = datosDia[2];
				String hora2 = datosDia[2];
				clus = modelo.viajesFranja(zonaO, zonaD, hora1, hora2);
				if (clus == null) {
					System.out.println("No existen viajes desde la zona de origen con ID " + zonaO
							+ " hasta la zona destino con ID " + zonaD + " en la franja horaria " + hora1 + "-"
							+ hora2);
				} else {
					for (int i = 0; i < clus.darTamano(); i++) {
						String[] actual = clus.dequeue();
						System.out.println(actual[0] + actual[1] + actual[2] + actual[3] + "\n---------");
					}
				}
				break;
			case 6:
				System.out.println("--------- \nDar el ID de la zona de origen y el ID de la zona destino (eg. 6,22)");
				dato = lector.next();
				datos = dato.split(",");
				String[] resp = modelo.graficaASCII(datos[0], datos[1]);
				System.out.println("Aproximaci�n en minutos de viajes entre zona origen y zona destino.\n"
						+ "Trimestre " + trimestre + " del 2018 detallado por cada hora del d�a.\n" + "Zona Origen: "
						+ datos[0] + " Zona Destino: " + datos[1] + "\n" + "Hora|  # de minutos\n");
				for (int i = 0; i < resp.length; i++) {
					System.out.println(i + "  |  " + resp[i] + "\n");
				}
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
