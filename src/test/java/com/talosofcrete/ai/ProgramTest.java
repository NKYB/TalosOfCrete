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

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testModify() {
//        System.out.println("modify");
//        Program[] programs = null;
//        Program instance = null;
//        instance.modify(programs);
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    public void testEval() {
//        System.out.println("eval");
//        Program instance = null;
//        instance.eval();
        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
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

    /**
     * Test of modifyByAdding method, of class Program.
     */
    public void testModifyByAdding() {
//        System.out.println("modifyByAdding");
//        Program instance = null;
//        instance.modifyByAdding();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of modifyByChanging method, of class Program.
     */
    public void testModifyByChanging() {
//        System.out.println("modifyByChanging");
//        int modifyIndex = 0;
//        String[] wordList = null;
//        Program instance = null;
//        instance.modifyByChanging(modifyIndex, wordList);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of modifyByDeleting method, of class Program.
     */
    public void testModifyByDeleting() {
//        System.out.println("modifyByDeleting");
//        int deleteIndex = 0;
//        String[] wordList = null;
//        Program instance = null;
//        instance.modifyByDeleting(deleteIndex, wordList);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of modifyByCrossing method, of class Program.
     */
    public void testModifyByCrossing() {
//        System.out.println("modifyByCrossing");
//        Program programToCrossFrom = null;
//        Program instance = null;
//        instance.modifyByCrossing(programToCrossFrom);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
}
