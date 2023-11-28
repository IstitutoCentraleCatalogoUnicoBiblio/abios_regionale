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
 * $Id: CatalogoCollettivoType.java,v 1.7 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Catalogo collettivo. Deriva da "catalogoSpecialeType",
 *  con l'aggiunta della "zona", a sua volta specializzata
 *  in un "nome" e in un "tipo".
 *  
 * 
 * @version $Revision: 1.7 $ $Date: 2012/11/22 09:50:20 $
 */
@SuppressWarnings("serial")
public abstract class CatalogoCollettivoType extends it.inera.abi.logic.formatodiscambio.castor.CatalogoSpecialeType 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _zona.
     */
    private it.inera.abi.logic.formatodiscambio.castor.Zona _zona;


      //----------------/
     //- Constructors -/
    //----------------/

    public CatalogoCollettivoType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'zona'.
     * 
     * @return the value of field 'Zona'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Zona getZona(
    ) {
        return this._zona;
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
     * Sets the value of field 'zona'.
     * 
     * @param zona the value of field 'zona'.
     */
    public void setZona(
            final it.inera.abi.logic.formatodiscambio.castor.Zona zona) {
        this._zona = zona;
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
