package model.logic;

public class ZonaJSON {
	private int MOVEMENT_ID;
	private String scanombre;
	private double shape_leng;
	private double shape_area;
	private String valor;

	public ZonaJSON(String id, String nombre, String perimetro, String area) {
		this.MOVEMENT_ID = Integer.parseInt(id);
		this.scanombre = nombre;
		this.shape_leng = Double.parseDouble(perimetro);
		this.shape_area = Double.parseDouble(area);
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

	public double getArea() {
		return shape_area;
	}
	
	public int numPtosGeo() { //Modificar
		return 0;
	}
	
	public String getValor() {
		valor = scanombre + "," + shape_leng + "," + shape_area + "," + numPtosGeo();
		return valor;
	}

	public String toString() {
		return "ZonasJSON [id=" + MOVEMENT_ID + ", nombre=" + scanombre + ", perimetro=" + shape_leng + ", area=" + shape_area + "]";
	}

}
