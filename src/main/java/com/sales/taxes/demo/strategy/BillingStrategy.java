package com.sales.taxes.demo.strategy;

import java.math.BigDecimal;

/**
 * Created by alessandrotravi on 27/02/2019.
 */
public interface BillingStrategy {
    /**
     * calculator for base taxes
     *
     * @return base tax total
     */
    BigDecimal taxCalculator();

    /**
     * Calculator for import taxes
     *
     * @return importTax total
     */
    BigDecimal importTaxCalculator();

    /**
     * Calculator for total price
     *
     * @param taxes previously calculated
     * @return total price
     */
    BigDecimal totalPriceCalculator(BigDecimal taxes);
}
