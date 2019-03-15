package tfg.k8s.gui;

public class HpaK8S {

	private String name;
	private String maxReplica;
	private String minReplica;
	private String currReplicas;
	private String reference;
	private String targets;
	private String age;
	
	
	public HpaK8S(String name) {
		this.setName(name);
	}
	
	public HpaK8S(String name, String reference, String targets, String minpods, String maxpods, String replicas, String age) {
		this.setName(name);
		this.setReference(reference);
		this.setTargets(targets);
		this.setMinReplica(minpods);
		this.setMaxReplica(maxpods);
		this.setCurrReplicas(replicas);
		this.setAge(age);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getMaxReplica() {
		return maxReplica;
	}
	public void setMaxReplica(String maxReplica) {
		this.maxReplica = maxReplica;
	}
	public String getCurrReplicas() {
		return currReplicas;
	}
	public void setCurrReplicas(String currReplicas) {
		this.currReplicas = currReplicas;
	}
	public String getMinReplica() {
		return minReplica;
	}
	public void setMinReplica(String minReplica) {
		this.minReplica = minReplica;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getTargets() {
		return targets;
	}
	public void setTargets(String targets) {
		this.targets = targets;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
}
