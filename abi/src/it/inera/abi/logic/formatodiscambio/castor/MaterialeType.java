/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: MaterialeType.java,v 1.1 2012/06/22 13:55:04 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Un tipo di materiale è descritto semplicemente da un
 *  nome e da un posseduto (opzionale). Quest'ultimo
 *  dev'essere un intero non negativo.
 *  
 * 
 * @version $Revision: 1.1 $ $Date: 2012/06/22 13:55:04 $
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
