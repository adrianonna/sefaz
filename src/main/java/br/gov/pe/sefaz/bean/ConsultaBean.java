package br.gov.pe.sefaz.bean;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
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
	
	private Map<Integer, Boolean> checked = new HashMap<Integer, Boolean>();
	
	@Inject
	private UsuarioController uController;
	
	@PostConstruct
	public void init() {
		usuarios = uController.findAll();
	};
	
	public String excluir(Usuario usuario) {
		uController.excluir(usuario);
		this.addInfoMessage("Usuario excluido com sucesso!");
		return null;
	}
	
	public String excluirSelecionados() {
		Usuario u = null;
		System.out.println("checked= "+checked);
		System.out.println("checked.keySet()= "+checked.keySet());
		for (Integer id : checked.keySet()) {
			System.out.println("checked.get(id)= "+checked.get(id));
			if (checked.get(id)) {
				u = uController.find(id);
				System.out.println("u= "+u);
				uController.excluir(u);
			}
		}
		usuarios = uController.findAll();
		checked.clear();
		this.addInfoMessage("Usuarios selecionados excluidos com sucesso!");
		return null;
	}
	
	public String editar(Usuario usuario) {
		Flash fs = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		fs.put("usuario", usuario);
		return "edit";
	}
	
	public String salvaEditar() {
		Flash fs = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		Usuario usuario = (Usuario) fs.get("usuario"); //usuario alterado
		uController.edit(usuario);
//		uController.find(usuario.getId());
		this.addInfoMessage("Usuario editado com sucesso!");
		return null;
	}
	
	public void carregueDadosDoFlash() {
		Flash fs = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		this.setUsuario((Usuario) fs.get("usuario"));
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
