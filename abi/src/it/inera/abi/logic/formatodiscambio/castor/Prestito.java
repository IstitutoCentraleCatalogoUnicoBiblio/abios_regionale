/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: Prestito.java,v 1.2 2012/07/17 09:09:28 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Varie informazioni relative al servizio di
 *  prestito. Ci sono diversi sotto-elementi di
 *  ovvio significato, tutti opzionali.
 *  
 * 
 * @version $Revision: 1.2 $ $Date: 2012/07/17 09:09:28 $
 */
@SuppressWarnings("serial")
public class Prestito implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * A parte l'elemento "automatizzato",
     *  che serve ad indicare se il prestito
     *  avviene con procedure automatizzate
     *  oppure no, e che pertanto è di tipo
     *  "siNoType", gli altri sono soltanto
     *  stringhe, anche se la durata
     *  potrebbe essere vincolata con un
     *  pattern opportuno, e gli altri due
     *  possono contenere valori
     *  controllati, che però è meglio
     *  lasciare fuori dallo schema. In
     *  particolare, il materiale escluso
     *  potrebbe essere normalizzato in base
     *  alle apposite norme ISO. Qualcosa
     *  del genere andrebbe studiato anche
     *  per gli utenti ammessi.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.Locale _locale;

    /**
     * In questo elemento, pur opzionale,
     *  si è ritenuto opportuno rendere
     *  obbligatorio almeno il
     *  sotto-elemento "tipo", escludendo la
     *  paradossale situazione di una
     *  biblioteca che offre il servizio, ma
     *  non sa di che tipo.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.Interbibliotecario _interbibliotecario;

    /**
     * Con questo elemento si dà la possibilità di 
     *  inserire una lista di materiali esclusi dal
     *  prestito locale. 
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.MaterialiEsclusiLocale _materialiEsclusiLocale;

    /**
     * Con questo elemento si dà la possibilità di 
     *  esprimere le modalità di riprodurre i vari
     *  materiali ammessi al prestito locale, 
     *  nazionale e/o internazionale. 
     *  
     */
    private java.util.List<it.inera.abi.logic.formatodiscambio.castor.Riproduzioni> _riproduzioniList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Prestito() {
        super();
        this._riproduzioniList = new java.util.ArrayList<it.inera.abi.logic.formatodiscambio.castor.Riproduzioni>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vRiproduzioni
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addRiproduzioni(
            final it.inera.abi.logic.formatodiscambio.castor.Riproduzioni vRiproduzioni)
    throws java.lang.IndexOutOfBoundsException {
        this._riproduzioniList.add(vRiproduzioni);
    }

    /**
     * 
     * 
     * @param index
     * @param vRiproduzioni
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addRiproduzioni(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.Riproduzioni vRiproduzioni)
    throws java.lang.IndexOutOfBoundsException {
        this._riproduzioniList.add(index, vRiproduzioni);
    }

    /**
     * Method enumerateRiproduzioni.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends it.inera.abi.logic.formatodiscambio.castor.Riproduzioni> enumerateRiproduzioni(
    ) {
        return java.util.Collections.enumeration(this._riproduzioniList);
    }

    /**
     * Returns the value of field 'interbibliotecario'. The field
     * 'interbibliotecario' has the following description: In
     * questo elemento, pur opzionale,
     *  si è ritenuto opportuno rendere
     *  obbligatorio almeno il
     *  sotto-elemento "tipo", escludendo la
     *  paradossale situazione di una
     *  biblioteca che offre il servizio, ma
     *  non sa di che tipo.
     *  
     * 
     * @return the value of field 'Interbibliotecario'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Interbibliotecario getInterbibliotecario(
    ) {
        return this._interbibliotecario;
    }

    /**
     * Returns the value of field 'locale'. The field 'locale' has
     * the following description: A parte l'elemento
     * "automatizzato",
     *  che serve ad indicare se il prestito
     *  avviene con procedure automatizzate
     *  oppure no, e che pertanto è di tipo
     *  "siNoType", gli altri sono soltanto
     *  stringhe, anche se la durata
     *  potrebbe essere vincolata con un
     *  pattern opportuno, e gli altri due
     *  possono contenere valori
     *  controllati, che però è meglio
     *  lasciare fuori dallo schema. In
     *  particolare, il materiale escluso
     *  potrebbe essere normalizzato in base
     *  alle apposite norme ISO. Qualcosa
     *  del genere andrebbe studiato anche
     *  per gli utenti ammessi.
     *  
     * 
     * @return the value of field 'Locale'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Locale getLocale(
    ) {
        return this._locale;
    }

    /**
     * Returns the value of field 'materialiEsclusiLocale'. The
     * field 'materialiEsclusiLocale' has the following
     * description: Con questo elemento si dà la possibilità di 
     *  inserire una lista di materiali esclusi dal
     *  prestito locale. 
     *  
     * 
     * @return the value of field 'MaterialiEsclusiLocale'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.MaterialiEsclusiLocale getMaterialiEsclusiLocale(
    ) {
        return this._materialiEsclusiLocale;
    }

    /**
     * Method getRiproduzioni.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * it.inera.abi.logic.formatodiscambio.castor.Riproduzioni at
     * the given index
     */
    public it.inera.abi.logic.formatodiscambio.castor.Riproduzioni getRiproduzioni(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._riproduzioniList.size()) {
            throw new IndexOutOfBoundsException("getRiproduzioni: Index value '" + index + "' not in range [0.." + (this._riproduzioniList.size() - 1) + "]");
        }

        return (it.inera.abi.logic.formatodiscambio.castor.Riproduzioni) _riproduzioniList.get(index);
    }

    /**
     * Method getRiproduzioni.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public it.inera.abi.logic.formatodiscambio.castor.Riproduzioni[] getRiproduzioni(
    ) {
        it.inera.abi.logic.formatodiscambio.castor.Riproduzioni[] array = new it.inera.abi.logic.formatodiscambio.castor.Riproduzioni[0];
        return (it.inera.abi.logic.formatodiscambio.castor.Riproduzioni[]) this._riproduzioniList.toArray(array);
    }

    /**
     * Method getRiproduzioniCount.
     * 
     * @return the size of this collection
     */
    public int getRiproduzioniCount(
    ) {
        return this._riproduzioniList.size();
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
     * Method iterateRiproduzioni.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends it.inera.abi.logic.formatodiscambio.castor.Riproduzioni> iterateRiproduzioni(
    ) {
        return this._riproduzioniList.iterator();
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
    public void removeAllRiproduzioni(
    ) {
        this._riproduzioniList.clear();
    }

    /**
     * Method removeRiproduzioni.
     * 
     * @param vRiproduzioni
     * @return true if the object was removed from the collection.
     */
    public boolean removeRiproduzioni(
            final it.inera.abi.logic.formatodiscambio.castor.Riproduzioni vRiproduzioni) {
        boolean removed = _riproduzioniList.remove(vRiproduzioni);
        return removed;
    }

    /**
     * Method removeRiproduzioniAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public it.inera.abi.logic.formatodiscambio.castor.Riproduzioni removeRiproduzioniAt(
            final int index) {
        java.lang.Object obj = this._riproduzioniList.remove(index);
        return (it.inera.abi.logic.formatodiscambio.castor.Riproduzioni) obj;
    }

    /**
     * Sets the value of field 'interbibliotecario'. The field
     * 'interbibliotecario' has the following description: In
     * questo elemento, pur opzionale,
     *  si è ritenuto opportuno rendere
     *  obbligatorio almeno il
     *  sotto-elemento "tipo", escludendo la
     *  paradossale situazione di una
     *  biblioteca che offre il servizio, ma
     *  non sa di che tipo.
     *  
     * 
     * @param interbibliotecario the value of field
     * 'interbibliotecario'.
     */
    public void setInterbibliotecario(
            final it.inera.abi.logic.formatodiscambio.castor.Interbibliotecario interbibliotecario) {
        this._interbibliotecario = interbibliotecario;
    }

    /**
     * Sets the value of field 'locale'. The field 'locale' has the
     * following description: A parte l'elemento "automatizzato",
     *  che serve ad indicare se il prestito
     *  avviene con procedure automatizzate
     *  oppure no, e che pertanto è di tipo
     *  "siNoType", gli altri sono soltanto
     *  stringhe, anche se la durata
     *  potrebbe essere vincolata con un
     *  pattern opportuno, e gli altri due
     *  possono contenere valori
     *  controllati, che però è meglio
     *  lasciare fuori dallo schema. In
     *  particolare, il materiale escluso
     *  potrebbe essere normalizzato in base
     *  alle apposite norme ISO. Qualcosa
     *  del genere andrebbe studiato anche
     *  per gli utenti ammessi.
     *  
     * 
     * @param locale the value of field 'locale'.
     */
    public void setLocale(
            final it.inera.abi.logic.formatodiscambio.castor.Locale locale) {
        this._locale = locale;
    }

    /**
     * Sets the value of field 'materialiEsclusiLocale'. The field
     * 'materialiEsclusiLocale' has the following description: Con
     * questo elemento si dà la possibilità di 
     *  inserire una lista di materiali esclusi dal
     *  prestito locale. 
     *  
     * 
     * @param materialiEsclusiLocale the value of field
     * 'materialiEsclusiLocale'.
     */
    public void setMaterialiEsclusiLocale(
            final it.inera.abi.logic.formatodiscambio.castor.MaterialiEsclusiLocale materialiEsclusiLocale) {
        this._materialiEsclusiLocale = materialiEsclusiLocale;
    }

    /**
     * 
     * 
     * @param index
     * @param vRiproduzioni
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setRiproduzioni(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.Riproduzioni vRiproduzioni)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._riproduzioniList.size()) {
            throw new IndexOutOfBoundsException("setRiproduzioni: Index value '" + index + "' not in range [0.." + (this._riproduzioniList.size() - 1) + "]");
        }

        this._riproduzioniList.set(index, vRiproduzioni);
    }

    /**
     * 
     * 
     * @param vRiproduzioniArray
     */
    public void setRiproduzioni(
            final it.inera.abi.logic.formatodiscambio.castor.Riproduzioni[] vRiproduzioniArray) {
        //-- copy array
        _riproduzioniList.clear();

        for (int i = 0; i < vRiproduzioniArray.length; i++) {
                this._riproduzioniList.add(vRiproduzioniArray[i]);
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
     * it.inera.abi.logic.formatodiscambio.castor.Prestito
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Prestito unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Prestito) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Prestito.class, reader);
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
