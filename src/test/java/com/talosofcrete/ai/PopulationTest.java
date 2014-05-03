package com.talosofcrete.ai;

import junit.framework.TestCase;

/**
 *
 * @author nkyb
 */
public class PopulationTest extends TestCase {
    
    public PopulationTest(String testName) {
        super(testName);
    }

    /**
     * setup a population
     */
    public void testSomeMethod() {
        Config config = new Config();
        config.population_max_generations = 1000;
        config.population_max_size = 10;
        
        Data data = new Data();
        data.createRandom(5, 5);
        
        Population population = new Population(config, data);
        
        config.program_success_score = config.program_initial_score;
        population = new Population(config, data);
        assertTrue( true );
    }
    
    /**
     * test modifying the config
     */
    public void testModifyConfig(){
        Config config = new Config();
        config.population_max_generations = 1000;
        config.population_max_size = 10;
        
        Data data = new Data();
        data.createRandom(5, 5);
        
        Population population = new Population(config, data);
        population.programs[0].score = 10000;
        population.topScore = 0;
        population.modifyConfig(50000);
        assertTrue( true );
    }
    
    /**
     * test the output to JavaScript
     */
    public void testRenderAsJS(){
        Config config = new Config();
        config.population_max_generations = 1000;
        config.population_max_size = 10;
        
        Data data = new Data();
        data.createRandom(5, 5);
        
        Population population = new Population(config, data);
        population.modifyConfig(50000);
        population.topScore = 0;
        population.programs[0].words = "ADD_CONSTANT:1;SUBTRACT_CONSTANT:1;MULTIPLY_CONSTANT:1;DIVIDE_CONSTANT:1;ADD:VAR_1;SUBTRACT:VAR_1;MULTIPLY:VAR_1;DIVIDE:VAR_1;";
        population.renderAsJS();
        assertTrue( true );
    }
}
