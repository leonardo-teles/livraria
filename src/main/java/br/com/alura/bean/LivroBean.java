package br.com.alura.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

import br.com.alura.dao.DAO;
import br.com.alura.modelo.Autor;
import br.com.alura.modelo.Livro;
import br.com.alura.modelo.LivroDataModel;

@ManagedBean
@ViewScoped
public class LivroBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Livro livro = new Livro();
	private Integer livroId;
	private Integer autorId;

	private List<Livro> livros;
	
	private LivroDataModel livroDataModel = new LivroDataModel();
	
	private List<String> generos = Arrays.asList("Romance", "Drama", "Ação");

	public List<Livro> getLivros() {
		DAO<Livro> dao = new DAO<Livro>(Livro.class);
		if (this.livros == null) {
			this.livros = dao.listaTodos();
		}
		
		return livros;
	}
	
	public List<Autor> getAutores() {
		return new DAO<Autor>(Autor.class).listaTodos();
	}
	
	public List<Autor> getAutoresDoLivro() {
		return this.livro.getAutores();
	}
	
	public void carregarLivroPeloId() {
		this.livro = new DAO<Livro>(Livro.class).buscaPorId(livroId);
	}
	
	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());
		
		if (livro.getAutores().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Livro deve ter pelo menos um autor"));
		}
		
		DAO<Livro> dao = new DAO<Livro>(Livro.class);
		if (this.livro.getId() == null) {
			dao.adiciona(this.livro);
			this.livros = dao.listaTodos();
		} else {
			dao.atualiza(this.livro);
		}
		
		this.livro = new Livro();
	}
	
	public void remover(Livro livro) {
		System.out.println("Removendo livro: " + livro.getTitulo());
		new DAO<Livro>(Livro.class).remove(livro);
	}
	
	public void removerAutorDoLivro(Autor autor) {
		this.livro.removeAutor(autor);
	}
	
	public String formAutor() {
		System.out.println("chamando o formulário do autor");
		return "autor?faces-redirect=true";
	}
	
	public void gravarAutor() {
		Autor autor = new DAO<Autor>(Autor.class).buscaPorId(autorId);
		this.livro.adicionaAutor(autor);
	}
	
	public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
		String valor = value.toString();
		if (!valor.startsWith("1")) {
			throw new ValidatorException(new FacesMessage("ISBD deveria começar com 1"));
		}
	}
	
	public boolean precoEhMenor(Object valorColuna, Object filtroDigitado, Locale locale) {
		
		//tirando espaços do filtro
		String textoDigitado = (filtroDigitado == null) ? null : filtroDigitado.toString().trim();
		
		System.out.println("filtroando pelo " + textoDigitado + ", valor do elemento: " + valorColuna);
		
		// o filtro é nulo ou vazio?
		if (textoDigitado == null || textoDigitado.equals("")) {
			return true;
		}
		
		// elemento da tabela é nulo?
		if (valorColuna == null) {
			return false;
		}
		
		try {
			// fazendo o parsing do filtro para converter para double
			Double precoDigitado = Double.valueOf(textoDigitado);
			Double precoColuna = (Double) valorColuna;
			
			// comparando os valores, compareTo devolve um valor negativo se o value é menor do que o filtro
			return precoColuna.compareTo(precoDigitado) < 0;
		} catch (NumberFormatException e) {
			// usuário não digitou um número
			return false;
		}
	}
	
	public Livro getLivro() {
		return livro;
	}
	
	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public Integer getAutorId() {
		return autorId;
	}

	public void setAutorId(Integer autorId) {
		this.autorId = autorId;
	}

	public Integer getLivroId() {
		return livroId;
	}

	public void setLivroId(Integer livroId) {
		this.livroId = livroId;
	}

	public LivroDataModel getLivroDataModel() {
		return livroDataModel;
	}

	public List<String> getGeneros() {
		return generos;
	}
}
