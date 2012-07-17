/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: MaterialiEsclusiLocale.java,v 1.3 2012/07/30 15:17:04 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Con questo elemento si dà la possibilità di 
 *  inserire una lista di materiali esclusi dal
 *  prestito locale. 
 *  
 * 
 * @version $Revision: 1.3 $ $Date: 2012/07/30 15:17:04 $
 */
@SuppressWarnings("serial")
public class MaterialiEsclusiLocale implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _materialeList.
     */
    private java.util.List<java.lang.String> _materialeList;


      //----------------/
     //- Constructors -/
    //----------------/

    public MaterialiEsclusiLocale() {
        super();
        this._materialeList = new java.util.ArrayList<java.lang.String>();
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
            final java.lang.String vMateriale)
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
            final java.lang.String vMateriale)
    throws java.lang.IndexOutOfBoundsException {
        this._materialeList.add(index, vMateriale);
    }

    /**
     * Method enumerateMateriale.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends java.lang.String> enumerateMateriale(
    ) {
        return java.util.Collections.enumeration(this._materialeList);
    }

    /**
     * Method getMateriale.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the java.lang.String at the given index
     */
    public java.lang.String getMateriale(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._materialeList.size()) {
            throw new IndexOutOfBoundsException("getMateriale: Index value '" + index + "' not in range [0.." + (this._materialeList.size() - 1) + "]");
        }

        return (java.lang.String) _materialeList.get(index);
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
    public java.lang.String[] getMateriale(
    ) {
        java.lang.String[] array = new java.lang.String[0];
        return (java.lang.String[]) this._materialeList.toArray(array);
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
    public java.util.Iterator<? extends java.lang.String> iterateMateriale(
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
            final java.lang.String vMateriale) {
        boolean removed = _materialeList.remove(vMateriale);
        return removed;
    }

    /**
     * Method removeMaterialeAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public java.lang.String removeMaterialeAt(
            final int index) {
        java.lang.Object obj = this._materialeList.remove(index);
        return (java.lang.String) obj;
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
            final java.lang.String vMateriale)
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
            final java.lang.String[] vMaterialeArray) {
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
     * it.inera.abi.logic.formatodiscambio.castor.MaterialiEsclusiLocale
     */
    public static it.inera.abi.logic.formatodiscambio.castor.MaterialiEsclusiLocale unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.MaterialiEsclusiLocale) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.MaterialiEsclusiLocale.class, reader);
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
