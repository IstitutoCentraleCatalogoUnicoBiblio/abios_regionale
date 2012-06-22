/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: OrarioType.java,v 1.1 2012/06/22 13:55:04 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Questo tipo descrive sostanzialmente l'orario ufficiale,
 *  ma è anche un tipo base da cui derivare le variazioni di
 *  orario. Poiché le variazioni sono all'interno dei
 *  servizi, questo tipo base deve essere globale.
 *  
 *  La struttura di questo tipo è molto semplice: un giorno
 *  della settimana, un orario iniziale e un'orario finale.
 *  Sebbene il questionario ICCU preveda una struttura più
 *  articolata, queste informazioni sono sufficienti a
 *  descrivere qualsiasi ragionevole situazione, e
 *  dovrebbero essere gestibili da qualsiasi base dati.
 *  Strutture più articolate possono essere ricondotte a
 *  questa, ripetendo opportunamente gli elementi.
 *  
 *  Per i limiti orari si potrebbe specificare un vincolo
 *  più fine, perché quello attuale accetterebbe anche un
 *  orario come "35:72".
 *  
 * 
 * @version $Revision: 1.1 $ $Date: 2012/06/22 13:55:04 $
 */
@SuppressWarnings("serial")
public abstract class OrarioType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _orarioList.
     */
    private java.util.List<it.inera.abi.logic.formatodiscambio.castor.Orario> _orarioList;


      //----------------/
     //- Constructors -/
    //----------------/

    public OrarioType() {
        super();
        this._orarioList = new java.util.ArrayList<it.inera.abi.logic.formatodiscambio.castor.Orario>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vOrario
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addOrario(
            final it.inera.abi.logic.formatodiscambio.castor.Orario vOrario)
    throws java.lang.IndexOutOfBoundsException {
        this._orarioList.add(vOrario);
    }

    /**
     * 
     * 
     * @param index
     * @param vOrario
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addOrario(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.Orario vOrario)
    throws java.lang.IndexOutOfBoundsException {
        this._orarioList.add(index, vOrario);
    }

    /**
     * Method enumerateOrario.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends it.inera.abi.logic.formatodiscambio.castor.Orario> enumerateOrario(
    ) {
        return java.util.Collections.enumeration(this._orarioList);
    }

    /**
     * Method getOrario.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * it.inera.abi.logic.formatodiscambio.castor.Orario at the
     * given index
     */
    public it.inera.abi.logic.formatodiscambio.castor.Orario getOrario(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._orarioList.size()) {
            throw new IndexOutOfBoundsException("getOrario: Index value '" + index + "' not in range [0.." + (this._orarioList.size() - 1) + "]");
        }

        return (it.inera.abi.logic.formatodiscambio.castor.Orario) _orarioList.get(index);
    }

    /**
     * Method getOrario.Returns the contents of the collection in
     * an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public it.inera.abi.logic.formatodiscambio.castor.Orario[] getOrario(
    ) {
        it.inera.abi.logic.formatodiscambio.castor.Orario[] array = new it.inera.abi.logic.formatodiscambio.castor.Orario[0];
        return (it.inera.abi.logic.formatodiscambio.castor.Orario[]) this._orarioList.toArray(array);
    }

    /**
     * Method getOrarioCount.
     * 
     * @return the size of this collection
     */
    public int getOrarioCount(
    ) {
        return this._orarioList.size();
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
     * Method iterateOrario.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends it.inera.abi.logic.formatodiscambio.castor.Orario> iterateOrario(
    ) {
        return this._orarioList.iterator();
    }

    /**
     */
    public void removeAllOrario(
    ) {
        this._orarioList.clear();
    }

    /**
     * Method removeOrario.
     * 
     * @param vOrario
     * @return true if the object was removed from the collection.
     */
    public boolean removeOrario(
            final it.inera.abi.logic.formatodiscambio.castor.Orario vOrario) {
        boolean removed = _orarioList.remove(vOrario);
        return removed;
    }

    /**
     * Method removeOrarioAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public it.inera.abi.logic.formatodiscambio.castor.Orario removeOrarioAt(
            final int index) {
        java.lang.Object obj = this._orarioList.remove(index);
        return (it.inera.abi.logic.formatodiscambio.castor.Orario) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vOrario
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setOrario(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.Orario vOrario)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._orarioList.size()) {
            throw new IndexOutOfBoundsException("setOrario: Index value '" + index + "' not in range [0.." + (this._orarioList.size() - 1) + "]");
        }

        this._orarioList.set(index, vOrario);
    }

    /**
     * 
     * 
     * @param vOrarioArray
     */
    public void setOrario(
            final it.inera.abi.logic.formatodiscambio.castor.Orario[] vOrarioArray) {
        //-- copy array
        _orarioList.clear();

        for (int i = 0; i < vOrarioArray.length; i++) {
                this._orarioList.add(vOrarioArray[i]);
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
