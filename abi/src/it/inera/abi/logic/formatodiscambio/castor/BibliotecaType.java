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
 * $Id: BibliotecaType.java,v 1.7 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * L'elemento "biblioteca" è la radice di un documento
 *  di
 *  questo tipo. Tale elemento fa parte di un content model
 *  "xsd:all",
 *  che non obbliga a rispettare l'ordine in cui
 *  si presentano gli
 *  elementi, a differenza di
 *  "xsd:sequence".
 *  
 *  In tutto il resto dello schema si tende ad usare
 *  questo
 *  content model, perché meno rigido. Non dovrebbe
 *  comportare
 *  eccessivo carico per il parser.
 *  
 *  La maggior parte degli elementi e attributi
 *  risultano
 *  opzionali e ripetibili, ma questi aspetti saranno
 *  oggetto di
 *  una revisione futura dell'intero schema alla
 *  luce delle possibilità e
 *  delle richieste dei diversi
 *  partner interessati a scambiare dati nel
 *  formato qui
 *  descritto.
 *  
 *  Nei casi più elementari si è ritenuto opportuno
 *  fissare
 *  l'obbligatorietà e la ripetibilità degli elementi.
 *  
 *  L'elemento biblioteca adesso è obbligatorio.
 *  
 * 
 * @version $Revision: 1.7 $ $Date: 2012/11/22 09:50:20 $
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
     * E' stato introdotto l'elemento ripetibile
     *  'specializzazioni' come contenitore
     *  delle eventuali specializzazioni.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.Specializzazioni _specializzazioni;

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
    }


      //-----------/
     //- Methods -/
    //-----------/

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
     * Returns the value of field 'specializzazioni'. The field
     * 'specializzazioni' has the following description: E' stato
     * introdotto l'elemento ripetibile
     *  'specializzazioni' come contenitore
     *  delle eventuali specializzazioni.
     *  
     * 
     * @return the value of field 'Specializzazioni'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Specializzazioni getSpecializzazioni(
    ) {
        return this._specializzazioni;
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
     * Sets the value of field 'specializzazioni'. The field
     * 'specializzazioni' has the following description: E' stato
     * introdotto l'elemento ripetibile
     *  'specializzazioni' come contenitore
     *  delle eventuali specializzazioni.
     *  
     * 
     * @param specializzazioni the value of field 'specializzazioni'
     */
    public void setSpecializzazioni(
            final it.inera.abi.logic.formatodiscambio.castor.Specializzazioni specializzazioni) {
        this._specializzazioni = specializzazioni;
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
