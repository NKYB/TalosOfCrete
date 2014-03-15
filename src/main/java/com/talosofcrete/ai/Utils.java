/**
 * Utility class for loss ends 
 * 
 * @author NKYB
 */

package com.talosofcrete.ai;

public class Utils {
    
    /**
     * Generate a random int between the min and max, inclusive
     * 
     * @param min the minimun random value
     * @param max the maximum random value
     * @return the random number
     */
    public static int getRndInt(int min, int max){
        return ((int)(java.lang.Math.random()*(max+1))) + min;
    }
        
    /**
     * Replace all occurance of needle with the new string in the source
     * 
     * @param source the string to search for the needle string
     * @param os the original <CODE>string</CODE> to search for
     * @param ns the new <CODE>string</CODE> to replace with
     * @return the new source with needle removed
     */
    public static String replace (String source, String os, String ns) {
        if (source == null) {
            return null;
        }
        int i = 0;
        if ((i = source.indexOf(os, i)) >= 0) {
            char[] sourceArray = source.toCharArray();
            char[] nsArray = ns.toCharArray();
            int oLength = os.length();
            StringBuilder buf = new StringBuilder (sourceArray.length);
            buf.append (sourceArray, 0, i).append(nsArray);
            i += oLength;
            int j = i;
            // Replace all remaining instances of oldString with newString.
            while ((i = source.indexOf(os, i)) > 0) {
                buf.append (sourceArray, j, i - j).append(nsArray);
                i += oLength;
                j = i;
            }
            buf.append (sourceArray, j, sourceArray.length - j);
            source = buf.toString();
            buf.setLength (0);
        }
        return source;
    }
}
