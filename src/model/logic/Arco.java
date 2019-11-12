package model.logic;

import java.util.Arrays;

public class Arco {
	private int idInicio;
	private int idFinal;
	private double costo;
	
	public Arco(String idInicio, String idFinal, double pCosto) {
		this.idInicio = Integer.parseInt(idInicio);
		this.idFinal = Integer.parseInt(idFinal);
		this.costo = pCosto;
	}
	public int getIdInicio() {
		return idInicio;
	}
	public int getIdFinal() {
		return idFinal;
	}
	
	public double getCosto() {
		return costo;
	}
	
	public void setCostArc(double nCosto) {
		this.costo = nCosto;
	}
	
	@Override
	public String toString() {
		return "Arco [idInicio=" + idInicio + ", idFinal=" +  idFinal + ", costo= " + costo + "]";
	}
	
}
