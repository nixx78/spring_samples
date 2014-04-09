package lv.nixx.poc.spring;

public class MainBean {
	
	private String name;
	
	public MainBean(){
		this.name = "default"; 
	}
	
	public MainBean(String name){
		this.name = name;
	}
	
	@Override
	public String toString(){
		return "name [" + name + "]";
	}

}
