/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: RuoloType.java,v 1.7 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor.types;

/**
 * Enumeration RuoloType.
 * 
 * @version $Revision: 1.7 $ $Date: 2012/11/22 09:50:20 $
 */
public enum RuoloType {


      //------------------/
     //- Enum Constants -/
    //------------------/

    /**
     * Constant POS
     */
    POS("POS"),
    /**
     * Constant DSC
     */
    DSC("DSC");

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
    private static final java.util.Map<java.lang.String, RuoloType> enumConstants = new java.util.HashMap<java.lang.String, RuoloType>();


    static {
        for (RuoloType c: RuoloType.values()) {
            RuoloType.enumConstants.put(c.value, c);
        }

    };


      //----------------/
     //- Constructors -/
    //----------------/

    private RuoloType(final java.lang.String value) {
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
    public static it.inera.abi.logic.formatodiscambio.castor.types.RuoloType fromValue(
            final java.lang.String value) {
        RuoloType c = RuoloType.enumConstants.get(value);
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
