package lv.nixx.poc.spring;

public class ProfiledBean {
	
	private String profile;
	
	public ProfiledBean(String profile){
		this.profile = profile;
	}
	
	@Override
	public String toString(){
		return "profile [" + profile + "]";
	}

}
