/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: CataloghiType.java,v 1.1 2012/06/22 13:55:04 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Tipo che raccoglie i diversi cataloghi. Un elemento di
 *  questo tipo può dunque contenere i tre tipi di cataloghi
 *  di seguito definiti.
 *  
 *  Tutti i tre sotto-elementi sono opzionali e ripetibili
 *  in un ordine qualsiasi. Nel definire i tre sottotipi
 *  sono sfruttate dove possibile le somiglianze fra i tre,
 *  attraverso la derivazione di un tipo da un altro.
 *  
 * 
 * @version $Revision: 1.1 $ $Date: 2012/06/22 13:55:04 $
 */
@SuppressWarnings("serial")
public abstract class CataloghiType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _catalogoGeneraleList.
     */
    private java.util.List<it.inera.abi.logic.formatodiscambio.castor.CatalogoGenerale> _catalogoGeneraleList;

    /**
     * Field _catalogoSpecialeList.
     */
    private java.util.List<it.inera.abi.logic.formatodiscambio.castor.CatalogoSpeciale> _catalogoSpecialeList;

    /**
     * Field _catalogoCollettivoList.
     */
    private java.util.List<it.inera.abi.logic.formatodiscambio.castor.CatalogoCollettivo> _catalogoCollettivoList;


      //----------------/
     //- Constructors -/
    //----------------/

    public CataloghiType() {
        super();
        this._catalogoGeneraleList = new java.util.ArrayList<it.inera.abi.logic.formatodiscambio.castor.CatalogoGenerale>();
        this._catalogoSpecialeList = new java.util.ArrayList<it.inera.abi.logic.formatodiscambio.castor.CatalogoSpeciale>();
        this._catalogoCollettivoList = new java.util.ArrayList<it.inera.abi.logic.formatodiscambio.castor.CatalogoCollettivo>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vCatalogoCollettivo
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addCatalogoCollettivo(
            final it.inera.abi.logic.formatodiscambio.castor.CatalogoCollettivo vCatalogoCollettivo)
    throws java.lang.IndexOutOfBoundsException {
        this._catalogoCollettivoList.add(vCatalogoCollettivo);
    }

    /**
     * 
     * 
     * @param index
     * @param vCatalogoCollettivo
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addCatalogoCollettivo(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.CatalogoCollettivo vCatalogoCollettivo)
    throws java.lang.IndexOutOfBoundsException {
        this._catalogoCollettivoList.add(index, vCatalogoCollettivo);
    }

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
     * 
     * 
     * @param vCatalogoSpeciale
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addCatalogoSpeciale(
            final it.inera.abi.logic.formatodiscambio.castor.CatalogoSpeciale vCatalogoSpeciale)
    throws java.lang.IndexOutOfBoundsException {
        this._catalogoSpecialeList.add(vCatalogoSpeciale);
    }

    /**
     * 
     * 
     * @param index
     * @param vCatalogoSpeciale
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addCatalogoSpeciale(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.CatalogoSpeciale vCatalogoSpeciale)
    throws java.lang.IndexOutOfBoundsException {
        this._catalogoSpecialeList.add(index, vCatalogoSpeciale);
    }

    /**
     * Method enumerateCatalogoCollettivo.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends it.inera.abi.logic.formatodiscambio.castor.CatalogoCollettivo> enumerateCatalogoCollettivo(
    ) {
        return java.util.Collections.enumeration(this._catalogoCollettivoList);
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
     * Method enumerateCatalogoSpeciale.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends it.inera.abi.logic.formatodiscambio.castor.CatalogoSpeciale> enumerateCatalogoSpeciale(
    ) {
        return java.util.Collections.enumeration(this._catalogoSpecialeList);
    }

    /**
     * Method getCatalogoCollettivo.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * it.inera.abi.logic.formatodiscambio.castor.CatalogoCollettivo
     * at the given index
     */
    public it.inera.abi.logic.formatodiscambio.castor.CatalogoCollettivo getCatalogoCollettivo(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._catalogoCollettivoList.size()) {
            throw new IndexOutOfBoundsException("getCatalogoCollettivo: Index value '" + index + "' not in range [0.." + (this._catalogoCollettivoList.size() - 1) + "]");
        }

        return (it.inera.abi.logic.formatodiscambio.castor.CatalogoCollettivo) _catalogoCollettivoList.get(index);
    }

    /**
     * Method getCatalogoCollettivo.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public it.inera.abi.logic.formatodiscambio.castor.CatalogoCollettivo[] getCatalogoCollettivo(
    ) {
        it.inera.abi.logic.formatodiscambio.castor.CatalogoCollettivo[] array = new it.inera.abi.logic.formatodiscambio.castor.CatalogoCollettivo[0];
        return (it.inera.abi.logic.formatodiscambio.castor.CatalogoCollettivo[]) this._catalogoCollettivoList.toArray(array);
    }

    /**
     * Method getCatalogoCollettivoCount.
     * 
     * @return the size of this collection
     */
    public int getCatalogoCollettivoCount(
    ) {
        return this._catalogoCollettivoList.size();
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
     * Method getCatalogoSpeciale.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * it.inera.abi.logic.formatodiscambio.castor.CatalogoSpeciale
     * at the given index
     */
    public it.inera.abi.logic.formatodiscambio.castor.CatalogoSpeciale getCatalogoSpeciale(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._catalogoSpecialeList.size()) {
            throw new IndexOutOfBoundsException("getCatalogoSpeciale: Index value '" + index + "' not in range [0.." + (this._catalogoSpecialeList.size() - 1) + "]");
        }

        return (it.inera.abi.logic.formatodiscambio.castor.CatalogoSpeciale) _catalogoSpecialeList.get(index);
    }

    /**
     * Method getCatalogoSpeciale.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public it.inera.abi.logic.formatodiscambio.castor.CatalogoSpeciale[] getCatalogoSpeciale(
    ) {
        it.inera.abi.logic.formatodiscambio.castor.CatalogoSpeciale[] array = new it.inera.abi.logic.formatodiscambio.castor.CatalogoSpeciale[0];
        return (it.inera.abi.logic.formatodiscambio.castor.CatalogoSpeciale[]) this._catalogoSpecialeList.toArray(array);
    }

    /**
     * Method getCatalogoSpecialeCount.
     * 
     * @return the size of this collection
     */
    public int getCatalogoSpecialeCount(
    ) {
        return this._catalogoSpecialeList.size();
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
     * Method iterateCatalogoCollettivo.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends it.inera.abi.logic.formatodiscambio.castor.CatalogoCollettivo> iterateCatalogoCollettivo(
    ) {
        return this._catalogoCollettivoList.iterator();
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
     * Method iterateCatalogoSpeciale.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends it.inera.abi.logic.formatodiscambio.castor.CatalogoSpeciale> iterateCatalogoSpeciale(
    ) {
        return this._catalogoSpecialeList.iterator();
    }

    /**
     */
    public void removeAllCatalogoCollettivo(
    ) {
        this._catalogoCollettivoList.clear();
    }

    /**
     */
    public void removeAllCatalogoGenerale(
    ) {
        this._catalogoGeneraleList.clear();
    }

    /**
     */
    public void removeAllCatalogoSpeciale(
    ) {
        this._catalogoSpecialeList.clear();
    }

    /**
     * Method removeCatalogoCollettivo.
     * 
     * @param vCatalogoCollettivo
     * @return true if the object was removed from the collection.
     */
    public boolean removeCatalogoCollettivo(
            final it.inera.abi.logic.formatodiscambio.castor.CatalogoCollettivo vCatalogoCollettivo) {
        boolean removed = _catalogoCollettivoList.remove(vCatalogoCollettivo);
        return removed;
    }

    /**
     * Method removeCatalogoCollettivoAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public it.inera.abi.logic.formatodiscambio.castor.CatalogoCollettivo removeCatalogoCollettivoAt(
            final int index) {
        java.lang.Object obj = this._catalogoCollettivoList.remove(index);
        return (it.inera.abi.logic.formatodiscambio.castor.CatalogoCollettivo) obj;
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
     * Method removeCatalogoSpeciale.
     * 
     * @param vCatalogoSpeciale
     * @return true if the object was removed from the collection.
     */
    public boolean removeCatalogoSpeciale(
            final it.inera.abi.logic.formatodiscambio.castor.CatalogoSpeciale vCatalogoSpeciale) {
        boolean removed = _catalogoSpecialeList.remove(vCatalogoSpeciale);
        return removed;
    }

    /**
     * Method removeCatalogoSpecialeAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public it.inera.abi.logic.formatodiscambio.castor.CatalogoSpeciale removeCatalogoSpecialeAt(
            final int index) {
        java.lang.Object obj = this._catalogoSpecialeList.remove(index);
        return (it.inera.abi.logic.formatodiscambio.castor.CatalogoSpeciale) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vCatalogoCollettivo
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setCatalogoCollettivo(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.CatalogoCollettivo vCatalogoCollettivo)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._catalogoCollettivoList.size()) {
            throw new IndexOutOfBoundsException("setCatalogoCollettivo: Index value '" + index + "' not in range [0.." + (this._catalogoCollettivoList.size() - 1) + "]");
        }

        this._catalogoCollettivoList.set(index, vCatalogoCollettivo);
    }

    /**
     * 
     * 
     * @param vCatalogoCollettivoArray
     */
    public void setCatalogoCollettivo(
            final it.inera.abi.logic.formatodiscambio.castor.CatalogoCollettivo[] vCatalogoCollettivoArray) {
        //-- copy array
        _catalogoCollettivoList.clear();

        for (int i = 0; i < vCatalogoCollettivoArray.length; i++) {
                this._catalogoCollettivoList.add(vCatalogoCollettivoArray[i]);
        }
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
     * 
     * 
     * @param index
     * @param vCatalogoSpeciale
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setCatalogoSpeciale(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.CatalogoSpeciale vCatalogoSpeciale)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._catalogoSpecialeList.size()) {
            throw new IndexOutOfBoundsException("setCatalogoSpeciale: Index value '" + index + "' not in range [0.." + (this._catalogoSpecialeList.size() - 1) + "]");
        }

        this._catalogoSpecialeList.set(index, vCatalogoSpeciale);
    }

    /**
     * 
     * 
     * @param vCatalogoSpecialeArray
     */
    public void setCatalogoSpeciale(
            final it.inera.abi.logic.formatodiscambio.castor.CatalogoSpeciale[] vCatalogoSpecialeArray) {
        //-- copy array
        _catalogoSpecialeList.clear();

        for (int i = 0; i < vCatalogoSpecialeArray.length; i++) {
                this._catalogoSpecialeList.add(vCatalogoSpecialeArray[i]);
        }
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
