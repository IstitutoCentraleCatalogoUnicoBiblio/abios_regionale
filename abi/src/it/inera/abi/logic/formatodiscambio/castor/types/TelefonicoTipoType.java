/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: TelefonicoTipoType.java,v 1.1 2012/06/22 13:55:37 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor.types;

/**
 * Enumeration TelefonicoTipoType.
 * 
 * @version $Revision: 1.1 $ $Date: 2012/06/22 13:55:37 $
 */
public enum TelefonicoTipoType {


      //------------------/
     //- Enum Constants -/
    //------------------/

    /**
     * Constant FAX
     */
    FAX("fax"),
    /**
     * Constant TELEFONO
     */
    TELEFONO("telefono");

      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field value.
     */
    private final java.lang.String value;

    /**
     * Field enumConstants.
     */
    private static final java.util.Map<java.lang.String, TelefonicoTipoType> enumConstants = new java.util.HashMap<java.lang.String, TelefonicoTipoType>();


    static {
        for (TelefonicoTipoType c: TelefonicoTipoType.values()) {
            TelefonicoTipoType.enumConstants.put(c.value, c);
        }

    };


      //----------------/
     //- Constructors -/
    //----------------/

    private TelefonicoTipoType(final java.lang.String value) {
        this.value = value;
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method fromValue.
     * 
     * @param value
     * @return the constant for this value
     */
    public static it.inera.abi.logic.formatodiscambio.castor.types.TelefonicoTipoType fromValue(
            final java.lang.String value) {
        TelefonicoTipoType c = TelefonicoTipoType.enumConstants.get(value);
        if (c != null) {
            return c;
        }
        throw new IllegalArgumentException(value);
    }

    /**
     * 
     * 
     * @param value
     */
    public void setValue(
            final java.lang.String value) {
    }

    /**
     * Method toString.
     * 
     * @return the value of this constant
     */
    public java.lang.String toString(
    ) {
        return this.value;
    }

    /**
     * Method value.
     * 
     * @return the value of this constant
     */
    public java.lang.String value(
    ) {
        return this.value;
    }

}
