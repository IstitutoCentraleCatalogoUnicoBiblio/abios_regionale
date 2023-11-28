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
 * $Id: Istituzione.java,v 1.7 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Data in cui è stata istituita formalmente
 *  l'attuale biblioteca. È possibile anche indicare
 *  l'anno di prima
 *  istituzione o fondazione, nel
 *  caso che la biblioteca abbia avuto
 *  nel tempo
 *  gestioni amministrative diverse.
 *  
 *  DEPRECATO: 'Istituzione' è stato sostituito
 *  dall'elemento 'istituzione'.
 *  Inoltre gli elementi 'dataIstituzione'
 *  e 'dataFondazione' sono diventati
 *  rispettivamente
 *  'data-istituzione' e 'data-fondazione'.
 *  
 * 
 * @version $Revision: 1.7 $ $Date: 2012/11/22 09:50:20 $
 */
@SuppressWarnings("serial")
public class Istituzione implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _dataIstituzione.
     */
    private it.inera.abi.logic.formatodiscambio.castor.DataIstituzione _dataIstituzione;

    /**
     * Field _dataFondazione.
     */
    private it.inera.abi.logic.formatodiscambio.castor.DataFondazione _dataFondazione;


      //----------------/
     //- Constructors -/
    //----------------/

    public Istituzione() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'dataFondazione'.
     * 
     * @return the value of field 'DataFondazione'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.DataFondazione getDataFondazione(
    ) {
        return this._dataFondazione;
    }

    /**
     * Returns the value of field 'dataIstituzione'.
     * 
     * @return the value of field 'DataIstituzione'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.DataIstituzione getDataIstituzione(
    ) {
        return this._dataIstituzione;
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
     * Sets the value of field 'dataFondazione'.
     * 
     * @param dataFondazione the value of field 'dataFondazione'.
     */
    public void setDataFondazione(
            final it.inera.abi.logic.formatodiscambio.castor.DataFondazione dataFondazione) {
        this._dataFondazione = dataFondazione;
    }

    /**
     * Sets the value of field 'dataIstituzione'.
     * 
     * @param dataIstituzione the value of field 'dataIstituzione'.
     */
    public void setDataIstituzione(
            final it.inera.abi.logic.formatodiscambio.castor.DataIstituzione dataIstituzione) {
        this._dataIstituzione = dataIstituzione;
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
     * it.inera.abi.logic.formatodiscambio.castor.Istituzione
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Istituzione unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Istituzione) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Istituzione.class, reader);
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
