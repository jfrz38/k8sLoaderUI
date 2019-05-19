package tfg.k8s.gui;

public class HpaK8S {

	private String name;
	private String maxReplica;
	private String minReplica;
	private String currReplicas;
	
	
	public HpaK8S(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
