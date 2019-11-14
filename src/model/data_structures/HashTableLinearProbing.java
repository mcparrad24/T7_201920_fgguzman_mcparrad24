package model.data_structures;

import model.logic.Queue;

public class HashTableLinearProbing<K extends Comparable<K>, V> implements IHashTable<K, V> {

	private K[] tablaLlave;
	private K llaveH;
	private V[] tablaDato;
	private int tamano;
	private int tamanoMax;
	private int rehashes = 0;
	private final double loadFactor = 0.75;

	public HashTableLinearProbing(int N) {
		this.crear(N);
	}

	public void crear(int tamanoMax) {
		this.tamanoMax = tamanoMax;
		this.tamano = 0;
		tablaLlave = (K[]) new Object[tamanoMax + 1];
		tablaDato = (V[]) new Object[tamanoMax + 1];
	}

	private void resize(int capacity) {
		K[] temp = (K[]) new Object[capacity + 1];
		V[] temp2 = (V[]) new Object[capacity + 1];
		for (int i = 1; i <= tamano; i++) {
			temp[i] = tablaLlave[i];
			temp2[i] = tablaDato[i];
		}
		tablaLlave = temp;
		tablaDato = temp2;
		tamanoMax = capacity;
	}

	public void put(K llave, V dato) {
		if (tamano == tablaLlave.length - 1)
			resize(2 * tablaLlave.length);
		this.llaveH = llave;
		int hash = hashCode();
		int pos = Math.abs(hash % tamanoMax);
		int i = pos;
		if (tablaLlave[pos] != null) {
			for (; i < tablaLlave.length; i++) {
				if (tablaLlave[i] == null)
					break;
				if (i == tablaLlave.length - 1)
					i = -1;
			}
		}
		if (llave != null) {
			tablaLlave[i] = llave;
			tablaDato[i] = dato;
			tamano++;
		}
		double carga = (1.0 * tamano) / tamanoMax;
		if (carga > this.loadFactor)
			rehash();
	}

	private void rehash() {
		K[] temp = tablaLlave;
		V[] temp2 = tablaDato;
		rehashes++;

		this.crear(numRehash());
		for (int i = 0; i < temp.length; i++) {
			this.put(temp[i], temp2[i]);
		}
	}

	public int darTamano() {
		return tamano;
	}
	
	public int darTamanoMax() {
		return tamanoMax;
	}
	
	public double cargaF() {
		double carga = (1.0 * tamano) / tamanoMax;
		return carga;
	}
	
	public int darRehashes() {
		return rehashes;
	}

	public boolean isEmpty() {
		if (tamano == 0) {
			return true;
		} else {
			return false;
		}
	}

	public V get(K llave) {
		this.llaveH = llave;
		int hash = hashCode();
		int pos = Math.abs(hash % tamanoMax);
		int i = pos;
		if (!tablaLlave[pos].equals(llave)) {
			for (; i < tablaLlave.length; i++) {
				if (i == tablaLlave.length)
					i = 0;
				if (tablaLlave[i].equals(llave))
					break;
			}
		}
		V retorno = tablaDato[i];
		return retorno;
	}

	public V delete(K llave) {
		this.llaveH = llave;
		int hash = hashCode();
		int pos = Math.abs(hash % tamanoMax);
		int i = pos;
		if (!tablaLlave[pos].equals(llave)) {
			for (; i < tablaLlave.length; i++) {
				if (i == tablaLlave.length)
					i = 0;
				if (tablaLlave[i].equals(llave))
					break;
			}
		}
		V retorno = tablaDato[i];
		tablaLlave[i] = null;
		tablaDato[i] = null;
		tamano--;
		return retorno;
	}

	public Iterable<K> keys() {
		Queue<K> queue = new Queue<K>();
		for (int i = 0; i < tamano; i++)
			if (tablaLlave[i] != null)
				queue.enqueue(tablaLlave[i]);
		return queue;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((this.llaveH == null) ? 0 : this.llaveH.hashCode());
		return result;
	}
	
	public boolean esPrimoMayor(int num) {
		boolean es = true;
		if (num > tamanoMax) {
			for (int i = 1; i < num; i++) {
				if ((i != 1) && (i != num) && ((num%i)==0)){
					es = false;
				}
			}
		}
		return es;
	}
	
	public int numRehash() {
		int retorno = 0;
		for (int i = 1; i < tamanoMax; i++) {
			int num = 2*tamanoMax + i;
			if (esPrimoMayor(num)) {
				retorno = num;
				return num;
			}
		}
		return retorno;
	}


	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HashTableLinearProbing other = (HashTableLinearProbing) obj;
		if (this.llaveH == null) {
			if (other.llaveH != null)
				return false;
		} else if (!this.llaveH.equals(other.llaveH))
			return false;
		return true;
	}

}