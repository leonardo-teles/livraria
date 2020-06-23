package br.com.alura.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class TemaBean {

	private String tema = "redmond";
	
	public static final String[] TODOS_TEMAS = { 
			"afterdark", "afternoon", "afterwork", "aristo", "black-tie", 
			"blitzer", "bluesky", "bootstrap", "casablanca", "cruze", 
			"cupertino", "dark-hive", "dot-luv", "eggplant", "excite-bike", 
			"flick", "glass-x", "home", "hot-sneaks", "humanity", 
			"le-frog", "midnight", "mint-choc", "overcast", "pepper-grinder", 
			"redmond", "rocket", "sam", "smoothness", "south-street", 
			"start", "sunny", "swanky-purse", "trontastic", "ui-darkness", 
			"ui-lightness", "vader" };
	
	public String[] getTemas() {
		return(TODOS_TEMAS);
	}	
	
	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}
}
