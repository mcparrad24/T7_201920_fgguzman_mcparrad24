package model.data_structures;

public interface IBinaryTree <Key, Value> {
	
	/**
	 * Retornar el numero de elementos presentes en el árbol
	 * @return tamano del árbol
	 */
	int darTamano( );
	
	/**
	 * Agregar el nuevo elemento.
	 * @param llave y valor del nuevo elemento.
	 */
	public void insertar( Key llave, Value valor );
	
	/**
	 * Eliminar el elemento dado del árbol.
	 * @param llave del elemento a eliminar.
	 */
	public void eliminar(Key llave);
	
	/**
	 * Devuelve el valor del elemento dado.
	 * @param llave del elemento.
	 * @return valor del elemento 
	 */
	public Value get(Key llave);
	
	/**
	 * Dice si el elemento dado está en el árbol o no.
	 * @param llave del elemento a consultar
	 * @return True si está en el árbol, false si no.
	 */
	public boolean contains(Key llave);
	
	/**
	 * Dice si el árbol esta vacio o no.
	 * @return True si el árbol esta vacio, false si no.
	 */
	public boolean isEmpty();
	
	/**
	 * Retorna las llaves que se encuentran en el rango dado por parametro
	 * @param min llave de la parte baja del rango
	 * @param max llave de la parte alta del rango
	 * @return llaves que se encuentran en el rango
	 */
	public Iterable<Key> keys(Key min, Key max);

}
