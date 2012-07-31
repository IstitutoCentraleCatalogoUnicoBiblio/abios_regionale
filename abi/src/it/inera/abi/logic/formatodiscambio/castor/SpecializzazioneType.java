/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: SpecializzazioneType.java,v 1.4 2012/07/31 15:00:07 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * La specializzazione di una biblioteca è
 *  semplicemente
 *  una CDD più una descrizione libera opzionale.
 *  
 * 
 * @version $Revision: 1.4 $ $Date: 2012/07/31 15:00:07 $
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
