package model.data_structures;

public interface IStack<T> {
	
	public void push( T dato );

	public T pop( );

	public int darTamano( );

	public boolean isEmpty( );

	public T consultarElementoTope( );

}
