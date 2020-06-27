package br.com.alura.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.alura.dao.AutorDao;
import br.com.alura.modelo.Autor;
import br.com.alura.tx.Transacional;

@Named
@ViewScoped
public class AutorBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Autor autor = new Autor();
	
	private Integer autorId;

	@Inject
	private AutorDao dao; //CDI faz new AutorDao() e injeta
	
	public List<Autor> getAutores() {
		return this.dao.listaTodos();
	}
	
	public void carregarAutorPeloId() {
		this.autor = this.dao.buscaPorId(autorId);
	}
	
	@Transacional
	public String gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());
		
		if (autor.getNome().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("O nome do autor é obrigatório"));
		}
		
		if (this.autor.getId() == null) {
			this.dao.adiciona(this.autor);
		} else {
			this.dao.atualiza(this.autor);
		}
		
		this.autor = new Autor();
		
		return "livro?faces-redirect=true";
	}
	
	@Transacional
	public void remover(Autor autor) {
		System.out.println("Removendo autor: " + autor.getNome());
		this.dao.remove(autor);
	}
	
	public Autor getAutor() {
		return autor;
	}

	public void setAutor(Autor autor) {
		this.autor = autor;
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}
}
