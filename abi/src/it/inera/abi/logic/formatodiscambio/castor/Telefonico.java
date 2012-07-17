/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: Telefonico.java,v 1.3 2012/07/30 15:17:05 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Un contatto di tipo telefonico. Il
 *  prefisso è ovviamente quello
 *  internazionale.
 *  
 *  Nelle note, opzionali e ripetibili,
 *  si dovrebbe indicare, ad esempio, se
 *  si tratta di un centralino, o del
 *  numero di una persona etc...
 *  L'attributo "tipo" serve a
 *  distinguere un telefono da un fax.
 *  
 *  Numero e prefisso sono obbligatori e
 *  non ripetibili, altrimenti non
 *  avrebbe senso istanziare questo
 *  elemento.
 *  
 *  Prefisso e numero sono validati in
 *  modo molto elementare.
 *  
 * 
 * @version $Revision: 1.3 $ $Date: 2012/07/30 15:17:05 $
 */
@SuppressWarnings("serial")
public class Telefonico implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _tipo.
     */
    private it.inera.abi.logic.formatodiscambio.castor.types.TelefonicoTipoType _tipo;

    /**
     * Field _prefisso.
     */
    private java.lang.String _prefisso;

    /**
     * Field _numero.
     */
    private java.lang.String _numero;

    /**
     * Field _note.
     */
    private java.lang.String _note;


      //----------------/
     //- Constructors -/
    //----------------/

    public Telefonico() {
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
     * Returns the value of field 'numero'.
     * 
     * @return the value of field 'Numero'.
     */
    public java.lang.String getNumero(
    ) {
        return this._numero;
    }

    /**
     * Returns the value of field 'prefisso'.
     * 
     * @return the value of field 'Prefisso'.
     */
    public java.lang.String getPrefisso(
    ) {
        return this._prefisso;
    }

    /**
     * Returns the value of field 'tipo'.
     * 
     * @return the value of field 'Tipo'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.types.TelefonicoTipoType getTipo(
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
     * Sets the value of field 'note'.
     * 
     * @param note the value of field 'note'.
     */
    public void setNote(
            final java.lang.String note) {
        this._note = note;
    }

    /**
     * Sets the value of field 'numero'.
     * 
     * @param numero the value of field 'numero'.
     */
    public void setNumero(
            final java.lang.String numero) {
        this._numero = numero;
    }

    /**
     * Sets the value of field 'prefisso'.
     * 
     * @param prefisso the value of field 'prefisso'.
     */
    public void setPrefisso(
            final java.lang.String prefisso) {
        this._prefisso = prefisso;
    }

    /**
     * Sets the value of field 'tipo'.
     * 
     * @param tipo the value of field 'tipo'.
     */
    public void setTipo(
            final it.inera.abi.logic.formatodiscambio.castor.types.TelefonicoTipoType tipo) {
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
     * it.inera.abi.logic.formatodiscambio.castor.Telefonico
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Telefonico unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Telefonico) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Telefonico.class, reader);
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
