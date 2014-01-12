package com.talosofcrete.ai;

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
    
    public void modify(Program[] programs){
        if (words.length() > 0){
            int action_index = (int)(java.lang.Math.random() * (config.program_action_cross_end+1));
            if (action_index >= config.program_action_add_start && action_index <= config.program_action_add_end){
                modifyByAdding();
            } else if (action_index >= config.program_action_delete_start && action_index <= config.program_action_delete_end){
                modifyByChanging();    
            } else if (action_index >= config.program_action_modify_start && action_index <= config.program_action_modify_end){
                modifyByDeleting();
            } else if (action_index >= config.program_action_cross_start && action_index <= config.program_action_cross_end){
                modifyByCrossing(programs[0]);
            }
        } else {
            words = Word.createRandom(this, data.inputData[0].length); 
        }
    }
    
    private void modifyByAdding(){
        String[] wordList = createWordListFromWords(words);
        if (wordList.length < config.program_max_words_length){
            words += Word.createRandom(this, data.inputData[0].length);
        }
    }
    
    private void modifyByChanging(){
        String[] wordList = createWordListFromWords(words);
        int modifyIndex = (int)(java.lang.Math.random() * (wordList.length));
        wordList[modifyIndex] = Word.createRandom(this, data.inputData[0].length).replace(";","");
        words = "";
        for (int i = 0; i < wordList.length; i++) {
            words += wordList[i] + ";";
        }
    }
    
    private void modifyByDeleting(){
        String[] wordList = createWordListFromWords(words);
        int deleteIndex = (int)(java.lang.Math.random() * (wordList.length));
        wordList[deleteIndex] = "";
        words = "";
        for (int i = 0; i < wordList.length; i++) {
            if (!wordList[i].equals("")){ 
                words += wordList[i] + ";";
            }
        }
    }
    
    private void modifyByCrossing(Program programToCrossFrom){
        String[] wordList = createWordListFromWords(words);
        String[] crossWordList = createWordListFromWords(programToCrossFrom.words);
        words = "";
        for (int i = 0; i < config.program_max_words_length; i++) {
            int typeToSelectFrom = (int)(java.lang.Math.random() * 2);
            if (i < wordList.length && typeToSelectFrom == 0){
                words += wordList[i] + ";";
            } else if (i < crossWordList.length && typeToSelectFrom == 1){
                words += crossWordList[i] + ";";
            }
        }
    }
    
    private String[] createWordListFromWords(String words){
        return words.split(";");
    }
    
    public void eval(){
        if (words.length() > 0){
            float evalScore = 0;
            float subScore = 0;
            String[] word = words.split(";");
            for (int i = 0; i < data.inputData.length; i++) {
                evalScore = 0;
                for (int j = 0; j < word.length; j++) {
                    float wordScore = Word.eval(word[j], data.inputData[i], evalScore);
                    evalScore += wordScore;
                }
                subScore += Math.abs(data.outputData[i] - evalScore);
            }
            this.score = Math.abs(subScore);
        }
    }
    
    public static Program shallowCopy(Program programFrom, Config config, Data data){
        Program swap = new Program(config, data);
        swap.words = programFrom.words;
        swap.score = programFrom.score;
        return swap;
    }
}
