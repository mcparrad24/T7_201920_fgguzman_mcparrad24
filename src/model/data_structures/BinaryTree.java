package model.data_structures;

import model.logic.Queue;

public class BinaryTree <Key extends Comparable<Key>, Value> implements IBinaryTree<Key, Value> {
	//Código tomado de: https://github.com/kevin-wayne/algs4/blob/master/src/main/java/edu/princeton/cs/algs4/BST.java
	
	private Node root;             // root of BST

    private class Node {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees
        private int tamano;          // number of nodes in subtree

        public Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.tamano = size;
        }
    }
    public BinaryTree() {
    }

	/**
	 * Retornar el numero de elementos presentes en el árbol
	 * @return tamano del árbol
	 */
	public int darTamano( ) {
		return tamano(root);
	}
	
	public int tamano(Node nodo) {
		if (nodo == null) {
			return 0;
		}
		else {
			return nodo.tamano;
		}
	}
	
	/**
	 * Agregar el nuevo elemento.
	 * @param llave y valor del nuevo elemento.
	 */
	public void insertar( Key llave, Value valor ) {
		if (valor == null) {
			eliminar(llave);
		}
		root = insertarN(root, llave, valor);
	}
	
	public Node insertarN(Node nodo, Key llave, Value val) {
		if (nodo == null) {
			return new Node(llave, val, 1);
		}
		int c = llave.compareTo(nodo.key);
		if (c < 0) { 
			nodo.left = insertarN(nodo.left, llave, val);
		}
		else if (c > 0) {
			nodo.right = insertarN(nodo.right, llave, val);
		}
		else {
			nodo.val = val;
		}
		nodo.tamano = 1 + tamano(nodo.left) + tamano(nodo.right);
		return nodo;
	}
	
	/**
	 * Eliminar el elemento dado del árbol.
	 * @param llave del elemento a eliminar.
	 */
	public void eliminar(Key llave) {
		root = eliminarN(root, llave);
	}
	
	public Node eliminarN(Node nodo, Key llave) {
		if (nodo == null) {
			return null;
		}
		int c = llave.compareTo(nodo.key);
		if (c < 0) {
			nodo.left = eliminarN(nodo.left, llave);
		}
		else if (c > 0) {
			nodo.right = eliminarN(nodo.right, llave);
		}
		else {
			if (nodo.right == null) {
				return nodo.left;
			}
			if (nodo.left == null) {
				return nodo.right;
			}
			Node t = nodo;
			nodo = min(t.right);
			nodo.right = deleteMin(t.right);
			nodo.left = t.left;
		}
		nodo.tamano = tamano(nodo.left) + tamano(nodo.right) + 1;
		return nodo;
	}
	
	/**
	 * Devuelve el valor del elemento dado.
	 * @param llave del elemento.
	 * @return valor del elemento 
	 */
	public Value get(Key llave) {
		return get(root, llave);
	}
	
	public Value get(Node nodo, Key llave) {
		if (nodo == null) {
			return null;
		}
		int c = llave.compareTo(nodo.key);
		if (c < 0) {
			return get(nodo.left, llave);
		}
		else if (c > 0) {
			return get(nodo.right, llave);
		}
		else {
			return nodo.val;
		}
	}
	
	/**
	 * Dice si el elemento dado está en el árbol o no.
	 * @param llave del elemento a consultar
	 * @return True si está en el árbol, false si no.
	 */
	public boolean contains(Key llave) {
		return get(llave) != null;
	}
	
	/**
	 * Dice si el árbol esta vacio o no.
	 * @return True si el árbol esta vacio, false si no.
	 */
	public boolean isEmpty() {
		if (darTamano() == 0) {
			return true;
		}
		else {
			return false;
		}
	}
	
	public Iterable<Key> keys(Key min, Key max){
		 Queue<Key> queue = new Queue<Key>();
	     keys(root, queue, min, max);
	     return queue;
	}
	
	private void keys(Node nodo, Queue<Key> queue, Key min, Key max) { 
        if (nodo == null) return; 
        int cmplo = min.compareTo(nodo.key); 
        int cmphi = max.compareTo(nodo.key); 
        if (cmplo < 0) keys(nodo.left, queue, min, max); 
        if (cmplo <= 0 && cmphi >= 0) queue.enqueue(nodo.key); 
        if (cmphi > 0) keys(nodo.right, queue, min, max); 
    } 
	
	public Key min() {
        return min(root).key;
    } 

    private Node min(Node nodo) { 
        if (nodo.left == null) return nodo; 
        else                return min(nodo.left); 
    } 
    
    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node nodo) {
        if (nodo.left == null) return nodo.right;
        nodo.left = deleteMin(nodo.left);
        nodo.tamano = tamano(nodo.left) + tamano(nodo.right) + 1;
        return nodo;
    }
}
