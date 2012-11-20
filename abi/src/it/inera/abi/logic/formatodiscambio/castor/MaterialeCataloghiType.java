/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: MaterialeCataloghiType.java,v 1.4 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Class MaterialeCataloghiType.
 * 
 * @version $Revision: 1.4 $ $Date: 2012/11/22 09:50:20 $
 */
@SuppressWarnings("serial")
public abstract class MaterialeCataloghiType extends it.inera.abi.logic.formatodiscambio.castor.MaterialeType 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _catSpecForme.
     */
    private it.inera.abi.logic.formatodiscambio.castor.CatSpecForme _catSpecForme;

    /**
     * La copertura bibliografica deve essere espressa
     *  tramite un anno iniziale (elemento "da-anno") e
     *  un anno finale (elemento "ad-anno"). Entrambi
     *  devono avere il formato "YYYY", cioè esattamente
     *  quattro cifre (non è ammesso alcun altro carattere).
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.CatSpecFormeCopertura _catSpecFormeCopertura;


      //----------------/
     //- Constructors -/
    //----------------/

    public MaterialeCataloghiType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'catSpecForme'.
     * 
     * @return the value of field 'CatSpecForme'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.CatSpecForme getCatSpecForme(
    ) {
        return this._catSpecForme;
    }

    /**
     * Returns the value of field 'catSpecFormeCopertura'. The
     * field 'catSpecFormeCopertura' has the following description:
     * La copertura bibliografica deve essere espressa
     *  tramite un anno iniziale (elemento "da-anno") e
     *  un anno finale (elemento "ad-anno"). Entrambi
     *  devono avere il formato "YYYY", cioè esattamente
     *  quattro cifre (non è ammesso alcun altro carattere).
     *  
     * 
     * @return the value of field 'CatSpecFormeCopertura'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.CatSpecFormeCopertura getCatSpecFormeCopertura(
    ) {
        return this._catSpecFormeCopertura;
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
     * Sets the value of field 'catSpecForme'.
     * 
     * @param catSpecForme the value of field 'catSpecForme'.
     */
    public void setCatSpecForme(
            final it.inera.abi.logic.formatodiscambio.castor.CatSpecForme catSpecForme) {
        this._catSpecForme = catSpecForme;
    }

    /**
     * Sets the value of field 'catSpecFormeCopertura'. The field
     * 'catSpecFormeCopertura' has the following description: La
     * copertura bibliografica deve essere espressa
     *  tramite un anno iniziale (elemento "da-anno") e
     *  un anno finale (elemento "ad-anno"). Entrambi
     *  devono avere il formato "YYYY", cioè esattamente
     *  quattro cifre (non è ammesso alcun altro carattere).
     *  
     * 
     * @param catSpecFormeCopertura the value of field
     * 'catSpecFormeCopertura'.
     */
    public void setCatSpecFormeCopertura(
            final it.inera.abi.logic.formatodiscambio.castor.CatSpecFormeCopertura catSpecFormeCopertura) {
        this._catSpecFormeCopertura = catSpecFormeCopertura;
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
