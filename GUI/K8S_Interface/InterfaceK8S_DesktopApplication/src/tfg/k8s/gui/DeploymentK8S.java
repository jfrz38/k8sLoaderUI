package tfg.k8s.gui;

public class DeploymentK8S {

	private String name;
	
	public DeploymentK8S(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
