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
 * $Id: InfCarType.java,v 1.7 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Un elemento di questo tipo serve solo a specificare una
 *  forma cartacea e una forma informatizzata di qualcosa,
 *  tipicamente un catalogo, ma non solo. Entrambi gli
 *  elementi che definisce sono semplici siNoType.
 *  
 * 
 * @version $Revision: 1.7 $ $Date: 2012/11/22 09:50:20 $
 */
@SuppressWarnings("serial")
public abstract class InfCarType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _informatizzato.
     */
    private it.inera.abi.logic.formatodiscambio.castor.types.SiNoType _informatizzato;

    /**
     * Field _cartaceo.
     */
    private it.inera.abi.logic.formatodiscambio.castor.types.SiNoType _cartaceo;


      //----------------/
     //- Constructors -/
    //----------------/

    public InfCarType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'cartaceo'.
     * 
     * @return the value of field 'Cartaceo'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.types.SiNoType getCartaceo(
    ) {
        return this._cartaceo;
    }

    /**
     * Returns the value of field 'informatizzato'.
     * 
     * @return the value of field 'Informatizzato'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.types.SiNoType getInformatizzato(
    ) {
        return this._informatizzato;
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
     * Sets the value of field 'cartaceo'.
     * 
     * @param cartaceo the value of field 'cartaceo'.
     */
    public void setCartaceo(
            final it.inera.abi.logic.formatodiscambio.castor.types.SiNoType cartaceo) {
        this._cartaceo = cartaceo;
    }

    /**
     * Sets the value of field 'informatizzato'.
     * 
     * @param informatizzato the value of field 'informatizzato'.
     */
    public void setInformatizzato(
            final it.inera.abi.logic.formatodiscambio.castor.types.SiNoType informatizzato) {
        this._informatizzato = informatizzato;
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
