/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: Altro.java,v 1.3 2012/07/30 15:17:05 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * I contatti di questo tipo prevedono
 *  solo le note e un valore, oltre a un
 *  attributo "tipo" che, al momento,
 *  consente di specificare solo un
 *  indirizzo e-mail, una URL o un
 *  telex. Ovviamente la stringa
 *  "valore" può contenere qualsiasi
 *  cosa, quindi non c'è garanzia che
 *  tale stringa sia conforme al tipo
 *  specificato.
 *  
 *  Le note sono opzionali e ripetibili,
 *  sebbene la ripetibilità sia
 *  discutibile. Il valore è invece
 *  obbligatorio e non ripetibile,
 *  altrimenti si potrebbe istanziare un
 *  elemento vuoto.
 *  
 *  Una linea di sviluppo potrebbe
 *  prevedere dei pattern per la
 *  validazione dei valori. Questo
 *  comporterebbe però l'uso di
 *  sotto-elementi invece che di
 *  attributi, altrimenti non sarebbe
 *  possibile vincolare i valori con
 *  pattern.
 *  
 * 
 * @version $Revision: 1.3 $ $Date: 2012/07/30 15:17:05 $
 */
@SuppressWarnings("serial")
public class Altro implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _tipo.
     */
    private it.inera.abi.logic.formatodiscambio.castor.types.AltroTipoType _tipo;

    /**
     * Field _valore.
     */
    private java.lang.String _valore;

    /**
     * Field _note.
     */
    private java.lang.String _note;


      //----------------/
     //- Constructors -/
    //----------------/

    public Altro() {
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
     * Returns the value of field 'tipo'.
     * 
     * @return the value of field 'Tipo'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.types.AltroTipoType getTipo(
    ) {
        return this._tipo;
    }

    /**
     * Returns the value of field 'valore'.
     * 
     * @return the value of field 'Valore'.
     */
    public java.lang.String getValore(
    ) {
        return this._valore;
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
     * Sets the value of field 'tipo'.
     * 
     * @param tipo the value of field 'tipo'.
     */
    public void setTipo(
            final it.inera.abi.logic.formatodiscambio.castor.types.AltroTipoType tipo) {
        this._tipo = tipo;
    }

    /**
     * Sets the value of field 'valore'.
     * 
     * @param valore the value of field 'valore'.
     */
    public void setValore(
            final java.lang.String valore) {
        this._valore = valore;
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
     * it.inera.abi.logic.formatodiscambio.castor.Altro
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Altro unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Altro) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Altro.class, reader);
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
