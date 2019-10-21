package model.data_structures;

import java.util.Iterator;

import model.logic.Queue;

public class RedBlackBST <Key extends Comparable<Key>, Value> implements IRedBlackBST<Key, Value> {
	//Código tomado de: https://github.com/kevin-wayne/algs4/blob/master/src/main/java/edu/princeton/cs/algs4/RedBlackBST.java
	//Copyright 2002-2018, Robert Sedgewick and Kevin Wayne.

	private static final boolean RED   = true;
	private static final boolean BLACK = false;

	private Node root;

	private class Node {
		private Key key;
		private Value val;
		private Node left, right; 
		private boolean color;  
		private int size;  

		public Node(Key key, Value val, boolean color, int size) {
			this.key = key;
			this.val = val;
			this.color = color;
			this.size = size;
		}
	}

	/**
	 * Initializes an empty symbol table.
	 */
	public RedBlackBST() {
	}

	/***************************************************************************
	 *  Node helper methods.
	 ***************************************************************************/
	// is node x red; false if x is null ?
	private boolean isRed(Node x) {
		if (x == null) return false;
		return x.color == RED;
	}

	// number of node in subtree rooted at x; 0 if x is null
	private int size(Node x) {
		if (x == null) return 0;
		return x.size;
	} 


	/**
	 * Returns the number of key-value pairs in this symbol table.
	 * @return the number of key-value pairs in this symbol table
	 */
	public int darTamano() {
		return size(root);
	}

	/**
	 * Is this symbol table empty?
	 * @return {@code true} if this symbol table is empty and {@code false} otherwise
	 */
	public boolean isEmpty() {
		return root == null;
	}


	/***************************************************************************
	 *  Standard BST search.
	 ***************************************************************************/

	/**
	 * Returns the value associated with the given key.
	 * @param key the key
	 * @return the value associated with the given key if the key is in the symbol table
	 *     and {@code null} if the key is not in the symbol table
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public Value get(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to get() is null");
		return get(root, key);
	}

	// value associated with the given key in subtree rooted at x; null if no such key
	private Value get(Node x, Key key) {
		while (x != null) {
			int cmp = key.compareTo(x.key);
			if      (cmp < 0) x = x.left;
			else if (cmp > 0) x = x.right;
			else              return x.val;
		}
		return null;
	}

	/**
	 * Does this symbol table contain the given key?
	 * @param key the key
	 * @return {@code true} if this symbol table contains {@code key} and
	 *     {@code false} otherwise
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public boolean contains(Key key) {
		return get(key) != null;
	}

	/***************************************************************************
	 *  Red-black tree insertion.
	 ***************************************************************************/

	/**
	 * Inserts the specified key-value pair into the symbol table, overwriting the old 
	 * value with the new value if the symbol table already contains the specified key.
	 * Deletes the specified key (and its associated value) from this symbol table
	 * if the specified value is {@code null}.
	 *
	 * @param key the key
	 * @param val the value
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public void insertar(Key key, Value val) {
		if (key == null) throw new IllegalArgumentException("first argument to put() is null");
		if (val == null) {
			return;
		}

		root = put(root, key, val);
		root.color = BLACK;
		// assert check();
	}

	// insert the key-value pair in the subtree rooted at h
	private Node put(Node h, Key key, Value val) { 
		if (h == null) return new Node(key, val, RED, 1);

		int cmp = key.compareTo(h.key);
		if      (cmp < 0) h.left  = put(h.left,  key, val); 
		else if (cmp > 0) h.right = put(h.right, key, val); 
		else              h.val   = val;

		// fix-up any right-leaning links
		if (isRed(h.right) && !isRed(h.left))      h = rotateLeft(h);
		if (isRed(h.left)  &&  isRed(h.left.left)) h = rotateRight(h);
		if (isRed(h.left)  &&  isRed(h.right))     flipColors(h);
		h.size = size(h.left) + size(h.right) + 1;

		return h;
	}

	/***************************************************************************
	 *  Red-black tree helper functions.
	 ***************************************************************************/

