/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: Internet.java,v 1.2 2012/07/17 09:09:28 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Accesso a Internet da postazioni locali. La
 *  modalità "limitato" potrebbe essere fonte di
 *  equivoci. In ogni caso, la modalità è opzionale
 *  e ripetibile.
 *  NB. MODIFICATO TIPO ENUMERATO 
 *  
 *  L'elemento modo opzionale e ripetibile è stato
 *  sostituito da tre flag: a tempo, a pagamento, con proxy.
 *  
 * 
 * @version $Revision: 1.2 $ $Date: 2012/07/17 09:09:28 $
 */
@SuppressWarnings("serial")
public class Internet implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _aTempo.
     */
    private it.inera.abi.logic.formatodiscambio.castor.types.SiNoType _aTempo;

    /**
     * Field _aPagamento.
     */
    private it.inera.abi.logic.formatodiscambio.castor.types.SiNoType _aPagamento;

    /**
     * Field _conProxy.
     */
    private it.inera.abi.logic.formatodiscambio.castor.types.SiNoType _conProxy;


      //----------------/
     //- Constructors -/
    //----------------/

    public Internet() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'aPagamento'.
     * 
     * @return the value of field 'APagamento'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.types.SiNoType getAPagamento(
    ) {
        return this._aPagamento;
    }

    /**
     * Returns the value of field 'aTempo'.
     * 
     * @return the value of field 'ATempo'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.types.SiNoType getATempo(
    ) {
        return this._aTempo;
    }

    /**
     * Returns the value of field 'conProxy'.
     * 
     * @return the value of field 'ConProxy'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.types.SiNoType getConProxy(
    ) {
        return this._conProxy;
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
     * Sets the value of field 'aPagamento'.
     * 
     * @param aPagamento the value of field 'aPagamento'.
     */
    public void setAPagamento(
            final it.inera.abi.logic.formatodiscambio.castor.types.SiNoType aPagamento) {
        this._aPagamento = aPagamento;
    }

    /**
     * Sets the value of field 'aTempo'.
     * 
     * @param aTempo the value of field 'aTempo'.
     */
    public void setATempo(
            final it.inera.abi.logic.formatodiscambio.castor.types.SiNoType aTempo) {
        this._aTempo = aTempo;
    }

    /**
     * Sets the value of field 'conProxy'.
     * 
     * @param conProxy the value of field 'conProxy'.
     */
    public void setConProxy(
            final it.inera.abi.logic.formatodiscambio.castor.types.SiNoType conProxy) {
        this._conProxy = conProxy;
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
     * it.inera.abi.logic.formatodiscambio.castor.Internet
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Internet unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Internet) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Internet.class, reader);
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
