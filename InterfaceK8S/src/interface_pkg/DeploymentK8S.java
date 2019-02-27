package interface_pkg;

public class DeploymentK8S {

	private String name;
	private String labels;
	private String replicas;
	private String readyReplicas;
	private String updatedReplicas;
	private String availableReplicas;
	private boolean status;
	private String apiVersion;
	private String fecha;
	
	public DeploymentK8S(String name, String labels, String replicas, String readyReplicas,  String updatedReplicas, String availableReplicas, boolean status, String apiVersion, String fecha) {
		this.setName(name);
		this.setLabels(labels);
		this.setReplicas(replicas);
		this.setReadyReplicas(readyReplicas);
		this.setUpdatedReplicas(updatedReplicas);
		this.setAvailableReplicas(availableReplicas);
		this.setStatus(status);
		this.setApiVersion(apiVersion);
		this.setFecha(fecha);
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReadyReplicas() {
		return readyReplicas;
	}

	public void setReadyReplicas(String readyReplicas) {
		this.readyReplicas = readyReplicas;
	}

	public String getReplicas() {
		return replicas;
	}

	public void setReplicas(String replicas) {
		this.replicas = replicas;
	}

	public String getLabels() {
		return labels;
	}

	public void setLabels(String labels) {
		this.labels = labels;
	}

	public String getUpdatedReplicas() {
		return updatedReplicas;
	}

	public void setUpdatedReplicas(String updatedReplicas) {
		this.updatedReplicas = updatedReplicas;
	}

	public String getAvailableReplicas() {
		return availableReplicas;
	}

	public void setAvailableReplicas(String availableReplicas) {
		this.availableReplicas = availableReplicas;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
}
