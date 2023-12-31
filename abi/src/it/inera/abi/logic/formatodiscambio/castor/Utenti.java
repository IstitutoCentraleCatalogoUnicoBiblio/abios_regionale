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
 * $Id: Utenti.java,v 1.7 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Informazioni relative agli utenti della biblioteca.
 *  
 * 
 * @version $Revision: 1.7 $ $Date: 2012/11/22 09:50:20 $
 */
@SuppressWarnings("serial")
public class Utenti implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _ultimoAnno.
     */
    private long _ultimoAnno;

    /**
     * keeps track of state for field: _ultimoAnno
     */
    private boolean _has_ultimoAnno;

    /**
     * Field _iscrittiPrestito.
     */
    private long _iscrittiPrestito;

    /**
     * keeps track of state for field: _iscrittiPrestito
     */
    private boolean _has_iscrittiPrestito;

    /**
     * Field _minoriQuattordiciAnni.
     */
    private long _minoriQuattordiciAnni;

    /**
     * keeps track of state for field: _minoriQuattordiciAnni
     */
    private boolean _has_minoriQuattordiciAnni;


      //----------------/
     //- Constructors -/
    //----------------/

    public Utenti() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deleteIscrittiPrestito(
    ) {
        this._has_iscrittiPrestito= false;
    }

    /**
     */
    public void deleteMinoriQuattordiciAnni(
    ) {
        this._has_minoriQuattordiciAnni= false;
    }

    /**
     */
    public void deleteUltimoAnno(
    ) {
        this._has_ultimoAnno= false;
    }

    /**
     * Returns the value of field 'iscrittiPrestito'.
     * 
     * @return the value of field 'IscrittiPrestito'.
     */
    public long getIscrittiPrestito(
    ) {
        return this._iscrittiPrestito;
    }

    /**
     * Returns the value of field 'minoriQuattordiciAnni'.
     * 
     * @return the value of field 'MinoriQuattordiciAnni'.
     */
    public long getMinoriQuattordiciAnni(
    ) {
        return this._minoriQuattordiciAnni;
    }

    /**
     * Returns the value of field 'ultimoAnno'.
     * 
     * @return the value of field 'UltimoAnno'.
     */
    public long getUltimoAnno(
    ) {
        return this._ultimoAnno;
    }

    /**
     * Method hasIscrittiPrestito.
     * 
     * @return true if at least one IscrittiPrestito has been added
     */
    public boolean hasIscrittiPrestito(
    ) {
        return this._has_iscrittiPrestito;
    }

    /**
     * Method hasMinoriQuattordiciAnni.
     * 
     * @return true if at least one MinoriQuattordiciAnni has been
     * added
     */
    public boolean hasMinoriQuattordiciAnni(
    ) {
        return this._has_minoriQuattordiciAnni;
    }

    /**
     * Method hasUltimoAnno.
     * 
     * @return true if at least one UltimoAnno has been added
     */
    public boolean hasUltimoAnno(
    ) {
        return this._has_ultimoAnno;
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
     * Sets the value of field 'iscrittiPrestito'.
     * 
     * @param iscrittiPrestito the value of field 'iscrittiPrestito'
     */
    public void setIscrittiPrestito(
            final long iscrittiPrestito) {
        this._iscrittiPrestito = iscrittiPrestito;
        this._has_iscrittiPrestito = true;
    }

    /**
     * Sets the value of field 'minoriQuattordiciAnni'.
     * 
     * @param minoriQuattordiciAnni the value of field
     * 'minoriQuattordiciAnni'.
     */
    public void setMinoriQuattordiciAnni(
            final long minoriQuattordiciAnni) {
        this._minoriQuattordiciAnni = minoriQuattordiciAnni;
        this._has_minoriQuattordiciAnni = true;
    }

    /**
     * Sets the value of field 'ultimoAnno'.
     * 
     * @param ultimoAnno the value of field 'ultimoAnno'.
     */
    public void setUltimoAnno(
            final long ultimoAnno) {
        this._ultimoAnno = ultimoAnno;
        this._has_ultimoAnno = true;
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
     * it.inera.abi.logic.formatodiscambio.castor.Utenti
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Utenti unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Utenti) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Utenti.class, reader);
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
