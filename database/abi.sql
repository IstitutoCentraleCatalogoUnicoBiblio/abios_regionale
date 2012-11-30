CREATE SCHEMA abiregionale;

CREATE TABLE abiregionale.accesso_modalita ( 
    id_accesso_modalita INT UNSIGNED NOT NULL AUTO_INCREMENT,
    descrizione VARCHAR( 255 ) NOT NULL,
CONSTRAINT PK_Table PRIMARY KEY ( id_accesso_modalita )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE abiregionale.bibliografia ( 
    id_bibliografia INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_biblioteca INT UNSIGNED NOT NULL,
    descrizione TEXT NULL,
CONSTRAINT pk_bibliografia PRIMARY KEY ( id_bibliografia )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX bibliografia_FKIndex1 ON abiregionale.bibliografia ( id_biblioteca );

CREATE TABLE abiregionale.biblioteca ( 
    id_biblioteca INT UNSIGNED NOT NULL AUTO_INCREMENT,
    isil_stato VARCHAR( 2 ) NOT NULL DEFAULT 'IT',
    isil_provincia VARCHAR( 2 ) NOT NULL,
    isil_numero INT UNSIGNED NOT NULL,
    denominazione_ufficiale VARCHAR( 255 ) NOT NULL,
    indirizzo VARCHAR( 255 ) NULL,
    frazione VARCHAR( 255 ) NULL,
    cap VARCHAR( 5 ) NULL,
    id_comune INT UNSIGNED NOT NULL,
    codice_fiscale VARCHAR( 16 ) NULL,
    partita_iva VARCHAR( 11 ) NULL,
    data_fondazione VARCHAR( 20 ) NULL,
    data_istituzione VARCHAR( 255 ) NULL,
    edificio_denominazione VARCHAR( 255 ) NULL,
    edificio_monumentale BOOL NULL,
    edificio_data_costruzione VARCHAR( 255 ) NULL,
    edificio_appositamente_costruito BOOL NULL,
    autonomia_amministrativa BOOL NULL,
    struttura_gerarchica_sovraordinata VARCHAR( 255 ) NULL,
    accesso_riservato BOOL NULL,
    accesso_handicap BOOL NULL,
    attivo_accesso_internet BOOL NULL,
    accesso_internet_pagamento BOOL NULL,
    accesso_internet_tempo BOOL NULL,
    accesso_internet_proxy BOOL NULL,
    inventario_cartaceo BOOL NULL,
    inventario_informatizzato BOOL NULL,
    catalogo_topografico_cartaceo BOOL NULL,
    catalogo_topografico_informatizzato BOOL NULL,
    id_fondi_antichi_consistenza INT UNSIGNED NOT NULL,
    id_ente INT UNSIGNED NOT NULL,
    id_tipologia_funzionale INT UNSIGNED NOT NULL,
    procedure_ill_automatizzate BOOL NULL,
    id_biblioteca_padre INT UNSIGNED NULL,
    mq_totali INT UNSIGNED NULL,
    mq_pubblici INT UNSIGNED NULL,
    ml_magazzini INT UNSIGNED NULL,
    ml_aperti INT UNSIGNED NULL,
    posti_lettura INT UNSIGNED NULL,
    posti_video INT UNSIGNED NULL,
    posti_audio INT UNSIGNED NULL,
    posti_internet INT UNSIGNED NULL,
    utenti INT UNSIGNED NULL,
    utenti_under14 INT UNSIGNED NULL DEFAULT 0,
    utenti_iscritti INT UNSIGNED NULL,
    utenti_iscritti_prestito_anno INT UNSIGNED NULL,
    personale_temporaneo INT UNSIGNED NULL,
    personale_esterno INT UNSIGNED NULL,
    personale_part_time INT UNSIGNED NULL,
    personale_totale INT UNSIGNED NULL,
    bilancio_entrate INT UNSIGNED NULL,
    bilancio_uscite INT UNSIGNED NULL,
    bilancio_uscite_personale INT UNSIGNED NULL,
    bilancio_uscite_funzionamento INT UNSIGNED NULL,
    bilancio_uscite_incremento_patrimonio INT UNSIGNED NULL,
    bilancio_uscite_automazione INT UNSIGNED NULL,
    bilancio_uscite_acquisti_anno INT UNSIGNED NULL DEFAULT 0,
    bilancio_uscite_acquisti_ultimi15 INT UNSIGNED NULL DEFAULT 0,
    bilancio_uscite_varie INT UNSIGNED NULL,
    bilancio_patrimoniale_posseduto INT NULL,
    bilancio_patrimoniale_posseduto_under14 INT NULL,
    n_ore_settimanali INT UNSIGNED NULL DEFAULT 0,
    n_ore_settimanali_pom INT UNSIGNED NULL DEFAULT 0,
    n_settim_apertura INT UNSIGNED NULL DEFAULT 0,
    prestito_interbiblio_nazionale BOOL NULL,
    prestito_interbiblio_internazionale BOOL NULL,
    attivo_informazioni_bibliografiche BOOL NULL,
    gestisce_servizio_bibliografico_interno BOOL NULL,
    gestisce_servizio_bibliografico_esterno BOOL NULL,
    n_prestiti_interbibliotecari_annuo INT UNSIGNED NULL,
    n_prestiti_locali_annuo INT UNSIGNED NULL,
    catalogazione_data_modifica DATETIME NULL,
    id_utenti INT UNSIGNED NULL,
    id_stato_biblioteca_workflow INT NOT NULL,
    catalogazione_data_import DATETIME NULL,
    catalogazione_data_modifica_remota DATETIME NULL,
    catalogazione_note TEXT NULL,
    catalogazione_data_censimento DATETIME NULL,
    attivo_riproduzioni BOOL NULL,
    attivo_prestito_locale BOOL NULL,
    attivo_reference BOOL NULL,
    reference_locale BOOL NULL,
    reference_online BOOL NULL,
    attivo_document_delivery BOOL NULL,
    attivo_deposito_legale BOOL NULL,
    fonte VARCHAR( 255 ) NULL,
CONSTRAINT PK_biblioteca PRIMARY KEY ( id_biblioteca )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX biblioteca_FKIndex1 ON abiregionale.biblioteca ( id_ente );

CREATE INDEX biblioteca_FKIndex2 ON abiregionale.biblioteca ( id_comune );

CREATE INDEX biblioteca_FKIndex3 ON abiregionale.biblioteca ( id_biblioteca_padre );

CREATE INDEX biblioteca_FKIndex4 ON abiregionale.biblioteca ( id_tipologia_funzionale );

CREATE INDEX idx_1_fondi_antichi ON abiregionale.biblioteca ( id_fondi_antichi_consistenza );

CREATE INDEX idx_1_utentemod ON abiregionale.biblioteca ( id_utenti );

CREATE TABLE abiregionale.biblioteca_has_accesso_modalita ( 
    id_biblioteca INT UNSIGNED NOT NULL,
    id_accesso_modalita INT UNSIGNED NOT NULL,
CONSTRAINT chiave_accessomod PRIMARY KEY ( id_accesso_modalita, id_biblioteca )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX idx_biblioteca_has_accesso_utenti_ammessi ON abiregionale.biblioteca_has_accesso_modalita ( id_accesso_modalita );

CREATE TABLE abiregionale.biblioteca_has_dewey ( 
    id_biblioteca INT UNSIGNED NOT NULL,
    id_dewey VARCHAR( 6 ) NOT NULL,
CONSTRAINT pk_special PRIMARY KEY ( id_biblioteca, id_dewey )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX biblioteca_has_dewey_FKIndex1 ON abiregionale.biblioteca_has_dewey ( id_biblioteca );

CREATE INDEX biblioteca_has_dewey_FKIndex2 ON abiregionale.biblioteca_has_dewey ( id_dewey );

CREATE TABLE abiregionale.biblioteca_has_document_delivery ( 
    id_biblioteca INT UNSIGNED NOT NULL,
    id_riproduzioni_tipo INT UNSIGNED NOT NULL,
CONSTRAINT pk_bib_has_doc_del PRIMARY KEY ( id_biblioteca, id_riproduzioni_tipo )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX idx_bib_has_doc_del1 ON abiregionale.biblioteca_has_document_delivery ( id_biblioteca );

CREATE INDEX idx_bib_has_doc_del2 ON abiregionale.biblioteca_has_document_delivery ( id_riproduzioni_tipo );

CREATE TABLE abiregionale.biblioteca_has_fondi_speciali ( 
    id_biblioteca INT UNSIGNED NOT NULL,
    id_fondi_speciali INT UNSIGNED NOT NULL,
CONSTRAINT pk_biblioteca_has_fondi_speciali PRIMARY KEY ( id_biblioteca, id_fondi_speciali )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX biblioteca_has_fondi_speciali_FKIndex1 ON abiregionale.biblioteca_has_fondi_speciali ( id_biblioteca );

CREATE INDEX biblioteca_has_fondi_speciali_FKIndex2 ON abiregionale.biblioteca_has_fondi_speciali ( id_fondi_speciali );

CREATE TABLE abiregionale.biblioteca_has_indicizzazione_classificata ( 
    id_biblioteca INT UNSIGNED NOT NULL,
    id_indicizzazione_classificata INT UNSIGNED NOT NULL,
CONSTRAINT pk_biblioteca_has_indic_classificata PRIMARY KEY ( id_biblioteca, id_indicizzazione_classificata )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX biblioteca_has_indic_classificata_FKIndex1 ON abiregionale.biblioteca_has_indicizzazione_classificata ( id_biblioteca );

CREATE INDEX biblioteca_has_indic_classificata_FKIndex2 ON abiregionale.biblioteca_has_indicizzazione_classificata ( id_indicizzazione_classificata );

CREATE TABLE abiregionale.biblioteca_has_indicizzazione_soggetto ( 
    id_biblioteca INT UNSIGNED NOT NULL,
    id_indicizzazione_soggetto INT UNSIGNED NOT NULL,
CONSTRAINT pk_biblioteca_has_indic_soggetto PRIMARY KEY ( id_biblioteca, id_indicizzazione_soggetto )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX biblioteca_has_indic_soggetto_FKIndex1 ON abiregionale.biblioteca_has_indicizzazione_soggetto ( id_biblioteca );

CREATE INDEX biblioteca_has_indic_soggetto_FKIndex2 ON abiregionale.biblioteca_has_indicizzazione_soggetto ( id_indicizzazione_soggetto );

CREATE TABLE abiregionale.biblioteca_has_norme_catalogazione ( 
    id_biblioteca INT UNSIGNED NOT NULL,
    id_norme_catalogazione INT UNSIGNED NOT NULL,
CONSTRAINT pk_biblioteca_has_norme_catalogazione PRIMARY KEY ( id_biblioteca, id_norme_catalogazione )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX biblioteca_has_norme_catalogazione_FKIndex1 ON abiregionale.biblioteca_has_norme_catalogazione ( id_biblioteca );

CREATE INDEX biblioteca_has_norme_catalogazione_FKIndex2 ON abiregionale.biblioteca_has_norme_catalogazione ( id_norme_catalogazione );

CREATE TABLE abiregionale.biblioteca_has_prestito_interbibliotecario ( 
    id_biblioteca INT UNSIGNED NOT NULL,
    id_prestito_interbibliotecario INT UNSIGNED NOT NULL,
CONSTRAINT pk_biblioteca_has_pre_pos_dsc PRIMARY KEY ( id_biblioteca, id_prestito_interbibliotecario )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX biblioteca_has_pre_pos_dsc_FKIndex1 ON abiregionale.biblioteca_has_prestito_interbibliotecario ( id_biblioteca );

CREATE INDEX biblioteca_has_pre_pos_dsc_FKIndex2 ON abiregionale.biblioteca_has_prestito_interbibliotecario ( id_prestito_interbibliotecario );

CREATE TABLE abiregionale.biblioteca_has_servizi_bibliotecari_carta ( 
    id__servizi_bibliotecari_carta INT UNSIGNED NOT NULL,
    id_biblioteca INT UNSIGNED NOT NULL,
CONSTRAINT pk_servizi_biblio PRIMARY KEY ( id__servizi_bibliotecari_carta, id_biblioteca )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX servizi_biblio_FKIndex1 ON abiregionale.biblioteca_has_servizi_bibliotecari_carta ( id_biblioteca );

CREATE INDEX idx_biblioteca_has_servizi_bibliotecari ON abiregionale.biblioteca_has_servizi_bibliotecari_carta ( id__servizi_bibliotecari_carta );

CREATE TABLE abiregionale.biblioteca_has_servizi_informazioni_bibliografiche ( 
    id_servizi_informazioni_bibliografiche_modalita INT UNSIGNED NOT NULL,
    id_biblioteca INT UNSIGNED NOT NULL,
CONSTRAINT pk_servizi_biblio PRIMARY KEY ( id_servizi_informazioni_bibliografiche_modalita, id_biblioteca )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX servizi_biblio_FKIndex1 ON abiregionale.biblioteca_has_servizi_informazioni_bibliografiche ( id_biblioteca );

CREATE INDEX idx_biblioteca_has_servizi_bibliotecari ON abiregionale.biblioteca_has_servizi_informazioni_bibliografiche ( id_servizi_informazioni_bibliografiche_modalita );

CREATE TABLE abiregionale.biblioteca_has_sezioni_speciali ( 
    id_biblioteca INT UNSIGNED NOT NULL,
    id_sezioni_speciali INT UNSIGNED NOT NULL,
CONSTRAINT pk_biblioteca_has_sezioni_speciali PRIMARY KEY ( id_biblioteca, id_sezioni_speciali )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX biblioteca_has_sezioni_speciali_FKIndex1 ON abiregionale.biblioteca_has_sezioni_speciali ( id_biblioteca );

CREATE INDEX biblioteca_has_sezioni_speciali_FKIndex2 ON abiregionale.biblioteca_has_sezioni_speciali ( id_sezioni_speciali );

CREATE TABLE abiregionale.biblioteca_has_sistemi_biblioteche ( 
    id_biblioteca INT UNSIGNED NOT NULL,
    id_sistemi_biblioteche INT UNSIGNED NOT NULL,
CONSTRAINT pk_biblioteca_has_partec_sist_biblio PRIMARY KEY ( id_biblioteca, id_sistemi_biblioteche )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX biblioteca_has_partec_sist_biblio_FKIndex1 ON abiregionale.biblioteca_has_sistemi_biblioteche ( id_biblioteca );

CREATE INDEX biblioteca_has_partec_sist_biblio_FKIndex2 ON abiregionale.biblioteca_has_sistemi_biblioteche ( id_sistemi_biblioteche );

CREATE TABLE abiregionale.biblioteca_has_sistemi_prestito_interbibliotecario ( 
    id_biblioteca INT UNSIGNED NOT NULL,
    id_sistemi_prestito_interbibliotecario INT UNSIGNED NOT NULL,
CONSTRAINT pk_biblioteca_has_sist_pr_interbiblio PRIMARY KEY ( id_biblioteca, id_sistemi_prestito_interbibliotecario )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX biblioteca_has_sist_pr_interbiblio_FKIndex1 ON abiregionale.biblioteca_has_sistemi_prestito_interbibliotecario ( id_biblioteca );

CREATE INDEX biblioteca_has_sist_pr_interbiblio_FKIndex2 ON abiregionale.biblioteca_has_sistemi_prestito_interbibliotecario ( id_sistemi_prestito_interbibliotecario );

CREATE TABLE abiregionale.biblioteca_has_utenti ( 
    id_utenti INT UNSIGNED NOT NULL,
    id_biblioteca INT UNSIGNED NOT NULL,
CONSTRAINT idx_bibliotecahasutenti PRIMARY KEY ( id_utenti, id_biblioteca )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE abiregionale.cataloghi_collettivi ( 
    id_cataloghi_collettivi INT UNSIGNED NOT NULL AUTO_INCREMENT,
    descrizione VARCHAR( 255 ) NULL,
    id_cataloghi_collettivi_zona_tipo INT UNSIGNED NOT NULL,
    dettaglio_zona VARCHAR( 50 ) NULL,
CONSTRAINT pk_catal_coll_estens PRIMARY KEY ( id_cataloghi_collettivi )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX idx_cataloghi_collettivi_estensione ON abiregionale.cataloghi_collettivi ( id_cataloghi_collettivi_zona_tipo );

CREATE TABLE abiregionale.cataloghi_collettivi_materiale_url ( 
    id_cataloghi_collettivi_materiale_url INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_cataloghi_collettivi_materiale INT UNSIGNED NOT NULL,
    url VARCHAR( 255 ) NULL,
    descrizione VARCHAR( 255 ) NULL,
CONSTRAINT pk_catal_coll_url PRIMARY KEY ( id_cataloghi_collettivi_materiale_url )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX idx_cataloghi_collettivi_url ON abiregionale.cataloghi_collettivi_materiale_url ( id_cataloghi_collettivi_materiale );

CREATE TABLE abiregionale.cataloghi_collettivi_zona_tipo ( 
    id_cataloghi_collettivi_zona_tipo INT UNSIGNED NOT NULL AUTO_INCREMENT,
    descrizione VARCHAR( 255 ) NOT NULL,
CONSTRAINT PK_Table_3 PRIMARY KEY ( id_cataloghi_collettivi_zona_tipo )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE abiregionale.cataloghi_generali_url ( 
    id_cataloghi_generali_url INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_cataloghi_generali INT UNSIGNED NOT NULL,
    url VARCHAR( 255 ) NULL,
    descrizione VARCHAR( 255 ) NULL,
CONSTRAINT pk_catal_gener_url PRIMARY KEY ( id_cataloghi_generali_url )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX idx_id_cataloghi_generali ON abiregionale.cataloghi_generali_url ( id_cataloghi_generali );

CREATE TABLE abiregionale.cataloghi_speciali_materiale_url ( 
    id_cataloghi_speciali_materiale_url INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_cataloghi_speciali_materiale INT UNSIGNED NOT NULL,
    url VARCHAR( 255 ) NULL,
    descrizione VARCHAR( 255 ) NULL,
CONSTRAINT pk_catal_spec_url PRIMARY KEY ( id_cataloghi_speciali_materiale_url )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX idx_cataloghi_speciali_url ON abiregionale.cataloghi_speciali_materiale_url ( id_cataloghi_speciali_materiale );

CREATE TABLE abiregionale.cataloghi_supporto_digitale_tipo ( 
    id_cataloghi_supporto_digitale_tipo INT UNSIGNED NOT NULL AUTO_INCREMENT,
    descrizione VARCHAR( 255 ) NOT NULL,
CONSTRAINT PK_Table_3 PRIMARY KEY ( id_cataloghi_supporto_digitale_tipo )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE abiregionale.catalogo_generale_tipo ( 
    id_catalogo_generale_tipo INT UNSIGNED NOT NULL AUTO_INCREMENT,
    descrizione VARCHAR( 255 ) NOT NULL,
CONSTRAINT PK_Table_4 PRIMARY KEY ( id_catalogo_generale_tipo )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE abiregionale.codici ( 
    id_biblioteca INT UNSIGNED NOT NULL,
    id_codici INT UNSIGNED NOT NULL,
    valore VARCHAR( 100 ) NOT NULL,
CONSTRAINT idx_pk_bibliotecacodici PRIMARY KEY ( id_biblioteca, id_codici )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX idx_biblioteca_has_codice ON abiregionale.codici ( id_codici );

CREATE INDEX idx_codici ON abiregionale.codici ( id_biblioteca );

CREATE TABLE abiregionale.codici_tipo ( 
    id_codici INT UNSIGNED NOT NULL,
    descrizione VARCHAR( 255 ) NOT NULL,
CONSTRAINT PK_Table_4 PRIMARY KEY ( id_codici )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE abiregionale.comune ( 
    id_comune INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_provincia INT UNSIGNED NOT NULL,
    denominazione VARCHAR( 100 ) NOT NULL,
    codice_istat VARCHAR( 6 ) NULL,
    codice_finanze VARCHAR( 6 ) NULL,
CONSTRAINT pk_comune PRIMARY KEY ( id_comune )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX comune_FKIndex1 ON abiregionale.comune ( id_provincia );

CREATE TABLE abiregionale.comunicazioni ( 
    id_comunicazioni INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_biblioteca INT UNSIGNED NOT NULL,
    descrizione TEXT NULL,
CONSTRAINT pk_comunicazioni PRIMARY KEY ( id_comunicazioni )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX comunicazioni_FKIndex1 ON abiregionale.comunicazioni ( id_biblioteca );

CREATE TABLE abiregionale.contatti ( 
    id_contatti INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_biblioteca INT UNSIGNED NOT NULL,
    id_contatti_tipi INT UNSIGNED NOT NULL,
    valore VARCHAR( 255 ) NOT NULL,
    note VARCHAR( 100 ) NULL,
CONSTRAINT PK_contatti PRIMARY KEY ( id_contatti )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE abiregionale.contatti_tipo ( 
    id_contatti_tipo INT UNSIGNED NOT NULL AUTO_INCREMENT,
    descrizione VARCHAR( 255 ) NOT NULL,
CONSTRAINT PK_Table_1 PRIMARY KEY ( id_contatti_tipo )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE abiregionale.dati_province_istat ( 
    id_provincia INT UNSIGNED NOT NULL,
    totale_abitanti INT UNSIGNED NOT NULL DEFAULT 0,
CONSTRAINT pk_dati_province_istat PRIMARY KEY ( id_provincia )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE abiregionale.dati_regioni_istat ( 
    id_regione INT UNSIGNED NOT NULL,
    totale_abitanti INT UNSIGNED NOT NULL DEFAULT 0,
    descrizione_zona VARCHAR( 20 ) NULL,
    id_sort INT UNSIGNED NULL,
CONSTRAINT pk_dati_regioni_istat PRIMARY KEY ( id_regione )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE abiregionale.denominazioni_alternative ( 
    id_denominazioni_alternative INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_biblioteca INT UNSIGNED NOT NULL,
    denominazione VARCHAR( 255 ) NULL,
CONSTRAINT pk_denominata_anche PRIMARY KEY ( id_denominazioni_alternative )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX denominata_anche_FKIndex1 ON abiregionale.denominazioni_alternative ( id_biblioteca );

CREATE TABLE abiregionale.denominazioni_precedenti ( 
    id_denominazioni_precedenti INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_biblioteca INT UNSIGNED NOT NULL,
    denominazione VARCHAR( 255 ) NULL,
CONSTRAINT pk_denominata_gia PRIMARY KEY ( id_denominazioni_precedenti )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX denominata_gia_FKIndex1 ON abiregionale.denominazioni_precedenti ( id_biblioteca );

CREATE TABLE abiregionale.depositi_legali ( 
    id_biblioteca INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_depositi_legali_tipo INT UNSIGNED NOT NULL,
    da_anno VARCHAR( 10 ) NULL,
CONSTRAINT pk_biblioteca_has_deposito_legale PRIMARY KEY ( id_biblioteca, id_depositi_legali_tipo )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE abiregionale.depositi_legali_tipo ( 
    id_depositi_legali_tipo INT UNSIGNED NOT NULL AUTO_INCREMENT,
    descrizione VARCHAR( 255 ) NOT NULL,
CONSTRAINT pk_deposito_legale PRIMARY KEY ( id_depositi_legali_tipo )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE abiregionale.destinazioni_sociali ( 
    id_biblioteca INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_destinazioni_sociali_tipo INT UNSIGNED NOT NULL,
    note VARCHAR( 255 ) NULL,
CONSTRAINT pk_biblioteca_has_destinaz_sociale PRIMARY KEY ( id_biblioteca, id_destinazioni_sociali_tipo )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE abiregionale.destinazioni_sociali_tipo ( 
    id_destinazioni_sociali INT UNSIGNED NOT NULL AUTO_INCREMENT,
    descrizione VARCHAR( 255 ) NOT NULL,
CONSTRAINT pk_destinaz_sociale PRIMARY KEY ( id_destinazioni_sociali )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE abiregionale.dewey ( 
    id_dewey VARCHAR( 6 ) NOT NULL,
    descrizione VARCHAR( 255 ) NOT NULL,
CONSTRAINT pk_dewey PRIMARY KEY ( id_dewey )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE abiregionale.dewey_libero ( 
    id_biblioteca INT UNSIGNED NOT NULL,
    id_dewey VARCHAR( 6 ) NOT NULL,
    descrizione VARCHAR( 255 ) NULL,
CONSTRAINT pk_biblioteca_has_dewey_lib PRIMARY KEY ( id_biblioteca, id_dewey )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE abiregionale.ente ( 
    id_ente INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_stato INT UNSIGNED NOT NULL,
    id_ente_tipologia_amministrativa INT UNSIGNED NOT NULL,
    denominazione VARCHAR( 255 ) NULL,
    partita_iva VARCHAR( 11 ) NULL,
    codice_fiscale VARCHAR( 16 ) NULL,
    asia_asip VARCHAR( 11 ) NULL,
CONSTRAINT pk_ente PRIMARY KEY ( id_ente )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX ente_FKIndex2 ON abiregionale.ente ( id_ente_tipologia_amministrativa );

CREATE INDEX ente_FKIndex3 ON abiregionale.ente ( id_stato );

CREATE TABLE abiregionale.ente_tipologia_amministrativa ( 
    id_ente_tipologia_amministrativa INT UNSIGNED NOT NULL AUTO_INCREMENT,
    descrizione VARCHAR( 255 ) NOT NULL,
CONSTRAINT pk_tipologia_ente PRIMARY KEY ( id_ente_tipologia_amministrativa )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE abiregionale.fondi_antichi_consistenza ( 
    id_fondi_antichi_consistenza INT UNSIGNED NOT NULL AUTO_INCREMENT,
    descrizione VARCHAR( 255 ) NOT NULL,
CONSTRAINT pk_tipologia_funzionale PRIMARY KEY ( id_fondi_antichi_consistenza )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE abiregionale.fondi_speciali ( 
    id_fondi_speciali INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_dewey VARCHAR( 6 ) NULL,
    denominazione VARCHAR( 255 ) NOT NULL,
    fondo_depositato BOOL NULL,
    descrizione VARCHAR( 255 ) NULL,
    id_fondi_speciali_catalogazione_inventario INT UNSIGNED NOT NULL,
    catalogazione_inventario_url VARCHAR( 255 ) NULL,
    citazione_bibliografica TEXT NULL,
CONSTRAINT pk_fondi_speciali PRIMARY KEY ( id_fondi_speciali )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX fondi_speciali_FKIndex1 ON abiregionale.fondi_speciali ( id_dewey );

CREATE INDEX idx_fondi_speciali ON abiregionale.fondi_speciali ( id_fondi_speciali_catalogazione_inventario );

CREATE TABLE abiregionale.fondi_speciali_catalogazione_inventario ( 
    id_fondi_speciali_catalogazione_inventario INT UNSIGNED NOT NULL AUTO_INCREMENT,
    descrizione VARCHAR( 255 ) NOT NULL,
CONSTRAINT PK_Table_3 PRIMARY KEY ( id_fondi_speciali_catalogazione_inventario )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE abiregionale.geolocalizzazione ( 
    id_biblioteca INT UNSIGNED NOT NULL,
    latitudine DOUBLE NULL,
    longitudine DOUBLE NULL,
    precisione VARCHAR( 50 ) NULL,
    warning TEXT NULL,
    error TEXT NULL,
CONSTRAINT pk_id_bib PRIMARY KEY ( id_biblioteca )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE abiregionale.indicizzazione_classificata ( 
    id_indicizzazione_classificata INT UNSIGNED NOT NULL AUTO_INCREMENT,
    descrizione VARCHAR( 255 ) NULL,
CONSTRAINT pk_indic_classificata PRIMARY KEY ( id_indicizzazione_classificata )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE abiregionale.indicizzazione_soggetto ( 
    id_indicizzazione_soggetto INT UNSIGNED NOT NULL AUTO_INCREMENT,
    descrizione VARCHAR( 255 ) NULL,
CONSTRAINT pk_indic_soggetto PRIMARY KEY ( id_indicizzazione_soggetto )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE abiregionale.norme_catalogazione ( 
    id_norme_catalogazione INT UNSIGNED NOT NULL AUTO_INCREMENT,
    descrizione VARCHAR( 255 ) NOT NULL,
CONSTRAINT pk_norme_catalogazione PRIMARY KEY ( id_norme_catalogazione )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE abiregionale.orario_chiusure ( 
    id_orario_chiusure INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_biblioteca INT UNSIGNED NOT NULL,
    descrizione VARCHAR( 255 ) NULL,
CONSTRAINT pk_periodo_chiusura PRIMARY KEY ( id_orario_chiusure )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX periodo_chiusura_FKIndex1 ON abiregionale.orario_chiusure ( id_biblioteca );

CREATE TABLE abiregionale.orario_ufficiali ( 
    id_orario_ufficiali INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_biblioteca INT UNSIGNED NOT NULL,
    giorno INT NOT NULL,
    dalle TIME NOT NULL,
    alle TIME NOT NULL,
CONSTRAINT pk_orario_ufficiale PRIMARY KEY ( id_orario_ufficiali )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX orario_ufficiale_FKIndex1 ON abiregionale.orario_ufficiali ( id_biblioteca );

CREATE TABLE abiregionale.orario_variazioni ( 
    id_orario_variazioni INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_biblioteca INT UNSIGNED NOT NULL,
    descrizione VARCHAR( 255 ) NOT NULL,
    giorno INT NULL,
    dalle TIME NULL,
    alle TIME NULL,
CONSTRAINT pk_orari_variazioni PRIMARY KEY ( id_orario_variazioni )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX orari_variazioni_FKIndex1 ON abiregionale.orario_variazioni ( id_biblioteca );

CREATE TABLE abiregionale.partecipa_cataloghi_collettivi_materiale ( 
    id_cataloghi_collettivi_materiale INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_cataloghi_collettivi INT UNSIGNED NOT NULL,
    id_patrimonio_specializzazione INT UNSIGNED NOT NULL,
    id_biblioteca INT UNSIGNED NOT NULL,
    schede BOOL NULL,
    volume BOOL NULL,
    microforme BOOL NULL,
    id_cataloghi_supporto_digitale_tipo INT UNSIGNED NOT NULL,
    descrizione_volume VARCHAR( 255 ) NULL,
    percentuale_schede INT UNSIGNED NULL,
    percentuale_volume INT UNSIGNED NULL,
    percentuale_microforme INT UNSIGNED NULL,
    percentuale_informatizzata INT UNSIGNED NULL,
    da_anno INT UNSIGNED NULL,
    a_anno INT UNSIGNED NULL,
CONSTRAINT pk_catal_coll PRIMARY KEY ( id_cataloghi_collettivi_materiale )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX catal_coll_FKIndex1 ON abiregionale.partecipa_cataloghi_collettivi_materiale ( id_biblioteca );

CREATE INDEX catal_coll_FKIndex2 ON abiregionale.partecipa_cataloghi_collettivi_materiale ( id_patrimonio_specializzazione );

CREATE INDEX catal_coll_FKIndex3 ON abiregionale.partecipa_cataloghi_collettivi_materiale ( id_cataloghi_collettivi );

CREATE INDEX idx_cataloghi_collettivi ON abiregionale.partecipa_cataloghi_collettivi_materiale ( id_cataloghi_supporto_digitale_tipo );

CREATE TABLE abiregionale.partecipa_cataloghi_generali ( 
    id_cataloghi_generali INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_biblioteca INT UNSIGNED NOT NULL,
    schede BOOL NULL,
    volume BOOL NULL,
    microforme BOOL NULL,
    id_cataloghi_supporto_digitale_tipo INT UNSIGNED NOT NULL,
    descrizione_volume VARCHAR( 255 ) NULL,
    percentuale_schede INT UNSIGNED NULL,
    percentuale_volume INT UNSIGNED NULL,
    percentuale_microforme INT UNSIGNED NULL,
    percentuale_informatizzata INT UNSIGNED NULL,
    da_anno INT UNSIGNED NULL,
    a_anno INT UNSIGNED NULL,
    id_catalogo_generale_tipo INT UNSIGNED NOT NULL,
CONSTRAINT pk_catal_gener PRIMARY KEY ( id_cataloghi_generali )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX cataloghi_gener_FKIndex1 ON abiregionale.partecipa_cataloghi_generali ( id_biblioteca );

CREATE INDEX idx_cataloghi_generali ON abiregionale.partecipa_cataloghi_generali ( id_cataloghi_supporto_digitale_tipo );

CREATE INDEX idx_1_cataloghi_generali_tipo ON abiregionale.partecipa_cataloghi_generali ( id_catalogo_generale_tipo );

CREATE TABLE abiregionale.partecipa_cataloghi_speciali_materiale ( 
    id_cataloghi_speciali_materiale INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_patrimonio_specializzazione INT UNSIGNED NOT NULL,
    id_biblioteca INT UNSIGNED NOT NULL,
    schede BOOL NULL,
    volume BOOL NULL,
    microforme BOOL NULL,
    id_cataloghi_supporto_digitale_tipo INT UNSIGNED NOT NULL,
    descrizione_volume VARCHAR( 255 ) NULL,
    percentuale_schede INT UNSIGNED NULL,
    percentuale_volume INT UNSIGNED NULL,
    percentuale_microforme INT UNSIGNED NULL,
    percentuale_informatizzata INT UNSIGNED NULL,
    da_anno INT UNSIGNED NULL,
    a_anno INT UNSIGNED NULL,
    denominazione VARCHAR( 255 ) NULL,
CONSTRAINT pk_catal_spec PRIMARY KEY ( id_cataloghi_speciali_materiale )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX catal_spec_FKIndex1 ON abiregionale.partecipa_cataloghi_speciali_materiale ( id_biblioteca );

CREATE INDEX catal_spec_FKIndex2 ON abiregionale.partecipa_cataloghi_speciali_materiale ( id_patrimonio_specializzazione );

CREATE INDEX idx_cataloghi_speciali ON abiregionale.partecipa_cataloghi_speciali_materiale ( id_cataloghi_supporto_digitale_tipo );

CREATE TABLE abiregionale.patrimonio ( 
    id_biblioteca INT UNSIGNED NOT NULL,
    id_patrimonio_specializzazione INT UNSIGNED NOT NULL,
    quantita INT UNSIGNED NOT NULL,
    quantita_ultimo_anno INT UNSIGNED NOT NULL,
CONSTRAINT idx_1 PRIMARY KEY ( id_biblioteca, id_patrimonio_specializzazione )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE abiregionale.patrimonio_specializzazione ( 
    id_patrimonio_specializzazione INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_patrimonio_specializzazione_categoria INT UNSIGNED NOT NULL,
    descrizione TEXT NULL,
    id_sort INT UNSIGNED NULL,
CONSTRAINT pk_patr_spec PRIMARY KEY ( id_patrimonio_specializzazione )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX patr_spec_FKIndex1 ON abiregionale.patrimonio_specializzazione ( id_patrimonio_specializzazione_categoria );

CREATE TABLE abiregionale.patrimonio_specializzazione_categoria ( 
    id_patrimonio_specializzazione_categoria INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_patrimonio_specializzazione_categoria_madre INT UNSIGNED NULL,
    descrizione TEXT NULL,
    id_sort INT UNSIGNED NULL,
CONSTRAINT pk_patr_spec_cat PRIMARY KEY ( id_patrimonio_specializzazione_categoria )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX patr_spec_cat_FKIndex1 ON abiregionale.patrimonio_specializzazione_categoria ( id_patrimonio_specializzazione_categoria_madre );

CREATE TABLE abiregionale.prestito_interbibliotecario ( 
    id_prestito_interbibliotecario INT UNSIGNED NOT NULL AUTO_INCREMENT,
    nazionale BOOL NULL,
    internazionale BOOL NULL,
    id_prestito_interbibliotecario_modo INT UNSIGNED NOT NULL,
CONSTRAINT pk_pre_pos_dsc PRIMARY KEY ( id_prestito_interbibliotecario )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX idx_prestito_interbibliotecario ON abiregionale.prestito_interbibliotecario ( id_prestito_interbibliotecario_modo );

CREATE TABLE abiregionale.prestito_interbibliotecario_modo ( 
    id_prestito_interbibliotecario_modo INT UNSIGNED NOT NULL AUTO_INCREMENT,
    descrizione VARCHAR( 255 ) NOT NULL,
CONSTRAINT PK_Table_4 PRIMARY KEY ( id_prestito_interbibliotecario_modo )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE abiregionale.prestito_locale ( 
    id_prestito_locale INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_biblioteca INT UNSIGNED NOT NULL,
    automatizzato BOOL NULL,
    durata_giorni INT UNSIGNED NULL,
CONSTRAINT pk_prestito_locale PRIMARY KEY ( id_prestito_locale )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX prestito_locale_FKIndex1 ON abiregionale.prestito_locale ( id_biblioteca );

CREATE TABLE abiregionale.prestito_locale_has_materiale_escluso ( 
    id_prestito_locale INT UNSIGNED NOT NULL,
    id_prestito_locale_materiale_escluso INT UNSIGNED NOT NULL,
CONSTRAINT pk_prestito_locale_has_prest_escluso PRIMARY KEY ( id_prestito_locale, id_prestito_locale_materiale_escluso )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX prestito_locale_has_prest_escluso_FKIndex1 ON abiregionale.prestito_locale_has_materiale_escluso ( id_prestito_locale );

CREATE INDEX prestito_locale_has_prest_escluso_FKIndex2 ON abiregionale.prestito_locale_has_materiale_escluso ( id_prestito_locale_materiale_escluso );

CREATE TABLE abiregionale.prestito_locale_has_utenti_ammessi ( 
    id_prestito_locale INT UNSIGNED NOT NULL,
    id_prestito_locale_utenti_ammessi INT UNSIGNED NOT NULL,
CONSTRAINT pk_prestito_locale_has_prest_zona PRIMARY KEY ( id_prestito_locale, id_prestito_locale_utenti_ammessi )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX prestito_locale_has_prest_zona_FKIndex1 ON abiregionale.prestito_locale_has_utenti_ammessi ( id_prestito_locale );

CREATE INDEX prestito_locale_has_prest_zona_FKIndex2 ON abiregionale.prestito_locale_has_utenti_ammessi ( id_prestito_locale_utenti_ammessi );

CREATE TABLE abiregionale.prestito_locale_materiale_escluso ( 
    id_prestito_locale_materiale_escluso INT UNSIGNED NOT NULL AUTO_INCREMENT,
    descrizione VARCHAR( 255 ) NOT NULL,
CONSTRAINT pk_prest_escluso PRIMARY KEY ( id_prestito_locale_materiale_escluso )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE abiregionale.prestito_locale_utenti_ammessi ( 
    id_prestito_locale_utenti_ammessi INT UNSIGNED NOT NULL AUTO_INCREMENT,
    descrizione VARCHAR( 255 ) NOT NULL,
CONSTRAINT pk_prest_zona PRIMARY KEY ( id_prestito_locale_utenti_ammessi )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE abiregionale.profili ( 
    id_profili INT UNSIGNED NOT NULL AUTO_INCREMENT,
    denominazione VARCHAR( 255 ) NULL,
CONSTRAINT pk_profili PRIMARY KEY ( id_profili )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE abiregionale.provincia ( 
    id_provincia INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_regione INT UNSIGNED NOT NULL,
    denominazione VARCHAR( 100 ) NOT NULL,
    codice_istat VARCHAR( 10 ) NULL,
    sigla VARCHAR( 2 ) NULL,
CONSTRAINT pk_provincia PRIMARY KEY ( id_provincia )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX provincia_FKIndex1 ON abiregionale.provincia ( id_regione );

CREATE TABLE abiregionale.pubblicazioni ( 
    id_pubblicazioni INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_biblioteca INT UNSIGNED NOT NULL,
    descrizione VARCHAR( 255 ) NOT NULL,
CONSTRAINT PK_biblioteca_has_pubblicazioni PRIMARY KEY ( id_pubblicazioni )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX biblioteca_has_pubblicazioni_FKIndex1 ON abiregionale.pubblicazioni ( id_biblioteca );

CREATE TABLE abiregionale.regione ( 
    id_regione INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_stato INT UNSIGNED NOT NULL,
    denominazione VARCHAR( 100 ) NOT NULL,
    sigla VARCHAR( 2 ) NULL,
CONSTRAINT pk_regione PRIMARY KEY ( id_regione )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX regione_FKIndex1 ON abiregionale.regione ( id_stato );

CREATE TABLE abiregionale.regolamento ( 
    id_regolamento INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_biblioteca INT UNSIGNED NOT NULL,
    riferimento_normativa VARCHAR( 255 ) NULL,
    url VARCHAR( 255 ) NULL,
CONSTRAINT pk_regolamento PRIMARY KEY ( id_regolamento )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX regolamento_FKIndex1 ON abiregionale.regolamento ( id_biblioteca );

CREATE TABLE abiregionale.riproduzioni ( 
    id_biblioteca INT UNSIGNED NOT NULL,
    id_riproduzioni_tipo INT UNSIGNED NOT NULL,
    locale BOOL NULL,
    nazionale BOOL NULL,
    internazionale BOOL NULL,
CONSTRAINT idx PRIMARY KEY ( id_biblioteca, id_riproduzioni_tipo )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE abiregionale.riproduzioni_tipo ( 
    id_riproduzioni_tipo INT UNSIGNED NOT NULL AUTO_INCREMENT,
    descrizione VARCHAR( 255 ) NULL,
CONSTRAINT pk_tipo_riproduzioni PRIMARY KEY ( id_riproduzioni_tipo )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE abiregionale.servizi_bibliotecari_carta ( 
    id__servizi_bibliotecari_carta INT UNSIGNED NOT NULL AUTO_INCREMENT,
    descrizione VARCHAR( 255 ) NOT NULL,
CONSTRAINT PK_Table_2 PRIMARY KEY ( id__servizi_bibliotecari_carta )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE abiregionale.servizi_informazioni_bibliografiche_modalita ( 
    id_servizi_informazioni_bibliografiche_modalita INT UNSIGNED NOT NULL AUTO_INCREMENT,
    descrizione VARCHAR( 255 ) NOT NULL,
CONSTRAINT PK_Table_2 PRIMARY KEY ( id_servizi_informazioni_bibliografiche_modalita )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE abiregionale.sezioni_speciali ( 
    id_sezioni_speciali INT UNSIGNED NOT NULL AUTO_INCREMENT,
    descrizione VARCHAR( 255 ) NULL,
CONSTRAINT pk_sezioni_speciali PRIMARY KEY ( id_sezioni_speciali )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE abiregionale.sistemi_biblioteche ( 
    id_sistemi_biblioteche INT UNSIGNED NOT NULL AUTO_INCREMENT,
    descrizione VARCHAR( 255 ) NULL,
CONSTRAINT pk_partec_sist_biblio PRIMARY KEY ( id_sistemi_biblioteche )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE abiregionale.sistemi_prestito_interbibliotecario ( 
    id_sistemi_prestito_interbibliotecario INT UNSIGNED NOT NULL AUTO_INCREMENT,
    descrizione VARCHAR( 255 ) NOT NULL,
    url VARCHAR( 255 ) NULL,
CONSTRAINT pk_sist_pr_interbiblio PRIMARY KEY ( id_sistemi_prestito_interbibliotecario )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE abiregionale.spogli_bibliografici ( 
    id_spogli_bibliografici INT UNSIGNED NOT NULL AUTO_INCREMENT,
    id_biblioteca INT UNSIGNED NOT NULL,
    descrizione_bibliografica TEXT NOT NULL,
CONSTRAINT PK_Table_3 PRIMARY KEY ( id_spogli_bibliografici )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX idx_spogli_bibliografici ON abiregionale.spogli_bibliografici ( id_biblioteca );

CREATE TABLE abiregionale.stato ( 
    id_stato INT UNSIGNED NOT NULL AUTO_INCREMENT,
    denominazione VARCHAR( 100 ) NOT NULL,
    sigla VARCHAR( 2 ) NULL,
CONSTRAINT pk_stato PRIMARY KEY ( id_stato )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE abiregionale.stato_biblioteca_workflow ( 
    id_stato INT NOT NULL AUTO_INCREMENT,
    descrizione VARCHAR( 255 ) NULL,
    label VARCHAR( 64 ) NOT NULL,
CONSTRAINT PK_Table_4 PRIMARY KEY ( id_stato )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE abiregionale.stato_catalogazione ( 
    id_biblioteca INT UNSIGNED NOT NULL,
    id_stato_catalogazione INT UNSIGNED NOT NULL,
    id_biblioteca_target INT UNSIGNED NULL,
CONSTRAINT pk_biblioteca_has_stato_catalogazione PRIMARY KEY ( id_biblioteca, id_stato_catalogazione )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX idx_stato_catalogazione ON abiregionale.stato_catalogazione ( id_biblioteca_target );

CREATE TABLE abiregionale.stato_catalogazione_tipo ( 
    id_stato_catalogazione_tipo INT UNSIGNED NOT NULL AUTO_INCREMENT,
    descrizione VARCHAR( 100 ) NULL,
CONSTRAINT pk_stato_catalogazione PRIMARY KEY ( id_stato_catalogazione_tipo )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE abiregionale.tipologia_funzionale ( 
    id_tipologia_funzionale INT UNSIGNED NOT NULL AUTO_INCREMENT,
    descrizione VARCHAR( 255 ) NOT NULL,
CONSTRAINT pk_tipologia_funzionale PRIMARY KEY ( id_tipologia_funzionale )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE TABLE abiregionale.utenti ( 
    id_utenti INT UNSIGNED NOT NULL AUTO_INCREMENT,
    login VARCHAR( 20 ) NOT NULL,
    password VARCHAR( 50 ) NOT NULL,
    nome VARCHAR( 255 ) NULL,
    cognome VARCHAR( 255 ) NULL,
    tel VARCHAR( 50 ) NULL,
    fax VARCHAR( 50 ) NULL,
    email VARCHAR( 50 ) NULL,
    incarico VARCHAR( 50 ) NULL,
    note VARCHAR( 255 ) NULL,
    data_modifica_password DATETIME NULL,
    enabled BOOL NOT NULL DEFAULT 1,
    codice_validazione VARCHAR( 50 ) NULL,
CONSTRAINT PK_utenti PRIMARY KEY ( id_utenti ),
CONSTRAINT login UNIQUE ( login )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

ALTER TABLE abiregionale.utenti MODIFY data_modifica_password DATETIME COMMENT 'data di ultima generazione della password';

CREATE TABLE abiregionale.utenti_has_profili ( 
    id_utenti INT UNSIGNED NOT NULL,
    id_profili INT UNSIGNED NOT NULL,
CONSTRAINT pk_utenti_has_profili PRIMARY KEY ( id_utenti, id_profili )
 ) engine=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

CREATE INDEX utenti_has_profili_FKIndex1 ON abiregionale.utenti_has_profili ( id_utenti );

CREATE INDEX utenti_has_profili_FKIndex2 ON abiregionale.utenti_has_profili ( id_profili );

ALTER TABLE abiregionale.bibliografia ADD FOREIGN KEY fk_0 ( id_biblioteca ) REFERENCES abiregionale.biblioteca( id_biblioteca ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.biblioteca ADD FOREIGN KEY fk_0 ( id_comune ) REFERENCES abiregionale.comune( id_comune ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.biblioteca ADD FOREIGN KEY fk_2 ( id_tipologia_funzionale ) REFERENCES abiregionale.tipologia_funzionale( id_tipologia_funzionale ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.biblioteca ADD FOREIGN KEY fk_3 ( id_ente ) REFERENCES abiregionale.ente( id_ente ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.biblioteca ADD FOREIGN KEY fk_4 ( id_biblioteca_padre ) REFERENCES abiregionale.biblioteca( id_biblioteca ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.biblioteca ADD FOREIGN KEY fk_1 ( id_fondi_antichi_consistenza ) REFERENCES abiregionale.fondi_antichi_consistenza( id_fondi_antichi_consistenza ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.biblioteca ADD FOREIGN KEY fk_5 ( id_utenti ) REFERENCES abiregionale.utenti( id_utenti ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.biblioteca ADD FOREIGN KEY fk_6 ( id_stato_biblioteca_workflow ) REFERENCES abiregionale.stato_biblioteca_workflow( id_stato ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.biblioteca_has_accesso_modalita ADD FOREIGN KEY fk_0 ( id_biblioteca ) REFERENCES abiregionale.biblioteca( id_biblioteca ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.biblioteca_has_accesso_modalita ADD FOREIGN KEY fk_1 ( id_accesso_modalita ) REFERENCES abiregionale.accesso_modalita( id_accesso_modalita ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.biblioteca_has_dewey ADD FOREIGN KEY fk_0 ( id_biblioteca ) REFERENCES abiregionale.biblioteca( id_biblioteca ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.biblioteca_has_dewey ADD FOREIGN KEY fk_1 ( id_dewey ) REFERENCES abiregionale.dewey( id_dewey ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.biblioteca_has_document_delivery ADD FOREIGN KEY fk_ripro_tipo ( id_riproduzioni_tipo ) REFERENCES abiregionale.riproduzioni_tipo( id_riproduzioni_tipo ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.biblioteca_has_document_delivery ADD FOREIGN KEY fk_biblioteca ( id_biblioteca ) REFERENCES abiregionale.biblioteca( id_biblioteca ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.biblioteca_has_fondi_speciali ADD FOREIGN KEY fk_0 ( id_biblioteca ) REFERENCES abiregionale.biblioteca( id_biblioteca ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.biblioteca_has_fondi_speciali ADD FOREIGN KEY fk_1 ( id_fondi_speciali ) REFERENCES abiregionale.fondi_speciali( id_fondi_speciali ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.biblioteca_has_indicizzazione_classificata ADD FOREIGN KEY fk_0 ( id_biblioteca ) REFERENCES abiregionale.biblioteca( id_biblioteca ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.biblioteca_has_indicizzazione_classificata ADD FOREIGN KEY fk_1 ( id_indicizzazione_classificata ) REFERENCES abiregionale.indicizzazione_classificata( id_indicizzazione_classificata ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.biblioteca_has_indicizzazione_soggetto ADD FOREIGN KEY fk_0 ( id_biblioteca ) REFERENCES abiregionale.biblioteca( id_biblioteca ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.biblioteca_has_indicizzazione_soggetto ADD FOREIGN KEY fk_1 ( id_indicizzazione_soggetto ) REFERENCES abiregionale.indicizzazione_soggetto( id_indicizzazione_soggetto ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.biblioteca_has_norme_catalogazione ADD FOREIGN KEY fk_0 ( id_biblioteca ) REFERENCES abiregionale.biblioteca( id_biblioteca ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.biblioteca_has_norme_catalogazione ADD FOREIGN KEY fk_1 ( id_norme_catalogazione ) REFERENCES abiregionale.norme_catalogazione( id_norme_catalogazione ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.biblioteca_has_prestito_interbibliotecario ADD FOREIGN KEY fk_0 ( id_biblioteca ) REFERENCES abiregionale.biblioteca( id_biblioteca ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.biblioteca_has_prestito_interbibliotecario ADD FOREIGN KEY fk_1 ( id_prestito_interbibliotecario ) REFERENCES abiregionale.prestito_interbibliotecario( id_prestito_interbibliotecario ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.biblioteca_has_servizi_bibliotecari_carta ADD FOREIGN KEY fk_0 ( id_biblioteca ) REFERENCES abiregionale.biblioteca( id_biblioteca ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.biblioteca_has_servizi_bibliotecari_carta ADD FOREIGN KEY fk_1 ( id__servizi_bibliotecari_carta ) REFERENCES abiregionale.servizi_bibliotecari_carta( id__servizi_bibliotecari_carta ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.biblioteca_has_servizi_informazioni_bibliografiche ADD FOREIGN KEY fk_0 ( id_biblioteca ) REFERENCES abiregionale.biblioteca( id_biblioteca ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.biblioteca_has_servizi_informazioni_bibliografiche ADD FOREIGN KEY fk_1 ( id_servizi_informazioni_bibliografiche_modalita ) REFERENCES abiregionale.servizi_informazioni_bibliografiche_modalita( id_servizi_informazioni_bibliografiche_modalita ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.biblioteca_has_sezioni_speciali ADD FOREIGN KEY fk_0 ( id_biblioteca ) REFERENCES abiregionale.biblioteca( id_biblioteca ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.biblioteca_has_sezioni_speciali ADD FOREIGN KEY fk_1 ( id_sezioni_speciali ) REFERENCES abiregionale.sezioni_speciali( id_sezioni_speciali ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.biblioteca_has_sistemi_biblioteche ADD FOREIGN KEY fk_0 ( id_biblioteca ) REFERENCES abiregionale.biblioteca( id_biblioteca ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.biblioteca_has_sistemi_biblioteche ADD FOREIGN KEY fk_1 ( id_sistemi_biblioteche ) REFERENCES abiregionale.sistemi_biblioteche( id_sistemi_biblioteche ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.biblioteca_has_sistemi_prestito_interbibliotecario ADD FOREIGN KEY fk_0 ( id_biblioteca ) REFERENCES abiregionale.biblioteca( id_biblioteca ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.biblioteca_has_sistemi_prestito_interbibliotecario ADD FOREIGN KEY fk_1 ( id_sistemi_prestito_interbibliotecario ) REFERENCES abiregionale.sistemi_prestito_interbibliotecario( id_sistemi_prestito_interbibliotecario ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.biblioteca_has_utenti ADD FOREIGN KEY fk_0 ( id_utenti ) REFERENCES abiregionale.utenti( id_utenti ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.biblioteca_has_utenti ADD FOREIGN KEY fk_1 ( id_biblioteca ) REFERENCES abiregionale.biblioteca( id_biblioteca ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.cataloghi_collettivi ADD FOREIGN KEY fk_0 ( id_cataloghi_collettivi_zona_tipo ) REFERENCES abiregionale.cataloghi_collettivi_zona_tipo( id_cataloghi_collettivi_zona_tipo ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.cataloghi_collettivi_materiale_url ADD FOREIGN KEY fk_0 ( id_cataloghi_collettivi_materiale ) REFERENCES abiregionale.partecipa_cataloghi_collettivi_materiale( id_cataloghi_collettivi_materiale ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.cataloghi_generali_url ADD FOREIGN KEY fk_cataloghi_generali_url ( id_cataloghi_generali ) REFERENCES abiregionale.partecipa_cataloghi_generali( id_cataloghi_generali ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.cataloghi_speciali_materiale_url ADD FOREIGN KEY fk_0 ( id_cataloghi_speciali_materiale ) REFERENCES abiregionale.partecipa_cataloghi_speciali_materiale( id_cataloghi_speciali_materiale ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.codici ADD FOREIGN KEY fk_0 ( id_codici ) REFERENCES abiregionale.codici_tipo( id_codici ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.codici ADD FOREIGN KEY fk_1 ( id_biblioteca ) REFERENCES abiregionale.biblioteca( id_biblioteca ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.comune ADD FOREIGN KEY fk_comune_provincia1 ( id_provincia ) REFERENCES abiregionale.provincia( id_provincia ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.comunicazioni ADD FOREIGN KEY fk_0 ( id_biblioteca ) REFERENCES abiregionale.biblioteca( id_biblioteca ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.contatti ADD FOREIGN KEY fk_0 ( id_biblioteca ) REFERENCES abiregionale.biblioteca( id_biblioteca ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.contatti ADD FOREIGN KEY fk_1 ( id_contatti_tipi ) REFERENCES abiregionale.contatti_tipo( id_contatti_tipo ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.dati_province_istat ADD FOREIGN KEY fk_0 ( id_provincia ) REFERENCES abiregionale.provincia( id_provincia ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.dati_regioni_istat ADD FOREIGN KEY fk_0 ( id_regione ) REFERENCES abiregionale.regione( id_regione ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.denominazioni_alternative ADD FOREIGN KEY fk_0 ( id_biblioteca ) REFERENCES abiregionale.biblioteca( id_biblioteca ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.denominazioni_precedenti ADD FOREIGN KEY fk_0 ( id_biblioteca ) REFERENCES abiregionale.biblioteca( id_biblioteca ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.depositi_legali ADD FOREIGN KEY fk_0 ( id_biblioteca ) REFERENCES abiregionale.biblioteca( id_biblioteca ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.depositi_legali ADD FOREIGN KEY fk_1 ( id_depositi_legali_tipo ) REFERENCES abiregionale.depositi_legali_tipo( id_depositi_legali_tipo ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.destinazioni_sociali ADD FOREIGN KEY fk_0 ( id_biblioteca ) REFERENCES abiregionale.biblioteca( id_biblioteca ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.destinazioni_sociali ADD FOREIGN KEY fk_1 ( id_destinazioni_sociali_tipo ) REFERENCES abiregionale.destinazioni_sociali_tipo( id_destinazioni_sociali ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.dewey_libero ADD FOREIGN KEY fk_0 ( id_biblioteca ) REFERENCES abiregionale.biblioteca( id_biblioteca ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.dewey_libero ADD FOREIGN KEY fk_1 ( id_dewey ) REFERENCES abiregionale.dewey( id_dewey ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.ente ADD FOREIGN KEY fk_0 ( id_ente_tipologia_amministrativa ) REFERENCES abiregionale.ente_tipologia_amministrativa( id_ente_tipologia_amministrativa ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.ente ADD FOREIGN KEY fk_2 ( id_stato ) REFERENCES abiregionale.stato( id_stato ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.fondi_speciali ADD FOREIGN KEY fk_0 ( id_dewey ) REFERENCES abiregionale.dewey( id_dewey ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.fondi_speciali ADD FOREIGN KEY fk_1 ( id_fondi_speciali_catalogazione_inventario ) REFERENCES abiregionale.fondi_speciali_catalogazione_inventario( id_fondi_speciali_catalogazione_inventario ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.geolocalizzazione ADD FOREIGN KEY fk_0 ( id_biblioteca ) REFERENCES abiregionale.biblioteca( id_biblioteca ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.orario_chiusure ADD FOREIGN KEY fk_0 ( id_biblioteca ) REFERENCES abiregionale.biblioteca( id_biblioteca ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.orario_ufficiali ADD FOREIGN KEY fk_0 ( id_biblioteca ) REFERENCES abiregionale.biblioteca( id_biblioteca ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.orario_variazioni ADD FOREIGN KEY fk_0 ( id_biblioteca ) REFERENCES abiregionale.biblioteca( id_biblioteca ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.partecipa_cataloghi_collettivi_materiale ADD FOREIGN KEY fk_0 ( id_biblioteca ) REFERENCES abiregionale.biblioteca( id_biblioteca ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.partecipa_cataloghi_collettivi_materiale ADD FOREIGN KEY fk_1 ( id_cataloghi_collettivi ) REFERENCES abiregionale.cataloghi_collettivi( id_cataloghi_collettivi ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.partecipa_cataloghi_collettivi_materiale ADD FOREIGN KEY fk_2 ( id_patrimonio_specializzazione ) REFERENCES abiregionale.patrimonio_specializzazione( id_patrimonio_specializzazione ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.partecipa_cataloghi_collettivi_materiale ADD FOREIGN KEY fk_4 ( id_cataloghi_supporto_digitale_tipo ) REFERENCES abiregionale.cataloghi_supporto_digitale_tipo( id_cataloghi_supporto_digitale_tipo ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.partecipa_cataloghi_generali ADD FOREIGN KEY fk_0 ( id_biblioteca ) REFERENCES abiregionale.biblioteca( id_biblioteca ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.partecipa_cataloghi_generali ADD FOREIGN KEY fk_2 ( id_cataloghi_supporto_digitale_tipo ) REFERENCES abiregionale.cataloghi_supporto_digitale_tipo( id_cataloghi_supporto_digitale_tipo ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.partecipa_cataloghi_generali ADD FOREIGN KEY fk_3 ( id_catalogo_generale_tipo ) REFERENCES abiregionale.catalogo_generale_tipo( id_catalogo_generale_tipo ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.partecipa_cataloghi_speciali_materiale ADD FOREIGN KEY fk_0 ( id_biblioteca ) REFERENCES abiregionale.biblioteca( id_biblioteca ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.partecipa_cataloghi_speciali_materiale ADD FOREIGN KEY fk_2 ( id_patrimonio_specializzazione ) REFERENCES abiregionale.patrimonio_specializzazione( id_patrimonio_specializzazione ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.partecipa_cataloghi_speciali_materiale ADD FOREIGN KEY fk_3 ( id_cataloghi_supporto_digitale_tipo ) REFERENCES abiregionale.cataloghi_supporto_digitale_tipo( id_cataloghi_supporto_digitale_tipo ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.patrimonio ADD FOREIGN KEY fk_0 ( id_biblioteca ) REFERENCES abiregionale.biblioteca( id_biblioteca ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.patrimonio ADD FOREIGN KEY fk_1 ( id_patrimonio_specializzazione ) REFERENCES abiregionale.patrimonio_specializzazione( id_patrimonio_specializzazione ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.patrimonio_specializzazione ADD FOREIGN KEY fk_0 ( id_patrimonio_specializzazione_categoria ) REFERENCES abiregionale.patrimonio_specializzazione_categoria( id_patrimonio_specializzazione_categoria ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.patrimonio_specializzazione_categoria ADD FOREIGN KEY fk_0 ( id_patrimonio_specializzazione_categoria_madre ) REFERENCES abiregionale.patrimonio_specializzazione_categoria( id_patrimonio_specializzazione_categoria ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.prestito_interbibliotecario ADD FOREIGN KEY fk_0 ( id_prestito_interbibliotecario_modo ) REFERENCES abiregionale.prestito_interbibliotecario_modo( id_prestito_interbibliotecario_modo ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.prestito_locale ADD FOREIGN KEY fk_0 ( id_biblioteca ) REFERENCES abiregionale.biblioteca( id_biblioteca ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.prestito_locale_has_materiale_escluso ADD FOREIGN KEY fk_0 ( id_prestito_locale ) REFERENCES abiregionale.prestito_locale( id_prestito_locale ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.prestito_locale_has_materiale_escluso ADD FOREIGN KEY fk_1 ( id_prestito_locale_materiale_escluso ) REFERENCES abiregionale.prestito_locale_materiale_escluso( id_prestito_locale_materiale_escluso ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.prestito_locale_has_utenti_ammessi ADD FOREIGN KEY fk_1 ( id_prestito_locale ) REFERENCES abiregionale.prestito_locale( id_prestito_locale ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.prestito_locale_has_utenti_ammessi ADD FOREIGN KEY fk_0 ( id_prestito_locale_utenti_ammessi ) REFERENCES abiregionale.prestito_locale_utenti_ammessi( id_prestito_locale_utenti_ammessi ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.provincia ADD FOREIGN KEY fk_provincia_regione1 ( id_regione ) REFERENCES abiregionale.regione( id_regione ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.pubblicazioni ADD FOREIGN KEY fk_0 ( id_biblioteca ) REFERENCES abiregionale.biblioteca( id_biblioteca ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.regione ADD FOREIGN KEY fk_regione_stato1 ( id_stato ) REFERENCES abiregionale.stato( id_stato ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.regolamento ADD FOREIGN KEY fk_0 ( id_biblioteca ) REFERENCES abiregionale.biblioteca( id_biblioteca ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.riproduzioni ADD FOREIGN KEY fk_0 ( id_riproduzioni_tipo ) REFERENCES abiregionale.riproduzioni_tipo( id_riproduzioni_tipo ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.riproduzioni ADD FOREIGN KEY fk_1 ( id_biblioteca ) REFERENCES abiregionale.biblioteca( id_biblioteca ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.spogli_bibliografici ADD FOREIGN KEY fk_0 ( id_biblioteca ) REFERENCES abiregionale.biblioteca( id_biblioteca ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.stato_catalogazione ADD FOREIGN KEY fk_0 ( id_biblioteca ) REFERENCES abiregionale.biblioteca( id_biblioteca ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.stato_catalogazione ADD FOREIGN KEY fk_1 ( id_stato_catalogazione ) REFERENCES abiregionale.stato_catalogazione_tipo( id_stato_catalogazione_tipo ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.stato_catalogazione ADD FOREIGN KEY fk_2 ( id_biblioteca_target ) REFERENCES abiregionale.biblioteca( id_biblioteca ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.utenti_has_profili ADD FOREIGN KEY fk_0 ( id_utenti ) REFERENCES abiregionale.utenti( id_utenti ) ON DELETE NO ACTION ON UPDATE NO ACTION;

ALTER TABLE abiregionale.utenti_has_profili ADD FOREIGN KEY fk_1 ( id_profili ) REFERENCES abiregionale.profili( id_profili ) ON DELETE NO ACTION ON UPDATE NO ACTION;


