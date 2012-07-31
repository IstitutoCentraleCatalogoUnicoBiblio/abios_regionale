/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: Materiali.java,v 1.4 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Class Materiali.
 * 
 * @version $Revision: 1.4 $ $Date: 2012/11/22 09:50:20 $
 */
@SuppressWarnings("serial")
public class Materiali implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _materialeList.
     */
    private java.util.List<it.inera.abi.logic.formatodiscambio.castor.Materiale> _materialeList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Materiali() {
        super();
        this._materialeList = new java.util.ArrayList<it.inera.abi.logic.formatodiscambio.castor.Materiale>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vMateriale
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addMateriale(
            final it.inera.abi.logic.formatodiscambio.castor.Materiale vMateriale)
    throws java.lang.IndexOutOfBoundsException {
        this._materialeList.add(vMateriale);
    }

    /**
     * 
     * 
     * @param index
     * @param vMateriale
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addMateriale(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.Materiale vMateriale)
    throws java.lang.IndexOutOfBoundsException {
        this._materialeList.add(index, vMateriale);
    }

    /**
     * Method enumerateMateriale.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends it.inera.abi.logic.formatodiscambio.castor.Materiale> enumerateMateriale(
    ) {
        return java.util.Collections.enumeration(this._materialeList);
    }

    /**
     * Method getMateriale.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * it.inera.abi.logic.formatodiscambio.castor.Materiale at the
     * given index
     */
    public it.inera.abi.logic.formatodiscambio.castor.Materiale getMateriale(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._materialeList.size()) {
            throw new IndexOutOfBoundsException("getMateriale: Index value '" + index + "' not in range [0.." + (this._materialeList.size() - 1) + "]");
        }

        return (it.inera.abi.logic.formatodiscambio.castor.Materiale) _materialeList.get(index);
    }

    /**
     * Method getMateriale.Returns the contents of the collection
     * in an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public it.inera.abi.logic.formatodiscambio.castor.Materiale[] getMateriale(
    ) {
        it.inera.abi.logic.formatodiscambio.castor.Materiale[] array = new it.inera.abi.logic.formatodiscambio.castor.Materiale[0];
        return (it.inera.abi.logic.formatodiscambio.castor.Materiale[]) this._materialeList.toArray(array);
    }

    /**
     * Method getMaterialeCount.
     * 
     * @return the size of this collection
     */
    public int getMaterialeCount(
    ) {
        return this._materialeList.size();
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
     * Method iterateMateriale.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends it.inera.abi.logic.formatodiscambio.castor.Materiale> iterateMateriale(
    ) {
        return this._materialeList.iterator();
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
    public void removeAllMateriale(
    ) {
        this._materialeList.clear();
    }

    /**
     * Method removeMateriale.
     * 
     * @param vMateriale
     * @return true if the object was removed from the collection.
     */
    public boolean removeMateriale(
            final it.inera.abi.logic.formatodiscambio.castor.Materiale vMateriale) {
        boolean removed = _materialeList.remove(vMateriale);
        return removed;
    }

    /**
     * Method removeMaterialeAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public it.inera.abi.logic.formatodiscambio.castor.Materiale removeMaterialeAt(
            final int index) {
        java.lang.Object obj = this._materialeList.remove(index);
        return (it.inera.abi.logic.formatodiscambio.castor.Materiale) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vMateriale
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setMateriale(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.Materiale vMateriale)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._materialeList.size()) {
            throw new IndexOutOfBoundsException("setMateriale: Index value '" + index + "' not in range [0.." + (this._materialeList.size() - 1) + "]");
        }

        this._materialeList.set(index, vMateriale);
    }

    /**
     * 
     * 
     * @param vMaterialeArray
     */
    public void setMateriale(
            final it.inera.abi.logic.formatodiscambio.castor.Materiale[] vMaterialeArray) {
        //-- copy array
        _materialeList.clear();

        for (int i = 0; i < vMaterialeArray.length; i++) {
                this._materialeList.add(vMaterialeArray[i]);
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
     * it.inera.abi.logic.formatodiscambio.castor.Materiali
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Materiali unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Materiali) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Materiali.class, reader);
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
