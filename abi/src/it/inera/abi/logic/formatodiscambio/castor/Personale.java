/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: Personale.java,v 1.1 2012/06/22 13:55:04 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Informazioni relative al personale impiegato a
 *  vario titolo nella biblioteca.
 *  
 * 
 * @version $Revision: 1.1 $ $Date: 2012/06/22 13:55:04 $
 */
@SuppressWarnings("serial")
public class Personale implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _totale.
     */
    private long _totale;

    /**
     * keeps track of state for field: _totale
     */
    private boolean _has_totale;

    /**
     * Field _temporaneo.
     */
    private long _temporaneo;

    /**
     * keeps track of state for field: _temporaneo
     */
    private boolean _has_temporaneo;

    /**
     * Field _partTime.
     */
    private long _partTime;

    /**
     * keeps track of state for field: _partTime
     */
    private boolean _has_partTime;

    /**
     * Field _esterno.
     */
    private long _esterno;

    /**
     * keeps track of state for field: _esterno
     */
    private boolean _has_esterno;


      //----------------/
     //- Constructors -/
    //----------------/

    public Personale() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deleteEsterno(
    ) {
        this._has_esterno= false;
    }

    /**
     */
    public void deletePartTime(
    ) {
        this._has_partTime= false;
    }

    /**
     */
    public void deleteTemporaneo(
    ) {
        this._has_temporaneo= false;
    }

    /**
     */
    public void deleteTotale(
    ) {
        this._has_totale= false;
    }

    /**
     * Returns the value of field 'esterno'.
     * 
     * @return the value of field 'Esterno'.
     */
    public long getEsterno(
    ) {
        return this._esterno;
    }

    /**
     * Returns the value of field 'partTime'.
     * 
     * @return the value of field 'PartTime'.
     */
    public long getPartTime(
    ) {
        return this._partTime;
    }

    /**
     * Returns the value of field 'temporaneo'.
     * 
     * @return the value of field 'Temporaneo'.
     */
    public long getTemporaneo(
    ) {
        return this._temporaneo;
    }

    /**
     * Returns the value of field 'totale'.
     * 
     * @return the value of field 'Totale'.
     */
    public long getTotale(
    ) {
        return this._totale;
    }

    /**
     * Method hasEsterno.
     * 
     * @return true if at least one Esterno has been added
     */
    public boolean hasEsterno(
    ) {
        return this._has_esterno;
    }

    /**
     * Method hasPartTime.
     * 
     * @return true if at least one PartTime has been added
     */
    public boolean hasPartTime(
    ) {
        return this._has_partTime;
    }

    /**
     * Method hasTemporaneo.
     * 
     * @return true if at least one Temporaneo has been added
     */
    public boolean hasTemporaneo(
    ) {
        return this._has_temporaneo;
    }

    /**
     * Method hasTotale.
     * 
     * @return true if at least one Totale has been added
     */
    public boolean hasTotale(
    ) {
        return this._has_totale;
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
     * Sets the value of field 'esterno'.
     * 
     * @param esterno the value of field 'esterno'.
     */
    public void setEsterno(
            final long esterno) {
        this._esterno = esterno;
        this._has_esterno = true;
    }

    /**
     * Sets the value of field 'partTime'.
     * 
     * @param partTime the value of field 'partTime'.
     */
    public void setPartTime(
            final long partTime) {
        this._partTime = partTime;
        this._has_partTime = true;
    }

    /**
     * Sets the value of field 'temporaneo'.
     * 
     * @param temporaneo the value of field 'temporaneo'.
     */
    public void setTemporaneo(
            final long temporaneo) {
        this._temporaneo = temporaneo;
        this._has_temporaneo = true;
    }

    /**
     * Sets the value of field 'totale'.
     * 
     * @param totale the value of field 'totale'.
     */
    public void setTotale(
            final long totale) {
        this._totale = totale;
        this._has_totale = true;
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
     * it.inera.abi.logic.formatodiscambio.castor.Personale
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Personale unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Personale) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Personale.class, reader);
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
