/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: Scaffalature.java,v 1.3 2012/07/30 15:17:05 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * I valori dei sotto-elementi sono
 *  ovviamente da intendersi in metri.
 *  Trattandosi di decimali, non sono
 *  ammessi valori come "30m" o "30
 *  metri".
 *  
 * 
 * @version $Revision: 1.3 $ $Date: 2012/07/30 15:17:05 $
 */
@SuppressWarnings("serial")
public class Scaffalature implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _magazzino.
     */
    private java.math.BigDecimal _magazzino;

    /**
     * Field _pubbliche.
     */
    private java.math.BigDecimal _pubbliche;


      //----------------/
     //- Constructors -/
    //----------------/

    public Scaffalature() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'magazzino'.
     * 
     * @return the value of field 'Magazzino'.
     */
    public java.math.BigDecimal getMagazzino(
    ) {
        return this._magazzino;
    }

    /**
     * Returns the value of field 'pubbliche'.
     * 
     * @return the value of field 'Pubbliche'.
     */
    public java.math.BigDecimal getPubbliche(
    ) {
        return this._pubbliche;
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
     * Sets the value of field 'magazzino'.
     * 
     * @param magazzino the value of field 'magazzino'.
     */
    public void setMagazzino(
            final java.math.BigDecimal magazzino) {
        this._magazzino = magazzino;
    }

    /**
     * Sets the value of field 'pubbliche'.
     * 
     * @param pubbliche the value of field 'pubbliche'.
     */
    public void setPubbliche(
            final java.math.BigDecimal pubbliche) {
        this._pubbliche = pubbliche;
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
     * it.inera.abi.logic.formatodiscambio.castor.Scaffalature
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Scaffalature unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Scaffalature) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Scaffalature.class, reader);
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
