/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: BibliotecaType.java,v 1.3 2012/07/30 15:17:05 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * L'elemento "biblioteca" è la radice di un documento di
 *  questo tipo. Tale elemento fa parte di un content model
 *  "xsd:all", che non obbliga a rispettare l'ordine in cui
 *  si presentano gli elementi, a differenza di
 *  "xsd:sequence".
 *  
 *  In tutto il resto dello schema si tende ad usare questo
 *  content model, perché meno rigido. Non dovrebbe
 *  comportare eccessivo carico per il parser.
 *  
 *  La maggior parte degli elementi e attributi risultano
 *  opzionali e ripetibili, ma questi aspetti saranno
 *  oggetto di una revisione futura dell'intero schema alla
 *  luce delle possibilità e delle richieste dei diversi
 *  partner interessati a scambiare dati nel formato qui
 *  descritto.
 *  
 *  Nei casi più elementari si è ritenuto opportuno fissare
 *  l'obbligatorietà e la ripetibilità degli elementi.
 *  
 *  L'elemento biblioteca adesso è obbligatorio.
 *  
 * 
 * @version $Revision: 1.3 $ $Date: 2012/07/30 15:17:05 $
 */
@SuppressWarnings("serial")
public abstract class BibliotecaType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _anagrafica.
     */
    private it.inera.abi.logic.formatodiscambio.castor.Anagrafica _anagrafica;

    /**
     * Field _cataloghi.
     */
    private it.inera.abi.logic.formatodiscambio.castor.Cataloghi _cataloghi;

    /**
     * Field _patrimonio.
     */
    private it.inera.abi.logic.formatodiscambio.castor.Patrimonio _patrimonio;

    /**
     * Field _specializzazioneList.
     */
    private java.util.List<it.inera.abi.logic.formatodiscambio.castor.Specializzazione> _specializzazioneList;

    /**
     * Field _servizi.
     */
    private it.inera.abi.logic.formatodiscambio.castor.Servizi _servizi;

    /**
     * Field _amministrativa.
     */
    private it.inera.abi.logic.formatodiscambio.castor.Amministrativa _amministrativa;


      //----------------/
     //- Constructors -/
    //----------------/

    public BibliotecaType() {
        super();
        this._specializzazioneList = new java.util.ArrayList<it.inera.abi.logic.formatodiscambio.castor.Specializzazione>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vSpecializzazione
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addSpecializzazione(
            final it.inera.abi.logic.formatodiscambio.castor.Specializzazione vSpecializzazione)
    throws java.lang.IndexOutOfBoundsException {
        this._specializzazioneList.add(vSpecializzazione);
    }

    /**
     * 
     * 
     * @param index
     * @param vSpecializzazione
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addSpecializzazione(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.Specializzazione vSpecializzazione)
    throws java.lang.IndexOutOfBoundsException {
        this._specializzazioneList.add(index, vSpecializzazione);
    }

    /**
     * Method enumerateSpecializzazione.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends it.inera.abi.logic.formatodiscambio.castor.Specializzazione> enumerateSpecializzazione(
    ) {
        return java.util.Collections.enumeration(this._specializzazioneList);
    }

    /**
     * Returns the value of field 'amministrativa'.
     * 
     * @return the value of field 'Amministrativa'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Amministrativa getAmministrativa(
    ) {
        return this._amministrativa;
    }

    /**
     * Returns the value of field 'anagrafica'.
     * 
     * @return the value of field 'Anagrafica'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Anagrafica getAnagrafica(
    ) {
        return this._anagrafica;
    }

    /**
     * Returns the value of field 'cataloghi'.
     * 
     * @return the value of field 'Cataloghi'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Cataloghi getCataloghi(
    ) {
        return this._cataloghi;
    }

    /**
     * Returns the value of field 'patrimonio'.
     * 
     * @return the value of field 'Patrimonio'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Patrimonio getPatrimonio(
    ) {
        return this._patrimonio;
    }

    /**
     * Returns the value of field 'servizi'.
     * 
     * @return the value of field 'Servizi'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Servizi getServizi(
    ) {
        return this._servizi;
    }

    /**
     * Method getSpecializzazione.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * it.inera.abi.logic.formatodiscambio.castor.Specializzazione
     * at the given index
     */
    public it.inera.abi.logic.formatodiscambio.castor.Specializzazione getSpecializzazione(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._specializzazioneList.size()) {
            throw new IndexOutOfBoundsException("getSpecializzazione: Index value '" + index + "' not in range [0.." + (this._specializzazioneList.size() - 1) + "]");
        }

        return (it.inera.abi.logic.formatodiscambio.castor.Specializzazione) _specializzazioneList.get(index);
    }

    /**
     * Method getSpecializzazione.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public it.inera.abi.logic.formatodiscambio.castor.Specializzazione[] getSpecializzazione(
    ) {
        it.inera.abi.logic.formatodiscambio.castor.Specializzazione[] array = new it.inera.abi.logic.formatodiscambio.castor.Specializzazione[0];
        return (it.inera.abi.logic.formatodiscambio.castor.Specializzazione[]) this._specializzazioneList.toArray(array);
    }

    /**
     * Method getSpecializzazioneCount.
     * 
     * @return the size of this collection
     */
    public int getSpecializzazioneCount(
    ) {
        return this._specializzazioneList.size();
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
     * Method iterateSpecializzazione.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends it.inera.abi.logic.formatodiscambio.castor.Specializzazione> iterateSpecializzazione(
    ) {
        return this._specializzazioneList.iterator();
    }

    /**
     */
    public void removeAllSpecializzazione(
    ) {
        this._specializzazioneList.clear();
    }

    /**
     * Method removeSpecializzazione.
     * 
     * @param vSpecializzazione
     * @return true if the object was removed from the collection.
     */
    public boolean removeSpecializzazione(
            final it.inera.abi.logic.formatodiscambio.castor.Specializzazione vSpecializzazione) {
        boolean removed = _specializzazioneList.remove(vSpecializzazione);
        return removed;
    }

    /**
     * Method removeSpecializzazioneAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public it.inera.abi.logic.formatodiscambio.castor.Specializzazione removeSpecializzazioneAt(
            final int index) {
        java.lang.Object obj = this._specializzazioneList.remove(index);
        return (it.inera.abi.logic.formatodiscambio.castor.Specializzazione) obj;
    }

    /**
     * Sets the value of field 'amministrativa'.
     * 
     * @param amministrativa the value of field 'amministrativa'.
     */
    public void setAmministrativa(
            final it.inera.abi.logic.formatodiscambio.castor.Amministrativa amministrativa) {
        this._amministrativa = amministrativa;
    }

    /**
     * Sets the value of field 'anagrafica'.
     * 
     * @param anagrafica the value of field 'anagrafica'.
     */
    public void setAnagrafica(
            final it.inera.abi.logic.formatodiscambio.castor.Anagrafica anagrafica) {
        this._anagrafica = anagrafica;
    }

    /**
     * Sets the value of field 'cataloghi'.
     * 
     * @param cataloghi the value of field 'cataloghi'.
     */
    public void setCataloghi(
            final it.inera.abi.logic.formatodiscambio.castor.Cataloghi cataloghi) {
        this._cataloghi = cataloghi;
    }

    /**
     * Sets the value of field 'patrimonio'.
     * 
     * @param patrimonio the value of field 'patrimonio'.
     */
    public void setPatrimonio(
            final it.inera.abi.logic.formatodiscambio.castor.Patrimonio patrimonio) {
        this._patrimonio = patrimonio;
    }

    /**
     * Sets the value of field 'servizi'.
     * 
     * @param servizi the value of field 'servizi'.
     */
    public void setServizi(
            final it.inera.abi.logic.formatodiscambio.castor.Servizi servizi) {
        this._servizi = servizi;
    }

    /**
     * 
     * 
     * @param index
     * @param vSpecializzazione
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setSpecializzazione(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.Specializzazione vSpecializzazione)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._specializzazioneList.size()) {
            throw new IndexOutOfBoundsException("setSpecializzazione: Index value '" + index + "' not in range [0.." + (this._specializzazioneList.size() - 1) + "]");
        }

        this._specializzazioneList.set(index, vSpecializzazione);
    }

    /**
     * 
     * 
     * @param vSpecializzazioneArray
     */
    public void setSpecializzazione(
            final it.inera.abi.logic.formatodiscambio.castor.Specializzazione[] vSpecializzazioneArray) {
        //-- copy array
        _specializzazioneList.clear();

        for (int i = 0; i < vSpecializzazioneArray.length; i++) {
                this._specializzazioneList.add(vSpecializzazioneArray[i]);
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
