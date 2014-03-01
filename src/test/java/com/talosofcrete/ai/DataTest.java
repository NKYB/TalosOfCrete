package com.talosofcrete.ai;

import junit.framework.TestCase;

/**
 *
 * @author nbuwald
 */
public class DataTest extends TestCase {
    
    public DataTest(String testName) {
        super(testName);
    }

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
