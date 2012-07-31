/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: FondiSpeciali.java,v 1.4 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Class FondiSpeciali.
 * 
 * @version $Revision: 1.4 $ $Date: 2012/11/22 09:50:20 $
 */
@SuppressWarnings("serial")
public class FondiSpeciali implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _fondoSpecialeList.
     */
    private java.util.List<it.inera.abi.logic.formatodiscambio.castor.FondoSpeciale> _fondoSpecialeList;


      //----------------/
     //- Constructors -/
    //----------------/

    public FondiSpeciali() {
        super();
        this._fondoSpecialeList = new java.util.ArrayList<it.inera.abi.logic.formatodiscambio.castor.FondoSpeciale>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vFondoSpeciale
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addFondoSpeciale(
            final it.inera.abi.logic.formatodiscambio.castor.FondoSpeciale vFondoSpeciale)
    throws java.lang.IndexOutOfBoundsException {
        this._fondoSpecialeList.add(vFondoSpeciale);
    }

    /**
     * 
     * 
     * @param index
     * @param vFondoSpeciale
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addFondoSpeciale(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.FondoSpeciale vFondoSpeciale)
    throws java.lang.IndexOutOfBoundsException {
        this._fondoSpecialeList.add(index, vFondoSpeciale);
    }

    /**
     * Method enumerateFondoSpeciale.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends it.inera.abi.logic.formatodiscambio.castor.FondoSpeciale> enumerateFondoSpeciale(
    ) {
        return java.util.Collections.enumeration(this._fondoSpecialeList);
    }

    /**
     * Method getFondoSpeciale.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * it.inera.abi.logic.formatodiscambio.castor.FondoSpeciale at
     * the given index
     */
    public it.inera.abi.logic.formatodiscambio.castor.FondoSpeciale getFondoSpeciale(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._fondoSpecialeList.size()) {
            throw new IndexOutOfBoundsException("getFondoSpeciale: Index value '" + index + "' not in range [0.." + (this._fondoSpecialeList.size() - 1) + "]");
        }

        return (it.inera.abi.logic.formatodiscambio.castor.FondoSpeciale) _fondoSpecialeList.get(index);
    }

    /**
     * Method getFondoSpeciale.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public it.inera.abi.logic.formatodiscambio.castor.FondoSpeciale[] getFondoSpeciale(
    ) {
        it.inera.abi.logic.formatodiscambio.castor.FondoSpeciale[] array = new it.inera.abi.logic.formatodiscambio.castor.FondoSpeciale[0];
        return (it.inera.abi.logic.formatodiscambio.castor.FondoSpeciale[]) this._fondoSpecialeList.toArray(array);
    }

    /**
     * Method getFondoSpecialeCount.
     * 
     * @return the size of this collection
     */
    public int getFondoSpecialeCount(
    ) {
        return this._fondoSpecialeList.size();
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
     * Method iterateFondoSpeciale.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends it.inera.abi.logic.formatodiscambio.castor.FondoSpeciale> iterateFondoSpeciale(
    ) {
        return this._fondoSpecialeList.iterator();
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
    public void removeAllFondoSpeciale(
    ) {
        this._fondoSpecialeList.clear();
    }

    /**
     * Method removeFondoSpeciale.
     * 
     * @param vFondoSpeciale
     * @return true if the object was removed from the collection.
     */
    public boolean removeFondoSpeciale(
            final it.inera.abi.logic.formatodiscambio.castor.FondoSpeciale vFondoSpeciale) {
        boolean removed = _fondoSpecialeList.remove(vFondoSpeciale);
        return removed;
    }

    /**
     * Method removeFondoSpecialeAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public it.inera.abi.logic.formatodiscambio.castor.FondoSpeciale removeFondoSpecialeAt(
            final int index) {
        java.lang.Object obj = this._fondoSpecialeList.remove(index);
        return (it.inera.abi.logic.formatodiscambio.castor.FondoSpeciale) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vFondoSpeciale
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setFondoSpeciale(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.FondoSpeciale vFondoSpeciale)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._fondoSpecialeList.size()) {
            throw new IndexOutOfBoundsException("setFondoSpeciale: Index value '" + index + "' not in range [0.." + (this._fondoSpecialeList.size() - 1) + "]");
        }

        this._fondoSpecialeList.set(index, vFondoSpeciale);
    }

    /**
     * 
     * 
     * @param vFondoSpecialeArray
     */
    public void setFondoSpeciale(
            final it.inera.abi.logic.formatodiscambio.castor.FondoSpeciale[] vFondoSpecialeArray) {
        //-- copy array
        _fondoSpecialeList.clear();

        for (int i = 0; i < vFondoSpecialeArray.length; i++) {
                this._fondoSpecialeList.add(vFondoSpecialeArray[i]);
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
     * it.inera.abi.logic.formatodiscambio.castor.FondiSpeciali
     */
    public static it.inera.abi.logic.formatodiscambio.castor.FondiSpeciali unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.FondiSpeciali) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.FondiSpeciali.class, reader);
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
