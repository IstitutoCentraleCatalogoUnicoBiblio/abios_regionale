/*
 * Author Inera srl https://www.inera.it
 * Copyright (C) 2023  Inera srl https://www.inera.it/
 *
 * European Union Public Licence V. 1.2
 * EUPL (c) the European Community 2017
 *
 * This European Union Public Licence (the "EUPL") applies to the Work or Software (as defined below) which is provided under the terms of this Licence.
 * Any use of the Work, other than as authorised under this Licence is prohibited (to the extent such use is covered by a right of the copyright holder of the Work).
 * The Original Work is provided under the terms of this Licence when the Licensor (as defined below) has placed the following notice immediately following the copyright notice for the Original Work:
 * Licensed under the EUPL V.1.2 or has expressed by any other mean his willingness to license under the EUPL.
 *
 * You should have received a copy of the European Union Public Licence V. 1.2 along with this program.  If not, see https://eupl.eu/1.2/en/
 */

/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: Accesso.java,v 1.7 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Riguarda le modalità di accesso alla biblioteca,
 *  sia in termini logistici (e.g. portatori di
 *  handicap), sia in
 *  termini amministrativi (chi
 *  può accedere ai servizi e a quali
 *  condizioni può
 *  farlo).
 *  
 * 
 * @version $Revision: 1.7 $ $Date: 2012/11/22 09:50:20 $
 */
@SuppressWarnings("serial")
public class Accesso implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * L'elemento segnala semplicemente la
     *  possibilità di accedere liberamente alla
     *  biblioteca. In caso contrario la biblioteca
     *  risulta essere riservata.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.types.SiNoType _aperta;

    /**
     * La presenza di questo elemento, che
     *  può essere
     *  vuoto, segnala
     *  semplicemente la possibilità di
     *  accesso ai
     *  portatori di handicap, ma
     *  per ora non viene qui attribuito
     *  alcun
     *  significato particolare al suo
     *  eventuale contenuto.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.types.SiNoType _handicap;

    /**
     * Modalità di accesso. Le condizioni raggruppate 
     *  sono intese in AND logico.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.ModalitaAccesso _modalitaAccesso;

    /**
     * Field _destinazioniSociali.
     */
    private it.inera.abi.logic.formatodiscambio.castor.DestinazioniSociali _destinazioniSociali;


      //----------------/
     //- Constructors -/
    //----------------/

    public Accesso() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'aperta'. The field 'aperta' has
     * the following description: L'elemento segnala semplicemente
     * la
     *  possibilità di accedere liberamente alla
     *  biblioteca. In caso contrario la biblioteca
     *  risulta essere riservata.
     *  
     * 
     * @return the value of field 'Aperta'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.types.SiNoType getAperta(
    ) {
        return this._aperta;
    }

    /**
     * Returns the value of field 'destinazioniSociali'.
     * 
     * @return the value of field 'DestinazioniSociali'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.DestinazioniSociali getDestinazioniSociali(
    ) {
        return this._destinazioniSociali;
    }

    /**
     * Returns the value of field 'handicap'. The field 'handicap'
     * has the following description: La presenza di questo
     * elemento, che
     *  può essere
     *  vuoto, segnala
     *  semplicemente la possibilità di
     *  accesso ai
     *  portatori di handicap, ma
     *  per ora non viene qui attribuito
     *  alcun
     *  significato particolare al suo
     *  eventuale contenuto.
     *  
     * 
     * @return the value of field 'Handicap'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.types.SiNoType getHandicap(
    ) {
        return this._handicap;
    }

    /**
     * Returns the value of field 'modalitaAccesso'. The field
     * 'modalitaAccesso' has the following description: Modalità di
     * accesso. Le condizioni raggruppate 
     *  sono intese in AND logico.
     *  
     * 
     * @return the value of field 'ModalitaAccesso'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.ModalitaAccesso getModalitaAccesso(
    ) {
        return this._modalitaAccesso;
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
     * Sets the value of field 'aperta'. The field 'aperta' has the
     * following description: L'elemento segnala semplicemente la
     *  possibilità di accedere liberamente alla
     *  biblioteca. In caso contrario la biblioteca
     *  risulta essere riservata.
     *  
     * 
     * @param aperta the value of field 'aperta'.
     */
    public void setAperta(
            final it.inera.abi.logic.formatodiscambio.castor.types.SiNoType aperta) {
        this._aperta = aperta;
    }

    /**
     * Sets the value of field 'destinazioniSociali'.
     * 
     * @param destinazioniSociali the value of field
     * 'destinazioniSociali'.
     */
    public void setDestinazioniSociali(
            final it.inera.abi.logic.formatodiscambio.castor.DestinazioniSociali destinazioniSociali) {
        this._destinazioniSociali = destinazioniSociali;
    }

    /**
     * Sets the value of field 'handicap'. The field 'handicap' has
     * the following description: La presenza di questo elemento,
     * che
     *  può essere
     *  vuoto, segnala
     *  semplicemente la possibilità di
     *  accesso ai
     *  portatori di handicap, ma
     *  per ora non viene qui attribuito
     *  alcun
     *  significato particolare al suo
     *  eventuale contenuto.
     *  
     * 
     * @param handicap the value of field 'handicap'.
     */
    public void setHandicap(
            final it.inera.abi.logic.formatodiscambio.castor.types.SiNoType handicap) {
        this._handicap = handicap;
    }

    /**
     * Sets the value of field 'modalitaAccesso'. The field
     * 'modalitaAccesso' has the following description: Modalità di
     * accesso. Le condizioni raggruppate 
     *  sono intese in AND logico.
     *  
     * 
     * @param modalitaAccesso the value of field 'modalitaAccesso'.
     */
    public void setModalitaAccesso(
            final it.inera.abi.logic.formatodiscambio.castor.ModalitaAccesso modalitaAccesso) {
        this._modalitaAccesso = modalitaAccesso;
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
     * it.inera.abi.logic.formatodiscambio.castor.Accesso
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Accesso unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Accesso) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Accesso.class, reader);
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
