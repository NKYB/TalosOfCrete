package com.talosofcrete.ai;

public class Population {

    public Program[] programs;
    
    private Config config;
    private Data data;
    
    public Population(Config config, Data data){
        this.config = config;
        this.data = data;
        initPopulation();
        for (int i = 0; i < config.population_max_generations; i++) {
            for (int j = 0; j < config.population_max_size; j++) {
                evolve(j);
            }
            sort();
            prune();
            debug_loop(i);
            if (programs[0].score < config.program_success_score){
                i = config.population_max_generations;
            }
        }
        debug_final_score();
    }

    
    private void initPopulation(){
        programs = new Program[config.population_max_size];
        for (int i = 0; i < config.population_max_size; i++) {
            programs[i] = new Program(config, data);
        }
    }
    
    private void sort(){
        // bubbleSortPrograms
        Program swapProgram;
        for (int a = 0; a < programs.length-1; a++) {
            for (int b = 0; b < programs.length-a-1; b++) {
                if (programs[b].score > programs[b+1].score){
                    swapProgram = programs[b];
                    programs[b] = programs[b+1];
                    programs[b+1] = swapProgram;
                }
            }
        }
    }
    
    private void evolve(int programIndex){
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
    
    private void debug_loop(int loopIndex){
        if (loopIndex%50000==0){
            System.out.println(loopIndex + " Current Top Score: " + programs[0].score);
        }
    }
    
    private void debug_final_score(){
        for (int j = 0; j < config.population_max_size; j++) {
            System.out.println(j + " - Word: " + programs[j].words  + " Score: " + programs[j].score);
        }
    }
}
