/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.0.1</a>, using an XML
 * Schema.
 * $Id: MarkerType.java,v 1.1 2012/06/22 13:55:12 m.bartolozzi Exp $
 */

package it.inera.gmap.markersgenereator.castor;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import java.io.IOException;
import java.io.Reader;
import java.io.Serializable;
import java.io.Writer;
import org.exolab.castor.xml.MarshalException;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.exolab.castor.xml.ValidationException;
import org.xml.sax.ContentHandler;

/**
 * Class MarkerType.
 * 
 * @version $Revision: 1.1 $ $Date: 2012/06/22 13:55:12 $
 */
public class MarkerType implements java.io.Serializable {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _id_bib
     */
    private java.lang.String _id_bib;

    /**
     * Field _isil_pr
     */
    private java.lang.String _isil_pr;

    /**
     * Field _isil_nr
     */
    private java.lang.String _isil_nr;

    /**
     * Field _denominazione
     */
    private java.lang.String _denominazione;

    /**
     * Field _indirizzo
     */
    private java.lang.String _indirizzo;

    /**
     * Field _cap
     */
    private java.lang.String _cap;

    /**
     * Field _comune
     */
    private java.lang.String _comune;

    /**
     * Field _id_com
     */
    private java.lang.String _id_com;

    /**
     * Field _provincia
     */
    private java.lang.String _provincia;

    /**
     * Field _id_pro
     */
    private java.lang.String _id_pro;

    /**
     * Field _regione
     */
    private java.lang.String _regione;

    /**
     * Field _id_reg
     */
    private java.lang.String _id_reg;

    /**
     * Field _stato
     */
    private java.lang.String _stato;

    /**
     * Field _lat
     */
    private java.lang.String _lat;

    /**
     * Field _lng
     */
    private java.lang.String _lng;


      //----------------/
     //- Constructors -/
    //----------------/

    public MarkerType() 
     {
        super();
    } //-- it.inera.gmap.markersgenereator.castor.MarkerType()


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Returns the value of field 'cap'.
     * 
     * @return String
     * @return the value of field 'cap'.
     */
    public java.lang.String getCap()
    {
        return this._cap;
    } //-- java.lang.String getCap() 

    /**
     * Returns the value of field 'comune'.
     * 
     * @return String
     * @return the value of field 'comune'.
     */
    public java.lang.String getComune()
    {
        return this._comune;
    } //-- java.lang.String getComune() 

    /**
     * Returns the value of field 'denominazione'.
     * 
     * @return String
     * @return the value of field 'denominazione'.
     */
    public java.lang.String getDenominazione()
    {
        return this._denominazione;
    } //-- java.lang.String getDenominazione() 

    /**
     * Returns the value of field 'id_bib'.
     * 
     * @return String
     * @return the value of field 'id_bib'.
     */
    public java.lang.String getId_bib()
    {
        return this._id_bib;
    } //-- java.lang.String getId_bib() 

    /**
     * Returns the value of field 'id_com'.
     * 
     * @return String
     * @return the value of field 'id_com'.
     */
    public java.lang.String getId_com()
    {
        return this._id_com;
    } //-- java.lang.String getId_com() 

    /**
     * Returns the value of field 'id_pro'.
     * 
     * @return String
     * @return the value of field 'id_pro'.
     */
    public java.lang.String getId_pro()
    {
        return this._id_pro;
    } //-- java.lang.String getId_pro() 

    /**
     * Returns the value of field 'id_reg'.
     * 
     * @return String
     * @return the value of field 'id_reg'.
     */
    public java.lang.String getId_reg()
    {
        return this._id_reg;
    } //-- java.lang.String getId_reg() 

    /**
     * Returns the value of field 'indirizzo'.
     * 
     * @return String
     * @return the value of field 'indirizzo'.
     */
    public java.lang.String getIndirizzo()
    {
        return this._indirizzo;
    } //-- java.lang.String getIndirizzo() 

    /**
     * Returns the value of field 'isil_nr'.
     * 
     * @return String
     * @return the value of field 'isil_nr'.
     */
    public java.lang.String getIsil_nr()
    {
        return this._isil_nr;
    } //-- java.lang.String getIsil_nr() 

    /**
     * Returns the value of field 'isil_pr'.
     * 
     * @return String
     * @return the value of field 'isil_pr'.
     */
    public java.lang.String getIsil_pr()
    {
        return this._isil_pr;
    } //-- java.lang.String getIsil_pr() 

    /**
     * Returns the value of field 'lat'.
     * 
     * @return String
     * @return the value of field 'lat'.
     */
    public java.lang.String getLat()
    {
        return this._lat;
    } //-- java.lang.String getLat() 

    /**
     * Returns the value of field 'lng'.
     * 
     * @return String
     * @return the value of field 'lng'.
     */
    public java.lang.String getLng()
    {
        return this._lng;
    } //-- java.lang.String getLng() 

    /**
     * Returns the value of field 'provincia'.
     * 
     * @return String
     * @return the value of field 'provincia'.
     */
    public java.lang.String getProvincia()
    {
        return this._provincia;
    } //-- java.lang.String getProvincia() 

