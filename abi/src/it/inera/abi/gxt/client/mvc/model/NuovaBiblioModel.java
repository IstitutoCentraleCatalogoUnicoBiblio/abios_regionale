package it.inera.abi.gxt.client.mvc.model;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 * Modello utilizzato per i dati di una nuova biblioteca
 *
 */
public class NuovaBiblioModel extends BaseModel implements Serializable {
	public static final String IDBIBLIO = "idbiblio";
	public static final String READONLY = "readOnly";
	
	private static final long serialVersionUID = -6446621451322517579L;
	
	ComuniModel c;
	EnteModel e;
	VoceUnicaModel v;
	
	public NuovaBiblioModel() {
		
	}
	
	public int getIdBiblio() {
		return (Integer) get("idbiblio");
	}
	
	public void setIdBiblio(int idbiblio) {
		set("idbiblio", idbiblio);
	}

	public Boolean getAccessoHandicap() {
		return get("accessoHandicap");
	}
	
	public void setAccessoHandicap(Boolean accessoHandicap) {
		set("accessoHandicap", accessoHandicap);
	}
	
	public Boolean getAccessoInternetPagamento() {
		return get("accessoInternetPagamento");
	}
	
	public void setAccessoInternetPagamento(Boolean accessoInternetPagamento) {
		set("accessoInternetPagamento", accessoInternetPagamento);
	}
	
	public Boolean getAccessoInternetProxy() {
		return get("accessoInternetProxy");
	}
	
	public void setAccessoInternetProxy(Boolean accessoInternetProxy) {
		set("accessoInternetProxy", accessoInternetProxy);
	}
	
	public Boolean getAccessoInternetTempo() {
		return get("accessoInternetTempo");
	}
	
	public void setAccessoInternetTempo(Boolean accessoInternetTempo) {
		set("accessoInternetTempo", accessoInternetTempo);
	}
	
	public Boolean getAccessoRiservato() {
		return get("accessoRiservato");
	}
	
	public void setAccessoRiservato(Boolean accessoRiservato) {
		set("accessoRiservato", accessoRiservato);
	}
	
	public Boolean getAutonomiaAmministrativa() {
		return get("autonomiaAmministrativa");
	}
	
	public void setAutonomiaAmministrativa(Boolean autonomiaAmministrativa) {
		set("autonomiaAmministrativa", autonomiaAmministrativa);
	}
	
	public Boolean getCatalogoTopograficoCartaceo() {
		return get("catalogoTopograficoCartaceo");
	}
	
	public void setCatalogoTopograficoCartaceo(Boolean catalogoTopograficoCartaceo) {
		set("catalogoTopograficoCartaceo", catalogoTopograficoCartaceo);
	}
	
	public Boolean getCatalogoTopograficoInformatizzato() {
		return get("catalogoTopograficoInformatizzato");
	}
	
	public void setCatalogoTopograficoInformatizzato(Boolean catalogoTopograficoInformatizzato) {
		set("catalogoTopograficoInformatizzato", catalogoTopograficoInformatizzato);
	}
	
	public String getDataFondazione() {
		return get("dataFondazione");
	}
	
	public void setDataFondazione(String dataFondazione) {
		set("dataFondazione", dataFondazione);
	}
	
	public String getDataIstituzione() {
		return get("dataIstituzione");
	}
	
	public void setDataIstituzione(String dataIstituzione) {
		set("dataIstituzione", dataIstituzione);
	}
	
	public String getDenominazioneUfficiale() {
		return get("denominazioneUfficiale");
	}
	
	public void setDenominazioneUfficiale(String denominazioneUfficiale) {
		set("denominazioneUfficiale", denominazioneUfficiale);
	}
	
	public Boolean getEdificioAppositamenteCostruito() {
		return get("edificioAppositamenteCostruito");
	}
	
	public void setEdificioAppositamenteCostruito(Boolean edificioAppositamenteCostruito) {
		set("edificioAppositamenteCostruito", edificioAppositamenteCostruito);
	}
	
	public String getEdificioDataCostruzione() {
		return get("edificioDataCostruzione");
	}
	
	public void setEdificioDataCostruzione(String edificioDataCostruzione) {
		set("edificioDataCostruzione", edificioDataCostruzione);
	}
	
