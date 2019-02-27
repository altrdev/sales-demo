package com.sales.taxes.demo.bean;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by alessandrotravi on 27/02/2019.
 */
@Data
public class Book extends Product {
    public Book() {
        super();
    }

    @Override
    public BigDecimal taxCalculator() {
        return new BigDecimal("0");
    }
}
