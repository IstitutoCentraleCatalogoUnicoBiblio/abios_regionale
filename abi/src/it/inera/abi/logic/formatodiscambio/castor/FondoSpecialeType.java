/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: FondoSpecialeType.java,v 1.7 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * A parte alcuni elementi dall'ovvio significato, la
 *  "descrizione" corrisponde ad una descrizione libera del
 *  fondo speciale, mentre "digitalizzazione" registra in
 *  forma libera lo stato dell'eventuale digitalizzazione
 *  del fondo.
 *  
 * 
 * @version $Revision: 1.7 $ $Date: 2012/11/22 09:50:20 $
 */
@SuppressWarnings("serial")
public abstract class FondoSpecialeType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _nome.
     */
    private java.lang.String _nome;

    /**
     * Field _descrizione.
     */
    private java.lang.String _descrizione;

    /**
     * Field _fondoSpecialeCdd.
     */
    private java.lang.String _fondoSpecialeCdd;

    /**
     * Field _depositato.
     */
    private it.inera.abi.logic.formatodiscambio.castor.types.SiNoType _depositato;

    /**
     * Field _digitalizzazione.
     */
    private it.inera.abi.logic.formatodiscambio.castor.types.SiNoType _digitalizzazione;

    /**
     * Field _catalogoInventario.
     */
    private it.inera.abi.logic.formatodiscambio.castor.types.CatalogoInventarioType _catalogoInventario;

    /**
     * Field _catalogoInventarioUrl.
     */
    private java.lang.String _catalogoInventarioUrl;


      //----------------/
     //- Constructors -/
    //----------------/

    public FondoSpecialeType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'catalogoInventario'.
     * 
     * @return the value of field 'CatalogoInventario'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.types.CatalogoInventarioType getCatalogoInventario(
    ) {
        return this._catalogoInventario;
    }

    /**
     * Returns the value of field 'catalogoInventarioUrl'.
     * 
     * @return the value of field 'CatalogoInventarioUrl'.
     */
    public java.lang.String getCatalogoInventarioUrl(
    ) {
        return this._catalogoInventarioUrl;
    }

    /**
     * Returns the value of field 'depositato'.
     * 
     * @return the value of field 'Depositato'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.types.SiNoType getDepositato(
    ) {
        return this._depositato;
    }

    /**
     * Returns the value of field 'descrizione'.
     * 
     * @return the value of field 'Descrizione'.
     */
    public java.lang.String getDescrizione(
    ) {
        return this._descrizione;
    }

    /**
     * Returns the value of field 'digitalizzazione'.
     * 
     * @return the value of field 'Digitalizzazione'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.types.SiNoType getDigitalizzazione(
    ) {
        return this._digitalizzazione;
    }

    /**
     * Returns the value of field 'fondoSpecialeCdd'.
     * 
     * @return the value of field 'FondoSpecialeCdd'.
     */
    public java.lang.String getFondoSpecialeCdd(
    ) {
        return this._fondoSpecialeCdd;
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
     * Sets the value of field 'catalogoInventario'.
     * 
     * @param catalogoInventario the value of field
     * 'catalogoInventario'.
     */
    public void setCatalogoInventario(
            final it.inera.abi.logic.formatodiscambio.castor.types.CatalogoInventarioType catalogoInventario) {
        this._catalogoInventario = catalogoInventario;
    }

    /**
     * Sets the value of field 'catalogoInventarioUrl'.
     * 
     * @param catalogoInventarioUrl the value of field
     * 'catalogoInventarioUrl'.
     */
    public void setCatalogoInventarioUrl(
            final java.lang.String catalogoInventarioUrl) {
        this._catalogoInventarioUrl = catalogoInventarioUrl;
    }

    /**
     * Sets the value of field 'depositato'.
     * 
     * @param depositato the value of field 'depositato'.
     */
    public void setDepositato(
            final it.inera.abi.logic.formatodiscambio.castor.types.SiNoType depositato) {
        this._depositato = depositato;
    }

    /**
     * Sets the value of field 'descrizione'.
     * 
     * @param descrizione the value of field 'descrizione'.
     */
    public void setDescrizione(
            final java.lang.String descrizione) {
        this._descrizione = descrizione;
    }

    /**
     * Sets the value of field 'digitalizzazione'.
     * 
     * @param digitalizzazione the value of field 'digitalizzazione'
     */
    public void setDigitalizzazione(
            final it.inera.abi.logic.formatodiscambio.castor.types.SiNoType digitalizzazione) {
        this._digitalizzazione = digitalizzazione;
    }

    /**
     * Sets the value of field 'fondoSpecialeCdd'.
     * 
     * @param fondoSpecialeCdd the value of field 'fondoSpecialeCdd'
     */
    public void setFondoSpecialeCdd(
            final java.lang.String fondoSpecialeCdd) {
        this._fondoSpecialeCdd = fondoSpecialeCdd;
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
