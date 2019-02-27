package com.sales.taxes.demo.utility;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by alessandrotravi on 27/02/2019.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class MathUtilityTest {

    /**
     * Unit test with an expected results
     */
    @Test
    public void getTaxAmountExpected() {
        BigDecimal taxProduct1 = MathUtility.getTaxAmount(12.49d, 0);
        BigDecimal taxProduct2 = MathUtility.getTaxAmount(14.99d, 10);
        BigDecimal taxProduct3 = MathUtility.getTaxAmount(0.85d, 0);
        BigDecimal taxProduct4 = MathUtility.getTaxAmount(10.00d, 5);
        BigDecimal taxProduct5 = MathUtility.getTaxAmount(47.50d, 15);
        BigDecimal taxProduct6 = MathUtility.getTaxAmount(27.99d, 15);
        BigDecimal taxProduct7 = MathUtility.getTaxAmount(18.99d, 10);
        BigDecimal taxProduct8 = MathUtility.getTaxAmount(9.75d, 0);
        BigDecimal taxProduct9 = MathUtility.getTaxAmount(11.25d, 5);

        double[] expectedArray = {0d, 1.50d, 0d, 0.50d, 7.15d, 4.20d, 1.90d, 0d, 0.60d};
        double[] resultArray = {
                taxProduct1.doubleValue(),
                taxProduct2.doubleValue(),
                taxProduct3.doubleValue(),
                taxProduct4.doubleValue(),
                taxProduct5.doubleValue(),
                taxProduct6.doubleValue(),
                taxProduct7.doubleValue(),
                taxProduct8.doubleValue(),
                taxProduct9.doubleValue()};

        assertArrayEquals(expectedArray, resultArray, 0);
    }

    @Test
    public void roundNumber() {
        BigDecimal bd1 = MathUtility.roundNumber(new BigDecimal(0.234));
        assertEquals("0.25", bd1.toString());

        BigDecimal bd2 = MathUtility.roundNumber(new BigDecimal(2.96));
        assertEquals("3.00", bd2.toString());

        BigDecimal bd3 = MathUtility.roundNumber(new BigDecimal(2034.232131231));
        assertEquals("2034.25", bd3.toString());

    }
}