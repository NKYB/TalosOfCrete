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
    
    public WordTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
        
        config = new Config();
        config.population_max_generations = 1000;
        config.population_max_size = 10;
        
        data = new Data();
        data.createSequential(5, 5);
    }

    public void testCreateRandom() {
        System.out.println("createRandom");
        int numOfVars = data.inputData[0].length;
        String expResult = "";
        for (int i = 0; i < 1000; i++) {
            String result = Word.createRandom(numOfVars, config);
            assertNotSame(result, "");
        }
    }
    
    public void testInit(){
        Word word = new Word();
    }

    public void testEval() {
        System.out.println("eval");
        float[] vars = data.inputData[0];
        assertEquals(1.0F,  Word.eval("ADD:VAR_1;", vars, 0.0F), 0.0);
        assertEquals(-1.0F, Word.eval("SUBTRACT:VAR_1;", vars, 0.0F), 0.0);
        assertEquals(15.0F, Word.eval("MULTIPLY:VAR_3;", vars, 5.0F), 0.0);
        assertEquals(3.0F,  Word.eval("DIVIDE:VAR_2;", vars, 6.0F), 0.0);
        assertEquals(0.0F,  Word.eval("DIVIDE:VAR_0;", vars, 6.0F), 0.0);
        assertEquals(0.0F,  Word.eval("DIVIDE:VAR_2;", vars, 0.0F), 0.0);
        
        assertEquals(8.0F,  Word.eval("ADD_CONSTANT:VAR_2;", vars, 6.0F), 0.0);
        assertEquals(4.0F,  Word.eval("SUBTRACT_CONSTANT:VAR_2;", vars, 6.0F), 0.0);
        assertEquals(12.0F, Word.eval("MULTIPLY_CONSTANT:VAR_2;", vars, 6.0F), 0.0);
        assertEquals(3.0F,  Word.eval("DIVIDE_CONSTANT:VAR_2;", vars, 6.0F), 0.0);
        assertEquals(0.0F,  Word.eval("DIVIDE_CONSTANT:VAR_0;", vars, 6.0F), 0.0);
        assertEquals(0.0F,  Word.eval("DIVIDE_CONSTANT:VAR_2;", vars, 0.0F), 0.0);
    }

    public void testReplace() {
        System.out.println("replace");
        assertEquals("rqplacq tqst", Word.replace("replace test", "e", "q"));
        assertEquals(null, Word.replace(null, "e", "q"));
        assertEquals("", Word.replace("", "e", "q"));
    }

    public void testCreate() {
        System.out.println("create");
        int actionIndex = 0;
        int numOfVars = 5;
        String result = Word.create(actionIndex, numOfVars, config);
        assertNotSame(result, "");
    }

    public void testGetRndInt() {
        System.out.println("GetRndInt");
        int min = 0;
        int max = 2;
        boolean other_flag = false;
        boolean zero_flag = false;
        boolean one_flag = false;
        boolean two_flag = false;
        for (int i = 0; i < 1000; i++) {
            int result = Word.GetRndInt(min, max);
            if (result == 0){
                zero_flag = true;
            } else if (result == 1){
                one_flag = true;
            } else if (result == 2){
                    two_flag = true;
            } else {
                other_flag = true;
            }
        }
        assertTrue(!other_flag && zero_flag && one_flag && two_flag);
    }
}
