/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: CatalogoCollettivoType.java,v 1.4 2012/07/31 15:00:07 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Catalogo collettivo. Deriva da
 *  "catalogoSpecialeType",
 *  con l'aggiunta della "zona", a sua volta
 *  specializzata
 *  in un "nome" e in un "tipo".
 *  
 * 
 * @version $Revision: 1.4 $ $Date: 2012/07/31 15:00:07 $
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
