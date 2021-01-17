package br.gov.pe.sefaz.bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.gov.pe.sefaz.controller.UsuarioController;
import br.gov.pe.sefaz.model.Usuario;

@Named
@ViewScoped
public class CadastroBean extends GenericoSefazBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Usuario usuario;
	
	@Inject
	private UsuarioController uController;
	
	public String cadastrar() {
		uController.insert(usuario);
		usuario = new Usuario();
		this.addInfoMessage("Usuarios cadastrado com sucesso!");
		return null;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
