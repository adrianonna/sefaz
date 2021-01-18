package br.gov.pe.sefaz.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.gov.pe.sefaz.controller.TelefoneController;
import br.gov.pe.sefaz.controller.UsuarioController;
import br.gov.pe.sefaz.model.Telefone;
import br.gov.pe.sefaz.model.Usuario;

@Named
@ViewScoped
public class CadastroBean extends GenericoSefazBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private Usuario usuario;
	
	@Inject
	private Telefone telefone;
	
	@Inject
	private UsuarioController uController;
	
	@Inject
	private TelefoneController tController;
	
	@PostConstruct
	public void init() {
		Usuario usuarioFlash = (Usuario) this.getFlash("usuario");
		Telefone telefoneFlash = (Telefone) this.getFlash("telefone");
		if (usuarioFlash != null) {
			this.usuario = usuarioFlash;
		}
		if (telefoneFlash != null) {
			this.telefone = telefoneFlash;
		}
	}
	
	
	public String cadastrar() {		
		Integer idu = usuario.getId();
		Integer idt = telefone.getId();
		
		uController.saveOrUpdate(usuario);
		tController.saveOrUpdate(telefone);
		
		
		System.out.println("Telefones= "+usuario.getTelefones()+" do usuario "+usuario);
		System.out.println("Usuario.getId()= "+usuario.getId());
		System.out.println("Telefones= "+telefone);
		System.out.println("Telefones.getId()= "+telefone.getId());
		
		this.keepMessages();
		if (idu == null && idt == null) {
			this.addInfoMessage("Usuarios cadastrado com sucesso!");			
		} else {
			this.addInfoMessage("Usuarios atualizado com sucesso!");	
		}
		
		
		usuario = new Usuario();
		telefone = new Telefone();

		return "consulta?faces-redirect=true";
	}
	

	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}


	public Telefone getTelefone() {
		return telefone;
	}
	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}
	
}
