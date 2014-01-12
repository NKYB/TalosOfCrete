package com.talosofcrete.ai;

public class Word {
    public static String createRandom(Program program, int numOfVars){
        String word = "";
        int actionIndex = (int)(java.lang.Math.random()*8);
        switch(actionIndex){
            case 0:
                word = getMathWord("ADD",numOfVars);
                break;
            case 1:
                word = getMathWord("SUBTRACT",numOfVars);
                break;
            case 2:
                word = getMathWord("MULTIPLY",numOfVars);
                break;
            case 3:
                word = getMathWord("DIVIDE",numOfVars);
                break;
            case 4:
                word = getMathWordConstant("ADD_CONSTANT",1,100);
                break;
            case 5:
                word = getMathWordConstant("SUBTRACT_CONSTANT",1,100);
                break;
            case 6:
                word = getMathWordConstant("MULTIPLY_CONSTANT",1,100);
                break;
            case 7:
                word = getMathWordConstant("DIVIDE_CONSTANT",1,100);
                break;
//            case 8:
//                word = getMathWord2Vars("IF_GREATER",numOfVars);
//                break;
//            case 9:
//                word = getMathWord2Vars("IF_LESS_THAN",numOfVars);
//                break;
//             case 10:
//                word = "CONSTANT:" + (int)(java.lang.Math.random()*100) + ";";
////                program.data.addConstant((int)(java.lang.Math.random()*100));
//                break;
        }
        return word;
    }
    
    private static String getMathWord2Vars(String action, int numOfVars){
        int var1Index = (int)(java.lang.Math.random()*numOfVars);
        int var2Index = (int)(java.lang.Math.random()*numOfVars);
        return action + ":VAR_" + var1Index + ":VAR_" + var2Index + ";";
    }
    
    private static String getMathWord(String action, int numOfVars){
        int varIndex = (int)(java.lang.Math.random()*numOfVars);
        return action + ":VAR_" + varIndex + ";";
    }
    
    private static String getMathWordConstant(String action, int minValue, int maxValue){
        int constantValue = ((int)(java.lang.Math.random()*(maxValue - minValue)) + minValue);
        return action + ":" + constantValue + ";";
    }
    
    public static float eval(String word, float[] vars, float currentScore){
        word = replace(word,";","");
        word = replace(word,"VAR_","");
        
        String[] partsTemp = word.split(":");
        int[] parts = new int[partsTemp.length];
        for(int i = 1; i < partsTemp.length; i++) {
            parts[i] = Integer.parseInt(partsTemp[i]);
        }
        String action = partsTemp[0];
        switch(action){
            case "ADD":
                return currentScore + vars[parts[1]];
            case "SUBTRACT":
                return currentScore - vars[parts[1]];
            case "MULTIPLY":
                return currentScore * vars[parts[1]];
            case "DIVIDE":
                float varToDivideBy = vars[parts[1]];
                if (varToDivideBy != 0 && currentScore != 0){
                    return currentScore / varToDivideBy;
                } else {
                    break;
                }
            case "ADD_CONSTANT":
                return currentScore + parts[1];
            case "SUBTRACT_CONSTANT":
                return currentScore - parts[1];
            case "MULTIPLY_CONSTANT":
                return currentScore * parts[1];
            case "DIVIDE_CONSTANT":
                float conToDivideBy = parts[1];
                if (conToDivideBy != 0 && currentScore != 0){
                    return currentScore / conToDivideBy;
                } else {
                    break;
                }
//            case "IF_GREATER":
//                return (vars[parts[1]] > vars[parts[2]]) ? 1 : 0;
//            case "IF_LESS_THAN":
//                return (vars[parts[1]] < vars[parts[2]]) ? 1 : 0;
//            case "CONSTANT":
//                return (float) parts[1];
        }
        return 0;
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
