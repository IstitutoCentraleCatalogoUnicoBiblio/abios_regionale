/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: Contatti.java,v 1.1 2012/06/22 13:55:04 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Varie modalità per contattare la biblioteca e il
 *  suo personale.
 *  
 * 
 * @version $Revision: 1.1 $ $Date: 2012/06/22 13:55:04 $
 */
@SuppressWarnings("serial")
public class Contatti implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Un contatto di tipo telefonico. Il
     *  prefisso è ovviamente quello
     *  internazionale.
     *  
     */
    private java.util.List<it.inera.abi.logic.formatodiscambio.castor.Telefonico> _telefonicoList;

    /**
     * I contatti di questo tipo prevedono
     *  solo le note e un valore, oltre a un
     *  attributo "tipo" che, al momento,
     *  consente di specificare solo un
     *  indirizzo e-mail, una URL o un
     *  telex. Ovviamente la stringa
     *  "valore" può contenere qualsiasi
     *  cosa, quindi non c'è garanzia che
     *  tale stringa sia conforme al tipo
     *  specificato.
     *  
     */
    private java.util.List<it.inera.abi.logic.formatodiscambio.castor.Altro> _altroList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Contatti() {
        super();
        this._telefonicoList = new java.util.ArrayList<it.inera.abi.logic.formatodiscambio.castor.Telefonico>();
        this._altroList = new java.util.ArrayList<it.inera.abi.logic.formatodiscambio.castor.Altro>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vAltro
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addAltro(
            final it.inera.abi.logic.formatodiscambio.castor.Altro vAltro)
    throws java.lang.IndexOutOfBoundsException {
        this._altroList.add(vAltro);
    }

    /**
     * 
     * 
     * @param index
     * @param vAltro
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addAltro(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.Altro vAltro)
    throws java.lang.IndexOutOfBoundsException {
        this._altroList.add(index, vAltro);
    }

    /**
     * 
     * 
     * @param vTelefonico
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addTelefonico(
            final it.inera.abi.logic.formatodiscambio.castor.Telefonico vTelefonico)
    throws java.lang.IndexOutOfBoundsException {
        this._telefonicoList.add(vTelefonico);
    }

    /**
     * 
     * 
     * @param index
     * @param vTelefonico
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addTelefonico(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.Telefonico vTelefonico)
    throws java.lang.IndexOutOfBoundsException {
        this._telefonicoList.add(index, vTelefonico);
    }

    /**
     * Method enumerateAltro.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends it.inera.abi.logic.formatodiscambio.castor.Altro> enumerateAltro(
    ) {
        return java.util.Collections.enumeration(this._altroList);
    }

    /**
     * Method enumerateTelefonico.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends it.inera.abi.logic.formatodiscambio.castor.Telefonico> enumerateTelefonico(
    ) {
        return java.util.Collections.enumeration(this._telefonicoList);
    }

    /**
     * Method getAltro.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * it.inera.abi.logic.formatodiscambio.castor.Altro at the
     * given index
     */
    public it.inera.abi.logic.formatodiscambio.castor.Altro getAltro(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._altroList.size()) {
            throw new IndexOutOfBoundsException("getAltro: Index value '" + index + "' not in range [0.." + (this._altroList.size() - 1) + "]");
        }

        return (it.inera.abi.logic.formatodiscambio.castor.Altro) _altroList.get(index);
    }

    /**
     * Method getAltro.Returns the contents of the collection in an
     * Array.  <p>Note:  Just in case the collection contents are
     * changing in another thread, we pass a 0-length Array of the
     * correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public it.inera.abi.logic.formatodiscambio.castor.Altro[] getAltro(
    ) {
        it.inera.abi.logic.formatodiscambio.castor.Altro[] array = new it.inera.abi.logic.formatodiscambio.castor.Altro[0];
        return (it.inera.abi.logic.formatodiscambio.castor.Altro[]) this._altroList.toArray(array);
    }

    /**
     * Method getAltroCount.
     * 
     * @return the size of this collection
     */
    public int getAltroCount(
    ) {
        return this._altroList.size();
    }

    /**
     * Method getTelefonico.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * it.inera.abi.logic.formatodiscambio.castor.Telefonico at the
     * given index
     */
    public it.inera.abi.logic.formatodiscambio.castor.Telefonico getTelefonico(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._telefonicoList.size()) {
            throw new IndexOutOfBoundsException("getTelefonico: Index value '" + index + "' not in range [0.." + (this._telefonicoList.size() - 1) + "]");
        }

        return (it.inera.abi.logic.formatodiscambio.castor.Telefonico) _telefonicoList.get(index);
    }

    /**
     * Method getTelefonico.Returns the contents of the collection
     * in an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public it.inera.abi.logic.formatodiscambio.castor.Telefonico[] getTelefonico(
    ) {
        it.inera.abi.logic.formatodiscambio.castor.Telefonico[] array = new it.inera.abi.logic.formatodiscambio.castor.Telefonico[0];
        return (it.inera.abi.logic.formatodiscambio.castor.Telefonico[]) this._telefonicoList.toArray(array);
    }

    /**
     * Method getTelefonicoCount.
     * 
     * @return the size of this collection
     */
    public int getTelefonicoCount(
    ) {
        return this._telefonicoList.size();
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
     * Method iterateAltro.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends it.inera.abi.logic.formatodiscambio.castor.Altro> iterateAltro(
    ) {
        return this._altroList.iterator();
    }

    /**
     * Method iterateTelefonico.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends it.inera.abi.logic.formatodiscambio.castor.Telefonico> iterateTelefonico(
    ) {
        return this._telefonicoList.iterator();
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
    public void removeAllAltro(
    ) {
        this._altroList.clear();
    }

    /**
     */
    public void removeAllTelefonico(
    ) {
        this._telefonicoList.clear();
    }

    /**
     * Method removeAltro.
     * 
     * @param vAltro
     * @return true if the object was removed from the collection.
     */
    public boolean removeAltro(
            final it.inera.abi.logic.formatodiscambio.castor.Altro vAltro) {
        boolean removed = _altroList.remove(vAltro);
        return removed;
    }

    /**
     * Method removeAltroAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public it.inera.abi.logic.formatodiscambio.castor.Altro removeAltroAt(
            final int index) {
        java.lang.Object obj = this._altroList.remove(index);
        return (it.inera.abi.logic.formatodiscambio.castor.Altro) obj;
    }

    /**
     * Method removeTelefonico.
     * 
     * @param vTelefonico
     * @return true if the object was removed from the collection.
     */
    public boolean removeTelefonico(
            final it.inera.abi.logic.formatodiscambio.castor.Telefonico vTelefonico) {
        boolean removed = _telefonicoList.remove(vTelefonico);
        return removed;
    }

    /**
     * Method removeTelefonicoAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public it.inera.abi.logic.formatodiscambio.castor.Telefonico removeTelefonicoAt(
            final int index) {
        java.lang.Object obj = this._telefonicoList.remove(index);
        return (it.inera.abi.logic.formatodiscambio.castor.Telefonico) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vAltro
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setAltro(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.Altro vAltro)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._altroList.size()) {
            throw new IndexOutOfBoundsException("setAltro: Index value '" + index + "' not in range [0.." + (this._altroList.size() - 1) + "]");
        }

        this._altroList.set(index, vAltro);
    }

    /**
     * 
     * 
     * @param vAltroArray
     */
    public void setAltro(
            final it.inera.abi.logic.formatodiscambio.castor.Altro[] vAltroArray) {
        //-- copy array
        _altroList.clear();

        for (int i = 0; i < vAltroArray.length; i++) {
                this._altroList.add(vAltroArray[i]);
        }
    }

    /**
     * 
     * 
     * @param index
     * @param vTelefonico
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setTelefonico(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.Telefonico vTelefonico)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._telefonicoList.size()) {
            throw new IndexOutOfBoundsException("setTelefonico: Index value '" + index + "' not in range [0.." + (this._telefonicoList.size() - 1) + "]");
        }

        this._telefonicoList.set(index, vTelefonico);
    }

    /**
     * 
     * 
     * @param vTelefonicoArray
     */
    public void setTelefonico(
            final it.inera.abi.logic.formatodiscambio.castor.Telefonico[] vTelefonicoArray) {
        //-- copy array
        _telefonicoList.clear();

        for (int i = 0; i < vTelefonicoArray.length; i++) {
                this._telefonicoList.add(vTelefonicoArray[i]);
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
     * it.inera.abi.logic.formatodiscambio.castor.Contatti
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Contatti unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Contatti) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Contatti.class, reader);
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
