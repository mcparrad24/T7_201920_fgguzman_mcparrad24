package model.data_structures;
import java.lang.reflect.Array;

public class Stack<T> implements IStack<T> {

	private T[] contenedor;
	private int top;
	private int maxT = 10;

	public Stack() {
        this.contenedor = (T[]) new Object[maxT];
        this.top = -1;
	}

	private T[] resizeArray() {
        /**
         * create a new array double the size of the old, copy the old elements then return the new array */
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

	public T pop() {
		if (top == -1)
			return null;
		return contenedor[top--];
	}

	public void push(T itm) {
		contenedor[++top] = itm;
	}

	public int darTamano() {
		return (top + 1);
	}
}
