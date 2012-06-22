package it.inera.abi.logic.stampe;

import java.util.Hashtable;
import java.util.Vector;

@SuppressWarnings({"rawtypes"})
public class BiblioStampe {
	
	public String id_bib = null;
	public String isil_pr = null;
	public String isil_nr = null;
	public String isil_st = null;
	public String denominazione = null;
	public String comune = null;
	public String provincia = null;
	public String regione = null;
	public String siglaProvincia = null;
	public String indirizzo = null;
	public String cap = null;
	public String frazione = null;
	public Vector denominazione_gia = null;
	public Vector denominazione_anche = null;
	public Vector tel = null;
	public Vector fax = null;
	public Vector email = null;
	public Vector url = null;
	public String tipologiaAmministrativa = null;
	public String tipologiaFunzionale = null;
	public String utente = null;
	public Hashtable patrimonio = new Hashtable();
	
	public BiblioStampe() {
	}

	public String getCap() {
		return cap;
	}

	public void setCap(String c) {
		this.cap = c;
		//System.out.println("settato cap a " + this.cap + " con il valore " + c);
	}
	
	public String getFrazione() {
		return frazione;
	}

	public void setFrazione(String f) {
		this.frazione = f;
	}
	
	public String getComune() {
		return comune;
	}

	public void setComune(String comune) {
		this.comune = comune;
	}

	public Vector getTel() {
		return this.tel;
	}

	public void setTel(Vector t) {
		this.tel = t;
	}

	public Vector getFax() {
		return this.fax;
	}

	public void setFax(Vector f) {
		this.fax = f;
	}
	
	public Vector getUrl() {
		return url;
	}

	public void setUrl(Vector url) {
		this.url = url;
	}
	
	public Vector getEmail() {
		return email;
	}
	
	public void setEmail(Vector email) {
		this.email = email;
	}
	
	public Vector getDenominazioneAnche() {
		return this.denominazione_anche;
	}

	public void setDenominazioneAnche(Vector d) {
		this.denominazione_anche = d;
	}
	
	public Vector getDenominazioneGia() {
		return this.denominazione_gia;
	}

	public void setDenominazioneGia(Vector d) {
		this.denominazione_gia = d;
	}
	
	
	public String getDenominazione() {
		return denominazione;
	}

	public void setDenominazione(String denominazione) {
		this.denominazione = denominazione;
	}

	public String getId_bib() {
		return id_bib;
	}

	public void setId_bib(String id_bib) {
		this.id_bib = id_bib;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getIsil_nr() {
		return isil_nr;
	}

	public void setIsil_nr(String isil_nr) {
		this.isil_nr = isil_nr;
	}

	public String getIsil_pr() {
		return isil_pr;
	}

	public void setIsil_pr(String isil_pr) {
		this.isil_pr = isil_pr;
	}

	public String getIsil_st() {
		return isil_st;
	}

	public void setIsil_st(String isil_st) {
		this.isil_st = isil_st;
	}

	public String getProvincia() {
		return provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getRegione() {
		return regione;
	}

	public void setRegione(String regione) {
		this.regione = regione;
	}
	
	public String getSiglaProvincia() {
		return siglaProvincia;
	}

	public void setSiglaProvincia(String reg) {
		this.siglaProvincia = reg;
	}

	public String getTipologiaAmministrativa() {
		return tipologiaAmministrativa;
	}

	public void setTipologiaAmministrativa(String tipologiaAmministrativa) {
		this.tipologiaAmministrativa = tipologiaAmministrativa;
	}

	public String getTipologiaFunzionale() {
		return tipologiaFunzionale;
	}

	public void setTipologiaFunzionale(String tipologiaFunzionale) {
		this.tipologiaFunzionale = tipologiaFunzionale;
	}

	public Hashtable getPatrimonio() {
		return patrimonio;
	}

	public void setPatrimonio(Hashtable patrimonio) {
		this.patrimonio = patrimonio;
	}	

	public String getUtente() {
		return utente;
	}

	public void setUtente(String utente) {
		this.utente = utente;
	}

	public Vector getDenominazione_anche() {
		return denominazione_anche;
	}

	public void setDenominazione_anche(Vector denominazione_anche) {
		this.denominazione_anche = denominazione_anche;
	}

	public Vector getDenominazione_gia() {
		return denominazione_gia;
	}

	public void setDenominazione_gia(Vector denominazione_gia) {
		this.denominazione_gia = denominazione_gia;
	}
}
