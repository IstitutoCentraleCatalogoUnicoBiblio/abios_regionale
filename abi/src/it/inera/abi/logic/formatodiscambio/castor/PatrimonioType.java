/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: PatrimonioType.java,v 1.3 2012/07/30 15:17:05 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Tipo utilizzato per descrivere il patrimonio di una
 *  biblioteca. I sotto-elementi sono tutti ripetibili, e
 *  sarebbe opportuno istanziare questo elemento solo se
 *  contiene almeno un sotto-elemento non vuoto, ma si è
 *  preferito comunque non rendere obbligatori i
 *  sotto-elementi.
 *  
 *  L'elemento "materiale" va ripetuto per ciascun tipo di
 *  materiale. Non è prevista una gerarchia fra i materiali,
 *  anche se alcune basi dati, fra cui quella ICCU,
 *  organizzano i materiali in gerarchie a due o più
 *  livelli. Imporre in questo contesto una gerarchia non
 *  gestita in modo uniforme dalle base dati appare
 *  superfluo e limitante.
 *  
 *  Anche l'elemento "fondo-speciale" va ripetuto per
 *  ciascuna istanza di fondo. Inventario e catalogo
 *  topografico sono invece non ripetibili. L'elemento
 *  "fondi-antichi", invece, serve solo a conteggiare in
 *  modo approssimativo i volumi dei diversi fondi antichi
 *  della biblioteca, e quindi non è ripetibile. I tre
 *  valori dell'attributo "volumi" sono da intendersi "fino
 *  a 1000", "da 1000 a 5000", e "oltre 5000".
 *  
 * 
 * @version $Revision: 1.3 $ $Date: 2012/07/30 15:17:05 $
 */
