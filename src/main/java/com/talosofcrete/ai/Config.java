package com.talosofcrete.ai;

public class Config {
    public int population_max_generations = 10000;
    public int program_initial_score = 100000000;
    
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
}
