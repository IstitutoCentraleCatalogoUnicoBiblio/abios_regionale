package org.ranjan.xml;

/**
  * @author Amit Kirdatt
  * @date 06/08/2002
  */
import java.io.InputStream;
import java.io.IOException;
import java.io.StringReader;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;

import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;

import org.ranjan.sql.RanjanResultSetMetaData;
import org.ranjan.sql.RanjanResultSetException;

import org.ranjan.xml.RanjanXMLResultSetException;

/**
  * Gets data in XML format. For Example:
  * <br>
  * <br>
  * <pre>
  * &lt;?xml version="1.0"?&gt;<br>
  * &lt;ROWSET&gt;<br>
  *  &lt;ROW num="1"&gt;<br>
  *   &lt;COL1&gt;1&lt;/COL1&gt;<br>
  *   &lt;COL2&gt;amitkirdatt&lt;/COL2><br>
  *   &lt;COL4&gt;testing&lt;/COL3&gt;<br>
  *   &lt;COL5&gt;amitkirdatt@yahoo.com&lt;/COL5&gt;<br>
  * &lt;/ROW&gt;<br>
  * &lt;ROW num="2"&gt;<br>
  *  .........<br>
  *  ........<br>
  * &lt;/ROWSET&gt;<br>
  * </pre>
  */
public class RanjanXMLResultSet
{
  private boolean UPPER_CASE=true;
  private ResultSet myResultSet;
  
  private static final String LINE_SEPARATOR = System.getProperty("line.separator");
  private static final String SEPARATOR = "  ";
  
  private String errorString = new String();

  /**
    *
    * @param ResultSet aResultSet - The ResultSet to be converted
    *                               into XML
    */
  public RanjanXMLResultSet(ResultSet aResultSet)
  {
    myResultSet = aResultSet;
  }
  
  /**
    * 
    * @param Connection aConnection - A Database Connection
    * @param ResultSet aResultSet - The ResultSet to be converted
    *                               into XML
    */
  public RanjanXMLResultSet(Connection aConnection,
                            String sql)
  {
    try
    {
      PreparedStatement t_PreparedStatement = 
                         aConnection.prepareStatement(sql);
      myResultSet = t_PreparedStatement.executeQuery();
    }
    catch(SQLException sqlex)
    {
      errorString = sqlex.getMessage();
    }
  } 
    
  /**
    * Get the data as an XML String
    *
    * @return String xmlString - Data as an XMLString
    *
    * @exception RanjanXMLResultSetException - an error occured
    *                                          while building the
    *				               XML.
    */
  public String getXML() throws RanjanXMLResultSetException
  {
    if(errorString == null)
    {
      throw new RanjanXMLResultSetException(errorString);
    }
    if(myResultSet == null)
    {
      throw new RanjanXMLResultSetException("ResultSet is NULL");
    }
      
    ArrayList<String> t_ArrayList = new ArrayList<String>();
    RanjanResultSetMetaData t_MetaData = new RanjanResultSetMetaData();
    
    try
    { 
      t_ArrayList = t_MetaData.getColumnNames(myResultSet);
    }
    catch(RanjanResultSetException rrsex)
    {
      throw new RanjanXMLResultSetException(rrsex.getMessage());
    }
    
    StringBuffer t_StringBuffer = new StringBuffer();
    String columnName = new String(); 
    
    t_StringBuffer.append("<?xml version=\"1.0\"?>");
    t_StringBuffer.append(LINE_SEPARATOR);
    t_StringBuffer.append("<ROWSET>");
    
    try
    { 
      int counter = 1;
      while(myResultSet.next())
      {
        t_StringBuffer.append(LINE_SEPARATOR);
        t_StringBuffer.append(SEPARATOR);
        t_StringBuffer.append("<ROW");
        t_StringBuffer.append(" ");
        t_StringBuffer.append("num=\"");
        t_StringBuffer.append(counter);
        t_StringBuffer.append("\"");
        t_StringBuffer.append(">");

        for(int i = 0; i < t_ArrayList.size(); i++)
        {
          columnName = (String)t_ArrayList.get(i);
          String data = String.valueOf(myResultSet.getObject(columnName));

          if(UPPER_CASE)
          {
            columnName = columnName.toUpperCase();
          }
          else
          {
            columnName = columnName.toLowerCase();
          }
          
          t_StringBuffer.append(LINE_SEPARATOR);
          t_StringBuffer.append(SEPARATOR);
          t_StringBuffer.append(SEPARATOR);
          t_StringBuffer.append("<");
          t_StringBuffer.append(columnName);
          t_StringBuffer.append(">");
          t_StringBuffer.append(data);
          t_StringBuffer.append("</");
          t_StringBuffer.append(columnName);
          t_StringBuffer.append(">");
        }
        t_StringBuffer.append(LINE_SEPARATOR);
        t_StringBuffer.append(SEPARATOR);
        t_StringBuffer.append("</ROW");
        t_StringBuffer.append(">");

        counter++;
      }
    t_StringBuffer.append(LINE_SEPARATOR);
    t_StringBuffer.append("</ROWSET>");
    }
    catch(SQLException sqlex)
    {
      throw new RanjanXMLResultSetException(sqlex.getMessage());
    }
      
    return t_StringBuffer.toString();
  } 
  
  /**
    * Get the result as a org.w3c.dom.Document
    *
    * @return org.w3c.dom.Document - The xml Document
    */
  public Document getDocument() throws RanjanXMLResultSetException
  {
    String xmlString = getXML();
    StringReader t_StringReader = new StringReader(xmlString);  
    
    InputSource t_InputSource = new InputSource(t_StringReader);
    InputStream t_InputStream = t_InputSource.getByteStream();

    DocumentBuilderFactory t_Factory = DocumentBuilderFactory.newInstance();
    
    DocumentBuilder t_Builder = null;
    try
    {
      t_Builder = t_Factory.newDocumentBuilder();
    }
    catch(ParserConfigurationException pcex)
    {
      throw new RanjanXMLResultSetException(pcex.getMessage());
    }
    Document t_Document = null;
    try
    {
      t_Document = t_Builder.parse(t_InputStream);
    }
    catch(IOException ioex)
    {
      throw new RanjanXMLResultSetException(ioex.getMessage());
    }
    catch(SAXException saxex)
    {
      throw new RanjanXMLResultSetException(saxex.getMessage());
    }
     
    return t_Document; 
     
  }  
  /**
    * Sets the column Names to Lower Case. They are in upper case 
    * by default
    */
  public void setLowerCase()
  {
    UPPER_CASE = false;
  }
}
 
