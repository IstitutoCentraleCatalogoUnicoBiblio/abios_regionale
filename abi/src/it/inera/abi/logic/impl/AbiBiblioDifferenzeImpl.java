package it.inera.abi.logic.impl;

import it.inera.abi.commons.CompareUtils;
import it.inera.abi.commons.Utility;
import it.inera.abi.dao.BiblioDao;
import it.inera.abi.logic.AbiBiblioDifferenze;
import it.inera.abi.persistence.AccessoModalita;
import it.inera.abi.persistence.Bibliografia;
import it.inera.abi.persistence.Biblioteca;
import it.inera.abi.persistence.Contatti;
import it.inera.abi.persistence.DenominazioniAlternative;
import it.inera.abi.persistence.DenominazioniPrecedenti;
import it.inera.abi.persistence.DepositiLegali;
import it.inera.abi.persistence.DestinazioniSociali;
import it.inera.abi.persistence.DeweyLibero;
import it.inera.abi.persistence.Ente;
import it.inera.abi.persistence.Geolocalizzazione;
import it.inera.abi.persistence.IndicizzazioneClassificata;
import it.inera.abi.persistence.IndicizzazioneSoggetto;
import it.inera.abi.persistence.NormeCatalogazione;
import it.inera.abi.persistence.OrarioChiusure;
import it.inera.abi.persistence.OrarioUfficiali;
import it.inera.abi.persistence.OrarioVariazioni;
import it.inera.abi.persistence.Patrimonio;
import it.inera.abi.persistence.Pubblicazioni;
import it.inera.abi.persistence.Regolamento;
import it.inera.abi.persistence.Riproduzioni;
import it.inera.abi.persistence.SezioniSpeciali;
import it.inera.abi.persistence.SistemiBiblioteche;
import it.inera.abi.persistence.SistemiPrestitoInterbibliotecario;
import it.inera.abi.persistence.SpogliBibliografici;
import it.inera.abi.persistence.TipologiaFunzionale;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.exolab.castor.xml.Unmarshaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AbiBiblioDifferenzeImpl implements AbiBiblioDifferenze {

	private Log _log = LogFactory.getLog(AbiBiblioDifferenzeImpl.class);
	
	public static final String FONDI_ANTICHI_FINO_1000_VOLUMI="Fino a 1000 volumi";
	public static final String FONDI_ANTICHI_1000_5000_VOLUMI="Da 1000 a 5000 volumi";
	public static final String FONDI_ANTICHI_OLTRE_5000_VOLUMI="Oltre 5000 volumi";
	public static final String NON_SPECIFICATO="Non specificato";


	@Autowired private BiblioDao biblioDao;

	private @Value("${abi.saved.dir}") String saveDir;

	/**
	 * Confronta le denominazioni precedenti con quelle attuali
	 * @param differenze
	 * @param bibliotecaSalvata
	 * @param bibliotecaAttuale
	 */
	private void compareDenominazioniPrecedenti(List<Differenze> differenze, Biblioteca bibliotecaSalvata, Biblioteca bibliotecaAttuale) {
		if (bibliotecaAttuale.getDenominazioniPrecedentis() != null) {
			boolean found = false;
			for (DenominazioniPrecedenti denominazioniPrecedentiAttuale : bibliotecaAttuale.getDenominazioniPrecedentis()) {
				if (bibliotecaSalvata.getDenominazioniPrecedentis() != null) {
					for (DenominazioniPrecedenti denominazioniPrecedentiSalvata : bibliotecaSalvata.getDenominazioniPrecedentis()) {
						if (CompareUtils.equals(denominazioniPrecedentiAttuale.getDenominazione(), denominazioniPrecedentiSalvata.getDenominazione())) {
							found = true;
							break;
						}
					}
				}
				if (!found) createDifference(differenze, "Denominazione precedente", "", denominazioniPrecedentiAttuale.getDenominazione());
				found = false;
			}
		}
		if (bibliotecaSalvata.getDenominazioniPrecedentis() != null) {
			boolean found = false;
			for (DenominazioniPrecedenti denominazioniPrecedentiSalvata : bibliotecaSalvata.getDenominazioniPrecedentis()) {
				if (bibliotecaAttuale.getDenominazioniPrecedentis() != null) {
					for (DenominazioniPrecedenti denominazioniPrecedentiAttuale : bibliotecaAttuale.getDenominazioniPrecedentis()) {
						if (CompareUtils.equals(denominazioniPrecedentiAttuale.getDenominazione(), denominazioniPrecedentiSalvata.getDenominazione())) {
							found = true;
							break;
						}
					}
				}
				if (!found) createDifference(differenze, "Denominazione precedente", denominazioniPrecedentiSalvata.getDenominazione(), "");
				found = false;
			}
		} 
	}

	/**
	 * Confronta le denominazioni alternative con quelle attuali
	 * @param differenze
	 * @param bibliotecaSalvata
	 * @param bibliotecaAttuale
	 */
	private void compareDenominazioniAlternative(List<Differenze> differenze, Biblioteca bibliotecaSalvata, Biblioteca bibliotecaAttuale) {
		if (bibliotecaAttuale.getDenominazioniAlternatives() != null) {
			boolean found = false;
			for (DenominazioniAlternative denominazioniAlternativeAttuale : bibliotecaAttuale.getDenominazioniAlternatives()) {
				if (bibliotecaSalvata.getDenominazioniAlternatives() != null) {
					for (DenominazioniAlternative denominazioniAlternativeSalvata : bibliotecaSalvata.getDenominazioniAlternatives()) {
						if (CompareUtils.equals(denominazioniAlternativeAttuale.getDenominazione(), denominazioniAlternativeSalvata.getDenominazione())) {
							found = true;
							break;
						}
					}
				}
				if (!found) createDifference(differenze, "Denominazione alternativa", "", denominazioniAlternativeAttuale.getDenominazione());
				found = false;
			}
		}
		if (bibliotecaSalvata.getDenominazioniAlternatives() != null) {
			boolean found = false;
			for (DenominazioniAlternative denominazioniAlternativeSalvata : bibliotecaSalvata.getDenominazioniAlternatives()) {
				if (bibliotecaAttuale.getDenominazioniAlternatives() != null) {
					for (DenominazioniAlternative denominazioniAlternativeAttuale : bibliotecaAttuale.getDenominazioniAlternatives()) {
						if (CompareUtils.equals(denominazioniAlternativeAttuale.getDenominazione(), denominazioniAlternativeSalvata.getDenominazione())) {
							found = true;
							break;
						}
					}
				}
				if (!found) createDifference(differenze, "Denominazione alternativa", denominazioniAlternativeSalvata.getDenominazione(), "");
				found = false;
			}
		} 
	}

	/**
	 * 
	 * @param differenze
	 * @param bibliotecaSalvata
	 * @param bibliotecaAttuale
	 */
	private void compareRecapiti(List<Differenze> differenze, Biblioteca bibliotecaSalvata, Biblioteca bibliotecaAttuale) {
		if (bibliotecaAttuale.getContattis() != null) {
			boolean found = false;
			for (Contatti contattiAttuale : bibliotecaAttuale.getContattis()) {
				if (bibliotecaSalvata.getContattis() != null) {
					for (Contatti contattiSalvata : bibliotecaAttuale.getContattis()) {
						if (
								(CompareUtils.equals(contattiSalvata.getContattiTipo().getDescrizione(), contattiAttuale.getContattiTipo().getDescrizione())) &&
								(CompareUtils.equals(contattiSalvata.getNote(), contattiAttuale.getNote())) &&
								(CompareUtils.equals(contattiSalvata.getValore(), contattiAttuale.getValore()))
						) {
							found = true;
							break;
						}
					}
				}
				if (!found) createDifferenceContatti(differenze, null, contattiAttuale);
				found = false;
			}
		}
		if (bibliotecaSalvata.getContattis() != null) {
			boolean found = false;
			for (Contatti contattiSalvati : bibliotecaSalvata.getContattis()) {
				if (bibliotecaAttuale.getContattis() != null) {
					for (Contatti contattiAttuale : bibliotecaAttuale.getContattis()) {
						if (
								(CompareUtils.equals(contattiAttuale.getContattiTipo().getDescrizione(), contattiSalvati.getContattiTipo().getDescrizione())) &&
								(CompareUtils.equals(contattiAttuale.getNote(), contattiSalvati.getNote())) &&
								(CompareUtils.equals(contattiAttuale.getValore(), contattiSalvati.getValore()))
						) {
							found = true;
							break;
						}
					}
				}
				if (!found) createDifferenceContatti(differenze, contattiSalvati, null);
				found = false;
			}
		}
	}

	/**
	 * 
	 * @param differenze
	 * @param bibliotecaSalvata
	 * @param bibliotecaAttuale
	 */
	private void compareSistemi(List<Differenze> differenze, Biblioteca bibliotecaSalvata, Biblioteca bibliotecaAttuale) {
		if (bibliotecaAttuale.getSistemiBiblioteches() != null) {
			boolean found = false;
			for (SistemiBiblioteche sistemiBibliotecheAttuale : bibliotecaAttuale.getSistemiBiblioteches()) {
				if (bibliotecaSalvata.getSistemiBiblioteches() != null) {
					for (SistemiBiblioteche sistemiBibliotecheSalvata : bibliotecaSalvata.getSistemiBiblioteches()) {
						if (CompareUtils.equals(sistemiBibliotecheAttuale.getDescrizione(), sistemiBibliotecheSalvata.getDescrizione())) {
							found = true;
							break;
						}
					}
				}
				if (!found) createDifference(differenze, "Sistemi biblioteche", null, sistemiBibliotecheAttuale.getDescrizione());
				found = false;
			}
		} 
	}
	private void compareBilancio(List<Differenze> differenze, Biblioteca bibliotecaSalvata, Biblioteca bibliotecaAttuale) {

		if (!CompareUtils.equals(bibliotecaSalvata.getBilancioUscite(), bibliotecaAttuale.getBilancioUscite()))
			createDifference(differenze, "Bilancio Uscite", bibliotecaSalvata.getBilancioUscite(), 
					bibliotecaAttuale.getBilancioUscite());
		
		if (!CompareUtils.equals(bibliotecaSalvata.getBilancioUscitePersonale(), bibliotecaAttuale.getBilancioUscitePersonale()))
			createDifference(differenze, "Bilancio Uscite Personale", bibliotecaSalvata.getBilancioUscitePersonale(), 
					bibliotecaAttuale.getBilancioUscitePersonale());


		if (!CompareUtils.equals(bibliotecaSalvata.getBilancioUsciteFunzionamento(), bibliotecaAttuale.getBilancioUsciteFunzionamento()))
			createDifference(differenze, "Bilancio Uscite Funzionamento", bibliotecaSalvata.getBilancioUsciteFunzionamento(), 
					bibliotecaAttuale.getBilancioUsciteFunzionamento());

		if (!CompareUtils.equals(bibliotecaSalvata.getBilancioUsciteIncrementoPatrimonio(), bibliotecaAttuale.getBilancioUsciteIncrementoPatrimonio()))
			createDifference(differenze, "Bilancio Uscite Incremento Patrimonio", bibliotecaSalvata.getBilancioUsciteIncrementoPatrimonio(), 
					bibliotecaAttuale.getBilancioUsciteIncrementoPatrimonio());

	
		if (!CompareUtils.equals(bibliotecaSalvata.getBilancioUsciteAutomazione(), bibliotecaAttuale.getBilancioUsciteAutomazione()))
			createDifference(differenze, "Bilancio Uscite Automazione", bibliotecaSalvata.getBilancioUsciteAutomazione(), 
					bibliotecaAttuale.getBilancioUsciteAutomazione());
		
		if (!CompareUtils.equals(bibliotecaSalvata.getBilancioUsciteVarie(), bibliotecaAttuale.getBilancioUsciteVarie()))
			createDifference(differenze, "Bilancio Uscite Varie", bibliotecaSalvata.getBilancioUsciteVarie(), 
					bibliotecaAttuale.getBilancioUsciteVarie());

		if (!CompareUtils.equals(bibliotecaSalvata.getBilancioEntrate(), bibliotecaAttuale.getBilancioEntrate()))
			createDifference(differenze, "Bilancio Entrate", bibliotecaSalvata.getBilancioEntrate(), 
					bibliotecaAttuale.getBilancioEntrate());
		

		/*Non mappati nell'interfaccia (formato di scambio)*/
//		if (!CompareUtils.equals(bibliotecaSalvata.getBilancioPatrimonialePosseduto(), bibliotecaAttuale.getBilancioPatrimonialePosseduto()))
//			createDifference(differenze, "Bilancio Patrimoniale Posseduto", bibliotecaSalvata.getBilancioPatrimonialePosseduto(), 
//					bibliotecaAttuale.getBilancioPatrimonialePosseduto());
//
//
//
//		if (!CompareUtils.equals(bibliotecaSalvata.getBilancioUsciteAcquistiAnno(), bibliotecaAttuale.getBilancioUsciteAcquistiAnno()))
//			createDifference(differenze, "Bilancio Uscite Acquisti Anno", bibliotecaSalvata.getBilancioUsciteAcquistiAnno(),
//					bibliotecaAttuale.getBilancioUsciteAcquistiAnno());
//
//
//
//		if (!CompareUtils.equals(bibliotecaSalvata.getBilancioPatrimonialePossedutoUnder14(), bibliotecaAttuale.getBilancioPatrimonialePossedutoUnder14()))
//			createDifference(differenze, "Bilancio Patrimoniale PossedutoUnder 14", bibliotecaSalvata.getBilancioPatrimonialePossedutoUnder14(), 
//					bibliotecaAttuale.getBilancioPatrimonialePossedutoUnder14());
//
//		if (!CompareUtils.equals(bibliotecaSalvata.getBilancioUsciteAcquistiUltimi15(), bibliotecaAttuale.getBilancioUsciteAcquistiUltimi15()))
//			createDifference(differenze, "Bilancio Uscite Acquisti Ultimi 15", bibliotecaSalvata.getBilancioUsciteAcquistiUltimi15(), 
//					bibliotecaAttuale.getBilancioUsciteAcquistiUltimi15());
		/*Fine---Non mappati nell'interfaccia (formato di scambio)*/
	}


	/**
	 * 
	 * @param differenze
	 * @param bibliotecaSalvata
	 * @param bibliotecaAttuale
	 */
	private void compareAccessoModalita(List<Differenze> differenze, Biblioteca bibliotecaSalvata, Biblioteca bibliotecaAttuale) {
		if (bibliotecaAttuale.getAccessoModalitas() != null) {
			boolean found = false;
			for (AccessoModalita accessoModalitaAttuale : bibliotecaAttuale.getAccessoModalitas()) {
				if (bibliotecaSalvata.getAccessoModalitas() != null) {
					for (AccessoModalita accessoModalitaSalvata : bibliotecaSalvata.getAccessoModalitas()) {
						if (CompareUtils.equals(accessoModalitaAttuale.getDescrizione(), accessoModalitaSalvata.getDescrizione())) {
							found = true;
							break;							
						}
					}
				}
				if (!found) createDifference(differenze, "Accesso Modalita", null, accessoModalitaAttuale.getDescrizione());
				found = false;
			}
		}
		if (bibliotecaSalvata.getAccessoModalitas() != null) {
			boolean found = false;
			for (AccessoModalita accessoModalitaSalvata : bibliotecaSalvata.getAccessoModalitas()) {
				if (bibliotecaAttuale.getAccessoModalitas() != null) {
					for (AccessoModalita accessoModalitaAttuale : bibliotecaAttuale.getAccessoModalitas()) {
						if (CompareUtils.equals(accessoModalitaSalvata.getDescrizione(), accessoModalitaAttuale.getDescrizione())) {
							found = true;
							break;							
						}
					}
				}
				if (!found) createDifference(differenze, "Accesso Modalita", accessoModalitaSalvata.getDescrizione(), null);
				found = false;
			}
		}
	}

	/**
	 * 
	 * @param differenze
	 * @param bibliotecaSalvata
	 * @param bibliotecaAttuale
	 */
	private void compareDestinazioniSociali(List<Differenze> differenze, Biblioteca bibliotecaSalvata, Biblioteca bibliotecaAttuale) {
		if (bibliotecaAttuale.getDestinazioniSocialis() != null) {
			boolean found = false;
			for (DestinazioniSociali destinazioniSocialiAttuale : bibliotecaAttuale.getDestinazioniSocialis()) {
				if (bibliotecaSalvata.getDestinazioniSocialis() != null) {
					for (DestinazioniSociali destinazioniSocialiSalvata : bibliotecaSalvata.getDestinazioniSocialis()) {
						if ((!CompareUtils.equals(destinazioniSocialiSalvata.getDestinazioniSocialiTipo().getDescrizione(), destinazioniSocialiAttuale.getDestinazioniSocialiTipo().getDescrizione()) &&
								!CompareUtils.equals(destinazioniSocialiSalvata.getNote(), destinazioniSocialiAttuale.getNote())	)) {
							found = true;
							break;
						}
					}
				}
				if (!found) createDifferenceDestinazioniSociali(differenze, null, destinazioniSocialiAttuale);
				found = false;
			}
		}
		if (bibliotecaSalvata.getDestinazioniSocialis() != null) {
			boolean found = false;
			for (DestinazioniSociali destinazioniSocialiSalvata : bibliotecaSalvata.getDestinazioniSocialis()) {
				if (bibliotecaAttuale.getDestinazioniSocialis() != null) {
					for (DestinazioniSociali destinazioniSocialiAttuale : bibliotecaAttuale.getDestinazioniSocialis()) {
						if ((CompareUtils.equals(destinazioniSocialiSalvata.getDestinazioniSocialiTipo().getDescrizione(), destinazioniSocialiAttuale.getDestinazioniSocialiTipo().getDescrizione()) &&
								CompareUtils.equals(destinazioniSocialiSalvata.getNote(), destinazioniSocialiAttuale.getNote())	)) {
							found = true;
							break;
						}
					}
				}
				if (!found) createDifferenceDestinazioniSociali(differenze, destinazioniSocialiSalvata, null);
				found = false;
			}
		}
	}

	private void compareRegolamento(List<Differenze> differenze, Biblioteca bibliotecaSalvata, Biblioteca bibliotecaAttuale) {
		if (bibliotecaAttuale.getRegolamentos() != null) {
			boolean found = false;
			for (Regolamento regolamentoAttuale : bibliotecaAttuale.getRegolamentos()) {
				if (bibliotecaSalvata.getRegolamentos() != null) {
					for (Regolamento regolamentoSalvata : bibliotecaAttuale.getRegolamentos()) {
						if ((CompareUtils.equals(regolamentoSalvata.getRiferimentoNormativa(), regolamentoAttuale.getRiferimentoNormativa()) &&
								CompareUtils.equals(regolamentoSalvata.getUrl(), regolamentoAttuale.getUrl()))) {
							found = true;
							break;
						}
					}
				}
				if (!found) createDifferenceRegolamento(differenze, null, regolamentoAttuale);
				found = false;
			}
		} 
		if (bibliotecaSalvata.getRegolamentos() != null) {
			boolean found = false;
			for (Regolamento regolamentoSalvata : bibliotecaSalvata.getRegolamentos()) {
				if (bibliotecaAttuale.getRegolamentos() != null) {
					for (Regolamento regolamentoAttuale : bibliotecaAttuale.getRegolamentos()) {
						if ((CompareUtils.equals(regolamentoSalvata.getRiferimentoNormativa(), regolamentoAttuale.getRiferimentoNormativa()) &&
								CompareUtils.equals(regolamentoSalvata.getUrl(), regolamentoAttuale.getUrl()))) {
							found = true;
							break;
						}
					}
				}
				if (!found) createDifferenceRegolamento(differenze, regolamentoSalvata, null);
				found = false;
			}
		} 
	}

	/**
	 * 
	 * @param differenze
	 * @param bibliotecaSalvata
	 * @param bibliotecaAttuale
	 */
	public void compareOrarioUfficiale(List<Differenze> differenze, Biblioteca bibliotecaSalvata, Biblioteca bibliotecaAttuale) {
		if (bibliotecaAttuale.getOrarioUfficialis() != null) {
			boolean found = false;
			for (OrarioUfficiali orarioUfficialiAttuale : bibliotecaAttuale.getOrarioUfficialis()) {
				if (bibliotecaSalvata.getOrarioUfficialis() != null) {
					for (OrarioUfficiali orarioUfficialiSalvata : bibliotecaSalvata.getOrarioUfficialis()) {
						if (CompareUtils.equals(orarioUfficialiSalvata, orarioUfficialiAttuale)) {
							found = true;
							break;
						}
					}
				}
				if (!found) createDifferenceOrarioUfficiale(differenze, null, orarioUfficialiAttuale);
				found = false;
			}
		} 
		if (bibliotecaSalvata.getOrarioUfficialis() != null) {
			boolean found = false;
			for (OrarioUfficiali orarioUfficialiSalvata : bibliotecaSalvata.getOrarioUfficialis()) {
				if (bibliotecaAttuale.getOrarioUfficialis() != null) {
					for (OrarioUfficiali orarioUfficialiAttuale : bibliotecaAttuale.getOrarioUfficialis()) {
						if (CompareUtils.equals(orarioUfficialiSalvata, orarioUfficialiAttuale)) {
							found = true;
							break;
						}
					}
				}
				if (!found) createDifferenceOrarioUfficiale(differenze, orarioUfficialiSalvata, null);
				found = false;
			}
		} 
	}

	public void compareAccessoInternet(List<Differenze> differenze, Biblioteca bibliotecaSalvata, Biblioteca bibliotecaAttuale) {
		if (bibliotecaSalvata.getAccessoInternetPagamento() != bibliotecaAttuale.getAccessoInternetPagamento()) {
			createDifference(differenze, "Accesso Internet Pagamento", siNoBoolean(bibliotecaSalvata.getAccessoInternetPagamento()), siNoBoolean(bibliotecaAttuale.getAccessoInternetPagamento()));
		}
		if (bibliotecaSalvata.getAccessoInternetProxy() != bibliotecaAttuale.getAccessoInternetProxy()) {
			createDifference(differenze, "Accesso Internet Proxy", siNoBoolean(bibliotecaSalvata.getAccessoInternetProxy()),siNoBoolean( bibliotecaAttuale.getAccessoInternetProxy()));
		}
		if (bibliotecaSalvata.getAccessoInternetTempo() != bibliotecaAttuale.getAccessoInternetTempo()) {
			createDifference(differenze, "Accesso Internet Tempo", siNoBoolean(bibliotecaSalvata.getAccessoInternetTempo()),siNoBoolean( bibliotecaAttuale.getAccessoInternetTempo()));
		}
	}


	private void comparePrestitoInterbibliotecario(List<Differenze> differenze, Biblioteca bibliotecaSalvata, Biblioteca bibliotecaAttuale) {
		if (bibliotecaSalvata.getPrestitoInterbiblioInternazionale() != bibliotecaAttuale.getPrestitoInterbiblioInternazionale()) {
			createDifference(differenze, "Prestito Interbiblio Internazionale", 
					siNoBoolean(bibliotecaSalvata.getPrestitoInterbiblioInternazionale()), 
					siNoBoolean(bibliotecaAttuale.getPrestitoInterbiblioInternazionale()));
		}
		if (bibliotecaSalvata.getPrestitoInterbiblioNazionale() != bibliotecaAttuale.getPrestitoInterbiblioNazionale()) {
			createDifference(differenze, "Prestito Interbiblio Nazionale", 
					siNoBoolean(bibliotecaSalvata.getPrestitoInterbiblioNazionale()), 
					siNoBoolean(bibliotecaAttuale.getPrestitoInterbiblioNazionale()));
		}
		if (bibliotecaSalvata.getProcedureIllAutomatizzate() != bibliotecaAttuale.getProcedureIllAutomatizzate()) {
			createDifference(differenze, "Procedure Ill Automatizzate", 
					siNoBoolean(bibliotecaSalvata.getProcedureIllAutomatizzate()), 
					siNoBoolean(bibliotecaAttuale.getProcedureIllAutomatizzate()));
		}
	}

	private void comparePersonale(List<Differenze> differenze, Biblioteca bibliotecaSalvata, Biblioteca bibliotecaAttuale) {
		if (!CompareUtils.equals(bibliotecaAttuale.getPersonaleTotale(), bibliotecaSalvata.getPersonaleTotale())) {
			createDifference(differenze, "Personale Totale", 
					bibliotecaSalvata.getPersonaleTotale(), 
					bibliotecaAttuale.getPersonaleTotale());
		}
		
		if (!CompareUtils.equals(bibliotecaAttuale.getPersonalePartTime(), bibliotecaSalvata.getPersonalePartTime())) {
			createDifference(differenze, "Personale Part-Time", 
					bibliotecaSalvata.getPersonalePartTime(), 
					bibliotecaAttuale.getPersonalePartTime());
		}
		
		if (!CompareUtils.equals(bibliotecaAttuale.getPersonaleTemporaneo(), bibliotecaSalvata.getPersonaleTemporaneo())) {
			createDifference(differenze, "Personale Temporaneo", 
					bibliotecaSalvata.getPersonaleTemporaneo(), 
					bibliotecaAttuale.getPersonaleTemporaneo());
		}
	
		if (!CompareUtils.equals(bibliotecaAttuale.getPersonaleEsterno(), bibliotecaSalvata.getPersonaleEsterno())) {
			createDifference(differenze, "Personale Esterno", 
					bibliotecaSalvata.getPersonaleEsterno(), 
					bibliotecaAttuale.getPersonaleEsterno());
		}
		
	}

	private void compareInfoSuppUtenti(List<Differenze> differenze, Biblioteca bibliotecaSalvata, Biblioteca bibliotecaAttuale) {
		if (!CompareUtils.equals(bibliotecaAttuale.getUtenti(), bibliotecaSalvata.getUtenti())) {
			createDifference(differenze, "Ingressi registrati ultimi 12 mesi", bibliotecaSalvata.getUtenti(), bibliotecaAttuale.getUtenti());
		}
		
		if (!CompareUtils.equals(bibliotecaAttuale.getUtentiIscrittiPrestitoAnno(), bibliotecaSalvata.getUtentiIscrittiPrestitoAnno())) {
			createDifference(differenze, "Utenti Iscritti al Prestito ultimi 12 mesi", bibliotecaSalvata.getUtentiIscrittiPrestitoAnno(), bibliotecaAttuale.getUtentiIscrittiPrestitoAnno());
		}
		
		if (!CompareUtils.equals(bibliotecaAttuale.getUtentiIscritti(), bibliotecaSalvata.getUtentiIscritti())) {
			createDifference(differenze, "Utenti Iscritti", bibliotecaSalvata.getUtentiIscritti(), bibliotecaAttuale.getUtentiIscritti());
		}
	}

	private void compareDepositoLegale(List<Differenze> differenze, Biblioteca bibliotecaSalvata, Biblioteca bibliotecaAttuale) {
		if (bibliotecaAttuale.getDepositiLegalis() != null) {
			boolean found = false;
			for (DepositiLegali depositiLegaliAttuale : bibliotecaAttuale.getDepositiLegalis()) {
				if (bibliotecaSalvata.getDepositiLegalis() != null) {
					for (DepositiLegali depositiLegaliSalvata : bibliotecaAttuale.getDepositiLegalis()) {
						if ((CompareUtils.equals(depositiLegaliSalvata.getDaAnno(), depositiLegaliAttuale.getDaAnno()) &&
								CompareUtils.equals(depositiLegaliSalvata.getDepositiLegaliTipo().getDescrizione(), depositiLegaliAttuale.getDepositiLegaliTipo().getDescrizione()))) {
							found = true;
							break;
						}
					}
				}
				if (!found) createDifferenceDepositiLegali(differenze, null, depositiLegaliAttuale);
				found = false;
			}
		} 
		if (bibliotecaSalvata.getDepositiLegalis() != null) {
			boolean found = false;
			for (DepositiLegali depositiLegaliSalvata : bibliotecaSalvata.getDepositiLegalis()) {
				if (bibliotecaAttuale.getDepositiLegalis() != null) {
					for (DepositiLegali depositiLegaliAttuale : bibliotecaAttuale.getDepositiLegalis()) {
						if ((CompareUtils.equals(depositiLegaliSalvata.getDaAnno(), depositiLegaliAttuale.getDaAnno()) &&
								CompareUtils.equals(depositiLegaliSalvata.getDepositiLegaliTipo().getDescrizione(), depositiLegaliAttuale.getDepositiLegaliTipo().getDescrizione()))) {
							found = true;
							break;
						}
					}
				}
				if (!found) createDifferenceDepositiLegali(differenze, depositiLegaliSalvata, null);
				found = false;
			}
		} 
	}

	private void compareSezioniSpeciali(List<Differenze> differenze, Biblioteca bibliotecaSalvata, Biblioteca bibliotecaAttuale) {
		if (bibliotecaAttuale.getSezioniSpecialis() != null) {
			boolean found = false;
			for (SezioniSpeciali sezioniSpecialiAttuale : bibliotecaAttuale.getSezioniSpecialis()) {
				if (bibliotecaSalvata.getSezioniSpecialis() != null) {
					for (SezioniSpeciali sezioniSpecialiSalvata : bibliotecaAttuale.getSezioniSpecialis()) {
						if (CompareUtils.equals(sezioniSpecialiSalvata.getDescrizione(), sezioniSpecialiAttuale.getDescrizione())) {
							found = true;
							break;
						}
					}
				}
				if (!found) createDifference(differenze, "Sezione speciale", null, sezioniSpecialiAttuale.getDescrizione());
				found = false;
			}
		} 
		if (bibliotecaSalvata.getSezioniSpecialis() != null) {
			boolean found = false;
			for (SezioniSpeciali sezioniSpecialiSalvata : bibliotecaSalvata.getSezioniSpecialis()) {
				if (bibliotecaAttuale.getSezioniSpecialis() != null) {
					for (SezioniSpeciali sezioniSpecialiAttuale : bibliotecaAttuale.getSezioniSpecialis()) {
						if (CompareUtils.equals(sezioniSpecialiSalvata.getDescrizione(), sezioniSpecialiAttuale.getDescrizione())) {
							found = true;
							break;
						}
					}
				}
				if (!found) createDifference(differenze, "Sezione speciale", sezioniSpecialiSalvata.getDescrizione(), null);
				found = false;
			}
		} 
	}

	private void compareRiproduzioni(List<Differenze> differenze, Biblioteca bibliotecaSalvata, Biblioteca bibliotecaAttuale) {
		if (bibliotecaAttuale.getRiproduzionis() != null) {
			boolean found = false;
			for (Riproduzioni riproduzioniAttuale : bibliotecaAttuale.getRiproduzionis()) {
				if (bibliotecaSalvata.getRiproduzionis() != null) {
					for (Riproduzioni riproduzioniSalvata : bibliotecaAttuale.getRiproduzionis()) {
						if (riproduzioniSalvata.getInternazionale() == riproduzioniAttuale.getInternazionale() &&
								riproduzioniSalvata.getLocale() == riproduzioniAttuale.getLocale() &&
								riproduzioniSalvata.getNazionale() == riproduzioniAttuale.getNazionale() &&
								CompareUtils.equals(riproduzioniSalvata.getRiproduzioniTipo().getDescrizione(), riproduzioniAttuale.getRiproduzioniTipo().getDescrizione())) {
							found = true;
							break;
						}
					}
				}
				if (!found) createDifferenceRiproduzioni(differenze, null, riproduzioniAttuale);
				found = false;
			}
		} 
		if (bibliotecaSalvata.getRiproduzionis() != null) {
			boolean found = false;
			for (Riproduzioni riproduzioniSalvata : bibliotecaSalvata.getRiproduzionis()) {
				if (bibliotecaAttuale.getRiproduzionis() != null) {
					for (Riproduzioni riproduzioniAttuale : bibliotecaAttuale.getRiproduzionis()) {
						if (riproduzioniSalvata.getInternazionale() == riproduzioniAttuale.getInternazionale() &&
								riproduzioniSalvata.getLocale() == riproduzioniAttuale.getLocale() &&
								riproduzioniSalvata.getNazionale() == riproduzioniAttuale.getNazionale() &&
								CompareUtils.equals(riproduzioniSalvata.getRiproduzioniTipo().getDescrizione(), riproduzioniAttuale.getRiproduzioniTipo().getDescrizione())) {
							found = true;
							break;
						}
					}
				}
				if (!found) createDifferenceRiproduzioni(differenze, riproduzioniSalvata, null);
				found = false;
			}
		} 
	}

	private void compareVariazioniOrario(List<Differenze> differenze, Biblioteca bibliotecaSalvata, Biblioteca bibliotecaAttuale) {
		if (bibliotecaAttuale.getOrarioVariazionis() != null) {
			boolean found = false;
			for (OrarioVariazioni orarioVariazioniAttuale : bibliotecaAttuale.getOrarioVariazionis()) {
				if (bibliotecaSalvata.getOrarioVariazionis() != null) {
					for (OrarioVariazioni orarioVariazioniSalvata : bibliotecaAttuale.getOrarioVariazionis()) {
						if (CompareUtils.equals(orarioVariazioniSalvata.getDescrizione(), orarioVariazioniAttuale.getDescrizione())) {
							found = true;
							break;
						}
					}
				}
				if (!found) createDifference(differenze, "Orario Variazioni", null, orarioVariazioniAttuale.getDescrizione());
				found = false;
			}
		} 
		if (bibliotecaSalvata.getOrarioVariazionis() != null) {
			boolean found = false;
			for (OrarioVariazioni orarioVariazioniSalvata : bibliotecaSalvata.getOrarioVariazionis()) {
				if (bibliotecaAttuale.getOrarioVariazionis() != null) {
					for (OrarioVariazioni orarioVariazioniAttuale : bibliotecaAttuale.getOrarioVariazionis()) {
						if (CompareUtils.equals(orarioVariazioniSalvata.getDescrizione(), orarioVariazioniAttuale.getDescrizione())) {
							found = true;
							break;
						}
					}
				}
				if (!found) createDifference(differenze, "Orario Variazioni", orarioVariazioniSalvata.getDescrizione(), null);
				found = false;
			}
		} 
	}

	private void compareChiusureOrario(List<Differenze> differenze, Biblioteca bibliotecaSalvata, Biblioteca bibliotecaAttuale) {
		if (bibliotecaAttuale.getOrarioChiusures() != null) {
			boolean found = false;
			for (OrarioChiusure orarioChiusureAttuale : bibliotecaAttuale.getOrarioChiusures()) {
				if (bibliotecaSalvata.getOrarioChiusures() != null) {
					for (OrarioChiusure orarioChiusureSalvata : bibliotecaAttuale.getOrarioChiusures()) {
						if (CompareUtils.equals(orarioChiusureSalvata.getDescrizione(), orarioChiusureAttuale.getDescrizione())) {
							found = true;
							break;
						}
					}
				}
				if (!found) createDifference(differenze, "Orario Chiusure", null, orarioChiusureAttuale.getDescrizione());
				found = false;
			}
		} 
		if (bibliotecaSalvata.getOrarioChiusures() != null) {
			boolean found = false;
			for (OrarioChiusure orarioChiusureSalvata : bibliotecaSalvata.getOrarioChiusures()) {
				if (bibliotecaAttuale.getOrarioChiusures() != null) {
					for (OrarioChiusure orarioChiusureAttuale : bibliotecaAttuale.getOrarioChiusures()) {
						if (CompareUtils.equals(orarioChiusureSalvata.getDescrizione(), orarioChiusureAttuale.getDescrizione())) {
							found = true;
							break;
						}
					}
				}
				if (!found) createDifference(differenze, "Orario Chiusure", orarioChiusureSalvata.getDescrizione(), null);
				found = false;
			}
		} 
	}


	private void comparePatrimonioLibrario(List<Differenze> differenze, Biblioteca bibliotecaSalvata, Biblioteca bibliotecaAttuale) {
		if (bibliotecaAttuale.getPatrimonios() != null) {
			boolean found = false;
			for (Patrimonio patrimonioAttuale : bibliotecaAttuale.getPatrimonios()) {
				if (bibliotecaSalvata.getPatrimonios() != null) {
					for (Patrimonio patrimonioSalvata : bibliotecaAttuale.getPatrimonios()) {
						if (patrimonioSalvata.getQuantita() == patrimonioAttuale.getQuantita() &&
								patrimonioSalvata.getQuantitaUltimoAnno() == patrimonioAttuale.getQuantitaUltimoAnno() &&
								CompareUtils.equals(patrimonioSalvata.getPatrimonioSpecializzazione().getDescrizione(), patrimonioAttuale.getPatrimonioSpecializzazione().getDescrizione())) {
							found = true;
							break;
						}
					}
				}
				if (!found) createDifferencePatrimonio(differenze, null, patrimonioAttuale);
				found = false;
			}
		} 
		if (bibliotecaSalvata.getPatrimonios() != null) {
			boolean found = false;
			for (Patrimonio patrimonioSalvata : bibliotecaSalvata.getPatrimonios()) {
				if (bibliotecaAttuale.getPatrimonios() != null) {
					for (Patrimonio patrimonioAttuale : bibliotecaAttuale.getPatrimonios()) {
						if (patrimonioSalvata.getQuantita() == patrimonioAttuale.getQuantita() &&
								patrimonioSalvata.getQuantitaUltimoAnno() == patrimonioAttuale.getQuantitaUltimoAnno() &&
								CompareUtils.equals(patrimonioSalvata.getPatrimonioSpecializzazione().getDescrizione(), patrimonioAttuale.getPatrimonioSpecializzazione().getDescrizione())) {
							found = true;
							break;
						}
					}
				}
				if (!found) createDifferencePatrimonio(differenze, patrimonioSalvata, null);
				found = false;
			}
		} 
	}

	private void compareInventarioCatalogoTopograficoFondiAntichi(List<Differenze> differenze, Biblioteca bibliotecaSalvata, Biblioteca bibliotecaAttuale) {
		if (!CompareUtils.equals(bibliotecaSalvata.getFondiAntichiConsistenza().getDescrizione(), bibliotecaAttuale.getFondiAntichiConsistenza().getDescrizione()))
			createDifference(differenze, "Fondi Antichi Consistenza", mappingFondiAntichiConsistenza(bibliotecaSalvata.getFondiAntichiConsistenza().getIdFondiAntichiConsistenza()), mappingFondiAntichiConsistenza(bibliotecaAttuale.getFondiAntichiConsistenza().getIdFondiAntichiConsistenza()));

		if (bibliotecaSalvata.getInventarioCartaceo() != bibliotecaAttuale.getInventarioCartaceo())
			createDifference(differenze, "Inventario Cartaceo", siNoBoolean(bibliotecaSalvata.getInventarioCartaceo()), siNoBoolean(bibliotecaAttuale.getInventarioCartaceo()));

		if (bibliotecaSalvata.getInventarioInformatizzato() != bibliotecaAttuale.getInventarioInformatizzato())
			createDifference(differenze, "Inventario Informatizzato", siNoBoolean(bibliotecaSalvata.getInventarioInformatizzato()), siNoBoolean(bibliotecaAttuale.getInventarioInformatizzato()));

		if (bibliotecaSalvata.getCatalogoTopograficoCartaceo() != bibliotecaAttuale.getCatalogoTopograficoCartaceo())
			createDifference(differenze, "Catalogo Topografico Cartaceo", siNoBoolean(bibliotecaSalvata.getCatalogoTopograficoCartaceo()), siNoBoolean(bibliotecaAttuale.getCatalogoTopograficoCartaceo()));

		if (bibliotecaSalvata.getCatalogoTopograficoInformatizzato() != bibliotecaAttuale.getCatalogoTopograficoInformatizzato())
			createDifference(differenze, "Catalogo Topografico Informatizzato", siNoBoolean(bibliotecaSalvata.getCatalogoTopograficoInformatizzato()), siNoBoolean(bibliotecaAttuale.getCatalogoTopograficoInformatizzato()));
	}

	public String mappingFondiAntichiConsistenza(int id_fondiAntichi1830){
		
			String fondiAntichi1830 = null;

			switch (id_fondiAntichi1830) {
			case 1: {
				fondiAntichi1830 = NON_SPECIFICATO;
				break;
			}
			case 2: {
				fondiAntichi1830 = FONDI_ANTICHI_FINO_1000_VOLUMI;
				break;
			}

			case 3: {
				fondiAntichi1830 = FONDI_ANTICHI_1000_5000_VOLUMI;
				break;
			}

			case 4: {
				fondiAntichi1830 = FONDI_ANTICHI_OLTRE_5000_VOLUMI;
				break;
			}

			}
			return fondiAntichi1830;
		
	}
	
	
	private void compareSpecializzazioni(List<Differenze> differenze, Biblioteca bibliotecaSalvata, Biblioteca bibliotecaAttuale) {
		if (bibliotecaAttuale.getDeweyLiberos() != null) {
			boolean found = false;
			for (DeweyLibero deweyLiberoAttuale : bibliotecaAttuale.getDeweyLiberos()) {
				if (bibliotecaSalvata.getDeweyLiberos() != null) {
					for (DeweyLibero deweyLiberoSalvata : bibliotecaAttuale.getDeweyLiberos()) {
						if (CompareUtils.equals(deweyLiberoSalvata.getDescrizione(), deweyLiberoAttuale.getDescrizione())) {
							found = true;
							break;
						}
					}
				}
				if (!found) createDifference(differenze, "Specializzazione", null, deweyLiberoAttuale.getDescrizione());
				found = false;
			}
		} 
		if (bibliotecaSalvata.getDeweyLiberos() != null) {
			boolean found = false;
			for (DeweyLibero deweyLiberoSalvata : bibliotecaSalvata.getDeweyLiberos()) {
				if (bibliotecaAttuale.getDeweyLiberos() != null) {
					for (DeweyLibero deweyLiberoAttuale : bibliotecaAttuale.getDeweyLiberos()) {
						if (CompareUtils.equals(deweyLiberoSalvata.getDescrizione(), deweyLiberoAttuale.getDescrizione())) {
							found = true;
							break;
						}
					}
				}
				if (!found) createDifference(differenze, "Specializzazione", deweyLiberoSalvata.getDescrizione(), null);
				found = false;
			}
		} 
	}

	private void comparePubblicazioni(List<Differenze> differenze, Biblioteca bibliotecaSalvata, Biblioteca bibliotecaAttuale) {
		if (bibliotecaAttuale.getPubblicazionis() != null) {
			boolean found = false;
			for (Pubblicazioni pubblicazioniAttuale : bibliotecaAttuale.getPubblicazionis()) {
				if (bibliotecaSalvata.getPubblicazionis() != null) {
					for (Pubblicazioni pubblicazioniSalvata : bibliotecaAttuale.getPubblicazionis()) {
						if (CompareUtils.equals(pubblicazioniSalvata.getDescrizione(), pubblicazioniAttuale.getDescrizione())) {
							found = true;
							break;
						}
					}
				}
				if (!found) createDifference(differenze, "Pubblicazione", null, pubblicazioniAttuale.getDescrizione());
				found = false;
			}
		} 
		if (bibliotecaSalvata.getPubblicazionis() != null) {
			boolean found = false;
			for (Pubblicazioni pubblicazioniSalvata : bibliotecaSalvata.getPubblicazionis()) {
				if (bibliotecaAttuale.getPubblicazionis() != null) {
					for (Pubblicazioni pubblicazioniAttuale : bibliotecaAttuale.getPubblicazionis()) {
						if (CompareUtils.equals(pubblicazioniSalvata.getDescrizione(), pubblicazioniAttuale.getDescrizione())) {
							found = true;
							break;
						}
					}
				}
				if (!found) createDifference(differenze, "Pubblicazione", pubblicazioniSalvata.getDescrizione(), null);
				found = false;
			}
		} 
	}

	private void compareNormeCatalogazione(List<Differenze> differenze, Biblioteca bibliotecaSalvata, Biblioteca bibliotecaAttuale) {
		if (bibliotecaAttuale.getNormeCatalogaziones() != null) {
			boolean found = false;
			for (NormeCatalogazione normeCatalogazioneAttuale : bibliotecaAttuale.getNormeCatalogaziones()) {
				if (bibliotecaSalvata.getNormeCatalogaziones() != null) {
					for (NormeCatalogazione normeCatalogazioneSalvata : bibliotecaAttuale.getNormeCatalogaziones()) {
						if (CompareUtils.equals(normeCatalogazioneSalvata.getDescrizione(), normeCatalogazioneAttuale.getDescrizione())) {
							found = true;
							break;
						}
					}
				}
				if (!found) createDifference(differenze, "Norme Catalogazione", null, normeCatalogazioneAttuale.getDescrizione());
				found = false;
			}
		} 
		if (bibliotecaSalvata.getNormeCatalogaziones() != null) {
			boolean found = false;
			for (NormeCatalogazione normeCatalogazioneSalvata : bibliotecaSalvata.getNormeCatalogaziones()) {
				if (bibliotecaAttuale.getNormeCatalogaziones() != null) {
					for (NormeCatalogazione normeCatalogazioneAttuale : bibliotecaAttuale.getNormeCatalogaziones()) {
						if (CompareUtils.equals(normeCatalogazioneSalvata.getDescrizione(), normeCatalogazioneAttuale.getDescrizione())) {
							found = true;
							break;
						}
					}
				}
				if (!found) createDifference(differenze, "Norme Catalogazione", normeCatalogazioneSalvata.getDescrizione(), null);
				found = false;
			}
		} 
	}

	private void compareSistemiPrestitoInterbibliotecario(List<Differenze> differenze, Biblioteca bibliotecaSalvata, Biblioteca bibliotecaAttuale) {
		if (bibliotecaAttuale.getSistemiPrestitoInterbibliotecarios() != null) {
			boolean found = false;
			for (SistemiPrestitoInterbibliotecario sistemiPrestitoInterbibliotecarioAttuale : bibliotecaAttuale.getSistemiPrestitoInterbibliotecarios()) {
				if (bibliotecaSalvata.getSistemiPrestitoInterbibliotecarios() != null) {
					for (SistemiPrestitoInterbibliotecario sistemiPrestitoInterbibliotecarioSalvata : bibliotecaAttuale.getSistemiPrestitoInterbibliotecarios()) {
						if (CompareUtils.equals(sistemiPrestitoInterbibliotecarioSalvata.getDescrizione(), sistemiPrestitoInterbibliotecarioAttuale.getDescrizione())) {
							found = true;
							break;
						}
					}
				}
				if (!found) createDifference(differenze, "Sistemi Prestito Interbibliotecario", null, sistemiPrestitoInterbibliotecarioAttuale.getDescrizione());
				found = false;
			}
		} 
		if (bibliotecaSalvata.getSistemiPrestitoInterbibliotecarios() != null) {
			boolean found = false;
			for (SistemiPrestitoInterbibliotecario sistemiPrestitoInterbibliotecarioSalvata : bibliotecaSalvata.getSistemiPrestitoInterbibliotecarios()) {
				if (bibliotecaAttuale.getSistemiPrestitoInterbibliotecarios() != null) {
					for (SistemiPrestitoInterbibliotecario sistemiPrestitoInterbibliotecarioAttuale : bibliotecaAttuale.getSistemiPrestitoInterbibliotecarios()) {
						if (CompareUtils.equals(sistemiPrestitoInterbibliotecarioSalvata.getDescrizione(), sistemiPrestitoInterbibliotecarioAttuale.getDescrizione())) {
							found = true;
							break;
						}
					}
				}
				if (!found) createDifference(differenze, "Sistemi Prestito Interbibliotecario", sistemiPrestitoInterbibliotecarioSalvata.getDescrizione(), null);
				found = false;
			}
		} 
	}
	
	
	private void compareSpogliMaterialeBibliografico(List<Differenze> differenze, Biblioteca bibliotecaSalvata, Biblioteca bibliotecaAttuale) {
		if (bibliotecaAttuale.getSpogliBibliograficis() != null) {
			boolean found = false;
			for (SpogliBibliografici spogliBibliograficiAttuale : bibliotecaAttuale.getSpogliBibliograficis()) {
				if (bibliotecaSalvata.getSpogliBibliograficis() != null) {
					for (SpogliBibliografici spogliBibliograficiSalvata : bibliotecaAttuale.getSpogliBibliograficis()) {
						if (CompareUtils.equals(spogliBibliograficiSalvata.getDescrizioneBibliografica(), spogliBibliograficiAttuale.getDescrizioneBibliografica())) {
							found = true;
							break;
						}
					}
				}
				if (!found) createDifference(differenze, "Spogli Materiale Bibliografici", null, spogliBibliograficiAttuale.getDescrizioneBibliografica());
				found = false;
			}
		} 
		if (bibliotecaSalvata.getSpogliBibliograficis() != null) {
			boolean found = false;
			for (SpogliBibliografici spogliBibliograficiSalvata : bibliotecaSalvata.getSpogliBibliograficis()) {
				if (bibliotecaAttuale.getSpogliBibliograficis() != null) {
					for (SpogliBibliografici spogliBibliograficiAttuale : bibliotecaAttuale.getSpogliBibliograficis()) {
						if (CompareUtils.equals(spogliBibliograficiSalvata.getDescrizioneBibliografica(), spogliBibliograficiAttuale.getDescrizioneBibliografica())) {
							found = true;
							break;
						}
					}
				}
				if (!found) createDifference(differenze, "Spogli Materiale Bibliografici", spogliBibliograficiSalvata.getDescrizioneBibliografica(), null);
				found = false;
			}
		} 
	}
	
	private void compareIndicizzazioneClassificata(List<Differenze> differenze, Biblioteca bibliotecaSalvata, Biblioteca bibliotecaAttuale) {
		if (bibliotecaAttuale.getIndicizzazioneClassificatas() != null) {
			boolean found = false;
			for (IndicizzazioneClassificata indicizzazioneClassificataAttuale : bibliotecaAttuale.getIndicizzazioneClassificatas()) {
				if (bibliotecaSalvata.getIndicizzazioneClassificatas() != null) {
					for (IndicizzazioneClassificata indicizzazioneClassificataSalvata : bibliotecaAttuale.getIndicizzazioneClassificatas()) {
						if (CompareUtils.equals(indicizzazioneClassificataSalvata.getDescrizione(), indicizzazioneClassificataAttuale.getDescrizione())) {
							found = true;
							break;
						}
					}
				}
				if (!found) createDifference(differenze, "Indicizzazione Classificata", null, indicizzazioneClassificataAttuale.getDescrizione());
				found = false;
			}
		} 
		if (bibliotecaSalvata.getIndicizzazioneClassificatas() != null) {
			boolean found = false;
			for (IndicizzazioneClassificata indicizzazioneClassificataSalvata : bibliotecaSalvata.getIndicizzazioneClassificatas()) {
				if (bibliotecaAttuale.getIndicizzazioneClassificatas() != null) {
					for (IndicizzazioneClassificata indicizzazioneClassificataAttuale : bibliotecaAttuale.getIndicizzazioneClassificatas()) {
						if (CompareUtils.equals(indicizzazioneClassificataSalvata.getDescrizione(), indicizzazioneClassificataAttuale.getDescrizione())) {
							found = true;
							break;
						}
					}
				}
				if (!found) createDifference(differenze, "Indicizzazione Classificata", indicizzazioneClassificataSalvata.getDescrizione(), null);
				found = false;
			}
		} 
	}
	
	private void compareIndicizzazioneSoggetto(List<Differenze> differenze, Biblioteca bibliotecaSalvata, Biblioteca bibliotecaAttuale) {
		if (bibliotecaAttuale.getIndicizzazioneSoggettos() != null) {
			boolean found = false;
			for (IndicizzazioneSoggetto indicizzazioneSoggettoAttuale : bibliotecaAttuale.getIndicizzazioneSoggettos()) {
				if (bibliotecaSalvata.getIndicizzazioneSoggettos() != null) {
					for (IndicizzazioneSoggetto indicizzazioneSoggettoSalvata : bibliotecaAttuale.getIndicizzazioneSoggettos()) {
						if (CompareUtils.equals(indicizzazioneSoggettoSalvata.getDescrizione(), indicizzazioneSoggettoAttuale.getDescrizione())) {
							found = true;
							break;
						}
					}
				}
				if (!found) createDifference(differenze, "Indicizzazione Soggetto", null, indicizzazioneSoggettoAttuale.getDescrizione());
				found = false;
			}
		} 
		if (bibliotecaSalvata.getIndicizzazioneSoggettos() != null) {
			boolean found = false;
			for (IndicizzazioneSoggetto indicizzazioneSoggettoSalvata : bibliotecaSalvata.getIndicizzazioneSoggettos()) {
				if (bibliotecaAttuale.getIndicizzazioneSoggettos() != null) {
					for (IndicizzazioneSoggetto indicizzazioneSoggettoAttuale : bibliotecaAttuale.getIndicizzazioneSoggettos()) {
						if (CompareUtils.equals(indicizzazioneSoggettoSalvata.getDescrizione(), indicizzazioneSoggettoAttuale.getDescrizione())) {
							found = true;
							break;
						}
					}
				}
				if (!found) createDifference(differenze, "Indicizzazione Soggetto", indicizzazioneSoggettoSalvata.getDescrizione(), null);
				found = false;
			}
		} 
	}
	
	private void compareBibliografia(List<Differenze> differenze, Biblioteca bibliotecaSalvata, Biblioteca bibliotecaAttuale) {
		int idMax = 0;
		String descrSalvata = null;
		if (bibliotecaSalvata.getBibliografias() != null) {
			for (Bibliografia temp: bibliotecaSalvata.getBibliografias()) {
				if (idMax < temp.getIdBibliografia()) {
					idMax = temp.getIdBibliografia();
					descrSalvata = temp.getDescrizione();
				}
			}
		}
		String descrAttuale = null;
		if (bibliotecaAttuale.getBibliografias() != null) {
			for (Bibliografia temp: bibliotecaAttuale.getBibliografias()) {
				if (idMax < temp.getIdBibliografia()) {
					idMax = temp.getIdBibliografia();
					descrAttuale = temp.getDescrizione();
				}
			}
		}
		createDifference(differenze, "Bibliografia", descrSalvata, descrAttuale);
	}
	
//	private void comparePrestitoLocale(List<Differenze> differenze, Biblioteca bibliotecaSalvata, Biblioteca bibliotecaAttuale) {
//		if (bibliotecaAttuale.getPrestitoLocales() != null) {
//			boolean found = false;
//			for (PrestitoLocale prestitoLocaleAttuale : bibliotecaAttuale.getPrestitoLocales()) {
//				if (bibliotecaSalvata.getPrestitoLocales() != null) {
//					for (PrestitoLocale prestitoLocaleSalvata : bibliotecaAttuale.getPrestitoLocales()) {
//						if (CompareUtils.equals(prestitoLocaleSalvata.getDescrizione(), prestitoLocaleAttuale.getDescrizione())) {
//							found = true;
//							break;
//						}
//					}
//				}
//				if (!found) createDifference(differenze, "Indicizzazione Soggetto", null, prestitoLocaleAttuale.getDescrizione());
//				found = false;
//			}
//		} 
//		if (bibliotecaSalvata.getPrestitoLocales() != null) {
//			boolean found = false;
//			for (PrestitoLocale prestitoLocaleSalvata : bibliotecaSalvata.getPrestitoLocales()) {
//				if (bibliotecaAttuale.getPrestitoLocales() != null) {
//					for (PrestitoLocale prestitoLocaleAttuale : bibliotecaAttuale.getPrestitoLocales()) {
//						if (CompareUtils.equals(prestitoLocaleSalvata.getDescrizione(), prestitoLocaleAttuale.getDescrizione())) {
//							found = true;
//							break;
//						}
//					}
//				}
//				if (!found) createDifference(differenze, "Indicizzazione Soggetto", prestitoLocaleSalvata.getDescrizione(), null);
//				found = false;
//			}
//		} 
//	}
	
	
	/**
	 * Ritorna una mappa delle differenze tra la biblioteca salvata e quella sul database
	 * @param idbiblio
	 * @return
	 * @throws Exception
	 */
	@Override
	@Transactional
	public List<Differenze> difference(int idbiblio) throws Exception {
		String fullSavedFilename = Utility.getSavedFilename(idbiblio, saveDir);

		List<Differenze> differenze = new ArrayList<Differenze>();
		try {
			Biblioteca bibliotecaSalvata = (Biblioteca) Unmarshaller.unmarshal(Biblioteca.class, new FileReader(new File(fullSavedFilename)));
			Biblioteca bibliotecaAttuale = biblioDao.getBibliotecaById(idbiblio);

			// **********denominazione ufficiale**********
			if (!CompareUtils.equals(bibliotecaAttuale.getDenominazioneUfficiale(), bibliotecaSalvata.getDenominazioneUfficiale())) {
				createDifference(differenze, "Denominazione ufficiale", 
						bibliotecaSalvata.getDenominazioneUfficiale(), bibliotecaAttuale.getDenominazioneUfficiale());
			}

			// **********denominazioni precedenti**********
			//compareDenominazioniPrecedenti(differenze, bibliotecaSalvata, bibliotecaAttuale);


			// **********denominazioni alternative**********
			//compareDenominazioniAlternative(differenze, bibliotecaSalvata, bibliotecaAttuale);

			// **********indirizzo********** 
			if (!CompareUtils.equals(bibliotecaSalvata.getIndirizzo(), bibliotecaAttuale.getIndirizzo())) 
				createDifference(differenze, "Indirizzo", bibliotecaSalvata.getIndirizzo(),	bibliotecaAttuale.getIndirizzo());
			if (!CompareUtils.equals(bibliotecaSalvata.getFrazione(), bibliotecaAttuale.getFrazione())) 
				createDifference(differenze, "Frazione", bibliotecaSalvata.getFrazione(),	bibliotecaAttuale.getFrazione());
			if (!CompareUtils.equals(bibliotecaSalvata.getCap(), bibliotecaAttuale.getCap())) 
				createDifference(differenze, "Cap", bibliotecaSalvata.getCap(),	bibliotecaAttuale.getCap());
			if (!CompareUtils.equals(bibliotecaSalvata.getComune().getDenominazione(), bibliotecaAttuale.getComune().getDenominazione())) 
				createDifference(differenze, "Comune", bibliotecaSalvata.getComune().getDenominazione(),	bibliotecaAttuale.getComune().getDenominazione());
			Geolocalizzazione geoAttuale = bibliotecaAttuale.getGeolocalizzazione();
			Geolocalizzazione geoSalvata = bibliotecaSalvata.getGeolocalizzazione();
			if (!CompareUtils.equals(getLatFromGeo(geoSalvata), getLatFromGeo(geoAttuale))) {
				createDifference(differenze, "Latitudine", getLatFromGeo(geoSalvata), getLatFromGeo(geoAttuale));
			}
			if (!CompareUtils.equals(getLongFromGeo(geoSalvata), getLongFromGeo(geoAttuale))) {
				createDifference(differenze, "Longitudine", getLongFromGeo(geoSalvata),	getLongFromGeo(geoAttuale));
			}

			// **********recapiti**********
			//compareRecapiti(differenze, bibliotecaSalvata, bibliotecaAttuale);


			// **********sistemi**********
			//compareSistemi(differenze, bibliotecaSalvata, bibliotecaAttuale);

			// punti decentrati

			// **********profilo storico e sede**********
			if (bibliotecaAttuale.getEdificioMonumentale() != bibliotecaSalvata.getEdificioMonumentale())
				createDifference(differenze, "Edificio Monumentale", siNoBoolean(bibliotecaSalvata.getEdificioMonumentale()), siNoBoolean(bibliotecaAttuale.getEdificioMonumentale()));
			if (!CompareUtils.equals(bibliotecaAttuale.getEdificioDenominazione(), bibliotecaSalvata.getEdificioDenominazione()))
				createDifference(differenze, "Edificio Denominazione", bibliotecaSalvata.getEdificioDenominazione(), bibliotecaAttuale.getEdificioDenominazione());
			if (bibliotecaAttuale.getEdificioAppositamenteCostruito() != bibliotecaSalvata.getEdificioAppositamenteCostruito())
				createDifference(differenze, "Edificio Appositamente Costruito", siNoBoolean(bibliotecaSalvata.getEdificioAppositamenteCostruito()), siNoBoolean(bibliotecaAttuale.getEdificioAppositamenteCostruito()));
			if (!CompareUtils.equals(bibliotecaAttuale.getEdificioDataCostruzione(), bibliotecaSalvata.getEdificioDataCostruzione()))
				createDifference(differenze, "Edificio Data Costruzione", bibliotecaSalvata.getEdificioDataCostruzione(), bibliotecaAttuale.getEdificioDataCostruzione());


			// **********locali**********
			if (!CompareUtils.equals(bibliotecaAttuale.getMlAperti(), bibliotecaSalvata.getMlAperti()))
				createDifference(differenze, "Mq Locali Aperti", bibliotecaSalvata.getMlAperti(), bibliotecaAttuale.getMlAperti());
			if (!CompareUtils.equals(bibliotecaAttuale.getMlMagazzini(), bibliotecaSalvata.getMlMagazzini()))
				createDifference(differenze, "Mq Locali Magazzini", bibliotecaSalvata.getMlMagazzini(), bibliotecaAttuale.getMlMagazzini());
			if (!CompareUtils.equals(bibliotecaAttuale.getMqPubblici(), bibliotecaSalvata.getMqPubblici()))
				createDifference(differenze, "Mq Locali Pubblici", bibliotecaSalvata.getMqPubblici(), bibliotecaAttuale.getMqPubblici());
			if (!CompareUtils.equals(bibliotecaAttuale.getMqTotali(), bibliotecaSalvata.getMqTotali()))
				createDifference(differenze, "Mq totali", bibliotecaSalvata.getMqTotali(), bibliotecaAttuale.getMqTotali());


			// **********postazioni**********
			if (!CompareUtils.equals(bibliotecaAttuale.getPostiAudio(), bibliotecaSalvata.getPostiAudio()))
				createDifference(differenze, "Posti audio", bibliotecaSalvata.getPostiAudio(), bibliotecaAttuale.getPostiAudio());
			if (!CompareUtils.equals(bibliotecaAttuale.getPostiInternet(), bibliotecaSalvata.getPostiInternet()))
				createDifference(differenze, "Posti internet", bibliotecaSalvata.getPostiInternet(), bibliotecaAttuale.getPostiInternet());
			if (!CompareUtils.equals(bibliotecaAttuale.getPostiLettura(), bibliotecaSalvata.getPostiLettura()))
				createDifference(differenze, "Posti lettura", bibliotecaSalvata.getPostiLettura(), bibliotecaAttuale.getPostiLettura());
			if (!CompareUtils.equals(bibliotecaAttuale.getPostiVideo(), bibliotecaSalvata.getPostiVideo()))
				createDifference(differenze, "Posti video", bibliotecaSalvata.getPostiVideo(), bibliotecaAttuale.getPostiVideo());

			// **********Ente di appartenenza**********
			Ente enteSalvato = bibliotecaSalvata.getEnte();
			Ente enteAttuale = bibliotecaAttuale.getEnte();
			if (!CompareUtils.equals(enteSalvato.getDenominazione(), enteAttuale.getDenominazione())) {
				createDifference(differenze, "Ente", enteSalvato.getDenominazione(), enteAttuale.getDenominazione());
			}

			// **********Autonomia amministrativa della biblioteca**********
			if (bibliotecaSalvata.getAutonomiaAmministrativa() !=  bibliotecaAttuale.getAutonomiaAmministrativa()) {
				createDifference(differenze, "Autonomia Amministrativa", 
						siNoBoolean(bibliotecaSalvata.getAutonomiaAmministrativa()), 
						siNoBoolean(bibliotecaAttuale.getAutonomiaAmministrativa()));
			}
			if (!CompareUtils.equals(bibliotecaSalvata.getStrutturaGerarchicaSovraordinata(), bibliotecaAttuale.getStrutturaGerarchicaSovraordinata())) {
				createDifference(differenze, "Struttura Gerarchica Sovraordinata", bibliotecaSalvata.getStrutturaGerarchicaSovraordinata(), bibliotecaAttuale.getStrutturaGerarchicaSovraordinata());
			}

			// **********Tipologia funzionale**********
			TipologiaFunzionale tipologiaFunzionaleSalvata = bibliotecaSalvata.getTipologiaFunzionale();
			TipologiaFunzionale tipologiaFunzionaleAttuale = bibliotecaAttuale.getTipologiaFunzionale();
			if (!CompareUtils.equals(tipologiaFunzionaleSalvata.getDescrizione(), tipologiaFunzionaleAttuale.getDescrizione())) {
				createDifference(differenze, "Tipologia Funzionale", tipologiaFunzionaleSalvata.getDescrizione(), tipologiaFunzionaleAttuale.getDescrizione());
			}
			// **********Fondazione**********
			if (!CompareUtils.equals(bibliotecaSalvata.getDataFondazione(), bibliotecaAttuale.getDataFondazione())) {
				createDifference(differenze, "Data Fondazione", bibliotecaSalvata.getDataFondazione(), bibliotecaAttuale.getDataFondazione());
			}
			if (!CompareUtils.equals(bibliotecaSalvata.getDataIstituzione(), bibliotecaAttuale.getDataIstituzione())) {
				createDifference(differenze, "Data Istituzione", bibliotecaSalvata.getDataIstituzione(), bibliotecaAttuale.getDataIstituzione());
			}
			// **********info accesso
			if (bibliotecaAttuale.getAccessoRiservato() != bibliotecaSalvata.getAccessoRiservato()){
				String bibliotecaSalvataAccessoRiservato=null;
				String bibliotecaAttualeAccessoRiservato=null;
				if(bibliotecaSalvata.getAccessoRiservato()!=null){
					bibliotecaSalvataAccessoRiservato= bibliotecaSalvata.getAccessoRiservato()==true?"Riservata":"Aperta a tutti";
				}else bibliotecaSalvataAccessoRiservato= "Informazione non disponibile";
				
				if(bibliotecaAttuale.getAccessoRiservato()!=null) {
					bibliotecaAttualeAccessoRiservato=bibliotecaAttuale.getAccessoRiservato()==true?"Riservata":"Aperta a tutti";
				}else bibliotecaAttualeAccessoRiservato= "Informazione non disponibile";
				
				createDifference(differenze, "Accesso Riservato",bibliotecaSalvataAccessoRiservato, bibliotecaAttualeAccessoRiservato);
			}
		    if (!CompareUtils.equals(bibliotecaAttuale.getAccessoLimiteEtaMin(), bibliotecaSalvata.getAccessoLimiteEtaMin()))
				createDifference(differenze, "Accesso Limite Eta Min", bibliotecaSalvata.getAccessoLimiteEtaMin(), bibliotecaAttuale.getAccessoLimiteEtaMin());
		    if (!CompareUtils.equals(bibliotecaAttuale.getAccessoLimiteEtaMax(), bibliotecaSalvata.getAccessoLimiteEtaMax()))
				createDifference(differenze, "Accesso Limite Eta Max", bibliotecaSalvata.getAccessoLimiteEtaMax(), bibliotecaAttuale.getAccessoLimiteEtaMax());
			
			// **********Accesso modalità**********
			//compareAccessoModalita(differenze, bibliotecaSalvata, bibliotecaAttuale);
			if (bibliotecaAttuale.getAccessoHandicap() != bibliotecaSalvata.getAccessoHandicap()){
				createDifference(differenze, "Accesso Handicap",siNoBoolean(bibliotecaSalvata.getAccessoHandicap()) , siNoBoolean(bibliotecaAttuale.getAccessoHandicap()));
			}
			// ********* dest sociali *************
			//compareDestinazioniSociali(differenze, bibliotecaSalvata, bibliotecaAttuale);

			// ********* regolamento **************
			//compareRegolamento(differenze, bibliotecaSalvata, bibliotecaAttuale);

			// ********* orario ufficiale **************
			//compareOrarioUfficiale(differenze, bibliotecaSalvata, bibliotecaAttuale);
			
			// ********* variazioni d'orario**************
			//compareVariazioniOrario(differenze, bibliotecaSalvata, bibliotecaAttuale);
			
			// ********* chiusure **************
			//compareChiusureOrario(differenze, bibliotecaSalvata, bibliotecaAttuale);
			
			// ********* patrimonio librario **************
			//comparePatrimonioLibrario(differenze, bibliotecaSalvata, bibliotecaAttuale);
			
			// ******** Inventario - Catalogo topografico - Fondi antichi (fino al 1830)
			compareInventarioCatalogoTopograficoFondiAntichi(differenze, bibliotecaSalvata, bibliotecaAttuale);
			
			// ******* Specializzazioni *************
			//compareSpecializzazioni(differenze, bibliotecaSalvata, bibliotecaAttuale);
			
			// ******* fondi speciali *************
			
			// ******** sistemi di indicizzazione Classificata **********
			//compareIndicizzazioneClassificata(differenze, bibliotecaSalvata, bibliotecaAttuale);
			
			// ******** sistemi di indicizzazione per Soggetto **********
			//compareIndicizzazioneSoggetto(differenze, bibliotecaSalvata, bibliotecaAttuale);
			
			// ******** norme di catalogazione ***********
			//compareNormeCatalogazione(differenze, bibliotecaSalvata, bibliotecaAttuale);
			
			// ******** Spogli materiale bibliografico **********
			//compareSpogliMaterialeBibliografico(differenze, bibliotecaSalvata, bibliotecaAttuale);
			
			// ******** Pubblicazioni *************
			//comparePubblicazioni(differenze, bibliotecaSalvata, bibliotecaAttuale);
			
			// ******** Bibliografia ***********
			//compareBibliografia(differenze, bibliotecaSalvata, bibliotecaAttuale);
			
			// ******** Cataloghi generali *******
			
			// ******** Cataloghi Speciali *******
			
			// ******** Cataloghi Collettivi *******
			
			// ******** Servizi *****************
			//compareRiproduzioni(differenze, bibliotecaSalvata, bibliotecaAttuale);
			
			// ******** Informazioni bibliografiche *****************
			if (bibliotecaAttuale.getGestisceServizioBibliograficoEsterno() != bibliotecaSalvata.getGestisceServizioBibliograficoEsterno())
				createDifference(differenze, "Gestisce Servizio Bibliografico Esterno", siNoBoolean(bibliotecaSalvata.getGestisceServizioBibliograficoEsterno()), siNoBoolean(bibliotecaAttuale.getGestisceServizioBibliograficoEsterno()) );
			if (bibliotecaAttuale.getGestisceServizioBibliograficoInterno() != bibliotecaSalvata.getGestisceServizioBibliograficoInterno())
				createDifference(differenze, "Gestisce Servizio Bibliografico Interno", siNoBoolean(bibliotecaSalvata.getGestisceServizioBibliograficoInterno()), siNoBoolean(bibliotecaAttuale.getGestisceServizioBibliograficoInterno()));
			
			// ******** Sezioni speciali *****************
			//compareSezioniSpeciali(differenze, bibliotecaSalvata, bibliotecaAttuale);
			
			// ******** Accesso internet *****************
			compareAccessoInternet(differenze, bibliotecaSalvata, bibliotecaAttuale);
			// ******** PrestitoLocale *****************
			
			// ******** Prestito interbibliotecario *****************
			comparePrestitoInterbibliotecario(differenze, bibliotecaSalvata, bibliotecaAttuale);
			// ******** Sistemi di prestito interbibliotecario *****************
			//compareSistemiPrestitoInterbibliotecario(differenze, bibliotecaSalvata, bibliotecaAttuale);
			// ******** Personale *****************
			comparePersonale(differenze, bibliotecaSalvata, bibliotecaAttuale);
			// ******** Informazioni supplementari utenti *****************
			compareInfoSuppUtenti(differenze, bibliotecaSalvata, bibliotecaAttuale);
			// ******** Bilancio *****************
			compareBilancio(differenze, bibliotecaSalvata, bibliotecaAttuale);
			// ******** Deposito legale *****************
			//compareDepositoLegale(differenze, bibliotecaSalvata, bibliotecaAttuale);
			// ******** Note catalogatore *****************
			// non si confrontano
			// ******** Comunicazioni *****************
			// nn si confrontano

		} catch (Exception e) {
			_log.error("Errore nel confrontare la biblioteca dall'XML", e);
			throw e;
		}

		return differenze;
	}


	protected static String siNoBoolean(Boolean test) {
		if (test == null) return "Non specificato";
		if (test) return "Si";
		return "No";
	}
	protected String getLatFromGeo(Geolocalizzazione geo) {
		if (geo == null) return null;
		if (geo.getLatitudine() == null) return null;
		return String.valueOf(geo.getLatitudine());
	}
	protected String getLongFromGeo(Geolocalizzazione geo) {
		if (geo == null) return null;
		if (geo.getLongitudine() == null) return null;
		return String.valueOf(geo.getLongitudine());
	}

	protected void createDifference(List<Differenze> differenze, String property, Object oldvalue, Object newvalue) {
		Vector<String> oldNew = new Vector<String>();
		String v1 = (oldvalue == null) ? "&nbsp;" : oldvalue.toString();
		String v2 = (newvalue == null) ? "&nbsp;" : newvalue.toString();
		oldNew.add(v1);
		oldNew.add(v2);
		Differenze diff = new Differenze(property);
		diff.put(oldNew);
		differenze.add(diff);
	}

	protected void createDifferenceContatti(List<Differenze> differenze, Contatti salvati, Contatti attuale) {
		String salvatoString = "";
		if (salvati != null) {
			salvatoString = salvati.getContattiTipo().getDescrizione().concat(" ").concat(salvati.getValore()).concat(" ").concat(salvati.getNote());	
		}
		String attualeString = "";
		if (attuale != null) {
			attualeString = attuale.getContattiTipo().getDescrizione().concat(" ").concat(attuale.getValore()).concat(" ").concat(attuale.getNote());	
		}
		createDifference(differenze, "Contatto", salvatoString, attualeString);
	}

	protected void createDifferenceDestinazioniSociali(List<Differenze> differenze, DestinazioniSociali salvati, DestinazioniSociali attuale) {
		String salvatoString = "";
		if (salvati != null) {
			salvatoString = salvati.getDestinazioniSocialiTipo().getDescrizione().concat(" ").concat(salvati.getNote());	
		}
		String attualeString = "";
		if (attuale != null) {
			attualeString = attuale.getDestinazioniSocialiTipo().getDescrizione().concat(" ").concat(attuale.getNote());	
		}
		createDifference(differenze, "Destinazione sociale", salvatoString, attualeString);
	}

	protected void createDifferenceRegolamento(List<Differenze> differenze, Regolamento salvati, Regolamento attuale) {
		String salvatoString = "";
		if (salvati != null) {
			salvatoString = salvati.getRiferimentoNormativa().concat(" ").concat(salvati.getUrl());	
		}
		String attualeString = "";
		if (attuale != null) {
			attualeString = attuale.getRiferimentoNormativa().concat(" ").concat(attuale.getUrl());	
		}
		createDifference(differenze, "Regolamento", salvatoString, attualeString);
	}

	protected void createDifferenceOrarioUfficiale(List<Differenze> differenze, OrarioUfficiali salvati, OrarioUfficiali attuale) {
		String salvatoString = "";
		if (salvati != null) {
			salvatoString = formatOrarioUfficiale(salvati);	
		}
		String attualeString = "";
		if (attuale != null) {
			attualeString = formatOrarioUfficiale(attuale);	
		}
		createDifference(differenze, "Orario ufficiale", salvatoString, attualeString);
	}
	protected String formatOrarioUfficiale(OrarioUfficiali of) {
		int giorno = of.getGiorno();
		String alle = DateFormatUtils.format(of.getAlle(), "hh:mm");
		String dalle = DateFormatUtils.format(of.getDalle(), "hh:mm");
		return "giorno ".concat(String.valueOf(giorno)).concat(" dalle ").concat(dalle).concat(" alle ").concat(alle);
	}
	

	protected void createDifferenceDepositiLegali(List<Differenze> differenze, DepositiLegali salvati, DepositiLegali attuale) {
		String salvatoString = "";
		if (salvati != null) {
			salvatoString = salvati.getDaAnno().concat(" ").concat(salvati.getDepositiLegaliTipo().getDescrizione());	
		}
		String attualeString = "";
		if (attuale != null) {
			attualeString = attuale.getDaAnno().concat(" ").concat(attuale.getDepositiLegaliTipo().getDescrizione());	
		}
		createDifference(differenze, "Deposito legale", salvatoString, attualeString);
	}

	protected void createDifferenceRiproduzioni(List<Differenze> differenze, Riproduzioni salvati, Riproduzioni attuale) {
		String salvatoString = "";
		if (salvati != null) {
			salvatoString = "Internazionale:".concat(siNoBoolean(salvati.getInternazionale())).concat(" ").
			concat("Nazionale:").concat(siNoBoolean(salvati.getNazionale())).concat(" ").
			concat("Locale:").concat(siNoBoolean(salvati.getLocale())).concat(" ").
			concat("Tipo:").concat(salvati.getRiproduzioniTipo().getDescrizione());
		}

		String attualeString = "";
		if (attuale != null) {
			attualeString = "Internazionale:".concat(siNoBoolean(attuale.getInternazionale())).concat(" ").
			concat("Nazionale:").concat(siNoBoolean(attuale.getNazionale())).concat(" ").
			concat("Locale:").concat(siNoBoolean(attuale.getLocale())).concat(" ").
			concat("Tipo:").concat(attuale.getRiproduzioniTipo().getDescrizione());
		}
		createDifference(differenze, "Riproduzioni", salvatoString, attualeString);
	}

	protected void createDifferencePatrimonio(List<Differenze> differenze, Patrimonio salvati, Patrimonio attuale) {
		String salvatoString = "";
		if (salvati != null) {
			salvatoString = "Quantità:".concat(salvati.getQuantita().toString()).concat(" ").
			concat("Quantita Ultimo Anno:").concat(salvati.getQuantitaUltimoAnno().toString()).concat(" ").
			concat("Patrimonio Specializzazione:").concat(salvati.getPatrimonioSpecializzazione().getDescrizione());
		}

		String attualeString = "";
		if (attuale != null) {
			attualeString = "Quantità:".concat(attuale.getQuantita().toString()).concat(" ").
			concat("Quantita Ultimo Anno:").concat(attuale.getQuantitaUltimoAnno().toString()).concat(" ").
			concat("Patrimonio Specializzazione:").concat(attuale.getPatrimonioSpecializzazione().getDescrizione());
		}
		createDifference(differenze, "Patrimonio", salvatoString, attualeString);
	}
	
	
}