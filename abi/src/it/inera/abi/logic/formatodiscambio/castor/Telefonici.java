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
 * $Id: Telefonici.java,v 1.4 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * E' stato introdotto l'elemento ripetibile
 *  'telefonici' come contenitore degli 
 *  eventuali contatti telefonici.
 *  
 * 
 * @version $Revision: 1.4 $ $Date: 2012/11/22 09:50:20 $
 */
@SuppressWarnings("serial")
public class Telefonici implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Un contatto di tipo telefonico. Il prefisso è
     *  ovviamente quello internazionale.
     *  
     */
    private java.util.List<it.inera.abi.logic.formatodiscambio.castor.Telefonico> _telefonicoList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Telefonici() {
        super();
        this._telefonicoList = new java.util.ArrayList<it.inera.abi.logic.formatodiscambio.castor.Telefonico>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vTelefonico
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addTelefonico(
            final it.inera.abi.logic.formatodiscambio.castor.Telefonico vTelefonico)
    throws java.lang.IndexOutOfBoundsException {
        this._telefonicoList.add(vTelefonico);
    }

    /**
     * 
     * 
     * @param index
     * @param vTelefonico
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addTelefonico(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.Telefonico vTelefonico)
    throws java.lang.IndexOutOfBoundsException {
        this._telefonicoList.add(index, vTelefonico);
    }

    /**
     * Method enumerateTelefonico.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends it.inera.abi.logic.formatodiscambio.castor.Telefonico> enumerateTelefonico(
    ) {
        return java.util.Collections.enumeration(this._telefonicoList);
    }

    /**
     * Method getTelefonico.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * it.inera.abi.logic.formatodiscambio.castor.Telefonico at the
     * given index
     */
    public it.inera.abi.logic.formatodiscambio.castor.Telefonico getTelefonico(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._telefonicoList.size()) {
            throw new IndexOutOfBoundsException("getTelefonico: Index value '" + index + "' not in range [0.." + (this._telefonicoList.size() - 1) + "]");
        }

        return (it.inera.abi.logic.formatodiscambio.castor.Telefonico) _telefonicoList.get(index);
    }

    /**
     * Method getTelefonico.Returns the contents of the collection
     * in an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public it.inera.abi.logic.formatodiscambio.castor.Telefonico[] getTelefonico(
    ) {
        it.inera.abi.logic.formatodiscambio.castor.Telefonico[] array = new it.inera.abi.logic.formatodiscambio.castor.Telefonico[0];
        return (it.inera.abi.logic.formatodiscambio.castor.Telefonico[]) this._telefonicoList.toArray(array);
    }

    /**
     * Method getTelefonicoCount.
     * 
     * @return the size of this collection
     */
    public int getTelefonicoCount(
    ) {
        return this._telefonicoList.size();
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
     * Method iterateTelefonico.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends it.inera.abi.logic.formatodiscambio.castor.Telefonico> iterateTelefonico(
    ) {
        return this._telefonicoList.iterator();
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
    public void removeAllTelefonico(
    ) {
        this._telefonicoList.clear();
    }

    /**
     * Method removeTelefonico.
     * 
     * @param vTelefonico
     * @return true if the object was removed from the collection.
     */
    public boolean removeTelefonico(
            final it.inera.abi.logic.formatodiscambio.castor.Telefonico vTelefonico) {
        boolean removed = _telefonicoList.remove(vTelefonico);
        return removed;
    }

    /**
     * Method removeTelefonicoAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public it.inera.abi.logic.formatodiscambio.castor.Telefonico removeTelefonicoAt(
            final int index) {
        java.lang.Object obj = this._telefonicoList.remove(index);
        return (it.inera.abi.logic.formatodiscambio.castor.Telefonico) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vTelefonico
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setTelefonico(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.Telefonico vTelefonico)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._telefonicoList.size()) {
            throw new IndexOutOfBoundsException("setTelefonico: Index value '" + index + "' not in range [0.." + (this._telefonicoList.size() - 1) + "]");
        }

        this._telefonicoList.set(index, vTelefonico);
    }

    /**
     * 
     * 
     * @param vTelefonicoArray
     */
    public void setTelefonico(
            final it.inera.abi.logic.formatodiscambio.castor.Telefonico[] vTelefonicoArray) {
        //-- copy array
        _telefonicoList.clear();

        for (int i = 0; i < vTelefonicoArray.length; i++) {
                this._telefonicoList.add(vTelefonicoArray[i]);
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
     * it.inera.abi.logic.formatodiscambio.castor.Telefonici
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Telefonici unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Telefonici) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Telefonici.class, reader);
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
