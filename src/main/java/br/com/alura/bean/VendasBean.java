package br.com.alura.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import br.com.alura.dao.LivroDao;
import br.com.alura.modelo.Livro;
import br.com.alura.modelo.Venda;

@Named
@ViewScoped
public class VendasBean implements Serializable {
	private static final long serialVersionUID = 1L;

	@Inject
	private LivroDao livroDao;
	
	public BarChartModel getVendasModel() {
		BarChartModel model = new BarChartModel();
		model.setAnimate(true);
		 
        ChartSeries vendaSerie = new ChartSeries();
        vendaSerie.setLabel("Vendas 2020");
        
        List<Venda> vendas = getVendas(12345);
        for (Venda venda : vendas) {
        	vendaSerie.set(venda.getLivro().getTitulo(), venda.getQuantidade());
		}
        
        ChartSeries vendaSerie2019 = new ChartSeries();
        vendaSerie2019.setLabel("Vendas 2019");
        
        vendas = getVendas(54321);
        for (Venda venda : vendas) {
        	vendaSerie2019.set(venda.getLivro().getTitulo(), venda.getQuantidade());
		}
        
        model.addSeries(vendaSerie);
        model.addSeries(vendaSerie2019);
 
        return model;		
	}
	
	public List<Venda> getVendas(long seed) {
		List<Livro> livros = livroDao.listaTodos();
		List<Venda> vendas = new ArrayList<>();

		Random random = new Random(seed);

		for (Livro livro : livros) {
			Integer quantidade = random.nextInt(500);
			
			vendas.add(new Venda(livro, quantidade));
		}
		
		return vendas;
	}
}
