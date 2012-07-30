package it.inera.abi.gxt.client.mvc.model;

import it.inera.abi.gxt.client.costants.CostantiGestioneBiblio;

import java.io.Serializable;
import java.util.Date;

import com.extjs.gxt.ui.client.data.BaseModel;

public class BiblioModel extends BaseModel implements Serializable {

	public static final String IDBIBLIO = "idbiblio";
	public static final String READONLY = "readOnly";
	public static final String REVISIONE = "revisione";
	
	/**
	 * Variabili per serializzazione
	 */
	ComuniModel c;
	EnteModel e;
	VoceUnicaModel v;
	RegolamentoModel r;
	private static final long serialVersionUID = 7928291626880619822L;

	public BiblioModel() {

	}

	public BiblioModel(String codice, String denominazione, String indirizzo,
			ComuniModel comune, String utenteUltimaModifica,
			String statoCatalogazione) {

		set("codice", codice);
		set("denominazione", denominazione);
		set("indirizzo", indirizzo);
		set("comune", comune);
		set("comuneDenominazione",
				(comune == null ? "" : comune.getDenominazione()));
		set("utenteUltimaModifica", utenteUltimaModifica);
		set("statoCatalogazione", statoCatalogazione);
	}

	public BiblioModel(int idbiblio, String codice, String denominazione,
			String indirizzo, ComuniModel comune, String utenteUltimaModifica,
			String statoCatalogazione, EnteModel ente) {
		set("idbiblio", idbiblio);
		set("codice", codice);
		set("denominazione", denominazione);
		set("indirizzo", indirizzo);
		set("comune", comune);
		set("utenteUltimaModifica", utenteUltimaModifica);
		set("statoCatalogazione", statoCatalogazione);
		set("comuneDenominazione",
				(comune == null ? "" : comune.getDenominazione()));
		set("ente", ente);
	}

	public BiblioModel(int idbiblio, String codice, String denominazione,
			String indirizzo, ComuniModel comune, String utenteUltimaModifica,
			String statoCatalogazione, String frazione, String cap,
			String provincia, String regione, String stato, double geoX,
			double geoY, Boolean autonomiaAmministrativa,
			String struttura_gerarchica_sovraordinata,
			VoceUnicaModel tipologiaFunzionale, String dataFondazione,
			String dataIstituzione, Boolean accessoRiservato,
			Integer limiteEtaMin, Integer limiteEtaMax,Boolean inventarioInformatizzato,Boolean inventarioCartaceo,Boolean catalogoInformatizzato,Boolean catalogoCartaceo) {
		set("idbiblio", idbiblio);
		set("codice", codice);
		set("denominazione", denominazione);
		set("indirizzo", indirizzo);
		set("comune", comune);
		set("comuneDenominazione",
				(comune == null ? "" : comune.getDenominazione()));
		set("utenteUltimaModifica", utenteUltimaModifica);
		set("statoCatalogazione", statoCatalogazione);
		set("frazione", frazione);
		set("cap", cap);
		set("provincia", provincia);
		set("regione", regione);
		set("stato", stato);
		set("geoX", geoX);
		set("geoY", geoY);
		set("autonomiaAmministrativa", autonomiaAmministrativa);
		set("struttura_gerarchica_sovraordinata",
				struttura_gerarchica_sovraordinata);
		set("tipologiaFunzionale", tipologiaFunzionale);
		set("dataFondazione", dataFondazione);
		set("dataIstituzione", dataIstituzione);
		set("accessoRiservato", accessoRiservato);

		set("limiteEtaMin", limiteEtaMin);
		set("limiteEtaMax", limiteEtaMax);
		set("inventarioInformatizzato", inventarioInformatizzato);
		set("inventarioCartaceo", inventarioCartaceo);
		set("catalogoCartaceo", catalogoCartaceo);
		set("catalogoInformatizzato", catalogoInformatizzato);
	}
	
