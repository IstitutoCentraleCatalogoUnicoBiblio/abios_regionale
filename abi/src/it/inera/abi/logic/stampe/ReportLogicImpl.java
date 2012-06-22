package it.inera.abi.logic.stampe;

import it.inera.abi.dao.BiblioDao;
import it.inera.abi.persistence.Biblioteca;
import it.inera.abi.persistence.Contatti;
import it.inera.abi.persistence.DenominazioniAlternative;
import it.inera.abi.persistence.DenominazioniPrecedenti;
import it.inera.abi.persistence.Patrimonio;

import java.util.Hashtable;
import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReportLogicImpl implements ReportLogic {
	
	protected @Autowired BiblioDao biblioDao;
	
	@Transactional
	public Vector<BiblioStampe> retrieveBiblioReport(List<Integer> idbibs) {
		
		Vector<Biblioteca> bibvector = biblioDao.getBibliotecheReport(idbibs);
		
		if (bibvector != null) {
			Vector<BiblioStampe> biblioStampeVector = new Vector<BiblioStampe>();
			
			for (Biblioteca entry : bibvector) {
				BiblioStampe tmp = new BiblioStampe();
				tmp.setId_bib(entry.getIdBiblioteca().toString());
				tmp.setIsil_nr(entry.getIsilNumero().toString());
				tmp.setIsil_pr(entry.getIsilProvincia());
				tmp.setIsil_st(entry.getIsilStato());
				tmp.setDenominazione(entry.getDenominazioneUfficiale());
				tmp.setComune(entry.getComune().getDenominazione());
				tmp.setProvincia(entry.getComune().getProvincia().getDenominazione());
				tmp.setRegione(entry.getComune().getProvincia().getRegione().getDenominazione());
				tmp.setSiglaProvincia(entry.getComune().getProvincia().getSigla());
				tmp.setIndirizzo(entry.getIndirizzo());
				tmp.setCap(entry.getCap());
				tmp.setFrazione(entry.getFrazione());
				if (entry.getDenominazioniPrecedentis() != null && entry.getDenominazioniPrecedentis().size() != 0) {
					
					Vector<String> den_gia = new Vector<String>();
					for (DenominazioniPrecedenti gia : entry.getDenominazioniPrecedentis())
						den_gia.add(gia.getDenominazione());
					tmp.setDenominazione_gia(den_gia);
					
				}
				if (entry.getDenominazioniAlternatives() != null && entry.getDenominazioniAlternatives().size() != 0) {
					
					Vector<String> den_anche = new Vector<String>();
					for (DenominazioniAlternative anche : entry.getDenominazioniAlternatives())
						den_anche.add(anche.getDenominazione());
					tmp.setDenominazione_anche(den_anche);
					
				}
				if (entry.getContattis() != null && entry.getContattis().size() != 0) {
					/* Telefono/i */
					Vector<String> tel = new Vector<String>();
					for (Contatti cont : entry.getContattis()) {
						if (cont.getContattiTipo().getIdContattiTipo() == 1)
							tel.add(cont.getValore());
						
					}
					
					if (tel.size() != 0)
						tmp.setTel(tel);
					
					/* Fax */
					Vector<String> fax = new Vector<String>();
					for (Contatti cont : entry.getContattis()) {
						if (cont.getContattiTipo().getIdContattiTipo() == 2)
							fax.add(cont.getValore());
						
					}
					
					if (fax.size() != 0)
						tmp.setFax(fax);
					
					/* Email */
					Vector<String> email = new Vector<String>();
					for (Contatti cont : entry.getContattis()) {
						if (cont.getContattiTipo().getIdContattiTipo() == 3)
							email.add(cont.getValore());
						
					}
					
					if (email.size() != 0)
						tmp.setEmail(email);
					
					/* Url */
					Vector<String> url = new Vector<String>();
					for (Contatti cont : entry.getContattis()) {
						if (cont.getContattiTipo().getIdContattiTipo() == 5)
							url.add(cont.getValore());
						
					}
					
					if (url.size() != 0)
						tmp.setUrl(url);
					
				}
				
				tmp.setTipologiaAmministrativa(entry.getEnte().getEnteTipologiaAmministrativa().getDescrizione());
				tmp.setTipologiaFunzionale(entry.getTipologiaFunzionale().getDescrizione());
				if (entry.getUtenteUltimaModifica() != null && entry.getUtenteUltimaModifica().getLogin() != null)
					tmp.setUtente(entry.getUtenteUltimaModifica().getLogin());
				
				if (entry.getPatrimonios() != null && entry.getPatrimonios().size() != 0) {
					Hashtable<String, String> patr = new Hashtable<String, String>();					
								
					/* 
					 * L'hashtable viene riempita nel modo seguente:
					 * chiave: campo descrizione del patrimonio_specializzazione 
					 * valore: campo quantità 
					 *  
					 */				
					for (Patrimonio patrimoni : entry.getPatrimonios()) 
						patr.put(patrimoni.getPatrimonioSpecializzazione().getDescrizione(), patrimoni.getQuantita().toString());
					
					tmp.setPatrimonio(patr);
					
				}
				/* Aggiungo l'elemento 'BiblioStampe' appena creato al Vector */
				biblioStampeVector.add(tmp);
				
			}
			
			return biblioStampeVector;
		}
		else return null;
		
	}
	
}
