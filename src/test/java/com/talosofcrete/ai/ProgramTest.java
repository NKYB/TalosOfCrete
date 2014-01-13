/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.talosofcrete.ai;

import junit.framework.TestCase;

/**
 *
 * @author nbuwald
 */
public class ProgramTest extends TestCase {
    
    public ProgramTest(String testName) {
        super(testName);
    }

    public void testModify() {
        System.out.println("modify");
        Program[] programs = null;
        Program instance = null;
        instance.modify(programs);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public void testEval() {
        System.out.println("eval");
        Program instance = null;
        instance.eval();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public void testShallowCopy() {
        System.out.println("shallowCopy");
        
        Config config = new Config();
        config.population_max_generations = 1000;
        config.population_max_size = 10;
        
        Data data = new Data();
        data.createRandom(5, 5);
        
        Program programFrom = new Program(config, data);
        
        Program programTo = Program.shallowCopy(programFrom, config, data);
        
        assertEquals(programFrom.words, programTo.words);
        assertEquals(programFrom.score, programTo.score);
    }
}
