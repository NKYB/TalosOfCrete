package com.talosofcrete.ai;

import junit.framework.TestCase;

public class EndToEndTest extends TestCase {
    
    public EndToEndTest(String testName) {
        super(testName);
    }
    
    public void testCreateRandom() {
        System.out.println("What what");
        
        Config config = new Config();
        config.population_max_generations = 1000000;
        config.population_max_size = 10;
        
        int numRows = 3;
        int numCols = 3;
        Data data = new Data();
        data.createSequential(numRows, numCols);
        
        Population population = new Population(config, data);
        
        assertTrue( true );
    }
}
