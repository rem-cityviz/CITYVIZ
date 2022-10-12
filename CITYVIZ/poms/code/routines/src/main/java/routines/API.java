package routines;

import java.util.ArrayList;
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
public class API {
	
	static ArrayList<String> wrongBoolean=new ArrayList<String>();
	

    /**
     * buildURL: return the API Url with the parameters from the config_API.csv file
     * 
     * 
     * {talendTypes} String dataset, String q, int rows, 
     * 
     * {Category} API 
     * 
     * {param} string("world") input: The string need to be printed.
     * 
     * {example} "https://data.economie.gouv.fr/api/records/1.0/search/?dataset=decp_augmente"
     */
    public static String buildURL(String dataset, String q, String rows, Integer start, String lang, String geoFilter_long, String geoFilter_lat, String distance) {
    	
    	String url = "https://data.economie.gouv.fr/api/records/1.0/search/?dataset=";
    	
        url += dataset; 
        if(q != null) url += "&" + "q=" + q;
        if(rows != null) url += "&" + "rows=" + rows;
        if(start != null) url += "&" + "start=" + start;
        if(lang != null) url += "&" + "lang=" + lang;
        if(!(geoFilter_long.isEmpty())  && !(geoFilter_lat.isEmpty()) && !(distance.isEmpty())) url += "&" + "geofilter.distance=" + geoFilter_long + "%2C+" + geoFilter_lat + "%2C+" + distance;
        else {	
        	// msg pour avertir qu'il faut longitude + latitude + distance pour filter la geolocalisation des marchés.
        	
        }
        url += "&sort=-datepublicationdonnees";
        System.out.println("URL : : " + url); 
       
        return url;
    }
    
    //=2022%2F01%2F01
    
public static String dailyAPI_Request(String dataset, String q, String rows, Integer start, String lang, String geoFilter_long, String geoFilter_lat, String distance) {
    	
    	String url = "https://data.economie.gouv.fr/api/records/1.0/search/?dataset=";
    	Date currentDt = TalendDate.getCurrentDate();
    	
    	int currentDay 		= TalendDate.getPartOfDate("DAY_OF_MONTH", currentDt);
    	int currentMonth 	= TalendDate.getPartOfDate("MONTH", currentDt);
    	int currentYear 	= TalendDate.getPartOfDate("YEAR", currentDt);
    	
    	TalendDate.getPartOfDate("DAY_OF_MONTH", TalendDate.getCurrentDate());
    	TalendDate.getPartOfDate("MONTH", TalendDate.getCurrentDate());
    	
    	String newDay = "";
    	String newMonth = "";
    	
        url += dataset; 
        if(q != null) url += "&" + "q=" + q;
        if(rows != null) url += "&" + "rows=1000";
        if(start != null) url += "&" + "start=" + start;
        //if(!(geoFilter_long.isEmpty())  && !(geoFilter_lat.isEmpty()) && !(distance.isEmpty())) url += "&" + "geofilter.distance=" + geoFilter_long + "%2C+" + geoFilter_lat + "%2C+" + distance;
        else {	
        	// msg pour avertir qu'il faut longitude + latitude + distance pour filter la geolocalisation des marchés.
        	
        }
        if(currentDay <= 9) newDay = "0" + Integer.toString(currentDay);
        else newDay = Integer.toString(currentDay);
        
        if(currentMonth <= 9) newMonth = "0" + Integer.toString(currentMonth+1);
        else newMonth = Integer.toString(currentMonth);
       
        
        url += "&refine.datenotification=" + currentYear + "%2F" + newMonth + "%2F" + newDay;
        System.out.println(url); 
       
        return url;
    }
    
    
    /**
     * fill_null: fill null values with "unkmown"
     * 
     * 
     * {talendTypes} String str 
     * 
     * {Category} API 
     * 
     * {example} "https://data.economie.gouv.fr/api/records/1.0/search/?dataset=decp_augmente"
     */
    public static String fill_null(String str) {
    	
    	String result = ""; 
    	
    	if(str == null) result="unknown";
    	else result = str;
    	
    	return result;
    }
    
    /**
     * check_boolean: Return the value of the char 
     * 
     * 
     * {talendTypes} String str 
     * 
     * {Category} API 
     */
    public static Boolean check_boolean(Object str, String id) {
    	
    	Boolean result = false; 
    	
    	if(str instanceof Boolean) result= (Boolean)str;
    	else {
    		result = false;
    		wrongBoolean.add("La valeur de l'enregistrement [" + id + "] a été fixé à False.");
    	}
    	
    	return result;
    }
    
    public static ArrayList<String> getwrongBoolean(){
    	return wrongBoolean;
    }
    
}
