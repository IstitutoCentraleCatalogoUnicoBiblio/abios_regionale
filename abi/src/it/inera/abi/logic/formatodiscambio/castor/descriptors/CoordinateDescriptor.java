/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: CoordinateDescriptor.java,v 1.6 2012/11/22 09:50:20 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor.descriptors;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import it.inera.abi.logic.formatodiscambio.castor.Coordinate;

/**
 * Class CoordinateDescriptor.
 * 
 * @version $Revision: 1.6 $ $Date: 2012/11/22 09:50:20 $
 */
public class CoordinateDescriptor extends org.exolab.castor.xml.util.XMLClassDescriptorImpl {


      //--------------------------/
     //- Class/Member Variables -/
    //--------------------------/

    /**
     * Field _elementDefinition.
     */
    private boolean _elementDefinition;

    /**
     * Field _nsPrefix.
     */
    private java.lang.String _nsPrefix;

    /**
     * Field _nsURI.
     */
    private java.lang.String _nsURI;

    /**
     * Field _xmlName.
     */
    private java.lang.String _xmlName;

    /**
     * Field _identity.
     */
    private org.exolab.castor.xml.XMLFieldDescriptor _identity;


      //----------------/
     //- Constructors -/
    //----------------/

    public CoordinateDescriptor() {
        super();
        _xmlName = "coordinate";
        _elementDefinition = true;
        org.exolab.castor.xml.util.XMLFieldDescriptorImpl  desc           = null;
        org.exolab.castor.mapping.FieldHandler             handler        = null;
        org.exolab.castor.xml.FieldValidator               fieldValidator = null;
        //-- initialize attribute descriptors

        //-- _latitudine
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.Double.TYPE, "_latitudine", "latitudine", org.exolab.castor.xml.NodeType.Attribute);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            @Override
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                Coordinate target = (Coordinate) object;
                if (!target.hasLatitudine()) { return null; }
                return new java.lang.Double(target.getLatitudine());
            }
            @Override
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    Coordinate target = (Coordinate) object;
                    // ignore null values for non optional primitives
                    if (value == null) { return; }

                    target.setLatitudine( ((java.lang.Double) value).doubleValue());
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            @Override
            @SuppressWarnings("unused")
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("double");
        desc.setHandler(handler);
        desc.setRequired(true);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);

        //-- validation code for: _latitudine
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        fieldValidator.setMinOccurs(1);
        { //-- local scope
            org.exolab.castor.xml.validators.DoubleValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.DoubleValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setMinInclusive(-1.7976931348623157E308);
            typeValidator.setMaxInclusive(1.7976931348623157E308);
        }
        desc.setValidator(fieldValidator);
        //-- _longitudine
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(java.lang.Double.TYPE, "_longitudine", "longitudine", org.exolab.castor.xml.NodeType.Attribute);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            @Override
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                Coordinate target = (Coordinate) object;
                if (!target.hasLongitudine()) { return null; }
                return new java.lang.Double(target.getLongitudine());
            }
            @Override
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    Coordinate target = (Coordinate) object;
                    // ignore null values for non optional primitives
                    if (value == null) { return; }

                    target.setLongitudine( ((java.lang.Double) value).doubleValue());
                } catch (java.lang.Exception ex) {
                    throw new IllegalStateException(ex.toString());
                }
            }
            @Override
            @SuppressWarnings("unused")
            public java.lang.Object newInstance(java.lang.Object parent) {
                return null;
            }
        };
        desc.setSchemaType("double");
        desc.setHandler(handler);
        desc.setRequired(true);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);

        //-- validation code for: _longitudine
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        fieldValidator.setMinOccurs(1);
        { //-- local scope
            org.exolab.castor.xml.validators.DoubleValidator typeValidator;
            typeValidator = new org.exolab.castor.xml.validators.DoubleValidator();
            fieldValidator.setValidator(typeValidator);
            typeValidator.setMinInclusive(-1.7976931348623157E308);
            typeValidator.setMaxInclusive(1.7976931348623157E308);
        }
        desc.setValidator(fieldValidator);
        //-- initialize element descriptors

    }


      //-----------/
     //- Methods -/
    //-----------/

    /**
     * Method getAccessMode.
     * 
     * @return the access mode specified for this class.
     */
    @Override()
    public org.exolab.castor.mapping.AccessMode getAccessMode(
    ) {
        return null;
    }

    /**
     * Method getIdentity.
     * 
     * @return the identity field, null if this class has no
     * identity.
     */
    @Override()
    public org.exolab.castor.mapping.FieldDescriptor getIdentity(
    ) {
        return _identity;
    }

    /**
     * Method getJavaClass.
     * 
     * @return the Java class represented by this descriptor.
     */
    @Override()
    public java.lang.Class getJavaClass(
    ) {
        return it.inera.abi.logic.formatodiscambio.castor.Coordinate.class;
    }

    /**
     * Method getNameSpacePrefix.
     * 
     * @return the namespace prefix to use when marshaling as XML.
     */
    @Override()
    public java.lang.String getNameSpacePrefix(
    ) {
        return _nsPrefix;
    }

    /**
     * Method getNameSpaceURI.
     * 
     * @return the namespace URI used when marshaling and
     * unmarshaling as XML.
     */
    @Override()
    public java.lang.String getNameSpaceURI(
    ) {
        return _nsURI;
    }

    /**
     * Method getValidator.
     * 
     * @return a specific validator for the class described by this
     * ClassDescriptor.
     */
    @Override()
    public org.exolab.castor.xml.TypeValidator getValidator(
    ) {
        return this;
    }

    /**
     * Method getXMLName.
     * 
     * @return the XML Name for the Class being described.
     */
    @Override()
    public java.lang.String getXMLName(
    ) {
        return _xmlName;
    }

    /**
     * Method isElementDefinition.
     * 
     * @return true if XML schema definition of this Class is that
     * of a global
     * element or element with anonymous type definition.
     */
    public boolean isElementDefinition(
    ) {
        return _elementDefinition;
    }

}
