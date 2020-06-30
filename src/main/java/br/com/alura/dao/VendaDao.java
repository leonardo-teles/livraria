package br.com.alura.dao;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.alura.modelo.Venda;

public class VendaDao implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Inject
	EntityManager em;
	
	private DAO<Venda> dao;
	
	@PostConstruct
	void init()  {
		this.dao = new DAO<Venda>(this.em, Venda.class);
	}
	
	public List<Venda> listaTodos() {
		return dao.listaTodos();
	}

}
