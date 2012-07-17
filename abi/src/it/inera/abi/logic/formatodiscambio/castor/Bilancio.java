/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: Bilancio.java,v 1.4 2012/07/31 15:00:07 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Informazioni relative al bilancio della
 *  biblioteca.
 *  
 * 
 * @version $Revision: 1.4 $ $Date: 2012/07/31 15:00:07 $
 */
@SuppressWarnings("serial")
public class Bilancio implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _entrate.
     */
    private java.math.BigDecimal _entrate;

    /**
     * Field _uscite.
     */
    private it.inera.abi.logic.formatodiscambio.castor.Uscite _uscite;


      //----------------/
     //- Constructors -/
    //----------------/

    public Bilancio() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'entrate'.
     * 
     * @return the value of field 'Entrate'.
     */
    public java.math.BigDecimal getEntrate(
    ) {
        return this._entrate;
    }

    /**
     * Returns the value of field 'uscite'.
     * 
     * @return the value of field 'Uscite'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Uscite getUscite(
    ) {
        return this._uscite;
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
     * Sets the value of field 'entrate'.
     * 
     * @param entrate the value of field 'entrate'.
     */
    public void setEntrate(
            final java.math.BigDecimal entrate) {
        this._entrate = entrate;
    }

    /**
     * Sets the value of field 'uscite'.
     * 
     * @param uscite the value of field 'uscite'.
     */
    public void setUscite(
            final it.inera.abi.logic.formatodiscambio.castor.Uscite uscite) {
        this._uscite = uscite;
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
     * it.inera.abi.logic.formatodiscambio.castor.Bilancio
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Bilancio unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Bilancio) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Bilancio.class, reader);
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
