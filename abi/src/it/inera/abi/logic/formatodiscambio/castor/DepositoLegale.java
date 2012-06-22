/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: DepositoLegale.java,v 1.1 2012/06/22 13:55:03 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Class DepositoLegale.
 * 
 * @version $Revision: 1.1 $ $Date: 2012/06/22 13:55:03 $
 */
@SuppressWarnings("serial")
public class DepositoLegale implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _tipo.
     */
    private java.lang.String _tipo;

    /**
     * Field _annoInizio.
     */
    private java.lang.String _annoInizio;


      //----------------/
     //- Constructors -/
    //----------------/

    public DepositoLegale() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'annoInizio'.
     * 
     * @return the value of field 'AnnoInizio'.
     */
    public java.lang.String getAnnoInizio(
    ) {
        return this._annoInizio;
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
     * Sets the value of field 'annoInizio'.
     * 
     * @param annoInizio the value of field 'annoInizio'.
     */
    public void setAnnoInizio(
            final java.lang.String annoInizio) {
        this._annoInizio = annoInizio;
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
     * it.inera.abi.logic.formatodiscambio.castor.DepositoLegale
     */
    public static it.inera.abi.logic.formatodiscambio.castor.DepositoLegale unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.DepositoLegale) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.DepositoLegale.class, reader);
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
