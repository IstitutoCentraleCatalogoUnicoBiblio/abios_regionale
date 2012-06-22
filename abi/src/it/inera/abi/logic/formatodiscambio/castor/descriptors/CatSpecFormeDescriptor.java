/*
 * This class was automatically generated with 
 * <a href="http://www.castor.org">Castor 1.3.1</a>, using an XML
 * Schema.
 * $Id: CatSpecFormeDescriptor.java,v 1.1 2012/06/22 13:54:50 m.bartolozzi Exp $
 */

package it.inera.abi.logic.formatodiscambio.castor.descriptors;

  //---------------------------------/
 //- Imported classes and packages -/
//---------------------------------/

import it.inera.abi.logic.formatodiscambio.castor.CatSpecForme;

/**
 * Class CatSpecFormeDescriptor.
 * 
 * @version $Revision: 1.1 $ $Date: 2012/06/22 13:54:50 $
 */
public class CatSpecFormeDescriptor extends org.exolab.castor.xml.util.XMLClassDescriptorImpl {


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

    public CatSpecFormeDescriptor() {
        super();
        _xmlName = "forme";
        _elementDefinition = true;

        //-- set grouping compositor
        setCompositorAsSequence();
        org.exolab.castor.xml.util.XMLFieldDescriptorImpl  desc           = null;
        org.exolab.castor.mapping.FieldHandler             handler        = null;
        org.exolab.castor.xml.FieldValidator               fieldValidator = null;
        //-- initialize attribute descriptors

        //-- initialize element descriptors

        //-- _catSpecFormeSchede
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(it.inera.abi.logic.formatodiscambio.castor.CatSpecFormeSchede.class, "_catSpecFormeSchede", "schede", org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            @Override
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                CatSpecForme target = (CatSpecForme) object;
                return target.getCatSpecFormeSchede();
            }
            @Override
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    CatSpecForme target = (CatSpecForme) object;
                    target.setCatSpecFormeSchede( (it.inera.abi.logic.formatodiscambio.castor.CatSpecFormeSchede) value);
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
        desc.setSchemaType("it.inera.abi.logic.formatodiscambio.castor.CatSpecFormeSchede");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);

        //-- validation code for: _catSpecFormeSchede
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
        }
        desc.setValidator(fieldValidator);
        //-- _catSpecFormeVolume
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(it.inera.abi.logic.formatodiscambio.castor.CatSpecFormeVolume.class, "_catSpecFormeVolume", "volume", org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            @Override
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                CatSpecForme target = (CatSpecForme) object;
                return target.getCatSpecFormeVolume();
            }
            @Override
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    CatSpecForme target = (CatSpecForme) object;
                    target.setCatSpecFormeVolume( (it.inera.abi.logic.formatodiscambio.castor.CatSpecFormeVolume) value);
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
        desc.setSchemaType("it.inera.abi.logic.formatodiscambio.castor.CatSpecFormeVolume");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);

        //-- validation code for: _catSpecFormeVolume
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
        }
        desc.setValidator(fieldValidator);
        //-- _catSpecFormeMicroforme
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(it.inera.abi.logic.formatodiscambio.castor.CatSpecFormeMicroforme.class, "_catSpecFormeMicroforme", "microforme", org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            @Override
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                CatSpecForme target = (CatSpecForme) object;
                return target.getCatSpecFormeMicroforme();
            }
            @Override
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    CatSpecForme target = (CatSpecForme) object;
                    target.setCatSpecFormeMicroforme( (it.inera.abi.logic.formatodiscambio.castor.CatSpecFormeMicroforme) value);
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
        desc.setSchemaType("it.inera.abi.logic.formatodiscambio.castor.CatSpecFormeMicroforme");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);

        //-- validation code for: _catSpecFormeMicroforme
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
        }
        desc.setValidator(fieldValidator);
        //-- _catSpecFormeDigitale
        desc = new org.exolab.castor.xml.util.XMLFieldDescriptorImpl(it.inera.abi.logic.formatodiscambio.castor.CatSpecFormeDigitale.class, "_catSpecFormeDigitale", "digitale", org.exolab.castor.xml.NodeType.Element);
        handler = new org.exolab.castor.xml.XMLFieldHandler() {
            @Override
            public java.lang.Object getValue( java.lang.Object object ) 
                throws IllegalStateException
            {
                CatSpecForme target = (CatSpecForme) object;
                return target.getCatSpecFormeDigitale();
            }
            @Override
            public void setValue( java.lang.Object object, java.lang.Object value) 
                throws IllegalStateException, IllegalArgumentException
            {
                try {
                    CatSpecForme target = (CatSpecForme) object;
                    target.setCatSpecFormeDigitale( (it.inera.abi.logic.formatodiscambio.castor.CatSpecFormeDigitale) value);
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
        desc.setSchemaType("it.inera.abi.logic.formatodiscambio.castor.CatSpecFormeDigitale");
        desc.setHandler(handler);
        desc.setMultivalued(false);
        addFieldDescriptor(desc);
        addSequenceElement(desc);

        //-- validation code for: _catSpecFormeDigitale
        fieldValidator = new org.exolab.castor.xml.FieldValidator();
        { //-- local scope
        }
        desc.setValidator(fieldValidator);
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
        return it.inera.abi.logic.formatodiscambio.castor.CatSpecForme.class;
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
