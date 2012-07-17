/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: CondizioniAccesso.java,v 1.3 2012/07/30 15:17:04 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Condizioni di accesso. Tutti i
 *  sotto-elementi sono opzionali, anche
 *  se avrebbe poco senso un elemento
 *  vuoto. I documenti, a loro volta,
 *  possono non essere specializzati.
 *  Questo potrebbe indicare la
 *  necessità di esibire un generico
 *  documento di riconoscimento, non
 *  meglio specificato.
 *  
 *  Le condizioni raggruppate andrebbero
 *  intese in AND logico. I documenti
 *  nell'apposito elemento sono invece
 *  equivalenti, cioè in OR logico.
 *  Infine, più elementi
 *  "condizioni-accesso", con gli
 *  opportuni sottoelementi, sono da
 *  considerarsi in OR logico.
 *  
 *  Ad esempio, il tipico caso "almeno
 *  18 anni e un documento valido" si
 *  traduce in opportuni elementi "età"
 *  e "documenti", ma senza un elemento
 *  "appuntamento". Se in alternativa la
 *  biblioteca è accessibile
 *  incondizionatamente per
 *  appuntamento, va aggiunto un
 *  apposito elemento
 *  "condizioni-accesso", col solo
 *  sotto-elemento "appuntamento".
 *  
 * 
 * @version $Revision: 1.3 $ $Date: 2012/07/30 15:17:04 $
 */
@SuppressWarnings("serial")
public class CondizioniAccesso implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _eta.
     */
    private it.inera.abi.logic.formatodiscambio.castor.Eta _eta;

    /**
     * Field _documenti.
     */
    private it.inera.abi.logic.formatodiscambio.castor.Documenti _documenti;

    /**
     * Field _appuntamento.
     */
    private java.lang.String _appuntamento;


      //----------------/
     //- Constructors -/
    //----------------/

    public CondizioniAccesso() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'appuntamento'.
     * 
     * @return the value of field 'Appuntamento'.
     */
    public java.lang.String getAppuntamento(
    ) {
        return this._appuntamento;
    }

    /**
     * Returns the value of field 'documenti'.
     * 
     * @return the value of field 'Documenti'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Documenti getDocumenti(
    ) {
        return this._documenti;
    }

    /**
     * Returns the value of field 'eta'.
     * 
     * @return the value of field 'Eta'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Eta getEta(
    ) {
        return this._eta;
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
     * Sets the value of field 'appuntamento'.
     * 
     * @param appuntamento the value of field 'appuntamento'.
     */
    public void setAppuntamento(
            final java.lang.String appuntamento) {
        this._appuntamento = appuntamento;
    }

    /**
     * Sets the value of field 'documenti'.
     * 
     * @param documenti the value of field 'documenti'.
     */
    public void setDocumenti(
            final it.inera.abi.logic.formatodiscambio.castor.Documenti documenti) {
        this._documenti = documenti;
    }

    /**
     * Sets the value of field 'eta'.
     * 
     * @param eta the value of field 'eta'.
     */
    public void setEta(
            final it.inera.abi.logic.formatodiscambio.castor.Eta eta) {
        this._eta = eta;
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
     * it.inera.abi.logic.formatodiscambio.castor.CondizioniAccesso
     */
    public static it.inera.abi.logic.formatodiscambio.castor.CondizioniAccesso unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.CondizioniAccesso) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.CondizioniAccesso.class, reader);
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
