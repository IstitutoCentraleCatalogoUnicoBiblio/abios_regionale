/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: Interbibliotecario.java,v 1.1 2012/06/22 13:55:04 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * In questo elemento, pur opzionale,
 *  si è ritenuto opportuno rendere
 *  obbligatorio almeno il
 *  sotto-elemento "tipo", escludendo la
 *  paradossale situazione di una
 *  biblioteca che offre il servizio, ma
 *  non sa di che tipo.
 *  
 *  L'elemento è ripetibile al più due
 *  volte perché la biblioteca può
 *  offrire due tipi di prestito
 *  interbibliotecario, ed è opportuno
 *  che essi siano descritti in elementi
 *  separati. Tuttavia non si può
 *  escludere che vengano descritti due
 *  elementi dello stesso tipo, sebbene
 *  un applicativo dovrebbe produrne
 *  tipicamente sempre al più due, e di
 *  tipi diversi.
 *  
 *  Ovviamente l'elemento "sistema-ill"
 *  è opzionale e ripetibile, in quanto
 *  la biblioteca può partecipare a più
 *  d'uno.
 *  
 * 
 * @version $Revision: 1.1 $ $Date: 2012/06/22 13:55:04 $
 */
@SuppressWarnings("serial")
public class Interbibliotecario implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _tipoPrestitoList.
     */
    private java.util.List<it.inera.abi.logic.formatodiscambio.castor.TipoPrestito> _tipoPrestitoList;

    /**
     * Field _prestitoInterbibliotecarioAutomatizzato.
     */
    private it.inera.abi.logic.formatodiscambio.castor.types.SiNoType _prestitoInterbibliotecarioAutomatizzato;

    /**
     * Questo elemento
     *  conteggia il totale dei
     *  prestiti di questo tipo
     *  effettuati nel periodo
     *  di osservazione.
     *  Dev'essere un numero.
     *  
     */
    private java.math.BigDecimal _totalePrestiti;

    /**
     * Field _sistemaIllList.
     */
    private java.util.List<it.inera.abi.logic.formatodiscambio.castor.SistemaIll> _sistemaIllList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Interbibliotecario() {
        super();
        this._tipoPrestitoList = new java.util.ArrayList<it.inera.abi.logic.formatodiscambio.castor.TipoPrestito>();
        this._sistemaIllList = new java.util.ArrayList<it.inera.abi.logic.formatodiscambio.castor.SistemaIll>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vSistemaIll
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addSistemaIll(
            final it.inera.abi.logic.formatodiscambio.castor.SistemaIll vSistemaIll)
    throws java.lang.IndexOutOfBoundsException {
        this._sistemaIllList.add(vSistemaIll);
    }

    /**
     * 
     * 
     * @param index
     * @param vSistemaIll
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addSistemaIll(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.SistemaIll vSistemaIll)
    throws java.lang.IndexOutOfBoundsException {
        this._sistemaIllList.add(index, vSistemaIll);
    }

    /**
     * 
     * 
     * @param vTipoPrestito
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addTipoPrestito(
            final it.inera.abi.logic.formatodiscambio.castor.TipoPrestito vTipoPrestito)
    throws java.lang.IndexOutOfBoundsException {
        this._tipoPrestitoList.add(vTipoPrestito);
    }

    /**
     * 
     * 
     * @param index
     * @param vTipoPrestito
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addTipoPrestito(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.TipoPrestito vTipoPrestito)
    throws java.lang.IndexOutOfBoundsException {
        this._tipoPrestitoList.add(index, vTipoPrestito);
    }

    /**
     * Method enumerateSistemaIll.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends it.inera.abi.logic.formatodiscambio.castor.SistemaIll> enumerateSistemaIll(
    ) {
        return java.util.Collections.enumeration(this._sistemaIllList);
    }

    /**
     * Method enumerateTipoPrestito.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends it.inera.abi.logic.formatodiscambio.castor.TipoPrestito> enumerateTipoPrestito(
    ) {
        return java.util.Collections.enumeration(this._tipoPrestitoList);
    }

    /**
     * Returns the value of field
     * 'prestitoInterbibliotecarioAutomatizzato'.
     * 
     * @return the value of field
     * 'PrestitoInterbibliotecarioAutomatizzato'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.types.SiNoType getPrestitoInterbibliotecarioAutomatizzato(
    ) {
        return this._prestitoInterbibliotecarioAutomatizzato;
    }

    /**
     * Method getSistemaIll.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * it.inera.abi.logic.formatodiscambio.castor.SistemaIll at the
     * given index
     */
    public it.inera.abi.logic.formatodiscambio.castor.SistemaIll getSistemaIll(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._sistemaIllList.size()) {
            throw new IndexOutOfBoundsException("getSistemaIll: Index value '" + index + "' not in range [0.." + (this._sistemaIllList.size() - 1) + "]");
        }

        return (it.inera.abi.logic.formatodiscambio.castor.SistemaIll) _sistemaIllList.get(index);
    }

    /**
     * Method getSistemaIll.Returns the contents of the collection
     * in an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public it.inera.abi.logic.formatodiscambio.castor.SistemaIll[] getSistemaIll(
    ) {
        it.inera.abi.logic.formatodiscambio.castor.SistemaIll[] array = new it.inera.abi.logic.formatodiscambio.castor.SistemaIll[0];
        return (it.inera.abi.logic.formatodiscambio.castor.SistemaIll[]) this._sistemaIllList.toArray(array);
    }

    /**
     * Method getSistemaIllCount.
     * 
     * @return the size of this collection
     */
    public int getSistemaIllCount(
    ) {
        return this._sistemaIllList.size();
    }

    /**
     * Method getTipoPrestito.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * it.inera.abi.logic.formatodiscambio.castor.TipoPrestito at
     * the given index
     */
    public it.inera.abi.logic.formatodiscambio.castor.TipoPrestito getTipoPrestito(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._tipoPrestitoList.size()) {
            throw new IndexOutOfBoundsException("getTipoPrestito: Index value '" + index + "' not in range [0.." + (this._tipoPrestitoList.size() - 1) + "]");
        }

        return (it.inera.abi.logic.formatodiscambio.castor.TipoPrestito) _tipoPrestitoList.get(index);
    }

    /**
     * Method getTipoPrestito.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public it.inera.abi.logic.formatodiscambio.castor.TipoPrestito[] getTipoPrestito(
    ) {
        it.inera.abi.logic.formatodiscambio.castor.TipoPrestito[] array = new it.inera.abi.logic.formatodiscambio.castor.TipoPrestito[0];
        return (it.inera.abi.logic.formatodiscambio.castor.TipoPrestito[]) this._tipoPrestitoList.toArray(array);
    }

    /**
     * Method getTipoPrestitoCount.
     * 
     * @return the size of this collection
     */
    public int getTipoPrestitoCount(
    ) {
        return this._tipoPrestitoList.size();
    }

    /**
     * Returns the value of field 'totalePrestiti'. The field
     * 'totalePrestiti' has the following description: Questo
     * elemento
     *  conteggia il totale dei
     *  prestiti di questo tipo
     *  effettuati nel periodo
     *  di osservazione.
     *  Dev'essere un numero.
     *  
     * 
     * @return the value of field 'TotalePrestiti'.
     */
    public java.math.BigDecimal getTotalePrestiti(
    ) {
        return this._totalePrestiti;
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
     * Method iterateSistemaIll.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends it.inera.abi.logic.formatodiscambio.castor.SistemaIll> iterateSistemaIll(
    ) {
        return this._sistemaIllList.iterator();
    }

    /**
     * Method iterateTipoPrestito.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends it.inera.abi.logic.formatodiscambio.castor.TipoPrestito> iterateTipoPrestito(
    ) {
        return this._tipoPrestitoList.iterator();
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
    public void removeAllSistemaIll(
    ) {
        this._sistemaIllList.clear();
    }

    /**
     */
    public void removeAllTipoPrestito(
    ) {
        this._tipoPrestitoList.clear();
    }

    /**
     * Method removeSistemaIll.
     * 
     * @param vSistemaIll
     * @return true if the object was removed from the collection.
     */
    public boolean removeSistemaIll(
            final it.inera.abi.logic.formatodiscambio.castor.SistemaIll vSistemaIll) {
        boolean removed = _sistemaIllList.remove(vSistemaIll);
        return removed;
    }

    /**
     * Method removeSistemaIllAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public it.inera.abi.logic.formatodiscambio.castor.SistemaIll removeSistemaIllAt(
            final int index) {
        java.lang.Object obj = this._sistemaIllList.remove(index);
        return (it.inera.abi.logic.formatodiscambio.castor.SistemaIll) obj;
    }

    /**
     * Method removeTipoPrestito.
     * 
     * @param vTipoPrestito
     * @return true if the object was removed from the collection.
     */
    public boolean removeTipoPrestito(
            final it.inera.abi.logic.formatodiscambio.castor.TipoPrestito vTipoPrestito) {
        boolean removed = _tipoPrestitoList.remove(vTipoPrestito);
        return removed;
    }

    /**
     * Method removeTipoPrestitoAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public it.inera.abi.logic.formatodiscambio.castor.TipoPrestito removeTipoPrestitoAt(
            final int index) {
        java.lang.Object obj = this._tipoPrestitoList.remove(index);
        return (it.inera.abi.logic.formatodiscambio.castor.TipoPrestito) obj;
    }

    /**
     * Sets the value of field
     * 'prestitoInterbibliotecarioAutomatizzato'.
     * 
     * @param prestitoInterbibliotecarioAutomatizzato the value of
     * field 'prestitoInterbibliotecarioAutomatizzato'.
     */
    public void setPrestitoInterbibliotecarioAutomatizzato(
            final it.inera.abi.logic.formatodiscambio.castor.types.SiNoType prestitoInterbibliotecarioAutomatizzato) {
        this._prestitoInterbibliotecarioAutomatizzato = prestitoInterbibliotecarioAutomatizzato;
    }

    /**
     * 
     * 
     * @param index
     * @param vSistemaIll
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setSistemaIll(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.SistemaIll vSistemaIll)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._sistemaIllList.size()) {
            throw new IndexOutOfBoundsException("setSistemaIll: Index value '" + index + "' not in range [0.." + (this._sistemaIllList.size() - 1) + "]");
        }

        this._sistemaIllList.set(index, vSistemaIll);
    }

    /**
     * 
     * 
     * @param vSistemaIllArray
     */
    public void setSistemaIll(
            final it.inera.abi.logic.formatodiscambio.castor.SistemaIll[] vSistemaIllArray) {
        //-- copy array
        _sistemaIllList.clear();

        for (int i = 0; i < vSistemaIllArray.length; i++) {
                this._sistemaIllList.add(vSistemaIllArray[i]);
        }
    }

    /**
     * 
     * 
     * @param index
     * @param vTipoPrestito
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setTipoPrestito(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.TipoPrestito vTipoPrestito)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._tipoPrestitoList.size()) {
            throw new IndexOutOfBoundsException("setTipoPrestito: Index value '" + index + "' not in range [0.." + (this._tipoPrestitoList.size() - 1) + "]");
        }

        this._tipoPrestitoList.set(index, vTipoPrestito);
    }

    /**
     * 
     * 
     * @param vTipoPrestitoArray
     */
    public void setTipoPrestito(
            final it.inera.abi.logic.formatodiscambio.castor.TipoPrestito[] vTipoPrestitoArray) {
        //-- copy array
        _tipoPrestitoList.clear();

        for (int i = 0; i < vTipoPrestitoArray.length; i++) {
                this._tipoPrestitoList.add(vTipoPrestitoArray[i]);
        }
    }

    /**
     * Sets the value of field 'totalePrestiti'. The field
     * 'totalePrestiti' has the following description: Questo
     * elemento
     *  conteggia il totale dei
     *  prestiti di questo tipo
     *  effettuati nel periodo
     *  di osservazione.
     *  Dev'essere un numero.
     *  
     * 
     * @param totalePrestiti the value of field 'totalePrestiti'.
     */
    public void setTotalePrestiti(
            final java.math.BigDecimal totalePrestiti) {
        this._totalePrestiti = totalePrestiti;
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
     * it.inera.abi.logic.formatodiscambio.castor.Interbibliotecario
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Interbibliotecario unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Interbibliotecario) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Interbibliotecario.class, reader);
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
