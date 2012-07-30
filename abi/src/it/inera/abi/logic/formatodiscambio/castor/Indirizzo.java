/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: Indirizzo.java,v 1.3 2012/07/30 15:17:04 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Questo tipo descrive un indirizzo di biblioteca.
 *  È opzionale come tutti i suoi sotto-elementi, ma
 *  forse almeno uno dovrebbe essere reso
 *  obbligatorio, altrimenti si potrebbe istanziare
 *  un "indirizzo" vuoto.
 *  
 *  Per il CAP, il comune, la provincia, la regione
 *  e lo stato valgono le osservazioni fatte circa i
 *  codici di biblioteca: la validazione si ferma
 *  alla semplice struttura.
 *  
 *  Per comuni e provincie si adottano i codici
 *  ISTAT. Per lo stato si usa il codice ISO di due
 *  lettere, senza distinguere fra maiuscolo e
 *  minuscolo.
 *  
 *  Al momento non viene fatto alcun controllo sulla
 *  regione, ma è un elemento che la base dati
 *  ricevente dovrebbe essere in grado di ricavare a
 *  partire dal solo codice ISTAT del comune,
 *  attraverso proprie tabelle di raggruppamento.
 *  
 *  Inserito nuovo elemento, opzionale, coordinate contenente
 *  due attributi (se specificati, devono essere valorizzati
 *  entrambi), latitudine e longitudine.
 *  
 * 
 * @version $Revision: 1.3 $ $Date: 2012/07/30 15:17:04 $
 */
@SuppressWarnings("serial")
public class Indirizzo implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _via.
     */
    private java.lang.String _via;

    /**
     * Field _cap.
     */
    private java.lang.String _cap;

    /**
     * Field _frazione.
     */
    private java.lang.String _frazione;

    /**
     * L'elemento comune adesso è obbligatorio.
     *  
     */
    private java.lang.String _comune;

    /**
     * Field _provincia.
     */
    private java.lang.String _provincia;

    /**
     * Field _regione.
     */
    private java.lang.String _regione;

    /**
     * Field _stato.
     */
    private java.lang.String _stato;

    /**
     * Field _coordinate.
     */
    private it.inera.abi.logic.formatodiscambio.castor.Coordinate _coordinate;


      //----------------/
     //- Constructors -/
    //----------------/

    public Indirizzo() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'cap'.
     * 
     * @return the value of field 'Cap'.
     */
    public java.lang.String getCap(
    ) {
        return this._cap;
    }

    /**
     * Returns the value of field 'comune'. The field 'comune' has
     * the following description: L'elemento comune adesso è
     * obbligatorio.
     *  
     * 
     * @return the value of field 'Comune'.
     */
    public java.lang.String getComune(
    ) {
        return this._comune;
    }

    /**
     * Returns the value of field 'coordinate'.
     * 
     * @return the value of field 'Coordinate'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Coordinate getCoordinate(
    ) {
        return this._coordinate;
    }

    /**
     * Returns the value of field 'frazione'.
     * 
     * @return the value of field 'Frazione'.
     */
    public java.lang.String getFrazione(
    ) {
        return this._frazione;
    }

    /**
     * Returns the value of field 'provincia'.
     * 
     * @return the value of field 'Provincia'.
     */
    public java.lang.String getProvincia(
    ) {
        return this._provincia;
    }

    /**
     * Returns the value of field 'regione'.
     * 
     * @return the value of field 'Regione'.
     */
    public java.lang.String getRegione(
    ) {
        return this._regione;
    }

    /**
     * Returns the value of field 'stato'.
     * 
     * @return the value of field 'Stato'.
     */
    public java.lang.String getStato(
    ) {
        return this._stato;
    }

    /**
     * Returns the value of field 'via'.
     * 
     * @return the value of field 'Via'.
     */
    public java.lang.String getVia(
    ) {
        return this._via;
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
     * 
     * 
     * @param out
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void marshal(
            final java.io.Writer out)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Marshaller.marshal(this, out);
    }

    /**
     * 
     * 
     * @param handler
     * @throws java.io.IOException if an IOException occurs during
     * marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     */
    public void marshal(
            final org.xml.sax.ContentHandler handler)
    throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Marshaller.marshal(this, handler);
    }

    /**
     * Sets the value of field 'cap'.
     * 
     * @param cap the value of field 'cap'.
     */
    public void setCap(
            final java.lang.String cap) {
        this._cap = cap;
    }

    /**
     * Sets the value of field 'comune'. The field 'comune' has the
     * following description: L'elemento comune adesso è
     * obbligatorio.
     *  
     * 
     * @param comune the value of field 'comune'.
     */
    public void setComune(
            final java.lang.String comune) {
        this._comune = comune;
    }

    /**
     * Sets the value of field 'coordinate'.
     * 
     * @param coordinate the value of field 'coordinate'.
     */
    public void setCoordinate(
            final it.inera.abi.logic.formatodiscambio.castor.Coordinate coordinate) {
        this._coordinate = coordinate;
    }

    /**
     * Sets the value of field 'frazione'.
     * 
     * @param frazione the value of field 'frazione'.
     */
    public void setFrazione(
            final java.lang.String frazione) {
        this._frazione = frazione;
    }

    /**
     * Sets the value of field 'provincia'.
     * 
     * @param provincia the value of field 'provincia'.
     */
    public void setProvincia(
            final java.lang.String provincia) {
        this._provincia = provincia;
    }

    /**
     * Sets the value of field 'regione'.
     * 
     * @param regione the value of field 'regione'.
     */
    public void setRegione(
            final java.lang.String regione) {
        this._regione = regione;
    }

    /**
     * Sets the value of field 'stato'.
     * 
     * @param stato the value of field 'stato'.
     */
    public void setStato(
            final java.lang.String stato) {
        this._stato = stato;
    }

    /**
     * Sets the value of field 'via'.
     * 
     * @param via the value of field 'via'.
     */
    public void setVia(
            final java.lang.String via) {
        this._via = via;
    }

    /**
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled
     * it.inera.abi.logic.formatodiscambio.castor.Indirizzo
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Indirizzo unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Indirizzo) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Indirizzo.class, reader);
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
