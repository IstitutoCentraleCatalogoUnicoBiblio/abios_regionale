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
 * $Id: CataloghiType.java,v 1.7 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Tipo che raccoglie i diversi cataloghi. Un elemento
 *  di
 *  questo tipo può dunque contenere i tre tipi di cataloghi
 *  di seguito
 *  definiti.
 *  
 *  Tutti i tre sotto-elementi sono opzionali e
 *  ripetibili
 *  in un ordine qualsiasi. Nel definire i tre sottotipi
 *  sono
 *  sfruttate dove possibile le somiglianze fra i tre,
 *  attraverso la
 *  derivazione di un tipo da un altro.
 *  
 * 
 * @version $Revision: 1.7 $ $Date: 2012/11/22 09:50:20 $
 */
@SuppressWarnings("serial")
public abstract class CataloghiType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * E' stato introdotto l'elemento ripetibile
     *  'cataloghi-generali' come contenitore
     *  degli eventuali cataloghi generali.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.CataloghiGenerali _cataloghiGenerali;

    /**
     * E' stato introdotto l'elemento ripetibile
     *  'cataloghi-speciali' come contenitore
     *  degli eventuali cataloghi speciali.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.CataloghiSpeciali _cataloghiSpeciali;

    /**
     * E' stato introdotto l'elemento ripetibile
     *  'cataloghi-collettivi' come contenitore
     *  degli eventuali cataloghi collettivi.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.CataloghiCollettivi _cataloghiCollettivi;


      //----------------/
     //- Constructors -/
    //----------------/

    public CataloghiType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'cataloghiCollettivi'. The field
     * 'cataloghiCollettivi' has the following description: E'
     * stato introdotto l'elemento ripetibile
     *  'cataloghi-collettivi' come contenitore
     *  degli eventuali cataloghi collettivi.
     *  
     * 
     * @return the value of field 'CataloghiCollettivi'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.CataloghiCollettivi getCataloghiCollettivi(
    ) {
        return this._cataloghiCollettivi;
    }

    /**
     * Returns the value of field 'cataloghiGenerali'. The field
     * 'cataloghiGenerali' has the following description: E' stato
     * introdotto l'elemento ripetibile
     *  'cataloghi-generali' come contenitore
     *  degli eventuali cataloghi generali.
     *  
     * 
     * @return the value of field 'CataloghiGenerali'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.CataloghiGenerali getCataloghiGenerali(
    ) {
        return this._cataloghiGenerali;
    }

    /**
     * Returns the value of field 'cataloghiSpeciali'. The field
     * 'cataloghiSpeciali' has the following description: E' stato
     * introdotto l'elemento ripetibile
     *  'cataloghi-speciali' come contenitore
     *  degli eventuali cataloghi speciali.
     *  
     * 
     * @return the value of field 'CataloghiSpeciali'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.CataloghiSpeciali getCataloghiSpeciali(
    ) {
        return this._cataloghiSpeciali;
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
     * Sets the value of field 'cataloghiCollettivi'. The field
     * 'cataloghiCollettivi' has the following description: E'
     * stato introdotto l'elemento ripetibile
     *  'cataloghi-collettivi' come contenitore
     *  degli eventuali cataloghi collettivi.
     *  
     * 
     * @param cataloghiCollettivi the value of field
     * 'cataloghiCollettivi'.
     */
    public void setCataloghiCollettivi(
            final it.inera.abi.logic.formatodiscambio.castor.CataloghiCollettivi cataloghiCollettivi) {
        this._cataloghiCollettivi = cataloghiCollettivi;
    }

    /**
     * Sets the value of field 'cataloghiGenerali'. The field
     * 'cataloghiGenerali' has the following description: E' stato
     * introdotto l'elemento ripetibile
     *  'cataloghi-generali' come contenitore
     *  degli eventuali cataloghi generali.
     *  
     * 
     * @param cataloghiGenerali the value of field
     * 'cataloghiGenerali'.
     */
    public void setCataloghiGenerali(
            final it.inera.abi.logic.formatodiscambio.castor.CataloghiGenerali cataloghiGenerali) {
        this._cataloghiGenerali = cataloghiGenerali;
    }

    /**
     * Sets the value of field 'cataloghiSpeciali'. The field
     * 'cataloghiSpeciali' has the following description: E' stato
     * introdotto l'elemento ripetibile
     *  'cataloghi-speciali' come contenitore
     *  degli eventuali cataloghi speciali.
     *  
     * 
     * @param cataloghiSpeciali the value of field
     * 'cataloghiSpeciali'.
     */
    public void setCataloghiSpeciali(
            final it.inera.abi.logic.formatodiscambio.castor.CataloghiSpeciali cataloghiSpeciali) {
        this._cataloghiSpeciali = cataloghiSpeciali;
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
