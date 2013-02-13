/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: Immagini.java,v 1.1.2.1 2013/02/13 15:05:01 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * E' stato introdotto l'elemento ripetibile
 *  'immagini' come contenitore delle 
 *  eventuali immagini inserite per la biblioteca.
 *  
 * 
 * @version $Revision: 1.1.2.1 $ $Date: 2013/02/13 15:05:01 $
 */
@SuppressWarnings("serial")
public class Immagini implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _immagineList.
     */
    private java.util.List<it.inera.abi.logic.formatodiscambio.castor.Immagine> _immagineList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Immagini() {
        super();
        this._immagineList = new java.util.ArrayList<it.inera.abi.logic.formatodiscambio.castor.Immagine>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vImmagine
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addImmagine(
            final it.inera.abi.logic.formatodiscambio.castor.Immagine vImmagine)
    throws java.lang.IndexOutOfBoundsException {
        this._immagineList.add(vImmagine);
    }

    /**
     * 
     * 
     * @param index
     * @param vImmagine
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addImmagine(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.Immagine vImmagine)
    throws java.lang.IndexOutOfBoundsException {
        this._immagineList.add(index, vImmagine);
    }

    /**
     * Method enumerateImmagine.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends it.inera.abi.logic.formatodiscambio.castor.Immagine> enumerateImmagine(
    ) {
        return java.util.Collections.enumeration(this._immagineList);
    }

    /**
     * Method getImmagine.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * it.inera.abi.logic.formatodiscambio.castor.Immagine at the
     * given index
     */
    public it.inera.abi.logic.formatodiscambio.castor.Immagine getImmagine(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._immagineList.size()) {
            throw new IndexOutOfBoundsException("getImmagine: Index value '" + index + "' not in range [0.." + (this._immagineList.size() - 1) + "]");
        }

        return (it.inera.abi.logic.formatodiscambio.castor.Immagine) _immagineList.get(index);
    }

    /**
     * Method getImmagine.Returns the contents of the collection in
     * an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public it.inera.abi.logic.formatodiscambio.castor.Immagine[] getImmagine(
    ) {
        it.inera.abi.logic.formatodiscambio.castor.Immagine[] array = new it.inera.abi.logic.formatodiscambio.castor.Immagine[0];
        return (it.inera.abi.logic.formatodiscambio.castor.Immagine[]) this._immagineList.toArray(array);
    }

    /**
     * Method getImmagineCount.
     * 
     * @return the size of this collection
     */
    public int getImmagineCount(
    ) {
        return this._immagineList.size();
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
     * Method iterateImmagine.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends it.inera.abi.logic.formatodiscambio.castor.Immagine> iterateImmagine(
    ) {
        return this._immagineList.iterator();
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
    public void removeAllImmagine(
    ) {
        this._immagineList.clear();
    }

    /**
     * Method removeImmagine.
     * 
     * @param vImmagine
     * @return true if the object was removed from the collection.
     */
    public boolean removeImmagine(
            final it.inera.abi.logic.formatodiscambio.castor.Immagine vImmagine) {
        boolean removed = _immagineList.remove(vImmagine);
        return removed;
    }

    /**
     * Method removeImmagineAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public it.inera.abi.logic.formatodiscambio.castor.Immagine removeImmagineAt(
            final int index) {
        java.lang.Object obj = this._immagineList.remove(index);
        return (it.inera.abi.logic.formatodiscambio.castor.Immagine) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vImmagine
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setImmagine(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.Immagine vImmagine)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._immagineList.size()) {
            throw new IndexOutOfBoundsException("setImmagine: Index value '" + index + "' not in range [0.." + (this._immagineList.size() - 1) + "]");
        }

        this._immagineList.set(index, vImmagine);
    }

    /**
     * 
     * 
     * @param vImmagineArray
     */
    public void setImmagine(
            final it.inera.abi.logic.formatodiscambio.castor.Immagine[] vImmagineArray) {
        //-- copy array
        _immagineList.clear();

        for (int i = 0; i < vImmagineArray.length; i++) {
                this._immagineList.add(vImmagineArray[i]);
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
     * it.inera.abi.logic.formatodiscambio.castor.Immagini
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Immagini unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Immagini) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Immagini.class, reader);
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
