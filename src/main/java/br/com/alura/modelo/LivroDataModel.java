package br.com.alura.modelo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.alura.dao.LivroDao;

@Named
@ViewScoped
public class LivroDataModel extends LazyDataModel<Livro> implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	private LivroDao livroDao;

	@PostConstruct
	void init() {
		super.setRowCount(livroDao.quantidadeDeElemenos());
	}
	
	@Override
	public List<Livro> load(int inicio, int quantidade, String campoOrdenacao, SortOrder sentidoOrdenacao, Map<String, Object> filtros) {
		String tituto = (String) filtros.get("titulo");
		
		return livroDao.listaTodosPaginada(inicio, quantidade, "titulo", tituto);
	}
}
