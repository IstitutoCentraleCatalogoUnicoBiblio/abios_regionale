package org.ranjan.sql;

/*
 * @author Amit Kirdatt
 * @date 06/08/2002
 */


/*
 * Signals that an error occured while 
 * proccessing Database related data 
 */
public class RanjanResultSetException extends Exception
{
	private static final long serialVersionUID = 1L;

/**
    * Default Error Message
    */
  public RanjanResultSetException()
  {
    super("An error occured");
  }
  
  /**
    * An Exception with 
    *
    * @param String message - The message to 
    */
  public RanjanResultSetException(String message)
  {
    super(message);
  }
}
