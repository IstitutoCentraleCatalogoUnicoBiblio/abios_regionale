/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: DepositiLegali.java,v 1.1 2012/07/31 15:00:07 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Class DepositiLegali.
 * 
 * @version $Revision: 1.1 $ $Date: 2012/07/31 15:00:07 $
 */
@SuppressWarnings("serial")
public class DepositiLegali implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _depositoLegaleList.
     */
    private java.util.List<it.inera.abi.logic.formatodiscambio.castor.DepositoLegale> _depositoLegaleList;


      //----------------/
     //- Constructors -/
    //----------------/

    public DepositiLegali() {
        super();
        this._depositoLegaleList = new java.util.ArrayList<it.inera.abi.logic.formatodiscambio.castor.DepositoLegale>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vDepositoLegale
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addDepositoLegale(
            final it.inera.abi.logic.formatodiscambio.castor.DepositoLegale vDepositoLegale)
    throws java.lang.IndexOutOfBoundsException {
        this._depositoLegaleList.add(vDepositoLegale);
    }

    /**
     * 
     * 
     * @param index
     * @param vDepositoLegale
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addDepositoLegale(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.DepositoLegale vDepositoLegale)
    throws java.lang.IndexOutOfBoundsException {
        this._depositoLegaleList.add(index, vDepositoLegale);
    }

    /**
     * Method enumerateDepositoLegale.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends it.inera.abi.logic.formatodiscambio.castor.DepositoLegale> enumerateDepositoLegale(
    ) {
        return java.util.Collections.enumeration(this._depositoLegaleList);
    }

    /**
     * Method getDepositoLegale.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * it.inera.abi.logic.formatodiscambio.castor.DepositoLegale at
     * the given index
     */
    public it.inera.abi.logic.formatodiscambio.castor.DepositoLegale getDepositoLegale(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._depositoLegaleList.size()) {
            throw new IndexOutOfBoundsException("getDepositoLegale: Index value '" + index + "' not in range [0.." + (this._depositoLegaleList.size() - 1) + "]");
        }

        return (it.inera.abi.logic.formatodiscambio.castor.DepositoLegale) _depositoLegaleList.get(index);
    }

    /**
     * Method getDepositoLegale.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public it.inera.abi.logic.formatodiscambio.castor.DepositoLegale[] getDepositoLegale(
    ) {
        it.inera.abi.logic.formatodiscambio.castor.DepositoLegale[] array = new it.inera.abi.logic.formatodiscambio.castor.DepositoLegale[0];
        return (it.inera.abi.logic.formatodiscambio.castor.DepositoLegale[]) this._depositoLegaleList.toArray(array);
    }

    /**
     * Method getDepositoLegaleCount.
     * 
     * @return the size of this collection
     */
    public int getDepositoLegaleCount(
    ) {
        return this._depositoLegaleList.size();
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
     * Method iterateDepositoLegale.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends it.inera.abi.logic.formatodiscambio.castor.DepositoLegale> iterateDepositoLegale(
    ) {
        return this._depositoLegaleList.iterator();
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
    public void removeAllDepositoLegale(
    ) {
        this._depositoLegaleList.clear();
    }

    /**
     * Method removeDepositoLegale.
     * 
     * @param vDepositoLegale
     * @return true if the object was removed from the collection.
     */
    public boolean removeDepositoLegale(
            final it.inera.abi.logic.formatodiscambio.castor.DepositoLegale vDepositoLegale) {
        boolean removed = _depositoLegaleList.remove(vDepositoLegale);
        return removed;
    }

    /**
     * Method removeDepositoLegaleAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public it.inera.abi.logic.formatodiscambio.castor.DepositoLegale removeDepositoLegaleAt(
            final int index) {
        java.lang.Object obj = this._depositoLegaleList.remove(index);
        return (it.inera.abi.logic.formatodiscambio.castor.DepositoLegale) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vDepositoLegale
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setDepositoLegale(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.DepositoLegale vDepositoLegale)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._depositoLegaleList.size()) {
            throw new IndexOutOfBoundsException("setDepositoLegale: Index value '" + index + "' not in range [0.." + (this._depositoLegaleList.size() - 1) + "]");
        }

        this._depositoLegaleList.set(index, vDepositoLegale);
    }

    /**
     * 
     * 
     * @param vDepositoLegaleArray
     */
    public void setDepositoLegale(
            final it.inera.abi.logic.formatodiscambio.castor.DepositoLegale[] vDepositoLegaleArray) {
        //-- copy array
        _depositoLegaleList.clear();

        for (int i = 0; i < vDepositoLegaleArray.length; i++) {
                this._depositoLegaleList.add(vDepositoLegaleArray[i]);
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
     * it.inera.abi.logic.formatodiscambio.castor.DepositiLegali
     */
    public static it.inera.abi.logic.formatodiscambio.castor.DepositiLegali unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.DepositiLegali) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.DepositiLegali.class, reader);
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
