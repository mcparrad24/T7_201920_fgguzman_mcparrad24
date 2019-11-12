package model.logic;

public class Info {
	private double lng;
	private double lat;
	private int MOVEMENT_ID;
	
	public Info(String lng, String lat, String mOVEMENT_ID) {
		this.lng = Double.parseDouble(lng);
		this.lat = Double.parseDouble(lat);
		this.MOVEMENT_ID = Integer.parseInt(mOVEMENT_ID);
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
		return "Vertice [longitud=" + lng + ", latitud=" + lat + ", MOVEMENT_ID=" + MOVEMENT_ID + "]";
	}
}
