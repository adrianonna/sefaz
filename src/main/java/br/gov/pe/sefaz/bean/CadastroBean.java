package br.gov.pe.sefaz.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
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
	
	@PostConstruct
	public void init() {
		Usuario usuarioFlash = (Usuario) this.getFlash("usuario");
		if (usuarioFlash != null) {
			this.usuario = usuarioFlash;
		}
	}
	
	
	public String cadastrar() {		
		Integer id = usuario.getId();
		uController.saveOrUpdate(usuario);
		
		this.keepMessages();
		if (id == null) {
			this.addInfoMessage("Usuarios cadastrado com sucesso!");			
		} else {
			this.addInfoMessage("Usuarios atualizado com sucesso!");	
		}
		
		usuario = new Usuario();
		return "consulta?faces-redirect=true";
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
