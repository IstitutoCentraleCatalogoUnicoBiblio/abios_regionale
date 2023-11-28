/*
 * Author Inera srl https://www.inera.it
 * Copyright (C) 2023  Inera srl https://www.inera.it/
 *
 * European Union Public Licence V. 1.2
 * EUPL (c) the European Community 2017
 *
 * This European Union Public Licence (the "EUPL") applies to the Work or Software (as defined below) which is provided under the terms of this Licence.
 * Any use of the Work, other than as authorised under this Licence is prohibited (to the extent such use is covered by a right of the copyright holder of the Work).
 * The Original Work is provided under the terms of this Licence when the Licensor (as defined below) has placed the following notice immediately following the copyright notice for the Original Work:
 * Licensed under the EUPL V.1.2 or has expressed by any other mean his willingness to license under the EUPL.
 *
 * You should have received a copy of the European Union Public Licence V. 1.2 along with this program.  If not, see https://eupl.eu/1.2/en/
 */

/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: ServizioEsterno.java,v 1.7 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Se è specificato il servizio
 *  esterno, sarebbe
 *  opportuno indicare
 *  almeno un "modo" di espletamento del
 *  servizio
 *  stesso. Come in altri casi,
 *  anche un elemento vuoto potrebbe
 *  essere accettato, come semplice
 *  segnalazione di un servizio
 *  disponibile. Starà poi
 *  all'applicativo ricevente decidere
 *  se
 *  ignorare tale informazione,
 *  perché troppo vaga, o tenerne
 *  comunque conto.
 *  
 * 
 * @version $Revision: 1.7 $ $Date: 2012/11/22 09:50:20 $
 */
@SuppressWarnings("serial")
public class ServizioEsterno implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _modoList.
     */
    private java.util.List<java.lang.String> _modoList;


      //----------------/
     //- Constructors -/
    //----------------/

    public ServizioEsterno() {
        super();
        this._modoList = new java.util.ArrayList<java.lang.String>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vModo
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addModo(
            final java.lang.String vModo)
    throws java.lang.IndexOutOfBoundsException {
        this._modoList.add(vModo);
    }

    /**
     * 
     * 
     * @param index
     * @param vModo
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addModo(
            final int index,
            final java.lang.String vModo)
    throws java.lang.IndexOutOfBoundsException {
        this._modoList.add(index, vModo);
    }

    /**
     * Method enumerateModo.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends java.lang.String> enumerateModo(
    ) {
        return java.util.Collections.enumeration(this._modoList);
    }

    /**
     * Method getModo.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the java.lang.String at the given index
     */
    public java.lang.String getModo(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._modoList.size()) {
            throw new IndexOutOfBoundsException("getModo: Index value '" + index + "' not in range [0.." + (this._modoList.size() - 1) + "]");
        }

        return (java.lang.String) _modoList.get(index);
    }

    /**
     * Method getModo.Returns the contents of the collection in an
     * Array.  <p>Note:  Just in case the collection contents are
     * changing in another thread, we pass a 0-length Array of the
     * correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public java.lang.String[] getModo(
    ) {
        java.lang.String[] array = new java.lang.String[0];
        return (java.lang.String[]) this._modoList.toArray(array);
    }

    /**
     * Method getModoCount.
     * 
     * @return the size of this collection
     */
    public int getModoCount(
    ) {
        return this._modoList.size();
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
     * Method iterateModo.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends java.lang.String> iterateModo(
    ) {
        return this._modoList.iterator();
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
     */
    public void removeAllModo(
    ) {
        this._modoList.clear();
    }

    /**
     * Method removeModo.
     * 
     * @param vModo
     * @return true if the object was removed from the collection.
     */
    public boolean removeModo(
            final java.lang.String vModo) {
        boolean removed = _modoList.remove(vModo);
        return removed;
    }

    /**
     * Method removeModoAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public java.lang.String removeModoAt(
            final int index) {
        java.lang.Object obj = this._modoList.remove(index);
        return (java.lang.String) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vModo
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setModo(
            final int index,
            final java.lang.String vModo)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._modoList.size()) {
            throw new IndexOutOfBoundsException("setModo: Index value '" + index + "' not in range [0.." + (this._modoList.size() - 1) + "]");
        }

        this._modoList.set(index, vModo);
    }

    /**
     * 
     * 
     * @param vModoArray
     */
    public void setModo(
            final java.lang.String[] vModoArray) {
        //-- copy array
        _modoList.clear();

        for (int i = 0; i < vModoArray.length; i++) {
                this._modoList.add(vModoArray[i]);
        }
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
     * it.inera.abi.logic.formatodiscambio.castor.ServizioEsterno
     */
    public static it.inera.abi.logic.formatodiscambio.castor.ServizioEsterno unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.ServizioEsterno) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.ServizioEsterno.class, reader);
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
