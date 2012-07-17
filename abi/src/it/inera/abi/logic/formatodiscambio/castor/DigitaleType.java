/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: DigitaleType.java,v 1.3 2012/07/30 15:17:05 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor;

/**
 * La forma "digitale" di un catalogo richiede
 *  l'indicazione di un supporto. Per il momento, nel caso
 *  on-line non si può indicare la URL.
 *  
 *  Si noti che comunque questo tipo è derivato da
 *  "formaType" e quindi eredita le sue caratteristiche.
 *  
 * 
 * @version $Revision: 1.3 $ $Date: 2012/07/30 15:17:05 $
 */
@SuppressWarnings("serial")
public abstract class DigitaleType extends it.inera.abi.logic.formatodiscambio.castor.FormaType 
implements java.io.Serializable
{


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _supporto.
     */
    private java.lang.String _supporto;

    /**
     * Field _urlList.
     */
    private java.util.List<java.lang.String> _urlList;


      //----------------/
     //- Constructors -/
    //----------------/

    public DigitaleType() {
        super();
        this._urlList = new java.util.ArrayList<java.lang.String>();
    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * 
     * 
     * @param vUrl
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addUrl(
            final java.lang.String vUrl)
    throws java.lang.IndexOutOfBoundsException {
        this._urlList.add(vUrl);
    }

    /**
     * 
     * 
     * @param index
     * @param vUrl
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void addUrl(
            final int index,
            final java.lang.String vUrl)
    throws java.lang.IndexOutOfBoundsException {
        this._urlList.add(index, vUrl);
    }

    /**
     * Method enumerateUrl.
     * 
     * @return an Enumeration over all possible elements of this
     * collection
     */
    public java.util.Enumeration<? extends java.lang.String> enumerateUrl(
    ) {
        return java.util.Collections.enumeration(this._urlList);
    }

    /**
     * Returns the value of field 'supporto'.
     * 
     * @return the value of field 'Supporto'.
     */
    public java.lang.String getSupporto(
    ) {
        return this._supporto;
    }

    /**
     * Method getUrl.
     * 
     * @param index
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     * @return the value of the java.lang.String at the given index
     */
    public java.lang.String getUrl(
            final int index)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._urlList.size()) {
            throw new IndexOutOfBoundsException("getUrl: Index value '" + index + "' not in range [0.." + (this._urlList.size() - 1) + "]");
        }

        return (java.lang.String) _urlList.get(index);
    }

    /**
     * Method getUrl.Returns the contents of the collection in an
     * Array.  <p>Note:  Just in case the collection contents are
     * changing in another thread, we pass a 0-length Array of the
     * correct type into the API call.  This way we <i>know</i>
     * that the Array returned is of exactly the correct length.
     * 
     * @return this collection as an Array
     */
    public java.lang.String[] getUrl(
    ) {
        java.lang.String[] array = new java.lang.String[0];
        return (java.lang.String[]) this._urlList.toArray(array);
    }

    /**
     * Method getUrlCount.
     * 
     * @return the size of this collection
     */
    public int getUrlCount(
    ) {
        return this._urlList.size();
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
     * Method iterateUrl.
     * 
     * @return an Iterator over all possible elements in this
     * collection
     */
    public java.util.Iterator<? extends java.lang.String> iterateUrl(
    ) {
        return this._urlList.iterator();
    }

    /**
     */
    public void removeAllUrl(
    ) {
        this._urlList.clear();
    }

    /**
     * Method removeUrl.
     * 
     * @param vUrl
     * @return true if the object was removed from the collection.
     */
    public boolean removeUrl(
            final java.lang.String vUrl) {
        boolean removed = _urlList.remove(vUrl);
        return removed;
    }

    /**
     * Method removeUrlAt.
     * 
     * @param index
     * @return the element removed from the collection
     */
    public java.lang.String removeUrlAt(
            final int index) {
        java.lang.Object obj = this._urlList.remove(index);
        return (java.lang.String) obj;
    }

    /**
     * Sets the value of field 'supporto'.
     * 
     * @param supporto the value of field 'supporto'.
     */
    public void setSupporto(
            final java.lang.String supporto) {
        this._supporto = supporto;
    }

    /**
     * 
     * 
     * @param index
     * @param vUrl
     * @throws java.lang.IndexOutOfBoundsException if the index
     * given is outside the bounds of the collection
     */
    public void setUrl(
            final int index,
            final java.lang.String vUrl)
    throws java.lang.IndexOutOfBoundsException {
        // check bounds for index
        if (index < 0 || index >= this._urlList.size()) {
            throw new IndexOutOfBoundsException("setUrl: Index value '" + index + "' not in range [0.." + (this._urlList.size() - 1) + "]");
        }

        this._urlList.set(index, vUrl);
    }

    /**
     * 
     * 
     * @param vUrlArray
     */
    public void setUrl(
            final java.lang.String[] vUrlArray) {
        //-- copy array
        _urlList.clear();

        for (int i = 0; i < vUrlArray.length; i++) {
                this._urlList.add(vUrlArray[i]);
        }
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
