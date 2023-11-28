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
 * $Id: Sistemi.java,v 1.7 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Se la biblioteca partecipa a uno o più sistemi
 *  di
 *  biblioteche, questi devono essere dichiarati
 *  in questo elemento,
 *  utilizzando sottoelementi
 *  "sistema". È obbligatorio almeno uno di
 *  questi
 *  sottoelementi. I sottoelementi "sistema"
 *  contegono
 *  semplicemente il nome di ciascun
 *  sistema di biblioteche.
 *  
 * 
 * @version $Revision: 1.7 $ $Date: 2012/11/22 09:50:20 $
 */
@SuppressWarnings("serial")
public class Sistemi implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _items.
     */
    private java.util.List<it.inera.abi.logic.formatodiscambio.castor.SistemiItem> _items;


      //----------------/
     //- Constructors -/
    //----------------/

    public Sistemi() {
        super();
        this._items = new java.util.ArrayList<it.inera.abi.logic.formatodiscambio.castor.SistemiItem>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vSistemiItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addSistemiItem(
            final it.inera.abi.logic.formatodiscambio.castor.SistemiItem vSistemiItem)
    throws java.lang.IndexOutOfBoundsException {
        this._items.add(vSistemiItem);
    }

    /**
     * 
     * 
     * @param index
     * @param vSistemiItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addSistemiItem(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.SistemiItem vSistemiItem)
    throws java.lang.IndexOutOfBoundsException {
        this._items.add(index, vSistemiItem);
    }

    /**
     * Method enumerateSistemiItem.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends it.inera.abi.logic.formatodiscambio.castor.SistemiItem> enumerateSistemiItem(
    ) {
        return java.util.Collections.enumeration(this._items);
    }

    /**
     * Method getSistemiItem.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * it.inera.abi.logic.formatodiscambio.castor.SistemiItem at
     * the given index
     */
    public it.inera.abi.logic.formatodiscambio.castor.SistemiItem getSistemiItem(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("getSistemiItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }

        return (it.inera.abi.logic.formatodiscambio.castor.SistemiItem) _items.get(index);
    }

    /**
     * Method getSistemiItem.Returns the contents of the collection
     * in an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public it.inera.abi.logic.formatodiscambio.castor.SistemiItem[] getSistemiItem(
    ) {
        it.inera.abi.logic.formatodiscambio.castor.SistemiItem[] array = new it.inera.abi.logic.formatodiscambio.castor.SistemiItem[0];
        return (it.inera.abi.logic.formatodiscambio.castor.SistemiItem[]) this._items.toArray(array);
    }

    /**
     * Method getSistemiItemCount.
     * 
     * @return the size of this collection
     */
    public int getSistemiItemCount(
    ) {
        return this._items.size();
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
     * Method iterateSistemiItem.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends it.inera.abi.logic.formatodiscambio.castor.SistemiItem> iterateSistemiItem(
    ) {
        return this._items.iterator();
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
    public void removeAllSistemiItem(
    ) {
        this._items.clear();
    }

    /**
     * Method removeSistemiItem.
     * 
     * @param vSistemiItem
     * @return true if the object was removed from the collection.
     */
    public boolean removeSistemiItem(
            final it.inera.abi.logic.formatodiscambio.castor.SistemiItem vSistemiItem) {
        boolean removed = _items.remove(vSistemiItem);
        return removed;
    }

    /**
     * Method removeSistemiItemAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public it.inera.abi.logic.formatodiscambio.castor.SistemiItem removeSistemiItemAt(
            final int index) {
        java.lang.Object obj = this._items.remove(index);
        return (it.inera.abi.logic.formatodiscambio.castor.SistemiItem) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vSistemiItem
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setSistemiItem(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.SistemiItem vSistemiItem)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._items.size()) {
            throw new IndexOutOfBoundsException("setSistemiItem: Index value '" + index + "' not in range [0.." + (this._items.size() - 1) + "]");
        }

        this._items.set(index, vSistemiItem);
    }

    /**
     * 
     * 
     * @param vSistemiItemArray
     */
    public void setSistemiItem(
            final it.inera.abi.logic.formatodiscambio.castor.SistemiItem[] vSistemiItemArray) {
        //-- copy array
        _items.clear();

        for (int i = 0; i < vSistemiItemArray.length; i++) {
                this._items.add(vSistemiItemArray[i]);
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
     * it.inera.abi.logic.formatodiscambio.castor.Sistemi
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Sistemi unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Sistemi) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Sistemi.class, reader);
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
