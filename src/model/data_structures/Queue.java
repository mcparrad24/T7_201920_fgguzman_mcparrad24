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
		}
		else {
			Node<T> nodoActual = cabeza;
			while (nodoActual.next != null) {
				nodoActual = nodoActual.next;
			}
			cola = new Node<T>();
			nodoActual.next = cola;
			cola.dato = dato;
		}
    }

	public T dequeue() {
		T eliminado = cabeza.dato;
		if(cabeza.next != null) {
			cabeza.dato = (T) cabeza.next.dato;
			cabeza = cabeza.next;
		} else {
			return null;
		}
		return eliminado;
	}

	public int darTamano() {
		tamanoAct = 0;
		Node<T> nodoActual = cabeza;
		while (nodoActual != null) {
			nodoActual = nodoActual.next;
			tamanoAct++;
		}
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