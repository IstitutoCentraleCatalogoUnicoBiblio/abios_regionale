/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: CatalogoSpecialeType.java,v 1.7 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Catalogo speciale. Deriva da "catalogoType", con
 *  l'aggiunta del "nome" del catalogo e del "materiale".
 *  
 *  Notare come tale tipo di elemento costituisce la base
 *  per il "catalogoSpecialeType".
 *  
 * 
 * @version $Revision: 1.7 $ $Date: 2012/11/22 09:50:20 $
 */
@SuppressWarnings("serial")
public abstract class CatalogoSpecialeType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _nome.
     */
    private java.lang.String _nome;

    /**
     * Field _catSpecMateriali.
     */
    private it.inera.abi.logic.formatodiscambio.castor.CatSpecMateriali _catSpecMateriali;


      //----------------/
     //- Constructors -/
    //----------------/

    public CatalogoSpecialeType() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'catSpecMateriali'.
     * 
     * @return the value of field 'CatSpecMateriali'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.CatSpecMateriali getCatSpecMateriali(
    ) {
        return this._catSpecMateriali;
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
     * Sets the value of field 'catSpecMateriali'.
     * 
     * @param catSpecMateriali the value of field 'catSpecMateriali'
     */
    public void setCatSpecMateriali(
            final it.inera.abi.logic.formatodiscambio.castor.CatSpecMateriali catSpecMateriali) {
        this._catSpecMateriali = catSpecMateriali;
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
