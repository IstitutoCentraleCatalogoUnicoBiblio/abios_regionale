/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: Altri.java,v 1.4 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * E' stato introdotto l'elemento ripetibile
 *  'altri' come contenitore degli eventuali
 *  altri contatti telefonici.
 *  
 * 
 * @version $Revision: 1.4 $ $Date: 2012/11/22 09:50:20 $
 */
@SuppressWarnings("serial")
public class Altri implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * I contatti di questo tipo prevedono
     *  solo le
     *  note e un valore, oltre a un
     *  attributo "tipo" che, al momento,
     *  consente di specificare solo un
     *  indirizzo e-mail, una URL o un
     *  telex. Ovviamente la stringa
     *  "valore" può contenere qualsiasi
     *  cosa, quindi non c'è garanzia che
     *  tale stringa sia conforme al
     *  tipo
     *  specificato.
     *  
     */
    private java.util.List<it.inera.abi.logic.formatodiscambio.castor.Altro> _altroList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Altri() {
        super();
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
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled
     * it.inera.abi.logic.formatodiscambio.castor.Altri
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Altri unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Altri) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Altri.class, reader);
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
