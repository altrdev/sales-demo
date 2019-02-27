package com.sales.taxes.demo.utility;

import java.math.BigDecimal;

/**
 * Created by alessandrotravi on 27/02/2019.
 */
public class MathUtility {

    private static final BigDecimal ROUND = new BigDecimal("0.05");

    /**
     * this method returns the total number of taxes
     *
     * @param number     Input number
     * @param percentage Input percentage value
     * @return amount of taxes
     */
    public static BigDecimal getTaxAmount(Double number, Integer percentage) {
        BigDecimal tax = new BigDecimal(number);
        tax = tax.multiply(new BigDecimal(percentage)).divide(new BigDecimal("100"));
        tax = roundNumber(tax);

        return tax;
    }

    /**
     * this method formats the number as required
     * Returns the value rounded up to the nearest 0.05
     *
     * @param number Input number
     * @return double with only 2 digits
     */
    public static BigDecimal roundNumber(BigDecimal number) {
        number = number.divide(ROUND);
        number = new BigDecimal(Math.ceil(number.doubleValue()));
        number = number.multiply(ROUND);
        return number;
    }
}
