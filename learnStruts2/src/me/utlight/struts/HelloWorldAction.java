package me.utlight.struts;

public class HelloWorldAction {
	
	private String name;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String execute() throws Exception{
		
		if (this.getName().equals("") || this.getName() == null) {
			return "error";
		}
		return "success";
	}
}
