package com.talosofcrete.ai;

public class Word {
    public String action = "";
    public int param = 0;
    
    public static String createRandom(String currentWord, int numOfVars, Config config){
        int actionIndex = Utils.getRndInt(0,7);
        return create(currentWord, actionIndex, numOfVars, config);
    }
    
    public static String create(String currentWord, int actionIndex, int numOfVars, Config config){
        String word = "";
        switch(actionIndex){
            case 0:
                word = getMathWord("ADD",numOfVars);
                break;
            case 1:
                word = getMathWord("SUBTRACT",numOfVars);
                break;
            case 2:
                if (!currentWord.equals(""))
                    word = getMathWord("MULTIPLY",numOfVars);
                break;
            case 3:
                if (!currentWord.equals(""))
                    word = getMathWord("DIVIDE",numOfVars);
                break;
            case 4:
                word = getMathWordConstant("ADD_CONSTANT", config.word_constant_min, config.word_constant_max);
                break;
            case 5:
                word = getMathWordConstant("SUBTRACT_CONSTANT", config.word_constant_min, config.word_constant_max);
                break;
            case 6:
                if (!currentWord.equals(""))
                    word = getMathWordConstant("MULTIPLY_CONSTANT", config.word_constant_min, config.word_constant_max);
                break;
            case 7:
                if (!currentWord.equals(""))
                    word = getMathWordConstant("DIVIDE_CONSTANT", config.word_constant_min, config.word_constant_max);
                break;
        }
        return word;
    }
    
    /**
     * gets the user friendly version of the math word
     * 
     * @param action math to apply
     * @param numOfVars index to apply
     * @return user friendly string
     */
    private static String getMathWord(String action, int numOfVars){
        int varIndex = Utils.getRndInt(0, numOfVars-1);
        return action + ":VAR_" + varIndex + ";";
    }
    
    /**
     * get a constant for the program
     * 
     * @param action math to apply
     * @param minValue min constant value
     * @param maxValue max constant value
     * @return 
     */
    private static String getMathWordConstant(String action, int minValue, int maxValue){
        int constantValue = Utils.getRndInt(minValue, maxValue);
        return action + ":" + constantValue + ";";
    }

    /**
     * Calculate value of word against data
     * 
     * @param word to evaluate
     * @param vars data set to test against
     * @param currentScore the current score to add to
     * @return the new value of current score
     */
    public static float eval(Word word, float[] vars, float currentScore){
        if (word.action.equals("ADD")){
            return currentScore + vars[word.param];
        } else if (word.action.equals("SUBTRACT")){
            return currentScore - vars[word.param];
        } else if (word.action.equals("MULTIPLY")){
            return currentScore * vars[word.param];
        } else if (word.action.equals("DIVIDE")){
            float varToDivideBy = vars[word.param];
            if (varToDivideBy != 0 && currentScore != 0){
                return currentScore / varToDivideBy;
            }
        } else if (word.action.equals("ADD_CONSTANT")){
            return currentScore + word.param;
        } else if (word.action.equals("SUBTRACT_CONSTANT")){
            return currentScore - word.param;
        } else if (word.action.equals("MULTIPLY_CONSTANT")){
            return currentScore * word.param;
        } else if (word.action.equals("DIVIDE_CONSTANT")){
            float conToDivideBy = word.param;
            if (conToDivideBy != 0 && currentScore != 0){
                return currentScore / conToDivideBy;
            }
        }
        return 0;
    }
}
