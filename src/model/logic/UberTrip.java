package model.logic;

public class UberTrip implements Comparable<UberTrip> {

	private int idOrigen;
	private int idDestino;
	private int HMD;
	private double tiempo;
	private double desviacion;
	private int trimestre;
	private String llave;
	private String valor;
	
	public UberTrip(String idOrigen, String idDestino, String HMD, String tiempo, String desviacion,
			int trimestre) {
		this.idOrigen = Integer.parseInt(idOrigen);
		this.idDestino = Integer.parseInt(idDestino);
		this.HMD = Integer.parseInt(HMD);
		this.tiempo = Double.parseDouble(tiempo);
		this.desviacion = Double.parseDouble(desviacion);
		this.trimestre = trimestre;
		this.llave = darLlave();
		this.valor = darValor();
	}

	public int compareTo(UberTrip viaje) {
		int menor = 1;
		if(this.tiempo < viaje.tiempo) {
			menor = -1;
		}
		else if (this.tiempo == viaje.tiempo) {
			menor = 0;
		}
		return menor;
	}
	
	public int darIdOrigen() {
		return idOrigen;
	}
	
	public int darIdDestino() {
		return idDestino;
	}
	
	public int darHMD() {
		return HMD;
	}
	
	public double darTiempoPromedio() {
		return tiempo;
	}
	
	public double darDesviacion() {
		return desviacion;
	}
	
	public int darTrimestre() {
		return trimestre;
	}
	
	public String darLlave() {
		llave = this.trimestre+"-"+this.idOrigen+"-"+this.idDestino;
		return llave;
	}
	
	public String darValor() {
		valor = this.trimestre+","+this.idOrigen+","+this.idDestino+","+this.HMD+","+this.tiempo;
		return valor;
	}
	
	public String toString() {
		return "["+this.idOrigen+","+this.idDestino+","+this.HMD+","+this.tiempo+","+this.desviacion+"]";
	}
	
}
