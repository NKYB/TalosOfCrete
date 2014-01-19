package com.talosofcrete.ai;

public class Config {
    public int population_max_generations = 10000;
    public int program_initial_score = 100000000;
    public float program_success_score = 0.001F;
    
    public int population_max_size_limit = 100;
    public int population_max_size = 10;
    public int program_max_words_length = 30;
    public int program_action_add_start = 0;
    public int program_action_add_end = 20;
    public int program_action_delete_start = 21;
    public int program_action_delete_end = 40;
    public int program_action_modify_start = 41;
    public int program_action_modify_end = 70;
    public int program_action_cross_start = 71;
    public int program_action_cross_end = 100;
    
    public int word_constant_min = 1;
    public int word_constant_max = 100;
    
    public Config randomize(){
        population_max_size = Utils.getRndInt(5, population_max_size_limit-1);
        if (population_max_size > 99)
            population_max_size = 99;
        program_max_words_length = Utils.getRndInt(5, 100);
        program_action_add_start = 0;
        program_action_add_end = Utils.getRndInt(5, 100);
        program_action_delete_start =  program_action_add_end + 1;
        program_action_delete_end = program_action_delete_start + Utils.getRndInt(5, 100);
        program_action_modify_start = program_action_delete_end + 1;
        program_action_modify_end = program_action_modify_start + Utils.getRndInt(5, 100);
        program_action_cross_start = program_action_modify_end + 1;
        program_action_cross_end = program_action_cross_start + Utils.getRndInt(5, 100);
        word_constant_min = 1;
        word_constant_max = Utils.getRndInt(2, 10000);
        return this;
    }
    
}
