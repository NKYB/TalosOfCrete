package com.talosofcrete.ai;

public class Word {
    public static String createRandom(int numOfVars, Config config){
        int actionIndex = GetRndInt(0,7);
        return create(actionIndex,numOfVars,config);
    }
    
    public static String create(int actionIndex, int numOfVars, Config config){
        String word = "";
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
                word = getMathWordConstant("ADD_CONSTANT", config.word_constant_min, config.word_constant_max);
                break;
            case 5:
                word = getMathWordConstant("SUBTRACT_CONSTANT", config.word_constant_min, config.word_constant_max);
                break;
            case 6:
                word = getMathWordConstant("MULTIPLY_CONSTANT", config.word_constant_min, config.word_constant_max);
                break;
            case 7:
                word = getMathWordConstant("DIVIDE_CONSTANT", config.word_constant_min, config.word_constant_max);
                break;
        }
        return word;
    }
    
    private static String getMathWord(String action, int numOfVars){
        int varIndex = GetRndInt(0, numOfVars-1);
        return action + ":VAR_" + varIndex + ";";
    }
    
    private static String getMathWordConstant(String action, int minValue, int maxValue){
        int constantValue = GetRndInt(minValue, maxValue);
        return action + ":" + constantValue + ";";
    }
    
    public static int GetRndInt(int min, int max){
        return ((int)(java.lang.Math.random()*(max+1))) + min;
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

        if (action.equals("ADD")){
            return currentScore + vars[parts[1]];
        } else if (action.equals("SUBTRACT")){
            return currentScore - vars[parts[1]];
        } else if (action.equals("MULTIPLY")){
            return currentScore * vars[parts[1]];
        } else if (action.equals("DIVIDE")){
            float varToDivideBy = vars[parts[1]];
            if (varToDivideBy != 0 && currentScore != 0){
                return currentScore / varToDivideBy;
            }
        } else if (action.equals("ADD_CONSTANT")){
            return currentScore + parts[1];
        } else if (action.equals("SUBTRACT_CONSTANT")){
            return currentScore - parts[1];
        } else if (action.equals("MULTIPLY_CONSTANT")){
            return currentScore * parts[1];
        } else if (action.equals("DIVIDE_CONSTANT")){
            float conToDivideBy = parts[1];
            if (conToDivideBy != 0 && currentScore != 0){
                return currentScore / conToDivideBy;
            }
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
