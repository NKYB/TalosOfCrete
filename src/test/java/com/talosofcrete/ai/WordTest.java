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
public class WordTest extends TestCase {
    
    private Config config;
    private Data data;
    
    /**
     * setup word test
     * @param testName 
     */
    public WordTest(String testName) {
        super(testName);
    }
    
    /**
     * 
     * @throws Exception 
     */
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        config = new Config();
        config.population_max_generations = 1000;
        config.population_max_size = 10;
        
        data = new Data();
        data.createSequential(5, 5);
    }

    /**
     * test the creation of random words
     */
    public void testCreateRandom() {
        System.out.println("createRandom");
        int numOfVars = data.inputData[0].length;
        String expResult = "";
        for (int i = 0; i < 1000; i++) {
            String result = Word.createRandom("ADD:VAR_1;", numOfVars, config);
            assertNotSame(result, "");
        }
    }
    
    /*
     * create new population item
     */
    public void testCreate() {
        System.out.println("create");
        int actionIndex = 0;
        int numOfVars = 5;
        String result = Word.create("ADD:VAR_1;", actionIndex, numOfVars, config);
        assertNotSame(result, "");
    }
    
    /**
     * init a new word
     */
    public void testInit(){
        Word word = new Word();
    }

    /**
     * test the eval of a word
     */
    public void testEval() {
        System.out.println("eval");
        float[] vars = data.inputData[0];
        assertEquals(1.0F,  Word.eval(Program.createCleanWordsFromWords("ADD:VAR_1;")[0], vars, 0.0F), 0.0);
        assertEquals(-1.0F, Word.eval(Program.createCleanWordsFromWords("SUBTRACT:VAR_1;")[0], vars, 0.0F), 0.0);
        assertEquals(15.0F, Word.eval(Program.createCleanWordsFromWords("MULTIPLY:VAR_3;")[0], vars, 5.0F), 0.0);
        assertEquals(3.0F,  Word.eval(Program.createCleanWordsFromWords("DIVIDE:VAR_2;")[0], vars, 6.0F), 0.0);
        assertEquals(0.0F,  Word.eval(Program.createCleanWordsFromWords("DIVIDE:VAR_0;")[0], vars, 6.0F), 0.0);
        assertEquals(0.0F,  Word.eval(Program.createCleanWordsFromWords("DIVIDE:VAR_2;")[0], vars, 0.0F), 0.0);
        
        assertEquals(8.0F,  Word.eval(Program.createCleanWordsFromWords("ADD_CONSTANT:VAR_2;")[0], vars, 6.0F), 0.0);
        assertEquals(4.0F,  Word.eval(Program.createCleanWordsFromWords("SUBTRACT_CONSTANT:VAR_2;")[0], vars, 6.0F), 0.0);
        assertEquals(12.0F, Word.eval(Program.createCleanWordsFromWords("MULTIPLY_CONSTANT:VAR_2;")[0], vars, 6.0F), 0.0);
        assertEquals(3.0F,  Word.eval(Program.createCleanWordsFromWords("DIVIDE_CONSTANT:VAR_2;")[0], vars, 6.0F), 0.0);
        assertEquals(0.0F,  Word.eval(Program.createCleanWordsFromWords("DIVIDE_CONSTANT:VAR_0;")[0], vars, 6.0F), 0.0);
        assertEquals(0.0F,  Word.eval(Program.createCleanWordsFromWords("DIVIDE_CONSTANT:VAR_2;")[0], vars, 0.0F), 0.0);
    }

    
}
