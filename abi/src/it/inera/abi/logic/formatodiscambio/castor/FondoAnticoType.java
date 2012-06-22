/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: FondoAnticoType.java,v 1.1 2012/06/22 13:55:04 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * I fondi antichi vanno classificati in base al posseduto:
 *  fino a 1000, da 1000 a 5000, oltre 5000. Non è quindi
 *  possibile specificare esattamente un posseduto.
 *  
 * 
 * @version $Revision: 1.1 $ $Date: 2012/06/22 13:55:04 $
 */
@SuppressWarnings("serial")
public abstract class FondoAnticoType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _volumi.
     */
    private it.inera.abi.logic.formatodiscambio.castor.types.FondoAnticoTypeVolumiType _volumi;


      //----------------/
     //- Constructors -/
    //----------------/

    public FondoAnticoType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'volumi'.
     * 
     * @return the value of field 'Volumi'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.types.FondoAnticoTypeVolumiType getVolumi(
    ) {
        return this._volumi;
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
     * Sets the value of field 'volumi'.
     * 
     * @param volumi the value of field 'volumi'.
     */
    public void setVolumi(
            final it.inera.abi.logic.formatodiscambio.castor.types.FondoAnticoTypeVolumiType volumi) {
        this._volumi = volumi;
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
