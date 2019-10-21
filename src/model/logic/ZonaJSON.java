package model.logic;

public class ZonaJSON {
	private int MOVEMENT_ID;
	private String scanombre;
	private double shape_leng;
	private String shape_area;
	private int ptosGeo;
	private String valor = scanombre + "," + shape_leng + "," + shape_area + "," + ptosGeo;

	public ZonaJSON(String id, String nombre, String perimetro, String area) {
		this.MOVEMENT_ID = Integer.parseInt(id);
		this.scanombre = nombre;
		this.shape_leng = Double.parseDouble(perimetro);
		this.shape_area = area;
		this.ptosGeo = getPtosGeo();
		this.valor = getValor();
	}

	public int getId() {
		return MOVEMENT_ID;
	}

	public String getNombre() {
		return scanombre;
	}

	public double getPerimetro() {
		return shape_leng;
	}

	public String getArea() {
		return shape_area;
	}

	public int getPtosGeo() {
		return ptosGeo;
	}

	public void setPtosGeo(int ptosGeo) {
		this.ptosGeo = ptosGeo;
		cambiarValor();	
	}

	public String getValor() {
		return valor;
	}
	
	public void cambiarValor() {
		valor = valor = scanombre + "," + shape_leng + "," + shape_area + "," + ptosGeo;
	}


	public String toString() {
		return "ZonaJSON [MOVEMENT_ID=" + MOVEMENT_ID + ", scanombre=" + scanombre + ", shape_leng=" + shape_leng
				+ ", shape_area=" + shape_area + ", ptosGeo=" + ptosGeo +"]";
	}

}
