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
 * $Id: CatalogoType.java,v 1.7 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * I cataloghi hanno diverse caratteristiche comuni.
 *  Vale la pena di definire un tipo che raccoglie queste
 *  caratteristiche, per poi derivare da esso, tramite
 *  restriction, i diversi tipi di cataloghi.
 *  
 *  Tutti contengono le quattro forme "schede",
 *  "volume", "microfilm" e "digitale", ciascuna con la sua
 *  percentuale di copertura (vedi "formaType"), e tutti
 *  hanno una copertura temporale.
 *  La forma "digitale" ha un tipo particolare, "digitaleType", che
 *  aggiunge a "formaType" il solo sotto-elemento "supporto".
 *  
 * 
 * @version $Revision: 1.7 $ $Date: 2012/11/22 09:50:20 $
 */
@SuppressWarnings("serial")
public abstract class CatalogoType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _tipo.
     */
    private java.lang.String _tipo;

    /**
     * Field _forme.
     */
    private it.inera.abi.logic.formatodiscambio.castor.Forme _forme;

    /**
     * La copertura bibliografica deve essere espressa
     *  tramite un anno iniziale (elemento "da-anno") e
     *  un anno finale (elemento "ad-anno"). Entrambi
     *  devono avere il formato "YYYY", cioè esattamente
     *  quattro cifre (non è ammesso alcun altro carattere).
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.Copertura _copertura;


      //----------------/
     //- Constructors -/
    //----------------/

    public CatalogoType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'copertura'. The field
     * 'copertura' has the following description: La copertura
     * bibliografica deve essere espressa
     *  tramite un anno iniziale (elemento "da-anno") e
     *  un anno finale (elemento "ad-anno"). Entrambi
     *  devono avere il formato "YYYY", cioè esattamente
     *  quattro cifre (non è ammesso alcun altro carattere).
     *  
     * 
     * @return the value of field 'Copertura'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Copertura getCopertura(
    ) {
        return this._copertura;
    }

    /**
     * Returns the value of field 'forme'.
     * 
     * @return the value of field 'Forme'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.Forme getForme(
    ) {
        return this._forme;
    }

    /**
     * Returns the value of field 'tipo'.
     * 
     * @return the value of field 'Tipo'.
     */
    public java.lang.String getTipo(
    ) {
        return this._tipo;
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
     * Sets the value of field 'copertura'. The field 'copertura'
     * has the following description: La copertura bibliografica
     * deve essere espressa
     *  tramite un anno iniziale (elemento "da-anno") e
     *  un anno finale (elemento "ad-anno"). Entrambi
     *  devono avere il formato "YYYY", cioè esattamente
     *  quattro cifre (non è ammesso alcun altro carattere).
     *  
     * 
     * @param copertura the value of field 'copertura'.
     */
    public void setCopertura(
            final it.inera.abi.logic.formatodiscambio.castor.Copertura copertura) {
        this._copertura = copertura;
    }

    /**
     * Sets the value of field 'forme'.
     * 
     * @param forme the value of field 'forme'.
     */
    public void setForme(
            final it.inera.abi.logic.formatodiscambio.castor.Forme forme) {
        this._forme = forme;
    }

    /**
     * Sets the value of field 'tipo'.
     * 
     * @param tipo the value of field 'tipo'.
     */
    public void setTipo(
            final java.lang.String tipo) {
        this._tipo = tipo;
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
