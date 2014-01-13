package com.talosofcrete.ai;

import junit.framework.TestCase;

/**
 *
 * @author nbuwald
 */
public class PopulationTest extends TestCase {
    
    public PopulationTest(String testName) {
        super(testName);
    }

    public void testSomeMethod() {
        Config config = new Config();
        config.population_max_generations = 1000;
        config.population_max_size = 10;
        
        Data data = new Data();
        data.createRandom(5, 5);
        
        Population population = new Population(config, data);
        
        config.program_success_score = config.program_initial_score;
        population = new Population(config, data);
    }
}
