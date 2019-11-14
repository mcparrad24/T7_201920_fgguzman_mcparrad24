package view;

import model.logic.MVCModelo;

public class MVCView {
	/**
	 * Metodo constructor
	 */
	public MVCView() {

	}
	/**
	 * Muestra el menu en consola
	 */
	public void printMenu() {
		System.out.println("1. Realizar carga de datos");
		System.out.println("2. Persistir grafo en esquema JSON");
		System.out.println("3. Cargar el grafo persistido de la opción 2");
		System.out.println("4. Consultar el número de componentes conexos del grafo");
		System.out.println("5. Graficar con GoogleMaps");
		System.out.println("6. Exit");
		System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
	}

	public void printMessage(String mensaje) {

		System.out.println(mensaje);
	}

	public void printModelo(MVCModelo modelo) {
		// TODO implementar
	}
}
