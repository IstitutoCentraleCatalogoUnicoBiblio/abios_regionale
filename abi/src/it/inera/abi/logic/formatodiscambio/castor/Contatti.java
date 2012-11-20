/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: Contatti.java,v 1.7 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Varie modalità per contattare la biblioteca e il suo personale.
 *  
 * 
 * @version $Revision: 1.7 $ $Date: 2012/11/22 09:50:20 $
 */
@SuppressWarnings("serial")
public class Contatti implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * E' stato introdotto l'elemento ripetibile
     *  'telefonici' come contenitore degli 
     *  eventuali contatti telefonici.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.Telefonici _telefonici;

    /**
     * E' stato introdotto l'elemento ripetibile
     *  'altri' come contenitore degli eventuali
     *  altri contatti telefonici.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.Altri _altri;


      //----------------/
     //- Constructors -/
    //----------------/

    public Contatti() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'altri'. The field 'altri' has
     * the following description: E' stato introdotto l'elemento
     * ripetibile
     *  'altri' come contenitore degli eventuali
     *  altri contatti telefonici.
     *  
     * 
     * @return the value of field 'Altri'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Altri getAltri(
    ) {
        return this._altri;
    }

    /**
     * Returns the value of field 'telefonici'. The field
     * 'telefonici' has the following description: E' stato
     * introdotto l'elemento ripetibile
     *  'telefonici' come contenitore degli 
     *  eventuali contatti telefonici.
     *  
     * 
     * @return the value of field 'Telefonici'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Telefonici getTelefonici(
    ) {
        return this._telefonici;
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
     * Sets the value of field 'altri'. The field 'altri' has the
     * following description: E' stato introdotto l'elemento
     * ripetibile
     *  'altri' come contenitore degli eventuali
     *  altri contatti telefonici.
     *  
     * 
     * @param altri the value of field 'altri'.
     */
    public void setAltri(
            final it.inera.abi.logic.formatodiscambio.castor.Altri altri) {
        this._altri = altri;
    }

    /**
     * Sets the value of field 'telefonici'. The field 'telefonici'
     * has the following description: E' stato introdotto
     * l'elemento ripetibile
     *  'telefonici' come contenitore degli 
     *  eventuali contatti telefonici.
     *  
     * 
     * @param telefonici the value of field 'telefonici'.
     */
    public void setTelefonici(
            final it.inera.abi.logic.formatodiscambio.castor.Telefonici telefonici) {
        this._telefonici = telefonici;
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
     * it.inera.abi.logic.formatodiscambio.castor.Contatti
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Contatti unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Contatti) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Contatti.class, reader);
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
