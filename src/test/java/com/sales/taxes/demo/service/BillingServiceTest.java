package com.sales.taxes.demo.service;

import com.sales.taxes.demo.bean.Invoice;
import com.sales.taxes.demo.entity.Basket;
import com.sales.taxes.demo.repository.BasketRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

/**
 * Created by alessandrotravi on 27/02/2019.
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class BillingServiceTest {

    @Autowired
    private BasketRepository basketRepository;
    @Autowired
    private BillingService billingService;


    /**
     * Unit test for Item1
     */
    @Test
    public void getInvoiceLogicItem1() {
        Optional<Basket> basket = basketRepository.findOneByBasketId("f0f56086-6f4c-4ede-9700-35bd351f78af");
        if (!basket.isPresent())
            fail("Should not be here!");

        Invoice invoice1 = billingService.getInvoice(basket.get());

        assertEquals("42.32", invoice1.getTotalAmount().toString());
        assertEquals("1.50", invoice1.getTaxesAmount().toString());

        assertEquals(3, invoice1.getProducts().size());

        assertEquals(2, invoice1.getProducts().get(0).getQuantity().intValue());
        assertEquals("12.49", invoice1.getProducts().get(0).getPrice().toString());
        assertEquals("book", invoice1.getProducts().get(0).getDescription());
        assertEquals("24.98", invoice1.getProducts().get(0).getTotalPrice().toString());
        assertFalse(invoice1.getProducts().get(0).getImported());

        assertEquals(1, invoice1.getProducts().get(1).getQuantity().intValue());
        assertEquals("14.99", invoice1.getProducts().get(1).getPrice().toString());
        assertEquals("music CD", invoice1.getProducts().get(1).getDescription());
        assertEquals("16.49", invoice1.getProducts().get(1).getTotalPrice().toString());
        assertFalse(invoice1.getProducts().get(1).getImported());

        assertEquals(1, invoice1.getProducts().get(2).getQuantity().intValue());
        assertEquals("0.85", invoice1.getProducts().get(2).getPrice().toString());
        assertEquals("chocolate bar", invoice1.getProducts().get(2).getDescription());
        assertEquals("0.85", invoice1.getProducts().get(2).getTotalPrice().toString());
        assertFalse(invoice1.getProducts().get(2).getImported());

    }

    /**
     * Unit test for Item2
     */
    @Test
    public void getInvoiceLogicItem2() {
        Optional<Basket> basket = basketRepository.findOneByBasketId("4616e590-089c-4794-b265-2702bb079da5");
        if (!basket.isPresent())
            fail("Should not be here!");

        Invoice invoice1 = billingService.getInvoice(basket.get());

        assertEquals("65.15", invoice1.getTotalAmount().toString());
        assertEquals("7.65", invoice1.getTaxesAmount().toString());

        assertEquals(2, invoice1.getProducts().size());

        assertEquals(1, invoice1.getProducts().get(0).getQuantity().intValue());
        assertEquals("10.0", invoice1.getProducts().get(0).getPrice().toString());
        assertEquals("box of chocolates", invoice1.getProducts().get(0).getDescription());
        assertEquals("10.5", invoice1.getProducts().get(0).getTotalPrice().toString());
        assertTrue(invoice1.getProducts().get(0).getImported());

        assertEquals(1, invoice1.getProducts().get(1).getQuantity().intValue());
        assertEquals("47.5", invoice1.getProducts().get(1).getPrice().toString());
        assertEquals("bottle of perfume", invoice1.getProducts().get(1).getDescription());
        assertEquals("54.65", invoice1.getProducts().get(1).getTotalPrice().toString());
        assertTrue(invoice1.getProducts().get(1).getImported());

    }

    /**
     * Unit test for Item3
     */
    @Test
    public void getInvoiceLogicItem3() {
        Optional<Basket> basket = basketRepository.findOneByBasketId("268f1260-0215-4e36-8c4f-dbc0dcd56812");
        if (!basket.isPresent())
            fail("Should not be here!");
        
        Invoice invoice1 = billingService.getInvoice(basket.get());

        assertEquals("98.38", invoice1.getTotalAmount().toString());
        assertEquals("7.90", invoice1.getTaxesAmount().toString());

        assertEquals(4, invoice1.getProducts().size());

        assertEquals(1, invoice1.getProducts().get(0).getQuantity().intValue());
        assertEquals("27.99", invoice1.getProducts().get(0).getPrice().toString());
        assertEquals("bottle of perfume", invoice1.getProducts().get(0).getDescription());
        assertEquals("32.19", invoice1.getProducts().get(0).getTotalPrice().toString());
        assertTrue(invoice1.getProducts().get(0).getImported());

        assertEquals(1, invoice1.getProducts().get(1).getQuantity().intValue());
        assertEquals("18.99", invoice1.getProducts().get(1).getPrice().toString());
        assertEquals("bottle of perfume", invoice1.getProducts().get(1).getDescription());
        assertEquals("20.89", invoice1.getProducts().get(1).getTotalPrice().toString());
        assertFalse(invoice1.getProducts().get(1).getImported());

        assertEquals(1, invoice1.getProducts().get(2).getQuantity().intValue());
        assertEquals("9.75", invoice1.getProducts().get(2).getPrice().toString());
        assertEquals("packet of headache pills", invoice1.getProducts().get(2).getDescription());
        assertEquals("9.75", invoice1.getProducts().get(2).getTotalPrice().toString());
        assertFalse(invoice1.getProducts().get(2).getImported());

        assertEquals(3, invoice1.getProducts().get(3).getQuantity().intValue());
        assertEquals("11.25", invoice1.getProducts().get(3).getPrice().toString());
        assertEquals("box of chocolates", invoice1.getProducts().get(3).getDescription());
        assertEquals("35.55", invoice1.getProducts().get(3).getTotalPrice().toString());
        assertTrue(invoice1.getProducts().get(3).getImported());
    }


    @Test
    public void getAllInvoice() {

        List<Invoice> invoices = billingService.getAllInvoice();

        assertEquals(3, invoices.size());

        Invoice invoice1 = invoices.get(0);

        assertEquals("42.32", invoice1.getTotalAmount().toString());
        assertEquals("1.50", invoice1.getTaxesAmount().toString());

        assertEquals(3, invoice1.getProducts().size());

        assertEquals(2, invoice1.getProducts().get(0).getQuantity().intValue());
        assertEquals("12.49", invoice1.getProducts().get(0).getPrice().toString());
        assertEquals("book", invoice1.getProducts().get(0).getDescription());
        assertEquals("24.98", invoice1.getProducts().get(0).getTotalPrice().toString());
        assertFalse(invoice1.getProducts().get(0).getImported());

        assertEquals(1, invoice1.getProducts().get(1).getQuantity().intValue());
        assertEquals("14.99", invoice1.getProducts().get(1).getPrice().toString());
        assertEquals("music CD", invoice1.getProducts().get(1).getDescription());
        assertEquals("16.49", invoice1.getProducts().get(1).getTotalPrice().toString());
        assertFalse(invoice1.getProducts().get(1).getImported());

        assertEquals(1, invoice1.getProducts().get(2).getQuantity().intValue());
        assertEquals("0.85", invoice1.getProducts().get(2).getPrice().toString());
        assertEquals("chocolate bar", invoice1.getProducts().get(2).getDescription());
        assertEquals("0.85", invoice1.getProducts().get(2).getTotalPrice().toString());
        assertFalse(invoice1.getProducts().get(2).getImported());

        Invoice invoice2 = invoices.get(1);

        assertEquals("65.15", invoice2.getTotalAmount().toString());
        assertEquals("7.65", invoice2.getTaxesAmount().toString());

        assertEquals(2, invoice2.getProducts().size());

        assertEquals(1, invoice2.getProducts().get(0).getQuantity().intValue());
        assertEquals("10.0", invoice2.getProducts().get(0).getPrice().toString());
        assertEquals("box of chocolates", invoice2.getProducts().get(0).getDescription());
        assertEquals("10.5", invoice2.getProducts().get(0).getTotalPrice().toString());
        assertTrue(invoice2.getProducts().get(0).getImported());

        assertEquals(1, invoice2.getProducts().get(1).getQuantity().intValue());
        assertEquals("47.5", invoice2.getProducts().get(1).getPrice().toString());
        assertEquals("bottle of perfume", invoice2.getProducts().get(1).getDescription());
        assertEquals("54.65", invoice2.getProducts().get(1).getTotalPrice().toString());
        assertTrue(invoice2.getProducts().get(1).getImported());

        Invoice invoice3 = invoices.get(2);

        assertEquals("98.38", invoice3.getTotalAmount().toString());
        assertEquals("7.90", invoice3.getTaxesAmount().toString());

        assertEquals(4, invoice3.getProducts().size());

        assertEquals(1, invoice3.getProducts().get(0).getQuantity().intValue());
        assertEquals("27.99", invoice3.getProducts().get(0).getPrice().toString());
        assertEquals("bottle of perfume", invoice3.getProducts().get(0).getDescription());
        assertEquals("32.19", invoice3.getProducts().get(0).getTotalPrice().toString());
        assertTrue(invoice3.getProducts().get(0).getImported());

        assertEquals(1, invoice3.getProducts().get(1).getQuantity().intValue());
        assertEquals("18.99", invoice3.getProducts().get(1).getPrice().toString());
        assertEquals("bottle of perfume", invoice3.getProducts().get(1).getDescription());
        assertEquals("20.89", invoice3.getProducts().get(1).getTotalPrice().toString());
        assertFalse(invoice3.getProducts().get(1).getImported());

        assertEquals(1, invoice3.getProducts().get(2).getQuantity().intValue());
        assertEquals("9.75", invoice3.getProducts().get(2).getPrice().toString());
        assertEquals("packet of headache pills", invoice3.getProducts().get(2).getDescription());
        assertEquals("9.75", invoice3.getProducts().get(2).getTotalPrice().toString());
        assertFalse(invoice3.getProducts().get(2).getImported());

        assertEquals(3, invoice3.getProducts().get(3).getQuantity().intValue());
        assertEquals("11.25", invoice3.getProducts().get(3).getPrice().toString());
        assertEquals("box of chocolates", invoice3.getProducts().get(3).getDescription());
        assertEquals("35.55", invoice3.getProducts().get(3).getTotalPrice().toString());
        assertTrue(invoice3.getProducts().get(3).getImported());
    }
}