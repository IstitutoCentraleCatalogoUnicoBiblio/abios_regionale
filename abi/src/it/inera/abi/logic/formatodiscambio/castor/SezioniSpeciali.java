/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: SezioniSpeciali.java,v 1.3 2012/07/30 15:17:04 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Se la biblioteca ha delle sezioni speciali,
 *  queste vanno inserite in altrettanti elementi
 *  "sezione". Nella maschera di ricerca avanzata
 *  dell'anagrafe, alla voce "Sezione speciale", è
 *  disponibile l'intero elenco delle sezioni
 *  speciali registrate dal sistema.
 *  
 * 
 * @version $Revision: 1.3 $ $Date: 2012/07/30 15:17:04 $
 */
@SuppressWarnings("serial")
public class SezioniSpeciali implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _sezioneList.
     */
    private java.util.List<java.lang.String> _sezioneList;


      //----------------/
     //- Constructors -/
    //----------------/

    public SezioniSpeciali() {
        super();
        this._sezioneList = new java.util.ArrayList<java.lang.String>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vSezione
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addSezione(
            final java.lang.String vSezione)
    throws java.lang.IndexOutOfBoundsException {
        this._sezioneList.add(vSezione);
    }

    /**
     * 
     * 
     * @param index
     * @param vSezione
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addSezione(
            final int index,
            final java.lang.String vSezione)
    throws java.lang.IndexOutOfBoundsException {
        this._sezioneList.add(index, vSezione);
    }

    /**
     * Method enumerateSezione.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends java.lang.String> enumerateSezione(
    ) {
        return java.util.Collections.enumeration(this._sezioneList);
    }

    /**
     * Method getSezione.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the java.lang.String at the given index
     */
    public java.lang.String getSezione(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._sezioneList.size()) {
            throw new IndexOutOfBoundsException("getSezione: Index value '" + index + "' not in range [0.." + (this._sezioneList.size() - 1) + "]");
        }

        return (java.lang.String) _sezioneList.get(index);
    }

    /**
     * Method getSezione.Returns the contents of the collection in
     * an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public java.lang.String[] getSezione(
    ) {
        java.lang.String[] array = new java.lang.String[0];
        return (java.lang.String[]) this._sezioneList.toArray(array);
    }

    /**
     * Method getSezioneCount.
     * 
     * @return the size of this collection
     */
    public int getSezioneCount(
    ) {
        return this._sezioneList.size();
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
     * Method iterateSezione.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends java.lang.String> iterateSezione(
    ) {
        return this._sezioneList.iterator();
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
    public void removeAllSezione(
    ) {
        this._sezioneList.clear();
    }

    /**
     * Method removeSezione.
     * 
     * @param vSezione
     * @return true if the object was removed from the collection.
     */
    public boolean removeSezione(
            final java.lang.String vSezione) {
        boolean removed = _sezioneList.remove(vSezione);
        return removed;
    }

    /**
     * Method removeSezioneAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public java.lang.String removeSezioneAt(
            final int index) {
        java.lang.Object obj = this._sezioneList.remove(index);
        return (java.lang.String) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vSezione
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setSezione(
            final int index,
            final java.lang.String vSezione)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._sezioneList.size()) {
            throw new IndexOutOfBoundsException("setSezione: Index value '" + index + "' not in range [0.." + (this._sezioneList.size() - 1) + "]");
        }

        this._sezioneList.set(index, vSezione);
    }

    /**
     * 
     * 
     * @param vSezioneArray
     */
    public void setSezione(
            final java.lang.String[] vSezioneArray) {
        //-- copy array
        _sezioneList.clear();

        for (int i = 0; i < vSezioneArray.length; i++) {
                this._sezioneList.add(vSezioneArray[i]);
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
     * it.inera.abi.logic.formatodiscambio.castor.SezioniSpeciali
     */
    public static it.inera.abi.logic.formatodiscambio.castor.SezioniSpeciali unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.SezioniSpeciali) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.SezioniSpeciali.class, reader);
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
