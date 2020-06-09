package br.com.alura.bean;

import javax.faces.bean.ManagedBean;

import br.com.alura.dao.DAO;
import br.com.alura.modelo.Autor;

@ManagedBean
public class AutorBean {

	private Autor autor = new Autor();
	
	public Autor getAutor() {
		return autor;
	}
	
	public String gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());
		
		new DAO<Autor>(Autor.class).adiciona(this.autor);
		
		this.autor = new Autor();
		
		return "livro?faces-redirect=true";
	}
}