	public void setAttivoPrestitoLocale(Boolean attivoPrestitoLocale) {
		set("attivoPrestitoLocale", attivoPrestitoLocale);
	}
	
	public Boolean getAttivoPrestitoLocale() {
		return get("attivoPrestitoLocale");
	}

	public void setAttivoRiproduzioni(Boolean attivoRiproduzioni) {
		set("attivoRiproduzioni", attivoRiproduzioni);
	}
	
	public Boolean getAttivoRiproduzioni() {
		return get("attivoRiproduzioni");
	}
	
	public void setLimiteEtaMin(Integer limiteEtaMin) {
		set("limiteEtaMin", limiteEtaMin);
	}

	public Integer getLimiteEtaMin() {
		return get("limiteEtaMin");
	}

	public void setLimiteEtaMax(Integer limiteEtaMax) {
		set("limiteEtaMax", limiteEtaMax);
	}

	public Integer getLimiteEtaMax() {
		return get("limiteEtaMax");
	}

	public void setAutonomiaAmministrativa(Boolean autonomiaAmministrativa) {
		set("autonomiaAmministrativa", autonomiaAmministrativa);
	}

	public Boolean getAutonomiaAmministrativa() {

		return get("autonomiaAmministrativa");
	}

	public void setAccessoRiservato(Boolean accessoRiservato) {
		set("accessoRiservato", accessoRiservato);
	}

	public Boolean getAccessoRiservato() {

		return get("accessoRiservato");
	}

	public void setStruttura_gerarchica_sovraordinata(
			String struttura_gerarchica_sovraordinata) {
		set("struttura_gerarchica_sovraordinata",
				struttura_gerarchica_sovraordinata);
	}

	public String getStruttura_gerarchica_sovraordinata() {
		return get("struttura_gerarchica_sovraordinata");
	}

	public void setGeoX(Double geoX) {
		set("geoX", geoX);
	}

	public void setGeoY(Double geoY) {
		set("geoY", geoY);
	}

	public Double getGeoX() {
		return get("geoX");
	}

	public Double getGeoY() {
		return get("geoY");
	}

	public void setIdBiblio(int idbiblio) {
		set("idbiblio", idbiblio);
	}

	public int getIdBiblio() {
		return (Integer) get("idbiblio");
	}

	public String getStato() {
		return get("stato");
	}

	public String getStatoCatalogazione() {
		return get("statoCatalogazione");
	}

	public String getCodice() {
		return get("codice");
	}

	public String getDenominazione() {
		return get("denominazione");
	}

	public String getIndirizzo() {
		return get("indirizzo");
	}

	public ComuniModel getComune() {
		return get("comune");
	}

	public String getComuneDenominazione() {

		return get("comuneDenominazione");
	}

	public void setComuneDenominazione(ComuniModel comune) {

		set("comuneDenominazione", comune.getDenominazione());
	}

	public String getUtenteUltimaModifica() {
		return get("utenteUltimaModifica");
	}

	public String getProvincia() {
		return get("provincia");
	}

	public String getRegione() {
		return get("regione");
	}

	public String getFrazione() {
		return get("frazione");
	}

	public String getCap() {
		return get("cap");
	}

	/**/
	public void setStato(String stato) {
		set("stato", stato);
	}

	public void setStatoCatalogazione(String statoCatalogazione) {
		set("statoCatalogazione", statoCatalogazione);
	}

	public void setCodice(String codice) {
		set("codice", codice);
	}

	public void setDenominazione(String denominazione) {
		set("denominazione", denominazione);
	}

	public void setIndirizzo(String indirizzo) {
		set("indirizzo", indirizzo);
	}

	public void setComune(ComuniModel comune) {
		set("comune", comune);
	}

	public void setUtenteUltimaModifica(String utenteUltimaModifica) {
		set("utenteUltimaModifica", utenteUltimaModifica);
	}

	public void setProvincia(String provincia) {
		set("provincia", provincia);
	}

	public void setRegione(String regione) {
		set("regione", regione);
	}

