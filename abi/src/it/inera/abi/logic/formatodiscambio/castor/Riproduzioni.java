/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: Riproduzioni.java,v 1.7 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Con questo elemento si dà la possibilità di
 *  inserire una lista di riproduzioni ammesse. 
 *  
 *  Introdotto attributo 'attivo' del tipo siNoType.
 *  
 * 
 * @version $Revision: 1.7 $ $Date: 2012/11/22 09:50:20 $
 */
@SuppressWarnings("serial")
public class Riproduzioni implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _attivo.
     */
    private it.inera.abi.logic.formatodiscambio.castor.types.SiNoType _attivo;

    /**
     * Con questo elemento si dà la possibilità di
     *  esprimere le modalità di riprodurre i vari
     *  materiali ammessi al
     *  prestito locale,
     *  nazionale e/o internazionale.
     *  
     */
    private java.util.List<it.inera.abi.logic.formatodiscambio.castor.Riproduzione> _riproduzioneList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Riproduzioni() {
        super();
        this._riproduzioneList = new java.util.ArrayList<it.inera.abi.logic.formatodiscambio.castor.Riproduzione>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vRiproduzione
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addRiproduzione(
            final it.inera.abi.logic.formatodiscambio.castor.Riproduzione vRiproduzione)
    throws java.lang.IndexOutOfBoundsException {
        this._riproduzioneList.add(vRiproduzione);
    }

    /**
     * 
     * 
     * @param index
     * @param vRiproduzione
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addRiproduzione(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.Riproduzione vRiproduzione)
    throws java.lang.IndexOutOfBoundsException {
        this._riproduzioneList.add(index, vRiproduzione);
    }

    /**
     * Method enumerateRiproduzione.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends it.inera.abi.logic.formatodiscambio.castor.Riproduzione> enumerateRiproduzione(
    ) {
        return java.util.Collections.enumeration(this._riproduzioneList);
    }

    /**
     * Returns the value of field 'attivo'.
     * 
     * @return the value of field 'Attivo'.
     */
    public it.inera.abi.logic.formatodiscambio.castor.types.SiNoType getAttivo(
    ) {
        return this._attivo;
    }

    /**
     * Method getRiproduzione.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * it.inera.abi.logic.formatodiscambio.castor.Riproduzione at
     * the given index
     */
    public it.inera.abi.logic.formatodiscambio.castor.Riproduzione getRiproduzione(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._riproduzioneList.size()) {
            throw new IndexOutOfBoundsException("getRiproduzione: Index value '" + index + "' not in range [0.." + (this._riproduzioneList.size() - 1) + "]");
        }

        return (it.inera.abi.logic.formatodiscambio.castor.Riproduzione) _riproduzioneList.get(index);
    }

    /**
     * Method getRiproduzione.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public it.inera.abi.logic.formatodiscambio.castor.Riproduzione[] getRiproduzione(
    ) {
        it.inera.abi.logic.formatodiscambio.castor.Riproduzione[] array = new it.inera.abi.logic.formatodiscambio.castor.Riproduzione[0];
        return (it.inera.abi.logic.formatodiscambio.castor.Riproduzione[]) this._riproduzioneList.toArray(array);
    }

    /**
     * Method getRiproduzioneCount.
     * 
     * @return the size of this collection
     */
    public int getRiproduzioneCount(
    ) {
        return this._riproduzioneList.size();
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
     * Method iterateRiproduzione.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends it.inera.abi.logic.formatodiscambio.castor.Riproduzione> iterateRiproduzione(
    ) {
        return this._riproduzioneList.iterator();
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
    public void removeAllRiproduzione(
    ) {
        this._riproduzioneList.clear();
    }

    /**
     * Method removeRiproduzione.
     * 
     * @param vRiproduzione
     * @return true if the object was removed from the collection.
     */
    public boolean removeRiproduzione(
            final it.inera.abi.logic.formatodiscambio.castor.Riproduzione vRiproduzione) {
        boolean removed = _riproduzioneList.remove(vRiproduzione);
        return removed;
    }

    /**
     * Method removeRiproduzioneAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public it.inera.abi.logic.formatodiscambio.castor.Riproduzione removeRiproduzioneAt(
            final int index) {
        java.lang.Object obj = this._riproduzioneList.remove(index);
        return (it.inera.abi.logic.formatodiscambio.castor.Riproduzione) obj;
    }

    /**
     * Sets the value of field 'attivo'.
     * 
     * @param attivo the value of field 'attivo'.
     */
    public void setAttivo(
            final it.inera.abi.logic.formatodiscambio.castor.types.SiNoType attivo) {
        this._attivo = attivo;
    }

    /**
     * 
     * 
     * @param index
     * @param vRiproduzione
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setRiproduzione(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.Riproduzione vRiproduzione)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._riproduzioneList.size()) {
            throw new IndexOutOfBoundsException("setRiproduzione: Index value '" + index + "' not in range [0.." + (this._riproduzioneList.size() - 1) + "]");
        }

        this._riproduzioneList.set(index, vRiproduzione);
    }

    /**
     * 
     * 
     * @param vRiproduzioneArray
     */
    public void setRiproduzione(
            final it.inera.abi.logic.formatodiscambio.castor.Riproduzione[] vRiproduzioneArray) {
        //-- copy array
        _riproduzioneList.clear();

        for (int i = 0; i < vRiproduzioneArray.length; i++) {
                this._riproduzioneList.add(vRiproduzioneArray[i]);
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
     * it.inera.abi.logic.formatodiscambio.castor.Riproduzioni
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Riproduzioni unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Riproduzioni) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Riproduzioni.class, reader);
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
