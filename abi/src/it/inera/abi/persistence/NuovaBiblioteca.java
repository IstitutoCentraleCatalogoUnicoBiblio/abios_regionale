package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the nuova_biblioteca database table.
 * 
 */
@Entity
@Table(name="nuova_biblioteca")
public class NuovaBiblioteca implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_biblioteca", unique=true, nullable=false)
	private Integer idBiblioteca;

	@Column(name="accesso_handicap")
	private Boolean accessoHandicap;

	@Column(name="accesso_internet_pagamento")
	private Boolean accessoInternetPagamento;

	@Column(name="accesso_internet_proxy")
	private Boolean accessoInternetProxy;

	@Column(name="accesso_internet_tempo")
	private Boolean accessoInternetTempo;

	@Column(name="accesso_riservato")
	private Boolean accessoRiservato;

	@Column(name="autonomia_amministrativa")
	private Boolean autonomiaAmministrativa;

	@Column(name="catalogo_topografico_cartaceo")
	private Boolean catalogoTopograficoCartaceo;

	@Column(name="catalogo_topografico_informatizzato")
	private Boolean catalogoTopograficoInformatizzato;

	@Column(name="data_fondazione", length=20)
	private String dataFondazione;

	@Column(name="denominazione_ufficiale", length=255)
	private String denominazioneUfficiale;

	@Column(name="edificio_appositamente_costruito")
	private Boolean edificioAppositamenteCostruito;

	@Column(name="edificio_data_costruzione", length=255)
	private String edificioDataCostruzione;

	@Column(name="edificio_monumentale")
	private Boolean edificioMonumentale;

	@Column(name="ente_denominazione", length=255)
	private String enteDenominazione;

	@Column(name="ente_stato", length=255)
	private String enteStato;

	@Column(name="ente_tipologia_amministrativa", length=255)
	private String enteTipologiaAmministrativa;

	@Column(name="gestisce_servizio_bibliografico_esterno")
	private Boolean gestisceServizioBibliograficoEsterno;

	@Column(name="gestisce_servizio_bibliografico_interno")
	private Boolean gestisceServizioBibliograficoInterno;

	@Column(name="inventario_cartaceo")
	private Boolean inventarioCartaceo;

	@Column(name="inventario_informatizzato")
	private Boolean inventarioInformatizzato;

	@Column(name="isil_numero")
	private Integer isilNumero;

	@Column(name="isil_provincia", nullable=false, length=2)
	private String isilProvincia;

	@Column(name="isil_stato", nullable=false, length=2)
	private String isilStato;

	//uni-directional many-to-one association to Comune
    @ManyToOne
	@JoinColumn(name="id_comune")
	private Comune comune;

	//uni-directional many-to-one association to StatoBibliotecaWorkflow
    @ManyToOne
	@JoinColumn(name="id_stato_biblioteca_workflow", nullable=false)
	private StatoBibliotecaWorkflow statoBibliotecaWorkflow;

	//uni-directional many-to-one association to TipologiaFunzionale
    @ManyToOne
	@JoinColumn(name="id_tipologia_funzionale")
	private TipologiaFunzionale tipologiaFunzionale;

	//bi-directional many-to-one association to Utenti
    @ManyToOne
	@JoinColumn(name="login_gestore")
	private Utenti utenti_gestore;

	//bi-directional many-to-one association to Utenti
    @ManyToOne
	@JoinColumn(name="login_creatore")
	private Utenti utenti_creatore;

    public NuovaBiblioteca() {
    }

	public Integer getIdBiblioteca() {
		return this.idBiblioteca;
	}

	public void setIdBiblioteca(Integer idBiblioteca) {
		this.idBiblioteca = idBiblioteca;
	}

	public Boolean getAccessoHandicap() {
		return this.accessoHandicap;
	}

	public void setAccessoHandicap(Boolean accessoHandicap) {
		this.accessoHandicap = accessoHandicap;
	}

	public Boolean getAccessoInternetPagamento() {
		return this.accessoInternetPagamento;
	}

	public void setAccessoInternetPagamento(Boolean accessoInternetPagamento) {
		this.accessoInternetPagamento = accessoInternetPagamento;
	}

	public Boolean getAccessoInternetProxy() {
		return this.accessoInternetProxy;
	}

	public void setAccessoInternetProxy(Boolean accessoInternetProxy) {
		this.accessoInternetProxy = accessoInternetProxy;
	}

	public Boolean getAccessoInternetTempo() {
		return this.accessoInternetTempo;
	}

	public void setAccessoInternetTempo(Boolean accessoInternetTempo) {
		this.accessoInternetTempo = accessoInternetTempo;
	}

	public Boolean getAccessoRiservato() {
		return this.accessoRiservato;
	}

	public void setAccessoRiservato(Boolean accessoRiservato) {
		this.accessoRiservato = accessoRiservato;
	}

	public Boolean getAutonomiaAmministrativa() {
		return this.autonomiaAmministrativa;
	}

	public void setAutonomiaAmministrativa(Boolean autonomiaAmministrativa) {
		this.autonomiaAmministrativa = autonomiaAmministrativa;
	}

	public Boolean getCatalogoTopograficoCartaceo() {
		return this.catalogoTopograficoCartaceo;
	}

	public void setCatalogoTopograficoCartaceo(Boolean catalogoTopograficoCartaceo) {
		this.catalogoTopograficoCartaceo = catalogoTopograficoCartaceo;
	}

	public Boolean getCatalogoTopograficoInformatizzato() {
		return this.catalogoTopograficoInformatizzato;
	}

	public void setCatalogoTopograficoInformatizzato(Boolean catalogoTopograficoInformatizzato) {
		this.catalogoTopograficoInformatizzato = catalogoTopograficoInformatizzato;
	}

	public String getDataFondazione() {
		return this.dataFondazione;
	}

	public void setDataFondazione(String dataFondazione) {
		this.dataFondazione = dataFondazione;
	}

	public String getDenominazioneUfficiale() {
		return this.denominazioneUfficiale;
	}

	public void setDenominazioneUfficiale(String denominazioneUfficiale) {
		this.denominazioneUfficiale = denominazioneUfficiale;
	}

	public Boolean getEdificioAppositamenteCostruito() {
		return this.edificioAppositamenteCostruito;
	}

	public void setEdificioAppositamenteCostruito(Boolean edificioAppositamenteCostruito) {
		this.edificioAppositamenteCostruito = edificioAppositamenteCostruito;
	}

	public String getEdificioDataCostruzione() {
		return this.edificioDataCostruzione;
	}

	public void setEdificioDataCostruzione(String edificioDataCostruzione) {
		this.edificioDataCostruzione = edificioDataCostruzione;
	}

	public Boolean getEdificioMonumentale() {
		return this.edificioMonumentale;
	}

	public void setEdificioMonumentale(Boolean edificioMonumentale) {
		this.edificioMonumentale = edificioMonumentale;
	}

	public String getEnteDenominazione() {
		return this.enteDenominazione;
	}

	public void setEnteDenominazione(String enteDenominazione) {
		this.enteDenominazione = enteDenominazione;
	}

	public String getEnteStato() {
		return this.enteStato;
	}

	public void setEnteStato(String enteStato) {
		this.enteStato = enteStato;
	}

	public String getEnteTipologiaAmministrativa() {
		return this.enteTipologiaAmministrativa;
	}

	public void setEnteTipologiaAmministrativa(String enteTipologiaAmministrativa) {
		this.enteTipologiaAmministrativa = enteTipologiaAmministrativa;
	}

	public Boolean getGestisceServizioBibliograficoEsterno() {
		return this.gestisceServizioBibliograficoEsterno;
	}

	public void setGestisceServizioBibliograficoEsterno(Boolean gestisceServizioBibliograficoEsterno) {
		this.gestisceServizioBibliograficoEsterno = gestisceServizioBibliograficoEsterno;
	}

	public Boolean getGestisceServizioBibliograficoInterno() {
		return this.gestisceServizioBibliograficoInterno;
	}

	public void setGestisceServizioBibliograficoInterno(Boolean gestisceServizioBibliograficoInterno) {
		this.gestisceServizioBibliograficoInterno = gestisceServizioBibliograficoInterno;
	}

	public Boolean getInventarioCartaceo() {
		return this.inventarioCartaceo;
	}

	public void setInventarioCartaceo(Boolean inventarioCartaceo) {
		this.inventarioCartaceo = inventarioCartaceo;
	}

	public Boolean getInventarioInformatizzato() {
		return this.inventarioInformatizzato;
	}

	public void setInventarioInformatizzato(Boolean inventarioInformatizzato) {
		this.inventarioInformatizzato = inventarioInformatizzato;
	}

	public Integer getIsilNumero() {
		return this.isilNumero;
	}

	public void setIsilNumero(Integer isilNumero) {
		this.isilNumero = isilNumero;
	}

	public String getIsilProvincia() {
		return this.isilProvincia;
	}

	public void setIsilProvincia(String isilProvincia) {
		this.isilProvincia = isilProvincia;
	}

	public String getIsilStato() {
		return this.isilStato;
	}

	public void setIsilStato(String isilStato) {
		this.isilStato = isilStato;
	}

	public Comune getComune() {
		return this.comune;
	}

	public void setComune(Comune comune) {
		this.comune = comune;
	}
	
	public StatoBibliotecaWorkflow getStatoBibliotecaWorkflow() {
		return this.statoBibliotecaWorkflow;
	}

	public void setStatoBibliotecaWorkflow(StatoBibliotecaWorkflow statoBibliotecaWorkflow) {
		this.statoBibliotecaWorkflow = statoBibliotecaWorkflow;
	}
	
	public TipologiaFunzionale getTipologiaFunzionale() {
		return this.tipologiaFunzionale;
	}

	public void setTipologiaFunzionale(TipologiaFunzionale tipologiaFunzionale) {
		this.tipologiaFunzionale = tipologiaFunzionale;
	}
	
	public Utenti getUtenti_gestore() {
		return this.utenti_gestore;
	}

	public void setUtenti_gestore(Utenti utenti_gestore) {
		this.utenti_gestore = utenti_gestore;
	}
	
	public Utenti getUtenti_creatore() {
		return this.utenti_creatore;
	}

	public void setUtenti_creatore(Utenti utenti_creatore) {
		this.utenti_creatore = utenti_creatore;
	}
	
}