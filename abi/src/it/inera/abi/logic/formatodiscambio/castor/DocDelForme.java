/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: DocDelForme.java,v 1.4 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Elemento opzionale tipologie, contenitore di "tipologia" che
 * attinge
 *  alla tabella dei tipi di riproduzione.
 *  
 * 
 * @version $Revision: 1.4 $ $Date: 2012/11/22 09:50:20 $
 */
@SuppressWarnings("serial")
public class DocDelForme implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _formaList.
     */
    private java.util.List<java.lang.String> _formaList;


      //----------------/
     //- Constructors -/
    //----------------/

    public DocDelForme() {
        super();
        this._formaList = new java.util.ArrayList<java.lang.String>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vForma
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addForma(
            final java.lang.String vForma)
    throws java.lang.IndexOutOfBoundsException {
        this._formaList.add(vForma);
    }

    /**
     * 
     * 
     * @param index
     * @param vForma
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addForma(
            final int index,
            final java.lang.String vForma)
    throws java.lang.IndexOutOfBoundsException {
        this._formaList.add(index, vForma);
    }

    /**
     * Method enumerateForma.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends java.lang.String> enumerateForma(
    ) {
        return java.util.Collections.enumeration(this._formaList);
    }

    /**
     * Method getForma.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the java.lang.String at the given index
     */
    public java.lang.String getForma(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._formaList.size()) {
            throw new IndexOutOfBoundsException("getForma: Index value '" + index + "' not in range [0.." + (this._formaList.size() - 1) + "]");
        }

        return (java.lang.String) _formaList.get(index);
    }

    /**
     * Method getForma.Returns the contents of the collection in an
     * Array.  <p>Note:  Just in case the collection contents are
     * changing in another thread, we pass a 0-length Array of the
     * correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public java.lang.String[] getForma(
    ) {
        java.lang.String[] array = new java.lang.String[0];
        return (java.lang.String[]) this._formaList.toArray(array);
    }

    /**
     * Method getFormaCount.
     * 
     * @return the size of this collection
     */
    public int getFormaCount(
    ) {
        return this._formaList.size();
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
     * Method iterateForma.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends java.lang.String> iterateForma(
    ) {
        return this._formaList.iterator();
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
    public void removeAllForma(
    ) {
        this._formaList.clear();
    }

    /**
     * Method removeForma.
     * 
     * @param vForma
     * @return true if the object was removed from the collection.
     */
    public boolean removeForma(
            final java.lang.String vForma) {
        boolean removed = _formaList.remove(vForma);
        return removed;
    }

    /**
     * Method removeFormaAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public java.lang.String removeFormaAt(
            final int index) {
        java.lang.Object obj = this._formaList.remove(index);
        return (java.lang.String) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vForma
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setForma(
            final int index,
            final java.lang.String vForma)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._formaList.size()) {
            throw new IndexOutOfBoundsException("setForma: Index value '" + index + "' not in range [0.." + (this._formaList.size() - 1) + "]");
        }

        this._formaList.set(index, vForma);
    }

    /**
     * 
     * 
     * @param vFormaArray
     */
    public void setForma(
            final java.lang.String[] vFormaArray) {
        //-- copy array
        _formaList.clear();

        for (int i = 0; i < vFormaArray.length; i++) {
                this._formaList.add(vFormaArray[i]);
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
     * it.inera.abi.logic.formatodiscambio.castor.DocDelForme
     */
    public static it.inera.abi.logic.formatodiscambio.castor.DocDelForme unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.DocDelForme) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.DocDelForme.class, reader);
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
