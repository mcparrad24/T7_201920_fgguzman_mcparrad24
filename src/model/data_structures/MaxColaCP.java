package model.data_structures;

public class MaxColaCP<T extends Comparable<T>> implements IMaxColaCP<T> {

	private Node<T> first;

	private Node<T> last;

	private int tamanoAct;

	private Node<T> max;

	public MaxColaCP() {
		first = null;
		last = null;
		max = null;
		tamanoAct = 0;
	}

	/**
	 * Retornar el numero de elementos presentes en la cola
	 * 
	 * @return tama�o de la cola
	 */
	public int darTamano() {
		return tamanoAct;
	}

	/**
	 * Agregar el nuevo datos.
	 * 
	 * @param dato nuevo elemento
	 */
	public void insertar(T dato) {
		if (first == null) {
			first = new Node<T>();
			first.dato = dato;
			last = first;
			max = first;
			tamanoAct = 1;
		} else {
			Node<T> nuevo = new Node<T>();
			nuevo.dato = dato;
			last.next = nuevo;
			nuevo.previous = last;
			last = nuevo;
			if (nuevo.dato.compareTo(max.dato) >= 1) {
				max = nuevo;
			}
			tamanoAct++;
		}
	}

	/**
	 * Eliminar el dato maximo de la cola.
	 * 
	 * @return dato eliminado
	 */
	public T eliminarMax() {
		T eliminado = max.dato;
		if (max.previous != null) {
			max.previous.next = max.next;
		}
		if (max.next != null) {
			max.next.previous = max.previous;
		}
		tamanoAct--;
		Node<T> actual = first;
		max = first;
		for (int i = 0; i < tamanoAct; i++) {
			if (actual.dato.compareTo(max.dato) >= 1) {
				max = actual;
			}
			actual = actual.next;
		}
		return eliminado;
	}

	/**
	 * Devuelve el dato maximo
	 * 
	 * @return dato maximo
	 */
	public T getMax() {
		return max.dato;
	}

	/**
	 * Dice si la cola esta vacia o no.
	 * 
	 * @return True si la cola esta vacia, false si no.
	 */
	public boolean isEmpty() {
		if (tamanoAct == 0) {
			return true;
		} else {
			return false;
		}
	}

	public T getLast() {
		return last.dato;
	}

	public T getIndex(int N) {
		Node<T> retorno = max;
		for (int i = 0; i < N; i++) {
			if (retorno.previous != null) {
				retorno.previous.next = retorno.next;
			}
			if (retorno.next != null) {
				retorno.next.previous = retorno.previous;
			}
		}
		return retorno.dato;
	}

	private class Node<T> {
		T dato;
		Node<T> next;
		Node<T> previous;
	}
}
