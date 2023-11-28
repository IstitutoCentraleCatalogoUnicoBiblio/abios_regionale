/*
 * Author Inera srl https://www.inera.it
 * Copyright (C) 2023  Inera srl https://www.inera.it/
 *
 * European Union Public Licence V. 1.2
 * EUPL (c) the European Community 2017
 *
 * This European Union Public Licence (the "EUPL") applies to the Work or Software (as defined below) which is provided under the terms of this Licence.
 * Any use of the Work, other than as authorised under this Licence is prohibited (to the extent such use is covered by a right of the copyright holder of the Work).
 * The Original Work is provided under the terms of this Licence when the Licensor (as defined below) has placed the following notice immediately following the copyright notice for the Original Work:
 * Licensed under the EUPL V.1.2 or has expressed by any other mean his willingness to license under the EUPL.
 *
 * You should have received a copy of the European Union Public Licence V. 1.2 along with this program.  If not, see https://eupl.eu/1.2/en/
 */

/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: PatrimonioType.java,v 1.7 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Tipo utilizzato per descrivere il patrimonio di una
 *  biblioteca. I sotto-elementi sono tutti ripetibili, e
 *  sarebbe
 *  opportuno istanziare questo elemento solo se
 *  contiene almeno un
 *  sotto-elemento non vuoto, ma si è
 *  preferito comunque non rendere
 *  obbligatori i
 *  sotto-elementi.
 *  
 *  L'elemento "materiale" va ripetuto per ciascun tipo
 *  di
 *  materiale. Non è prevista una gerarchia fra i materiali,
 *  anche se
 *  alcune basi dati, fra cui quella ICCU,
 *  organizzano i materiali in
 *  gerarchie a due o più
 *  livelli. Imporre in questo contesto una
 *  gerarchia non
 *  gestita in modo uniforme dalle base dati appare
 *  superfluo e limitante.
 *  
 *  Anche l'elemento "fondo-speciale" va ripetuto per
 *  ciascuna istanza di fondo. Inventario e catalogo
 *  topografico sono
 *  invece non ripetibili. L'elemento
 *  "fondi-antichi", invece, serve solo
 *  a conteggiare in
 *  modo approssimativo i volumi dei diversi fondi
 *  antichi
 *  della biblioteca, e quindi non è ripetibile. I tre
 *  valori
 *  dell'attributo "volumi" sono da intendersi "fino
 *  a 1000", "da 1000 a
 *  5000", e "oltre 5000".
 *  
 * 
 * @version $Revision: 1.7 $ $Date: 2012/11/22 09:50:20 $
 */
@SuppressWarnings("serial")
public abstract class PatrimonioType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _materiali.
     */
    private it.inera.abi.logic.formatodiscambio.castor.Materiali _materiali;

    /**
     * Field _fondiSpeciali.
     */
    private it.inera.abi.logic.formatodiscambio.castor.FondiSpeciali _fondiSpeciali;

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
     *  tipo
     *  di materiale, effettuati negli ultimi
     *  quindici anni.
     *  
     */
    private java.math.BigDecimal _acquistiUltimiQuindiciAnni;

    /**
     * Totale del posseduto, indipendentemente dal tipo
     *  di materiale. Si pone il problema della coerenza
     *  di questo dato con
     *  quello del posseduto per i
     *  singoli materiali. Si presume che
     *  l'applicativo
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
     *  Anche per questo
     *  dato, si presume che
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
    }


      //-----------/
     //- Methods -/
    //-----------/

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
     * Returns the value of field 'acquistiUltimiQuindiciAnni'. The
     * field 'acquistiUltimiQuindiciAnni' has the following
     * description: Totale degli acquisti, indipendentemente dal
     *  tipo
     *  di materiale, effettuati negli ultimi
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
     * Returns the value of field 'fondiSpeciali'.
     * 
     * @return the value of field 'FondiSpeciali'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.FondiSpeciali getFondiSpeciali(
    ) {
        return this._fondiSpeciali;
    }

    /**
     * Returns the value of field 'materiali'.
     * 
     * @return the value of field 'Materiali'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Materiali getMateriali(
    ) {
        return this._materiali;
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
     *  di questo dato con
     *  quello del posseduto per i
     *  singoli materiali. Si presume che
     *  l'applicativo
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
     *  Anche per questo
     *  dato, si presume che
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
     * Sets the value of field 'acquistiUltimiQuindiciAnni'. The
     * field 'acquistiUltimiQuindiciAnni' has the following
     * description: Totale degli acquisti, indipendentemente dal
     *  tipo
     *  di materiale, effettuati negli ultimi
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
     * Sets the value of field 'fondiSpeciali'.
     * 
     * @param fondiSpeciali the value of field 'fondiSpeciali'.
     */
    public void setFondiSpeciali(
            final it.inera.abi.logic.formatodiscambio.castor.FondiSpeciali fondiSpeciali) {
        this._fondiSpeciali = fondiSpeciali;
    }

    /**
     * Sets the value of field 'materiali'.
     * 
     * @param materiali the value of field 'materiali'.
     */
    public void setMateriali(
            final it.inera.abi.logic.formatodiscambio.castor.Materiali materiali) {
        this._materiali = materiali;
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
     *  di questo dato con
     *  quello del posseduto per i
     *  singoli materiali. Si presume che
     *  l'applicativo
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
     *  Anche per questo
     *  dato, si presume che
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
