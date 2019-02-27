package interface_pkg;

public class NamespaceK8S {
	
	private String kind;
	private String name;
	private String apiVersion;
	private boolean status;
	private String fecha;
	private String selfLink;
	
	public NamespaceK8S() {
		
	}
	
	public NamespaceK8S(String kind, String name, String apiVersion, boolean status) {
		this.setKind(kind);
		this.setName(name);
		this.setApiVersion(apiVersion);
		this.setStatus(status);
	}

	public String getKind() {
		return kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApiVersion() {
		return apiVersion;
	}

	public void setApiVersion(String apiVersion) {
		this.apiVersion = apiVersion;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
