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
 * $Id: AnagraficaType.java,v 1.7.4.1 2013/02/01 09:43:35 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Questo tipo raggruppa tutte le informazioni di tipo
 *  strettamente anagrafico.
 *  
 *  L'elemento anagrafica adesso è obbligatorio.
 *  
 * 
 * @version $Revision: 1.7.4.1 $ $Date: 2013/02/01 09:43:35 $
 */
@SuppressWarnings("serial")
public abstract class AnagraficaType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Data in cui è stata censita la biblioteca
     *  (raccolta dati).
     *  Formato: AAAA
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.DataCensimento _dataCensimento;

    /**
     * Ultima data in cui è stata modificata la
     *  biblioteca (dal gestionale) (x export ICCU).
     *  Formato:
     *  AAAA-MM-GGTHH:mm:ss
     *  
     */
    private java.util.Date _dataAggiornamento;

    /**
     * Fonte da cui proviene l'export della biblioteca
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.Fonte _fonte;

    /**
     * Nomi di una biblioteca. È un insieme in cui solo
     *  il nome attuale è obbligatorio e non ripetibile,
     *  mentre quelli
     *  precedenti e quelli alternativi
     *  sono opzionali e ripetibili.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.Nomi _nomi;

    /**
     * Codici di una biblioteca.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.Codici _codici;

    /**
     * Questo tipo descrive un indirizzo di biblioteca.
     *  È opzionale come tutti i suoi sotto-elementi, ma
     *  forse almeno uno
     *  dovrebbe essere reso
     *  obbligatorio, altrimenti si potrebbe
     *  istanziare
     *  un "indirizzo" vuoto.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.Indirizzo _indirizzo;

    /**
     * Varie modalità per contattare la biblioteca e il suo
     * personale.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.Contatti _contatti;

    /**
     * Dati relativi all'edificio. Sono tutti
     *  opzionali,
     *  ma potrebbe essere più sensato
     *  renderne obbligatorio almeno uno.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.Edificio _edificio;

    /**
     * Data in cui è stata istituita formalmente
     *  l'attuale biblioteca. È possibile anche indicare
     *  l'anno di prima
     *  istituzione o fondazione, nel
     *  caso che la biblioteca abbia avuto
     *  nel tempo
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
     * la biblioteca e il suo personale.
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
     * data in cui è stata modificata la
     *  biblioteca (dal gestionale) (x export ICCU).
     *  Formato:
     *  AAAA-MM-GGTHH:mm:ss
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
     * è stata censita la biblioteca
     *  (raccolta dati).
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
     *  opzionali,
     *  ma potrebbe essere più sensato
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
     * Returns the value of field 'fonte'. The field 'fonte' has
     * the following description: Fonte da cui proviene l'export
     * della biblioteca
     *  
     * 
     * @return the value of field 'Fonte'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Fonte getFonte(
    ) {
        return this._fonte;
    }

    /**
     * Returns the value of field 'indirizzo'. The field
     * 'indirizzo' has the following description: Questo tipo
     * descrive un indirizzo di biblioteca.
     *  È opzionale come tutti i suoi sotto-elementi, ma
     *  forse almeno uno
     *  dovrebbe essere reso
     *  obbligatorio, altrimenti si potrebbe
     *  istanziare
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
     *  l'anno di prima
     *  istituzione o fondazione, nel
     *  caso che la biblioteca abbia avuto
     *  nel tempo
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
     * Returns the value of field 'nomi'. The field 'nomi' has the
     * following description: Nomi di una biblioteca. È un insieme
     * in cui solo
     *  il nome attuale è obbligatorio e non ripetibile,
     *  mentre quelli
     *  precedenti e quelli alternativi
     *  sono opzionali e ripetibili.
     *  
     * 
     * @return the value of field 'Nomi'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Nomi getNomi(
    ) {
        return this._nomi;
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
     * biblioteca e il suo personale.
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
     * data in cui è stata modificata la
     *  biblioteca (dal gestionale) (x export ICCU).
     *  Formato:
     *  AAAA-MM-GGTHH:mm:ss
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
     * è stata censita la biblioteca
     *  (raccolta dati).
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
     *  opzionali,
     *  ma potrebbe essere più sensato
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
     * Sets the value of field 'fonte'. The field 'fonte' has the
     * following description: Fonte da cui proviene l'export della
     * biblioteca
     *  
     * 
     * @param fonte the value of field 'fonte'.
     */
    public void setFonte(
            final it.inera.abi.logic.formatodiscambio.castor.Fonte fonte) {
        this._fonte = fonte;
    }

    /**
     * Sets the value of field 'indirizzo'. The field 'indirizzo'
     * has the following description: Questo tipo descrive un
     * indirizzo di biblioteca.
     *  È opzionale come tutti i suoi sotto-elementi, ma
     *  forse almeno uno
     *  dovrebbe essere reso
     *  obbligatorio, altrimenti si potrebbe
     *  istanziare
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
     *  l'anno di prima
     *  istituzione o fondazione, nel
     *  caso che la biblioteca abbia avuto
     *  nel tempo
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
     * Sets the value of field 'nomi'. The field 'nomi' has the
     * following description: Nomi di una biblioteca. È un insieme
     * in cui solo
     *  il nome attuale è obbligatorio e non ripetibile,
     *  mentre quelli
     *  precedenti e quelli alternativi
     *  sono opzionali e ripetibili.
     *  
     * 
     * @param nomi the value of field 'nomi'.
     */
    public void setNomi(
            final it.inera.abi.logic.formatodiscambio.castor.Nomi nomi) {
        this._nomi = nomi;
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
