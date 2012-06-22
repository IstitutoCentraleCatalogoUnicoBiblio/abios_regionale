/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: Zona.java,v 1.1 2012/06/22 13:55:04 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Class Zona.
 * 
 * @version $Revision: 1.1 $ $Date: 2012/06/22 13:55:04 $
 */
@SuppressWarnings("serial")
public class Zona implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Il nome della zona può
     *  essere ad esempio "Toscana",
     *  "Triveneto", "Centro-Sud",
     *  "Castelli Romani"...
     *  
     */
    private java.lang.String _nome;

    /**
     * Il tipo della zona può
     *  essere invece "regionale",
     *  "comunale" ...
     *  
     */
    private java.lang.String _tipo;


      //----------------/
     //- Constructors -/
    //----------------/

    public Zona() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'nome'. The field 'nome' has the
     * following description: Il nome della zona può
     *  essere ad esempio "Toscana",
     *  "Triveneto", "Centro-Sud",
     *  "Castelli Romani"...
     *  
     * 
     * @return the value of field 'Nome'.
     */
    public java.lang.String getNome(
    ) {
        return this._nome;
    }

    /**
     * Returns the value of field 'tipo'. The field 'tipo' has the
     * following description: Il tipo della zona può
     *  essere invece "regionale",
     *  "comunale" ...
     *  
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
     * Sets the value of field 'nome'. The field 'nome' has the
     * following description: Il nome della zona può
     *  essere ad esempio "Toscana",
     *  "Triveneto", "Centro-Sud",
     *  "Castelli Romani"...
     *  
     * 
     * @param nome the value of field 'nome'.
     */
    public void setNome(
            final java.lang.String nome) {
        this._nome = nome;
    }

    /**
     * Sets the value of field 'tipo'. The field 'tipo' has the
     * following description: Il tipo della zona può
     *  essere invece "regionale",
     *  "comunale" ...
     *  
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
     * it.inera.abi.logic.formatodiscambio.castor.Zona
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Zona unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Zona) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Zona.class, reader);
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
