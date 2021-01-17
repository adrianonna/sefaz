package br.gov.pe.sefaz.controller;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.gov.pe.sefaz.dao.Transactional;
import br.gov.pe.sefaz.dao.UsuarioDAO;
import br.gov.pe.sefaz.model.Usuario;

public class UsuarioController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private UsuarioDAO usuarioDAO;
	
	public void excluir(Usuario usuario) {
		usuarioDAO.delete(usuario);
	}
	
	public List<Usuario> findAll(){
		return usuarioDAO.findAll();
	}
	
	public Usuario find(Integer id) {
		return usuarioDAO.find(id);
	}
	
	public Usuario edit(Usuario usuario) {
		return usuarioDAO.update(usuario);
	}
	
	@Transactional
	public Usuario insert(Usuario usuario) {
		return usuarioDAO.insert(usuario);
	}
		

}
