package tfg.k8s.gui;

public class DeploymentK8S {

	private String name;
	private String desired;
	private String current;
	private String uptodate;
	private String available;
	private String age;
	
	// Posiciones:
			// 0 = nombre
			// 1 = desired
			// 2 = current
			// 3 = up-to-date
			// 4 = available
			// 5 = age
	public DeploymentK8S(String name, String desired, String current, String uptodate, String available, String age) {
		this.setName(name);
		this.setDesired(desired);
		this.setCurrent(current);
		this.setUptodate(uptodate);
		this.setAvailable(available);
		this.setAge(age);
	}
	public DeploymentK8S(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public String getDesired() {
		return desired;
	}
	public void setDesired(String desired) {
		this.desired = desired;
	}
	public String getUptodate() {
		return uptodate;
	}
	public void setUptodate(String uptodate) {
		this.uptodate = uptodate;
	}
	public String getAvailable() {
		return available;
	}
	public void setAvailable(String available) {
		this.available = available;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getCurrent() {
		return current;
	}
	public void setCurrent(String current) {
		this.current = current;
	}
}
