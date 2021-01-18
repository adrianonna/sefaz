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
	
	@Transactional
	public void excluir(Usuario usuario) {
		usuarioDAO.delete(usuario);
	}
	
	public void refresh(Usuario usuario) {
		usuarioDAO.refresh(usuario);
	}
	
	public List<Usuario> findAll(){
		return usuarioDAO.findAll();
	}
	
	public Usuario find(Integer id) {
		return usuarioDAO.find(id);
	}
	
	@Transactional
	public Usuario update(Usuario usuario) {
		return usuarioDAO.update(usuario);
	}
	
	@Transactional
	public Usuario insert(Usuario usuario) {
		return usuarioDAO.insert(usuario);
	}
	
	@Transactional
	public Usuario saveOrUpdate(Usuario usuario) {
		if (usuario.getId() != null) {
			return usuario = usuarioDAO.update(usuario);
		} else {
			return usuarioDAO.insert(usuario);
		}
	}
		

}
