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
	 * Referencia al primer nodo de la lista.
	 */
	private Node<T> cabeza;
	
	/**
	 * Numero de elementos presentes en el arreglo (de forma compacta desde la
	 * posicion 0)
	 */
	private int tamanoAct;

	/**
	 * Referencia al último nodo de la lista.
	 */
	private Node<T> cola;

	/**
	 * Agrega un nodo con su dato al final de la lista.
	 * @param dato Dato que se debe agregar a la cola.
	 */
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

	/**
	 * Elimina el nodo que se encuentra en la cabeza de la lista.
	 * @return Nodo que fue eliminado.
	 */
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

	/**
	 * Retorna el tamaño actual de la lista.
	 * @return Tamaño actual de la lista.
	 */
	public int darTamano() {
		return tamanoAct;
	}

	/**
	 * Retorna el indicador que dice si la lista esta vacía o no.
	 * @return Indicador si la lista esta vacía o no.
	 */
	public boolean isEmpty() {
		if (darTamano() == 0) {
			return true;
		}
		else {
			return false;
		}
	}

	/**
	 * Consulta el nodo que se encuentra en la cabeza de la lista.
	 * @return Dato del nodo que está en la cabeza de la lista.
	 */
	public T consultarPrimerElemento() {
		return cabeza.dato;
	}

	/**
	 * Clase privada que crea un nodo junto con sus referencias a su dato y al nodo siguiente.
	 * @param <T> Dato de tipo T
	 */
	private class Node<T> {
		T dato;
		Node next;
	}

}