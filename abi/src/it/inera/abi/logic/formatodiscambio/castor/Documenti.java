/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: Documenti.java,v 1.4 2012/07/31 15:00:07 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Class Documenti.
 * 
 * @version $Revision: 1.4 $ $Date: 2012/07/31 15:00:07 $
 */
@SuppressWarnings("serial")
public class Documenti implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _tipoList.
     */
    private java.util.List<java.lang.String> _tipoList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Documenti() {
        super();
        this._tipoList = new java.util.ArrayList<java.lang.String>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vTipo
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addTipo(
            final java.lang.String vTipo)
    throws java.lang.IndexOutOfBoundsException {
        this._tipoList.add(vTipo);
    }

    /**
     * 
     * 
     * @param index
     * @param vTipo
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addTipo(
            final int index,
            final java.lang.String vTipo)
    throws java.lang.IndexOutOfBoundsException {
        this._tipoList.add(index, vTipo);
    }

    /**
     * Method enumerateTipo.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends java.lang.String> enumerateTipo(
    ) {
        return java.util.Collections.enumeration(this._tipoList);
    }

    /**
     * Method getTipo.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the java.lang.String at the given index
     */
    public java.lang.String getTipo(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._tipoList.size()) {
            throw new IndexOutOfBoundsException("getTipo: Index value '" + index + "' not in range [0.." + (this._tipoList.size() - 1) + "]");
        }

        return (java.lang.String) _tipoList.get(index);
    }

    /**
     * Method getTipo.Returns the contents of the collection in an
     * Array.  <p>Note:  Just in case the collection contents are
     * changing in another thread, we pass a 0-length Array of the
     * correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public java.lang.String[] getTipo(
    ) {
        java.lang.String[] array = new java.lang.String[0];
        return (java.lang.String[]) this._tipoList.toArray(array);
    }

    /**
     * Method getTipoCount.
     * 
     * @return the size of this collection
     */
    public int getTipoCount(
    ) {
        return this._tipoList.size();
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
     * Method iterateTipo.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends java.lang.String> iterateTipo(
    ) {
        return this._tipoList.iterator();
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
    public void removeAllTipo(
    ) {
        this._tipoList.clear();
    }

    /**
     * Method removeTipo.
     * 
     * @param vTipo
     * @return true if the object was removed from the collection.
     */
    public boolean removeTipo(
            final java.lang.String vTipo) {
        boolean removed = _tipoList.remove(vTipo);
        return removed;
    }

    /**
     * Method removeTipoAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public java.lang.String removeTipoAt(
            final int index) {
        java.lang.Object obj = this._tipoList.remove(index);
        return (java.lang.String) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vTipo
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setTipo(
            final int index,
            final java.lang.String vTipo)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._tipoList.size()) {
            throw new IndexOutOfBoundsException("setTipo: Index value '" + index + "' not in range [0.." + (this._tipoList.size() - 1) + "]");
        }

        this._tipoList.set(index, vTipo);
    }

    /**
     * 
     * 
     * @param vTipoArray
     */
    public void setTipo(
            final java.lang.String[] vTipoArray) {
        //-- copy array
        _tipoList.clear();

        for (int i = 0; i < vTipoArray.length; i++) {
                this._tipoList.add(vTipoArray[i]);
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
     * it.inera.abi.logic.formatodiscambio.castor.Documenti
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Documenti unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Documenti) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Documenti.class, reader);
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
