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
 * $Id: Locale.java,v 1.7 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * A parte l'elemento "automatizzato", che serve ad indicare se il
 * prestito
 *  avviene con procedure automatizzate oppure no, e che pertanto è
 * di tipo
 *  "siNoType", gli altri sono soltanto stringhe, anche se la
 * durata
 *  potrebbe essere vincolata con un pattern opportuno, e gli altri
 * due
 *  possono contenere valori controllati, che però è meglio
 *  lasciare fuori dallo schema. In particolare, il materiale
 * escluso
 *  potrebbe essere normalizzato in base alle apposite norme ISO. 
 *  Qualcosa del genere andrebbe studiato anche per gli utenti
 * ammessi.
 *  
 * 
 * @version $Revision: 1.7 $ $Date: 2012/11/22 09:50:20 $
 */
@SuppressWarnings("serial")
public class Locale implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _attivo.
     */
    private it.inera.abi.logic.formatodiscambio.castor.types.SiNoType _attivo;

    /**
     * Field _prestitoLocaleAutomatizzato.
     */
    private it.inera.abi.logic.formatodiscambio.castor.types.SiNoType _prestitoLocaleAutomatizzato;

    /**
     * E' stato introdotto l'elemento ripetibile
     *  'materiali-esclusi' come contenitore
     *  degli eventuali materiali.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.MaterialiEsclusi _materialiEsclusi;

    /**
     * Field _durata.
     */
    private java.lang.String _durata;

    /**
     * Field _utentiAmmessiList.
     */
    private java.util.List<java.lang.String> _utentiAmmessiList;

    /**
     * Questo elemento
     *  conteggia il totale dei
     *  prestiti di questo tipo
     *  effettuati nel periodo
     *  di
     *  osservazione.
     *  Dev'essere un numero.
     *  
     */
    private java.math.BigDecimal _totalePrestiti;


      //----------------/
     //- Constructors -/
    //----------------/

    public Locale() {
        super();
        this._utentiAmmessiList = new java.util.ArrayList<java.lang.String>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vUtentiAmmessi
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addUtentiAmmessi(
            final java.lang.String vUtentiAmmessi)
    throws java.lang.IndexOutOfBoundsException {
        this._utentiAmmessiList.add(vUtentiAmmessi);
    }

    /**
     * 
     * 
     * @param index
     * @param vUtentiAmmessi
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addUtentiAmmessi(
            final int index,
            final java.lang.String vUtentiAmmessi)
    throws java.lang.IndexOutOfBoundsException {
        this._utentiAmmessiList.add(index, vUtentiAmmessi);
    }

    /**
     * Method enumerateUtentiAmmessi.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends java.lang.String> enumerateUtentiAmmessi(
    ) {
        return java.util.Collections.enumeration(this._utentiAmmessiList);
    }

    /**
     * Returns the value of field 'attivo'.
     * 
     * @return the value of field 'Attivo'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.types.SiNoType getAttivo(
    ) {
        return this._attivo;
    }

    /**
     * Returns the value of field 'durata'.
     * 
     * @return the value of field 'Durata'.
     */
    public java.lang.String getDurata(
    ) {
        return this._durata;
    }

    /**
     * Returns the value of field 'materialiEsclusi'. The field
     * 'materialiEsclusi' has the following description: E' stato
     * introdotto l'elemento ripetibile
     *  'materiali-esclusi' come contenitore
     *  degli eventuali materiali.
     *  
     * 
     * @return the value of field 'MaterialiEsclusi'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.MaterialiEsclusi getMaterialiEsclusi(
    ) {
        return this._materialiEsclusi;
    }

    /**
     * Returns the value of field 'prestitoLocaleAutomatizzato'.
     * 
     * @return the value of field 'PrestitoLocaleAutomatizzato'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.types.SiNoType getPrestitoLocaleAutomatizzato(
    ) {
        return this._prestitoLocaleAutomatizzato;
    }

    /**
     * Returns the value of field 'totalePrestiti'. The field
     * 'totalePrestiti' has the following description: Questo
     * elemento
     *  conteggia il totale dei
     *  prestiti di questo tipo
     *  effettuati nel periodo
     *  di
     *  osservazione.
     *  Dev'essere un numero.
     *  
     * 
     * @return the value of field 'TotalePrestiti'.
     */
    public java.math.BigDecimal getTotalePrestiti(
    ) {
        return this._totalePrestiti;
    }

    /**
     * Method getUtentiAmmessi.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the java.lang.String at the given index
     */
    public java.lang.String getUtentiAmmessi(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._utentiAmmessiList.size()) {
            throw new IndexOutOfBoundsException("getUtentiAmmessi: Index value '" + index + "' not in range [0.." + (this._utentiAmmessiList.size() - 1) + "]");
        }

        return (java.lang.String) _utentiAmmessiList.get(index);
    }

    /**
     * Method getUtentiAmmessi.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public java.lang.String[] getUtentiAmmessi(
    ) {
        java.lang.String[] array = new java.lang.String[0];
        return (java.lang.String[]) this._utentiAmmessiList.toArray(array);
    }

    /**
     * Method getUtentiAmmessiCount.
     * 
     * @return the size of this collection
     */
    public int getUtentiAmmessiCount(
    ) {
        return this._utentiAmmessiList.size();
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
     * Method iterateUtentiAmmessi.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends java.lang.String> iterateUtentiAmmessi(
    ) {
        return this._utentiAmmessiList.iterator();
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
     */
    public void removeAllUtentiAmmessi(
    ) {
        this._utentiAmmessiList.clear();
    }

    /**
     * Method removeUtentiAmmessi.
     * 
     * @param vUtentiAmmessi
     * @return true if the object was removed from the collection.
     */
    public boolean removeUtentiAmmessi(
            final java.lang.String vUtentiAmmessi) {
        boolean removed = _utentiAmmessiList.remove(vUtentiAmmessi);
        return removed;
    }

    /**
     * Method removeUtentiAmmessiAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public java.lang.String removeUtentiAmmessiAt(
            final int index) {
        java.lang.Object obj = this._utentiAmmessiList.remove(index);
        return (java.lang.String) obj;
    }

    /**
     * Sets the value of field 'attivo'.
     * 
     * @param attivo the value of field 'attivo'.
     */
    public void setAttivo(
            final it.inera.abi.logic.formatodiscambio.castor.types.SiNoType attivo) {
        this._attivo = attivo;
    }

    /**
     * Sets the value of field 'durata'.
     * 
     * @param durata the value of field 'durata'.
     */
    public void setDurata(
            final java.lang.String durata) {
        this._durata = durata;
    }

    /**
     * Sets the value of field 'materialiEsclusi'. The field
     * 'materialiEsclusi' has the following description: E' stato
     * introdotto l'elemento ripetibile
     *  'materiali-esclusi' come contenitore
     *  degli eventuali materiali.
     *  
     * 
     * @param materialiEsclusi the value of field 'materialiEsclusi'
     */
    public void setMaterialiEsclusi(
            final it.inera.abi.logic.formatodiscambio.castor.MaterialiEsclusi materialiEsclusi) {
        this._materialiEsclusi = materialiEsclusi;
    }

    /**
     * Sets the value of field 'prestitoLocaleAutomatizzato'.
     * 
     * @param prestitoLocaleAutomatizzato the value of field
     * 'prestitoLocaleAutomatizzato'.
     */
    public void setPrestitoLocaleAutomatizzato(
            final it.inera.abi.logic.formatodiscambio.castor.types.SiNoType prestitoLocaleAutomatizzato) {
        this._prestitoLocaleAutomatizzato = prestitoLocaleAutomatizzato;
    }

    /**
     * Sets the value of field 'totalePrestiti'. The field
     * 'totalePrestiti' has the following description: Questo
     * elemento
     *  conteggia il totale dei
     *  prestiti di questo tipo
     *  effettuati nel periodo
     *  di
     *  osservazione.
     *  Dev'essere un numero.
     *  
     * 
     * @param totalePrestiti the value of field 'totalePrestiti'.
     */
    public void setTotalePrestiti(
            final java.math.BigDecimal totalePrestiti) {
        this._totalePrestiti = totalePrestiti;
    }

    /**
     * 
     * 
     * @param index
     * @param vUtentiAmmessi
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setUtentiAmmessi(
            final int index,
            final java.lang.String vUtentiAmmessi)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._utentiAmmessiList.size()) {
            throw new IndexOutOfBoundsException("setUtentiAmmessi: Index value '" + index + "' not in range [0.." + (this._utentiAmmessiList.size() - 1) + "]");
        }

        this._utentiAmmessiList.set(index, vUtentiAmmessi);
    }

    /**
     * 
     * 
     * @param vUtentiAmmessiArray
     */
    public void setUtentiAmmessi(
            final java.lang.String[] vUtentiAmmessiArray) {
        //-- copy array
        _utentiAmmessiList.clear();

        for (int i = 0; i < vUtentiAmmessiArray.length; i++) {
                this._utentiAmmessiList.add(vUtentiAmmessiArray[i]);
        }
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
     * it.inera.abi.logic.formatodiscambio.castor.Locale
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Locale unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Locale) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Locale.class, reader);
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
