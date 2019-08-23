package model.data_structures;

/**
 * 2019-01-23
 * Estructura de Datos Arreglo Dinamico de Strings.
 * El arreglo al llenarse (llegar a su maxima capacidad) debe aumentar su capacidad.
 * @author Fernando De la Rosa
 *
 */
public class Queue<T> implements IQueue<T> {
		/**
		 * Capacidad maxima del arreglo
		 */
        private Node<T> cabeza;
		/**
		 * Numero de elementos presentes en el arreglo (de forma compacta desde la posicion 0)
		 */
        private int tamanoAct;
        
        
        private Node<T> cola;
        
		public void enqueue( T dato )
        {
              
       }

		public T dequeue() {
			
			T eliminado = null;
			return eliminado;
		}

		public int darTamano() {
			return tamanoAct;
		}

		public boolean isEmpty() {
			return true;
		}

		public T consultarPrimerElemento() {
			// TODO implementar
			// Recomendacion: Usar el criterio de comparacion natural (metodo compareTo()) definido en Strings.
			return null;
		}
		
		private class Node<T> {
			T dato;
			Node next;
		}

}