package model.logic;

public class Vertice implements Comparable<Vertice> {
	private int id;
	private Info info;
	private Queue<Vertice> adj = new Queue<Vertice>(); 
	private Queue<Arco> arcos = new Queue<Arco>();
	
	public Vertice(String id, Info infor) {
		this.id = Integer.parseInt(id);
		this.info = infor;
	}
	
	public int getId() {
		return id;
	}
	
	public Info getInfo() {
		return info;
	}
	
	public Queue<Vertice> getAdj(){
		return adj;
	}
	
	public Queue<Arco> getArcos(){
		return arcos;
	}
	
	public void setInfo(Info nInfo) {
		this.info = nInfo;
	}
	
	public String toString() {
		return "Vertice [id=" + id + ", longitud=" + info.getLng() + ", latitud=" + info.getLat()+ ", MOVEMENT_ID=" + info.getMOVEMENT_ID() + "]";
	}

	@Override
	public int compareTo(Vertice o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
