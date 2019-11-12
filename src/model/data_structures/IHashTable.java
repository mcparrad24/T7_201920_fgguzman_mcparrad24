package model.data_structures;

public interface IHashTable<K,V> {

	/**
	 * Retornar el numero de elementos presentes en el arreglo
	 * @return
	 */
	int darTamano( );
	
	/**
	 * Agregar la llave y el valor.
	 * @param dato nuevo elemento
	 */
	public void put( K llave , V dato);
	
	/**
	 * Eliminar la llave K y su valor asociado
	 * @return dato asociado con la llave
	 */
	public V delete(K llave);
	/**
	 * Devuelve el dato asociado con la llave
	 * @return dato
	 */
	public V get(K llave);

}
