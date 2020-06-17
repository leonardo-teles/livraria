package br.com.alura.bean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.alura.dao.UsuarioDao;
import br.com.alura.modelo.Usuario;

@ManagedBean
@ViewScoped
public class LoginBean {

	private Usuario usuario = new Usuario();
	
	public String efetuarLogin() {
		System.out.println("efetuando login do usuário " + this.usuario.getEmail());
		
		boolean existe = new UsuarioDao().existe(this.usuario);
		if (existe) {
			return "livro?faces-redirect=true";
		}
		
		return null;
	}

	public Usuario getUsuario() {
		return usuario;
	}
}