    /**
     * Returns the value of field 'regione'.
     * 
     * @return String
     * @return the value of field 'regione'.
     */
    public java.lang.String getRegione()
    {
        return this._regione;
    } //-- java.lang.String getRegione() 

    /**
     * Returns the value of field 'stato'.
     * 
     * @return String
     * @return the value of field 'stato'.
     */
    public java.lang.String getStato()
    {
        return this._stato;
    } //-- java.lang.String getStato() 

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
     * Sets the value of field 'cap'.
     * 
     * @param cap the value of field 'cap'.
     */
    public void setCap(java.lang.String cap)
    {
        this._cap = cap;
    } //-- void setCap(java.lang.String) 

    /**
     * Sets the value of field 'comune'.
     * 
     * @param comune the value of field 'comune'.
     */
    public void setComune(java.lang.String comune)
    {
        this._comune = comune;
    } //-- void setComune(java.lang.String) 

    /**
     * Sets the value of field 'denominazione'.
     * 
     * @param denominazione the value of field 'denominazione'.
     */
    public void setDenominazione(java.lang.String denominazione)
    {
        this._denominazione = denominazione;
    } //-- void setDenominazione(java.lang.String) 

    /**
     * Sets the value of field 'id_bib'.
     * 
     * @param id_bib the value of field 'id_bib'.
     */
    public void setId_bib(java.lang.String id_bib)
    {
        this._id_bib = id_bib;
    } //-- void setId_bib(java.lang.String) 

    /**
     * Sets the value of field 'id_com'.
     * 
     * @param id_com the value of field 'id_com'.
     */
    public void setId_com(java.lang.String id_com)
    {
        this._id_com = id_com;
    } //-- void setId_com(java.lang.String) 

    /**
     * Sets the value of field 'id_pro'.
     * 
     * @param id_pro the value of field 'id_pro'.
     */
    public void setId_pro(java.lang.String id_pro)
    {
        this._id_pro = id_pro;
    } //-- void setId_pro(java.lang.String) 

    /**
     * Sets the value of field 'id_reg'.
     * 
     * @param id_reg the value of field 'id_reg'.
     */
    public void setId_reg(java.lang.String id_reg)
    {
        this._id_reg = id_reg;
    } //-- void setId_reg(java.lang.String) 

    /**
     * Sets the value of field 'indirizzo'.
     * 
     * @param indirizzo the value of field 'indirizzo'.
     */
    public void setIndirizzo(java.lang.String indirizzo)
    {
        this._indirizzo = indirizzo;
    } //-- void setIndirizzo(java.lang.String) 

    /**
     * Sets the value of field 'isil_nr'.
     * 
     * @param isil_nr the value of field 'isil_nr'.
     */
    public void setIsil_nr(java.lang.String isil_nr)
    {
        this._isil_nr = isil_nr;
    } //-- void setIsil_nr(java.lang.String) 

    /**
     * Sets the value of field 'isil_pr'.
     * 
     * @param isil_pr the value of field 'isil_pr'.
     */
    public void setIsil_pr(java.lang.String isil_pr)
    {
        this._isil_pr = isil_pr;
    } //-- void setIsil_pr(java.lang.String) 

    /**
     * Sets the value of field 'lat'.
     * 
     * @param lat the value of field 'lat'.
     */
    public void setLat(java.lang.String lat)
    {
        this._lat = lat;
    } //-- void setLat(java.lang.String) 

    /**
     * Sets the value of field 'lng'.
     * 
     * @param lng the value of field 'lng'.
     */
    public void setLng(java.lang.String lng)
    {
        this._lng = lng;
    } //-- void setLng(java.lang.String) 

    /**
     * Sets the value of field 'provincia'.
     * 
     * @param provincia the value of field 'provincia'.
     */
    public void setProvincia(java.lang.String provincia)
    {
        this._provincia = provincia;
    } //-- void setProvincia(java.lang.String) 

    /**
     * Sets the value of field 'regione'.
     * 
     * @param regione the value of field 'regione'.
     */
    public void setRegione(java.lang.String regione)
    {
        this._regione = regione;
    } //-- void setRegione(java.lang.String) 

    /**
     * Sets the value of field 'stato'.
     * 
     * @param stato the value of field 'stato'.
     */
    public void setStato(java.lang.String stato)
    {
        this._stato = stato;
    } //-- void setStato(java.lang.String) 

    /**
     * Method unmarshal
     * 
     * 
     * 
     * @param reader
     * @return MarkerType
     */
    public static it.inera.gmap.markersgenereator.castor.MarkerType unmarshal(java.io.Reader reader)
        throws org.exolab.castor.xml.MarshalException, org.exolab.castor.xml.ValidationException
    {
        return (it.inera.gmap.markersgenereator.castor.MarkerType) Unmarshaller.unmarshal(it.inera.gmap.markersgenereator.castor.MarkerType.class, reader);
    } //-- it.inera.gmap.markersgenereator.castor.MarkerType unmarshal(java.io.Reader) 

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
