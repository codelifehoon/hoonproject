package com.zebra.common.util;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class DebugUtil {
    
    
    public  static String DebugBoList(List list) {
        
        if (list==null ) return "null object\r\n";
        String buffer="";
         for (int i = 0; list != null && i < list.size(); i++) {
          Object o = list.get(i);
          buffer += "-------------------------------------------------------" + i + "\r\n" ;
          buffer += DebugBo(o);
         }       
         return buffer; 
       }

    public static String DebugBo(Object o) 
    {
         if (o == null) return "null object\r\n";
         
         Method[] m = o.getClass().getDeclaredMethods();
         String buffer="";
         for (int i = 0; i < m.length; i++) {            
          if (m[i].getName().startsWith("get")) {
           try {
               
               String className = "";

                
              if (m[i].invoke(o, null) != null) className = m[i].toString(); 
              else className = "";

             if (className.length() != 0  ) 
                {
                 if (className.indexOf("java.lang.String[][]",0) > 0) 
                     {
                         buffer += m[i].getName() + " : " + m[i].invoke(o, null) + "\r\n";
                     }
                 else if(className.indexOf("java.lang.String[]",0) > 0) 
                     { 
                         buffer += m[i].getName() + DebugStringArray((String[])m[i].invoke(o, null)) + "\r\n" ;
                     }
                 else
                     {
                         buffer += m[i].getName() + " : " + m[i].invoke(o, null)  + "\r\n";
                     }
                }
               
           } catch (Exception e) {
               
               buffer += m[i].getName() + " : " + e.getMessage() +" \r\n"; 
            
           }
          }
         }
         
         return buffer;
         
    }
    
    public static String DebugStringArray(String[] data)
    {
        if (data == null) return "null object\r\n";
        
        int str_len=0;
        String buffer="";
        str_len= data.length;
        
        for(int i=0;i<str_len;i++)
        {
            try {   buffer += "," +  data[i];        }
            catch (Exception e) 
                {  buffer += "exception(maybe null: " + i;      }
        }
        
        return buffer;
    }
    
    public static String DebugStringList(List data)
    {
        if (data == null) return "null object\r\n";
        
        int str_len=0;
        String buffer="";
        str_len= data.size();
        
        for(int i=0;i<str_len;i++)
        {
            try {   buffer += "," +  data.get(i);        }
            catch (Exception e) 
                {  buffer += "exception(maybe null: " + i;      }
        }
        
        return buffer;
    }
    
    
    public static String DebugHashMapBoList(HashMap map)
    {
        if (map == null) return "null object\r\n";
        String buffer = "";      
        Set set = map.keySet();
        Iterator keys = set.iterator();
        
        while(keys.hasNext()) 
        {
            Object key = keys.next();
            try 
            {
               
                buffer += "mapkey : " + key + "\r\n";
                buffer += DebugBoList((List)map.get(key));
                
               //buffer += map.get(key).toString();
                /*
               if (key instanceof String)   buffer += (String)key + " : " + DebugBoList((List)map.get(key));
               else                          buffer += key + " : " + DebugBoList((List)map.get(key));
               */
            }
            catch(Exception e)
            {
                buffer += key + " : exception\r\n" ;
            }
        }
        return buffer;        
    }
    
    public static String DebugHashMapBo(HashMap map)
    {
        if (map == null) return "null object\r\n";
        String buffer = "";      
        Set set = map.keySet();
        Iterator keys = set.iterator();
        
        while(keys.hasNext()) 
        {
            Object key = keys.next();
            try 
            {
               
                buffer += "mapkey : " + key + "\r\n";
                buffer += DebugBo(map.get(key));
                
            }
            catch(Exception e)
            {
                buffer += key + " : exception\r\n" ;
            }
        }
        return buffer;        
    }
    
    public static String DebugHashMapString(HashMap map)
    {
        if (map == null) return "null object value\r\n";
        String buffer = "";      
        Set set = map.keySet();
        Iterator keys = set.iterator();
        
        while(keys.hasNext()) 
        {
            Object key = keys.next();
            try 
            {
               
                buffer += "mapkey : " + key + "\r\n";
                buffer += map.get(key).toString();
                
            }
            catch(Exception e)
            {
                buffer += key + " : exception\r\n" ;
            }
        }
        return buffer;        
    }
    
    
        

}
