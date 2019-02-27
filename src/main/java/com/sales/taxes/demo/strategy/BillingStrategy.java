package com.sales.taxes.demo.strategy;

import java.math.BigDecimal;

/**
 * Created by alessandrotravi on 27/02/2019.
 */
public interface BillingStrategy {
    /**
     * @return
     */
    BigDecimal taxCalculator();

    /**
     * @return
     */
    BigDecimal importTaxCalculator();

    BigDecimal calculateTotalPrice(BigDecimal taxes);
}
