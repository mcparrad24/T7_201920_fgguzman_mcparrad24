package model.data_structures;

public class Stack<T> implements IStack<T> {

	/**
	 * Capacidad maxima del arreglo
	 */
	private Node<T> top;
	/**
	 * Numero de elementos presentes en el arreglo (de forma compacta desde la
	 * posicion 0)
	 */
	private int tamanoAct;

	private Node<T> bottom;

	public void push( T dato )
    {
		if (bottom == null) {
			bottom = new Node<T>();
			bottom.dato = dato;
		}
		else {
			Node<T> nodoActual = bottom;
			while (nodoActual.next != null) {
				nodoActual = nodoActual.next;
			}
			top = new Node<T>();
			nodoActual.next = top;
			top.dato = dato;
		}
          
    }

	public T pop( ) {
		Node<T> nodoActual = bottom;
		T eliminado = nodoActual.dato;
		while(nodoActual.next != top) {
			nodoActual = nodoActual.next;
			if(nodoActual != null) {
				eliminado = (T) nodoActual.next.dato;
			} else {
				return null;
			}
		}
		nodoActual.next = null;
		top = nodoActual;
		return eliminado;
	}

	public int darTamano() {
		tamanoAct = 0;
		Node<T> nodoActual = bottom;
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

	public T consultarElementoTope() {
		return top.dato;
	}

	private class Node<T> {
		T dato;
		Node next;
	}

}
