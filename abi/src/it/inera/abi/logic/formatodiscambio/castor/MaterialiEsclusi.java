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
 * $Id: MaterialiEsclusi.java,v 1.4 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * E' stato introdotto l'elemento ripetibile
 *  'materiali-esclusi' come contenitore
 *  degli eventuali materiali.
 *  
 * 
 * @version $Revision: 1.4 $ $Date: 2012/11/22 09:50:20 $
 */
@SuppressWarnings("serial")
public class MaterialiEsclusi implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _materialeEsclusoList.
     */
    private java.util.List<java.lang.String> _materialeEsclusoList;


      //----------------/
     //- Constructors -/
    //----------------/

    public MaterialiEsclusi() {
        super();
        this._materialeEsclusoList = new java.util.ArrayList<java.lang.String>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vMaterialeEscluso
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addMaterialeEscluso(
            final java.lang.String vMaterialeEscluso)
    throws java.lang.IndexOutOfBoundsException {
        this._materialeEsclusoList.add(vMaterialeEscluso);
    }

    /**
     * 
     * 
     * @param index
     * @param vMaterialeEscluso
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addMaterialeEscluso(
            final int index,
            final java.lang.String vMaterialeEscluso)
    throws java.lang.IndexOutOfBoundsException {
        this._materialeEsclusoList.add(index, vMaterialeEscluso);
    }

    /**
     * Method enumerateMaterialeEscluso.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends java.lang.String> enumerateMaterialeEscluso(
    ) {
        return java.util.Collections.enumeration(this._materialeEsclusoList);
    }

    /**
     * Method getMaterialeEscluso.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the java.lang.String at the given index
     */
    public java.lang.String getMaterialeEscluso(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._materialeEsclusoList.size()) {
            throw new IndexOutOfBoundsException("getMaterialeEscluso: Index value '" + index + "' not in range [0.." + (this._materialeEsclusoList.size() - 1) + "]");
        }

        return (java.lang.String) _materialeEsclusoList.get(index);
    }

    /**
     * Method getMaterialeEscluso.Returns the contents of the
     * collection in an Array.  <p>Note:  Just in case the
     * collection contents are changing in another thread, we pass
     * a 0-length Array of the correct type into the API call. 
     * This way we <i>know</i> that the Array returned is of
     * exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public java.lang.String[] getMaterialeEscluso(
    ) {
        java.lang.String[] array = new java.lang.String[0];
        return (java.lang.String[]) this._materialeEsclusoList.toArray(array);
    }

    /**
     * Method getMaterialeEsclusoCount.
     * 
     * @return the size of this collection
     */
    public int getMaterialeEsclusoCount(
    ) {
        return this._materialeEsclusoList.size();
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
     * Method iterateMaterialeEscluso.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends java.lang.String> iterateMaterialeEscluso(
    ) {
        return this._materialeEsclusoList.iterator();
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
    public void removeAllMaterialeEscluso(
    ) {
        this._materialeEsclusoList.clear();
    }

    /**
     * Method removeMaterialeEscluso.
     * 
     * @param vMaterialeEscluso
     * @return true if the object was removed from the collection.
     */
    public boolean removeMaterialeEscluso(
            final java.lang.String vMaterialeEscluso) {
        boolean removed = _materialeEsclusoList.remove(vMaterialeEscluso);
        return removed;
    }

    /**
     * Method removeMaterialeEsclusoAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public java.lang.String removeMaterialeEsclusoAt(
            final int index) {
        java.lang.Object obj = this._materialeEsclusoList.remove(index);
        return (java.lang.String) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vMaterialeEscluso
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setMaterialeEscluso(
            final int index,
            final java.lang.String vMaterialeEscluso)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._materialeEsclusoList.size()) {
            throw new IndexOutOfBoundsException("setMaterialeEscluso: Index value '" + index + "' not in range [0.." + (this._materialeEsclusoList.size() - 1) + "]");
        }

        this._materialeEsclusoList.set(index, vMaterialeEscluso);
    }

    /**
     * 
     * 
     * @param vMaterialeEsclusoArray
     */
    public void setMaterialeEscluso(
            final java.lang.String[] vMaterialeEsclusoArray) {
        //-- copy array
        _materialeEsclusoList.clear();

        for (int i = 0; i < vMaterialeEsclusoArray.length; i++) {
                this._materialeEsclusoList.add(vMaterialeEsclusoArray[i]);
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
     * it.inera.abi.logic.formatodiscambio.castor.MaterialiEsclusi
     */
    public static it.inera.abi.logic.formatodiscambio.castor.MaterialiEsclusi unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.MaterialiEsclusi) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.MaterialiEsclusi.class, reader);
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
