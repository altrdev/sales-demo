package com.sales.taxes.demo.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * Created by alessandrotravi on 27/02/2019.
 */
@Data
@NoArgsConstructor
public class Invoice implements Serializable {
    private static final long serialVersionUID = 221707435390921526L;

    private List<Product> products;
    private BigDecimal taxesAmount = new BigDecimal("0");
    private BigDecimal totalAmount = new BigDecimal("0");
}
