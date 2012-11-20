package org.ranjan.xml;

/**
 * Signals that an error occured while 
 * building XML 
 */
public class RanjanXMLResultSetException extends Exception
{
	private static final long serialVersionUID = 1L;

/**
    * Default Error Message
    */
  public RanjanXMLResultSetException()
  {
    super("An error occured");
  }
  
  /**
    * An Exception with 
    *
    * @param message - The message to 
    */
  public RanjanXMLResultSetException(String message)
  {
    super(message);
  }
}
