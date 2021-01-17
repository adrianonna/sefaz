package br.gov.pe.sefaz.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpSession;

import br.gov.pe.sefaz.model.Usuario;

@Named
@SessionScoped
public class LoginBean extends GenericoSefazBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Usuario usuario = new Usuario();

	public String autentique() {
		if (this.usuario.getEmail().equals("adriano@gmail.com") && this.usuario.getSenha().equals("1234")) {
			return "index";
		} else {
			this.addErrorMessage("Login e/ou senha invalido(s)");
			return null;
		}
	}
	
	//Não está funcionando!!!
	public String encerrar() {
		FacesContext fc = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) fc.getExternalContext().getSession(false);
		session.invalidate();
		return "login?faces-redirect=true";		
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
