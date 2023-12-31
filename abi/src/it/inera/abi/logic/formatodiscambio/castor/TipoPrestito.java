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
 * $Id: TipoPrestito.java,v 1.7 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Class TipoPrestito.
 * 
 * @version $Revision: 1.7 $ $Date: 2012/11/22 09:50:20 $
 */
@SuppressWarnings("serial")
public class TipoPrestito implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _internazionale.
     */
    private it.inera.abi.logic.formatodiscambio.castor.types.SiNoType _internazionale;

    /**
     * Field _nazionale.
     */
    private it.inera.abi.logic.formatodiscambio.castor.types.SiNoType _nazionale;

    /**
     * Field _ruolo.
     */
    private it.inera.abi.logic.formatodiscambio.castor.types.RuoloType _ruolo;


      //----------------/
     //- Constructors -/
    //----------------/

    public TipoPrestito() {
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
     * Returns the value of field 'nazionale'.
     * 
     * @return the value of field 'Nazionale'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.types.SiNoType getNazionale(
    ) {
        return this._nazionale;
    }

    /**
     * Returns the value of field 'ruolo'.
     * 
     * @return the value of field 'Ruolo'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.types.RuoloType getRuolo(
    ) {
        return this._ruolo;
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
     * Sets the value of field 'nazionale'.
     * 
     * @param nazionale the value of field 'nazionale'.
     */
    public void setNazionale(
            final it.inera.abi.logic.formatodiscambio.castor.types.SiNoType nazionale) {
        this._nazionale = nazionale;
    }

    /**
     * Sets the value of field 'ruolo'.
     * 
     * @param ruolo the value of field 'ruolo'.
     */
    public void setRuolo(
            final it.inera.abi.logic.formatodiscambio.castor.types.RuoloType ruolo) {
        this._ruolo = ruolo;
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
     * it.inera.abi.logic.formatodiscambio.castor.TipoPrestito
     */
    public static it.inera.abi.logic.formatodiscambio.castor.TipoPrestito unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.TipoPrestito) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.TipoPrestito.class, reader);
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
