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
		System.out.println("2. Consultar las letras más frecuentes por las que comienza el nombre de una zona");
		System.out.println("3. Consultar los nodos que delimitan las zonas por localización geográfica");
		System.out.println("4. Consultar los tiempos promedio de viaje");
		System.out.println("5. Consultar las zonas que están más al norte");
		System.out.println("6. Consultar los nodos de la malla vial por localización geográfica");
		System.out.println("7. Consultar los tiempos de espera que tienen una desviación estándar en un rango dado y que son del primer trimestre del 2018");
		System.out.println("8. Consultar las zonas priorizadas por la mayor cantidad de nodos que definen su frontera");
		System.out.println("9. Generar una grafica ASCII que muestre el porcentaje de datos faltantes para el primer semestre 2018");
		System.out.println("10. Exit");
		System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
	}

	public void printMessage(String mensaje) {

		System.out.println(mensaje);
	}

	public void printModelo(MVCModelo modelo) {
		// TODO implementar
	}
}
