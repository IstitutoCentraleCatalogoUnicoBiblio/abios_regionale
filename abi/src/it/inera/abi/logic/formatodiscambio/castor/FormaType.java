/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: FormaType.java,v 1.4 2012/07/31 15:00:07 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * La forma di un catalogo ha di solito un solo
 *  attributo,
 *  la percentuale di copertura, riferita al patrimonio
 *  inventariato o supposto, relativo alla forma
 *  particolare.
 *  Un'eccezione è la forma "digitale", che
 *  aggiunge un elemento (o
 *  attributo che sia) relativo al
 *  tipo di catalogo digitale (CD-ROM, web
 *  o altro).
 *  
 * 
 * @version $Revision: 1.4 $ $Date: 2012/07/31 15:00:07 $
 */
@SuppressWarnings("serial")
public abstract class FormaType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _percentuale.
     */
    private java.lang.String _percentuale;


      //----------------/
     //- Constructors -/
    //----------------/

    public FormaType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'percentuale'.
     * 
     * @return the value of field 'Percentuale'.
     */
    public java.lang.String getPercentuale(
    ) {
        return this._percentuale;
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
     * Sets the value of field 'percentuale'.
     * 
     * @param percentuale the value of field 'percentuale'.
     */
    public void setPercentuale(
            final java.lang.String percentuale) {
        this._percentuale = percentuale;
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
