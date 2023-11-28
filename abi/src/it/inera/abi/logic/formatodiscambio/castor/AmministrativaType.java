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
 * $Id: AmministrativaType.java,v 1.7 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Tipo di elemento recante informazioni di carattere
 *  amministrativo. È la parte che più probabilmente
 *  richiederà
 *  aggiustamenti per soddisfare le esigenze di
 *  vari enti. Molti elementi
 *  sono auto-esplicativi.
 *  
 * 
 * @version $Revision: 1.7 $ $Date: 2012/11/22 09:50:20 $
 */
@SuppressWarnings("serial")
public abstract class AmministrativaType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _codiceFiscale.
     */
    private java.lang.String _codiceFiscale;

    /**
     * Field _partitaIVA.
     */
    private java.lang.String _partitaIVA;

    /**
     * Eventuale autonomia di spesa.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.types.SiNoType _autonoma;

    /**
     * Dati relativi all'ente da cui la biblioteca
     *  dipende. Almeno il nome è obbligatorio. Inoltre,
     *  andrebbe discussa
     *  l'opportunità di fare
     *  riferimento ad una lista controllata di enti,
     *  individuati tramite il solo codice fiscale o la
     *  partita IVA. Devono
     *  esistere certamente simili
     *  liste, e le diverse basi dati dovrebbero
     *  attenersi ad esse, almeno in parte.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.Ente _ente;

    /**
     * Field _regolamento.
     */
    private it.inera.abi.logic.formatodiscambio.castor.Regolamento _regolamento;

    /**
     * Questo elemento opzionale indica se la
     *  biblioteca
     *  si è dotata di una carta dei servizi
     *  o meno, e per quali servizi
     *  specifici. Gli
     *  eventuali singoli servizi devono essere elencati
     *  in
     *  altrettanti elementi "servizio", sotto forma
     *  di stringhe
     *  alfanumeriche. Se l'elemento è
     *  vuoto, vuol dire solo che esiste una
     *  carta dei
     *  servizi, ma mancano altre informazioni. I valori
     *  andrebbero standardizzati attraverso
     *  un'opportuna lista d'autorità
     *  concordata.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.CartaServizi _cartaServizi;

    /**
     * Field _depositiLegali.
     */
    private it.inera.abi.logic.formatodiscambio.castor.DepositiLegali _depositiLegali;

    /**
     * Questo elemento e i suoi sotto-elementi
     *  descrivono sommariamente le strutture di cui la
     *  biblioteca dispone.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.Strutture _strutture;

    /**
     * Informazioni relative agli utenti della biblioteca.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.Utenti _utenti;

    /**
     * Informazioni relative al personale impiegato a vario titolo
     * nella biblioteca.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.Personale _personale;

    /**
     * Informazioni relative al bilancio della biblioteca.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.Bilancio _bilancio;


      //----------------/
     //- Constructors -/
    //----------------/

    public AmministrativaType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'autonoma'. The field 'autonoma'
     * has the following description: Eventuale autonomia di spesa.
     *  
     * 
     * @return the value of field 'Autonoma'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.types.SiNoType getAutonoma(
    ) {
        return this._autonoma;
    }

    /**
     * Returns the value of field 'bilancio'. The field 'bilancio'
     * has the following description: Informazioni relative al
     * bilancio della biblioteca.
     *  
     * 
     * @return the value of field 'Bilancio'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Bilancio getBilancio(
    ) {
        return this._bilancio;
    }

    /**
     * Returns the value of field 'cartaServizi'. The field
     * 'cartaServizi' has the following description: Questo
     * elemento opzionale indica se la
     *  biblioteca
     *  si è dotata di una carta dei servizi
     *  o meno, e per quali servizi
     *  specifici. Gli
     *  eventuali singoli servizi devono essere elencati
     *  in
     *  altrettanti elementi "servizio", sotto forma
     *  di stringhe
     *  alfanumeriche. Se l'elemento è
     *  vuoto, vuol dire solo che esiste una
     *  carta dei
     *  servizi, ma mancano altre informazioni. I valori
     *  andrebbero standardizzati attraverso
     *  un'opportuna lista d'autorità
     *  concordata.
     *  
     * 
     * @return the value of field 'CartaServizi'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.CartaServizi getCartaServizi(
    ) {
        return this._cartaServizi;
    }

    /**
     * Returns the value of field 'codiceFiscale'.
     * 
     * @return the value of field 'CodiceFiscale'.
     */
    public java.lang.String getCodiceFiscale(
    ) {
        return this._codiceFiscale;
    }

    /**
     * Returns the value of field 'depositiLegali'.
     * 
     * @return the value of field 'DepositiLegali'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.DepositiLegali getDepositiLegali(
    ) {
        return this._depositiLegali;
    }

    /**
     * Returns the value of field 'ente'. The field 'ente' has the
     * following description: Dati relativi all'ente da cui la
     * biblioteca
     *  dipende. Almeno il nome è obbligatorio. Inoltre,
     *  andrebbe discussa
     *  l'opportunità di fare
     *  riferimento ad una lista controllata di enti,
     *  individuati tramite il solo codice fiscale o la
     *  partita IVA. Devono
     *  esistere certamente simili
     *  liste, e le diverse basi dati dovrebbero
     *  attenersi ad esse, almeno in parte.
     *  
     * 
     * @return the value of field 'Ente'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Ente getEnte(
    ) {
        return this._ente;
    }

    /**
     * Returns the value of field 'partitaIVA'.
     * 
     * @return the value of field 'PartitaIVA'.
     */
    public java.lang.String getPartitaIVA(
    ) {
        return this._partitaIVA;
    }

    /**
     * Returns the value of field 'personale'. The field
     * 'personale' has the following description: Informazioni
     * relative al personale impiegato a vario titolo nella
     * biblioteca.
     *  
     * 
     * @return the value of field 'Personale'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Personale getPersonale(
    ) {
        return this._personale;
    }

    /**
     * Returns the value of field 'regolamento'.
     * 
     * @return the value of field 'Regolamento'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Regolamento getRegolamento(
    ) {
        return this._regolamento;
    }

    /**
     * Returns the value of field 'strutture'. The field
     * 'strutture' has the following description: Questo elemento e
     * i suoi sotto-elementi
     *  descrivono sommariamente le strutture di cui la
     *  biblioteca dispone.
     *  
     * 
     * @return the value of field 'Strutture'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Strutture getStrutture(
    ) {
        return this._strutture;
    }

    /**
     * Returns the value of field 'utenti'. The field 'utenti' has
     * the following description: Informazioni relative agli utenti
     * della biblioteca.
     *  
     * 
     * @return the value of field 'Utenti'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Utenti getUtenti(
    ) {
        return this._utenti;
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
     * Sets the value of field 'autonoma'. The field 'autonoma' has
     * the following description: Eventuale autonomia di spesa.
     *  
     * 
     * @param autonoma the value of field 'autonoma'.
     */
    public void setAutonoma(
            final it.inera.abi.logic.formatodiscambio.castor.types.SiNoType autonoma) {
        this._autonoma = autonoma;
    }

    /**
     * Sets the value of field 'bilancio'. The field 'bilancio' has
     * the following description: Informazioni relative al bilancio
     * della biblioteca.
     *  
     * 
     * @param bilancio the value of field 'bilancio'.
     */
    public void setBilancio(
            final it.inera.abi.logic.formatodiscambio.castor.Bilancio bilancio) {
        this._bilancio = bilancio;
    }

    /**
     * Sets the value of field 'cartaServizi'. The field
     * 'cartaServizi' has the following description: Questo
     * elemento opzionale indica se la
     *  biblioteca
     *  si è dotata di una carta dei servizi
     *  o meno, e per quali servizi
     *  specifici. Gli
     *  eventuali singoli servizi devono essere elencati
     *  in
     *  altrettanti elementi "servizio", sotto forma
     *  di stringhe
     *  alfanumeriche. Se l'elemento è
     *  vuoto, vuol dire solo che esiste una
     *  carta dei
     *  servizi, ma mancano altre informazioni. I valori
     *  andrebbero standardizzati attraverso
     *  un'opportuna lista d'autorità
     *  concordata.
     *  
     * 
     * @param cartaServizi the value of field 'cartaServizi'.
     */
    public void setCartaServizi(
            final it.inera.abi.logic.formatodiscambio.castor.CartaServizi cartaServizi) {
        this._cartaServizi = cartaServizi;
    }

    /**
     * Sets the value of field 'codiceFiscale'.
     * 
     * @param codiceFiscale the value of field 'codiceFiscale'.
     */
    public void setCodiceFiscale(
            final java.lang.String codiceFiscale) {
        this._codiceFiscale = codiceFiscale;
    }

    /**
     * Sets the value of field 'depositiLegali'.
     * 
     * @param depositiLegali the value of field 'depositiLegali'.
     */
    public void setDepositiLegali(
            final it.inera.abi.logic.formatodiscambio.castor.DepositiLegali depositiLegali) {
        this._depositiLegali = depositiLegali;
    }

    /**
     * Sets the value of field 'ente'. The field 'ente' has the
     * following description: Dati relativi all'ente da cui la
     * biblioteca
     *  dipende. Almeno il nome è obbligatorio. Inoltre,
     *  andrebbe discussa
     *  l'opportunità di fare
     *  riferimento ad una lista controllata di enti,
     *  individuati tramite il solo codice fiscale o la
     *  partita IVA. Devono
     *  esistere certamente simili
     *  liste, e le diverse basi dati dovrebbero
     *  attenersi ad esse, almeno in parte.
     *  
     * 
     * @param ente the value of field 'ente'.
     */
    public void setEnte(
            final it.inera.abi.logic.formatodiscambio.castor.Ente ente) {
        this._ente = ente;
    }

    /**
     * Sets the value of field 'partitaIVA'.
     * 
     * @param partitaIVA the value of field 'partitaIVA'.
     */
    public void setPartitaIVA(
            final java.lang.String partitaIVA) {
        this._partitaIVA = partitaIVA;
    }

    /**
     * Sets the value of field 'personale'. The field 'personale'
     * has the following description: Informazioni relative al
     * personale impiegato a vario titolo nella biblioteca.
     *  
     * 
     * @param personale the value of field 'personale'.
     */
    public void setPersonale(
            final it.inera.abi.logic.formatodiscambio.castor.Personale personale) {
        this._personale = personale;
    }

    /**
     * Sets the value of field 'regolamento'.
     * 
     * @param regolamento the value of field 'regolamento'.
     */
    public void setRegolamento(
            final it.inera.abi.logic.formatodiscambio.castor.Regolamento regolamento) {
        this._regolamento = regolamento;
    }

    /**
     * Sets the value of field 'strutture'. The field 'strutture'
     * has the following description: Questo elemento e i suoi
     * sotto-elementi
     *  descrivono sommariamente le strutture di cui la
     *  biblioteca dispone.
     *  
     * 
     * @param strutture the value of field 'strutture'.
     */
    public void setStrutture(
            final it.inera.abi.logic.formatodiscambio.castor.Strutture strutture) {
        this._strutture = strutture;
    }

    /**
     * Sets the value of field 'utenti'. The field 'utenti' has the
     * following description: Informazioni relative agli utenti
     * della biblioteca.
     *  
     * 
     * @param utenti the value of field 'utenti'.
     */
    public void setUtenti(
            final it.inera.abi.logic.formatodiscambio.castor.Utenti utenti) {
        this._utenti = utenti;
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
