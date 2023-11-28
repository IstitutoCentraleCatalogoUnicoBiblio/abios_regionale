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
 * $Id: Strutture.java,v 1.7 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Questo elemento e i suoi sotto-elementi
 *  descrivono sommariamente le strutture di cui la
 *  biblioteca dispone.
 *  
 * 
 * @version $Revision: 1.7 $ $Date: 2012/11/22 09:50:20 $
 */
@SuppressWarnings("serial")
public class Strutture implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * I valori dei sotto-elementi sono ovviamente da intendersi in
     * metri.
     *  Trattandosi di decimali, non sono ammessi valori come "30m"
     * o "30 metri".
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.Scaffalature _scaffalature;

    /**
     * I valori dei sotto-elementi sono ovviamente da
     *  intendersi in metri quadrati. Trattandosi di decimali,
     *  non sono ammessi valori come "100mq" o "100 metri quadri".
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.Superficie _superficie;

    /**
     * Le postazioni si dividono in diversi elementi, tutti
     * opzionali.
     *  Se un elemento è presente, deve avere un valore, e questo è
     * forse 
     *  scomodo se si vuole solo indicare che la biblioteca dispone
     * 
     *  genericamente di postazioni internet o audio, senza
     *  indicare quante. Per consentire la semplice segnalazione si
     * dovrebbe
     *  usare un costrutto più complesso.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.Postazioni _postazioni;


      //----------------/
     //- Constructors -/
    //----------------/

    public Strutture() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'postazioni'. The field
     * 'postazioni' has the following description: Le postazioni si
     * dividono in diversi elementi, tutti opzionali.
     *  Se un elemento è presente, deve avere un valore, e questo è
     * forse 
     *  scomodo se si vuole solo indicare che la biblioteca dispone
     * 
     *  genericamente di postazioni internet o audio, senza
     *  indicare quante. Per consentire la semplice segnalazione si
     * dovrebbe
     *  usare un costrutto più complesso.
     *  
     * 
     * @return the value of field 'Postazioni'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Postazioni getPostazioni(
    ) {
        return this._postazioni;
    }

    /**
     * Returns the value of field 'scaffalature'. The field
     * 'scaffalature' has the following description: I valori dei
     * sotto-elementi sono ovviamente da intendersi in metri.
     *  Trattandosi di decimali, non sono ammessi valori come "30m"
     * o "30 metri".
     *  
     * 
     * @return the value of field 'Scaffalature'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Scaffalature getScaffalature(
    ) {
        return this._scaffalature;
    }

    /**
     * Returns the value of field 'superficie'. The field
     * 'superficie' has the following description: I valori dei
     * sotto-elementi sono ovviamente da
     *  intendersi in metri quadrati. Trattandosi di decimali,
     *  non sono ammessi valori come "100mq" o "100 metri quadri".
     *  
     * 
     * @return the value of field 'Superficie'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Superficie getSuperficie(
    ) {
        return this._superficie;
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
     * Sets the value of field 'postazioni'. The field 'postazioni'
     * has the following description: Le postazioni si dividono in
     * diversi elementi, tutti opzionali.
     *  Se un elemento è presente, deve avere un valore, e questo è
     * forse 
     *  scomodo se si vuole solo indicare che la biblioteca dispone
     * 
     *  genericamente di postazioni internet o audio, senza
     *  indicare quante. Per consentire la semplice segnalazione si
     * dovrebbe
     *  usare un costrutto più complesso.
     *  
     * 
     * @param postazioni the value of field 'postazioni'.
     */
    public void setPostazioni(
            final it.inera.abi.logic.formatodiscambio.castor.Postazioni postazioni) {
        this._postazioni = postazioni;
    }

    /**
     * Sets the value of field 'scaffalature'. The field
     * 'scaffalature' has the following description: I valori dei
     * sotto-elementi sono ovviamente da intendersi in metri.
     *  Trattandosi di decimali, non sono ammessi valori come "30m"
     * o "30 metri".
     *  
     * 
     * @param scaffalature the value of field 'scaffalature'.
     */
    public void setScaffalature(
            final it.inera.abi.logic.formatodiscambio.castor.Scaffalature scaffalature) {
        this._scaffalature = scaffalature;
    }

    /**
     * Sets the value of field 'superficie'. The field 'superficie'
     * has the following description: I valori dei sotto-elementi
     * sono ovviamente da
     *  intendersi in metri quadrati. Trattandosi di decimali,
     *  non sono ammessi valori come "100mq" o "100 metri quadri".
     *  
     * 
     * @param superficie the value of field 'superficie'.
     */
    public void setSuperficie(
            final it.inera.abi.logic.formatodiscambio.castor.Superficie superficie) {
        this._superficie = superficie;
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
     * it.inera.abi.logic.formatodiscambio.castor.Strutture
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Strutture unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Strutture) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Strutture.class, reader);
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
