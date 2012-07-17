/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: InformazioniBibliografiche.java,v 1.2 2012/07/17 09:09:28 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Per segnalare la disponibilità del servizio
 *  interno si usa l'elemento vuoto omonimo,
 *  soluzione discutibile ma funzionante. Essa ha il
 *  vantaggio di mettere questo servizio allo stesso
 *  livello del "servizio-esterno", com'è logico.
 *  Quest'ultimo però è più articolato.
 *  
 * 
 * @version $Revision: 1.2 $ $Date: 2012/07/17 09:09:28 $
 */
@SuppressWarnings("serial")
public class InformazioniBibliografiche implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _servizioInterno.
     */
    private it.inera.abi.logic.formatodiscambio.castor.types.SiNoType _servizioInterno;

    /**
     * Se è specificato il servizio
     *  esterno, sarebbe opportuno indicare
     *  almeno un "modo" di espletamento del
     *  servizio stesso. Come in altri casi,
     *  anche un elemento vuoto potrebbe
     *  essere accettato, come semplice
     *  segnalazione di un servizio
     *  disponibile. Starà poi
     *  all'applicativo ricevente decidere
     *  se ignorare tale informazione,
     *  perché troppo vaga, o tenerne
     *  comunque conto.
     *  
     */
    private it.inera.abi.logic.formatodiscambio.castor.ServizioEsterno _servizioEsterno;


      //----------------/
     //- Constructors -/
    //----------------/

    public InformazioniBibliografiche() {
        super();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'servizioEsterno'. The field
     * 'servizioEsterno' has the following description: Se è
     * specificato il servizio
     *  esterno, sarebbe opportuno indicare
     *  almeno un "modo" di espletamento del
     *  servizio stesso. Come in altri casi,
     *  anche un elemento vuoto potrebbe
     *  essere accettato, come semplice
     *  segnalazione di un servizio
     *  disponibile. Starà poi
     *  all'applicativo ricevente decidere
     *  se ignorare tale informazione,
     *  perché troppo vaga, o tenerne
     *  comunque conto.
     *  
     * 
     * @return the value of field 'ServizioEsterno'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.ServizioEsterno getServizioEsterno(
    ) {
        return this._servizioEsterno;
    }

    /**
     * Returns the value of field 'servizioInterno'.
     * 
     * @return the value of field 'ServizioInterno'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.types.SiNoType getServizioInterno(
    ) {
        return this._servizioInterno;
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
     * Sets the value of field 'servizioEsterno'. The field
     * 'servizioEsterno' has the following description: Se è
     * specificato il servizio
     *  esterno, sarebbe opportuno indicare
     *  almeno un "modo" di espletamento del
     *  servizio stesso. Come in altri casi,
     *  anche un elemento vuoto potrebbe
     *  essere accettato, come semplice
     *  segnalazione di un servizio
     *  disponibile. Starà poi
     *  all'applicativo ricevente decidere
     *  se ignorare tale informazione,
     *  perché troppo vaga, o tenerne
     *  comunque conto.
     *  
     * 
     * @param servizioEsterno the value of field 'servizioEsterno'.
     */
    public void setServizioEsterno(
            final it.inera.abi.logic.formatodiscambio.castor.ServizioEsterno servizioEsterno) {
        this._servizioEsterno = servizioEsterno;
    }

    /**
     * Sets the value of field 'servizioInterno'.
     * 
     * @param servizioInterno the value of field 'servizioInterno'.
     */
    public void setServizioInterno(
            final it.inera.abi.logic.formatodiscambio.castor.types.SiNoType servizioInterno) {
        this._servizioInterno = servizioInterno;
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
     * it.inera.abi.logic.formatodiscambio.castor.InformazioniBibliografiche
     */
    public static it.inera.abi.logic.formatodiscambio.castor.InformazioniBibliografiche unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.InformazioniBibliografiche) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.InformazioniBibliografiche.class, reader);
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
