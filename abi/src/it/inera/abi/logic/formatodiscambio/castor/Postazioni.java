/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: Postazioni.java,v 1.4 2012/07/31 15:00:08 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Le postazioni si dividono in diversi
 *  elementi,
 *  tutti opzionali. Se un
 *  elemento è presente, deve avere un
 *  valore, e questo è forse scomodo se
 *  si vuole solo indicare che
 *  la
 *  biblioteca dispone genericamente di
 *  postazioni internet o
 *  audio, senza
 *  indicare quante. Per consentire la
 *  semplice
 *  segnalazione si dovrebbe
 *  usare un costrutto più complesso.
 *  
 * 
 * @version $Revision: 1.4 $ $Date: 2012/07/31 15:00:08 $
 */
@SuppressWarnings("serial")
public class Postazioni implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _lettura.
     */
    private long _lettura;

    /**
     * keeps track of state for field: _lettura
     */
    private boolean _has_lettura;

    /**
     * Field _video.
     */
    private long _video;

    /**
     * keeps track of state for field: _video
     */
    private boolean _has_video;

    /**
     * Field _audio.
     */
    private long _audio;

    /**
     * keeps track of state for field: _audio
     */
    private boolean _has_audio;

    /**
     * Field _internet.
     */
    private long _internet;

    /**
     * keeps track of state for field: _internet
     */
    private boolean _has_internet;


      //----------------/
     //- Constructors -/
    //----------------/

    public Postazioni() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deleteAudio(
    ) {
        this._has_audio= false;
    }

    /**
     */
    public void deleteInternet(
    ) {
        this._has_internet= false;
    }

    /**
     */
    public void deleteLettura(
    ) {
        this._has_lettura= false;
    }

    /**
     */
    public void deleteVideo(
    ) {
        this._has_video= false;
    }

    /**
     * Returns the value of field 'audio'.
     * 
     * @return the value of field 'Audio'.
     */
    public long getAudio(
    ) {
        return this._audio;
    }

    /**
     * Returns the value of field 'internet'.
     * 
     * @return the value of field 'Internet'.
     */
    public long getInternet(
    ) {
        return this._internet;
    }

    /**
     * Returns the value of field 'lettura'.
     * 
     * @return the value of field 'Lettura'.
     */
    public long getLettura(
    ) {
        return this._lettura;
    }

    /**
     * Returns the value of field 'video'.
     * 
     * @return the value of field 'Video'.
     */
    public long getVideo(
    ) {
        return this._video;
    }

    /**
     * Method hasAudio.
     * 
     * @return true if at least one Audio has been added
     */
    public boolean hasAudio(
    ) {
        return this._has_audio;
    }

    /**
     * Method hasInternet.
     * 
     * @return true if at least one Internet has been added
     */
    public boolean hasInternet(
    ) {
        return this._has_internet;
    }

    /**
     * Method hasLettura.
     * 
     * @return true if at least one Lettura has been added
     */
    public boolean hasLettura(
    ) {
        return this._has_lettura;
    }

    /**
     * Method hasVideo.
     * 
     * @return true if at least one Video has been added
     */
    public boolean hasVideo(
    ) {
        return this._has_video;
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
     * Sets the value of field 'audio'.
     * 
     * @param audio the value of field 'audio'.
     */
    public void setAudio(
            final long audio) {
        this._audio = audio;
        this._has_audio = true;
    }

    /**
     * Sets the value of field 'internet'.
     * 
     * @param internet the value of field 'internet'.
     */
    public void setInternet(
            final long internet) {
        this._internet = internet;
        this._has_internet = true;
    }

    /**
     * Sets the value of field 'lettura'.
     * 
     * @param lettura the value of field 'lettura'.
     */
    public void setLettura(
            final long lettura) {
        this._lettura = lettura;
        this._has_lettura = true;
    }

    /**
     * Sets the value of field 'video'.
     * 
     * @param video the value of field 'video'.
     */
    public void setVideo(
            final long video) {
        this._video = video;
        this._has_video = true;
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
     * it.inera.abi.logic.formatodiscambio.castor.Postazioni
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Postazioni unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Postazioni) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Postazioni.class, reader);
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
