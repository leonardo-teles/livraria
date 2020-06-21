package br.com.alura.modelo;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import br.com.alura.dao.DAO;

public class LivroDataModel extends LazyDataModel<Livro> {
	private static final long serialVersionUID = 1L;

	DAO<Livro> dao = new DAO<>(Livro.class);
	
	public LivroDataModel() {
		super.setRowCount(dao.quantidadeDeElemenos());
	}
	
	@Override
	public List<Livro> load(int inicio, int quantidade, String campoOrdenacao, SortOrder sentidoOrdenacao, Map<String, Object> filtros) {
		String tituto = (String) filtros.get("titulo");
		
		return dao.listaTodosPaginada(inicio, quantidade, "titulo", tituto);
	}
}
