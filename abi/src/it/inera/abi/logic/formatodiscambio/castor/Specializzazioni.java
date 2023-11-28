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
 * $Id: Specializzazioni.java,v 1.4 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * E' stato introdotto l'elemento ripetibile
 *  'specializzazioni' come contenitore
 *  delle eventuali specializzazioni.
 *  
 * 
 * @version $Revision: 1.4 $ $Date: 2012/11/22 09:50:20 $
 */
@SuppressWarnings("serial")
public class Specializzazioni implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _specializzazioneList.
     */
    private java.util.List<it.inera.abi.logic.formatodiscambio.castor.Specializzazione> _specializzazioneList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Specializzazioni() {
        super();
        this._specializzazioneList = new java.util.ArrayList<it.inera.abi.logic.formatodiscambio.castor.Specializzazione>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vSpecializzazione
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addSpecializzazione(
            final it.inera.abi.logic.formatodiscambio.castor.Specializzazione vSpecializzazione)
    throws java.lang.IndexOutOfBoundsException {
        this._specializzazioneList.add(vSpecializzazione);
    }

    /**
     * 
     * 
     * @param index
     * @param vSpecializzazione
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addSpecializzazione(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.Specializzazione vSpecializzazione)
    throws java.lang.IndexOutOfBoundsException {
        this._specializzazioneList.add(index, vSpecializzazione);
    }

    /**
     * Method enumerateSpecializzazione.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends it.inera.abi.logic.formatodiscambio.castor.Specializzazione> enumerateSpecializzazione(
    ) {
        return java.util.Collections.enumeration(this._specializzazioneList);
    }

    /**
     * Method getSpecializzazione.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * it.inera.abi.logic.formatodiscambio.castor.Specializzazione
     * at the given index
     */
    public it.inera.abi.logic.formatodiscambio.castor.Specializzazione getSpecializzazione(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._specializzazioneList.size()) {
            throw new IndexOutOfBoundsException("getSpecializzazione: Index value '" + index + "' not in range [0.." + (this._specializzazioneList.size() - 1) + "]");
        }

        return (it.inera.abi.logic.formatodiscambio.castor.Specializzazione) _specializzazioneList.get(index);
    }

    /**
     * Method getSpecializzazione.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public it.inera.abi.logic.formatodiscambio.castor.Specializzazione[] getSpecializzazione(
    ) {
        it.inera.abi.logic.formatodiscambio.castor.Specializzazione[] array = new it.inera.abi.logic.formatodiscambio.castor.Specializzazione[0];
        return (it.inera.abi.logic.formatodiscambio.castor.Specializzazione[]) this._specializzazioneList.toArray(array);
    }

    /**
     * Method getSpecializzazioneCount.
     * 
     * @return the size of this collection
     */
    public int getSpecializzazioneCount(
    ) {
        return this._specializzazioneList.size();
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
     * Method iterateSpecializzazione.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends it.inera.abi.logic.formatodiscambio.castor.Specializzazione> iterateSpecializzazione(
    ) {
        return this._specializzazioneList.iterator();
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
    public void removeAllSpecializzazione(
    ) {
        this._specializzazioneList.clear();
    }

    /**
     * Method removeSpecializzazione.
     * 
     * @param vSpecializzazione
     * @return true if the object was removed from the collection.
     */
    public boolean removeSpecializzazione(
            final it.inera.abi.logic.formatodiscambio.castor.Specializzazione vSpecializzazione) {
        boolean removed = _specializzazioneList.remove(vSpecializzazione);
        return removed;
    }

    /**
     * Method removeSpecializzazioneAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public it.inera.abi.logic.formatodiscambio.castor.Specializzazione removeSpecializzazioneAt(
            final int index) {
        java.lang.Object obj = this._specializzazioneList.remove(index);
        return (it.inera.abi.logic.formatodiscambio.castor.Specializzazione) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vSpecializzazione
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setSpecializzazione(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.Specializzazione vSpecializzazione)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._specializzazioneList.size()) {
            throw new IndexOutOfBoundsException("setSpecializzazione: Index value '" + index + "' not in range [0.." + (this._specializzazioneList.size() - 1) + "]");
        }

        this._specializzazioneList.set(index, vSpecializzazione);
    }

    /**
     * 
     * 
     * @param vSpecializzazioneArray
     */
    public void setSpecializzazione(
            final it.inera.abi.logic.formatodiscambio.castor.Specializzazione[] vSpecializzazioneArray) {
        //-- copy array
        _specializzazioneList.clear();

        for (int i = 0; i < vSpecializzazioneArray.length; i++) {
                this._specializzazioneList.add(vSpecializzazioneArray[i]);
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
     * it.inera.abi.logic.formatodiscambio.castor.Specializzazioni
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Specializzazioni unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Specializzazioni) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Specializzazioni.class, reader);
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
