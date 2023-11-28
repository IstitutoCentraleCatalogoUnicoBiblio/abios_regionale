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
 * $Id: Riproduzione.java,v 1.5 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Con questo elemento si dà la possibilità di
 *  esprimere le modalità di riprodurre i vari
 *  materiali ammessi al
 *  prestito locale,
 *  nazionale e/o internazionale.
 *  
 * 
 * @version $Revision: 1.5 $ $Date: 2012/11/22 09:50:20 $
 */
@SuppressWarnings("serial")
public class Riproduzione implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _tipo.
     */
    private java.lang.String _tipo;

    /**
     * Field _locale.
     */
    private it.inera.abi.logic.formatodiscambio.castor.types.SiNoType _locale;

    /**
     * Field _nazionale.
     */
    private it.inera.abi.logic.formatodiscambio.castor.types.SiNoType _nazionale;

    /**
     * Field _internazionale.
     */
    private it.inera.abi.logic.formatodiscambio.castor.types.SiNoType _internazionale;


      //----------------/
     //- Constructors -/
    //----------------/

    public Riproduzione() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'internazionale'.
     * 
     * @return the value of field 'Internazionale'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.types.SiNoType getInternazionale(
    ) {
        return this._internazionale;
    }

    /**
     * Returns the value of field 'locale'.
     * 
     * @return the value of field 'Locale'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.types.SiNoType getLocale(
    ) {
        return this._locale;
    }

    /**
     * Returns the value of field 'nazionale'.
     * 
     * @return the value of field 'Nazionale'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.types.SiNoType getNazionale(
    ) {
        return this._nazionale;
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
     * Sets the value of field 'internazionale'.
     * 
     * @param internazionale the value of field 'internazionale'.
     */
    public void setInternazionale(
            final it.inera.abi.logic.formatodiscambio.castor.types.SiNoType internazionale) {
        this._internazionale = internazionale;
    }

    /**
     * Sets the value of field 'locale'.
     * 
     * @param locale the value of field 'locale'.
     */
    public void setLocale(
            final it.inera.abi.logic.formatodiscambio.castor.types.SiNoType locale) {
        this._locale = locale;
    }

    /**
     * Sets the value of field 'nazionale'.
     * 
     * @param nazionale the value of field 'nazionale'.
     */
    public void setNazionale(
            final it.inera.abi.logic.formatodiscambio.castor.types.SiNoType nazionale) {
        this._nazionale = nazionale;
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
     * Method unmarshal.
     * 
     * @param reader
     * @throws org.exolab.castor.xml.MarshalException if object is
     * null or if any SAXException is thrown during marshaling
     * @throws org.exolab.castor.xml.ValidationException if this
     * object is an invalid instance according to the schema
     * @return the unmarshaled
     * it.inera.abi.logic.formatodiscambio.castor.Riproduzione
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Riproduzione unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Riproduzione) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Riproduzione.class, reader);
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
