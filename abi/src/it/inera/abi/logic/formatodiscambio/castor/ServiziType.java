/*
 * Author Inera srl https://www.inera.it
 * Copyright (C) 2023  Inera srl https://www.inera.it/
 *
 * European Union Public Licence V. 1.2
 * EUPL (c) the European Community 2017
 *
 * This European Union Public Licence (the "EUPL") applies to the Work or Software (as defined below) which is provided under the terms of this Licence.
 * Any use of the Work, other than as authorised under this Licence is prohibited (to the extent such use is covered by a right of the copyright holder of the Work).
 * The Original Work is provided under the terms of this Licence when the Licensor (as defined below) has placed the following notice immediately following the copyright notice for the Original Work:
 * Licensed under the EUPL V.1.2 or has expressed by any other mean his willingness to license under the EUPL.
 *
 * You should have received a copy of the European Union Public Licence V. 1.2 along with this program.  If not, see https://eupl.eu/1.2/en/
 */

/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: ServiziType.java,v 1.7 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Tipo di elemento relativo ai servizi offerti da una
 *  biblioteca. Fra questi, gli orari di apertura, il
 *  prestito, le
 *  informazioni bibliografiche, tipo e
 *  condizioni di accesso e diverse
 *  altre informazioni.
 *  
 * 
 * @version $Revision: 1.7 $ $Date: 2012/11/22 09:50:20 $
 */
