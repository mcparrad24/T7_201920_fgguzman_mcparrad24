package model.data_structures;
import java.lang.reflect.Array;

public class Stack<T> implements IStack<T> {

	private T[] contenedor;
	private T[] copia;
	private int top;
	private int maxT = 10;

	public Stack() {
        this.contenedor = (T[]) new Object[maxT];
        this.copia = (T[]) new Object[maxT];
        this.top = -1;
	}

	private T[] resizeArray() {
        //crea un arreglo más grande y emte los elementos al nuevo
        int newSize = maxT * 2;
        T[] newArray = (T[]) Array.newInstance(Stack.class, newSize);
        for(int i = 0; i < maxT; i++) {
            newArray[i] = this.contenedor[i];
        }
        return newArray;
    }
	
	public T consultarElementoTope() {
		if (top == -1)
			return null;
		return contenedor[top];
	}

	public boolean isEmpty() {
		return (top == -1);
	}
	
	public boolean isFull() {
        return top == maxT-1;
    }

	public T pop() {
		if (top == -1)
			return null;
		return contenedor[top--];
	}

	public void push(T item) {
		if(!this.isFull()) {
            top++;
            contenedor[top] = item;
        }
        else {
            this.contenedor = resizeArray();
            contenedor[top++] = item;
        }
	}

	public int darTamano() {
		return (top + 1);
	}
}
