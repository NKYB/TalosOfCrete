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
public class ConfigTest extends TestCase {
    
    public ConfigTest(String testName) {
        super(testName);
    }

    public void testRandomize() {
        System.out.println("randomize");
        Config instance = new Config();
        Config expResult = null;
        Config result = instance.randomize();
        assertTrue( true );
    }
}
