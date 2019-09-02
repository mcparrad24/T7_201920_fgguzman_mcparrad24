package view;

import model.logic.MVCModelo;

public class MVCView 
{
	    /**
	     * Metodo constructor
	     */
	    public MVCView()
	    {
	    	
	    }
	    
		public void printMenu()
		{
			System.out.println("1. Realizar carga de datos");
			System.out.println("2. Consultar el tiempo promedio de viaje y su desviación estándar entre una zona de origen y una zona destino");
			System.out.println("3. Consultar la información de un número de viajes con mayor tiempo promedio");
			System.out.println("4. Comparar los tiempos promedios de los viajes para una zona dada contra cada zona en un rango de zonas en ambos sentidos");
			System.out.println("5. Consultar los viajes entre una zona de origen y una zona destino en una franja horaria (hora inicial - hora final) dada");
			System.out.println("6. Generar una gráfica ASCII que muestre el tiempo promedio de los viajes entre una zona de origen y una zona destino para cada hora del día");
			System.out.println("7. Exit");
			System.out.println("Dar el numero de opcion a resolver, luego oprimir tecla Return: (e.g., 1):");
		}

		public void printMessage(String mensaje) {

			System.out.println(mensaje);
		}		
		
		public void printModelo(MVCModelo modelo)
		{
			// TODO implementar
		}
}
