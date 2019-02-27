package com.sales.taxes.demo.bean;

import com.sales.taxes.demo.utility.MathUtility;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by alessandrotravi on 27/02/2019.
 */
@Data
public class Other extends Product {
    public Other() {
        super();
    }

    @Override
    public BigDecimal taxCalculator() {
        BigDecimal tax = MathUtility.getTaxAmount(this.getPrice(), 10);
        tax = tax.multiply(new BigDecimal(this.getQuantity()));
        //tax = roundNumber(tax); //TODO vedere se serve
        return tax;
    }
}
