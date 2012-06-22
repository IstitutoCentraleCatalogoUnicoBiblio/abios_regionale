/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: Copertura.java,v 1.1 2012/06/22 13:55:04 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * La copertura bibliografica deve essere espressa
 *  tramite un anno iniziale (elemento "da-anno") e
 *  un anno finale (elemento "ad-anno"). Entrambi
 *  devono avere il formato "YYYY", cioè esattamente
 *  quattro cifre (non è ammesso alcun altro
 *  carattere).
 *  
 * 
 * @version $Revision: 1.1 $ $Date: 2012/06/22 13:55:04 $
 */
@SuppressWarnings("serial")
public class Copertura implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _daAnno.
     */
    private java.lang.String _daAnno;

    /**
     * Field _adAnno.
     */
    private java.lang.String _adAnno;


      //----------------/
     //- Constructors -/
    //----------------/

    public Copertura() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'adAnno'.
     * 
     * @return the value of field 'AdAnno'.
     */
    public java.lang.String getAdAnno(
    ) {
        return this._adAnno;
    }

    /**
     * Returns the value of field 'daAnno'.
     * 
     * @return the value of field 'DaAnno'.
     */
    public java.lang.String getDaAnno(
    ) {
        return this._daAnno;
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
     * Sets the value of field 'adAnno'.
     * 
     * @param adAnno the value of field 'adAnno'.
     */
    public void setAdAnno(
            final java.lang.String adAnno) {
        this._adAnno = adAnno;
    }

    /**
     * Sets the value of field 'daAnno'.
     * 
     * @param daAnno the value of field 'daAnno'.
     */
    public void setDaAnno(
            final java.lang.String daAnno) {
        this._daAnno = daAnno;
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
     * it.inera.abi.logic.formatodiscambio.castor.Copertura
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Copertura unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Copertura) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Copertura.class, reader);
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
