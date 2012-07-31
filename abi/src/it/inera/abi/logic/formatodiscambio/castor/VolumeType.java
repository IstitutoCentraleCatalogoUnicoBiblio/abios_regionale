/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: VolumeType.java,v 1.4 2012/07/31 15:00:08 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * La forma "volumeType" deriva dalla generica
 *  "formaType"
 *  con l'aggiunta della citazione bibliografica, specifica
 *  di questa forma. La citazione è comunque opzionale.
 *  
 * 
 * @version $Revision: 1.4 $ $Date: 2012/07/31 15:00:08 $
 */
@SuppressWarnings("serial")
public abstract class VolumeType extends it.inera.abi.logic.formatodiscambio.castor.FormaType 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _citazioneBibliografica.
     */
    private java.lang.String _citazioneBibliografica;


      //----------------/
     //- Constructors -/
    //----------------/

    public VolumeType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'citazioneBibliografica'.
     * 
     * @return the value of field 'CitazioneBibliografica'.
     */
    public java.lang.String getCitazioneBibliografica(
    ) {
        return this._citazioneBibliografica;
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
     * Sets the value of field 'citazioneBibliografica'.
     * 
     * @param citazioneBibliografica the value of field
     * 'citazioneBibliografica'.
     */
    public void setCitazioneBibliografica(
            final java.lang.String citazioneBibliografica) {
        this._citazioneBibliografica = citazioneBibliografica;
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
