/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: Riproduzione.java,v 1.1 2012/07/30 15:17:05 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Con questo elemento si dà la possibilità di 
 *  esprimere le modalità di riprodurre i vari
 *  materiali ammessi al prestito locale, 
 *  nazionale e/o internazionale. 
 *  
 * 
 * @version $Revision: 1.1 $ $Date: 2012/07/30 15:17:05 $
 */
@SuppressWarnings("serial")
public class Riproduzione implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _tipo.
     */
    private java.lang.String _tipo;

    /**
     * Field _locale.
     */
    private it.inera.abi.logic.formatodiscambio.castor.types.SiNoType _locale;

    /**
     * Field _nazionale.
     */
    private it.inera.abi.logic.formatodiscambio.castor.types.SiNoType _nazionale;

    /**
     * Field _internazionale.
     */
    private it.inera.abi.logic.formatodiscambio.castor.types.SiNoType _internazionale;


      //----------------/
     //- Constructors -/
    //----------------/

    public Riproduzione() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'internazionale'.
     * 
     * @return the value of field 'Internazionale'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.types.SiNoType getInternazionale(
    ) {
        return this._internazionale;
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
     * Returns the value of field 'nazionale'.
     * 
     * @return the value of field 'Nazionale'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.types.SiNoType getNazionale(
    ) {
        return this._nazionale;
    }

    /**
     * Returns the value of field 'tipo'.
     * 
     * @return the value of field 'Tipo'.
     */
    public java.lang.String getTipo(
    ) {
        return this._tipo;
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
     * Sets the value of field 'internazionale'.
     * 
     * @param internazionale the value of field 'internazionale'.
     */
    public void setInternazionale(
            final it.inera.abi.logic.formatodiscambio.castor.types.SiNoType internazionale) {
        this._internazionale = internazionale;
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
     * Sets the value of field 'nazionale'.
     * 
     * @param nazionale the value of field 'nazionale'.
     */
    public void setNazionale(
            final it.inera.abi.logic.formatodiscambio.castor.types.SiNoType nazionale) {
        this._nazionale = nazionale;
    }

    /**
     * Sets the value of field 'tipo'.
     * 
     * @param tipo the value of field 'tipo'.
     */
    public void setTipo(
            final java.lang.String tipo) {
        this._tipo = tipo;
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
     * it.inera.abi.logic.formatodiscambio.castor.Riproduzione
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Riproduzione unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Riproduzione) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Riproduzione.class, reader);
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
