package it.inera.abi.persistence;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the biblioteca database table.
 * 
 */
@Entity
@Table(name="biblioteca")
public class Biblioteca implements Serializable {
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

	@Column(name="accesso_limite_eta_max")
	private Integer accessoLimiteEtaMax;

	@Column(name="accesso_limite_eta_min")
	private Integer accessoLimiteEtaMin;

	@Column(name="accesso_riservato")
	private Boolean accessoRiservato;

	@Column(name="autonomia_amministrativa")
	private Boolean autonomiaAmministrativa;

	@Column(name="bilancio_entrate")
	private Integer bilancioEntrate;

	@Column(name="bilancio_patrimoniale_posseduto")
	private Integer bilancioPatrimonialePosseduto;

	@Column(name="bilancio_patrimoniale_posseduto_under14")
	private Integer bilancioPatrimonialePossedutoUnder14;

	@Column(name="bilancio_uscite")
	private Integer bilancioUscite;

	@Column(name="bilancio_uscite_acquisti_anno")
	private Integer bilancioUsciteAcquistiAnno;

	@Column(name="bilancio_uscite_acquisti_ultimi15")
	private Integer bilancioUsciteAcquistiUltimi15;

	@Column(name="bilancio_uscite_automazione")
	private Integer bilancioUsciteAutomazione;

	@Column(name="bilancio_uscite_funzionamento")
	private Integer bilancioUsciteFunzionamento;

	@Column(name="bilancio_uscite_incremento_patrimonio")
	private Integer bilancioUsciteIncrementoPatrimonio;

	@Column(name="bilancio_uscite_personale")
	private Integer bilancioUscitePersonale;

	@Column(name="bilancio_uscite_varie")
	private Integer bilancioUsciteVarie;

