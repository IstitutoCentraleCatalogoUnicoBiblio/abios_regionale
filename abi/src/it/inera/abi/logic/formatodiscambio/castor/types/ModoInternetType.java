/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: ModoInternetType.java,v 1.1 2012/06/22 13:55:37 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor.types;

/**
 * Enumeration ModoInternetType.
 * 
 * @version $Revision: 1.1 $ $Date: 2012/06/22 13:55:37 $
 */
public enum ModoInternetType {


      //------------------/
     //- Enum Constants -/
    //------------------/

    /**
     * Constant LIBERO
     */
    LIBERO("libero"),
    /**
     * Constant A_PAGAMENTO
     */
    A_PAGAMENTO("a pagamento"),
    /**
     * Constant A_TEMPO
     */
    A_TEMPO("a tempo"),
    /**
     * Constant LIMITATO
     */
    LIMITATO("limitato");

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
    private static final java.util.Map<java.lang.String, ModoInternetType> enumConstants = new java.util.HashMap<java.lang.String, ModoInternetType>();


    static {
        for (ModoInternetType c: ModoInternetType.values()) {
            ModoInternetType.enumConstants.put(c.value, c);
        }

    };


      //----------------/
     //- Constructors -/
    //----------------/

    private ModoInternetType(final java.lang.String value) {
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
    public static it.inera.abi.logic.formatodiscambio.castor.types.ModoInternetType fromValue(
            final java.lang.String value) {
        ModoInternetType c = ModoInternetType.enumConstants.get(value);
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
