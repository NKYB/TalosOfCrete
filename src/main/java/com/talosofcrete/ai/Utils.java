package com.talosofcrete.ai;

public class Utils {
    public static int getRndInt(int min, int max){
        return ((int)(java.lang.Math.random()*(max+1))) + min;
    }
        
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
