package com.zebra.common.util;

public class NumUtil {
    public static long toLong(String numStr) {
        try {
            return Long.parseLong(numStr);
        }   catch (Exception ex)  {
            return 0;
        }
    }
    
    public static int toInt(String numStr) {
        try {
            return Integer.parseInt(numStr);
        }   catch (Exception ex)  {
            return 0;
        }
    }
    
    
    public static double toDouble(String numStr) {
        try {
            return Double.parseDouble(numStr);
        }   catch (Exception ex)  {
            return 0;
        }
    }
    
    
}
