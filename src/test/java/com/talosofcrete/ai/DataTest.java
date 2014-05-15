/**
 * Test for data creation
 */
package com.talosofcrete.ai;

import junit.framework.TestCase;

public class DataTest extends TestCase {
    
    /**
     * Setup data test
     * 
     * @param testName 
     */
    public DataTest(String testName) {
        super(testName);
    }

    /**
     * Create a random set of values
     */
    public void testCreateRandom() {
        System.out.println("createRandom");
        int numRows = 5;
        int numCols = 6;
        Data data = new Data();
        data.createRandom(numRows, numCols);
        TestCase.assertEquals(data.inputData[0].length, numCols);
        TestCase.assertEquals(data.inputData.length, numRows);
        TestCase.assertEquals(data.outputData.length, numRows);
    }

    /**
     * Create a sequential set of values
     */
    public void testCreateSequential() {
        System.out.println("createSequential");
        int numRows = 5;
        int numCols = 6;
        Data data = new Data();
        data.createSequential(numRows, numCols);
        TestCase.assertEquals(data.inputData[0].length, numCols);
        TestCase.assertEquals(data.inputData.length, numRows);
        TestCase.assertEquals(data.outputData.length, numRows);
        TestCase.assertEquals(data.outputData[0], 2F);
    }
}
