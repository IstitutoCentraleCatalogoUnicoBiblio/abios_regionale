/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: Alternativi.java,v 1.4 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * E' stato introdotto l'elemento ripetibile
 *  'alternativi' come contenitore delle
 *  eventuali denominazioni alternative.
 *  
 * 
 * @version $Revision: 1.4 $ $Date: 2012/11/22 09:50:20 $
 */
@SuppressWarnings("serial")
public class Alternativi implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _alternativoList.
     */
    private java.util.List<java.lang.String> _alternativoList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Alternativi() {
        super();
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
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled
     * it.inera.abi.logic.formatodiscambio.castor.Alternativi
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Alternativi unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Alternativi) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Alternativi.class, reader);
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
