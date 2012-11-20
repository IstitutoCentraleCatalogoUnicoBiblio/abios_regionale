/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: Reference.java,v 1.4 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Servizio di reference: se disponibile, può essere locale o 
 *  online (email, telefono, fax, ecc...).
 *  Presente attributo 'attivo' del tipo siNoType.
 *  
 * 
 * @version $Revision: 1.4 $ $Date: 2012/11/22 09:50:20 $
 */
@SuppressWarnings("serial")
public class Reference implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _attivo.
     */
    private it.inera.abi.logic.formatodiscambio.castor.types.SiNoType _attivo;

    /**
     * Field _locale.
     */
    private it.inera.abi.logic.formatodiscambio.castor.types.SiNoType _locale;

    /**
     * Field _online.
     */
    private it.inera.abi.logic.formatodiscambio.castor.types.SiNoType _online;


      //----------------/
     //- Constructors -/
    //----------------/

    public Reference() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'attivo'.
     * 
     * @return the value of field 'Attivo'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.types.SiNoType getAttivo(
    ) {
        return this._attivo;
    }

    /**
     * Returns the value of field 'locale'.
     * 
     * @return the value of field 'Locale'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.types.SiNoType getLocale(
    ) {
        return this._locale;
    }

    /**
     * Returns the value of field 'online'.
     * 
     * @return the value of field 'Online'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.types.SiNoType getOnline(
    ) {
        return this._online;
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
     * Sets the value of field 'attivo'.
     * 
     * @param attivo the value of field 'attivo'.
     */
    public void setAttivo(
            final it.inera.abi.logic.formatodiscambio.castor.types.SiNoType attivo) {
        this._attivo = attivo;
    }

    /**
     * Sets the value of field 'locale'.
     * 
     * @param locale the value of field 'locale'.
     */
    public void setLocale(
            final it.inera.abi.logic.formatodiscambio.castor.types.SiNoType locale) {
        this._locale = locale;
    }

    /**
     * Sets the value of field 'online'.
     * 
     * @param online the value of field 'online'.
     */
    public void setOnline(
            final it.inera.abi.logic.formatodiscambio.castor.types.SiNoType online) {
        this._online = online;
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
     * it.inera.abi.logic.formatodiscambio.castor.Reference
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Reference unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Reference) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Reference.class, reader);
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
