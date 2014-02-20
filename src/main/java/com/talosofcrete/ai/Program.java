package com.talosofcrete.ai;

/**
 * Each unit in the generation has a program that is tested for fitness, better
 * programs survive to evolve to the end program that represents a model for the
 * data
 * 
 * @author Administrator
 */
public class Program {
    private Config config;
    public Data data;
    public String words = "";
    public float score;
    
    public Program(Config config, Data data){
        this.config = config;
        this.data = data;
        score = config.program_initial_score;
    }
    /**
     * apply evolution to each program, adding, modify, delete, crossing
     * 
     * @param programs 
     */
    public void modify(Program[] programs){
        if (words.length() > 0){
            int action_index = (int)(java.lang.Math.random() * (config.program_action_cross_end+1));
            if (action_index >= config.program_action_add_start && action_index <= config.program_action_add_end){
                
                // Modify by Adding
                String[] wordList = createWordListFromWords(words);
                String newWord = Word.createRandom(words,data.inputData[0].length, config);
                modifyByAdding(wordList, newWord);
                
            } else if (action_index >= config.program_action_delete_start && action_index <= config.program_action_delete_end){
                
                // Modify By Changing
                String[] wordList = createWordListFromWords(words);
                int modifyIndex = (int)(java.lang.Math.random() * (wordList.length));
                String newWord = Word.createRandom(words, data.inputData[0].length, config).replace(";","");
                modifyByChanging(modifyIndex, wordList, newWord); 
                
            } else if (action_index >= config.program_action_modify_start && action_index <= config.program_action_modify_end){
                
                // Modify By Deleting
                String[] wordList = createWordListFromWords(words);
                int deleteIndex = (int)(java.lang.Math.random() * (wordList.length));
                modifyByDeleting(deleteIndex, wordList);
                
            } else if (action_index >= config.program_action_cross_start && action_index <= config.program_action_cross_end){
                
                // Modify By Crossing
                modifyByCrossing(programs[0]);
            }
        } else {
            words = Word.createRandom(words, data.inputData[0].length, config); 
        }
    }
    
    /*
     * add the new word to the word list
     */
    public void modifyByAdding(String[] wordList, String newWord){
        if (wordList.length < config.program_max_words_length){
            words += newWord; 
        }
    }
    
    /*
     * change an existing word to a new word
     */
    public void modifyByChanging(int modifyIndex, String[] wordList, String newWord){
        if (indexExists(wordList, modifyIndex)){
            wordList[modifyIndex] = newWord;
            words = "";
            for (int i = 0; i < wordList.length; i++) {
                words += wordList[i] + ";";
            }
        }
    }
    
    /*
     * change the program by removing a word
     */
    public void modifyByDeleting(int deleteIndex, String[] wordList){
        if (indexExists(wordList, deleteIndex)){
            wordList[deleteIndex] = "";
            words = "";
            for (int i = 0; i < wordList.length; i++) {
                if (!wordList[i].equals("")){ 
                    words += wordList[i] + ";";
                }
            }
        }
    }
    
    /*
     * Modify program by selecting words from top scoring program
     */
    public void modifyByCrossing(Program programToCrossFrom){
        String[] wordList = createWordListFromWords(words);
        String[] crossWordList = createWordListFromWords(programToCrossFrom.words);
        words = "";
        int lengthOfCross = (int)(java.lang.Math.random() * config.program_max_words_length);
        for (int i = 0; i < lengthOfCross; i++) {
            int typeToSelectFrom = (int)(java.lang.Math.random() * 2);
            if (i < wordList.length && typeToSelectFrom == 0){
                words += wordList[i] + ";";
            } else if (i < crossWordList.length && typeToSelectFrom == 1){
                words += crossWordList[i] + ";";
            }
        }
    }
    
    public String[] createWordListFromWords(String words){
        return words.split(";");
    }
    
    public boolean indexExists(String[] words, final int index) {
        return index >= 0 && index < words.length;
    }
    
    public void eval(){
        if (words.length() > 0){
            float subScore = 0;
            Word[] wordList = createCleanWordsFromWords(words);
            for (int i = 0; i < data.inputData.length; i++) {
                subScore += Math.abs(data.outputData[i] - scoreWord(wordList, data.inputData[i]));
            }
            this.score = Math.abs(subScore);
        }
    }
    
    public float scoreWord(Word[] wordsList, float[] inputDataRow){
        float wordScore = 0;
        for (int j = 0; j < wordsList.length; j++) {
            wordScore = Word.eval(wordsList[j], inputDataRow, wordScore);
        }
        return wordScore;
    }
    
    public static Word[] createCleanWordsFromWords(String wordsAsString){
        String cleanWords = Utils.replace(wordsAsString,"VAR_","");
        String[] word = cleanWords.split(";");
        Word[] wordsList = new Word[word.length];
        for (int i = 0; i < word.length; i++) {
            String[] parts = word[i].split(":");
            wordsList[i] = new Word();
            wordsList[i].action = parts[0];
            wordsList[i].param = Integer.parseInt(parts[1]);
        }
        return wordsList;
    }
    
    public static Program shallowCopy(Program programFrom, Config config, Data data){
        Program swap = new Program(config, data);
        swap.words = programFrom.words;
        swap.score = programFrom.score;
        return swap;
    }
}