	@Column(length=5)
	private String cap;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="catalogazione_data_censimento")
	private Date catalogazioneDataCensimento;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="catalogazione_data_import")
	private Date catalogazioneDataImport;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="catalogazione_data_modifica")
	private Date catalogazioneDataModifica;

    @Temporal( TemporalType.TIMESTAMP)
	@Column(name="catalogazione_data_modifica_remota")
	private Date catalogazioneDataModificaRemota;

    @Lob()
	@Column(name="catalogazione_note")
	private String catalogazioneNote;

	@Column(name="catalogo_topografico_cartaceo")
	private Boolean catalogoTopograficoCartaceo;

	@Column(name="catalogo_topografico_informatizzato")
	private Boolean catalogoTopograficoInformatizzato;

	@Column(name="codice_fiscale", length=16)
	private String codiceFiscale;

	@Column(name="data_fondazione", length=20)
	private String dataFondazione;

	@Column(name="data_istituzione", length=255)
	private String dataIstituzione;

	@Column(name="denominazione_ufficiale", nullable=false, length=255)
	private String denominazioneUfficiale;

	@Column(name="edificio_appositamente_costruito")
	private Boolean edificioAppositamenteCostruito;

	@Column(name="edificio_data_costruzione", length=255)
	private String edificioDataCostruzione;

	@Column(name="edificio_denominazione", length=255)
	private String edificioDenominazione;

	@Column(name="edificio_monumentale")
	private Boolean edificioMonumentale;

	@Column(length=255)
	private String frazione;

	@Column(name="gestisce_servizio_bibliografico_esterno")
	private Boolean gestisceServizioBibliograficoEsterno;

	@Column(name="gestisce_servizio_bibliografico_interno")
	private Boolean gestisceServizioBibliograficoInterno;

	@Column(length=255)
	private String indirizzo;

	@Column(name="inventario_cartaceo")
	private Boolean inventarioCartaceo;

	@Column(name="inventario_informatizzato")
	private Boolean inventarioInformatizzato;

	@Column(name="isil_numero", nullable=false)
	private Integer isilNumero;

	@Column(name="isil_provincia", nullable=false, length=2)
	private String isilProvincia;

	@Column(name="isil_stato", nullable=false, length=2)
	private String isilStato;

	@Column(name="ml_aperti")
	private Integer mlAperti;

	@Column(name="ml_magazzini")
	private Integer mlMagazzini;

	@Column(name="mq_pubblici")
	private Integer mqPubblici;

	@Column(name="mq_totali")
	private Integer mqTotali;

	@Column(name="n_ore_settimanali")
	private Integer nOreSettimanali;

	@Column(name="n_ore_settimanali_pom")
	private Integer nOreSettimanaliPom;

	@Column(name="n_prestiti_interbibliotecari_annuo")
	private Integer nPrestitiInterbibliotecariAnnuo;

	@Column(name="n_prestiti_locali_annuo")
	private Integer nPrestitiLocaliAnnuo;

	@Column(name="n_settim_apertura")
	private Integer nSettimApertura;

	@Column(name="partita_iva", length=11)
	private String partitaIva;

	@Column(name="personale_esterno")
	private Integer personaleEsterno;

	@Column(name="personale_part_time")
	private Integer personalePartTime;

	@Column(name="personale_temporaneo")
	private Integer personaleTemporaneo;

	@Column(name="personale_totale")
	private Integer personaleTotale;

	@Column(name="posti_audio")
	private Integer postiAudio;

	@Column(name="posti_internet")
	private Integer postiInternet;

	@Column(name="posti_lettura")
	private Integer postiLettura;

	@Column(name="posti_video")
	private Integer postiVideo;

	@Column(name="prestito_interbiblio_internazionale")
	private Boolean prestitoInterbiblioInternazionale;

	@Column(name="prestito_interbiblio_nazionale")
	private Boolean prestitoInterbiblioNazionale;

	@Column(name="procedure_ill_automatizzate")
	private Boolean procedureIllAutomatizzate;

	@Column(name="struttura_gerarchica_sovraordinata", length=255)
	private String strutturaGerarchicaSovraordinata;

	private Integer utenti;

	@Column(name="utenti_iscritti")
	private Integer utentiIscritti;

	@Column(name="utenti_iscritti_prestito_anno")
	private Integer utentiIscrittiPrestitoAnno;

	@Column(name="utenti_under14")
	private Integer utentiUnder14;

	//bi-directional many-to-one association to Bibliografia
	@OneToMany(mappedBy="biblioteca")
	private List<Bibliografia> bibliografias;

	//uni-directional many-to-many association to AccessoModalita
    @ManyToMany
	@JoinTable(
		name="biblioteca_has_accesso_modalita"
		, joinColumns={
			@JoinColumn(name="id_biblioteca", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_accesso_modalita", nullable=false)
			}
		)
	private List<AccessoModalita> accessoModalitas;

	//bi-directional many-to-one association to Biblioteca
    @ManyToOne
	@JoinColumn(name="id_biblioteca_padre")
	private Biblioteca bibliotecaPadre;

	//bi-directional many-to-one association to Biblioteca
	@OneToMany(mappedBy="bibliotecaPadre")
	private List<Biblioteca> bibliotecasFigli;

	//uni-directional many-to-one association to Comune
    @ManyToOne
	@JoinColumn(name="id_comune", nullable=false)
	private Comune comune;

	//uni-directional many-to-many association to Dewey
    @ManyToMany
	@JoinTable(
		name="biblioteca_has_dewey"
		, joinColumns={
			@JoinColumn(name="id_biblioteca", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_dewey", nullable=false)
			}
		)
	private List<Dewey> deweys;

	//uni-directional many-to-one association to Ente
    @ManyToOne
	@JoinColumn(name="id_ente", nullable=false)
	private Ente ente;

	//uni-directional many-to-one association to FondiAntichiConsistenza
    @ManyToOne
	@JoinColumn(name="id_fondi_antichi_consistenza", nullable=false)
	private FondiAntichiConsistenza fondiAntichiConsistenza;

	//uni-directional many-to-many association to FondiSpeciali
    @ManyToMany
	@JoinTable(
		name="biblioteca_has_fondi_speciali"
		, joinColumns={
			@JoinColumn(name="id_biblioteca", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_fondi_speciali", nullable=false)
			}
		)
	private List<FondiSpeciali> fondiSpecialis;

	//uni-directional many-to-many association to IndicizzazioneClassificata
    @ManyToMany
	@JoinTable(
		name="biblioteca_has_indicizzazione_classificata"
		, joinColumns={
			@JoinColumn(name="id_biblioteca", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_indicizzazione_classificata", nullable=false)
			}
		)
	private List<IndicizzazioneClassificata> indicizzazioneClassificatas;

	//uni-directional many-to-many association to IndicizzazioneSoggetto
    @ManyToMany
	@JoinTable(
		name="biblioteca_has_indicizzazione_soggetto"
		, joinColumns={
			@JoinColumn(name="id_biblioteca", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_indicizzazione_soggetto", nullable=false)
			}
		)
	private List<IndicizzazioneSoggetto> indicizzazioneSoggettos;

	//uni-directional many-to-many association to NormeCatalogazione
    @ManyToMany
	@JoinTable(
		name="biblioteca_has_norme_catalogazione"
		, joinColumns={
			@JoinColumn(name="id_biblioteca", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_norme_catalogazione", nullable=false)
			}
		)
	private List<NormeCatalogazione> normeCatalogaziones;

	//uni-directional many-to-many association to PrestitoInterbibliotecario
    @ManyToMany
	@JoinTable(
		name="biblioteca_has_prestito_interbibliotecario"
		, joinColumns={
			@JoinColumn(name="id_biblioteca", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_prestito_interbibliotecario", nullable=false)
			}
		)
	private List<PrestitoInterbibliotecario> prestitoInterbibliotecarios;

	//uni-directional many-to-many association to ServiziBibliotecariCarta
    @ManyToMany
	@JoinTable(
		name="biblioteca_has_servizi_bibliotecari_carta"
		, joinColumns={
			@JoinColumn(name="id_biblioteca", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="id__servizi_bibliotecari_carta", nullable=false)
			}
		)
	private List<ServiziBibliotecariCarta> serviziBibliotecariCartas;

	//uni-directional many-to-many association to ServiziInformazioniBibliograficheModalita
    @ManyToMany
	@JoinTable(
		name="biblioteca_has_servizi_informazioni_bibliografiche"
		, joinColumns={
			@JoinColumn(name="id_biblioteca", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_servizi_informazioni_bibliografiche_modalita", nullable=false)
			}
		)
	private List<ServiziInformazioniBibliograficheModalita> serviziInformazioniBibliograficheModalitas;

	//uni-directional many-to-many association to SezioniSpeciali
    @ManyToMany
	@JoinTable(
		name="biblioteca_has_sezioni_speciali"
		, joinColumns={
			@JoinColumn(name="id_biblioteca", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_sezioni_speciali", nullable=false)
			}
		)
	private List<SezioniSpeciali> sezioniSpecialis;

	//uni-directional many-to-many association to SistemiBiblioteche
    @ManyToMany
	@JoinTable(
		name="biblioteca_has_sistemi_biblioteche"
		, joinColumns={
			@JoinColumn(name="id_biblioteca", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_sistemi_biblioteche", nullable=false)
			}
		)
	private List<SistemiBiblioteche> sistemiBiblioteches;

	//uni-directional many-to-many association to SistemiPrestitoInterbibliotecario
    @ManyToMany
	@JoinTable(
		name="biblioteca_has_sistemi_prestito_interbibliotecario"
		, joinColumns={
			@JoinColumn(name="id_biblioteca", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_sistemi_prestito_interbibliotecario", nullable=false)
			}
		)
	private List<SistemiPrestitoInterbibliotecario> sistemiPrestitoInterbibliotecarios;

	//uni-directional many-to-one association to StatoBibliotecaWorkflow
    @ManyToOne
	@JoinColumn(name="id_stato_biblioteca_workflow", nullable=false)
	private StatoBibliotecaWorkflow statoBibliotecaWorkflow;

	//uni-directional many-to-many association to Thesaurus
    @ManyToMany
	@JoinTable(
		name="biblioteca_has_thesaurus"
		, joinColumns={
			@JoinColumn(name="id_biblioteca", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_thesaurus", nullable=false)
			}
		)
	private List<Thesaurus> thesauruses;

	//uni-directional many-to-one association to TipologiaFunzionale
    @ManyToOne
	@JoinColumn(name="id_tipologia_funzionale", nullable=false)
	private TipologiaFunzionale tipologiaFunzionale;

	//uni-directional many-to-one association to Utenti
    @ManyToOne
	@JoinColumn(name="id_utenti")
	private Utenti utenteUltimaModifica;

	//uni-directional many-to-many association to Utenti
    @ManyToMany
	@JoinTable(
		name="biblioteca_has_utenti"
		, joinColumns={
			@JoinColumn(name="id_biblioteca", nullable=false)
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_utenti", nullable=false)
			}
		)
	private List<Utenti> utentisGestori;

	//bi-directional many-to-one association to Codici
	@OneToMany(mappedBy="biblioteca")
	private List<Codici> codicis;

	//bi-directional many-to-one association to Comunicazioni
	@OneToMany(mappedBy="biblioteca")
	private List<Comunicazioni> comunicazionis;

	//bi-directional many-to-one association to Contatti
	@OneToMany(mappedBy="biblioteca")
	private List<Contatti> contattis;

	//bi-directional many-to-one association to DenominazioniAlternative
	@OneToMany(mappedBy="biblioteca")
	private List<DenominazioniAlternative> denominazioniAlternatives;

	//bi-directional many-to-one association to DenominazioniPrecedenti
	@OneToMany(mappedBy="biblioteca")
	private List<DenominazioniPrecedenti> denominazioniPrecedentis;

	//bi-directional many-to-one association to DepositiLegali
	@OneToMany(mappedBy="biblioteca")
	private List<DepositiLegali> depositiLegalis;

	//bi-directional many-to-one association to DestinazioniSociali
	@OneToMany(mappedBy="biblioteca")
	private List<DestinazioniSociali> destinazioniSocialis;

	//bi-directional many-to-one association to DeweyLibero
	@OneToMany(mappedBy="biblioteca")
	private List<DeweyLibero> deweyLiberos;

//	//bi-directional many-to-one association to FondiDigitali
//	@OneToMany(mappedBy="biblioteca")
//	private List<FondiDigitali> fondiDigitalis;

	//uni-directional one-to-one association to Geolocalizzazione
	@OneToOne
	@JoinColumn(name="id_biblioteca", nullable=false, insertable=false, updatable=false)
	private Geolocalizzazione geolocalizzazione;

	//bi-directional many-to-one association to OrarioChiusure
	@OneToMany(mappedBy="biblioteca")
	private List<OrarioChiusure> orarioChiusures;

	//bi-directional many-to-one association to OrarioUfficiali
	@OneToMany(mappedBy="biblioteca")
	private List<OrarioUfficiali> orarioUfficialis;

	//bi-directional many-to-one association to OrarioVariazioni
	@OneToMany(mappedBy="biblioteca")
	private List<OrarioVariazioni> orarioVariazionis;

	//bi-directional many-to-one association to PartecipaCataloghiCollettiviMateriale
	@OneToMany(mappedBy="biblioteca")
	private List<PartecipaCataloghiCollettiviMateriale> partecipaCataloghiCollettiviMateriales;

	//bi-directional many-to-one association to PartecipaCataloghiGenerali
	@OneToMany(mappedBy="biblioteca")
	private List<PartecipaCataloghiGenerali> partecipaCataloghiGeneralis;

	//bi-directional many-to-one association to PartecipaCataloghiSpecialiMateriale
	@OneToMany(mappedBy="biblioteca")
	private List<PartecipaCataloghiSpecialiMateriale> partecipaCataloghiSpecialiMateriales;

	//bi-directional many-to-one association to Patrimonio
	@OneToMany(mappedBy="biblioteca")
	private List<Patrimonio> patrimonios;

	//bi-directional many-to-one association to PrestitoLocale
	@OneToMany(mappedBy="biblioteca")
	private List<PrestitoLocale> prestitoLocales;

	//bi-directional many-to-one association to Pubblicazioni
	@OneToMany(mappedBy="biblioteca")
	private List<Pubblicazioni> pubblicazionis;

	//bi-directional many-to-one association to Regolamento
	@OneToMany(mappedBy="biblioteca")
	private List<Regolamento> regolamentos;

	//bi-directional many-to-one association to Riproduzioni
	@OneToMany(mappedBy="biblioteca")
	private List<Riproduzioni> riproduzionis;

	//bi-directional many-to-one association to SpogliBibliografici
	@OneToMany(mappedBy="biblioteca")
	private List<SpogliBibliografici> spogliBibliograficis;

	//bi-directional many-to-one association to StatoCatalogazione
	@OneToMany(mappedBy="biblioteca")
	private List<StatoCatalogazione> statoCatalogaziones;

    public Biblioteca() {
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

	public Integer getAccessoLimiteEtaMax() {
		return this.accessoLimiteEtaMax;
	}

	public void setAccessoLimiteEtaMax(Integer accessoLimiteEtaMax) {
		this.accessoLimiteEtaMax = accessoLimiteEtaMax;
	}

	public Integer getAccessoLimiteEtaMin() {
		return this.accessoLimiteEtaMin;
	}

	public void setAccessoLimiteEtaMin(Integer accessoLimiteEtaMin) {
		this.accessoLimiteEtaMin = accessoLimiteEtaMin;
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

	public Integer getBilancioEntrate() {
		return this.bilancioEntrate;
	}

	public void setBilancioEntrate(Integer bilancioEntrate) {
		this.bilancioEntrate = bilancioEntrate;
	}

	public Integer getBilancioPatrimonialePosseduto() {
		return this.bilancioPatrimonialePosseduto;
	}

	public void setBilancioPatrimonialePosseduto(Integer bilancioPatrimonialePosseduto) {
		this.bilancioPatrimonialePosseduto = bilancioPatrimonialePosseduto;
	}

	public Integer getBilancioPatrimonialePossedutoUnder14() {
		return this.bilancioPatrimonialePossedutoUnder14;
	}

	public void setBilancioPatrimonialePossedutoUnder14(Integer bilancioPatrimonialePossedutoUnder14) {
		this.bilancioPatrimonialePossedutoUnder14 = bilancioPatrimonialePossedutoUnder14;
	}

	public Integer getBilancioUscite() {
		return this.bilancioUscite;
	}

	public void setBilancioUscite(Integer bilancioUscite) {
		this.bilancioUscite = bilancioUscite;
	}

	public Integer getBilancioUsciteAcquistiAnno() {
		return this.bilancioUsciteAcquistiAnno;
	}

	public void setBilancioUsciteAcquistiAnno(Integer bilancioUsciteAcquistiAnno) {
		this.bilancioUsciteAcquistiAnno = bilancioUsciteAcquistiAnno;
	}

	public Integer getBilancioUsciteAcquistiUltimi15() {
		return this.bilancioUsciteAcquistiUltimi15;
	}

	public void setBilancioUsciteAcquistiUltimi15(Integer bilancioUsciteAcquistiUltimi15) {
		this.bilancioUsciteAcquistiUltimi15 = bilancioUsciteAcquistiUltimi15;
	}

	public Integer getBilancioUsciteAutomazione() {
		return this.bilancioUsciteAutomazione;
	}

	public void setBilancioUsciteAutomazione(Integer bilancioUsciteAutomazione) {
		this.bilancioUsciteAutomazione = bilancioUsciteAutomazione;
	}

	public Integer getBilancioUsciteFunzionamento() {
		return this.bilancioUsciteFunzionamento;
	}

	public void setBilancioUsciteFunzionamento(Integer bilancioUsciteFunzionamento) {
		this.bilancioUsciteFunzionamento = bilancioUsciteFunzionamento;
	}

	public Integer getBilancioUsciteIncrementoPatrimonio() {
		return this.bilancioUsciteIncrementoPatrimonio;
	}

	public void setBilancioUsciteIncrementoPatrimonio(Integer bilancioUsciteIncrementoPatrimonio) {
		this.bilancioUsciteIncrementoPatrimonio = bilancioUsciteIncrementoPatrimonio;
	}

	public Integer getBilancioUscitePersonale() {
		return this.bilancioUscitePersonale;
	}

	public void setBilancioUscitePersonale(Integer bilancioUscitePersonale) {
		this.bilancioUscitePersonale = bilancioUscitePersonale;
	}

	public Integer getBilancioUsciteVarie() {
		return this.bilancioUsciteVarie;
	}

	public void setBilancioUsciteVarie(Integer bilancioUsciteVarie) {
		this.bilancioUsciteVarie = bilancioUsciteVarie;
	}

	public String getCap() {
		return this.cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public Date getCatalogazioneDataCensimento() {
		return this.catalogazioneDataCensimento;
	}

	public void setCatalogazioneDataCensimento(Date catalogazioneDataCensimento) {
		this.catalogazioneDataCensimento = catalogazioneDataCensimento;
	}

	public Date getCatalogazioneDataImport() {
		return this.catalogazioneDataImport;
	}

	public void setCatalogazioneDataImport(Date catalogazioneDataImport) {
		this.catalogazioneDataImport = catalogazioneDataImport;
	}

	public Date getCatalogazioneDataModifica() {
		return this.catalogazioneDataModifica;
	}

	public void setCatalogazioneDataModifica(Date catalogazioneDataModifica) {
		this.catalogazioneDataModifica = catalogazioneDataModifica;
	}

	public Date getCatalogazioneDataModificaRemota() {
		return this.catalogazioneDataModificaRemota;
	}

	public void setCatalogazioneDataModificaRemota(Date catalogazioneDataModificaRemota) {
		this.catalogazioneDataModificaRemota = catalogazioneDataModificaRemota;
	}

	public String getCatalogazioneNote() {
		return this.catalogazioneNote;
	}

	public void setCatalogazioneNote(String catalogazioneNote) {
		this.catalogazioneNote = catalogazioneNote;
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

	public String getCodiceFiscale() {
		return this.codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getDataFondazione() {
		return this.dataFondazione;
	}

	public void setDataFondazione(String dataFondazione) {
		this.dataFondazione = dataFondazione;
	}

	public String getDataIstituzione() {
		return this.dataIstituzione;
	}

	public void setDataIstituzione(String dataIstituzione) {
		this.dataIstituzione = dataIstituzione;
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

	public String getEdificioDenominazione() {
		return this.edificioDenominazione;
	}

	public void setEdificioDenominazione(String edificioDenominazione) {
		this.edificioDenominazione = edificioDenominazione;
	}

	public Boolean getEdificioMonumentale() {
		return this.edificioMonumentale;
	}

	public void setEdificioMonumentale(Boolean edificioMonumentale) {
		this.edificioMonumentale = edificioMonumentale;
	}

	public String getFrazione() {
		return this.frazione;
	}

	public void setFrazione(String frazione) {
		this.frazione = frazione;
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

	public String getIndirizzo() {
		return this.indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
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

	public Integer getMlAperti() {
		return this.mlAperti;
	}

	public void setMlAperti(Integer mlAperti) {
		this.mlAperti = mlAperti;
	}

	public Integer getMlMagazzini() {
		return this.mlMagazzini;
	}

	public void setMlMagazzini(Integer mlMagazzini) {
		this.mlMagazzini = mlMagazzini;
	}

	public Integer getMqPubblici() {
		return this.mqPubblici;
	}

	public void setMqPubblici(Integer mqPubblici) {
		this.mqPubblici = mqPubblici;
	}

	public Integer getMqTotali() {
		return this.mqTotali;
	}

	public void setMqTotali(Integer mqTotali) {
		this.mqTotali = mqTotali;
	}

	public Integer getNOreSettimanali() {
		return this.nOreSettimanali;
	}

	public void setNOreSettimanali(Integer nOreSettimanali) {
		this.nOreSettimanali = nOreSettimanali;
	}

	public Integer getNOreSettimanaliPom() {
		return this.nOreSettimanaliPom;
	}

	public void setNOreSettimanaliPom(Integer nOreSettimanaliPom) {
		this.nOreSettimanaliPom = nOreSettimanaliPom;
	}

	public Integer getNPrestitiInterbibliotecariAnnuo() {
		return this.nPrestitiInterbibliotecariAnnuo;
	}

	public void setNPrestitiInterbibliotecariAnnuo(Integer nPrestitiInterbibliotecariAnnuo) {
		this.nPrestitiInterbibliotecariAnnuo = nPrestitiInterbibliotecariAnnuo;
	}

	public Integer getNPrestitiLocaliAnnuo() {
		return this.nPrestitiLocaliAnnuo;
	}

	public void setNPrestitiLocaliAnnuo(Integer nPrestitiLocaliAnnuo) {
		this.nPrestitiLocaliAnnuo = nPrestitiLocaliAnnuo;
	}

	public Integer getNSettimApertura() {
		return this.nSettimApertura;
	}

	public void setNSettimApertura(Integer nSettimApertura) {
		this.nSettimApertura = nSettimApertura;
	}

	public String getPartitaIva() {
		return this.partitaIva;
	}

	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}

	public Integer getPersonaleEsterno() {
		return this.personaleEsterno;
	}

	public void setPersonaleEsterno(Integer personaleEsterno) {
		this.personaleEsterno = personaleEsterno;
	}

	public Integer getPersonalePartTime() {
		return this.personalePartTime;
	}

	public void setPersonalePartTime(Integer personalePartTime) {
		this.personalePartTime = personalePartTime;
	}

	public Integer getPersonaleTemporaneo() {
		return this.personaleTemporaneo;
	}

	public void setPersonaleTemporaneo(Integer personaleTemporaneo) {
		this.personaleTemporaneo = personaleTemporaneo;
	}

	public Integer getPersonaleTotale() {
		return this.personaleTotale;
	}

	public void setPersonaleTotale(Integer personaleTotale) {
		this.personaleTotale = personaleTotale;
	}

	public Integer getPostiAudio() {
		return this.postiAudio;
	}

	public void setPostiAudio(Integer postiAudio) {
		this.postiAudio = postiAudio;
	}

	public Integer getPostiInternet() {
		return this.postiInternet;
	}

	public void setPostiInternet(Integer postiInternet) {
		this.postiInternet = postiInternet;
	}

	public Integer getPostiLettura() {
		return this.postiLettura;
	}

	public void setPostiLettura(Integer postiLettura) {
		this.postiLettura = postiLettura;
	}

	public Integer getPostiVideo() {
		return this.postiVideo;
	}

	public void setPostiVideo(Integer postiVideo) {
		this.postiVideo = postiVideo;
	}

	public Boolean getPrestitoInterbiblioInternazionale() {
		return this.prestitoInterbiblioInternazionale;
	}

	public void setPrestitoInterbiblioInternazionale(Boolean prestitoInterbiblioInternazionale) {
		this.prestitoInterbiblioInternazionale = prestitoInterbiblioInternazionale;
	}

	public Boolean getPrestitoInterbiblioNazionale() {
		return this.prestitoInterbiblioNazionale;
	}

	public void setPrestitoInterbiblioNazionale(Boolean prestitoInterbiblioNazionale) {
		this.prestitoInterbiblioNazionale = prestitoInterbiblioNazionale;
	}

	public Boolean getProcedureIllAutomatizzate() {
		return this.procedureIllAutomatizzate;
	}

	public void setProcedureIllAutomatizzate(Boolean procedureIllAutomatizzate) {
		this.procedureIllAutomatizzate = procedureIllAutomatizzate;
	}

	public String getStrutturaGerarchicaSovraordinata() {
		return this.strutturaGerarchicaSovraordinata;
	}

	public void setStrutturaGerarchicaSovraordinata(String strutturaGerarchicaSovraordinata) {
		this.strutturaGerarchicaSovraordinata = strutturaGerarchicaSovraordinata;
	}

	public Integer getUtenti() {
		return this.utenti;
	}

	public void setUtenti(Integer utenti) {
		this.utenti = utenti;
	}

	public Integer getUtentiIscritti() {
		return this.utentiIscritti;
	}

	public void setUtentiIscritti(Integer utentiIscritti) {
		this.utentiIscritti = utentiIscritti;
	}

	public Integer getUtentiIscrittiPrestitoAnno() {
		return this.utentiIscrittiPrestitoAnno;
	}

	public void setUtentiIscrittiPrestitoAnno(Integer utentiIscrittiPrestitoAnno) {
		this.utentiIscrittiPrestitoAnno = utentiIscrittiPrestitoAnno;
	}

	public Integer getUtentiUnder14() {
		return this.utentiUnder14;
	}

	public void setUtentiUnder14(Integer utentiUnder14) {
		this.utentiUnder14 = utentiUnder14;
	}

	public List<Bibliografia> getBibliografias() {
		return this.bibliografias;
	}

	public void setBibliografias(List<Bibliografia> bibliografias) {
		this.bibliografias = bibliografias;
	}
	
	public List<AccessoModalita> getAccessoModalitas() {
		return this.accessoModalitas;
	}

	public void setAccessoModalitas(List<AccessoModalita> accessoModalitas) {
		this.accessoModalitas = accessoModalitas;
	}
	
	public Biblioteca getBibliotecaPadre() {
		return this.bibliotecaPadre;
	}

	public void setBibliotecaPadre(Biblioteca bibliotecaPadre) {
		this.bibliotecaPadre = bibliotecaPadre;
	}
	
	public List<Biblioteca> getBibliotecasFigli() {
		return this.bibliotecasFigli;
	}

	public void setBibliotecasFigli(List<Biblioteca> bibliotecasFigli) {
		this.bibliotecasFigli = bibliotecasFigli;
	}
	
	public Comune getComune() {
		return this.comune;
	}

	public void setComune(Comune comune) {
		this.comune = comune;
	}
	
	public List<Dewey> getDeweys() {
		return this.deweys;
	}

	public void setDeweys(List<Dewey> deweys) {
		this.deweys = deweys;
	}
	
	public Ente getEnte() {
		return this.ente;
	}

	public void setEnte(Ente ente) {
		this.ente = ente;
	}
	
	public FondiAntichiConsistenza getFondiAntichiConsistenza() {
		return this.fondiAntichiConsistenza;
	}

	public void setFondiAntichiConsistenza(FondiAntichiConsistenza fondiAntichiConsistenza) {
		this.fondiAntichiConsistenza = fondiAntichiConsistenza;
	}
	
	public List<FondiSpeciali> getFondiSpecialis() {
		return this.fondiSpecialis;
	}

	public void setFondiSpecialis(List<FondiSpeciali> fondiSpecialis) {
		this.fondiSpecialis = fondiSpecialis;
	}
	
	public List<IndicizzazioneClassificata> getIndicizzazioneClassificatas() {
		return this.indicizzazioneClassificatas;
	}

	public void setIndicizzazioneClassificatas(List<IndicizzazioneClassificata> indicizzazioneClassificatas) {
		this.indicizzazioneClassificatas = indicizzazioneClassificatas;
	}
	
	public List<IndicizzazioneSoggetto> getIndicizzazioneSoggettos() {
		return this.indicizzazioneSoggettos;
	}

	public void setIndicizzazioneSoggettos(List<IndicizzazioneSoggetto> indicizzazioneSoggettos) {
		this.indicizzazioneSoggettos = indicizzazioneSoggettos;
	}
	
	public List<NormeCatalogazione> getNormeCatalogaziones() {
		return this.normeCatalogaziones;
	}

	public void setNormeCatalogaziones(List<NormeCatalogazione> normeCatalogaziones) {
		this.normeCatalogaziones = normeCatalogaziones;
	}
	
	public List<PrestitoInterbibliotecario> getPrestitoInterbibliotecarios() {
		return this.prestitoInterbibliotecarios;
	}

	public void setPrestitoInterbibliotecarios(List<PrestitoInterbibliotecario> prestitoInterbibliotecarios) {
		this.prestitoInterbibliotecarios = prestitoInterbibliotecarios;
	}
	
	public List<ServiziBibliotecariCarta> getServiziBibliotecariCartas() {
		return this.serviziBibliotecariCartas;
	}

	public void setServiziBibliotecariCartas(List<ServiziBibliotecariCarta> serviziBibliotecariCartas) {
		this.serviziBibliotecariCartas = serviziBibliotecariCartas;
	}
	
	public List<ServiziInformazioniBibliograficheModalita> getServiziInformazioniBibliograficheModalitas() {
		return this.serviziInformazioniBibliograficheModalitas;
	}

	public void setServiziInformazioniBibliograficheModalitas(List<ServiziInformazioniBibliograficheModalita> serviziInformazioniBibliograficheModalitas) {
		this.serviziInformazioniBibliograficheModalitas = serviziInformazioniBibliograficheModalitas;
	}
	
	public List<SezioniSpeciali> getSezioniSpecialis() {
		return this.sezioniSpecialis;
	}

	public void setSezioniSpecialis(List<SezioniSpeciali> sezioniSpecialis) {
		this.sezioniSpecialis = sezioniSpecialis;
	}
	
	public List<SistemiBiblioteche> getSistemiBiblioteches() {
		return this.sistemiBiblioteches;
	}

	public void setSistemiBiblioteches(List<SistemiBiblioteche> sistemiBiblioteches) {
		this.sistemiBiblioteches = sistemiBiblioteches;
	}
	
	public List<SistemiPrestitoInterbibliotecario> getSistemiPrestitoInterbibliotecarios() {
		return this.sistemiPrestitoInterbibliotecarios;
	}

	public void setSistemiPrestitoInterbibliotecarios(List<SistemiPrestitoInterbibliotecario> sistemiPrestitoInterbibliotecarios) {
		this.sistemiPrestitoInterbibliotecarios = sistemiPrestitoInterbibliotecarios;
	}
	
	public StatoBibliotecaWorkflow getStatoBibliotecaWorkflow() {
		return this.statoBibliotecaWorkflow;
	}

	public void setStatoBibliotecaWorkflow(StatoBibliotecaWorkflow statoBibliotecaWorkflow) {
		this.statoBibliotecaWorkflow = statoBibliotecaWorkflow;
	}
	
	public List<Thesaurus> getThesauruses() {
		return this.thesauruses;
	}

	public void setThesauruses(List<Thesaurus> thesauruses) {
		this.thesauruses = thesauruses;
	}
	
	public TipologiaFunzionale getTipologiaFunzionale() {
		return this.tipologiaFunzionale;
	}

	public void setTipologiaFunzionale(TipologiaFunzionale tipologiaFunzionale) {
		this.tipologiaFunzionale = tipologiaFunzionale;
	}
	
	public Utenti getUtenteUltimaModifica() {
		return this.utenteUltimaModifica;
	}

	public void setUtenteUltimaModifica(Utenti utenteUltimaModifica) {
		this.utenteUltimaModifica = utenteUltimaModifica;
	}
	
	public List<Utenti> getUtentisGestori() {
		return this.utentisGestori;
	}

	public void setUtentisGestori(List<Utenti> utentisGestori) {
		this.utentisGestori = utentisGestori;
	}
	
	public List<Codici> getCodicis() {
		return this.codicis;
	}

	public void setCodicis(List<Codici> codicis) {
		this.codicis = codicis;
	}
	
	public List<Comunicazioni> getComunicazionis() {
		return this.comunicazionis;
	}

	public void setComunicazionis(List<Comunicazioni> comunicazionis) {
		this.comunicazionis = comunicazionis;
	}
	
	public List<Contatti> getContattis() {
		return this.contattis;
	}

	public void setContattis(List<Contatti> contattis) {
		this.contattis = contattis;
	}
	
	public List<DenominazioniAlternative> getDenominazioniAlternatives() {
		return this.denominazioniAlternatives;
	}

	public void setDenominazioniAlternatives(List<DenominazioniAlternative> denominazioniAlternatives) {
		this.denominazioniAlternatives = denominazioniAlternatives;
	}
	
	public List<DenominazioniPrecedenti> getDenominazioniPrecedentis() {
		return this.denominazioniPrecedentis;
	}

	public void setDenominazioniPrecedentis(List<DenominazioniPrecedenti> denominazioniPrecedentis) {
		this.denominazioniPrecedentis = denominazioniPrecedentis;
	}
	
	public List<DepositiLegali> getDepositiLegalis() {
		return this.depositiLegalis;
	}

	public void setDepositiLegalis(List<DepositiLegali> depositiLegalis) {
		this.depositiLegalis = depositiLegalis;
	}
	
	public List<DestinazioniSociali> getDestinazioniSocialis() {
		return this.destinazioniSocialis;
	}

	public void setDestinazioniSocialis(List<DestinazioniSociali> destinazioniSocialis) {
		this.destinazioniSocialis = destinazioniSocialis;
	}
	
	public List<DeweyLibero> getDeweyLiberos() {
		return this.deweyLiberos;
	}

	public void setDeweyLiberos(List<DeweyLibero> deweyLiberos) {
		this.deweyLiberos = deweyLiberos;
	}
	
//	public List<FondiDigitali> getFondiDigitalis() {
//		return this.fondiDigitalis;
//	}
//
//	public void setFondiDigitalis(List<FondiDigitali> fondiDigitalis) {
//		this.fondiDigitalis = fondiDigitalis;
//	}
	
	public Geolocalizzazione getGeolocalizzazione() {
		return this.geolocalizzazione;
	}

	public void setGeolocalizzazione(Geolocalizzazione geolocalizzazione) {
		this.geolocalizzazione = geolocalizzazione;
	}
	
	public List<OrarioChiusure> getOrarioChiusures() {
		return this.orarioChiusures;
	}

	public void setOrarioChiusures(List<OrarioChiusure> orarioChiusures) {
		this.orarioChiusures = orarioChiusures;
	}
	
	public List<OrarioUfficiali> getOrarioUfficialis() {
		return this.orarioUfficialis;
	}

	public void setOrarioUfficialis(List<OrarioUfficiali> orarioUfficialis) {
		this.orarioUfficialis = orarioUfficialis;
	}
	
	public List<OrarioVariazioni> getOrarioVariazionis() {
		return this.orarioVariazionis;
	}

	public void setOrarioVariazionis(List<OrarioVariazioni> orarioVariazionis) {
		this.orarioVariazionis = orarioVariazionis;
	}
	
	public List<PartecipaCataloghiCollettiviMateriale> getPartecipaCataloghiCollettiviMateriales() {
		return this.partecipaCataloghiCollettiviMateriales;
	}

	public void setPartecipaCataloghiCollettiviMateriales(List<PartecipaCataloghiCollettiviMateriale> partecipaCataloghiCollettiviMateriales) {
		this.partecipaCataloghiCollettiviMateriales = partecipaCataloghiCollettiviMateriales;
	}
	
	public List<PartecipaCataloghiGenerali> getPartecipaCataloghiGeneralis() {
		return this.partecipaCataloghiGeneralis;
	}

	public void setPartecipaCataloghiGeneralis(List<PartecipaCataloghiGenerali> partecipaCataloghiGeneralis) {
		this.partecipaCataloghiGeneralis = partecipaCataloghiGeneralis;
	}
	
	public List<PartecipaCataloghiSpecialiMateriale> getPartecipaCataloghiSpecialiMateriales() {
		return this.partecipaCataloghiSpecialiMateriales;
	}

	public void setPartecipaCataloghiSpecialiMateriales(List<PartecipaCataloghiSpecialiMateriale> partecipaCataloghiSpecialiMateriales) {
		this.partecipaCataloghiSpecialiMateriales = partecipaCataloghiSpecialiMateriales;
	}
	
	public List<Patrimonio> getPatrimonios() {
		return this.patrimonios;
	}

	public void setPatrimonios(List<Patrimonio> patrimonios) {
		this.patrimonios = patrimonios;
	}
	
	public List<PrestitoLocale> getPrestitoLocales() {
		return this.prestitoLocales;
	}

	public void setPrestitoLocales(List<PrestitoLocale> prestitoLocales) {
		this.prestitoLocales = prestitoLocales;
	}
	
	public List<Pubblicazioni> getPubblicazionis() {
		return this.pubblicazionis;
	}

	public void setPubblicazionis(List<Pubblicazioni> pubblicazionis) {
		this.pubblicazionis = pubblicazionis;
	}
	
	public List<Regolamento> getRegolamentos() {
		return this.regolamentos;
	}

	public void setRegolamentos(List<Regolamento> regolamentos) {
		this.regolamentos = regolamentos;
	}
	
	public List<Riproduzioni> getRiproduzionis() {
		return this.riproduzionis;
	}

	public void setRiproduzionis(List<Riproduzioni> riproduzionis) {
		this.riproduzionis = riproduzionis;
	}
	
	public List<SpogliBibliografici> getSpogliBibliograficis() {
		return this.spogliBibliograficis;
	}

	public void setSpogliBibliograficis(List<SpogliBibliografici> spogliBibliograficis) {
		this.spogliBibliograficis = spogliBibliograficis;
	}
	
	public List<StatoCatalogazione> getStatoCatalogaziones() {
		return this.statoCatalogaziones;
	}

	public void setStatoCatalogaziones(List<StatoCatalogazione> statoCatalogaziones) {
		this.statoCatalogaziones = statoCatalogaziones;
	}
	
}