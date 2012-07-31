/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: CartaServizi.java,v 1.7 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Questo elemento opzionale indica se la
 *  biblioteca
 *  si è dotata di una carta dei servizi
 *  o meno, e per quali servizi
 *  specifici. Gli
 *  eventuali singoli servizi devono essere elencati
 *  in
 *  altrettanti elementi "servizio", sotto forma
 *  di stringhe
 *  alfanumeriche. Se l'elemento è
 *  vuoto, vuol dire solo che esiste una
 *  carta dei
 *  servizi, ma mancano altre informazioni. I valori
 *  andrebbero standardizzati attraverso
 *  un'opportuna lista d'autorità
 *  concordata.
 *  
 * 
 * @version $Revision: 1.7 $ $Date: 2012/11/22 09:50:20 $
 */
@SuppressWarnings("serial")
public class CartaServizi implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _servizioList.
     */
    private java.util.List<java.lang.String> _servizioList;


      //----------------/
     //- Constructors -/
    //----------------/

    public CartaServizi() {
        super();
        this._servizioList = new java.util.ArrayList<java.lang.String>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vServizio
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addServizio(
            final java.lang.String vServizio)
    throws java.lang.IndexOutOfBoundsException {
        this._servizioList.add(vServizio);
    }

    /**
     * 
     * 
     * @param index
     * @param vServizio
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addServizio(
            final int index,
            final java.lang.String vServizio)
    throws java.lang.IndexOutOfBoundsException {
        this._servizioList.add(index, vServizio);
    }

    /**
     * Method enumerateServizio.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends java.lang.String> enumerateServizio(
    ) {
        return java.util.Collections.enumeration(this._servizioList);
    }

    /**
     * Method getServizio.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the java.lang.String at the given index
     */
    public java.lang.String getServizio(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._servizioList.size()) {
            throw new IndexOutOfBoundsException("getServizio: Index value '" + index + "' not in range [0.." + (this._servizioList.size() - 1) + "]");
        }

        return (java.lang.String) _servizioList.get(index);
    }

    /**
     * Method getServizio.Returns the contents of the collection in
     * an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public java.lang.String[] getServizio(
    ) {
        java.lang.String[] array = new java.lang.String[0];
        return (java.lang.String[]) this._servizioList.toArray(array);
    }

    /**
     * Method getServizioCount.
     * 
     * @return the size of this collection
     */
    public int getServizioCount(
    ) {
        return this._servizioList.size();
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
     * Method iterateServizio.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends java.lang.String> iterateServizio(
    ) {
        return this._servizioList.iterator();
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
    public void removeAllServizio(
    ) {
        this._servizioList.clear();
    }

    /**
     * Method removeServizio.
     * 
     * @param vServizio
     * @return true if the object was removed from the collection.
     */
    public boolean removeServizio(
            final java.lang.String vServizio) {
        boolean removed = _servizioList.remove(vServizio);
        return removed;
    }

    /**
     * Method removeServizioAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public java.lang.String removeServizioAt(
            final int index) {
        java.lang.Object obj = this._servizioList.remove(index);
        return (java.lang.String) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vServizio
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setServizio(
            final int index,
            final java.lang.String vServizio)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._servizioList.size()) {
            throw new IndexOutOfBoundsException("setServizio: Index value '" + index + "' not in range [0.." + (this._servizioList.size() - 1) + "]");
        }

        this._servizioList.set(index, vServizio);
    }

    /**
     * 
     * 
     * @param vServizioArray
     */
    public void setServizio(
            final java.lang.String[] vServizioArray) {
        //-- copy array
        _servizioList.clear();

        for (int i = 0; i < vServizioArray.length; i++) {
                this._servizioList.add(vServizioArray[i]);
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
     * it.inera.abi.logic.formatodiscambio.castor.CartaServizi
     */
    public static it.inera.abi.logic.formatodiscambio.castor.CartaServizi unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.CartaServizi) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.CartaServizi.class, reader);
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