	// make a left-leaning link lean to the right
	private Node rotateRight(Node h) {
		// assert (h != null) && isRed(h.left);
		Node x = h.left;
		h.left = x.right;
		x.right = h;
		x.color = x.right.color;
		x.right.color = RED;
		x.size = h.size;
		h.size = size(h.left) + size(h.right) + 1;
		return x;
	}

	// make a right-leaning link lean to the left
	private Node rotateLeft(Node h) {
		// assert (h != null) && isRed(h.right);
		Node x = h.right;
		h.right = x.left;
		x.left = h;
		x.color = x.left.color;
		x.left.color = RED;
		x.size = h.size;
		h.size = size(h.left) + size(h.right) + 1;
		return x;
	}

	// flip the colors of a node and its two children
	private void flipColors(Node h) {
		// h must have opposite color of its two children
		// assert (h != null) && (h.left != null) && (h.right != null);
		// assert (!isRed(h) &&  isRed(h.left) &&  isRed(h.right))
		//    || (isRed(h)  && !isRed(h.left) && !isRed(h.right));
		h.color = !h.color;
		h.left.color = !h.left.color;
		h.right.color = !h.right.color;
	}

	// Assuming that h is red and both h.left and h.left.left
	// are black, make h.left or one of its children red.
	private Node moveRedLeft(Node h) {
		// assert (h != null);
		// assert isRed(h) && !isRed(h.left) && !isRed(h.left.left);

		flipColors(h);
		if (isRed(h.right.left)) { 
			h.right = rotateRight(h.right);
			h = rotateLeft(h);
			flipColors(h);
		}
		return h;
	}

	// Assuming that h is red and both h.right and h.right.left
	// are black, make h.right or one of its children red.
	private Node moveRedRight(Node h) {
		// assert (h != null);
		// assert isRed(h) && !isRed(h.right) && !isRed(h.right.left);
		flipColors(h);
		if (isRed(h.left.left)) { 
			h = rotateRight(h);
			flipColors(h);
		}
		return h;
	}

	// restore red-black tree invariant
	private Node balance(Node h) {
		// assert (h != null);

		if (isRed(h.right))                      h = rotateLeft(h);
		if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
		if (isRed(h.left) && isRed(h.right))     flipColors(h);

		h.size = size(h.left) + size(h.right) + 1;
		return h;
	}


	/***************************************************************************
	 *  Utility functions.
	 ***************************************************************************/

	/**
	 * Returns the height of the BST (for debugging).
	 * @return the height of the BST (a 1-node tree has height 0)
	 */
	public int altura() {
		return height(root)-1;
	}
	
	private int height(Node x) {
		if (x == null) return -1;
		return 1 + Math.max(height(x.left), height(x.right));
	}

	public int alturaLlave(Key llave) { //ARREGLAR
		return 0;
	}

	/***************************************************************************
	 *  Ordered symbol table methods.
	 ***************************************************************************/

	/**
	 * Returns the smallest key in the symbol table.
	 * @return the smallest key in the symbol table
	 * @throws NoSuchElementException if the symbol table is empty
	 */
	public Key min() {
		if (isEmpty()) return null;
		return min(root).key;
	} 

	// the smallest key in subtree rooted at x; null if no such key
	private Node min(Node x) { 
		// assert x != null;
		if (x.left == null) return x; 
		else                return min(x.left); 
	} 

	/**
	 * Returns the largest key in the symbol table.
	 * @return the largest key in the symbol table
	 * @throws NoSuchElementException if the symbol table is empty
	 */
	public Key max() {
		if (isEmpty()) return null;
		return max(root).key;
	} 

	// the largest key in the subtree rooted at x; null if no such key
	private Node max(Node x) { 
		// assert x != null;
		if (x.right == null) return x; 
		else                 return max(x.right); 
	} 


