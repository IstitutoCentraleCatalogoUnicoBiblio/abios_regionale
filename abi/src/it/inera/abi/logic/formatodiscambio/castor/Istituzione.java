/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: Istituzione.java,v 1.1 2012/06/22 13:55:04 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Data in cui è stata istituita formalmente
 *  l'attuale biblioteca. È possibile anche indicare
 *  l'anno di prima istituzione o fondazione, nel
 *  caso che la biblioteca abbia avuto nel tempo
 *  gestioni amministrative diverse.
 *  
 * 
 * @version $Revision: 1.1 $ $Date: 2012/06/22 13:55:04 $
 */
@SuppressWarnings("serial")
public class Istituzione implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _dataIstituzione.
     */
    private it.inera.abi.logic.formatodiscambio.castor.DataIstituzione _dataIstituzione;

    /**
     * Field _dataFondazione.
     */
    private it.inera.abi.logic.formatodiscambio.castor.DataFondazione _dataFondazione;


      //----------------/
     //- Constructors -/
    //----------------/

    public Istituzione() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'dataFondazione'.
     * 
     * @return the value of field 'DataFondazione'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.DataFondazione getDataFondazione(
    ) {
        return this._dataFondazione;
    }

    /**
     * Returns the value of field 'dataIstituzione'.
     * 
     * @return the value of field 'DataIstituzione'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.DataIstituzione getDataIstituzione(
    ) {
        return this._dataIstituzione;
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
     * Sets the value of field 'dataFondazione'.
     * 
     * @param dataFondazione the value of field 'dataFondazione'.
     */
    public void setDataFondazione(
            final it.inera.abi.logic.formatodiscambio.castor.DataFondazione dataFondazione) {
        this._dataFondazione = dataFondazione;
    }

    /**
     * Sets the value of field 'dataIstituzione'.
     * 
     * @param dataIstituzione the value of field 'dataIstituzione'.
     */
    public void setDataIstituzione(
            final it.inera.abi.logic.formatodiscambio.castor.DataIstituzione dataIstituzione) {
        this._dataIstituzione = dataIstituzione;
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
     * it.inera.abi.logic.formatodiscambio.castor.Istituzione
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Istituzione unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Istituzione) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Istituzione.class, reader);
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
