/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: AnagraficaType.java,v 1.1 2012/06/22 13:55:04 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Questo tipo raggruppa tutte le informazioni di tipo
 *  strettamente anagrafico.
 *  
 * 
 * @version $Revision: 1.1 $ $Date: 2012/06/22 13:55:04 $
 */
@SuppressWarnings("serial")
public abstract class AnagraficaType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Data in cui è stata censita la biblioteca (raccolta dati).
     *  Formato: AAAA
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.DataCensimento _dataCensimento;

    /**
     * Ultima data in cui è stata modificata la biblioteca (dal
     * gestionale) (x export ICCU).
     *  Formato: AAAA-MM-GGTHH:mm:ss
     *  
     */
    private java.util.Date _dataAggiornamento;

    /**
     * Nomi di una biblioteca. È un insieme in cui solo
     *  il nome attuale è obbligatorio e non ripetibile,
     *  mentre quelli precedenti e quelli alternativi
     *  sono opzionali e ripetibili.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.Nome _nome;

    /**
     * Codici di una biblioteca.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.Codici _codici;

    /**
     * Questo tipo descrive un indirizzo di biblioteca.
     *  È opzionale come tutti i suoi sotto-elementi, ma
     *  forse almeno uno dovrebbe essere reso
     *  obbligatorio, altrimenti si potrebbe istanziare
     *  un "indirizzo" vuoto.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.Indirizzo _indirizzo;

    /**
     * Varie modalità per contattare la biblioteca e il
     *  suo personale.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.Contatti _contatti;

    /**
     * Dati relativi all'edificio. Sono tutti
     *  opzionali, ma potrebbe essere più sensato
     *  renderne obbligatorio almeno uno.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.Edificio _edificio;

    /**
     * Data in cui è stata istituita formalmente
     *  l'attuale biblioteca. È possibile anche indicare
     *  l'anno di prima istituzione o fondazione, nel
     *  caso che la biblioteca abbia avuto nel tempo
     *  gestioni amministrative diverse.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.Istituzione _istituzione;


      //----------------/
     //- Constructors -/
    //----------------/

    public AnagraficaType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'codici'. The field 'codici' has
     * the following description: Codici di una biblioteca.
     *  
     * 
     * @return the value of field 'Codici'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Codici getCodici(
    ) {
        return this._codici;
    }

    /**
     * Returns the value of field 'contatti'. The field 'contatti'
     * has the following description: Varie modalità per contattare
     * la biblioteca e il
     *  suo personale.
     *  
     * 
     * @return the value of field 'Contatti'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Contatti getContatti(
    ) {
        return this._contatti;
    }

    /**
     * Returns the value of field 'dataAggiornamento'. The field
     * 'dataAggiornamento' has the following description: Ultima
     * data in cui è stata modificata la biblioteca (dal
     * gestionale) (x export ICCU).
     *  Formato: AAAA-MM-GGTHH:mm:ss
     *  
     * 
     * @return the value of field 'DataAggiornamento'.
     */
    public java.util.Date getDataAggiornamento(
    ) {
        return this._dataAggiornamento;
    }

    /**
     * Returns the value of field 'dataCensimento'. The field
     * 'dataCensimento' has the following description: Data in cui
     * è stata censita la biblioteca (raccolta dati).
     *  Formato: AAAA
     *  
     * 
     * @return the value of field 'DataCensimento'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.DataCensimento getDataCensimento(
    ) {
        return this._dataCensimento;
    }

    /**
     * Returns the value of field 'edificio'. The field 'edificio'
     * has the following description: Dati relativi all'edificio.
     * Sono tutti
     *  opzionali, ma potrebbe essere più sensato
     *  renderne obbligatorio almeno uno.
     *  
     * 
     * @return the value of field 'Edificio'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Edificio getEdificio(
    ) {
        return this._edificio;
    }

    /**
     * Returns the value of field 'indirizzo'. The field
     * 'indirizzo' has the following description: Questo tipo
     * descrive un indirizzo di biblioteca.
     *  È opzionale come tutti i suoi sotto-elementi, ma
     *  forse almeno uno dovrebbe essere reso
     *  obbligatorio, altrimenti si potrebbe istanziare
     *  un "indirizzo" vuoto.
     *  
     * 
     * @return the value of field 'Indirizzo'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Indirizzo getIndirizzo(
    ) {
        return this._indirizzo;
    }

    /**
     * Returns the value of field 'istituzione'. The field
     * 'istituzione' has the following description: Data in cui è
     * stata istituita formalmente
     *  l'attuale biblioteca. È possibile anche indicare
     *  l'anno di prima istituzione o fondazione, nel
     *  caso che la biblioteca abbia avuto nel tempo
     *  gestioni amministrative diverse.
     *  
     * 
     * @return the value of field 'Istituzione'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Istituzione getIstituzione(
    ) {
        return this._istituzione;
    }

    /**
     * Returns the value of field 'nome'. The field 'nome' has the
     * following description: Nomi di una biblioteca. È un insieme
     * in cui solo
     *  il nome attuale è obbligatorio e non ripetibile,
     *  mentre quelli precedenti e quelli alternativi
     *  sono opzionali e ripetibili.
     *  
     * 
     * @return the value of field 'Nome'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Nome getNome(
    ) {
        return this._nome;
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
     * Sets the value of field 'codici'. The field 'codici' has the
     * following description: Codici di una biblioteca.
     *  
     * 
     * @param codici the value of field 'codici'.
     */
    public void setCodici(
            final it.inera.abi.logic.formatodiscambio.castor.Codici codici) {
        this._codici = codici;
    }

    /**
     * Sets the value of field 'contatti'. The field 'contatti' has
     * the following description: Varie modalità per contattare la
     * biblioteca e il
     *  suo personale.
     *  
     * 
     * @param contatti the value of field 'contatti'.
     */
    public void setContatti(
            final it.inera.abi.logic.formatodiscambio.castor.Contatti contatti) {
        this._contatti = contatti;
    }

    /**
     * Sets the value of field 'dataAggiornamento'. The field
     * 'dataAggiornamento' has the following description: Ultima
     * data in cui è stata modificata la biblioteca (dal
     * gestionale) (x export ICCU).
     *  Formato: AAAA-MM-GGTHH:mm:ss
     *  
     * 
     * @param dataAggiornamento the value of field
     * 'dataAggiornamento'.
     */
    public void setDataAggiornamento(
            final java.util.Date dataAggiornamento) {
        this._dataAggiornamento = dataAggiornamento;
    }

    /**
     * Sets the value of field 'dataCensimento'. The field
     * 'dataCensimento' has the following description: Data in cui
     * è stata censita la biblioteca (raccolta dati).
     *  Formato: AAAA
     *  
     * 
     * @param dataCensimento the value of field 'dataCensimento'.
     */
    public void setDataCensimento(
            final it.inera.abi.logic.formatodiscambio.castor.DataCensimento dataCensimento) {
        this._dataCensimento = dataCensimento;
    }

    /**
     * Sets the value of field 'edificio'. The field 'edificio' has
     * the following description: Dati relativi all'edificio. Sono
     * tutti
     *  opzionali, ma potrebbe essere più sensato
     *  renderne obbligatorio almeno uno.
     *  
     * 
     * @param edificio the value of field 'edificio'.
     */
    public void setEdificio(
            final it.inera.abi.logic.formatodiscambio.castor.Edificio edificio) {
        this._edificio = edificio;
    }

    /**
     * Sets the value of field 'indirizzo'. The field 'indirizzo'
     * has the following description: Questo tipo descrive un
     * indirizzo di biblioteca.
     *  È opzionale come tutti i suoi sotto-elementi, ma
     *  forse almeno uno dovrebbe essere reso
     *  obbligatorio, altrimenti si potrebbe istanziare
     *  un "indirizzo" vuoto.
     *  
     * 
     * @param indirizzo the value of field 'indirizzo'.
     */
    public void setIndirizzo(
            final it.inera.abi.logic.formatodiscambio.castor.Indirizzo indirizzo) {
        this._indirizzo = indirizzo;
    }

    /**
     * Sets the value of field 'istituzione'. The field
     * 'istituzione' has the following description: Data in cui è
     * stata istituita formalmente
     *  l'attuale biblioteca. È possibile anche indicare
     *  l'anno di prima istituzione o fondazione, nel
     *  caso che la biblioteca abbia avuto nel tempo
     *  gestioni amministrative diverse.
     *  
     * 
     * @param istituzione the value of field 'istituzione'.
     */
    public void setIstituzione(
            final it.inera.abi.logic.formatodiscambio.castor.Istituzione istituzione) {
        this._istituzione = istituzione;
    }

    /**
     * Sets the value of field 'nome'. The field 'nome' has the
     * following description: Nomi di una biblioteca. È un insieme
     * in cui solo
     *  il nome attuale è obbligatorio e non ripetibile,
     *  mentre quelli precedenti e quelli alternativi
     *  sono opzionali e ripetibili.
     *  
     * 
     * @param nome the value of field 'nome'.
     */
    public void setNome(
            final it.inera.abi.logic.formatodiscambio.castor.Nome nome) {
        this._nome = nome;
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
