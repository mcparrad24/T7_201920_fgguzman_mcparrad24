package model.data_structures;

public interface IStack<T> {
	public void enqueue( T dato );

	public T dequeue( );

	public int darTamano( );

	public boolean isEmpty( );

	public T consultarElementoTope( );

}
