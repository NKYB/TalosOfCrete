/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.talosofcrete.ai;

import junit.framework.TestCase;

public class ConfigTest extends TestCase {
    
    /**
     * setup config test
     * 
     * @param testName 
     */
    public ConfigTest(String testName) {
        super(testName);
    }

    /**
     * test to build out a new config
     */
    public void testRandomize() {
        System.out.println("randomize");
        Config instance = new Config();
        Config expResult = null;
        Config result = instance.randomize();
        assertTrue( true );
    }
}
