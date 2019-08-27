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

	public void push(T dato) {

	}

	public T pop() {

		T eliminado = null;
		return eliminado;
	}

	public int darTamano() {
		return tamanoAct;
	}

	public boolean isEmpty() {
		return true;
	}

	public T consultarElementoTope() {
		
		return null;
	}

	private class Node<T> {
		T dato;
		Node next;
	}

}
