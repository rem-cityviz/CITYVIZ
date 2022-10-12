package routines;

import java.util.Date;

/*
 * user specification: the function's comment should contain keys as follows: 1. write about the function's comment.but
 * it must be before the "{talendTypes}" key.
 * 
 * 2. {talendTypes} 's value must be talend Type, it is required . its value should be one of: String, char | Character,
 * long | Long, int | Integer, boolean | Boolean, byte | Byte, Date, double | Double, float | Float, Object, short |
 * Short
 * 
 * 3. {Category} define a category for the Function. it is required. its value is user-defined .
 * 
 * 4. {param} 's format is: {param} <type>[(<default value or closed list values>)] <name>[ : <comment>]
 * 
 * <type> 's value should be one of: string, int, list, double, object, boolean, long, char, date. <name>'s value is the
 * Function's parameter name. the {param} is optional. so if you the Function without the parameters. the {param} don't
 * added. you can have many parameters for the Function.
 * 
 * 5. {example} gives a example for the Function. it is optional.
 */
public class DataProcessing {

    /**
     * checkForNull: return true if the value is not null, else return false
     * 
     * 
     * {talendTypes} boolean
     * 
     * {Category} User Defined
     * 
     * {param} any field
     * 
     * {example} blank.
     */
    public static boolean checkForNull(String currentField) {
    	boolean res = false;
    	
    	if(currentField == null) {
    		res = false;
    		//System.out.println("NULL VERIFIED : " + currentField + " / isNullValue : " + res); 
    	}
    	else if (currentField != null) {
    		res = true;
    		//System.out.println("NOT NULL : " + currentField + " / isNullValue : " + res); 
    	}
        
        
        return res;
    }
    
    /**
     * replaceNullString: return true if the value is not null, else return false
     * 
     * 
     * {talendTypes} boolean
     * 
     * {Category} User Defined
     * 
     * {param} any field
     * 
     * {example} blank.
     */
    public static String replaceNullString(String valueToReplace) {
    	String res = "";
    	
    	if(valueToReplace == null) res = "null";
    	else res = valueToReplace;
        
        
        return res;
    }
    
    /**
     * replaceNullString: return true if the value is not null, else return false
     * 
     * 
     * {talendTypes} TalendDate
     * 
     * {Category} User Defined
     * 
     * {param} any field
     * 
     * {example} blank.
     */
    public static Date replaceNullDate(Date valueToReplace) {
    	    	
    	if(valueToReplace == null) {
    		System.out.println(TalendDate.parseDate("yyyy-DD-mm", "0001-01-01"));
    		return TalendDate.parseDate("yyyy-DD-mm", "0001-01-01");
    	}
    	else {
    		return valueToReplace;
    	}
        
    }
    
    
    /**
     * formatString: format string
     * 
     * 
     * {talendTypes} String
     * 
     * {Category} User Defined
     * 
     * {param} any field
     * 
     * {example} blank.
     */
    public static String formatString(String valueToFormat) {
    	
    	String res = "";
    	res = valueToFormat.replaceAll("[^a-zA-Z0-9]", " ");  
    	//System.out.println(res);  
    	
    	return res;
    }
}
