/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: Uscite.java,v 1.7 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Class Uscite.
 * 
 * @version $Revision: 1.7 $ $Date: 2012/11/22 09:50:20 $
 */
@SuppressWarnings("serial")
public class Uscite implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _totale.
     */
    private java.math.BigDecimal _totale;

    /**
     * Field _personale.
     */
    private java.math.BigDecimal _personale;

    /**
     * Field _funzionamento.
     */
    private java.math.BigDecimal _funzionamento;

    /**
     * Field _automazione.
     */
    private java.math.BigDecimal _automazione;

    /**
     * Field _patrimonio.
     */
    private java.math.BigDecimal _patrimonio;

    /**
     * Field _altre.
     */
    private java.math.BigDecimal _altre;


      //----------------/
     //- Constructors -/
    //----------------/

    public Uscite() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'altre'.
     * 
     * @return the value of field 'Altre'.
     */
    public java.math.BigDecimal getAltre(
    ) {
        return this._altre;
    }

    /**
     * Returns the value of field 'automazione'.
     * 
     * @return the value of field 'Automazione'.
     */
    public java.math.BigDecimal getAutomazione(
    ) {
        return this._automazione;
    }

    /**
     * Returns the value of field 'funzionamento'.
     * 
     * @return the value of field 'Funzionamento'.
     */
    public java.math.BigDecimal getFunzionamento(
    ) {
        return this._funzionamento;
    }

    /**
     * Returns the value of field 'patrimonio'.
     * 
     * @return the value of field 'Patrimonio'.
     */
    public java.math.BigDecimal getPatrimonio(
    ) {
        return this._patrimonio;
    }

    /**
     * Returns the value of field 'personale'.
     * 
     * @return the value of field 'Personale'.
     */
    public java.math.BigDecimal getPersonale(
    ) {
        return this._personale;
    }

    /**
     * Returns the value of field 'totale'.
     * 
     * @return the value of field 'Totale'.
     */
    public java.math.BigDecimal getTotale(
    ) {
        return this._totale;
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
     * Sets the value of field 'altre'.
     * 
     * @param altre the value of field 'altre'.
     */
    public void setAltre(
            final java.math.BigDecimal altre) {
        this._altre = altre;
    }

    /**
     * Sets the value of field 'automazione'.
     * 
     * @param automazione the value of field 'automazione'.
     */
    public void setAutomazione(
            final java.math.BigDecimal automazione) {
        this._automazione = automazione;
    }

    /**
     * Sets the value of field 'funzionamento'.
     * 
     * @param funzionamento the value of field 'funzionamento'.
     */
    public void setFunzionamento(
            final java.math.BigDecimal funzionamento) {
        this._funzionamento = funzionamento;
    }

    /**
     * Sets the value of field 'patrimonio'.
     * 
     * @param patrimonio the value of field 'patrimonio'.
     */
    public void setPatrimonio(
            final java.math.BigDecimal patrimonio) {
        this._patrimonio = patrimonio;
    }

    /**
     * Sets the value of field 'personale'.
     * 
     * @param personale the value of field 'personale'.
     */
    public void setPersonale(
            final java.math.BigDecimal personale) {
        this._personale = personale;
    }

    /**
     * Sets the value of field 'totale'.
     * 
     * @param totale the value of field 'totale'.
     */
    public void setTotale(
            final java.math.BigDecimal totale) {
        this._totale = totale;
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
     * it.inera.abi.logic.formatodiscambio.castor.Uscite
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Uscite unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Uscite) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Uscite.class, reader);
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
