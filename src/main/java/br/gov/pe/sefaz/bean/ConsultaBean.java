package br.gov.pe.sefaz.bean;

import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.gov.pe.sefaz.controller.UsuarioController;
import br.gov.pe.sefaz.model.Usuario;

@Named
@ViewScoped
public class ConsultaBean extends GenericoSefazBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private List<Usuario> usuarios;
	private Usuario usuario;
	private Integer id;
	
	private Map<Integer, Boolean> checked = new HashMap<Integer, Boolean>();
	
	@Inject
	private UsuarioController uController;
	

	public void init() {
		if (id == null) {
			usuarios = uController.findAll();			
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
	
	public String editar(Usuario usuario) {
		this.putFlash("usuario", usuario);
		return "cadastro?faces-redirect=true";
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
	
	public Map<Integer, Boolean> getChecked() {
		return checked;
	}
	public void setChecked(Map<Integer, Boolean> checked) {
		this.checked = checked;
	}
	
}
