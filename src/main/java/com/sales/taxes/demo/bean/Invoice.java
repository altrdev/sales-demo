package com.sales.taxes.demo.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
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
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Invoice implements Serializable {
    private static final long serialVersionUID = 221707435390921526L;

    private String id;
    private List<Product> products;
    private BigDecimal taxesAmount = new BigDecimal("0");
    private BigDecimal totalAmount = new BigDecimal("0");

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n");
        stringBuilder.append("OUTPUT\n\n");
        stringBuilder.append("Output: " + id + "\n");
        products.forEach(product -> {
            stringBuilder.append(product.getQuantity() + (product.getImported() ? " imported " : " ") + product.getDescription() + ": " + product.getTotalPrice() + "\n");
        });

        stringBuilder.append("Sales Taxes: " + taxesAmount + "\n");
        stringBuilder.append("Total: " + totalAmount + "\n\n");
        stringBuilder.append("------------------------");

        return stringBuilder.toString();
    }
}
