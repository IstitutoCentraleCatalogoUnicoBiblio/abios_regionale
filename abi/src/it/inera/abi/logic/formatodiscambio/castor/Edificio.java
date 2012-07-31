/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: Edificio.java,v 1.7 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Dati relativi all'edificio. Sono tutti
 *  opzionali,
 *  ma potrebbe essere più sensato
 *  renderne obbligatorio almeno uno.
 *  
 * 
 * @version $Revision: 1.7 $ $Date: 2012/11/22 09:50:20 $
 */
@SuppressWarnings("serial")
public class Edificio implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _denominazione.
     */
    private java.lang.String _denominazione;

    /**
     * Field _monumentale.
     */
    private it.inera.abi.logic.formatodiscambio.castor.types.SiNoType _monumentale;

    /**
     * Field _appositamenteCostruito.
     */
    private it.inera.abi.logic.formatodiscambio.castor.types.SiNoType _appositamenteCostruito;

    /**
     * Field _dataCostruzione.
     */
    private it.inera.abi.logic.formatodiscambio.castor.DataCostruzione _dataCostruzione;


      //----------------/
     //- Constructors -/
    //----------------/

    public Edificio() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'appositamenteCostruito'.
     * 
     * @return the value of field 'AppositamenteCostruito'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.types.SiNoType getAppositamenteCostruito(
    ) {
        return this._appositamenteCostruito;
    }

    /**
     * Returns the value of field 'dataCostruzione'.
     * 
     * @return the value of field 'DataCostruzione'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.DataCostruzione getDataCostruzione(
    ) {
        return this._dataCostruzione;
    }

    /**
     * Returns the value of field 'denominazione'.
     * 
     * @return the value of field 'Denominazione'.
     */
    public java.lang.String getDenominazione(
    ) {
        return this._denominazione;
    }

    /**
     * Returns the value of field 'monumentale'.
     * 
     * @return the value of field 'Monumentale'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.types.SiNoType getMonumentale(
    ) {
        return this._monumentale;
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
     * Sets the value of field 'appositamenteCostruito'.
     * 
     * @param appositamenteCostruito the value of field
     * 'appositamenteCostruito'.
     */
    public void setAppositamenteCostruito(
            final it.inera.abi.logic.formatodiscambio.castor.types.SiNoType appositamenteCostruito) {
        this._appositamenteCostruito = appositamenteCostruito;
    }

    /**
     * Sets the value of field 'dataCostruzione'.
     * 
     * @param dataCostruzione the value of field 'dataCostruzione'.
     */
    public void setDataCostruzione(
            final it.inera.abi.logic.formatodiscambio.castor.DataCostruzione dataCostruzione) {
        this._dataCostruzione = dataCostruzione;
    }

    /**
     * Sets the value of field 'denominazione'.
     * 
     * @param denominazione the value of field 'denominazione'.
     */
    public void setDenominazione(
            final java.lang.String denominazione) {
        this._denominazione = denominazione;
    }

    /**
     * Sets the value of field 'monumentale'.
     * 
     * @param monumentale the value of field 'monumentale'.
     */
    public void setMonumentale(
            final it.inera.abi.logic.formatodiscambio.castor.types.SiNoType monumentale) {
        this._monumentale = monumentale;
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
     * it.inera.abi.logic.formatodiscambio.castor.Edificio
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Edificio unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Edificio) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Edificio.class, reader);
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
