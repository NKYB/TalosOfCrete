package com.talosofcrete.ai;

import com.amd.aparapi.Kernel;
import com.amd.aparapi.Range;

public class Aparapi {
    
    private Config config;
    private Data data;
    
    public Aparapi(Config config, Data data){
        this.config = config;
        this.data = data;
    }
    
    public String evolve(Program program){
        
        final int[] kernelConfig = new int[10];
        kernelConfig[0] = config.aparapi_num_generations_to_run;
        kernelConfig[1] = config.program_max_words_length;
        kernelConfig[2] = config.aparapi_new_kernels;
        kernelConfig[5] = config.word_constant_max;
                
        final int[] seeds = Config.getSeeds(config.aparapi_new_kernels);
        
        final float inputData[] = new float[data.inputData[0].length];
        for (int i = 0; i < inputData.length; i++) {
            inputData[i] = data.inputData[0][i];
        }
        kernelConfig[4] = inputData.length;
        
        final float outputData[] = new float[1];
        outputData[0] = data.outputData[0];
        
        final float score[] = new float[1];
        score[0] = program.score;
        
        final int words[] = wordToFloatArray(Program.createCleanWordsFromWords(program.words));
        kernelConfig[3] = words.length;
                
        Kernel kernel = new Kernel(){
            @Override public void run(){
//                int[] blowupaparapi = new int[5];
                
                int gid= getGlobalId();
                int seedIndex = setIndex(gid,1440);
                int aparapi_num_generations_to_run = kernelConfig[0];
                int program_max_words_length = kernelConfig[1];
                int aparapi_new_kernels = kernelConfig[2];
                int num_slots = kernelConfig[3];
                int num_words = num_slots / 2;
                int num_vars = kernelConfig[4];
                int word_constant_max = kernelConfig[5];
                
                int curIndex = 0;
                int curAction = 0;
                int curVarIndex = 0;
                
                int newIndex = 0;
                int newAction = 0;
                int newVarIndex = 0;
                
                float sub_score = 10000000;
                float temp_score = 0;
                
                for (int i = 0; i < aparapi_num_generations_to_run; i++) {
                    newIndex = next(0,num_words,seeds,seedIndex) * 2;
                    seedIndex = setIndex(seedIndex,aparapi_new_kernels);
                    newAction = next(0,7,seeds,seedIndex);
                    seedIndex = setIndex(seedIndex,aparapi_new_kernels);
                    
                    if (newAction==0 || newAction==1 || newAction==2 || newAction==3){ 
                        newVarIndex = next(0,num_vars-1,seeds,seedIndex);
                        seedIndex = setIndex(seedIndex,aparapi_new_kernels);
                    }
                    
                    if (newAction==4 || newAction==5 || newAction==6 || newAction==7){
                        newVarIndex = next(0,word_constant_max,seeds,seedIndex);
                        seedIndex = setIndex(seedIndex,aparapi_new_kernels);
                    }
                    
                    temp_score = 0;
                    for (int j = 0; j < num_words; j++) {
                        curIndex = j*2;
                        if (j*2==newIndex){
                            curAction = newAction;
                            curVarIndex = newVarIndex;
                        } else {
                            curAction = words[j*2];
                            curVarIndex = words[j*2+1];
                        }
                        
                        if (curAction==0){
                            temp_score = temp_score + inputData[curVarIndex];
                        } else if (curAction==1){
                            temp_score = temp_score - inputData[curVarIndex];
                        } else if (curAction==2){
                            temp_score = temp_score * inputData[curVarIndex];
                        } else if (curAction==3){
                            temp_score = temp_score / inputData[curVarIndex];
                        } else if (curAction==4){
                            temp_score = temp_score + curVarIndex;
                        } else if (curAction==5){
                            temp_score = temp_score - curVarIndex;
                        } else if (curAction==6){
                            temp_score = temp_score * curVarIndex;
                        } else if (curAction==7){
                            temp_score = temp_score / curVarIndex;
                        }
                        
                    }
                    
                    if (temp_score > outputData[0]){
                        sub_score = temp_score - outputData[0];
                    } else {
                        sub_score = outputData[0] - temp_score;
                    }

                    if (sub_score < score[0]){
                        score[0] = sub_score;
                    }
                    
                }
            }
            
            int setIndex(int index, int maxLength){
                if (index >= maxLength){
                    index = 0;
                }
                return index;
            }
            
            int next(int min, int max, int[] seeds, int seedIndex){
                return min + (seeds[seedIndex] % (1 + max - min));
            }
        };
        Range range = Range.create(config.aparapi_new_kernels); 
        kernel.execute(range);
        
        return "";
    }
    
    public int[] wordToFloatArray(Word[] words){
        int[] wordsAsFloats =  new int[words.length*2];
        for (int i = 0; i < words.length; i++) {
            if (words[i].action.equals("ADD")){
                wordsAsFloats[(i*2)] = 0;
            } else if (words[i].action.equals("SUBTRACT")){
                wordsAsFloats[(i*2)] = 1;
            } else if (words[i].action.equals("MULTIPLY")){
                wordsAsFloats[(i*2)] = 2;
            } else if (words[i].action.equals("DIVIDE")){
                wordsAsFloats[(i*2)] = 3;
            } else if (words[i].action.equals("ADD_CONSTANT")){
                wordsAsFloats[(i*2)] = 4;
            } else if (words[i].action.equals("SUBTRACT_CONSTANT")){
                wordsAsFloats[(i*2)] = 5;
            } else if (words[i].action.equals("MULTIPLY_CONSTANT")){
                wordsAsFloats[(i*2)] = 6;
            } else if (words[i].action.equals("DIVIDE_CONSTANT")){
                wordsAsFloats[(i*2)] = 7;
            }
            wordsAsFloats[(i*2)+1] = words[i].param;
        }
        return wordsAsFloats;
    }
    
}
