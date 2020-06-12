package br.com.alura.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import br.com.alura.dao.DAO;
import br.com.alura.modelo.Autor;

@ManagedBean
@ViewScoped
public class AutorBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Autor autor = new Autor();
	
	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}
	
	public String gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());
		
		if (autor.getNome().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("O nome do autor é obrigatório"));
		}
		
		if (this.autor.getId() == null) {
			new DAO<Autor>(Autor.class).adiciona(this.autor);
		} else {
			new DAO<Autor>(Autor.class).atualiza(this.autor);
		}
		
		this.autor = new Autor();
		
		return "livro?faces-redirect=true";
	}
	
	public void remover(Autor autor) {
		System.out.println("Removendo autor: " + autor.getNome());
		new DAO<Autor>(Autor.class).remove(autor);
	}
	
	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}
	
}
