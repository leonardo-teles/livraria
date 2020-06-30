package br.com.alura.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.alura.dao.UsuarioDao;
import br.com.alura.modelo.Usuario;

@Named
@ViewScoped
public class LoginBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Usuario usuario = new Usuario();
	
	@Inject
	UsuarioDao dao;
	
	@Inject
	FacesContext context;
	
	public String efetuarLogin() {
		boolean existe = dao.existe(this.usuario);
		if (existe) {
			context.getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
			
			return "livro?faces-redirect=true";
		}

		context.getExternalContext().getFlash().setKeepMessages(true);
		context.addMessage(null, new FacesMessage("Usuário não encontrado"));
		
		return "login?faces-redirect=true";
	}
	
	public String deslogar() {
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		
		return "login?faces-redirect=true";
	}

	public Usuario getUsuario() {
		return usuario;
	}
}
