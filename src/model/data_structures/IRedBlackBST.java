package model.data_structures;

import java.util.Iterator;

public interface IRedBlackBST <Key, Value> {
	
	/**
	 * Retornar el numero de elementos presentes en el árbol
	 * @return tamano del árbol
	 */
	int darTamano( );
	
	/**
	 * Retorna la altura del árbol desde la raíz a la llave dada por parametro
	 * @param llave
	 * @return altura del árbol
	 */
	int alturaLlave(Key llave);
	
	/**
	 * Retorna la altura del árbol desde la raíz a la llave que tenga más enlaces
	 * @return altura del árbol
	 */
	int altura();
	
	/**
	 * Agregar el nuevo elemento.
	 * @param llave y valor del nuevo elemento.
	 */
	void insertar( Key llave, Value valor );
	
	/**
	 * Devuelve el valor del elemento dado.
	 * @param llave del elemento.
	 * @return valor del elemento 
	 */
	Value get(Key llave);
	
	/**
	 * Retorna la llave más pequeña del árbol. Null si el árbol esta vacío.
	 * @return llave más pequeña del árbol o null si esta vacio el árbol
	 */
	Key min();
	
	/**
	 * Retorna la llave más grande del árbol. Null si el árbol esta vacío.
	 * @return llave más grande del árbol o null si esta vacio el árbol
	 */
	Key max();
	
	/**
	 * Dice si el elemento dado está en el árbol o no.
	 * @param llave del elemento a consultar
	 * @return True si está en el árbol, false si no.
	 */
	boolean contains(Key llave);
	
	/**
	 * Dice si el árbol esta vacio o no.
	 * @return True si el árbol esta vacio, false si no.
	 */
	boolean isEmpty();
	
	/**
	 * Dice si el árbol es binario ordenado y está balanceado Rojo-Negro a la izquierda.
	 * @return True si el árbol es binario ordenado y esta balanceado Rojo-Negro a la izquierda, false si no.
	 */
	boolean check();
	
	/**
	 * Retorna todas las llaves del árbol
	 * @return llaves del árbol
	 */
	Iterator<Key> keys();
	
	/**
	 * Retorna las llaves que se encuentran en el rango dado por parametro
	 * @param min llave de la parte baja del rango
	 * @param max llave de la parte alta del rango
	 * @return llaves que se encuentran en el rango
	 */
	Iterator<Key> keysRango(Key min, Key max);
	
	/**
	 * Retorna los valores de las llaves que se encuentran en el rango dado por parametro
	 * @param min llave de la parte baja del rango
	 * @param max llave de la parte alta del rango
	 * @return valores de las llaves que se encuentran en el rango
	 */
	Iterator<Value> valuesRango(Key min, Key max);
}
