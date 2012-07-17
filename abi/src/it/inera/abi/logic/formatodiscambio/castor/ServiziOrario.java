/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: ServiziOrario.java,v 1.3 2012/07/30 15:17:05 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Questo elemento non ripetibile raggruppa tutte
 *  le informazioni relative agli orari di accesso
 *  alla biblioteca. I sottoelementi, tutti
 *  opzionali, sono per lo più ripetibili, eccetto
 *  ovviamente l'orario ufficiale.
 *  
 *  L'elemento serve solo da contenitore. Non è
 *  necessario dal punto di vista applicativo, ma
 *  agevola la leggibilità dello schema, che in
 *  questa parte è piuttosto articolato.
 *  
 * 
 * @version $Revision: 1.3 $ $Date: 2012/07/30 15:17:05 $
 */
@SuppressWarnings("serial")
public class ServiziOrario implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Questo elemento contiene almeno un
     *  elemento "orario" di tipo
     *  "orarioType", da non confondere con
     *  l'elemento soprastante. Si veda più
     *  avanti la descrizione di questo tipo
     *  di elemento.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.Ufficiale _ufficiale;

    /**
     * Le variazioni di orario sono
     *  espresse da eventuali elementi di
     *  questo tipo. Esso è identico ad un
     *  orario ufficiale (orarioType), salvo per
     *  l'aggiunta di una nota esplicativa (e
     *  del periodo di validità, attualmente deprecato e non 
     *  considerato in import e export). La nota esplicativa
     *  può essere di qualsiasi forma.
     *  
     */
    private java.util.List<it.inera.abi.logic.formatodiscambio.castor.Variazione> _variazioneList;

    /**
     * Un periodo di chiusura è
     *  semplicemente una nota esplicativa (e
     *  del periodo di validità, attualmente deprecato e non 
     *  considerato in import e export). La nota esplicativa
     *  può essere di qualsiasi forma. (dal-al, periodo
     * natalizio,...).
     *  
     */
    private java.util.List<it.inera.abi.logic.formatodiscambio.castor.Chiusura> _chiusuraList;

    /**
     * Totale delle ore di apertura
     *  settimanali. Questo elemento può
     *  essere presente insieme all'orario
     *  dettagliato (di tipo orarioType), e
     *  il suo valore può essere incoerente
     *  con l'orario dettagliato, ma gli
     *  applicativi che ricevono i dati sono
     *  liberi di verificare la congruità
     *  dei dati segnalati oppure no.
     *  
     */
    private java.math.BigDecimal _oreSettimanali;

    /**
     * Totale delle ore di apertura
     *  pomeridiana settimanali. Ovviamente
     *  dovrebbe essere minore di
     *  ore-settimanali, ma circa la
     *  congruità di questo dato con gli
     *  altri, vale quanto già detto per
     *  ore-settimanali: non è garantito un
     *  controllo da parte degli applicativi
     *  riceventi.
     *  
     */
    private java.math.BigDecimal _oreSettimanaliPomeridiane;

    /**
     * Numero di settimane di apertura
     *  della biblioteca in un anno. Anche
     *  per questo dato, gli applicativi
     *  riceventi non dovrebbero essere
     *  costretti a verificare la coerenza
     *  con gli altri dati di questo gruppo.
     *  
     */
    private java.math.BigDecimal _settimaneApertura;


      //----------------/
     //- Constructors -/
    //----------------/

    public ServiziOrario() {
        super();
        this._variazioneList = new java.util.ArrayList<it.inera.abi.logic.formatodiscambio.castor.Variazione>();
        this._chiusuraList = new java.util.ArrayList<it.inera.abi.logic.formatodiscambio.castor.Chiusura>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vChiusura
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addChiusura(
            final it.inera.abi.logic.formatodiscambio.castor.Chiusura vChiusura)
    throws java.lang.IndexOutOfBoundsException {
        this._chiusuraList.add(vChiusura);
    }

    /**
     * 
     * 
     * @param index
     * @param vChiusura
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addChiusura(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.Chiusura vChiusura)
    throws java.lang.IndexOutOfBoundsException {
        this._chiusuraList.add(index, vChiusura);
    }

    /**
     * 
     * 
     * @param vVariazione
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addVariazione(
            final it.inera.abi.logic.formatodiscambio.castor.Variazione vVariazione)
    throws java.lang.IndexOutOfBoundsException {
        this._variazioneList.add(vVariazione);
    }

    /**
     * 
     * 
     * @param index
     * @param vVariazione
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addVariazione(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.Variazione vVariazione)
    throws java.lang.IndexOutOfBoundsException {
        this._variazioneList.add(index, vVariazione);
    }

    /**
     * Method enumerateChiusura.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends it.inera.abi.logic.formatodiscambio.castor.Chiusura> enumerateChiusura(
    ) {
        return java.util.Collections.enumeration(this._chiusuraList);
    }

    /**
     * Method enumerateVariazione.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends it.inera.abi.logic.formatodiscambio.castor.Variazione> enumerateVariazione(
    ) {
        return java.util.Collections.enumeration(this._variazioneList);
    }

    /**
     * Method getChiusura.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * it.inera.abi.logic.formatodiscambio.castor.Chiusura at the
     * given index
     */
    public it.inera.abi.logic.formatodiscambio.castor.Chiusura getChiusura(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._chiusuraList.size()) {
            throw new IndexOutOfBoundsException("getChiusura: Index value '" + index + "' not in range [0.." + (this._chiusuraList.size() - 1) + "]");
        }

        return (it.inera.abi.logic.formatodiscambio.castor.Chiusura) _chiusuraList.get(index);
    }

    /**
     * Method getChiusura.Returns the contents of the collection in
     * an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public it.inera.abi.logic.formatodiscambio.castor.Chiusura[] getChiusura(
    ) {
        it.inera.abi.logic.formatodiscambio.castor.Chiusura[] array = new it.inera.abi.logic.formatodiscambio.castor.Chiusura[0];
        return (it.inera.abi.logic.formatodiscambio.castor.Chiusura[]) this._chiusuraList.toArray(array);
    }

    /**
     * Method getChiusuraCount.
     * 
     * @return the size of this collection
     */
    public int getChiusuraCount(
    ) {
        return this._chiusuraList.size();
    }

    /**
     * Returns the value of field 'oreSettimanali'. The field
     * 'oreSettimanali' has the following description: Totale delle
     * ore di apertura
     *  settimanali. Questo elemento può
     *  essere presente insieme all'orario
     *  dettagliato (di tipo orarioType), e
     *  il suo valore può essere incoerente
     *  con l'orario dettagliato, ma gli
     *  applicativi che ricevono i dati sono
     *  liberi di verificare la congruità
     *  dei dati segnalati oppure no.
     *  
     * 
     * @return the value of field 'OreSettimanali'.
     */
    public java.math.BigDecimal getOreSettimanali(
    ) {
        return this._oreSettimanali;
    }

    /**
     * Returns the value of field 'oreSettimanaliPomeridiane'. The
     * field 'oreSettimanaliPomeridiane' has the following
     * description: Totale delle ore di apertura
     *  pomeridiana settimanali. Ovviamente
     *  dovrebbe essere minore di
     *  ore-settimanali, ma circa la
     *  congruità di questo dato con gli
     *  altri, vale quanto già detto per
     *  ore-settimanali: non è garantito un
     *  controllo da parte degli applicativi
     *  riceventi.
     *  
     * 
     * @return the value of field 'OreSettimanaliPomeridiane'.
     */
    public java.math.BigDecimal getOreSettimanaliPomeridiane(
    ) {
        return this._oreSettimanaliPomeridiane;
    }

    /**
     * Returns the value of field 'settimaneApertura'. The field
     * 'settimaneApertura' has the following description: Numero di
     * settimane di apertura
     *  della biblioteca in un anno. Anche
     *  per questo dato, gli applicativi
     *  riceventi non dovrebbero essere
     *  costretti a verificare la coerenza
     *  con gli altri dati di questo gruppo.
     *  
     * 
     * @return the value of field 'SettimaneApertura'.
     */
    public java.math.BigDecimal getSettimaneApertura(
    ) {
        return this._settimaneApertura;
    }

    /**
     * Returns the value of field 'ufficiale'. The field
     * 'ufficiale' has the following description: Questo elemento
     * contiene almeno un
     *  elemento "orario" di tipo
     *  "orarioType", da non confondere con
     *  l'elemento soprastante. Si veda più
     *  avanti la descrizione di questo tipo
     *  di elemento.
     *  
     * 
     * @return the value of field 'Ufficiale'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Ufficiale getUfficiale(
    ) {
        return this._ufficiale;
    }

    /**
     * Method getVariazione.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * it.inera.abi.logic.formatodiscambio.castor.Variazione at the
     * given index
     */
    public it.inera.abi.logic.formatodiscambio.castor.Variazione getVariazione(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._variazioneList.size()) {
            throw new IndexOutOfBoundsException("getVariazione: Index value '" + index + "' not in range [0.." + (this._variazioneList.size() - 1) + "]");
        }

        return (it.inera.abi.logic.formatodiscambio.castor.Variazione) _variazioneList.get(index);
    }

    /**
     * Method getVariazione.Returns the contents of the collection
     * in an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public it.inera.abi.logic.formatodiscambio.castor.Variazione[] getVariazione(
    ) {
        it.inera.abi.logic.formatodiscambio.castor.Variazione[] array = new it.inera.abi.logic.formatodiscambio.castor.Variazione[0];
        return (it.inera.abi.logic.formatodiscambio.castor.Variazione[]) this._variazioneList.toArray(array);
    }

    /**
     * Method getVariazioneCount.
     * 
     * @return the size of this collection
     */
    public int getVariazioneCount(
    ) {
        return this._variazioneList.size();
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
     * Method iterateChiusura.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends it.inera.abi.logic.formatodiscambio.castor.Chiusura> iterateChiusura(
    ) {
        return this._chiusuraList.iterator();
    }

    /**
     * Method iterateVariazione.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends it.inera.abi.logic.formatodiscambio.castor.Variazione> iterateVariazione(
    ) {
        return this._variazioneList.iterator();
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
    public void removeAllChiusura(
    ) {
        this._chiusuraList.clear();
    }

    /**
     */
    public void removeAllVariazione(
    ) {
        this._variazioneList.clear();
    }

    /**
     * Method removeChiusura.
     * 
     * @param vChiusura
     * @return true if the object was removed from the collection.
     */
    public boolean removeChiusura(
            final it.inera.abi.logic.formatodiscambio.castor.Chiusura vChiusura) {
        boolean removed = _chiusuraList.remove(vChiusura);
        return removed;
    }

    /**
     * Method removeChiusuraAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public it.inera.abi.logic.formatodiscambio.castor.Chiusura removeChiusuraAt(
            final int index) {
        java.lang.Object obj = this._chiusuraList.remove(index);
        return (it.inera.abi.logic.formatodiscambio.castor.Chiusura) obj;
    }

    /**
     * Method removeVariazione.
     * 
     * @param vVariazione
     * @return true if the object was removed from the collection.
     */
    public boolean removeVariazione(
            final it.inera.abi.logic.formatodiscambio.castor.Variazione vVariazione) {
        boolean removed = _variazioneList.remove(vVariazione);
        return removed;
    }

    /**
     * Method removeVariazioneAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public it.inera.abi.logic.formatodiscambio.castor.Variazione removeVariazioneAt(
            final int index) {
        java.lang.Object obj = this._variazioneList.remove(index);
        return (it.inera.abi.logic.formatodiscambio.castor.Variazione) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vChiusura
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setChiusura(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.Chiusura vChiusura)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._chiusuraList.size()) {
            throw new IndexOutOfBoundsException("setChiusura: Index value '" + index + "' not in range [0.." + (this._chiusuraList.size() - 1) + "]");
        }

        this._chiusuraList.set(index, vChiusura);
    }

    /**
     * 
     * 
     * @param vChiusuraArray
     */
    public void setChiusura(
            final it.inera.abi.logic.formatodiscambio.castor.Chiusura[] vChiusuraArray) {
        //-- copy array
        _chiusuraList.clear();

        for (int i = 0; i < vChiusuraArray.length; i++) {
                this._chiusuraList.add(vChiusuraArray[i]);
        }
    }

    /**
     * Sets the value of field 'oreSettimanali'. The field
     * 'oreSettimanali' has the following description: Totale delle
     * ore di apertura
     *  settimanali. Questo elemento può
     *  essere presente insieme all'orario
     *  dettagliato (di tipo orarioType), e
     *  il suo valore può essere incoerente
     *  con l'orario dettagliato, ma gli
     *  applicativi che ricevono i dati sono
     *  liberi di verificare la congruità
     *  dei dati segnalati oppure no.
     *  
     * 
     * @param oreSettimanali the value of field 'oreSettimanali'.
     */
    public void setOreSettimanali(
            final java.math.BigDecimal oreSettimanali) {
        this._oreSettimanali = oreSettimanali;
    }

    /**
     * Sets the value of field 'oreSettimanaliPomeridiane'. The
     * field 'oreSettimanaliPomeridiane' has the following
     * description: Totale delle ore di apertura
     *  pomeridiana settimanali. Ovviamente
     *  dovrebbe essere minore di
     *  ore-settimanali, ma circa la
     *  congruità di questo dato con gli
     *  altri, vale quanto già detto per
     *  ore-settimanali: non è garantito un
     *  controllo da parte degli applicativi
     *  riceventi.
     *  
     * 
     * @param oreSettimanaliPomeridiane the value of field
     * 'oreSettimanaliPomeridiane'.
     */
    public void setOreSettimanaliPomeridiane(
            final java.math.BigDecimal oreSettimanaliPomeridiane) {
        this._oreSettimanaliPomeridiane = oreSettimanaliPomeridiane;
    }

    /**
     * Sets the value of field 'settimaneApertura'. The field
     * 'settimaneApertura' has the following description: Numero di
     * settimane di apertura
     *  della biblioteca in un anno. Anche
     *  per questo dato, gli applicativi
     *  riceventi non dovrebbero essere
     *  costretti a verificare la coerenza
     *  con gli altri dati di questo gruppo.
     *  
     * 
     * @param settimaneApertura the value of field
     * 'settimaneApertura'.
     */
    public void setSettimaneApertura(
            final java.math.BigDecimal settimaneApertura) {
        this._settimaneApertura = settimaneApertura;
    }

    /**
     * Sets the value of field 'ufficiale'. The field 'ufficiale'
     * has the following description: Questo elemento contiene
     * almeno un
     *  elemento "orario" di tipo
     *  "orarioType", da non confondere con
     *  l'elemento soprastante. Si veda più
     *  avanti la descrizione di questo tipo
     *  di elemento.
     *  
     * 
     * @param ufficiale the value of field 'ufficiale'.
     */
    public void setUfficiale(
            final it.inera.abi.logic.formatodiscambio.castor.Ufficiale ufficiale) {
        this._ufficiale = ufficiale;
    }

    /**
     * 
     * 
     * @param index
     * @param vVariazione
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setVariazione(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.Variazione vVariazione)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._variazioneList.size()) {
            throw new IndexOutOfBoundsException("setVariazione: Index value '" + index + "' not in range [0.." + (this._variazioneList.size() - 1) + "]");
        }

        this._variazioneList.set(index, vVariazione);
    }

    /**
     * 
     * 
     * @param vVariazioneArray
     */
    public void setVariazione(
            final it.inera.abi.logic.formatodiscambio.castor.Variazione[] vVariazioneArray) {
        //-- copy array
        _variazioneList.clear();

        for (int i = 0; i < vVariazioneArray.length; i++) {
                this._variazioneList.add(vVariazioneArray[i]);
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
     * it.inera.abi.logic.formatodiscambio.castor.ServiziOrario
     */
    public static it.inera.abi.logic.formatodiscambio.castor.ServiziOrario unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.ServiziOrario) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.ServiziOrario.class, reader);
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
