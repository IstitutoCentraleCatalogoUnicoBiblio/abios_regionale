/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: Orario.java,v 1.1 2012/06/22 13:55:04 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Class Orario.
 * 
 * @version $Revision: 1.1 $ $Date: 2012/06/22 13:55:04 $
 */
@SuppressWarnings("serial")
public class Orario implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _giorno.
     */
    private it.inera.abi.logic.formatodiscambio.castor.types.OrarioGiornoType _giorno;

    /**
     * Field _dalle.
     */
    private java.lang.String _dalle;

    /**
     * Field _alle.
     */
    private java.lang.String _alle;


      //----------------/
     //- Constructors -/
    //----------------/

    public Orario() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'alle'.
     * 
     * @return the value of field 'Alle'.
     */
    public java.lang.String getAlle(
    ) {
        return this._alle;
    }

    /**
     * Returns the value of field 'dalle'.
     * 
     * @return the value of field 'Dalle'.
     */
    public java.lang.String getDalle(
    ) {
        return this._dalle;
    }

    /**
     * Returns the value of field 'giorno'.
     * 
     * @return the value of field 'Giorno'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.types.OrarioGiornoType getGiorno(
    ) {
        return this._giorno;
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
     * Sets the value of field 'alle'.
     * 
     * @param alle the value of field 'alle'.
     */
    public void setAlle(
            final java.lang.String alle) {
        this._alle = alle;
    }

    /**
     * Sets the value of field 'dalle'.
     * 
     * @param dalle the value of field 'dalle'.
     */
    public void setDalle(
            final java.lang.String dalle) {
        this._dalle = dalle;
    }

    /**
     * Sets the value of field 'giorno'.
     * 
     * @param giorno the value of field 'giorno'.
     */
    public void setGiorno(
            final it.inera.abi.logic.formatodiscambio.castor.types.OrarioGiornoType giorno) {
        this._giorno = giorno;
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
     * it.inera.abi.logic.formatodiscambio.castor.Orario
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Orario unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Orario) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Orario.class, reader);
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
