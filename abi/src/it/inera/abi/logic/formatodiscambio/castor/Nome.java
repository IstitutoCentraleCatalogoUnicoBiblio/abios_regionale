/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: Nome.java,v 1.3 2012/07/30 15:17:04 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Nomi di una biblioteca. È un insieme in cui solo
 *  il nome attuale è obbligatorio e non ripetibile,
 *  mentre quelli precedenti e quelli alternativi
 *  sono opzionali e ripetibili.
 *  
 * 
 * @version $Revision: 1.3 $ $Date: 2012/07/30 15:17:04 $
 */
@SuppressWarnings("serial")
public class Nome implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _attuale.
     */
    private java.lang.String _attuale;

    /**
     * Field _precedenteList.
     */
    private java.util.List<java.lang.String> _precedenteList;

    /**
     * Field _alternativoList.
     */
    private java.util.List<java.lang.String> _alternativoList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Nome() {
        super();
        this._precedenteList = new java.util.ArrayList<java.lang.String>();
        this._alternativoList = new java.util.ArrayList<java.lang.String>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vAlternativo
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addAlternativo(
            final java.lang.String vAlternativo)
    throws java.lang.IndexOutOfBoundsException {
        this._alternativoList.add(vAlternativo);
    }

    /**
     * 
     * 
     * @param index
     * @param vAlternativo
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addAlternativo(
            final int index,
            final java.lang.String vAlternativo)
    throws java.lang.IndexOutOfBoundsException {
        this._alternativoList.add(index, vAlternativo);
    }

    /**
     * 
     * 
     * @param vPrecedente
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addPrecedente(
            final java.lang.String vPrecedente)
    throws java.lang.IndexOutOfBoundsException {
        this._precedenteList.add(vPrecedente);
    }

    /**
     * 
     * 
     * @param index
     * @param vPrecedente
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addPrecedente(
            final int index,
            final java.lang.String vPrecedente)
    throws java.lang.IndexOutOfBoundsException {
        this._precedenteList.add(index, vPrecedente);
    }

    /**
     * Method enumerateAlternativo.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends java.lang.String> enumerateAlternativo(
    ) {
        return java.util.Collections.enumeration(this._alternativoList);
    }

    /**
     * Method enumeratePrecedente.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends java.lang.String> enumeratePrecedente(
    ) {
        return java.util.Collections.enumeration(this._precedenteList);
    }

    /**
     * Method getAlternativo.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the java.lang.String at the given index
     */
    public java.lang.String getAlternativo(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._alternativoList.size()) {
            throw new IndexOutOfBoundsException("getAlternativo: Index value '" + index + "' not in range [0.." + (this._alternativoList.size() - 1) + "]");
        }

        return (java.lang.String) _alternativoList.get(index);
    }

    /**
     * Method getAlternativo.Returns the contents of the collection
     * in an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public java.lang.String[] getAlternativo(
    ) {
        java.lang.String[] array = new java.lang.String[0];
        return (java.lang.String[]) this._alternativoList.toArray(array);
    }

    /**
     * Method getAlternativoCount.
     * 
     * @return the size of this collection
     */
    public int getAlternativoCount(
    ) {
        return this._alternativoList.size();
    }

    /**
     * Returns the value of field 'attuale'.
     * 
     * @return the value of field 'Attuale'.
     */
    public java.lang.String getAttuale(
    ) {
        return this._attuale;
    }

    /**
     * Method getPrecedente.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the java.lang.String at the given index
     */
    public java.lang.String getPrecedente(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._precedenteList.size()) {
            throw new IndexOutOfBoundsException("getPrecedente: Index value '" + index + "' not in range [0.." + (this._precedenteList.size() - 1) + "]");
        }

        return (java.lang.String) _precedenteList.get(index);
    }

    /**
     * Method getPrecedente.Returns the contents of the collection
     * in an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public java.lang.String[] getPrecedente(
    ) {
        java.lang.String[] array = new java.lang.String[0];
        return (java.lang.String[]) this._precedenteList.toArray(array);
    }

    /**
     * Method getPrecedenteCount.
     * 
     * @return the size of this collection
     */
    public int getPrecedenteCount(
    ) {
        return this._precedenteList.size();
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
     * Method iterateAlternativo.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends java.lang.String> iterateAlternativo(
    ) {
        return this._alternativoList.iterator();
    }

    /**
     * Method iteratePrecedente.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends java.lang.String> iteratePrecedente(
    ) {
        return this._precedenteList.iterator();
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
    public void removeAllAlternativo(
    ) {
        this._alternativoList.clear();
    }

    /**
     */
    public void removeAllPrecedente(
    ) {
        this._precedenteList.clear();
    }

    /**
     * Method removeAlternativo.
     * 
     * @param vAlternativo
     * @return true if the object was removed from the collection.
     */
    public boolean removeAlternativo(
            final java.lang.String vAlternativo) {
        boolean removed = _alternativoList.remove(vAlternativo);
        return removed;
    }

    /**
     * Method removeAlternativoAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public java.lang.String removeAlternativoAt(
            final int index) {
        java.lang.Object obj = this._alternativoList.remove(index);
        return (java.lang.String) obj;
    }

    /**
     * Method removePrecedente.
     * 
     * @param vPrecedente
     * @return true if the object was removed from the collection.
     */
    public boolean removePrecedente(
            final java.lang.String vPrecedente) {
        boolean removed = _precedenteList.remove(vPrecedente);
        return removed;
    }

    /**
     * Method removePrecedenteAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public java.lang.String removePrecedenteAt(
            final int index) {
        java.lang.Object obj = this._precedenteList.remove(index);
        return (java.lang.String) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vAlternativo
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setAlternativo(
            final int index,
            final java.lang.String vAlternativo)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._alternativoList.size()) {
            throw new IndexOutOfBoundsException("setAlternativo: Index value '" + index + "' not in range [0.." + (this._alternativoList.size() - 1) + "]");
        }

        this._alternativoList.set(index, vAlternativo);
    }

    /**
     * 
     * 
     * @param vAlternativoArray
     */
    public void setAlternativo(
            final java.lang.String[] vAlternativoArray) {
        //-- copy array
        _alternativoList.clear();

        for (int i = 0; i < vAlternativoArray.length; i++) {
                this._alternativoList.add(vAlternativoArray[i]);
        }
    }

    /**
     * Sets the value of field 'attuale'.
     * 
     * @param attuale the value of field 'attuale'.
     */
    public void setAttuale(
            final java.lang.String attuale) {
        this._attuale = attuale;
    }

    /**
     * 
     * 
     * @param index
     * @param vPrecedente
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setPrecedente(
            final int index,
            final java.lang.String vPrecedente)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._precedenteList.size()) {
            throw new IndexOutOfBoundsException("setPrecedente: Index value '" + index + "' not in range [0.." + (this._precedenteList.size() - 1) + "]");
        }

        this._precedenteList.set(index, vPrecedente);
    }

    /**
     * 
     * 
     * @param vPrecedenteArray
     */
    public void setPrecedente(
            final java.lang.String[] vPrecedenteArray) {
        //-- copy array
        _precedenteList.clear();

        for (int i = 0; i < vPrecedenteArray.length; i++) {
                this._precedenteList.add(vPrecedenteArray[i]);
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
     * it.inera.abi.logic.formatodiscambio.castor.Nome
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Nome unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Nome) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Nome.class, reader);
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