@SuppressWarnings("serial")
public abstract class ServiziType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Questo elemento non ripetibile raggruppa tutte
     *  le
     *  informazioni relative agli orari di accesso
     *  alla biblioteca. I
     *  sottoelementi, tutti
     *  opzionali, sono per lo più ripetibili, eccetto
     *  ovviamente l'orario ufficiale.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.ServiziOrario _serviziOrario;

    /**
     * Varie informazioni relative al servizio di
     *  prestito. Ci sono diversi sotto-elementi di
     *  ovvio significato,
     *  tutti opzionali.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.Prestito _prestito;

    /**
     * Con questo elemento si dà la possibilità di
     *  inserire una lista di riproduzioni ammesse. 
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.Riproduzioni _riproduzioni;

    /**
     * Per segnalare la disponibilità del servizio
     *  interno si usa l'elemento vuoto omonimo,
     *  soluzione discutibile ma
     *  funzionante. Essa ha il
     *  vantaggio di mettere questo servizio allo
     *  stesso
     *  livello del "servizio-esterno", com'è logico.
     *  Quest'ultimo
     *  però è più articolato.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.InformazioniBibliografiche _informazioniBibliografiche;

    /**
     * Accesso a Internet da postazioni locali. La
     *  modalità "limitato" potrebbe essere fonte di
     *  equivoci. In ogni
     *  caso, la modalità è opzionale
     *  e ripetibile.
     *  NB. MODIFICATO TIPO
     *  ENUMERATO 
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.Internet _internet;

    /**
     * Riguarda le modalità di accesso alla biblioteca,
     *  sia in termini logistici (e.g. portatori di
     *  handicap), sia in
     *  termini amministrativi (chi
     *  può accedere ai servizi e a quali
     *  condizioni può
     *  farlo).
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.Accesso _accesso;

    /**
     * Se la biblioteca partecipa a uno o più sistemi
     *  di
     *  biblioteche, questi devono essere dichiarati
     *  in questo elemento,
     *  utilizzando sottoelementi
     *  "sistema". È obbligatorio almeno uno di
     *  questi
     *  sottoelementi. I sottoelementi "sistema"
     *  contegono
     *  semplicemente il nome di ciascun
     *  sistema di biblioteche.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.Sistemi _sistemi;

    /**
     * Se la biblioteca ha delle sezioni speciali,
     *  queste vanno inserite in altrettanti elementi
     *  "sezione". Nella
     *  maschera di ricerca avanzata
     *  dell'anagrafe, alla voce "Sezione
     *  speciale", è
     *  disponibile l'intero elenco delle sezioni
     *  speciali
     *  registrate dal sistema.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.SezioniSpeciali _sezioniSpeciali;

    /**
     * Servizio di reference: se disponibile, può essere locale o 
     *  online (email, telefono, fax, ecc...).
     *  Presente attributo 'attivo' del tipo siNoType.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.Reference _reference;

    /**
     * Servizio di document delivery: se attivo, può avere una o 
     *  più tipologie.
     *  Presente attributo 'attivo' del tipo siNoType.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.DocumentDelivery _documentDelivery;


      //----------------/
     //- Constructors -/
    //----------------/

    public ServiziType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'accesso'. The field 'accesso'
     * has the following description: Riguarda le modalità di
     * accesso alla biblioteca,
     *  sia in termini logistici (e.g. portatori di
     *  handicap), sia in
     *  termini amministrativi (chi
     *  può accedere ai servizi e a quali
     *  condizioni può
     *  farlo).
     *  
     * 
     * @return the value of field 'Accesso'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Accesso getAccesso(
    ) {
        return this._accesso;
    }

    /**
     * Returns the value of field 'documentDelivery'. The field
     * 'documentDelivery' has the following description: Servizio
     * di document delivery: se attivo, può avere una o 
     *  più tipologie.
     *  Presente attributo 'attivo' del tipo siNoType.
     *  
     * 
     * @return the value of field 'DocumentDelivery'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.DocumentDelivery getDocumentDelivery(
    ) {
        return this._documentDelivery;
    }

    /**
     * Returns the value of field 'informazioniBibliografiche'. The
     * field 'informazioniBibliografiche' has the following
     * description: Per segnalare la disponibilità del servizio
     *  interno si usa l'elemento vuoto omonimo,
     *  soluzione discutibile ma
     *  funzionante. Essa ha il
     *  vantaggio di mettere questo servizio allo
     *  stesso
     *  livello del "servizio-esterno", com'è logico.
     *  Quest'ultimo
     *  però è più articolato.
     *  
     * 
     * @return the value of field 'InformazioniBibliografiche'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.InformazioniBibliografiche getInformazioniBibliografiche(
    ) {
        return this._informazioniBibliografiche;
    }

    /**
     * Returns the value of field 'internet'. The field 'internet'
     * has the following description: Accesso a Internet da
     * postazioni locali. La
     *  modalità "limitato" potrebbe essere fonte di
     *  equivoci. In ogni
     *  caso, la modalità è opzionale
     *  e ripetibile.
     *  NB. MODIFICATO TIPO
     *  ENUMERATO 
     *  
     * 
     * @return the value of field 'Internet'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Internet getInternet(
    ) {
        return this._internet;
    }

    /**
     * Returns the value of field 'prestito'. The field 'prestito'
     * has the following description: Varie informazioni relative
     * al servizio di
     *  prestito. Ci sono diversi sotto-elementi di
     *  ovvio significato,
     *  tutti opzionali.
     *  
     * 
     * @return the value of field 'Prestito'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Prestito getPrestito(
    ) {
        return this._prestito;
    }

    /**
     * Returns the value of field 'reference'. The field
     * 'reference' has the following description: Servizio di
     * reference: se disponibile, può essere locale o 
     *  online (email, telefono, fax, ecc...).
     *  Presente attributo 'attivo' del tipo siNoType.
     *  
     * 
     * @return the value of field 'Reference'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Reference getReference(
    ) {
        return this._reference;
    }

    /**
     * Returns the value of field 'riproduzioni'. The field
     * 'riproduzioni' has the following description: Con questo
     * elemento si dà la possibilità di
     *  inserire una lista di riproduzioni ammesse. 
     *  
     * 
     * @return the value of field 'Riproduzioni'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Riproduzioni getRiproduzioni(
    ) {
        return this._riproduzioni;
    }

    /**
     * Returns the value of field 'serviziOrario'. The field
     * 'serviziOrario' has the following description: Questo
     * elemento non ripetibile raggruppa tutte
     *  le
     *  informazioni relative agli orari di accesso
     *  alla biblioteca. I
     *  sottoelementi, tutti
     *  opzionali, sono per lo più ripetibili, eccetto
     *  ovviamente l'orario ufficiale.
     *  
     * 
     * @return the value of field 'ServiziOrario'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.ServiziOrario getServiziOrario(
    ) {
        return this._serviziOrario;
    }

    /**
     * Returns the value of field 'sezioniSpeciali'. The field
     * 'sezioniSpeciali' has the following description: Se la
     * biblioteca ha delle sezioni speciali,
     *  queste vanno inserite in altrettanti elementi
     *  "sezione". Nella
     *  maschera di ricerca avanzata
     *  dell'anagrafe, alla voce "Sezione
     *  speciale", è
     *  disponibile l'intero elenco delle sezioni
     *  speciali
     *  registrate dal sistema.
     *  
     * 
     * @return the value of field 'SezioniSpeciali'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.SezioniSpeciali getSezioniSpeciali(
    ) {
        return this._sezioniSpeciali;
    }

    /**
     * Returns the value of field 'sistemi'. The field 'sistemi'
     * has the following description: Se la biblioteca partecipa a
     * uno o più sistemi
     *  di
     *  biblioteche, questi devono essere dichiarati
     *  in questo elemento,
     *  utilizzando sottoelementi
     *  "sistema". È obbligatorio almeno uno di
     *  questi
     *  sottoelementi. I sottoelementi "sistema"
     *  contegono
     *  semplicemente il nome di ciascun
     *  sistema di biblioteche.
     *  
     * 
     * @return the value of field 'Sistemi'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Sistemi getSistemi(
    ) {
        return this._sistemi;
    }

    /**
     * Method isValid.
     * 
     * @return true if this object is valid according to the schema
     */
    public boolean isValid(
    ) {
        try {
            validate();
        } catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    }

    /**
     * Sets the value of field 'accesso'. The field 'accesso' has
     * the following description: Riguarda le modalità di accesso
     * alla biblioteca,
     *  sia in termini logistici (e.g. portatori di
     *  handicap), sia in
     *  termini amministrativi (chi
     *  può accedere ai servizi e a quali
     *  condizioni può
     *  farlo).
     *  
     * 
     * @param accesso the value of field 'accesso'.
     */
    public void setAccesso(
            final it.inera.abi.logic.formatodiscambio.castor.Accesso accesso) {
        this._accesso = accesso;
    }

    /**
     * Sets the value of field 'documentDelivery'. The field
     * 'documentDelivery' has the following description: Servizio
     * di document delivery: se attivo, può avere una o 
     *  più tipologie.
     *  Presente attributo 'attivo' del tipo siNoType.
     *  
     * 
     * @param documentDelivery the value of field 'documentDelivery'
     */
    public void setDocumentDelivery(
            final it.inera.abi.logic.formatodiscambio.castor.DocumentDelivery documentDelivery) {
        this._documentDelivery = documentDelivery;
    }

    /**
     * Sets the value of field 'informazioniBibliografiche'. The
     * field 'informazioniBibliografiche' has the following
     * description: Per segnalare la disponibilità del servizio
     *  interno si usa l'elemento vuoto omonimo,
     *  soluzione discutibile ma
     *  funzionante. Essa ha il
     *  vantaggio di mettere questo servizio allo
     *  stesso
     *  livello del "servizio-esterno", com'è logico.
     *  Quest'ultimo
     *  però è più articolato.
     *  
     * 
     * @param informazioniBibliografiche the value of field
     * 'informazioniBibliografiche'.
     */
    public void setInformazioniBibliografiche(
            final it.inera.abi.logic.formatodiscambio.castor.InformazioniBibliografiche informazioniBibliografiche) {
        this._informazioniBibliografiche = informazioniBibliografiche;
    }

    /**
     * Sets the value of field 'internet'. The field 'internet' has
     * the following description: Accesso a Internet da postazioni
     * locali. La
     *  modalità "limitato" potrebbe essere fonte di
     *  equivoci. In ogni
     *  caso, la modalità è opzionale
     *  e ripetibile.
     *  NB. MODIFICATO TIPO
     *  ENUMERATO 
     *  
     * 
     * @param internet the value of field 'internet'.
     */
    public void setInternet(
            final it.inera.abi.logic.formatodiscambio.castor.Internet internet) {
        this._internet = internet;
    }

    /**
     * Sets the value of field 'prestito'. The field 'prestito' has
     * the following description: Varie informazioni relative al
     * servizio di
     *  prestito. Ci sono diversi sotto-elementi di
     *  ovvio significato,
     *  tutti opzionali.
     *  
     * 
     * @param prestito the value of field 'prestito'.
     */
    public void setPrestito(
            final it.inera.abi.logic.formatodiscambio.castor.Prestito prestito) {
        this._prestito = prestito;
    }

    /**
     * Sets the value of field 'reference'. The field 'reference'
     * has the following description: Servizio di reference: se
     * disponibile, può essere locale o 
     *  online (email, telefono, fax, ecc...).
     *  Presente attributo 'attivo' del tipo siNoType.
     *  
     * 
     * @param reference the value of field 'reference'.
     */
    public void setReference(
            final it.inera.abi.logic.formatodiscambio.castor.Reference reference) {
        this._reference = reference;
    }

    /**
     * Sets the value of field 'riproduzioni'. The field
     * 'riproduzioni' has the following description: Con questo
     * elemento si dà la possibilità di
     *  inserire una lista di riproduzioni ammesse. 
     *  
     * 
     * @param riproduzioni the value of field 'riproduzioni'.
     */
    public void setRiproduzioni(
            final it.inera.abi.logic.formatodiscambio.castor.Riproduzioni riproduzioni) {
        this._riproduzioni = riproduzioni;
    }

    /**
     * Sets the value of field 'serviziOrario'. The field
     * 'serviziOrario' has the following description: Questo
     * elemento non ripetibile raggruppa tutte
     *  le
     *  informazioni relative agli orari di accesso
     *  alla biblioteca. I
     *  sottoelementi, tutti
     *  opzionali, sono per lo più ripetibili, eccetto
     *  ovviamente l'orario ufficiale.
     *  
     * 
     * @param serviziOrario the value of field 'serviziOrario'.
     */
    public void setServiziOrario(
            final it.inera.abi.logic.formatodiscambio.castor.ServiziOrario serviziOrario) {
        this._serviziOrario = serviziOrario;
    }

    /**
     * Sets the value of field 'sezioniSpeciali'. The field
     * 'sezioniSpeciali' has the following description: Se la
     * biblioteca ha delle sezioni speciali,
     *  queste vanno inserite in altrettanti elementi
     *  "sezione". Nella
     *  maschera di ricerca avanzata
     *  dell'anagrafe, alla voce "Sezione
     *  speciale", è
     *  disponibile l'intero elenco delle sezioni
     *  speciali
     *  registrate dal sistema.
     *  
     * 
     * @param sezioniSpeciali the value of field 'sezioniSpeciali'.
     */
    public void setSezioniSpeciali(
            final it.inera.abi.logic.formatodiscambio.castor.SezioniSpeciali sezioniSpeciali) {
        this._sezioniSpeciali = sezioniSpeciali;
    }

    /**
     * Sets the value of field 'sistemi'. The field 'sistemi' has
     * the following description: Se la biblioteca partecipa a uno
     * o più sistemi
     *  di
     *  biblioteche, questi devono essere dichiarati
     *  in questo elemento,
     *  utilizzando sottoelementi
     *  "sistema". È obbligatorio almeno uno di
     *  questi
     *  sottoelementi. I sottoelementi "sistema"
     *  contegono
     *  semplicemente il nome di ciascun
     *  sistema di biblioteche.
     *  
     * 
     * @param sistemi the value of field 'sistemi'.
     */
    public void setSistemi(
            final it.inera.abi.logic.formatodiscambio.castor.Sistemi sistemi) {
        this._sistemi = sistemi;
    }

    /**
     * 
     * 
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void validate(
    )
    throws org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    }

}
