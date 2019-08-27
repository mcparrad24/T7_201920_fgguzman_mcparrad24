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
		if (top == null) {
			top = new Node<T>();
			top.dato = dato;
		}
		else {
			Node<T> nodoActual = top;
			while (nodoActual.next != null) {
				nodoActual = nodoActual.next;
			}
			bottom = new Node<T>();
			nodoActual.next = bottom;
			bottom.dato = dato;
		}
          
    }

	public T pop( ) {
		Node<T> nodoActual = top;
		while (nodoActual.next != null) {
			nodoActual = nodoActual.next;
		}
		T eliminado = (T) nodoActual.next.dato;
		nodoActual.next = null;
		bottom = nodoActual;
		return eliminado;
	}

	public int darTamano() {
		tamanoAct = 0;
		Node<T> nodoActual = top;
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
