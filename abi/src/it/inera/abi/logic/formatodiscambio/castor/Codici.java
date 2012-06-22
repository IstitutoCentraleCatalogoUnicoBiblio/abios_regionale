/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: Codici.java,v 1.1 2012/06/22 13:55:04 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Codici di una biblioteca.
 *  
 *  È un elemento che raggruppa diversi codici.
 *  Nessuno è ripetibile. I nomi degli elementi
 *  dovrebbero essere auto-esplicativi.
 *  
 *  Si sfruttano qui le possibilità offerte da XML
 *  Schema per effettuare una prima validazione dei
 *  codici attraverso opportune espressioni
 *  regolari. È però importante capire che si tratta
 *  di una validazione superficiale: ad esempio, per
 *  i codici basati sulle sigle di provincia non
 *  viene controllato che la sigla sia corretta, ma
 *  solo del tipo "AA".
 *  
 * 
 * @version $Revision: 1.1 $ $Date: 2012/06/22 13:55:04 $
 */
@SuppressWarnings("serial")
public class Codici implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Il codice "iccu" può essere omesso
     *  nel caso la biblioteca in esame non
     *  sia mai stata censita dall'ICCU.
     *  Essa sarà trattata come una nuova
     *  biblioteca, come avviene tramite
     *  l'applicativo web dell'ICCU. Per
     *  questo motivo, è necessario che
     *  nell'anagrafica sia specificata la
     *  provincia, altrimenti non è
     *  possibile assegnare la parte
     *  iniziale del codice.
     *  Nota r.eschini: tolte tutte le restirction e messo a
     * required il codice iccu
     *  
     */
    private java.lang.String _iccu;

    /**
     * Field _acnp.
     */
    private java.lang.String _acnp;

    /**
     * Field _rism.
     */
    private java.lang.String _rism;

    /**
     * Field _sbn.
     */
    private java.lang.String _sbn;

    /**
     * Field _cei.
     */
    private java.lang.String _cei;

    /**
     * Field _cmbs.
     */
    private java.lang.String _cmbs;


      //----------------/
     //- Constructors -/
    //----------------/

    public Codici() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'acnp'.
     * 
     * @return the value of field 'Acnp'.
     */
    public java.lang.String getAcnp(
    ) {
        return this._acnp;
    }

    /**
     * Returns the value of field 'cei'.
     * 
     * @return the value of field 'Cei'.
     */
    public java.lang.String getCei(
    ) {
        return this._cei;
    }

    /**
     * Returns the value of field 'cmbs'.
     * 
     * @return the value of field 'Cmbs'.
     */
    public java.lang.String getCmbs(
    ) {
        return this._cmbs;
    }

    /**
     * Returns the value of field 'iccu'. The field 'iccu' has the
     * following description: Il codice "iccu" può essere omesso
     *  nel caso la biblioteca in esame non
     *  sia mai stata censita dall'ICCU.
     *  Essa sarà trattata come una nuova
     *  biblioteca, come avviene tramite
     *  l'applicativo web dell'ICCU. Per
     *  questo motivo, è necessario che
     *  nell'anagrafica sia specificata la
     *  provincia, altrimenti non è
     *  possibile assegnare la parte
     *  iniziale del codice.
     *  Nota r.eschini: tolte tutte le restirction e messo a
     * required il codice iccu
     *  
     * 
     * @return the value of field 'Iccu'.
     */
    public java.lang.String getIccu(
    ) {
        return this._iccu;
    }

    /**
     * Returns the value of field 'rism'.
     * 
     * @return the value of field 'Rism'.
     */
    public java.lang.String getRism(
    ) {
        return this._rism;
    }

    /**
     * Returns the value of field 'sbn'.
     * 
     * @return the value of field 'Sbn'.
     */
    public java.lang.String getSbn(
    ) {
        return this._sbn;
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
     * Sets the value of field 'acnp'.
     * 
     * @param acnp the value of field 'acnp'.
     */
    public void setAcnp(
            final java.lang.String acnp) {
        this._acnp = acnp;
    }

    /**
     * Sets the value of field 'cei'.
     * 
     * @param cei the value of field 'cei'.
     */
    public void setCei(
            final java.lang.String cei) {
        this._cei = cei;
    }

    /**
     * Sets the value of field 'cmbs'.
     * 
     * @param cmbs the value of field 'cmbs'.
     */
    public void setCmbs(
            final java.lang.String cmbs) {
        this._cmbs = cmbs;
    }

    /**
     * Sets the value of field 'iccu'. The field 'iccu' has the
     * following description: Il codice "iccu" può essere omesso
     *  nel caso la biblioteca in esame non
     *  sia mai stata censita dall'ICCU.
     *  Essa sarà trattata come una nuova
     *  biblioteca, come avviene tramite
     *  l'applicativo web dell'ICCU. Per
     *  questo motivo, è necessario che
     *  nell'anagrafica sia specificata la
     *  provincia, altrimenti non è
     *  possibile assegnare la parte
     *  iniziale del codice.
     *  Nota r.eschini: tolte tutte le restirction e messo a
     * required il codice iccu
     *  
     * 
     * @param iccu the value of field 'iccu'.
     */
    public void setIccu(
            final java.lang.String iccu) {
        this._iccu = iccu;
    }

    /**
     * Sets the value of field 'rism'.
     * 
     * @param rism the value of field 'rism'.
     */
    public void setRism(
            final java.lang.String rism) {
        this._rism = rism;
    }

    /**
     * Sets the value of field 'sbn'.
     * 
     * @param sbn the value of field 'sbn'.
     */
    public void setSbn(
            final java.lang.String sbn) {
        this._sbn = sbn;
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
     * it.inera.abi.logic.formatodiscambio.castor.Codici
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Codici unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Codici) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Codici.class, reader);
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
