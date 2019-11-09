package model.logic;

public class Vertice {
	private int id;
	private double lng;
	private double lat;
	private int MOVEMENT_ID;
	public Vertice(String id, String lng, String lat, String mOVEMENT_ID) {
		this.id = Integer.parseInt(id);
		this.lng = Double.parseDouble(lng);
		this.lat = Double.parseDouble(lat);
		MOVEMENT_ID = Integer.parseInt(mOVEMENT_ID);
	}
	
	public int getId() {
		return id;
	}
	public double getLng() {
		return lng;
	}
	public double getLat() {
		return lat;
	}
	public int getMOVEMENT_ID() {
		return MOVEMENT_ID;
	}
	
	public String toString() {
		return "Vertice [id=" + id + ", longitud=" + lng + ", latitud=" + lat + ", MOVEMENT_ID=" + MOVEMENT_ID + "]";
	}
	
}