	/**
	 * Returns the largest key in the symbol table less than or equal to {@code key}.
	 * @param key the key
	 * @return the largest key in the symbol table less than or equal to {@code key}
	 * @throws NoSuchElementException if there is no such key
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public Key floor(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to floor() is null");
		Node x = floor(root, key);
		if (x == null) return null;
		else           return x.key;
	}    

	// the largest key in the subtree rooted at x less than or equal to the given key
	private Node floor(Node x, Key key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0) return x;
		if (cmp < 0)  return floor(x.left, key);
		Node t = floor(x.right, key);
		if (t != null) return t; 
		else           return x;
	}

	/**
	 * Returns the smallest key in the symbol table greater than or equal to {@code key}.
	 * @param key the key
	 * @return the smallest key in the symbol table greater than or equal to {@code key}
	 * @throws NoSuchElementException if there is no such key
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public Key ceiling(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
		Node x = ceiling(root, key);
		if (x == null) return null;
		else           return x.key;  
	}

	// the smallest key in the subtree rooted at x greater than or equal to the given key
	private Node ceiling(Node x, Key key) {  
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if (cmp == 0) return x;
		if (cmp > 0)  return ceiling(x.right, key);
		Node t = ceiling(x.left, key);
		if (t != null) return t; 
		else           return x;
	}

	/**
	 * Return the key in the symbol table whose rank is {@code k}.
	 * This is the (k+1)st smallest key in the symbol table. 
	 *
	 * @param  k the order statistic
	 * @return the key in the symbol table of rank {@code k}
	 * @throws IllegalArgumentException unless {@code k} is between 0 and
	 *        <em>n</em>–1
	 */
	public Key select(int k) {
		if (k < 0 || k >= darTamano()) {
			throw new IllegalArgumentException("argument to select() is invalid: " + k);
		}
		Node x = select(root, k);
		return x.key;
	}

	// the key of rank k in the subtree rooted at x
	private Node select(Node x, int k) {
		// assert x != null;
		// assert k >= 0 && k < size(x);
		int t = size(x.left); 
		if      (t > k) return select(x.left,  k); 
		else if (t < k) return select(x.right, k-t-1); 
		else            return x; 
	} 

	/**
	 * Return the number of keys in the symbol table strictly less than {@code key}.
	 * @param key the key
	 * @return the number of keys in the symbol table strictly less than {@code key}
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public int rank(Key key) {
		if (key == null) throw new IllegalArgumentException("argument to rank() is null");
		return rank(key, root);
	} 

	// number of keys less than key in the subtree rooted at x
	private int rank(Key key, Node x) {
		if (x == null) return 0; 
		int cmp = key.compareTo(x.key); 
		if      (cmp < 0) return rank(key, x.left); 
		else if (cmp > 0) return 1 + size(x.left) + rank(key, x.right); 
		else              return size(x.left); 
	} 

	/***************************************************************************
	 *  Range count and range search.
	 ***************************************************************************/

	/**
	 * Returns all keys in the symbol table as an {@code Iterable}.
	 * To iterate over all of the keys in the symbol table named {@code st},
	 * use the foreach notation: {@code for (Key key : st.keys())}.
	 * @return all keys in the symbol table as an {@code Iterable}
	 */
	public Iterator<Key> keys() {
		if (isEmpty()) return new Queue<Key>().iterator();
		return keysRango(min(), max());
	}

	/**
	 * Returns all keys in the symbol table in the given range,
	 * as an {@code Iterable}.
	 *
	 * @param  lo minimum endpoint
	 * @param  hi maximum endpoint
	 * @return all keys in the sybol table between {@code lo} 
	 *    (inclusive) and {@code hi} (inclusive) as an {@code Iterable}
	 * @throws IllegalArgumentException if either {@code lo} or {@code hi}
	 *    is {@code null}
	 */
	public Iterator<Key> keysRango(Key lo, Key hi) {
		Queue<Key> queue = new Queue<Key>();
		keys(root, queue, lo, hi);
		return queue.iterator();
	} 

