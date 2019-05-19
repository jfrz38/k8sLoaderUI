package tfg.k8s.gui;

public class Peticion {

	private String peticionesTotales;
	private String peticionesConcurrentes;
	private String peticionesPorSegundo;
	private String servicio;
	private String namespace;
	private String deployment;
	private String hpa;
	
	public Peticion(String peticionesTotales, String peticionesConcurrentes, String peticionesPorSegundo, String servicio, String namespace, String deployment, String hpa) {
		this.setPeticionesTotales(peticionesTotales);
		this.setPeticionesConcurrentes(peticionesConcurrentes);
		this.setPeticionesPorSegundo(peticionesPorSegundo);
		this.setServicio(servicio);
		this.setNamespace(namespace);
		this.setDeployment(deployment);
		this.setHpa(hpa);
	}

	public String getPeticionesTotales() {
		return peticionesTotales;
	}

	public void setPeticionesTotales(String peticionesTotales) {
		this.peticionesTotales = peticionesTotales;
	}

	public String getPeticionesPorSegundo() {
		return peticionesPorSegundo;
	}

	public void setPeticionesPorSegundo(String peticionesPorSegundo) {
		this.peticionesPorSegundo = peticionesPorSegundo;
	}

	public String getPeticionesConcurrentes() {
		return peticionesConcurrentes;
	}

	public void setPeticionesConcurrentes(String peticionesConcurrentes) {
		this.peticionesConcurrentes = peticionesConcurrentes;
	}

	public String getServicio() {
		return servicio;
	}

	public void setServicio(String servicio) {
		this.servicio = servicio;
	}

	public String getNamespace() {
		return namespace;
	}

	public void setNamespace(String namespace) {
		this.namespace = namespace;
	}

	public String getDeployment() {
		return deployment;
	}

	public void setDeployment(String deployment) {
		this.deployment = deployment;
	}

	public String getHpa() {
		return hpa;
	}

	public void setHpa(String hpa) {
		this.hpa = hpa;
	}
	
	public String toString() {
		return "Petición : peticionesTotales = "+this.getPeticionesTotales()+" ; peticionesConcurrentes = "+this.getPeticionesConcurrentes()+
				" ; peticionePorSegundo = "+this.getPeticionesPorSegundo()+" ; servicio = "+this.getServicio()+" ; deployment = "+this.getDeployment()+
				" ; hpa = "+this.getHpa();
	}
}
