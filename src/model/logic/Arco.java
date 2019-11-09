package model.logic;

import java.util.Arrays;

public class Arco {
	private int idInicio;
	private int[] idFinal;
	public Arco(String idInicio, String[] idFinal) {
		this.idInicio = Integer.parseInt(idInicio);
		for(int i = 0; i<idFinal.length;i++) {
			this.idFinal[i] = Integer.parseInt(idFinal[i]);
		}
	}
	public int getIdInicio() {
		return idInicio;
	}
	public int[] getIdFinal() {
		return idFinal;
	}
	@Override
	public String toString() {
		return "Arco [idInicio=" + idInicio + ", idFinal=" + Arrays.toString(idFinal) + "]";
	}
	
}