	public String getEdificioDenominazione() {
		return get("edificioDenominazione");
	}
	
	public void setEdificioDenominazione(String edificioDenominazione) {
		set("edificioDenominazione", edificioDenominazione);
	}
	
	public Boolean getEdificioMonumentale() {
		return get("edificioMonumentale");
	}
	
	public void setEdificioMonumentale(Boolean edificioMonumentale) {
		set("edificioMonumentale", edificioMonumentale);
	}
	
	public Boolean getGestisceServizioBibliograficoEsterno() {
		return get("gestisceServizioBibliograficoEsterno");
	}
	
	public void setGestisceServizioBibliograficoEsterno(Boolean gestisceServizioBibliograficoEsterno) {
		set("gestisceServizioBibliograficoEsterno", gestisceServizioBibliograficoEsterno);
	}
	
	public Boolean getGestisceServizioBibliograficoInterno() {
		return get("gestisceServizioBibliograficoInterno");
	}
	
	public void setGestisceServizioBibliograficoInterno(Boolean gestisceServizioBibliograficoInterno) {
		set("gestisceServizioBibliograficoInterno", gestisceServizioBibliograficoInterno);
	}
	
	public Boolean getInventarioCartaceo() {
		return get("inventarioCartaceo");
	}
	
	public void setInventarioCartaceo(Boolean inventarioCartaceo) {
		set("inventarioCartaceo", inventarioCartaceo);
	}
	
	public Boolean getInventarioInformatizzato() {
		return get("inventarioInformatizzato");
	}
	
	public void setInventarioInformatizzato(Boolean inventarioInformatizzato) {
		set("inventarioInformatizzato", inventarioInformatizzato);
	}
	
	public Integer getIsilNumero() {
		return get("isilNumero");
	}
	
	public void setIsilNumero(Boolean isilNumero) {
		set("isilNumero", isilNumero);
	}
	
	public String getIsilProvincia() {
		return get("isilProvincia");
	}
	
	public void setIsilProvincia(String isilProvincia) {
		set("isilProvincia", isilProvincia);
	}
	
	public String getIsilStato() {
		return get("isilStato");
	}
	
	public void setIsilStato(String isilStato) {
		set("isilStato", isilStato);
	}
	
	public ComuniModel getComune() {
		return get("comune");
	}
	
	public void setComune(ComuniModel comune) {
		set("comune", comune);
	}

	public String getComuneDenominazione() {
		return get("comuneDenominazione");
	}

	public void setComuneDenominazione(ComuniModel comune) {
		set("comuneDenominazione", comune.getDenominazione());
	}
	
	public void setEnte(EnteModel ente) {
		set("ente", ente);
	}

	public EnteModel getEnte() {
		return get("ente");
	}
	
	public VoceUnicaModel getTipologiaFunzionale() {
		return get("tipologiaFunzionale");

	}

	public void setTipologiaFunzionale(VoceUnicaModel tipologiaFunzionale) {
		set("tipologiaFunzionale", tipologiaFunzionale);

	}
	
	public String getStatoBibliotecaWorkflow() {
		return get("statoBibliotecaWorkflow");
	}
	
	public void setStatoBibliotecaWorkflow(String stato) {
		set("statoBibliotecaWorkflow", stato);
	}
	
	public String getLoginCreatore() {
		return get("loginCreatore");
	}
	
	public void setLoginCreatore(String loginCreatore) {
		set("loginCreatore", loginCreatore);
	}
	
	public String getLoginGestore() {
		return get("loginGestore");
	}
	
	public void setLoginGestore(String loginGestore) {
		set("loginGestore", loginGestore);
	}
	public String getEnteDenominazione() {
		return get("enteDenominazione");
	}
	
	public void setEnteDenominazione(String enteDen) {
		set("enteDenominazione", enteDen);
	}
	
	public String getEnteTipologiaAmministrativa() {
		return get("enteTipologiaAmministrativa");
	}
	
	public void setEnteTipologiaAmministrativa(String enteTipAmm) {
		set("enteTipologiaAmministrativa", enteTipAmm);
	}
	
	public String getEnteStato() {
		return get("enteStato");
	}
	
	public void setEnteStato(String enteSt) {
		set("enteStato", enteSt);
	}
	
}