@SuppressWarnings("serial")
public abstract class PatrimonioType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _materialeList.
     */
    private java.util.List<it.inera.abi.logic.formatodiscambio.castor.Materiale> _materialeList;

    /**
     * Field _fondoSpecialeList.
     */
    private java.util.List<it.inera.abi.logic.formatodiscambio.castor.FondoSpeciale> _fondoSpecialeList;

    /**
     * Field _fondiAntichi.
     */
    private it.inera.abi.logic.formatodiscambio.castor.FondiAntichi _fondiAntichi;

    /**
     * L'inventario, opzionale, può essere
     *  informatizzato o cartaceo (oppure, ovviamente,
     *  entrambi).
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.PatrimonioInventario _patrimonioInventario;

    /**
     * Il catalogo è trattato esattamente come
     *  l'inventario.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.CatalogoTopografico _catalogoTopografico;

    /**
     * Totale degli acquisti, indipendentemente dal
     *  tipo di materiale, effettuati negli ultimi
     *  quindici anni.
     *  
     */
    private java.math.BigDecimal _acquistiUltimiQuindiciAnni;

    /**
     * Totale del posseduto, indipendentemente dal tipo
     *  di materiale. Si pone il problema della coerenza
     *  di questo dato con quello del posseduto per i
     *  singoli materiali. Si presume che l'applicativo
     *  ricevente non sia tenuto a verificare questa
     *  coerenza.
     *  
     */
    private long _totalePosseduto;

    /**
     * keeps track of state for field: _totalePosseduto
     */
    private boolean _has_totalePosseduto;

    /**
     * Totale del posseduto destinato ai minori di 16
     *  anni, indipendentemente dal tipo di materiale.
     *  Anche per questo dato, si presume che
     *  l'applicativo ricevente non sia tenuto a
     *  verificarne la coerenza con gli altri dati
     *  patrimoniali ricevuti.
     *  
     */
    private long _totalePossedutoRagazzi;

    /**
     * keeps track of state for field: _totalePossedutoRagazzi
     */
    private boolean _has_totalePossedutoRagazzi;


      //----------------/
     //- Constructors -/
    //----------------/

    public PatrimonioType() {
        super();
        this._materialeList = new java.util.ArrayList<it.inera.abi.logic.formatodiscambio.castor.Materiale>();
        this._fondoSpecialeList = new java.util.ArrayList<it.inera.abi.logic.formatodiscambio.castor.FondoSpeciale>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vFondoSpeciale
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addFondoSpeciale(
            final it.inera.abi.logic.formatodiscambio.castor.FondoSpeciale vFondoSpeciale)
    throws java.lang.IndexOutOfBoundsException {
        this._fondoSpecialeList.add(vFondoSpeciale);
    }

    /**
     * 
     * 
     * @param index
     * @param vFondoSpeciale
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addFondoSpeciale(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.FondoSpeciale vFondoSpeciale)
    throws java.lang.IndexOutOfBoundsException {
        this._fondoSpecialeList.add(index, vFondoSpeciale);
    }

    /**
     * 
     * 
     * @param vMateriale
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addMateriale(
            final it.inera.abi.logic.formatodiscambio.castor.Materiale vMateriale)
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
            final it.inera.abi.logic.formatodiscambio.castor.Materiale vMateriale)
    throws java.lang.IndexOutOfBoundsException {
        this._materialeList.add(index, vMateriale);
    }

    /**
     */
    public void deleteTotalePosseduto(
    ) {
        this._has_totalePosseduto= false;
    }

    /**
     */
    public void deleteTotalePossedutoRagazzi(
    ) {
        this._has_totalePossedutoRagazzi= false;
    }

    /**
     * Method enumerateFondoSpeciale.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends it.inera.abi.logic.formatodiscambio.castor.FondoSpeciale> enumerateFondoSpeciale(
    ) {
        return java.util.Collections.enumeration(this._fondoSpecialeList);
    }

    /**
     * Method enumerateMateriale.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends it.inera.abi.logic.formatodiscambio.castor.Materiale> enumerateMateriale(
    ) {
        return java.util.Collections.enumeration(this._materialeList);
    }

    /**
     * Returns the value of field 'acquistiUltimiQuindiciAnni'. The
     * field 'acquistiUltimiQuindiciAnni' has the following
     * description: Totale degli acquisti, indipendentemente dal
     *  tipo di materiale, effettuati negli ultimi
     *  quindici anni.
     *  
     * 
     * @return the value of field 'AcquistiUltimiQuindiciAnni'.
     */
    public java.math.BigDecimal getAcquistiUltimiQuindiciAnni(
    ) {
        return this._acquistiUltimiQuindiciAnni;
    }

    /**
     * Returns the value of field 'catalogoTopografico'. The field
     * 'catalogoTopografico' has the following description: Il
     * catalogo è trattato esattamente come
     *  l'inventario.
     *  
     * 
     * @return the value of field 'CatalogoTopografico'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.CatalogoTopografico getCatalogoTopografico(
    ) {
        return this._catalogoTopografico;
    }

    /**
     * Returns the value of field 'fondiAntichi'.
     * 
     * @return the value of field 'FondiAntichi'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.FondiAntichi getFondiAntichi(
    ) {
        return this._fondiAntichi;
    }

    /**
     * Method getFondoSpeciale.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * it.inera.abi.logic.formatodiscambio.castor.FondoSpeciale at
     * the given index
     */
    public it.inera.abi.logic.formatodiscambio.castor.FondoSpeciale getFondoSpeciale(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._fondoSpecialeList.size()) {
            throw new IndexOutOfBoundsException("getFondoSpeciale: Index value '" + index + "' not in range [0.." + (this._fondoSpecialeList.size() - 1) + "]");
        }

        return (it.inera.abi.logic.formatodiscambio.castor.FondoSpeciale) _fondoSpecialeList.get(index);
    }

    /**
     * Method getFondoSpeciale.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public it.inera.abi.logic.formatodiscambio.castor.FondoSpeciale[] getFondoSpeciale(
    ) {
        it.inera.abi.logic.formatodiscambio.castor.FondoSpeciale[] array = new it.inera.abi.logic.formatodiscambio.castor.FondoSpeciale[0];
        return (it.inera.abi.logic.formatodiscambio.castor.FondoSpeciale[]) this._fondoSpecialeList.toArray(array);
    }

    /**
     * Method getFondoSpecialeCount.
     * 
     * @return the size of this collection
     */
    public int getFondoSpecialeCount(
    ) {
        return this._fondoSpecialeList.size();
    }

    /**
     * Method getMateriale.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * it.inera.abi.logic.formatodiscambio.castor.Materiale at the
     * given index
     */
    public it.inera.abi.logic.formatodiscambio.castor.Materiale getMateriale(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._materialeList.size()) {
            throw new IndexOutOfBoundsException("getMateriale: Index value '" + index + "' not in range [0.." + (this._materialeList.size() - 1) + "]");
        }

        return (it.inera.abi.logic.formatodiscambio.castor.Materiale) _materialeList.get(index);
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
    public it.inera.abi.logic.formatodiscambio.castor.Materiale[] getMateriale(
    ) {
        it.inera.abi.logic.formatodiscambio.castor.Materiale[] array = new it.inera.abi.logic.formatodiscambio.castor.Materiale[0];
        return (it.inera.abi.logic.formatodiscambio.castor.Materiale[]) this._materialeList.toArray(array);
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
     * Returns the value of field 'patrimonioInventario'. The field
     * 'patrimonioInventario' has the following description:
     * L'inventario, opzionale, può essere
     *  informatizzato o cartaceo (oppure, ovviamente,
     *  entrambi).
     *  
     * 
     * @return the value of field 'PatrimonioInventario'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.PatrimonioInventario getPatrimonioInventario(
    ) {
        return this._patrimonioInventario;
    }

    /**
     * Returns the value of field 'totalePosseduto'. The field
     * 'totalePosseduto' has the following description: Totale del
     * posseduto, indipendentemente dal tipo
     *  di materiale. Si pone il problema della coerenza
     *  di questo dato con quello del posseduto per i
     *  singoli materiali. Si presume che l'applicativo
     *  ricevente non sia tenuto a verificare questa
     *  coerenza.
     *  
     * 
     * @return the value of field 'TotalePosseduto'.
     */
    public long getTotalePosseduto(
    ) {
        return this._totalePosseduto;
    }

    /**
     * Returns the value of field 'totalePossedutoRagazzi'. The
     * field 'totalePossedutoRagazzi' has the following
     * description: Totale del posseduto destinato ai minori di 16
     *  anni, indipendentemente dal tipo di materiale.
     *  Anche per questo dato, si presume che
     *  l'applicativo ricevente non sia tenuto a
     *  verificarne la coerenza con gli altri dati
     *  patrimoniali ricevuti.
     *  
     * 
     * @return the value of field 'TotalePossedutoRagazzi'.
     */
    public long getTotalePossedutoRagazzi(
    ) {
        return this._totalePossedutoRagazzi;
    }

    /**
     * Method hasTotalePosseduto.
     * 
     * @return true if at least one TotalePosseduto has been added
     */
    public boolean hasTotalePosseduto(
    ) {
        return this._has_totalePosseduto;
    }

    /**
     * Method hasTotalePossedutoRagazzi.
     * 
     * @return true if at least one TotalePossedutoRagazzi has been
     * added
     */
    public boolean hasTotalePossedutoRagazzi(
    ) {
        return this._has_totalePossedutoRagazzi;
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
     * Method iterateFondoSpeciale.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends it.inera.abi.logic.formatodiscambio.castor.FondoSpeciale> iterateFondoSpeciale(
    ) {
        return this._fondoSpecialeList.iterator();
    }

    /**
     * Method iterateMateriale.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends it.inera.abi.logic.formatodiscambio.castor.Materiale> iterateMateriale(
    ) {
        return this._materialeList.iterator();
    }

    /**
     */
    public void removeAllFondoSpeciale(
    ) {
        this._fondoSpecialeList.clear();
    }

    /**
     */
    public void removeAllMateriale(
    ) {
        this._materialeList.clear();
    }

    /**
     * Method removeFondoSpeciale.
     * 
     * @param vFondoSpeciale
     * @return true if the object was removed from the collection.
     */
    public boolean removeFondoSpeciale(
            final it.inera.abi.logic.formatodiscambio.castor.FondoSpeciale vFondoSpeciale) {
        boolean removed = _fondoSpecialeList.remove(vFondoSpeciale);
        return removed;
    }

    /**
     * Method removeFondoSpecialeAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public it.inera.abi.logic.formatodiscambio.castor.FondoSpeciale removeFondoSpecialeAt(
            final int index) {
        java.lang.Object obj = this._fondoSpecialeList.remove(index);
        return (it.inera.abi.logic.formatodiscambio.castor.FondoSpeciale) obj;
    }

    /**
     * Method removeMateriale.
     * 
     * @param vMateriale
     * @return true if the object was removed from the collection.
     */
    public boolean removeMateriale(
            final it.inera.abi.logic.formatodiscambio.castor.Materiale vMateriale) {
        boolean removed = _materialeList.remove(vMateriale);
        return removed;
    }

    /**
     * Method removeMaterialeAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public it.inera.abi.logic.formatodiscambio.castor.Materiale removeMaterialeAt(
            final int index) {
        java.lang.Object obj = this._materialeList.remove(index);
        return (it.inera.abi.logic.formatodiscambio.castor.Materiale) obj;
    }

    /**
     * Sets the value of field 'acquistiUltimiQuindiciAnni'. The
     * field 'acquistiUltimiQuindiciAnni' has the following
     * description: Totale degli acquisti, indipendentemente dal
     *  tipo di materiale, effettuati negli ultimi
     *  quindici anni.
     *  
     * 
     * @param acquistiUltimiQuindiciAnni the value of field
     * 'acquistiUltimiQuindiciAnni'.
     */
    public void setAcquistiUltimiQuindiciAnni(
            final java.math.BigDecimal acquistiUltimiQuindiciAnni) {
        this._acquistiUltimiQuindiciAnni = acquistiUltimiQuindiciAnni;
    }

    /**
     * Sets the value of field 'catalogoTopografico'. The field
     * 'catalogoTopografico' has the following description: Il
     * catalogo è trattato esattamente come
     *  l'inventario.
     *  
     * 
     * @param catalogoTopografico the value of field
     * 'catalogoTopografico'.
     */
    public void setCatalogoTopografico(
            final it.inera.abi.logic.formatodiscambio.castor.CatalogoTopografico catalogoTopografico) {
        this._catalogoTopografico = catalogoTopografico;
    }

    /**
     * Sets the value of field 'fondiAntichi'.
     * 
     * @param fondiAntichi the value of field 'fondiAntichi'.
     */
    public void setFondiAntichi(
            final it.inera.abi.logic.formatodiscambio.castor.FondiAntichi fondiAntichi) {
        this._fondiAntichi = fondiAntichi;
    }

    /**
     * 
     * 
     * @param index
     * @param vFondoSpeciale
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setFondoSpeciale(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.FondoSpeciale vFondoSpeciale)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._fondoSpecialeList.size()) {
            throw new IndexOutOfBoundsException("setFondoSpeciale: Index value '" + index + "' not in range [0.." + (this._fondoSpecialeList.size() - 1) + "]");
        }

        this._fondoSpecialeList.set(index, vFondoSpeciale);
    }

    /**
     * 
     * 
     * @param vFondoSpecialeArray
     */
    public void setFondoSpeciale(
            final it.inera.abi.logic.formatodiscambio.castor.FondoSpeciale[] vFondoSpecialeArray) {
        //-- copy array
        _fondoSpecialeList.clear();

        for (int i = 0; i < vFondoSpecialeArray.length; i++) {
                this._fondoSpecialeList.add(vFondoSpecialeArray[i]);
        }
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
            final it.inera.abi.logic.formatodiscambio.castor.Materiale vMateriale)
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
            final it.inera.abi.logic.formatodiscambio.castor.Materiale[] vMaterialeArray) {
        //-- copy array
        _materialeList.clear();

        for (int i = 0; i < vMaterialeArray.length; i++) {
                this._materialeList.add(vMaterialeArray[i]);
        }
    }

    /**
     * Sets the value of field 'patrimonioInventario'. The field
     * 'patrimonioInventario' has the following description:
     * L'inventario, opzionale, può essere
     *  informatizzato o cartaceo (oppure, ovviamente,
     *  entrambi).
     *  
     * 
     * @param patrimonioInventario the value of field
     * 'patrimonioInventario'.
     */
    public void setPatrimonioInventario(
            final it.inera.abi.logic.formatodiscambio.castor.PatrimonioInventario patrimonioInventario) {
        this._patrimonioInventario = patrimonioInventario;
    }

    /**
     * Sets the value of field 'totalePosseduto'. The field
     * 'totalePosseduto' has the following description: Totale del
     * posseduto, indipendentemente dal tipo
     *  di materiale. Si pone il problema della coerenza
     *  di questo dato con quello del posseduto per i
     *  singoli materiali. Si presume che l'applicativo
     *  ricevente non sia tenuto a verificare questa
     *  coerenza.
     *  
     * 
     * @param totalePosseduto the value of field 'totalePosseduto'.
     */
    public void setTotalePosseduto(
            final long totalePosseduto) {
        this._totalePosseduto = totalePosseduto;
        this._has_totalePosseduto = true;
    }

    /**
     * Sets the value of field 'totalePossedutoRagazzi'. The field
     * 'totalePossedutoRagazzi' has the following description:
     * Totale del posseduto destinato ai minori di 16
     *  anni, indipendentemente dal tipo di materiale.
     *  Anche per questo dato, si presume che
     *  l'applicativo ricevente non sia tenuto a
     *  verificarne la coerenza con gli altri dati
     *  patrimoniali ricevuti.
     *  
     * 
     * @param totalePossedutoRagazzi the value of field
     * 'totalePossedutoRagazzi'.
     */
    public void setTotalePossedutoRagazzi(
            final long totalePossedutoRagazzi) {
        this._totalePossedutoRagazzi = totalePossedutoRagazzi;
        this._has_totalePossedutoRagazzi = true;
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
