package model.data_structures;

/**
 * 2019-01-23 Estructura de Datos Arreglo Dinamico de Strings. El arreglo al
 * llenarse (llegar a su maxima capacidad) debe aumentar su capacidad.
 * 
 * @author Fernando De la Rosa
 *
 */
public class Queue<T> implements IQueue<T> {
	/**
	 * Capacidad maxima del arreglo
	 */
	private Node<T> cabeza;
	/**
	 * Numero de elementos presentes en el arreglo (de forma compacta desde la
	 * posicion 0)
	 */
	private int tamanoAct;

	private Node<T> cola;

	public void enqueue( T dato )
    {
		if (cabeza == null) {
			cabeza = new Node<T>();
			cabeza.dato = dato;
			cola = cabeza;
			tamanoAct = 1;
		}
		else {
			Node <T> nuevo = new Node<T>();
			nuevo.dato = dato;
			cola.next = nuevo;
			cola = nuevo;
			tamanoAct++;
		}
    }

	public T dequeue() {
		T eliminado = null;
		if (cabeza != null) {
			eliminado = cabeza.dato;
			if(cabeza.next != null) {
				cabeza = cabeza.next;
				tamanoAct--;
			} else {
				cabeza = null;
				cola = null;
				tamanoAct = 0;
			}
		}
		return eliminado;
	}

	public int darTamano() {
		return tamanoAct;
	}

	public boolean isEmpty() {
		if (darTamano() == 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public T consultarPrimerElemento() {
		return cabeza.dato;
	}


	private class Node<T> {
		T dato;
		Node next;
	}

}