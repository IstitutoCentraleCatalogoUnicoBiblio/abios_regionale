/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: CataloghiGenerali.java,v 1.4 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * E' stato introdotto l'elemento ripetibile
 *  'cataloghi-generali' come contenitore
 *  degli eventuali cataloghi generali.
 *  
 * 
 * @version $Revision: 1.4 $ $Date: 2012/11/22 09:50:20 $
 */
@SuppressWarnings("serial")
public class CataloghiGenerali implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _catalogoGeneraleList.
     */
    private java.util.List<it.inera.abi.logic.formatodiscambio.castor.CatalogoGenerale> _catalogoGeneraleList;


      //----------------/
     //- Constructors -/
    //----------------/

    public CataloghiGenerali() {
        super();
        this._catalogoGeneraleList = new java.util.ArrayList<it.inera.abi.logic.formatodiscambio.castor.CatalogoGenerale>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vCatalogoGenerale
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addCatalogoGenerale(
            final it.inera.abi.logic.formatodiscambio.castor.CatalogoGenerale vCatalogoGenerale)
    throws java.lang.IndexOutOfBoundsException {
        this._catalogoGeneraleList.add(vCatalogoGenerale);
    }

    /**
     * 
     * 
     * @param index
     * @param vCatalogoGenerale
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addCatalogoGenerale(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.CatalogoGenerale vCatalogoGenerale)
    throws java.lang.IndexOutOfBoundsException {
        this._catalogoGeneraleList.add(index, vCatalogoGenerale);
    }

    /**
     * Method enumerateCatalogoGenerale.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends it.inera.abi.logic.formatodiscambio.castor.CatalogoGenerale> enumerateCatalogoGenerale(
    ) {
        return java.util.Collections.enumeration(this._catalogoGeneraleList);
    }

    /**
     * Method getCatalogoGenerale.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * it.inera.abi.logic.formatodiscambio.castor.CatalogoGenerale
     * at the given index
     */
    public it.inera.abi.logic.formatodiscambio.castor.CatalogoGenerale getCatalogoGenerale(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._catalogoGeneraleList.size()) {
            throw new IndexOutOfBoundsException("getCatalogoGenerale: Index value '" + index + "' not in range [0.." + (this._catalogoGeneraleList.size() - 1) + "]");
        }

        return (it.inera.abi.logic.formatodiscambio.castor.CatalogoGenerale) _catalogoGeneraleList.get(index);
    }

    /**
     * Method getCatalogoGenerale.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public it.inera.abi.logic.formatodiscambio.castor.CatalogoGenerale[] getCatalogoGenerale(
    ) {
        it.inera.abi.logic.formatodiscambio.castor.CatalogoGenerale[] array = new it.inera.abi.logic.formatodiscambio.castor.CatalogoGenerale[0];
        return (it.inera.abi.logic.formatodiscambio.castor.CatalogoGenerale[]) this._catalogoGeneraleList.toArray(array);
    }

    /**
     * Method getCatalogoGeneraleCount.
     * 
     * @return the size of this collection
     */
    public int getCatalogoGeneraleCount(
    ) {
        return this._catalogoGeneraleList.size();
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
     * Method iterateCatalogoGenerale.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends it.inera.abi.logic.formatodiscambio.castor.CatalogoGenerale> iterateCatalogoGenerale(
    ) {
        return this._catalogoGeneraleList.iterator();
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
    public void removeAllCatalogoGenerale(
    ) {
        this._catalogoGeneraleList.clear();
    }

    /**
     * Method removeCatalogoGenerale.
     * 
     * @param vCatalogoGenerale
     * @return true if the object was removed from the collection.
     */
    public boolean removeCatalogoGenerale(
            final it.inera.abi.logic.formatodiscambio.castor.CatalogoGenerale vCatalogoGenerale) {
        boolean removed = _catalogoGeneraleList.remove(vCatalogoGenerale);
        return removed;
    }

    /**
     * Method removeCatalogoGeneraleAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public it.inera.abi.logic.formatodiscambio.castor.CatalogoGenerale removeCatalogoGeneraleAt(
            final int index) {
        java.lang.Object obj = this._catalogoGeneraleList.remove(index);
        return (it.inera.abi.logic.formatodiscambio.castor.CatalogoGenerale) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vCatalogoGenerale
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setCatalogoGenerale(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.CatalogoGenerale vCatalogoGenerale)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._catalogoGeneraleList.size()) {
            throw new IndexOutOfBoundsException("setCatalogoGenerale: Index value '" + index + "' not in range [0.." + (this._catalogoGeneraleList.size() - 1) + "]");
        }

        this._catalogoGeneraleList.set(index, vCatalogoGenerale);
    }

    /**
     * 
     * 
     * @param vCatalogoGeneraleArray
     */
    public void setCatalogoGenerale(
            final it.inera.abi.logic.formatodiscambio.castor.CatalogoGenerale[] vCatalogoGeneraleArray) {
        //-- copy array
        _catalogoGeneraleList.clear();

        for (int i = 0; i < vCatalogoGeneraleArray.length; i++) {
                this._catalogoGeneraleList.add(vCatalogoGeneraleArray[i]);
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
     * it.inera.abi.logic.formatodiscambio.castor.CataloghiGenerali
     */
    public static it.inera.abi.logic.formatodiscambio.castor.CataloghiGenerali unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.CataloghiGenerali) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.CataloghiGenerali.class, reader);
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
