package model.data_structures;

public class Stack<T> implements IStack<T> {

	/**
	 * Capacidad maxima del arreglo
	 */
    private int top;
	/**
	 * Numero de elementos presentes en el arreglo (de forma compacta desde la posicion 0)
	 */
    private int tamanoAct;
    
    
    private int bottom;
    
	public void push( T dato )
    {
          
   }

	public T pop( ) {
		
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
		// TODO implementar
		// Recomendacion: Usar el criterio de comparacion natural (metodo compareTo()) definido en Strings.
		return null;
	}

	@Override
	public void enqueue(T dato) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T dequeue() {
		// TODO Auto-generated method stub
		return null;
	}

}
