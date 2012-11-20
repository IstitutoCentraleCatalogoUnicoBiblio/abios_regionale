/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: Biblioteche.java,v 1.6 2012/11/20 12:35:35 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * Class Biblioteche.
 * 
 * @version $Revision: 1.6 $ $Date: 2012/11/20 12:35:35 $
 */
@SuppressWarnings("serial")
public class Biblioteche implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Data della creazione del file XML (generato con
     *  un esport).
     *  
     */
    private java.util.Date _dataExport;

    /**
     * Fonte da cui proviene l'export delle biblioteche (se non
     * valorizzato, 
     *  sovrascrive comunque, annullandolo, il relativo campo di
     * ciascuna biblioteca)
     *  
     */
    private java.lang.String _fonte;

    /**
     * Field _bibliotecaList.
     */
    private java.util.List<it.inera.abi.logic.formatodiscambio.castor.Biblioteca> _bibliotecaList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Biblioteche() {
        super();
        this._bibliotecaList = new java.util.ArrayList<it.inera.abi.logic.formatodiscambio.castor.Biblioteca>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vBiblioteca
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addBiblioteca(
            final it.inera.abi.logic.formatodiscambio.castor.Biblioteca vBiblioteca)
    throws java.lang.IndexOutOfBoundsException {
        this._bibliotecaList.add(vBiblioteca);
    }

    /**
     * 
     * 
     * @param index
     * @param vBiblioteca
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addBiblioteca(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.Biblioteca vBiblioteca)
    throws java.lang.IndexOutOfBoundsException {
        this._bibliotecaList.add(index, vBiblioteca);
    }

    /**
     * Method enumerateBiblioteca.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends it.inera.abi.logic.formatodiscambio.castor.Biblioteca> enumerateBiblioteca(
    ) {
        return java.util.Collections.enumeration(this._bibliotecaList);
    }

    /**
     * Method getBiblioteca.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the
     * it.inera.abi.logic.formatodiscambio.castor.Biblioteca at the
     * given index
     */
    public it.inera.abi.logic.formatodiscambio.castor.Biblioteca getBiblioteca(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._bibliotecaList.size()) {
            throw new IndexOutOfBoundsException("getBiblioteca: Index value '" + index + "' not in range [0.." + (this._bibliotecaList.size() - 1) + "]");
        }

        return (it.inera.abi.logic.formatodiscambio.castor.Biblioteca) _bibliotecaList.get(index);
    }

    /**
     * Method getBiblioteca.Returns the contents of the collection
     * in an Array.  <p>Note:  Just in case the collection contents
     * are changing in another thread, we pass a 0-length Array of
     * the correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public it.inera.abi.logic.formatodiscambio.castor.Biblioteca[] getBiblioteca(
    ) {
        it.inera.abi.logic.formatodiscambio.castor.Biblioteca[] array = new it.inera.abi.logic.formatodiscambio.castor.Biblioteca[0];
        return (it.inera.abi.logic.formatodiscambio.castor.Biblioteca[]) this._bibliotecaList.toArray(array);
    }

    /**
     * Method getBibliotecaCount.
     * 
     * @return the size of this collection
     */
    public int getBibliotecaCount(
    ) {
        return this._bibliotecaList.size();
    }

    /**
     * Returns the value of field 'dataExport'. The field
     * 'dataExport' has the following description: Data della
     * creazione del file XML (generato con
     *  un esport).
     *  
     * 
     * @return the value of field 'DataExport'.
     */
    public java.util.Date getDataExport(
    ) {
        return this._dataExport;
    }

    /**
     * Returns the value of field 'fonte'. The field 'fonte' has
     * the following description: Fonte da cui proviene l'export
     * delle biblioteche (se non valorizzato, 
     *  sovrascrive comunque, annullandolo, il relativo campo di
     * ciascuna biblioteca)
     *  
     * 
     * @return the value of field 'Fonte'.
     */
    public java.lang.String getFonte(
    ) {
        return this._fonte;
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
     * Method iterateBiblioteca.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends it.inera.abi.logic.formatodiscambio.castor.Biblioteca> iterateBiblioteca(
    ) {
        return this._bibliotecaList.iterator();
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
    public void removeAllBiblioteca(
    ) {
        this._bibliotecaList.clear();
    }

    /**
     * Method removeBiblioteca.
     * 
     * @param vBiblioteca
     * @return true if the object was removed from the collection.
     */
    public boolean removeBiblioteca(
            final it.inera.abi.logic.formatodiscambio.castor.Biblioteca vBiblioteca) {
        boolean removed = _bibliotecaList.remove(vBiblioteca);
        return removed;
    }

    /**
     * Method removeBibliotecaAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public it.inera.abi.logic.formatodiscambio.castor.Biblioteca removeBibliotecaAt(
            final int index) {
        java.lang.Object obj = this._bibliotecaList.remove(index);
        return (it.inera.abi.logic.formatodiscambio.castor.Biblioteca) obj;
    }

    /**
     * 
     * 
     * @param index
     * @param vBiblioteca
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setBiblioteca(
            final int index,
            final it.inera.abi.logic.formatodiscambio.castor.Biblioteca vBiblioteca)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._bibliotecaList.size()) {
            throw new IndexOutOfBoundsException("setBiblioteca: Index value '" + index + "' not in range [0.." + (this._bibliotecaList.size() - 1) + "]");
        }

        this._bibliotecaList.set(index, vBiblioteca);
    }

    /**
     * 
     * 
     * @param vBibliotecaArray
     */
    public void setBiblioteca(
            final it.inera.abi.logic.formatodiscambio.castor.Biblioteca[] vBibliotecaArray) {
        //-- copy array
        _bibliotecaList.clear();

        for (int i = 0; i < vBibliotecaArray.length; i++) {
                this._bibliotecaList.add(vBibliotecaArray[i]);
        }
    }

    /**
     * Sets the value of field 'dataExport'. The field 'dataExport'
     * has the following description: Data della creazione del file
     * XML (generato con
     *  un esport).
     *  
     * 
     * @param dataExport the value of field 'dataExport'.
     */
    public void setDataExport(
            final java.util.Date dataExport) {
        this._dataExport = dataExport;
    }

    /**
     * Sets the value of field 'fonte'. The field 'fonte' has the
     * following description: Fonte da cui proviene l'export delle
     * biblioteche (se non valorizzato, 
     *  sovrascrive comunque, annullandolo, il relativo campo di
     * ciascuna biblioteca)
     *  
     * 
     * @param fonte the value of field 'fonte'.
     */
    public void setFonte(
            final java.lang.String fonte) {
        this._fonte = fonte;
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
     * it.inera.abi.logic.formatodiscambio.castor.Biblioteche
     */
    public static it.inera.abi.logic.formatodiscambio.castor.Biblioteche unmarshal(
            final java.io.Reader reader)
    throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException {
        return (it.inera.abi.logic.formatodiscambio.castor.Biblioteche) org.exolab.castor.xml.Unmarshaller.unmarshal(it.inera.abi.logic.formatodiscambio.castor.Biblioteche.class, reader);
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
