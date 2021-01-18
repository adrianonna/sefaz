package br.gov.pe.sefaz.bean;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.gov.pe.sefaz.controller.TelefoneController;
import br.gov.pe.sefaz.controller.UsuarioController;
import br.gov.pe.sefaz.model.Telefone;
import br.gov.pe.sefaz.model.Usuario;

@Named
@ViewScoped
public class ConsultaBean extends GenericoSefazBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private List<Telefone> telefones;
	private Telefone telefone;
	
	private List<Usuario> usuarios;
	private Usuario usuario;
	private Integer id;
	
	private Map<Integer, Boolean> checked = new HashMap<Integer, Boolean>();
	
	@Inject
	private UsuarioController uController;
	
	@Inject
	private TelefoneController tController;
	

	public void init() {
		if (id == null) {
			usuarios = uController.findAll();
			telefones = tController.findAll();
		} else {
			usuarios = Collections.singletonList(uController.find(id));
		}
	}
	
	public String excluir(Usuario usuario) {
		uController.excluir(usuario);
		this.addInfoMessage("Usuario excluido com sucesso!");
		this.init();
		return null;
	}
	
	public String excluirSelecionados() {
		Usuario u = null;
		for (Integer id : checked.keySet()) {
			if (checked.get(id)) {
				u = uController.find(id);
				uController.excluir(u);
			}
		}
		usuarios = uController.findAll();
		checked.clear();
		this.addInfoMessage("Usuarios selecionados excluidos com sucesso!");
		return null;
	}
	
//	public String editar(Usuario usuario) {
//		this.putFlash("usuario", usuario);
//		return "cadastro?faces-redirect=true";
//	}
	
	public String editarUsuario(Usuario usuario) {
		this.putFlash("usuario", usuario);
		this.putFlash("telefone", usuario.getTelefones().get(0));
		return "editarUsuario?faces-redirect=true";
	}
	
	public String adicionarTelefone(Usuario usuario) {
		this.putFlash("usuario", usuario);
		return "cadastroNumero?faces-redirect=true";
	}
	
	public String editarNumeros(Usuario usuario) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("usuario", usuario);
		return "listarNumeros?faces-redirect=true";
	}
	
	public String excluirNumero(Usuario usuario, Telefone telefone) {
		usuario.getTelefones().remove(telefone);
		tController.excluir(telefone);
		uController.update(usuario);
		this.addInfoMessage("Telefone excluido com sucesso!");
		this.init();
		return null;
	}
	
	public String editarNumero(Usuario usuario, Telefone telefone) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("usuario", usuario);
		System.out.println("editarNumero usuario= "+usuario);
		flash.put("telefone", telefone);
		System.out.println("editarNumero telefone= "+telefone);
		return "editarNumero?faces-redirect=true";
	}
	
	public String alterarNumero(Integer telefoneID, Integer usuarioID) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		uController.update((Usuario)flash.get("usuario"));
		return "consulta?faces-redirect=true";
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public List<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuario(List<Usuario> usuario) {
		this.usuarios = usuario;
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public List<Telefone> getTelefones() {
		return telefones;
	}
	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}

	public Telefone getTelefone() {
		return telefone;
	}
	public void setTelefone(Telefone telefone) {
		this.telefone = telefone;
	}

	public Map<Integer, Boolean> getChecked() {
		return checked;
	}
	public void setChecked(Map<Integer, Boolean> checked) {
		this.checked = checked;
	}
	
}
