package com.talosofcrete.ai;

/**
 * Population evolves over generations to define new programs, check their 
 * ability to survive and to evolve
 * 
 * @author nkyb
 */
public class Population {

    public Program[] programs;
    
    private Config config;
    private Data data;
    public float topScore;
    
    /**
     * Main loop to work through all the generations
     * 
     * @param config dynamic creation to continue evolution when progress stalls
     * @param data straining data with inputs and results
     */
    public Population(Config config, Data data){
        this.config = config;
        topScore = config.program_initial_score;
        this.data = data;
        initPopulation();
        for (int i = 0; i < config.population_max_generations; i++) {
            for (int j = 0; j < config.population_max_size; j++) {
                evolve(j);
            }
            sort();
            prune();
            modifyConfig(i);
            debug_loop(i);
            if (programs[0].score < config.program_success_score){
                System.out.println("Found on generation: " + i);
                i = config.population_max_generations;
            }
        }
        debug_final_score();
    }

    
    private void initPopulation(){
        programs = new Program[config.population_max_size_limit];
        for (int i = 0; i < config.population_max_size_limit; i++) {
            programs[i] = new Program(config, data);
        }
    }
    
    private void sort(){
        // bubbleSortPrograms
        Program swapProgram;
        for (int a = 0; a < config.population_max_size - 1; a++) {
            for (int b = 0; b < config.population_max_size - a - 1; b++) {
                if (programs[b].score > programs[b+1].score){
                    swapProgram = programs[b];
                    programs[b] = programs[b+1];
                    programs[b+1] = swapProgram;
                }
            }
        }
    }
    
    private void evolve(int programIndex){
        if (programIndex>=config.population_max_size_limit){
            programIndex=config.population_max_size_limit-1;
        }
        Program swap = Program.shallowCopy(programs[programIndex], config, data);
        swap.modify(programs);
        swap.eval();
        if (programIndex==0 || programIndex==1){
            if (swap.score < programs[programIndex].score){
                programs[programIndex] = Program.shallowCopy(swap, config, data);
            }
        } else {
            programs[programIndex] = Program.shallowCopy(swap, config, data);
        }
    }
    
    private void prune(){
        programs[config.population_max_size-1].words = "";
    }
    
    public void modifyConfig(int generation){
        if (generation > 0 && generation % 50000 == 0){
            if (topScore <= programs[0].score){
                config.randomize();
            }
            topScore = programs[0].score;
        }
    }
    
    private void debug_loop(int loopIndex){
        if (loopIndex%50000==0){
            System.out.println(loopIndex + " Current Top Score: " + programs[0].score);
        }
    }
    
    private void debug_final_score(){
        for (int j = 0; j < config.population_max_size; j++) {
            System.out.println(j + " - Word: " + programs[j].words  + " Score: " + programs[j].score);
        }
        renderAsJS();
    }
    
    public void renderAsJS(){
        String inputData = "var inputData = [";
        String outputData = "var outputData = [";
        for (int i = 0; i < data.inputData.length; i++) {
            inputData += "[";
            for (int j = 0; j < data.inputData[i].length; j++) {
                inputData += data.inputData[i][j] + ",";
            }
            inputData += "],";
            outputData += data.outputData[i] + ",";
        }
        inputData += "];";
        outputData += "];";
        System.out.println(inputData);
        System.out.println(outputData);
        
        String jsFunction = "function run(inputData, outputData){\n";
        jsFunction += "     var score = 0;\n";
        Word[] wordList = Program.createCleanWordsFromWords(programs[0].words);
        for (int j = 0; j < wordList.length; j++) {
            if (wordList[j].action.equals("ADD")){
                jsFunction += "     score += inputData[" + wordList[j].param + "];\n";
            } else if (wordList[j].action.equals("SUBTRACT")){
                jsFunction += "     score -= inputData[" + wordList[j].param + "];\n";
            } else if (wordList[j].action.equals("DIVIDE")){
                jsFunction += "     score /= inputData[" + wordList[j].param + "];\n";
            } else if (wordList[j].action.equals("MULTIPLY")){
                jsFunction += "     score *= inputData[" + wordList[j].param + "];\n";
            } else if (wordList[j].action.equals("ADD_CONSTANT")){
                jsFunction += "     score += " + wordList[j].param + ";\n";
            } else if (wordList[j].action.equals("SUBTRACT_CONSTANT")){
                jsFunction += "     score -= " + wordList[j].param + ";\n";
            } else if (wordList[j].action.equals("DIVIDE_CONSTANT")){
                jsFunction += "     score /= " + wordList[j].param + ";\n";
            } else if (wordList[j].action.equals("MULTIPLY_CONSTANT")){
                jsFunction += "     score *= " + wordList[j].param + ";\n";
            } 
        }
        jsFunction += "     console.log('score found:',score,' target:',outputData);\n";
        jsFunction += "}\n";
        for (int i = 0; i < data.inputData.length; i++) {
            jsFunction += "run(inputData[" + i + "], outputData[" + i + "]);\n";
        }
        System.out.println(jsFunction);
    }
}
