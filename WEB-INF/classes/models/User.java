package models;

public class User implements java.io.Serializable{
	
	private String name;
	private String pswd;
	private String type = null;
	
	public User(){
		
	}
		
	public User(String name, String pswd) {
		
		this.name = name;
		this.pswd = pswd;
	}
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPswd() {
		return pswd;
	}
	public void setPswd(String pswd) {
		this.pswd = pswd;
	}
	
}
