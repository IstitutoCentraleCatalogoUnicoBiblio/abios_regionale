/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: Forme.java,v 1.1 2012/06/22 13:55:04 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Class Forme.
 * 
 * @version $Revision: 1.1 $ $Date: 2012/06/22 13:55:04 $
 */
@SuppressWarnings("serial")
public class Forme implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _schede.
     */
    private it.inera.abi.logic.formatodiscambio.castor.Schede _schede;

    /**
     * Field _volume.
     */
    private it.inera.abi.logic.formatodiscambio.castor.Volume _volume;

    /**
     * Field _microforme.
     */
    private it.inera.abi.logic.formatodiscambio.castor.Microforme _microforme;

    /**
     * Field _digitale.
     */
    private it.inera.abi.logic.formatodiscambio.castor.Digitale _digitale;


      //----------------/
     //- Constructors -/
    //----------------/

    public Forme() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'digitale'.
     * 
     * @return the value of field 'Digitale'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Digitale getDigitale(
    ) {
        return this._digitale;
    }

    /**
     * Returns the value of field 'microforme'.
     * 
     * @return the value of field 'Microforme'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Microforme getMicroforme(
    ) {
        return this._microforme;
    }

    /**
     * Returns the value of field 'schede'.
     * 
     * @return the value of field 'Schede'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Schede getSchede(
    ) {
        return this._schede;
    }

    /**
     * Returns the value of field 'volume'.
     * 
     * @return the value of field 'Volume'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Volume getVolume(
    ) {
        return this._volume;
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
     * Sets the value of field 'digitale'.
     * 
     * @param digitale the value of field 'digitale'.
     */
    public void setDigitale(
            final it.inera.abi.logic.formatodiscambio.castor.Digitale digitale) {
        this._digitale = digitale;
    }

    /**
     * Sets the value of field 'microforme'.
     * 
     * @param microforme the value of field 'microforme'.
     */
    public void setMicroforme(
            final it.inera.abi.logic.formatodiscambio.castor.Microforme microforme) {
        this._microforme = microforme;
    }

    /**
     * Sets the value of field 'schede'.
     * 
     * @param schede the value of field 'schede'.
     */
    public void setSchede(
            final it.inera.abi.logic.formatodiscambio.castor.Schede schede) {
        this._schede = schede;
    }

    /**
     * Sets the value of field 'volume'.
     * 
     * @param volume the value of field 'volume'.
     */
    public void setVolume(
            final it.inera.abi.logic.formatodiscambio.castor.Volume volume) {
        this._volume = volume;
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
     * it.inera.abi.logic.formatodiscambio.castor.Forme
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Forme unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Forme) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Forme.class, reader);
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
