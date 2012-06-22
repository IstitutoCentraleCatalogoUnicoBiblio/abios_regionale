/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id: Markers.java,v 1.1 2012/06/22 13:55:12 m.bartolozzi Exp $
 */

package it.inera.gmap.markersgenereator.castor;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Enumeration;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.ContentHandler;

/**
 * Class Markers.
 * 
 * @version $Revision: 1.1 $ $Date: 2012/06/22 13:55:12 $
 */
public class Markers implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _total
     */
    private int _total;

    /**
     * keeps track of state for field: _total
     */
    private boolean _has_total;

    /**
     * Field _from
     */
    private int _from;

    /**
     * keeps track of state for field: _from
     */
    private boolean _has_from;

    /**
     * Field _nentries
     */
    private int _nentries;

    /**
     * keeps track of state for field: _nentries
     */
    private boolean _has_nentries;

    /**
     * Field _paramsList
     */
    private java.util.ArrayList _paramsList;

    /**
     * Field _markerList
     */
    private java.util.ArrayList _markerList;


      //----------------/
     //- Constructors -/
    //----------------/

    public Markers() 
     {
        super();
        _paramsList = new ArrayList();
        _markerList = new ArrayList();
    } //-- it.inera.gmap.markersgenereator.castor.Markers()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method addMarker
     * 
     * 
     * 
     * @param vMarker
     */
    public void addMarker(it.inera.gmap.markersgenereator.castor.Marker vMarker)
        throws java.lang.IndexOutOfBoundsException
    {
        _markerList.add(vMarker);
    } //-- void addMarker(it.inera.gmap.markersgenereator.castor.Marker) 

    /**
     * Method addMarker
     * 
     * 
     * 
     * @param index
     * @param vMarker
     */
    public void addMarker(int index, it.inera.gmap.markersgenereator.castor.Marker vMarker)
        throws java.lang.IndexOutOfBoundsException
    {
        _markerList.add(index, vMarker);
    } //-- void addMarker(int, it.inera.gmap.markersgenereator.castor.Marker) 

    /**
     * Method addParams
     * 
     * 
     * 
     * @param vParams
     */
    public void addParams(it.inera.gmap.markersgenereator.castor.Params vParams)
        throws java.lang.IndexOutOfBoundsException
    {
        _paramsList.add(vParams);
    } //-- void addParams(it.inera.gmap.markersgenereator.castor.Params) 

    /**
     * Method addParams
     * 
     * 
     * 
     * @param index
     * @param vParams
     */
    public void addParams(int index, it.inera.gmap.markersgenereator.castor.Params vParams)
        throws java.lang.IndexOutOfBoundsException
    {
        _paramsList.add(index, vParams);
    } //-- void addParams(int, it.inera.gmap.markersgenereator.castor.Params) 

    /**
     * Method clearMarker
     * 
     */
    public void clearMarker()
    {
        _markerList.clear();
    } //-- void clearMarker() 

    /**
     * Method clearParams
     * 
     */
    public void clearParams()
    {
        _paramsList.clear();
    } //-- void clearParams() 

    /**
     * Method deleteFrom
     * 
     */
    public void deleteFrom()
    {
        this._has_from= false;
    } //-- void deleteFrom() 

    /**
     * Method deleteNentries
     * 
     */
    public void deleteNentries()
    {
        this._has_nentries= false;
    } //-- void deleteNentries() 

    /**
     * Method deleteTotal
     * 
     */
    public void deleteTotal()
    {
        this._has_total= false;
    } //-- void deleteTotal() 

    /**
     * Method enumerateMarker
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateMarker()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_markerList.iterator());
    } //-- java.util.Enumeration enumerateMarker() 

    /**
     * Method enumerateParams
     * 
     * 
     * 
     * @return Enumeration
     */
    public java.util.Enumeration enumerateParams()
    {
        return new org.exolab.castor.util.IteratorEnumeration(_paramsList.iterator());
    } //-- java.util.Enumeration enumerateParams() 

    /**
     * Returns the value of field 'from'.
     * 
     * @return int
     * @return the value of field 'from'.
     */
    public int getFrom()
    {
        return this._from;
    } //-- int getFrom() 

    /**
     * Method getMarker
     * 
     * 
     * 
     * @param index
     * @return Marker
     */
    public it.inera.gmap.markersgenereator.castor.Marker getMarker(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _markerList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (it.inera.gmap.markersgenereator.castor.Marker) _markerList.get(index);
    } //-- it.inera.gmap.markersgenereator.castor.Marker getMarker(int) 

    /**
     * Method getMarker
     * 
     * 
     * 
     * @return Marker
     */
    public it.inera.gmap.markersgenereator.castor.Marker[] getMarker()
    {
        int size = _markerList.size();
        it.inera.gmap.markersgenereator.castor.Marker[] mArray = new it.inera.gmap.markersgenereator.castor.Marker[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (it.inera.gmap.markersgenereator.castor.Marker) _markerList.get(index);
        }
        return mArray;
    } //-- it.inera.gmap.markersgenereator.castor.Marker[] getMarker() 

    /**
     * Method getMarkerCount
     * 
     * 
     * 
     * @return int
     */
    public int getMarkerCount()
    {
        return _markerList.size();
    } //-- int getMarkerCount() 

    /**
     * Returns the value of field 'nentries'.
     * 
     * @return int
     * @return the value of field 'nentries'.
     */
    public int getNentries()
    {
        return this._nentries;
    } //-- int getNentries() 

    /**
     * Method getParams
     * 
     * 
     * 
     * @param index
     * @return Params
     */
    public it.inera.gmap.markersgenereator.castor.Params getParams(int index)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _paramsList.size())) {
            throw new IndexOutOfBoundsException();
        }
        
        return (it.inera.gmap.markersgenereator.castor.Params) _paramsList.get(index);
    } //-- it.inera.gmap.markersgenereator.castor.Params getParams(int) 

    /**
     * Method getParams
     * 
     * 
     * 
     * @return Params
     */
    public it.inera.gmap.markersgenereator.castor.Params[] getParams()
    {
        int size = _paramsList.size();
        it.inera.gmap.markersgenereator.castor.Params[] mArray = new it.inera.gmap.markersgenereator.castor.Params[size];
        for (int index = 0; index < size; index++) {
            mArray[index] = (it.inera.gmap.markersgenereator.castor.Params) _paramsList.get(index);
        }
        return mArray;
    } //-- it.inera.gmap.markersgenereator.castor.Params[] getParams() 

    /**
     * Method getParamsCount
     * 
     * 
     * 
     * @return int
     */
    public int getParamsCount()
    {
        return _paramsList.size();
    } //-- int getParamsCount() 

    /**
     * Returns the value of field 'total'.
     * 
     * @return int
     * @return the value of field 'total'.
     */
    public int getTotal()
    {
        return this._total;
    } //-- int getTotal() 

    /**
     * Method hasFrom
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasFrom()
    {
        return this._has_from;
    } //-- boolean hasFrom() 

    /**
     * Method hasNentries
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasNentries()
    {
        return this._has_nentries;
    } //-- boolean hasNentries() 

    /**
     * Method hasTotal
     * 
     * 
     * 
     * @return boolean
     */
    public boolean hasTotal()
    {
        return this._has_total;
    } //-- boolean hasTotal() 

    /**
     * Method isValid
     * 
     * 
     * 
     * @return boolean
     */
    public boolean isValid()
    {
        try {
            validate();
        }
        catch (org.exolab.castor.xml.ValidationException vex) {
            return false;
        }
        return true;
    } //-- boolean isValid() 

    /**
     * Method marshal
     * 
     * 
     * 
     * @param out
     */
    public void marshal(java.io.Writer out)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, out);
    } //-- void marshal(java.io.Writer) 

    /**
     * Method marshal
     * 
     * 
     * 
     * @param handler
     */
    public void marshal(org.xml.sax.ContentHandler handler)
        throws java.io.IOException, org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        
        Marshaller.marshal(this, handler);
    } //-- void marshal(org.xml.sax.ContentHandler) 

    /**
     * Method removeMarker
     * 
     * 
     * 
     * @param vMarker
     * @return boolean
     */
    public boolean removeMarker(it.inera.gmap.markersgenereator.castor.Marker vMarker)
    {
        boolean removed = _markerList.remove(vMarker);
        return removed;
    } //-- boolean removeMarker(it.inera.gmap.markersgenereator.castor.Marker) 

    /**
     * Method removeParams
     * 
     * 
     * 
     * @param vParams
     * @return boolean
     */
    public boolean removeParams(it.inera.gmap.markersgenereator.castor.Params vParams)
    {
        boolean removed = _paramsList.remove(vParams);
        return removed;
    } //-- boolean removeParams(it.inera.gmap.markersgenereator.castor.Params) 

    /**
     * Sets the value of field 'from'.
     * 
     * @param from the value of field 'from'.
     */
    public void setFrom(int from)
    {
        this._from = from;
        this._has_from = true;
    } //-- void setFrom(int) 

    /**
     * Method setMarker
     * 
     * 
     * 
     * @param index
     * @param vMarker
     */
    public void setMarker(int index, it.inera.gmap.markersgenereator.castor.Marker vMarker)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _markerList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _markerList.set(index, vMarker);
    } //-- void setMarker(int, it.inera.gmap.markersgenereator.castor.Marker) 

    /**
     * Method setMarker
     * 
     * 
     * 
     * @param markerArray
     */
    public void setMarker(it.inera.gmap.markersgenereator.castor.Marker[] markerArray)
    {
        //-- copy array
        _markerList.clear();
        for (int i = 0; i < markerArray.length; i++) {
            _markerList.add(markerArray[i]);
        }
    } //-- void setMarker(it.inera.gmap.markersgenereator.castor.Marker) 

    /**
     * Sets the value of field 'nentries'.
     * 
     * @param nentries the value of field 'nentries'.
     */
    public void setNentries(int nentries)
    {
        this._nentries = nentries;
        this._has_nentries = true;
    } //-- void setNentries(int) 

    /**
     * Method setParams
     * 
     * 
     * 
     * @param index
     * @param vParams
     */
    public void setParams(int index, it.inera.gmap.markersgenereator.castor.Params vParams)
        throws java.lang.IndexOutOfBoundsException
    {
        //-- check bounds for index
        if ((index < 0) || (index >= _paramsList.size())) {
            throw new IndexOutOfBoundsException();
        }
        _paramsList.set(index, vParams);
    } //-- void setParams(int, it.inera.gmap.markersgenereator.castor.Params) 

    /**
     * Method setParams
     * 
     * 
     * 
     * @param paramsArray
     */
    public void setParams(it.inera.gmap.markersgenereator.castor.Params[] paramsArray)
    {
        //-- copy array
        _paramsList.clear();
        for (int i = 0; i < paramsArray.length; i++) {
            _paramsList.add(paramsArray[i]);
        }
    } //-- void setParams(it.inera.gmap.markersgenereator.castor.Params) 

    /**
     * Sets the value of field 'total'.
     * 
     * @param total the value of field 'total'.
     */
    public void setTotal(int total)
    {
        this._total = total;
        this._has_total = true;
    } //-- void setTotal(int) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return Markers
     */
    public static it.inera.gmap.markersgenereator.castor.Markers unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (it.inera.gmap.markersgenereator.castor.Markers) Unmarshaller.unmarshal(it.inera.gmap.markersgenereator.castor.Markers.class, reader);
    } //-- it.inera.gmap.markersgenereator.castor.Markers unmarshal(java.io.Reader) 

    /**
     * Method validate
     * 
     */
    public void validate()
        throws org.exolab.castor.xml.ValidationException
    {
        org.exolab.castor.xml.Validator validator = new org.exolab.castor.xml.Validator();
        validator.validate(this);
    } //-- void validate() 

}
