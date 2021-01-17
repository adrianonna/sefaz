package br.gov.pe.sefaz.bean;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

public class GenericoSefazBean {

	
	protected void addMessage(String mensagem, Severity severidade) {
		FacesMessage fm = new FacesMessage(mensagem);
		fm.setSeverity(severidade);
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage(null, fm);
	}
	
	protected void addInfoMessage(String mensagem) {
		this.addMessage(mensagem, FacesMessage.SEVERITY_INFO);
	}
	
	protected void addErrorMessage(String mensagem) {
		this.addMessage(mensagem, FacesMessage.SEVERITY_ERROR);
	}
	
	protected void putFlash(String nome, Object valor) {
		Flash fs = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		fs.put(nome, valor);
	}
	
	protected Object getFlash(String nome) {
		Flash fs = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		return fs.get(nome);
	}
	
	protected Flash getFlash() {
		return FacesContext.getCurrentInstance().getExternalContext().getFlash();
	}
	
	protected void keepMessages() {
		this.getFlash().setKeepMessages(true);
	}
	
}
