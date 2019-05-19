package tfg.k8s.gui;

public class NamespaceK8S {

	private String name;
	
	public NamespaceK8S(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
