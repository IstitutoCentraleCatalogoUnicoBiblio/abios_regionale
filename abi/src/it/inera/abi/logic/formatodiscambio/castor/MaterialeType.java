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
 * $Id: MaterialeType.java,v 1.7 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Un tipo di materiale è descritto semplicemente da un
 *  nome e da un posseduto (opzionale). Quest'ultimo
 *  dev'essere un intero non negativo.
 *  
 * 
 * @version $Revision: 1.7 $ $Date: 2012/11/22 09:50:20 $
 */
@SuppressWarnings("serial")
public abstract class MaterialeType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _nome.
     */
    private java.lang.String _nome;

    /**
     * Field _posseduto.
     */
    private long _posseduto;

    /**
     * keeps track of state for field: _posseduto
     */
    private boolean _has_posseduto;

    /**
     * Field _acquistiUltimoAnno.
     */
    private long _acquistiUltimoAnno;

    /**
     * keeps track of state for field: _acquistiUltimoAnno
     */
    private boolean _has_acquistiUltimoAnno;


      //----------------/
     //- Constructors -/
    //----------------/

    public MaterialeType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     */
    public void deleteAcquistiUltimoAnno(
    ) {
        this._has_acquistiUltimoAnno= false;
    }

    /**
     */
    public void deletePosseduto(
    ) {
        this._has_posseduto= false;
    }

    /**
     * Returns the value of field 'acquistiUltimoAnno'.
     * 
     * @return the value of field 'AcquistiUltimoAnno'.
     */
    public long getAcquistiUltimoAnno(
    ) {
        return this._acquistiUltimoAnno;
    }

    /**
     * Returns the value of field 'nome'.
     * 
     * @return the value of field 'Nome'.
     */
    public java.lang.String getNome(
    ) {
        return this._nome;
    }

    /**
     * Returns the value of field 'posseduto'.
     * 
     * @return the value of field 'Posseduto'.
     */
    public long getPosseduto(
    ) {
        return this._posseduto;
    }

    /**
     * Method hasAcquistiUltimoAnno.
     * 
     * @return true if at least one AcquistiUltimoAnno has been adde
     */
    public boolean hasAcquistiUltimoAnno(
    ) {
        return this._has_acquistiUltimoAnno;
    }

    /**
     * Method hasPosseduto.
     * 
     * @return true if at least one Posseduto has been added
     */
    public boolean hasPosseduto(
    ) {
        return this._has_posseduto;
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
     * Sets the value of field 'acquistiUltimoAnno'.
     * 
     * @param acquistiUltimoAnno the value of field
     * 'acquistiUltimoAnno'.
     */
    public void setAcquistiUltimoAnno(
            final long acquistiUltimoAnno) {
        this._acquistiUltimoAnno = acquistiUltimoAnno;
        this._has_acquistiUltimoAnno = true;
    }

    /**
     * Sets the value of field 'nome'.
     * 
     * @param nome the value of field 'nome'.
     */
    public void setNome(
            final java.lang.String nome) {
        this._nome = nome;
    }

    /**
     * Sets the value of field 'posseduto'.
     * 
     * @param posseduto the value of field 'posseduto'.
     */
    public void setPosseduto(
            final long posseduto) {
        this._posseduto = posseduto;
        this._has_posseduto = true;
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
