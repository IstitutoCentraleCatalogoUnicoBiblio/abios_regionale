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
 * $Id: SpecializzazioneType.java,v 1.7 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * La specializzazione di una biblioteca è semplicemente
 *  una CDD più una descrizione libera opzionale.
 *  
 * 
 * @version $Revision: 1.7 $ $Date: 2012/11/22 09:50:20 $
 */
@SuppressWarnings("serial")
public abstract class SpecializzazioneType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _specializzazioneCdd.
     */
    private java.lang.String _specializzazioneCdd;

    /**
     * Field _descrizioneLibera.
     */
    private java.lang.String _descrizioneLibera;


      //----------------/
     //- Constructors -/
    //----------------/

    public SpecializzazioneType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'descrizioneLibera'.
     * 
     * @return the value of field 'DescrizioneLibera'.
     */
    public java.lang.String getDescrizioneLibera(
    ) {
        return this._descrizioneLibera;
    }

    /**
     * Returns the value of field 'specializzazioneCdd'.
     * 
     * @return the value of field 'SpecializzazioneCdd'.
     */
    public java.lang.String getSpecializzazioneCdd(
    ) {
        return this._specializzazioneCdd;
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
     * Sets the value of field 'descrizioneLibera'.
     * 
     * @param descrizioneLibera the value of field
     * 'descrizioneLibera'.
     */
    public void setDescrizioneLibera(
            final java.lang.String descrizioneLibera) {
        this._descrizioneLibera = descrizioneLibera;
    }

    /**
     * Sets the value of field 'specializzazioneCdd'.
     * 
     * @param specializzazioneCdd the value of field
     * 'specializzazioneCdd'.
     */
    public void setSpecializzazioneCdd(
            final java.lang.String specializzazioneCdd) {
        this._specializzazioneCdd = specializzazioneCdd;
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
