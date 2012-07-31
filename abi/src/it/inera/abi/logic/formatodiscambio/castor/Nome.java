/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: Nome.java,v 1.4 2012/07/31 15:00:07 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Nomi di una biblioteca. È un insieme in cui solo
 *  il nome attuale è obbligatorio e non ripetibile,
 *  mentre quelli
 *  precedenti e quelli alternativi
 *  sono opzionali e ripetibili.
 *  
 * 
 * @version $Revision: 1.4 $ $Date: 2012/07/31 15:00:07 $
 */
@SuppressWarnings("serial")
public class Nome implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _attuale.
     */
    private java.lang.String _attuale;

    /**
     * E' stato introdotto l'elemento ripetibile
     *  'precedenti' come
     *  contenitore
     *  delle eventuali precedenti
     *  denominazioni.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.Precedenti _precedenti;

    /**
     * E' stato introdotto l'elemento ripetibile
     *  'alternative' come
     *  contenitore
     *  delle eventuali denominazioni
     *  alternative.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.Alternative _alternative;


      //----------------/
     //- Constructors -/
    //----------------/

    public Nome() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'alternative'. The field
     * 'alternative' has the following description: E' stato
     * introdotto l'elemento ripetibile
     *  'alternative' come
     *  contenitore
     *  delle eventuali denominazioni
     *  alternative.
     *  
     * 
     * @return the value of field 'Alternative'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Alternative getAlternative(
    ) {
        return this._alternative;
    }

    /**
     * Returns the value of field 'attuale'.
     * 
     * @return the value of field 'Attuale'.
     */
    public java.lang.String getAttuale(
    ) {
        return this._attuale;
    }

    /**
     * Returns the value of field 'precedenti'. The field
     * 'precedenti' has the following description: E' stato
     * introdotto l'elemento ripetibile
     *  'precedenti' come
     *  contenitore
     *  delle eventuali precedenti
     *  denominazioni.
     *  
     * 
     * @return the value of field 'Precedenti'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Precedenti getPrecedenti(
    ) {
        return this._precedenti;
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
     * Sets the value of field 'alternative'. The field
     * 'alternative' has the following description: E' stato
     * introdotto l'elemento ripetibile
     *  'alternative' come
     *  contenitore
     *  delle eventuali denominazioni
     *  alternative.
     *  
     * 
     * @param alternative the value of field 'alternative'.
     */
    public void setAlternative(
            final it.inera.abi.logic.formatodiscambio.castor.Alternative alternative) {
        this._alternative = alternative;
    }

    /**
     * Sets the value of field 'attuale'.
     * 
     * @param attuale the value of field 'attuale'.
     */
    public void setAttuale(
            final java.lang.String attuale) {
        this._attuale = attuale;
    }

    /**
     * Sets the value of field 'precedenti'. The field 'precedenti'
     * has the following description: E' stato introdotto
     * l'elemento ripetibile
     *  'precedenti' come
     *  contenitore
     *  delle eventuali precedenti
     *  denominazioni.
     *  
     * 
     * @param precedenti the value of field 'precedenti'.
     */
    public void setPrecedenti(
            final it.inera.abi.logic.formatodiscambio.castor.Precedenti precedenti) {
        this._precedenti = precedenti;
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
     * it.inera.abi.logic.formatodiscambio.castor.Nome
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Nome unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Nome) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Nome.class, reader);
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
