/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.talosofcrete.ai;

import junit.framework.TestCase;

/**
 *
 * @author Administrator
 */
public class DataTest extends TestCase {
    
    public DataTest(String testName) {
        super(testName);
    }

    /**
     * Test of createRandom method, of class Data.
     */
    public void testCreateRandom() {
        System.out.println("createRandom");
        int numRows = 5;
        int numCols = 5;
        Data instance = new Data();
        instance.createRandom(numRows, numCols);
        assertTrue( true );
    }

    /**
     * Test of createSequential method, of class Data.
     */
    public void testCreateSequential() {
        System.out.println("createSequential");
        int numRows = 5;
        int numCols = 5;
        Data instance = new Data();
        instance.createSequential(numRows, numCols);
        assertTrue( true );
    }
}
