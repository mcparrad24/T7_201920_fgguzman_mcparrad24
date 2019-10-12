package model.logic;

public class MallaVial {
	private int nodo;
	private double longitud;
	private double latitud;

	public MallaVial(String nodo, String longitud, String latitud) {
		this.nodo = Integer.parseInt(nodo);
		this.longitud = Double.parseDouble(longitud);
		this.latitud = Double.parseDouble(latitud);
	}

	public int getNodo() {
		return nodo;
	}

	public double getLongitud() {
		return longitud;
	}

	public double getLatitud() {
		return latitud;
	}

	public String toString() {
		return "mallaVial [nodo=" + nodo + ", longitud=" + longitud + ", latitud=" + latitud + "]";
	}

}
