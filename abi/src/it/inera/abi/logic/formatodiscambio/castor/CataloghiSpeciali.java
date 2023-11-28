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
 * $Id: CataloghiSpeciali.java,v 1.4 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * E' stato introdotto l'elemento ripetibile
 *  'cataloghi-speciali' come contenitore
 *  degli eventuali cataloghi speciali.
 *  
 * 
 * @version $Revision: 1.4 $ $Date: 2012/11/22 09:50:20 $
 */
@SuppressWarnings("serial")
public class CataloghiSpeciali implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _catalogoSpecialeList.
     */
    private java.util.List<it.inera.abi.logic.formatodiscambio.castor.CatalogoSpeciale> _catalogoSpecialeList;


      //----------------/
     //- Constructors -/
    //----------------/

    public CataloghiSpeciali() {
        super();
        this._catalogoSpecialeList = new java.util.ArrayList<it.inera.abi.logic.formatodiscambio.castor.CatalogoSpeciale>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vCatalogoSpeciale
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addCatalogoSpeciale(
            final it.inera.abi.logic.formatodiscambio.castor.CatalogoSpeciale vCatalogoSpeciale)
    throws java.lang.IndexOutOfBoundsException {
        this._catalogoSpecialeList.add(vCatalogoSpeciale);
    }

    /**
     * 
     * 
     * @param index
     * @param vCatalogoSpeciale
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addCatalogoSpeciale(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.CatalogoSpeciale vCatalogoSpeciale)
    throws java.lang.IndexOutOfBoundsException {
        this._catalogoSpecialeList.add(index, vCatalogoSpeciale);
    }

    /**
     * Method enumerateCatalogoSpeciale.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends it.inera.abi.logic.formatodiscambio.castor.CatalogoSpeciale> enumerateCatalogoSpeciale(
    ) {
        return java.util.Collections.enumeration(this._catalogoSpecialeList);
    }

    /**
     * Method getCatalogoSpeciale.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * it.inera.abi.logic.formatodiscambio.castor.CatalogoSpeciale
     * at the given index
     */
    public it.inera.abi.logic.formatodiscambio.castor.CatalogoSpeciale getCatalogoSpeciale(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._catalogoSpecialeList.size()) {
            throw new IndexOutOfBoundsException("getCatalogoSpeciale: Index value '" + index + "' not in range [0.." + (this._catalogoSpecialeList.size() - 1) + "]");
        }

        return (it.inera.abi.logic.formatodiscambio.castor.CatalogoSpeciale) _catalogoSpecialeList.get(index);
    }

    /**
     * Method getCatalogoSpeciale.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public it.inera.abi.logic.formatodiscambio.castor.CatalogoSpeciale[] getCatalogoSpeciale(
    ) {
        it.inera.abi.logic.formatodiscambio.castor.CatalogoSpeciale[] array = new it.inera.abi.logic.formatodiscambio.castor.CatalogoSpeciale[0];
        return (it.inera.abi.logic.formatodiscambio.castor.CatalogoSpeciale[]) this._catalogoSpecialeList.toArray(array);
    }

    /**
     * Method getCatalogoSpecialeCount.
     * 
     * @return the size of this collection
     */
    public int getCatalogoSpecialeCount(
    ) {
        return this._catalogoSpecialeList.size();
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
     * Method iterateCatalogoSpeciale.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends it.inera.abi.logic.formatodiscambio.castor.CatalogoSpeciale> iterateCatalogoSpeciale(
    ) {
        return this._catalogoSpecialeList.iterator();
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
    public void removeAllCatalogoSpeciale(
    ) {
        this._catalogoSpecialeList.clear();
    }

    /**
     * Method removeCatalogoSpeciale.
     * 
     * @param vCatalogoSpeciale
     * @return true if the object was removed from the collection.
     */
    public boolean removeCatalogoSpeciale(
            final it.inera.abi.logic.formatodiscambio.castor.CatalogoSpeciale vCatalogoSpeciale) {
        boolean removed = _catalogoSpecialeList.remove(vCatalogoSpeciale);
        return removed;
    }

    /**
     * Method removeCatalogoSpecialeAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public it.inera.abi.logic.formatodiscambio.castor.CatalogoSpeciale removeCatalogoSpecialeAt(
            final int index) {
        java.lang.Object obj = this._catalogoSpecialeList.remove(index);
        return (it.inera.abi.logic.formatodiscambio.castor.CatalogoSpeciale) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vCatalogoSpeciale
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setCatalogoSpeciale(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.CatalogoSpeciale vCatalogoSpeciale)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._catalogoSpecialeList.size()) {
            throw new IndexOutOfBoundsException("setCatalogoSpeciale: Index value '" + index + "' not in range [0.." + (this._catalogoSpecialeList.size() - 1) + "]");
        }

        this._catalogoSpecialeList.set(index, vCatalogoSpeciale);
    }

    /**
     * 
     * 
     * @param vCatalogoSpecialeArray
     */
    public void setCatalogoSpeciale(
            final it.inera.abi.logic.formatodiscambio.castor.CatalogoSpeciale[] vCatalogoSpecialeArray) {
        //-- copy array
        _catalogoSpecialeList.clear();

        for (int i = 0; i < vCatalogoSpecialeArray.length; i++) {
                this._catalogoSpecialeList.add(vCatalogoSpecialeArray[i]);
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
     * it.inera.abi.logic.formatodiscambio.castor.CataloghiSpeciali
     */
    public static it.inera.abi.logic.formatodiscambio.castor.CataloghiSpeciali unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.CataloghiSpeciali) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.CataloghiSpeciali.class, reader);
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
