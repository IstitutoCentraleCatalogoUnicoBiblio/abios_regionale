/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: DestinazioniSociali.java,v 1.7 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Class DestinazioniSociali.
 * 
 * @version $Revision: 1.7 $ $Date: 2012/11/22 09:50:20 $
 */
@SuppressWarnings("serial")
public class DestinazioniSociali implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _destinazioneList.
     */
    private java.util.List<it.inera.abi.logic.formatodiscambio.castor.Destinazione> _destinazioneList;


      //----------------/
     //- Constructors -/
    //----------------/

    public DestinazioniSociali() {
        super();
        this._destinazioneList = new java.util.ArrayList<it.inera.abi.logic.formatodiscambio.castor.Destinazione>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vDestinazione
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addDestinazione(
            final it.inera.abi.logic.formatodiscambio.castor.Destinazione vDestinazione)
    throws java.lang.IndexOutOfBoundsException {
        this._destinazioneList.add(vDestinazione);
    }

    /**
     * 
     * 
     * @param index
     * @param vDestinazione
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addDestinazione(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.Destinazione vDestinazione)
    throws java.lang.IndexOutOfBoundsException {
        this._destinazioneList.add(index, vDestinazione);
    }

    /**
     * Method enumerateDestinazione.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends it.inera.abi.logic.formatodiscambio.castor.Destinazione> enumerateDestinazione(
    ) {
        return java.util.Collections.enumeration(this._destinazioneList);
    }

    /**
     * Method getDestinazione.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * it.inera.abi.logic.formatodiscambio.castor.Destinazione at
     * the given index
     */
    public it.inera.abi.logic.formatodiscambio.castor.Destinazione getDestinazione(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._destinazioneList.size()) {
            throw new IndexOutOfBoundsException("getDestinazione: Index value '" + index + "' not in range [0.." + (this._destinazioneList.size() - 1) + "]");
        }

        return (it.inera.abi.logic.formatodiscambio.castor.Destinazione) _destinazioneList.get(index);
    }

    /**
     * Method getDestinazione.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public it.inera.abi.logic.formatodiscambio.castor.Destinazione[] getDestinazione(
    ) {
        it.inera.abi.logic.formatodiscambio.castor.Destinazione[] array = new it.inera.abi.logic.formatodiscambio.castor.Destinazione[0];
        return (it.inera.abi.logic.formatodiscambio.castor.Destinazione[]) this._destinazioneList.toArray(array);
    }

    /**
     * Method getDestinazioneCount.
     * 
     * @return the size of this collection
     */
    public int getDestinazioneCount(
    ) {
        return this._destinazioneList.size();
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
     * Method iterateDestinazione.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends it.inera.abi.logic.formatodiscambio.castor.Destinazione> iterateDestinazione(
    ) {
        return this._destinazioneList.iterator();
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
    public void removeAllDestinazione(
    ) {
        this._destinazioneList.clear();
    }

    /**
     * Method removeDestinazione.
     * 
     * @param vDestinazione
     * @return true if the object was removed from the collection.
     */
    public boolean removeDestinazione(
            final it.inera.abi.logic.formatodiscambio.castor.Destinazione vDestinazione) {
        boolean removed = _destinazioneList.remove(vDestinazione);
        return removed;
    }

    /**
     * Method removeDestinazioneAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public it.inera.abi.logic.formatodiscambio.castor.Destinazione removeDestinazioneAt(
            final int index) {
        java.lang.Object obj = this._destinazioneList.remove(index);
        return (it.inera.abi.logic.formatodiscambio.castor.Destinazione) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vDestinazione
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setDestinazione(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.Destinazione vDestinazione)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._destinazioneList.size()) {
            throw new IndexOutOfBoundsException("setDestinazione: Index value '" + index + "' not in range [0.." + (this._destinazioneList.size() - 1) + "]");
        }

        this._destinazioneList.set(index, vDestinazione);
    }

    /**
     * 
     * 
     * @param vDestinazioneArray
     */
    public void setDestinazione(
            final it.inera.abi.logic.formatodiscambio.castor.Destinazione[] vDestinazioneArray) {
        //-- copy array
        _destinazioneList.clear();

        for (int i = 0; i < vDestinazioneArray.length; i++) {
                this._destinazioneList.add(vDestinazioneArray[i]);
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
     * it.inera.abi.logic.formatodiscambio.castor.DestinazioniSocial
     */
    public static it.inera.abi.logic.formatodiscambio.castor.DestinazioniSociali unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.DestinazioniSociali) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.DestinazioniSociali.class, reader);
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
