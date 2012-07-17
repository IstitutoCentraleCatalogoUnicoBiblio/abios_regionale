/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: Ente.java,v 1.2 2012/07/17 09:09:28 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Dati relativi all'ente da cui la biblioteca
 *  dipende. Almeno il nome è obbligatorio. Inoltre,
 *  andrebbe discussa l'opportunità di fare
 *  riferimento ad una lista controllata di enti,
 *  individuati tramite il solo codice fiscale o la
 *  partita IVA. Devono esistere certamente simili
 *  liste, e le diverse basi dati dovrebbero
 *  attenersi ad esse, almeno in parte.
 *  
 *  La funzione-obiettivo corrisponde al COFOG.
 *  
 * 
 * @version $Revision: 1.2 $ $Date: 2012/07/17 09:09:28 $
 */
@SuppressWarnings("serial")
public class Ente implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _nome.
     */
    private java.lang.String _nome;

    /**
     * Field _tipologiaAmministrativa.
     */
    private java.lang.String _tipologiaAmministrativa;

    /**
     * Field _tipologiaFunzionale.
     */
    private java.lang.String _tipologiaFunzionale;

    /**
     * Field _stato.
     */
    private java.lang.String _stato;

    /**
     * Field _codiceFiscale.
     */
    private java.lang.String _codiceFiscale;

    /**
     * Field _partitaIVA.
     */
    private java.lang.String _partitaIVA;

    /**
     * Field _funzioneObiettivo.
     */
    private java.lang.String _funzioneObiettivo;


      //----------------/
     //- Constructors -/
    //----------------/

    public Ente() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'codiceFiscale'.
     * 
     * @return the value of field 'CodiceFiscale'.
     */
    public java.lang.String getCodiceFiscale(
    ) {
        return this._codiceFiscale;
    }

    /**
     * Returns the value of field 'funzioneObiettivo'.
     * 
     * @return the value of field 'FunzioneObiettivo'.
     */
    public java.lang.String getFunzioneObiettivo(
    ) {
        return this._funzioneObiettivo;
    }

    /**
     * Returns the value of field 'nome'.
     * 
     * @return the value of field 'Nome'.
     */
    public java.lang.String getNome(
    ) {
        return this._nome;
    }

    /**
     * Returns the value of field 'partitaIVA'.
     * 
     * @return the value of field 'PartitaIVA'.
     */
    public java.lang.String getPartitaIVA(
    ) {
        return this._partitaIVA;
    }

    /**
     * Returns the value of field 'stato'.
     * 
     * @return the value of field 'Stato'.
     */
    public java.lang.String getStato(
    ) {
        return this._stato;
    }

    /**
     * Returns the value of field 'tipologiaAmministrativa'.
     * 
     * @return the value of field 'TipologiaAmministrativa'.
     */
    public java.lang.String getTipologiaAmministrativa(
    ) {
        return this._tipologiaAmministrativa;
    }

    /**
     * Returns the value of field 'tipologiaFunzionale'.
     * 
     * @return the value of field 'TipologiaFunzionale'.
     */
    public java.lang.String getTipologiaFunzionale(
    ) {
        return this._tipologiaFunzionale;
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
     * Sets the value of field 'codiceFiscale'.
     * 
     * @param codiceFiscale the value of field 'codiceFiscale'.
     */
    public void setCodiceFiscale(
            final java.lang.String codiceFiscale) {
        this._codiceFiscale = codiceFiscale;
    }

    /**
     * Sets the value of field 'funzioneObiettivo'.
     * 
     * @param funzioneObiettivo the value of field
     * 'funzioneObiettivo'.
     */
    public void setFunzioneObiettivo(
            final java.lang.String funzioneObiettivo) {
        this._funzioneObiettivo = funzioneObiettivo;
    }

    /**
     * Sets the value of field 'nome'.
     * 
     * @param nome the value of field 'nome'.
     */
    public void setNome(
            final java.lang.String nome) {
        this._nome = nome;
    }

    /**
     * Sets the value of field 'partitaIVA'.
     * 
     * @param partitaIVA the value of field 'partitaIVA'.
     */
    public void setPartitaIVA(
            final java.lang.String partitaIVA) {
        this._partitaIVA = partitaIVA;
    }

    /**
     * Sets the value of field 'stato'.
     * 
     * @param stato the value of field 'stato'.
     */
    public void setStato(
            final java.lang.String stato) {
        this._stato = stato;
    }

    /**
     * Sets the value of field 'tipologiaAmministrativa'.
     * 
     * @param tipologiaAmministrativa the value of field
     * 'tipologiaAmministrativa'.
     */
    public void setTipologiaAmministrativa(
            final java.lang.String tipologiaAmministrativa) {
        this._tipologiaAmministrativa = tipologiaAmministrativa;
    }

    /**
     * Sets the value of field 'tipologiaFunzionale'.
     * 
     * @param tipologiaFunzionale the value of field
     * 'tipologiaFunzionale'.
     */
    public void setTipologiaFunzionale(
            final java.lang.String tipologiaFunzionale) {
        this._tipologiaFunzionale = tipologiaFunzionale;
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
     * it.inera.abi.logic.formatodiscambio.castor.Ente
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Ente unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Ente) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Ente.class, reader);
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
