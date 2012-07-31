/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: CategorieAmmesse.java,v 1.1 2012/07/31 15:00:07 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Class CategorieAmmesse.
 * 
 * @version $Revision: 1.1 $ $Date: 2012/07/31 15:00:07 $
 */
@SuppressWarnings("serial")
public class CategorieAmmesse implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Le categorie dipendono molto dalle
     *  diverse
     *  basi
     *  dati, per cui in questo
     *  schema non si è ritenuto
     *  opportuno
     *  elencarne alcuna. Possono essere
     *  specificate più
     *  categorie,
     *  sotto
     *  forma di semplici stringhe non
     *  controllate. I
     *  valori qui
     *  utilizzati
     *  andrebbero però concordati,
     *  stabilendo
     *  una lista
     *  d'autorità.
     *  
     */
    private java.util.List<java.lang.String> _categoriaAmmessaList;


      //----------------/
     //- Constructors -/
    //----------------/

    public CategorieAmmesse() {
        super();
        this._categoriaAmmessaList = new java.util.ArrayList<java.lang.String>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vCategoriaAmmessa
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addCategoriaAmmessa(
            final java.lang.String vCategoriaAmmessa)
    throws java.lang.IndexOutOfBoundsException {
        this._categoriaAmmessaList.add(vCategoriaAmmessa);
    }

    /**
     * 
     * 
     * @param index
     * @param vCategoriaAmmessa
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addCategoriaAmmessa(
            final int index,
            final java.lang.String vCategoriaAmmessa)
    throws java.lang.IndexOutOfBoundsException {
        this._categoriaAmmessaList.add(index, vCategoriaAmmessa);
    }

    /**
     * Method enumerateCategoriaAmmessa.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends java.lang.String> enumerateCategoriaAmmessa(
    ) {
        return java.util.Collections.enumeration(this._categoriaAmmessaList);
    }

    /**
     * Method getCategoriaAmmessa.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the java.lang.String at the given index
     */
    public java.lang.String getCategoriaAmmessa(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._categoriaAmmessaList.size()) {
            throw new IndexOutOfBoundsException("getCategoriaAmmessa: Index value '" + index + "' not in range [0.." + (this._categoriaAmmessaList.size() - 1) + "]");
        }

        return (java.lang.String) _categoriaAmmessaList.get(index);
    }

    /**
     * Method getCategoriaAmmessa.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public java.lang.String[] getCategoriaAmmessa(
    ) {
        java.lang.String[] array = new java.lang.String[0];
        return (java.lang.String[]) this._categoriaAmmessaList.toArray(array);
    }

    /**
     * Method getCategoriaAmmessaCount.
     * 
     * @return the size of this collection
     */
    public int getCategoriaAmmessaCount(
    ) {
        return this._categoriaAmmessaList.size();
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
     * Method iterateCategoriaAmmessa.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends java.lang.String> iterateCategoriaAmmessa(
    ) {
        return this._categoriaAmmessaList.iterator();
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
    public void removeAllCategoriaAmmessa(
    ) {
        this._categoriaAmmessaList.clear();
    }

    /**
     * Method removeCategoriaAmmessa.
     * 
     * @param vCategoriaAmmessa
     * @return true if the object was removed from the collection.
     */
    public boolean removeCategoriaAmmessa(
            final java.lang.String vCategoriaAmmessa) {
        boolean removed = _categoriaAmmessaList.remove(vCategoriaAmmessa);
        return removed;
    }

    /**
     * Method removeCategoriaAmmessaAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public java.lang.String removeCategoriaAmmessaAt(
            final int index) {
        java.lang.Object obj = this._categoriaAmmessaList.remove(index);
        return (java.lang.String) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vCategoriaAmmessa
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setCategoriaAmmessa(
            final int index,
            final java.lang.String vCategoriaAmmessa)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._categoriaAmmessaList.size()) {
            throw new IndexOutOfBoundsException("setCategoriaAmmessa: Index value '" + index + "' not in range [0.." + (this._categoriaAmmessaList.size() - 1) + "]");
        }

        this._categoriaAmmessaList.set(index, vCategoriaAmmessa);
    }

    /**
     * 
     * 
     * @param vCategoriaAmmessaArray
     */
    public void setCategoriaAmmessa(
            final java.lang.String[] vCategoriaAmmessaArray) {
        //-- copy array
        _categoriaAmmessaList.clear();

        for (int i = 0; i < vCategoriaAmmessaArray.length; i++) {
                this._categoriaAmmessaList.add(vCategoriaAmmessaArray[i]);
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
     * it.inera.abi.logic.formatodiscambio.castor.CategorieAmmesse
     */
    public static it.inera.abi.logic.formatodiscambio.castor.CategorieAmmesse unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.CategorieAmmesse) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.CategorieAmmesse.class, reader);
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
