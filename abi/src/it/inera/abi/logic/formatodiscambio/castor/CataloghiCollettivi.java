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
 * $Id: CataloghiCollettivi.java,v 1.4 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * E' stato introdotto l'elemento ripetibile
 *  'cataloghi-collettivi' come contenitore
 *  degli eventuali cataloghi collettivi.
 *  
 * 
 * @version $Revision: 1.4 $ $Date: 2012/11/22 09:50:20 $
 */
@SuppressWarnings("serial")
public class CataloghiCollettivi implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _catalogoCollettivoList.
     */
    private java.util.List<it.inera.abi.logic.formatodiscambio.castor.CatalogoCollettivo> _catalogoCollettivoList;


      //----------------/
     //- Constructors -/
    //----------------/

    public CataloghiCollettivi() {
        super();
        this._catalogoCollettivoList = new java.util.ArrayList<it.inera.abi.logic.formatodiscambio.castor.CatalogoCollettivo>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vCatalogoCollettivo
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addCatalogoCollettivo(
            final it.inera.abi.logic.formatodiscambio.castor.CatalogoCollettivo vCatalogoCollettivo)
    throws java.lang.IndexOutOfBoundsException {
        this._catalogoCollettivoList.add(vCatalogoCollettivo);
    }

    /**
     * 
     * 
     * @param index
     * @param vCatalogoCollettivo
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addCatalogoCollettivo(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.CatalogoCollettivo vCatalogoCollettivo)
    throws java.lang.IndexOutOfBoundsException {
        this._catalogoCollettivoList.add(index, vCatalogoCollettivo);
    }

    /**
     * Method enumerateCatalogoCollettivo.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends it.inera.abi.logic.formatodiscambio.castor.CatalogoCollettivo> enumerateCatalogoCollettivo(
    ) {
        return java.util.Collections.enumeration(this._catalogoCollettivoList);
    }

    /**
     * Method getCatalogoCollettivo.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * it.inera.abi.logic.formatodiscambio.castor.CatalogoCollettivo
     * at the given index
     */
    public it.inera.abi.logic.formatodiscambio.castor.CatalogoCollettivo getCatalogoCollettivo(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._catalogoCollettivoList.size()) {
            throw new IndexOutOfBoundsException("getCatalogoCollettivo: Index value '" + index + "' not in range [0.." + (this._catalogoCollettivoList.size() - 1) + "]");
        }

        return (it.inera.abi.logic.formatodiscambio.castor.CatalogoCollettivo) _catalogoCollettivoList.get(index);
    }

    /**
     * Method getCatalogoCollettivo.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public it.inera.abi.logic.formatodiscambio.castor.CatalogoCollettivo[] getCatalogoCollettivo(
    ) {
        it.inera.abi.logic.formatodiscambio.castor.CatalogoCollettivo[] array = new it.inera.abi.logic.formatodiscambio.castor.CatalogoCollettivo[0];
        return (it.inera.abi.logic.formatodiscambio.castor.CatalogoCollettivo[]) this._catalogoCollettivoList.toArray(array);
    }

    /**
     * Method getCatalogoCollettivoCount.
     * 
     * @return the size of this collection
     */
    public int getCatalogoCollettivoCount(
    ) {
        return this._catalogoCollettivoList.size();
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
     * Method iterateCatalogoCollettivo.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends it.inera.abi.logic.formatodiscambio.castor.CatalogoCollettivo> iterateCatalogoCollettivo(
    ) {
        return this._catalogoCollettivoList.iterator();
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
    public void removeAllCatalogoCollettivo(
    ) {
        this._catalogoCollettivoList.clear();
    }

    /**
     * Method removeCatalogoCollettivo.
     * 
     * @param vCatalogoCollettivo
     * @return true if the object was removed from the collection.
     */
    public boolean removeCatalogoCollettivo(
            final it.inera.abi.logic.formatodiscambio.castor.CatalogoCollettivo vCatalogoCollettivo) {
        boolean removed = _catalogoCollettivoList.remove(vCatalogoCollettivo);
        return removed;
    }

    /**
     * Method removeCatalogoCollettivoAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public it.inera.abi.logic.formatodiscambio.castor.CatalogoCollettivo removeCatalogoCollettivoAt(
            final int index) {
        java.lang.Object obj = this._catalogoCollettivoList.remove(index);
        return (it.inera.abi.logic.formatodiscambio.castor.CatalogoCollettivo) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vCatalogoCollettivo
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setCatalogoCollettivo(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.CatalogoCollettivo vCatalogoCollettivo)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._catalogoCollettivoList.size()) {
            throw new IndexOutOfBoundsException("setCatalogoCollettivo: Index value '" + index + "' not in range [0.." + (this._catalogoCollettivoList.size() - 1) + "]");
        }

        this._catalogoCollettivoList.set(index, vCatalogoCollettivo);
    }

    /**
     * 
     * 
     * @param vCatalogoCollettivoArray
     */
    public void setCatalogoCollettivo(
            final it.inera.abi.logic.formatodiscambio.castor.CatalogoCollettivo[] vCatalogoCollettivoArray) {
        //-- copy array
        _catalogoCollettivoList.clear();

        for (int i = 0; i < vCatalogoCollettivoArray.length; i++) {
                this._catalogoCollettivoList.add(vCatalogoCollettivoArray[i]);
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
     * it.inera.abi.logic.formatodiscambio.castor.CataloghiCollettiv
     */
    public static it.inera.abi.logic.formatodiscambio.castor.CataloghiCollettivi unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.CataloghiCollettivi) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.CataloghiCollettivi.class, reader);
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
