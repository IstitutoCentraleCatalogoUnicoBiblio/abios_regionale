/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: Accesso.java,v 1.3 2012/07/30 15:17:04 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Riguarda le modalità di accesso alla biblioteca,
 *  sia in termini logistici (e.g. portatori di
 *  handicap), sia in termini amministrativi (chi
 *  può accedere ai servizi e a quali condizioni può
 *  farlo).
 *  
 * 
 * @version $Revision: 1.3 $ $Date: 2012/07/30 15:17:04 $
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
     *  può essere vuoto, segnala
     *  semplicemente la possibilità di
     *  accesso ai portatori di handicap, ma
     *  per ora non viene qui attribuito
     *  alcun significato particolare al suo
     *  eventuale contenuto.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.types.SiNoType _handicap;

    /**
     * Le categorie dipendono molto dalle
     *  diverse basi dati, per cui in questo
     *  schema non si è ritenuto opportuno
     *  elencarne alcuna. Possono essere
     *  specificate più categorie, sotto
     *  forma di semplici stringhe non
     *  controllate. I valori qui utilizzati
     *  andrebbero però concordati,
     *  stabilendo una lista d'autorità.
     *  
     */
    private java.util.List<java.lang.String> _categoriaAmmessaList;

    /**
     * Condizioni di accesso. Tutti i
     *  sotto-elementi sono opzionali, anche
     *  se avrebbe poco senso un elemento
     *  vuoto. I documenti, a loro volta,
     *  possono non essere specializzati.
     *  Questo potrebbe indicare la
     *  necessità di esibire un generico
     *  documento di riconoscimento, non
     *  meglio specificato.
     *  
     */
    private java.util.List<it.inera.abi.logic.formatodiscambio.castor.CondizioniAccesso> _condizioniAccessoList;

    /**
     * Field _destinazioniSociali.
     */
    private it.inera.abi.logic.formatodiscambio.castor.DestinazioniSociali _destinazioniSociali;


      //----------------/
     //- Constructors -/
    //----------------/

    public Accesso() {
        super();
        this._categoriaAmmessaList = new java.util.ArrayList<java.lang.String>();
        this._condizioniAccessoList = new java.util.ArrayList<it.inera.abi.logic.formatodiscambio.castor.CondizioniAccesso>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vCategoriaAmmessa
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addCategoriaAmmessa(
            final java.lang.String vCategoriaAmmessa)
    throws java.lang.IndexOutOfBoundsException {
        this._categoriaAmmessaList.add(vCategoriaAmmessa);
    }

    /**
     * 
     * 
     * @param index
     * @param vCategoriaAmmessa
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addCategoriaAmmessa(
            final int index,
            final java.lang.String vCategoriaAmmessa)
    throws java.lang.IndexOutOfBoundsException {
        this._categoriaAmmessaList.add(index, vCategoriaAmmessa);
    }

    /**
     * 
     * 
     * @param vCondizioniAccesso
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addCondizioniAccesso(
            final it.inera.abi.logic.formatodiscambio.castor.CondizioniAccesso vCondizioniAccesso)
    throws java.lang.IndexOutOfBoundsException {
        this._condizioniAccessoList.add(vCondizioniAccesso);
    }

    /**
     * 
     * 
     * @param index
     * @param vCondizioniAccesso
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addCondizioniAccesso(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.CondizioniAccesso vCondizioniAccesso)
    throws java.lang.IndexOutOfBoundsException {
        this._condizioniAccessoList.add(index, vCondizioniAccesso);
    }

    /**
     * Method enumerateCategoriaAmmessa.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends java.lang.String> enumerateCategoriaAmmessa(
    ) {
        return java.util.Collections.enumeration(this._categoriaAmmessaList);
    }

    /**
     * Method enumerateCondizioniAccesso.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends it.inera.abi.logic.formatodiscambio.castor.CondizioniAccesso> enumerateCondizioniAccesso(
    ) {
        return java.util.Collections.enumeration(this._condizioniAccessoList);
    }

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
     * Method getCategoriaAmmessa.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the java.lang.String at the given index
     */
    public java.lang.String getCategoriaAmmessa(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._categoriaAmmessaList.size()) {
            throw new IndexOutOfBoundsException("getCategoriaAmmessa: Index value '" + index + "' not in range [0.." + (this._categoriaAmmessaList.size() - 1) + "]");
        }

        return (java.lang.String) _categoriaAmmessaList.get(index);
    }

    /**
     * Method getCategoriaAmmessa.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public java.lang.String[] getCategoriaAmmessa(
    ) {
        java.lang.String[] array = new java.lang.String[0];
        return (java.lang.String[]) this._categoriaAmmessaList.toArray(array);
    }

    /**
     * Method getCategoriaAmmessaCount.
     * 
     * @return the size of this collection
     */
    public int getCategoriaAmmessaCount(
    ) {
        return this._categoriaAmmessaList.size();
    }

    /**
     * Method getCondizioniAccesso.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * it.inera.abi.logic.formatodiscambio.castor.CondizioniAccesso
     * at the given index
     */
    public it.inera.abi.logic.formatodiscambio.castor.CondizioniAccesso getCondizioniAccesso(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._condizioniAccessoList.size()) {
            throw new IndexOutOfBoundsException("getCondizioniAccesso: Index value '" + index + "' not in range [0.." + (this._condizioniAccessoList.size() - 1) + "]");
        }

        return (it.inera.abi.logic.formatodiscambio.castor.CondizioniAccesso) _condizioniAccessoList.get(index);
    }

    /**
     * Method getCondizioniAccesso.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public it.inera.abi.logic.formatodiscambio.castor.CondizioniAccesso[] getCondizioniAccesso(
    ) {
        it.inera.abi.logic.formatodiscambio.castor.CondizioniAccesso[] array = new it.inera.abi.logic.formatodiscambio.castor.CondizioniAccesso[0];
        return (it.inera.abi.logic.formatodiscambio.castor.CondizioniAccesso[]) this._condizioniAccessoList.toArray(array);
    }

    /**
     * Method getCondizioniAccessoCount.
     * 
     * @return the size of this collection
     */
    public int getCondizioniAccessoCount(
    ) {
        return this._condizioniAccessoList.size();
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
     *  può essere vuoto, segnala
     *  semplicemente la possibilità di
     *  accesso ai portatori di handicap, ma
     *  per ora non viene qui attribuito
     *  alcun significato particolare al suo
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
     * Method iterateCategoriaAmmessa.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends java.lang.String> iterateCategoriaAmmessa(
    ) {
        return this._categoriaAmmessaList.iterator();
    }

    /**
     * Method iterateCondizioniAccesso.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends it.inera.abi.logic.formatodiscambio.castor.CondizioniAccesso> iterateCondizioniAccesso(
    ) {
        return this._condizioniAccessoList.iterator();
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
    public void removeAllCategoriaAmmessa(
    ) {
        this._categoriaAmmessaList.clear();
    }

    /**
     */
    public void removeAllCondizioniAccesso(
    ) {
        this._condizioniAccessoList.clear();
    }

    /**
     * Method removeCategoriaAmmessa.
     * 
     * @param vCategoriaAmmessa
     * @return true if the object was removed from the collection.
     */
    public boolean removeCategoriaAmmessa(
            final java.lang.String vCategoriaAmmessa) {
        boolean removed = _categoriaAmmessaList.remove(vCategoriaAmmessa);
        return removed;
    }

    /**
     * Method removeCategoriaAmmessaAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public java.lang.String removeCategoriaAmmessaAt(
            final int index) {
        java.lang.Object obj = this._categoriaAmmessaList.remove(index);
        return (java.lang.String) obj;
    }

    /**
     * Method removeCondizioniAccesso.
     * 
     * @param vCondizioniAccesso
     * @return true if the object was removed from the collection.
     */
    public boolean removeCondizioniAccesso(
            final it.inera.abi.logic.formatodiscambio.castor.CondizioniAccesso vCondizioniAccesso) {
        boolean removed = _condizioniAccessoList.remove(vCondizioniAccesso);
        return removed;
    }

    /**
     * Method removeCondizioniAccessoAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public it.inera.abi.logic.formatodiscambio.castor.CondizioniAccesso removeCondizioniAccessoAt(
            final int index) {
        java.lang.Object obj = this._condizioniAccessoList.remove(index);
        return (it.inera.abi.logic.formatodiscambio.castor.CondizioniAccesso) obj;
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
     * 
     * 
     * @param index
     * @param vCategoriaAmmessa
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setCategoriaAmmessa(
            final int index,
            final java.lang.String vCategoriaAmmessa)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._categoriaAmmessaList.size()) {
            throw new IndexOutOfBoundsException("setCategoriaAmmessa: Index value '" + index + "' not in range [0.." + (this._categoriaAmmessaList.size() - 1) + "]");
        }

        this._categoriaAmmessaList.set(index, vCategoriaAmmessa);
    }

    /**
     * 
     * 
     * @param vCategoriaAmmessaArray
     */
    public void setCategoriaAmmessa(
            final java.lang.String[] vCategoriaAmmessaArray) {
        //-- copy array
        _categoriaAmmessaList.clear();

        for (int i = 0; i < vCategoriaAmmessaArray.length; i++) {
                this._categoriaAmmessaList.add(vCategoriaAmmessaArray[i]);
        }
    }

    /**
     * 
     * 
     * @param index
     * @param vCondizioniAccesso
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setCondizioniAccesso(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.CondizioniAccesso vCondizioniAccesso)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._condizioniAccessoList.size()) {
            throw new IndexOutOfBoundsException("setCondizioniAccesso: Index value '" + index + "' not in range [0.." + (this._condizioniAccessoList.size() - 1) + "]");
        }

        this._condizioniAccessoList.set(index, vCondizioniAccesso);
    }

    /**
     * 
     * 
     * @param vCondizioniAccessoArray
     */
    public void setCondizioniAccesso(
            final it.inera.abi.logic.formatodiscambio.castor.CondizioniAccesso[] vCondizioniAccessoArray) {
        //-- copy array
        _condizioniAccessoList.clear();

        for (int i = 0; i < vCondizioniAccessoArray.length; i++) {
                this._condizioniAccessoList.add(vCondizioniAccessoArray[i]);
        }
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
     *  può essere vuoto, segnala
     *  semplicemente la possibilità di
     *  accesso ai portatori di handicap, ma
     *  per ora non viene qui attribuito
     *  alcun significato particolare al suo
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
