package org.ranjan.sql;

/**
  * @author Amit Kirdatt
  * @date 06/08/2002
  */

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.util.ArrayList;

import org.ranjan.sql.RanjanResultSetException;

/**
  * An object which gets the ResultSet MetaData from the ResultSet
  */
public class RanjanResultSetMetaData
{
 
  /**
    * Get the column names in an ArrayList
    *
    * @param ResultSet aResultSet - The ResultSet for which we wanna get the
    *                               column names for.
    *
    * @return ArrayList t_ArrayList - Column names in an ArrayList
    *
    */
  public ArrayList<String> getColumnNames(ResultSet aResultSet) 
                          throws RanjanResultSetException
  {
     //ArrayList to hold the column names.
    //The position of the column name in the ArrayList
    //corresponds to the position in the ResultSet
    ArrayList<String> t_ArrayList = new ArrayList<String>();

    ResultSetMetaData t_ResultSetMetaData = null;
    int numCols = 0;
    try
    {
      t_ResultSetMetaData = aResultSet.getMetaData();
      numCols = t_ResultSetMetaData.getColumnCount();
    
      for(int i = 1; i <= numCols; i++)
      {
        t_ArrayList.add(t_ResultSetMetaData.getColumnName(i));
      }
    }
    catch(SQLException sqlex)
    {
      throw new RanjanResultSetException(sqlex.getMessage());
    }
    return t_ArrayList;  
  }
}
