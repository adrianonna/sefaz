package br.gov.pe.sefaz.controller;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;

import br.gov.pe.sefaz.dao.TelefoneDAO;
import br.gov.pe.sefaz.dao.Transactional;
import br.gov.pe.sefaz.model.Telefone;

public class TelefoneController implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Inject
	private TelefoneDAO telefoneDAO;
	
	@Transactional
	public void excluir(Telefone telefone) {
		telefoneDAO.delete(telefone);
	}
	
	public void refresh(Telefone telefone) {
		telefoneDAO.refresh(telefone);
	}
	
	public List<Telefone> findAll(){
		return telefoneDAO.findAll();
	}
	
	public Telefone find(Integer id) {
		return telefoneDAO.find(id);
	}
	
	@Transactional
	public Telefone update(Telefone telefone) {
		return telefoneDAO.update(telefone);
	}
	
	@Transactional
	public Telefone insert(Telefone telefone) {
		return telefoneDAO.insert(telefone);
	}
	
	@Transactional
	public void saveOrUpdate(Telefone telefone) {
		if (telefone.getId() != null) {
			telefone = telefoneDAO.update(telefone);
		} else {
			telefoneDAO.insert(telefone);
		}
	}
		

}
