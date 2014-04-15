/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.talosofcrete.ai;

import junit.framework.TestCase;

public class ProgramTest extends TestCase {
    
    /**
     * 
     * @param testName 
     */
    public ProgramTest(String testName) {
        super(testName);
    }
    
    public void testEval() {
        System.out.println("eval");
        
        Data data = new Data();
        data.createSequential(2, 5);
        
        Config config = new Config();
        config.population_max_generations = 1;
        config.population_max_size = 1;
        
        Program program = new Program(config, data);
        
        program.words = "ADD:VAR_1;";
        program.eval();
        assertEquals(12.0F, program.score);
        
        program.words = "ADD:VAR_1;ADD:VAR_3;";
        program.eval();
        assertEquals(8.0F, program.score);
    }
    
    public void testScoreWord(){
        System.out.println("scoreWord");
        
        Data data = new Data();
        data.createSequential(1, 5);
        
        Config config = new Config();
        config.population_max_generations = 1;
        config.population_max_size = 1;
        
        Program program = new Program(config, data);

        assertEquals(4.0F, program.scoreWord(Program.createCleanWordsFromWords("ADD:VAR_4;"), data.inputData[0]));
        assertEquals(4.0F, program.scoreWord(Program.createCleanWordsFromWords("ADD:VAR_4;ADD:VAR_0;"), data.inputData[0]));
        assertEquals(11.0F, program.scoreWord(Program.createCleanWordsFromWords("ADD:VAR_4;ADD:VAR_3;ADD:VAR_0;ADD:VAR_4;"), data.inputData[0]));
        
        assertEquals(-4.0F, program.scoreWord(Program.createCleanWordsFromWords("SUBTRACT:VAR_4;"), data.inputData[0]));
        assertEquals(-4.0F, program.scoreWord(Program.createCleanWordsFromWords("SUBTRACT:VAR_4;SUBTRACT:VAR_0;"), data.inputData[0]));
        assertEquals(-11.0F, program.scoreWord(Program.createCleanWordsFromWords("SUBTRACT:VAR_4;SUBTRACT:VAR_3;SUBTRACT:VAR_0;SUBTRACT:VAR_4;"), data.inputData[0]));
        
        assertEquals(0.0F, program.scoreWord(Program.createCleanWordsFromWords("MULTIPLY:VAR_4;"), data.inputData[0]));
        assertEquals(0.0F, program.scoreWord(Program.createCleanWordsFromWords("MULTIPLY:VAR_4;MULTIPLY:VAR_0;"), data.inputData[0]));
        assertEquals(0.0F, program.scoreWord(Program.createCleanWordsFromWords("MULTIPLY:VAR_4;MULTIPLY:VAR_3;MULTIPLY:VAR_0;MULTIPLY:VAR_4;"), data.inputData[0]));
        assertEquals(16.0F, program.scoreWord(Program.createCleanWordsFromWords("ADD:VAR_4;MULTIPLY:VAR_4;"), data.inputData[0]));
        assertEquals(0.0F, program.scoreWord(Program.createCleanWordsFromWords("ADD:VAR_4;MULTIPLY:VAR_4;MULTIPLY:VAR_0;"), data.inputData[0]));
        assertEquals(28.0F, program.scoreWord(Program.createCleanWordsFromWords("ADD:VAR_4;MULTIPLY:VAR_4;MULTIPLY:VAR_2;SUBTRACT:VAR_4;"), data.inputData[0]));
        
        assertEquals(0.0F, program.scoreWord(Program.createCleanWordsFromWords("DIVIDE:VAR_4;"), data.inputData[0]));
        assertEquals(0.0F, program.scoreWord(Program.createCleanWordsFromWords("DIVIDE:VAR_4;DIVIDE:VAR_0;"), data.inputData[0]));
        assertEquals(0.0F, program.scoreWord(Program.createCleanWordsFromWords("DIVIDE:VAR_4;DIVIDE:VAR_3;DIVIDE:VAR_0;DIVIDE:VAR_4;"), data.inputData[0]));
        assertEquals(2.0F, program.scoreWord(Program.createCleanWordsFromWords("ADD:VAR_4;DIVIDE:VAR_2;"), data.inputData[0]));
        assertEquals(0.0F, program.scoreWord(Program.createCleanWordsFromWords("ADD:VAR_4;DIVIDE:VAR_4;DIVIDE:VAR_0;"), data.inputData[0]));
        assertEquals(-3.5F, program.scoreWord(Program.createCleanWordsFromWords("ADD:VAR_4;DIVIDE:VAR_4;DIVIDE:VAR_2;SUBTRACT:VAR_4;"), data.inputData[0]));
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
    
    public void testModify() {
//        System.out.println("modify");
//        Program[] programs = null;
//        Program instance = null;
//        instance.modify(programs);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    public void testModifyByAdding() {
        System.out.println("modifyByAdding");
        
        Config config = new Config();
        config.population_max_generations = 1000;
        config.population_max_size = 10;
        
        Data data = new Data();
        data.createRandom(5, 5);
        
        Program program = new Program(config, data);
        
        program.words = "ADD:VAR_4;DIVIDE:VAR_4;DIVIDE:VAR_2;SUBTRACT:VAR_4;";
        String[] wordList = program.createWordListFromWords("ADD:VAR_4;DIVIDE:VAR_4;DIVIDE:VAR_2;SUBTRACT:VAR_4;");
        program.modifyByAdding(wordList, "CHANGE_TO;");
        assertEquals(program.words,"ADD:VAR_4;DIVIDE:VAR_4;DIVIDE:VAR_2;SUBTRACT:VAR_4;CHANGE_TO;");
        
        program.words = "";
        wordList = program.createWordListFromWords("");
        program.modifyByAdding(wordList, "CHANGE_TO;");
        assertEquals(program.words,"CHANGE_TO;");
    }

    public void testModifyByChanging() {
        System.out.println("modifyByChanging");
        
        Config config = new Config();
        config.population_max_generations = 1000;
        config.population_max_size = 10;
        
        Data data = new Data();
        data.createRandom(5, 5);
        
        Program program = new Program(config, data);
        
        program.words = "ADD:VAR_4;DIVIDE:VAR_4;DIVIDE:VAR_2;SUBTRACT:VAR_4;";
        String[] wordList = program.createWordListFromWords("ADD:VAR_4;DIVIDE:VAR_4;DIVIDE:VAR_2;SUBTRACT:VAR_4;");
        program.modifyByChanging(0, wordList, "CHANGE_TO");
        assertEquals(program.words,"CHANGE_TO;DIVIDE:VAR_4;DIVIDE:VAR_2;SUBTRACT:VAR_4;");
        
        program.words = "ADD:VAR_4;DIVIDE:VAR_4;DIVIDE:VAR_2;SUBTRACT:VAR_4;";
        wordList = program.createWordListFromWords("ADD:VAR_4;DIVIDE:VAR_4;DIVIDE:VAR_2;SUBTRACT:VAR_4;");
        program.modifyByChanging(1, wordList, "CHANGE_TO");
        assertEquals(program.words,"ADD:VAR_4;CHANGE_TO;DIVIDE:VAR_2;SUBTRACT:VAR_4;");
        
        program.words = "ADD:VAR_4;DIVIDE:VAR_4;DIVIDE:VAR_2;SUBTRACT:VAR_4;";
        wordList = program.createWordListFromWords("ADD:VAR_4;DIVIDE:VAR_4;DIVIDE:VAR_2;SUBTRACT:VAR_4;");
        program.modifyByChanging(3, wordList, "CHANGE_TO");
        assertEquals(program.words,"ADD:VAR_4;DIVIDE:VAR_4;DIVIDE:VAR_2;CHANGE_TO;");
        
        program.words = "ADD:VAR_4;DIVIDE:VAR_4;DIVIDE:VAR_2;SUBTRACT:VAR_4;";
        wordList = program.createWordListFromWords("ADD:VAR_4;DIVIDE:VAR_4;DIVIDE:VAR_2;SUBTRACT:VAR_4;");
        program.modifyByChanging(4, wordList, "CHANGE_TO");
        assertEquals(program.words,"ADD:VAR_4;DIVIDE:VAR_4;DIVIDE:VAR_2;SUBTRACT:VAR_4;");
        
        program.words = "ADD:VAR_4;DIVIDE:VAR_4;DIVIDE:VAR_2;SUBTRACT:VAR_4;";
        wordList = program.createWordListFromWords("ADD:VAR_4;DIVIDE:VAR_4;DIVIDE:VAR_2;SUBTRACT:VAR_4;");
        program.modifyByChanging(-1, wordList, "CHANGE_TO");
        assertEquals(program.words,"ADD:VAR_4;DIVIDE:VAR_4;DIVIDE:VAR_2;SUBTRACT:VAR_4;");
    }

    public void testModifyByDeleting() {
        Config config = new Config();
        config.population_max_generations = 1000;
        config.population_max_size = 10;
        
        Data data = new Data();
        data.createRandom(5, 5);
        
        Program program = new Program(config, data);
        
        program.words = "ADD:VAR_4;DIVIDE:VAR_4;DIVIDE:VAR_2;SUBTRACT:VAR_4;";
        String[] wordList = program.createWordListFromWords("ADD:VAR_4;DIVIDE:VAR_4;DIVIDE:VAR_2;SUBTRACT:VAR_4;");
        program.modifyByDeleting(0, wordList);
        assertEquals(program.words,"DIVIDE:VAR_4;DIVIDE:VAR_2;SUBTRACT:VAR_4;");
        
        program.words = "ADD:VAR_4;DIVIDE:VAR_4;DIVIDE:VAR_2;SUBTRACT:VAR_4;";
        wordList = program.createWordListFromWords("ADD:VAR_4;DIVIDE:VAR_4;DIVIDE:VAR_2;SUBTRACT:VAR_4;");
        program.modifyByDeleting(2, wordList);
        assertEquals(program.words,"ADD:VAR_4;DIVIDE:VAR_4;SUBTRACT:VAR_4;");
        
        program.words = "ADD:VAR_4;DIVIDE:VAR_4;DIVIDE:VAR_2;SUBTRACT:VAR_4;";
        wordList = program.createWordListFromWords("ADD:VAR_4;DIVIDE:VAR_4;DIVIDE:VAR_2;SUBTRACT:VAR_4;");
        program.modifyByDeleting(3, wordList);
        assertEquals(program.words,"ADD:VAR_4;DIVIDE:VAR_4;DIVIDE:VAR_2;");
        
        program.words = "ADD:VAR_4;DIVIDE:VAR_4;DIVIDE:VAR_2;SUBTRACT:VAR_4;";
        wordList = program.createWordListFromWords("ADD:VAR_4;DIVIDE:VAR_4;DIVIDE:VAR_2;SUBTRACT:VAR_4;");
        program.modifyByDeleting(4, wordList);
        assertEquals(program.words,"ADD:VAR_4;DIVIDE:VAR_4;DIVIDE:VAR_2;SUBTRACT:VAR_4;");
    }

    public void testModifyByCrossing() {
//        System.out.println("modifyByCrossing");
//        Program programToCrossFrom = null;
//        Program instance = null;
//        instance.modifyByCrossing(programToCrossFrom);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }
}
