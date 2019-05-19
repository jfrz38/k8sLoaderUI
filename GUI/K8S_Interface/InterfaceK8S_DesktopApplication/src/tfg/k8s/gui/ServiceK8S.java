package tfg.k8s.gui;

public class ServiceK8S {

	private String name;
	private String externalIP;
	private String internalIP;
	private String port;
	
	public ServiceK8S(String name) {
		this.setName(name);
		//this.setExternalIP(externalIP);
		//this.setInternalIP(internalIP);
		//this.setPort(port);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getExternalIP() {
		return externalIP;
	}

	public void setExternalIP(String externalIP) {
		this.externalIP = externalIP;
	}

	public String getInternalIP() {
		return internalIP;
	}

	public void setInternalIP(String internalIP) {
		this.internalIP = internalIP;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}
}