	// add the keys between lo and hi in the subtree rooted at x
	// to the queue
	private void keys(Node x, Queue<Key> queue, Key lo, Key hi) { 
		if (x == null) return; 
		int cmplo = lo.compareTo(x.key); 
		int cmphi = hi.compareTo(x.key); 
		if (cmplo < 0) keys(x.left, queue, lo, hi); 
		if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key); 
		if (cmphi > 0) keys(x.right, queue, lo, hi); 
	} 

	public Iterator<Value> valuesRango(Key lo, Key hi) {
		if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
		if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");

		Queue<Value> queue = new Queue<Value>();
		// if (isEmpty() || lo.compareTo(hi) > 0) return queue;
		values(root, queue, lo, hi);
		return queue.iterator();
	} 

	// add the keys between lo and hi in the subtree rooted at x
	// to the queue
	private void values(Node x, Queue<Value> queue, Key lo, Key hi) { 
		if (x == null) return; 
		int cmplo = lo.compareTo(x.key); 
		int cmphi = hi.compareTo(x.key); 
		if (cmplo < 0) values(x.left, queue, lo, hi); 
		if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.val); 
		if (cmphi > 0) values(x.right, queue, lo, hi); 
	} 

	/***************************************************************************
	 *  Check integrity of red-black tree data structure.
	 ***************************************************************************/
	public boolean check() {
		if (!isBST())            System.out.println("Not in symmetric order");
		if (!isSizeConsistent()) System.out.println("Subtree counts not consistent");
		if (!isRankConsistent()) System.out.println("Ranks not consistent");
		if (!is23())             System.out.println("Not a 2-3 tree");
		if (!isBalanced())       System.out.println("Not balanced");
		if (!nodoMayorSubArbolIzq(root)) System.out.println("Los nodos del subarbol izquierdo no son menores");
		if (!nodoMenorSubArbolDer(root)) System.out.println("Los nodos del subarbol derecho no son mayores");
		return isBST() && isSizeConsistent() && isRankConsistent() && is23() && isBalanced() && nodoMayorSubArbolIzq(root) && nodoMenorSubArbolDer(root);
	}

	// does this binary tree satisfy symmetric order?
	// Note: this test also ensures that data structure is a binary tree since order is strict
	private boolean isBST() {
		return isBST(root, null, null);
	}

	// is the tree rooted at x a BST with all keys strictly between min and max
	// (if min or max is null, treat as empty constraint)
	// Credit: Bob Dondero's elegant solution
	private boolean isBST(Node x, Key min, Key max) {
		if (x == null) return true;
		if (min != null && x.key.compareTo(min) <= 0) return false;
		if (max != null && x.key.compareTo(max) >= 0) return false;
		return isBST(x.left, min, x.key) && isBST(x.right, x.key, max);
	} 

	// are the size fields correct?
	private boolean isSizeConsistent() { return isSizeConsistent(root); }
	private boolean isSizeConsistent(Node x) {
		if (x == null) return true;
		if (x.size != size(x.left) + size(x.right) + 1) return false;
		return isSizeConsistent(x.left) && isSizeConsistent(x.right);
	} 

	// check that ranks are consistent
	private boolean isRankConsistent() {
		for (int i = 0; i < darTamano(); i++)
			if (i != rank(select(i))) return false;
		Key key = keys().next();
		while (keys().hasNext()) {
			if (key.compareTo(select(rank(key))) != 0) return false;
			key = keys().next();
		}
		return true;
	}

	// Does the tree have no red right links, and at most one (left)
	// red links in a row on any path?
	private boolean is23() { return is23(root); }
	private boolean is23(Node x) {
		if (x == null) return true;
		if (isRed(x.right)) return false;
		if (x != root && isRed(x) && isRed(x.left))
			return false;
		return is23(x.left) && is23(x.right);
	} 

	// do all paths from root to leaf have same number of black edges?
	private boolean isBalanced() { 
		int black = 0;     // number of black links on path from root to min
		Node x = root;
		while (x != null) {
			if (!isRed(x)) black++;
			x = x.left;
		}
		return isBalanced(root, black);
	}

	// does every path from the root to a leaf have the given number of black links?
	private boolean isBalanced(Node x, int black) {
		if (x == null) return black == 0;
		if (!isRed(x)) black--;
		return isBalanced(x.left, black) && isBalanced(x.right, black);
	} 
	
	private boolean nodoMayorSubArbolIzq(Node nodo) {
		if (nodo.left.key.compareTo(nodo.key) > 0) {
			return false;
		}
		else {
			return nodoMayorSubArbolIzq(nodo.left);
		}
	}
	
	private boolean nodoMenorSubArbolDer(Node nodo) {
		if (nodo.left.key.compareTo(nodo.key) < 0) {
			return false;
		}
		else {
			return nodoMenorSubArbolDer(nodo.left);
		}
	}
}