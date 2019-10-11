package model.data_structures;

public interface IMaxColaCP<T> {

	/**
	 * Retornar el numero de elementos presentes en la cola
	 * @return tamaño de la cola
	 */
	int darTamano( );
	
	/**
	 * Agregar el nuevo datos.
	 * @param dato nuevo elemento
	 */
	public void insertar( T dato );
	
	/**
	 * Eliminar el dato maximo de la cola.
	 * @return dato eliminado
	 */
	public T eliminarMax();
	
	/**
	 * Devuelve el dato maximo
	 * @return dato maximo
	 */
	public T getMax();
	
	/**
	 * Dice si la cola esta vacia o no.
	 * @return True si la cola esta vacia, false si no.
	 */
	public boolean isEmpty();
}

