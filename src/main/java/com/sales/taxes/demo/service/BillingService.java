package com.sales.taxes.demo.service;

import com.sales.taxes.demo.bean.Product;
import com.sales.taxes.demo.bean.Receipt;
import com.sales.taxes.demo.entity.Basket;
import com.sales.taxes.demo.exception.InternalServerException;
import com.sales.taxes.demo.exception.NotFoundException;
import com.sales.taxes.demo.repository.BasketRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by alessandrotravi on 27/02/2019.
 */
@Service
@AllArgsConstructor
@Slf4j
public class BillingService {

    private final BasketRepository basketRepository;

    /**
     * Returns single invoice
     *
     * @param basketId uniqueId for basket item
     * @return single Receipt
     */
    public Receipt getInvoice(String basketId) {
        Optional<Basket> basket = basketRepository.findOneByBasketId(basketId);
        if (!basket.isPresent())
            throw new NotFoundException(String.format("Id %s not found", basketId));

        return getInvoice(basket.get());
    }

    /**
     * Returns all invoices
     *
     * @return list of Receipt
     */
    public List<Receipt> getAllInvoice() {
        List<Basket> basketList = basketRepository.findAll();

        List<Receipt> receipts = new ArrayList<>();

        for (Basket basket : basketList)
            receipts.add(getInvoice(basket));


        return receipts;
    }

    /**
     * Common Logic
     *
     * @param basket Basket Entity, recovered from database
     * @return single Receipt
     */
    protected Receipt getInvoice(Basket basket) {
        try {
            Receipt receipt = new Receipt();
            receipt.setId(basket.getBasketId());
            List<Product> products = basket.getProducts()
                    .stream()
                    .peek(product -> {
                        BigDecimal taxes = product.taxCalculator().add(product.importTaxCalculator());
                        BigDecimal totalPrice = product.totalPriceCalculator(taxes);
                        receipt.setTaxesAmount(taxes.add((receipt.getTaxesAmount())));
                        receipt.setTotalAmount(receipt.getTotalAmount().add(totalPrice));
                    })
                    .collect(Collectors.toList());

            receipt.setProducts(products);

            // set to debug
            log.info(receipt.toString());

            return receipt;
        } catch (Exception e) {
            log.error("Unexpected error: {}", e.getMessage());
            throw new InternalServerException(String.format("Error processing %s", basket.getBasketId()));
        }
    }


}
