/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: AnnoSecoloTypeTipoType.java,v 1.7 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor.types;

/**
 * Enumeration AnnoSecoloTypeTipoType.
 * 
 * @version $Revision: 1.7 $ $Date: 2012/11/22 09:50:20 $
 */
public enum AnnoSecoloTypeTipoType {


      //------------------/
     //- Enum Constants -/
    //------------------/

    /**
     * Constant ANNO
     */
    ANNO("anno"),
    /**
     * Constant SECOLO
     */
    SECOLO("secolo");

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
    private static final java.util.Map<java.lang.String, AnnoSecoloTypeTipoType> enumConstants = new java.util.HashMap<java.lang.String, AnnoSecoloTypeTipoType>();


    static {
        for (AnnoSecoloTypeTipoType c: AnnoSecoloTypeTipoType.values()) {
            AnnoSecoloTypeTipoType.enumConstants.put(c.value, c);
        }

    };


      //----------------/
     //- Constructors -/
    //----------------/

    private AnnoSecoloTypeTipoType(final java.lang.String value) {
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
    public static it.inera.abi.logic.formatodiscambio.castor.types.AnnoSecoloTypeTipoType fromValue(
            final java.lang.String value) {
        AnnoSecoloTypeTipoType c = AnnoSecoloTypeTipoType.enumConstants.get(value);
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