	public void setFrazione(String frazione) {
		set("frazione", frazione);
	}

	public void setCap(String cap) {
		set("cap", cap);
	}

	public void setIsilStato(String isil_stato) {
		set("isil_stato", isil_stato);
	}

	public String getIsilStato() {
		return get("isil_stato");
	}

	public void setIsilProvincia(String isil_provincia) {
		set("isil_provincia", isil_provincia);
	}

	public String getIsilProvincia() {
		return get("isil_provincia");
	}

	public void setIsilNumero(String isil_numero) {
		set("isil_numero", isil_numero);
	}

	public String getIsilNumero() {
		return get("isil_numero");
	}

	public void isDecentrato(boolean is_decentrato) {
		set("is_decentrato", is_decentrato);
	}

	public boolean isDecentrato() {
		return (Boolean) get("is_decentrato");
	}

	public void setIsEdificioMonumentale(Boolean value) {
		set("edificioMonumentale", value);
	}

	public Boolean getIsEdificioMonumentale() {
		return get("edificioMonumentale");
	}

	public void setIsAppositamenteCostruito(Boolean value) {
		set("appositamenteCostruito", value);
	}

	public Boolean getIsAppositamenteCostruito() {
		return get("appositamenteCostruito");
	}

	public void setAccessoHandicap(Boolean value) {
		set("accessoHandicap", value);
	}

	public Boolean getAccessoHandicap() {
		return get("accessoHandicap");
	}

	public void setdenominazioneEdificio(String denominazioneEdificio) {
		set("denominazioneEdificio", denominazioneEdificio);
	}

	public String getdenominazioneEdificio() {
		return get("denominazioneEdificio");
	}

	public void setEdificioDataCostruzione(String edificioDataCostruzione) {
		set("edificioDataCostruzione", edificioDataCostruzione);
	}

	public String getEdificioDataCostruzione() {
		return get("edificioDataCostruzione");
	}

	public void setMqSupBilbio(int mqSupBiblio) {
		set("mqSupBiblio", mqSupBiblio);
	}

	public int getMqSupBiblio() {
		return (Integer)get("mqSupBiblio");
	}

	public void setMqPubblici(int mqPubblici) {
		set("mqPubblici", mqPubblici);
	}

	public int getMqPubblici() {
		return (Integer) get("mqPubblici");
	}

	public void setMqMagazzini(int mqMagazzini) {
		set("mqMagazzini", mqMagazzini);
	}

	public int getMqMagazzini() {
		return (Integer) get("mqMagazzini");
	}

	public void setMqAperti(int mqAperti) {
		set("mqAperti", mqAperti);
	}

	public int getMqAperti() {
		return (Integer) get("mqAperti");
	}

	public void setPostiLettura(int postiLettura) {
		set("postiLettura", postiLettura);
	}

	public int getPostiLettura() {
		return (Integer) get("postiLettura");
	}

	public void setPostiVideo(int postiVideo) {
		set("postiVideo", postiVideo);
	}

	public int getPostiVideo() {
		return (Integer) get("postiVideo");
	}

	public int getPostiAscolto() {
		return (Integer) get("postiAscolto");
	}

	public void setPostiAscolto(int postiAscolto) {
		set("postiAscolto", postiAscolto);
	}

	public int getPostiInternet() {
		return (Integer) get("postiInternet");
	}

