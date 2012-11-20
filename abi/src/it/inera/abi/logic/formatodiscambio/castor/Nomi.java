/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: Nomi.java,v 1.4 2012/11/22 09:50:20 m.bartolozzi Exp $
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
 * @version $Revision: 1.4 $ $Date: 2012/11/22 09:50:20 $
 */
@SuppressWarnings("serial")
public class Nomi implements java.io.Serializable {


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
     *  'alternativi' come contenitore delle
     *  eventuali denominazioni alternative.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.Alternativi _alternativi;


      //----------------/
     //- Constructors -/
    //----------------/

    public Nomi() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'alternativi'. The field
     * 'alternativi' has the following description: E' stato
     * introdotto l'elemento ripetibile
     *  'alternativi' come contenitore delle
     *  eventuali denominazioni alternative.
     *  
     * 
     * @return the value of field 'Alternativi'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Alternativi getAlternativi(
    ) {
        return this._alternativi;
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
     * Sets the value of field 'alternativi'. The field
     * 'alternativi' has the following description: E' stato
     * introdotto l'elemento ripetibile
     *  'alternativi' come contenitore delle
     *  eventuali denominazioni alternative.
     *  
     * 
     * @param alternativi the value of field 'alternativi'.
     */
    public void setAlternativi(
            final it.inera.abi.logic.formatodiscambio.castor.Alternativi alternativi) {
        this._alternativi = alternativi;
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
     * it.inera.abi.logic.formatodiscambio.castor.Nomi
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Nomi unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Nomi) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Nomi.class, reader);
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
