/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: Chiusura.java,v 1.3 2012/07/30 15:17:05 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Un periodo di chiusura è
 *  semplicemente una nota esplicativa (e
 *  del periodo di validità, attualmente deprecato e non 
 *  considerato in import e export). La nota esplicativa
 *  può essere di qualsiasi forma. (dal-al, periodo natalizio,...).
 *  
 * 
 * @version $Revision: 1.3 $ $Date: 2012/07/30 15:17:05 $
 */
@SuppressWarnings("serial")
public class Chiusura implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _periodo.
     */
    private java.lang.String _periodo;

    /**
     * Field _note.
     */
    private java.lang.String _note;


      //----------------/
     //- Constructors -/
    //----------------/

    public Chiusura() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'note'.
     * 
     * @return the value of field 'Note'.
     */
    public java.lang.String getNote(
    ) {
        return this._note;
    }

    /**
     * Returns the value of field 'periodo'.
     * 
     * @return the value of field 'Periodo'.
     */
    public java.lang.String getPeriodo(
    ) {
        return this._periodo;
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
     * Sets the value of field 'note'.
     * 
     * @param note the value of field 'note'.
     */
    public void setNote(
            final java.lang.String note) {
        this._note = note;
    }

    /**
     * Sets the value of field 'periodo'.
     * 
     * @param periodo the value of field 'periodo'.
     */
    public void setPeriodo(
            final java.lang.String periodo) {
        this._periodo = periodo;
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
     * it.inera.abi.logic.formatodiscambio.castor.Chiusura
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Chiusura unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Chiusura) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Chiusura.class, reader);
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
