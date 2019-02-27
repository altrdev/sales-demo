package com.sales.taxes.demo.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.sales.taxes.demo.strategy.BillingStrategy;
import com.sales.taxes.demo.utility.MathUtility;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Transient;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static com.sales.taxes.demo.utility.MathUtility.roundNumber;

/**
 * Created by alessandrotravi on 27/02/2019.
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME,
        property = "category")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Book.class, name = "book"),
        @JsonSubTypes.Type(value = Food.class, name = "food"),
        @JsonSubTypes.Type(value = Medical.class, name = "medical"),
        @JsonSubTypes.Type(value = Other.class, name = "other")
})
@AllArgsConstructor
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class Product implements BillingStrategy {
    private String description;
    private String category;
    private Double price;
    private Integer quantity;
    private Boolean imported;

    @Transient
    private Double totalPrice = 0d;

    public Product() {
    }

    /**
     * Common Logic for import tax
     *
     * @return import tax amount
     */
    @Override
    public BigDecimal importTaxCalculator() {
        if (imported) {
            BigDecimal tax = MathUtility.getTaxAmount(this.getPrice(), 5);
            tax = tax.multiply(new BigDecimal(this.getQuantity()));
            tax = roundNumber(tax);
            return tax;
        }

        return new BigDecimal("0");
    }

    /**
     * Common Logic for total price
     *
     * @param taxes previously calculated
     * @return total price amount
     */
    @Override
    public BigDecimal totalPriceCalculator(BigDecimal taxes) {
        BigDecimal totalPrice = new BigDecimal(this.getPrice()).multiply(new BigDecimal(this.getQuantity()));
        totalPrice = totalPrice.add(taxes);
        totalPrice = totalPrice.setScale(2, RoundingMode.HALF_UP);
        this.setTotalPrice(totalPrice.doubleValue());
        return totalPrice;
    }
}
