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
public class UtilsTest extends TestCase {
    
    public UtilsTest(String testName) {
        super(testName);
    }
    
    public void testReplace() {
        System.out.println("replace");
        
        Utils utils = new Utils();
        
        assertEquals("rqplacq tqst", Utils.replace("replace test", "e", "q"));
        assertEquals(null, Utils.replace(null, "e", "q"));
        assertEquals("", Utils.replace("", "e", "q"));
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
            int result = Utils.getRndInt(min, max);
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
