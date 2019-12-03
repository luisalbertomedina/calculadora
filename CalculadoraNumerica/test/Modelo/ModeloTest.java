/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class ModeloTest {
    
    public ModeloTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of calc method, of class Modelo.
     */
    @Test
    public void testCalc1() throws Exception {
        System.out.println("calc");
        String expresion = "(5+6)*2-3";
        Modelo instance = new Modelo();
        double expResult = 19;
        double result = instance.calc(expresion);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        if (expResult!=result) {
            fail("The test case is a prototype.");
        }
    }
    /**
     * Test of calc method, of class Modelo.
     */
    @Test
    public void testCalc2() throws Exception {
        System.out.println("calc");
        String expresion = "(5-3)^2-3";
        Modelo instance = new Modelo();
        double expResult = 1;
        double result = instance.calc(expresion);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        if (expResult!=result) {
            fail("The test case is a prototype.");
        }
    }
    /**
     * Test of calc method, of class Modelo.
     */
    @Test
    public void testCalc3() throws Exception {
        System.out.println("calc");
        String expresion = "6+15/3*2-1";
        Modelo instance = new Modelo();
        double expResult = 15;
        double result = instance.calc(expresion);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        if (expResult!=result) {
            fail("The test case is a prototype.");
        }
    }
    /**
     * Test of calc method, of class Modelo.
     */
    @Test
    public void testCalc4() throws Exception {
        System.out.println("calc");
        String expresion = "2^3+(6/3-2)";
        Modelo instance = new Modelo();
        double expResult = 8;
        double result = instance.calc(expresion);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        if (expResult!=result) {
            fail("The test case is a prototype.");
        }
    }
    /**
     * Test of calc method, of class Modelo.
     */
    @Test
    public void testCalc5() throws Exception {
        System.out.println("calc");
        String expresion = "(25/(2*5-5))";
        Modelo instance = new Modelo();
        double expResult = 5;
        double result = instance.calc(expresion);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        if (expResult!=result) {
            fail("The test case is a prototype.");
        }
    }
}
