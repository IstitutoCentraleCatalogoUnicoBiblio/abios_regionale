/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: AnnoSecoloType.java,v 1.3 2012/07/30 15:17:05 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Un elemento di questo tipo contiene una stringa come
 *  valore, e ha un attributo "tipo" che serve ad indicare
 *  se tale valore è un anno o un secolo. Questo non esclude
 *  un elemento con valore "XVI" e tipo "anno", per cui
 *  sarebbe auspicabile un costrutto più coerente.
 *  
 * 
 * @version $Revision: 1.3 $ $Date: 2012/07/30 15:17:05 $
 */
@SuppressWarnings("serial")
public abstract class AnnoSecoloType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * internal content storage
     */
    private java.lang.String _content = "";

    /**
     * Field _tipo.
     */
    private it.inera.abi.logic.formatodiscambio.castor.types.AnnoSecoloTypeTipoType _tipo;


      //----------------/
     //- Constructors -/
    //----------------/

    public AnnoSecoloType() {
        super();
        setContent("");
    }

    public AnnoSecoloType(final java.lang.String defaultValue) {
        try {
            setContent( new java.lang.String(defaultValue));
         } catch(Exception e) {
            throw new RuntimeException("Unable to cast default value for simple content!");
         } 
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'content'. The field 'content'
     * has the following description: internal content storage
     * 
     * @return the value of field 'Content'.
     */
    public java.lang.String getContent(
    ) {
        return this._content;
    }

    /**
     * Returns the value of field 'tipo'.
     * 
     * @return the value of field 'Tipo'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.types.AnnoSecoloTypeTipoType getTipo(
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
     * Sets the value of field 'content'. The field 'content' has
     * the following description: internal content storage
     * 
     * @param content the value of field 'content'.
     */
    public void setContent(
            final java.lang.String content) {
        this._content = content;
    }

    /**
     * Sets the value of field 'tipo'.
     * 
     * @param tipo the value of field 'tipo'.
     */
    public void setTipo(
            final it.inera.abi.logic.formatodiscambio.castor.types.AnnoSecoloTypeTipoType tipo) {
        this._tipo = tipo;
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
