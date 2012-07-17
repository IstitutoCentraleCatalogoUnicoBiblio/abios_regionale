/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: Coordinate.java,v 1.6 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Class Coordinate.
 * 
 * @version $Revision: 1.6 $ $Date: 2012/11/22 09:50:20 $
 */
@SuppressWarnings("serial")
public class Coordinate implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _latitudine.
     */
    private double _latitudine;

    /**
     * keeps track of state for field: _latitudine
     */
    private boolean _has_latitudine;

    /**
     * Field _longitudine.
     */
    private double _longitudine;

    /**
     * keeps track of state for field: _longitudine
     */
    private boolean _has_longitudine;


      //----------------/
     //- Constructors -/
    //----------------/

    public Coordinate() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deleteLatitudine(
    ) {
        this._has_latitudine= false;
    }

    /**
     */
    public void deleteLongitudine(
    ) {
        this._has_longitudine= false;
    }

    /**
     * Returns the value of field 'latitudine'.
     * 
     * @return the value of field 'Latitudine'.
     */
    public double getLatitudine(
    ) {
        return this._latitudine;
    }

    /**
     * Returns the value of field 'longitudine'.
     * 
     * @return the value of field 'Longitudine'.
     */
    public double getLongitudine(
    ) {
        return this._longitudine;
    }

    /**
     * Method hasLatitudine.
     * 
     * @return true if at least one Latitudine has been added
     */
    public boolean hasLatitudine(
    ) {
        return this._has_latitudine;
    }

    /**
     * Method hasLongitudine.
     * 
     * @return true if at least one Longitudine has been added
     */
    public boolean hasLongitudine(
    ) {
        return this._has_longitudine;
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
     * Sets the value of field 'latitudine'.
     * 
     * @param latitudine the value of field 'latitudine'.
     */
    public void setLatitudine(
            final double latitudine) {
        this._latitudine = latitudine;
        this._has_latitudine = true;
    }

    /**
     * Sets the value of field 'longitudine'.
     * 
     * @param longitudine the value of field 'longitudine'.
     */
    public void setLongitudine(
            final double longitudine) {
        this._longitudine = longitudine;
        this._has_longitudine = true;
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
     * it.inera.abi.logic.formatodiscambio.castor.Coordinate
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Coordinate unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Coordinate) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Coordinate.class, reader);
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