	public void setPostiInternet(int postiInternet) {
		set("postiInternet", postiInternet);
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

	public void setRegolamento(RegolamentoModel regolamento) {
		set("regolamento", regolamento);
	}

	public RegolamentoModel getRegolamento() {
		return get("regolamento");
	}

	public void setInventarioCartaceo(Boolean inventarioCartaceo) {
		set("inventarioCartaceo", inventarioCartaceo);
	}

	public Boolean getInventarioCartaceo() {
		return get("inventarioCartaceo");
	}

	public void setInventarioInformatizzato(Boolean inventarioInformatizzato) {
		set("inventarioInformatizzato", inventarioInformatizzato);
	}

	public Boolean getInventarioInformatizzato() {
		return get("inventarioInformatizzato");
	}

	/**/
	public void setCatalogoCartaceo(Boolean catalogoCartaceo) {
		set("catalogoCartaceo", catalogoCartaceo);
	}

	public Boolean getCatalogoCartaceo() {
		return get("catalogoCartaceo");
	}

	public void setCatalogoInformatizzato(Boolean catalogoInformatizzato) {
		
		set("catalogoInformatizzato", catalogoInformatizzato);
	}

	public Boolean getCatalogoInformatizzato() {
		return get("catalogoInformatizzato");
	}

	public void setFondiAntichiFinoAl1830(int id_fondiAntichi1830) {
		set("fondiAntichi1830",	getNumeroFondiAntichi1830byId(id_fondiAntichi1830));
	}
	
	public String getFondiAntichiFinoAl1830() {
		return	get("fondiAntichi1830");
	}
	
	public void setBibliografia(String bibliografia){
		set("bibliografia",bibliografia);
	}
	
	public String getBibliografia(){
	return	get("bibliografia");
	}
	
	public void setAttivoInformazioniBibliografiche(Boolean attivoInformazioniBibliografiche) {
		set("attivoInformazioniBibliografiche", attivoInformazioniBibliografiche);
	}
	
	public Boolean getAttivoInformazioniBibliografiche() {
		return get("attivoInformazioniBibliografiche");
	}

	public void setServizioBibliograficoEsterno(Boolean servizioBibliograficoEsterno){
		set("servizioBibliograficoEsterno",servizioBibliograficoEsterno);
	}
	
	public Boolean getServizioBibliograficoEsterno(){
		return	get("servizioBibliograficoEsterno");
	}
	
	public void setServizioBibliograficoInterno(Boolean servizioBibliograficoInterno){
		set("servizioBibliograficoInterno",servizioBibliograficoInterno);
	}
	
	public Boolean getServizioBibliograficoInterno(){
		return	get("servizioBibliograficoInterno");
	}
	
	public void setAttivoAccessoInternet(Boolean attivoAccessoInternet) {
		set("attivoAccessoInternet", attivoAccessoInternet);
	}
	
	public Boolean getAttivoAccessoInternet() {
		return get("attivoAccessoInternet");
	}
	
	public void setAccessoInternetAPagamento(String accessoInternetAPagamento){
		set("accessoInternetAPagamento",accessoInternetAPagamento);
	}
	
	public String getAccessoInternetAPagamento(){
		return get("accessoInternetAPagamento");
	}
	
	public void setAccessoInternetATempo(String accessoInternetATempo){
		set("accessoInternetATempo",accessoInternetATempo);
	}
	
	public String getAccessoInternetATempo(){
		return get("accessoInternetATempo");
	}
	
	public void setAccessoInternetProxy(String accessoInternetAProxy){
		set("accessoInternetAProxy",accessoInternetAProxy);
	}
	
	public String getAccessoInternetAProxy(){
		return get("accessoInternetAProxy");
	}
	
	
	public void setPrestitoNazionale(Boolean prestitoNazionale){
		set("prestitoNazionale",prestitoNazionale);
	}
	
	public Boolean getPrestitoNazionale(){
	return	get("prestitoNazionale");
	}
	
	
	public void setPrestitoInternazionale(Boolean prestitoInternazionale){
		set("prestitoInternazionale",prestitoInternazionale);
	}
	
	public Boolean getPrestitoInternazionale(){
	return	get("prestitoInternazionale");
	}
	
	public void setProcedureAutomatizzate(Boolean procedureAutomatizzate){
		set("procedureAutomatizzate",procedureAutomatizzate);
	}
	
	public Boolean getProcedureAutomatizzate(){
	return	get("procedureAutomatizzate");
	}
	
	public void setPersonaleTotale(Integer personaleTotale){
		set("personaleTotale",personaleTotale);
	}public Integer getPersonaleTotale(){
		return get("personaleTotale");
	}
	
	public void setPersonalePartTime(Integer personalePartTime){
		set("personalePartTime",personalePartTime);
	}public Integer getPersonalePartTime(){
		return get("personalePartTime");
	}
	
	public void setPersonaleTemporaneo(Integer personaleTemporaneo){
		set("personaleTemporaneo",personaleTemporaneo);
	}public Integer getPersonaleTemporaneo(){
		return get("personaleTemporaneo");
	}
	
	public void setPersonaleEsterno(Integer personaleEsterno){
		set("personaleEsterno",personaleEsterno);
	}public Integer getPersonaleEsterno(){
		return get("personaleEsterno");
	}
	
	public void setIngressiUltimi12Mesi(Integer ingressiUltimi12Mesi){
		set("ingressiUltimi12Mesi",ingressiUltimi12Mesi);
	}
	
	public Integer getIngressiUltimi12Mesi(){
		return get("ingressiUltimi12Mesi");
	}
	
	public void setIscrittiPrestitoUltimi12Mesi(Integer iscrittiPrestitoUltimi12Mesi){
		set("iscrittiPrestitoUltimi12Mesi",iscrittiPrestitoUltimi12Mesi);
	}
	
	public Integer getIscrittiPrestitoUltimi12Mesi(){
		return get("iscrittiPrestitoUltimi12Mesi");
	}
	
	public void setUtentiIscritti(Integer utentiIscritti){
		set("utentiIscritti",utentiIscritti);
	}
	
	public Integer getUtentiIscrittii(){
		return get("utentiIscritti");
	}
	
	public void setUsciteTotali(Integer usciteTotali){
		set("usciteTotali",usciteTotali);
	}
	
	public Integer getUsciteTotali(){
		return get("usciteTotali");
	}
	
	public void setUscitePersonale(Integer uscitePersonale){
		set("uscitePersonale",uscitePersonale);
	}
	
	public Integer getUscitePersonale(){
		return get("uscitePersonale");
	}
	
	public void setUsciteFunzionamento(Integer usciteFunzionamento){
		set("usciteFunzionamento",usciteFunzionamento);
	}
	
	public Integer getUsciteFunzionamento(){
		return get("usciteFunzionamento");
	}
	
	public void setUsciteAcquistoPatrimonio(Integer usciteAcquistoPatrimonio){
		set("usciteAcquistoPatrimonio",usciteAcquistoPatrimonio);
	}
	
	public Integer getUsciteAcquistoPatrimonio(){
		return get("usciteAcquistoPatrimonio");
	}
	
	public void setUsciteAutomazione(Integer usciteAutomazione){
		set("usciteAutomazione",usciteAutomazione);
	}
	
	public Integer getUsciteAutomazione(){
		return get("usciteAutomazione");
	}
	
	public void setUsciteAltro(Integer usciteAltro){
		set("usciteAltro",usciteAltro);
	}
	
	public Integer getUsciteAltro(){
		return get("usciteAltro");
	}
	
	public void setEntrateTotali(Integer entrateTotali){
		set("entrateTotali",entrateTotali);
	}
	
	public Integer getEntrateTotali(){
		return get("entrateTotali");
	}

	public void setDepositoLegale(int depositoLegale){
		set("depositoLegale",depositoLegale);
	}

	public int getDepositoLegale(){
		return	(Integer) get("depositoLegale");
	}
	
	public void setDepositoAnno(Integer depositoAnno){
		set("depositoAnno",depositoAnno);
	}
	
	public Integer getDepositoAnno(){
		return get("depositoAnno");
	}
	
	private String getNumeroFondiAntichi1830byId(int id_fondiAntichi1830) {
		String fondiAntichi1830 = null;

		switch (id_fondiAntichi1830) {
		case 1: {
			fondiAntichi1830 = CostantiGestioneBiblio.NON_SPECIFICATO;
			break;
		}
		case 2: {
			fondiAntichi1830 = CostantiGestioneBiblio.FONDI_ANTICHI_FINO_1000_VOLUMI;
			break;
		}

		case 3: {
			fondiAntichi1830 = CostantiGestioneBiblio.FONDI_ANTICHI_1000_5000_VOLUMI;
			break;
		}

		case 4: {
			fondiAntichi1830 = CostantiGestioneBiblio.FONDI_ANTICHI_OLTRE_5000_VOLUMI;
			break;
		}

		}
		return fondiAntichi1830;
	}
	
	public static int getIdbyNumeroFondiAntichi1830(String str){

		if (str.equalsIgnoreCase(CostantiGestioneBiblio.FONDI_ANTICHI_FINO_1000_VOLUMI))
			return 2;
		else if (str.equalsIgnoreCase(CostantiGestioneBiblio.FONDI_ANTICHI_1000_5000_VOLUMI))
			return 3;
		else if (str.equalsIgnoreCase(CostantiGestioneBiblio.FONDI_ANTICHI_OLTRE_5000_VOLUMI))
			return 4;
		else return 1;//Non specificato
	}

	public void setNoteCatalogatore(String catalogazioneNote) {
		set("catalogazioneNote",catalogazioneNote);
		
	}
	public String getNoteCatalogatore() {
		return get("catalogazioneNote");
		
	}

	public void setComunicazioni(String comunicazioni) {
		set("comunicazioni",comunicazioni);
		
	}
	
	public String getComunicazioni() {
		return get("comunicazioni");
		
	}

	public void setCatalogazioneDataModifica(Date catalogazioneDataModifica) {
		set("catalogazioneDataModifica", catalogazioneDataModifica);
	}
	
	public Date getCatalogazioneDataModifica() {
		return get("catalogazioneDataModifica");
	}
	
	public void setDataCensimento(String dataCensimento){
		set("dataCensimento",dataCensimento);
	}
	
	public String getDataCensimento(){
		return get("dataCensimento");
	}
	
	public void setDataImport(String dataImport){
		set("dataImport",dataImport);
	}
	
	public String getDataImport(){
		return get("dataImport");
	}
	
	public void setDataModificaRemota(String dataModificaRemota){
		set("dataModificaRemota",dataModificaRemota);
	}
	
	public String getDataModificaRemota(){
		return get("dataModificaRemota");
	}
	
	public void setDataModifica(String dataModifica){
		set("dataModifica",dataModifica);
	}
	
	public String getDataModifica(){
		return get("dataModifica");
	}
	
	public void setCodiceFiscale(String codiceFiscale){
		set("codiceFiscale",codiceFiscale);
	}
	
	public void setPartitaIva(String partitaIva){
		set("partitaIva",partitaIva);
	}
	public void setSBN(String sbn){
		set("sbn",sbn);
	}
	public void setRISM(String rism){
		set("rism",rism);
	}
	public void setACNP(String acnp){
		set("acnp",acnp);
	}
	public void setCEI(String cei){
		set("cei",cei);
	}
	public void setCMBS(String cmbs){
		set("cmbs",cmbs);
	}
	
	
	public String getCodiceFiscale(){
		return get("codiceFiscale");
	}
	
	public String getPartitaIva(){
		return get("partitaIva");
	}
	public String getSBN(){
		return get("sbn");
	}
	public String getRISM(){
		return get("rism");
	}
	public String getACNP(){
		return get("acnp");
	}
	public String getCEI(){
		return get("cei");
	}
	public String getCMBS(){
		return get("cmbs");
	}
	
	public void setStatoCatalogazioneModel(VoceUnicaModel statoCatalogazioneModel){
		set("statoCatalogazioneModel", statoCatalogazioneModel);
	}
	
	public VoceUnicaModel getStatoCatalogazioneModel(){
		return get("statoCatalogazioneModel");
	}
	
}
