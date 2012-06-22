/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: CatSpecForme.java,v 1.1 2012/06/22 13:55:04 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Class CatSpecForme.
 * 
 * @version $Revision: 1.1 $ $Date: 2012/06/22 13:55:04 $
 */
@SuppressWarnings("serial")
public class CatSpecForme implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _catSpecFormeSchede.
     */
    private it.inera.abi.logic.formatodiscambio.castor.CatSpecFormeSchede _catSpecFormeSchede;

    /**
     * Field _catSpecFormeVolume.
     */
    private it.inera.abi.logic.formatodiscambio.castor.CatSpecFormeVolume _catSpecFormeVolume;

    /**
     * Field _catSpecFormeMicroforme.
     */
    private it.inera.abi.logic.formatodiscambio.castor.CatSpecFormeMicroforme _catSpecFormeMicroforme;

    /**
     * Field _catSpecFormeDigitale.
     */
    private it.inera.abi.logic.formatodiscambio.castor.CatSpecFormeDigitale _catSpecFormeDigitale;


      //----------------/
     //- Constructors -/
    //----------------/

    public CatSpecForme() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'catSpecFormeDigitale'.
     * 
     * @return the value of field 'CatSpecFormeDigitale'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.CatSpecFormeDigitale getCatSpecFormeDigitale(
    ) {
        return this._catSpecFormeDigitale;
    }

    /**
     * Returns the value of field 'catSpecFormeMicroforme'.
     * 
     * @return the value of field 'CatSpecFormeMicroforme'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.CatSpecFormeMicroforme getCatSpecFormeMicroforme(
    ) {
        return this._catSpecFormeMicroforme;
    }

    /**
     * Returns the value of field 'catSpecFormeSchede'.
     * 
     * @return the value of field 'CatSpecFormeSchede'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.CatSpecFormeSchede getCatSpecFormeSchede(
    ) {
        return this._catSpecFormeSchede;
    }

    /**
     * Returns the value of field 'catSpecFormeVolume'.
     * 
     * @return the value of field 'CatSpecFormeVolume'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.CatSpecFormeVolume getCatSpecFormeVolume(
    ) {
        return this._catSpecFormeVolume;
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
     * 
     * 
     * @param out
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     */
    public void marshal(
            final java.io.Writer out)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Marshaller.marshal(this, out);
    }

    /**
     * 
     * 
     * @param handler
     * @throws java.io.IOException if an IOException occurs during
     * marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     */
    public void marshal(
            final org.xml.sax.ContentHandler handler)
    throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        org.exolab.castor.xml.Marshaller.marshal(this, handler);
    }

    /**
     * Sets the value of field 'catSpecFormeDigitale'.
     * 
     * @param catSpecFormeDigitale the value of field
     * 'catSpecFormeDigitale'.
     */
    public void setCatSpecFormeDigitale(
            final it.inera.abi.logic.formatodiscambio.castor.CatSpecFormeDigitale catSpecFormeDigitale) {
        this._catSpecFormeDigitale = catSpecFormeDigitale;
    }

    /**
     * Sets the value of field 'catSpecFormeMicroforme'.
     * 
     * @param catSpecFormeMicroforme the value of field
     * 'catSpecFormeMicroforme'.
     */
    public void setCatSpecFormeMicroforme(
            final it.inera.abi.logic.formatodiscambio.castor.CatSpecFormeMicroforme catSpecFormeMicroforme) {
        this._catSpecFormeMicroforme = catSpecFormeMicroforme;
    }

    /**
     * Sets the value of field 'catSpecFormeSchede'.
     * 
     * @param catSpecFormeSchede the value of field
     * 'catSpecFormeSchede'.
     */
    public void setCatSpecFormeSchede(
            final it.inera.abi.logic.formatodiscambio.castor.CatSpecFormeSchede catSpecFormeSchede) {
        this._catSpecFormeSchede = catSpecFormeSchede;
    }

    /**
     * Sets the value of field 'catSpecFormeVolume'.
     * 
     * @param catSpecFormeVolume the value of field
     * 'catSpecFormeVolume'.
     */
    public void setCatSpecFormeVolume(
            final it.inera.abi.logic.formatodiscambio.castor.CatSpecFormeVolume catSpecFormeVolume) {
        this._catSpecFormeVolume = catSpecFormeVolume;
    }

    /**
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled
     * it.inera.abi.logic.formatodiscambio.castor.CatSpecForme
     */
    public static it.inera.abi.logic.formatodiscambio.castor.CatSpecForme unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.CatSpecForme) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.CatSpecForme.class, reader);
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
