package com.sales.taxes.demo.service;

import com.sales.taxes.demo.bean.Receipt;
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

        Receipt receipt1 = billingService.getInvoice(basket.get());

        assertEquals("42.32", receipt1.getTotalAmount().toString());
        assertEquals("1.50", receipt1.getTaxesAmount().toString());

        assertEquals(3, receipt1.getProducts().size());

        assertEquals(2, receipt1.getProducts().get(0).getQuantity().intValue());
        assertEquals("12.49", receipt1.getProducts().get(0).getPrice().toString());
        assertEquals("book", receipt1.getProducts().get(0).getDescription());
        assertEquals("24.98", receipt1.getProducts().get(0).getTotalPrice().toString());
        assertFalse(receipt1.getProducts().get(0).getImported());

        assertEquals(1, receipt1.getProducts().get(1).getQuantity().intValue());
        assertEquals("14.99", receipt1.getProducts().get(1).getPrice().toString());
        assertEquals("music CD", receipt1.getProducts().get(1).getDescription());
        assertEquals("16.49", receipt1.getProducts().get(1).getTotalPrice().toString());
        assertFalse(receipt1.getProducts().get(1).getImported());

        assertEquals(1, receipt1.getProducts().get(2).getQuantity().intValue());
        assertEquals("0.85", receipt1.getProducts().get(2).getPrice().toString());
        assertEquals("chocolate bar", receipt1.getProducts().get(2).getDescription());
        assertEquals("0.85", receipt1.getProducts().get(2).getTotalPrice().toString());
        assertFalse(receipt1.getProducts().get(2).getImported());

    }

    /**
     * Unit test for Item2
     */
    @Test
    public void getInvoiceLogicItem2() {
        Optional<Basket> basket = basketRepository.findOneByBasketId("4616e590-089c-4794-b265-2702bb079da5");
        if (!basket.isPresent())
            fail("Should not be here!");

        Receipt receipt1 = billingService.getInvoice(basket.get());

        assertEquals("65.15", receipt1.getTotalAmount().toString());
        assertEquals("7.65", receipt1.getTaxesAmount().toString());

        assertEquals(2, receipt1.getProducts().size());

        assertEquals(1, receipt1.getProducts().get(0).getQuantity().intValue());
        assertEquals("10.0", receipt1.getProducts().get(0).getPrice().toString());
        assertEquals("box of chocolates", receipt1.getProducts().get(0).getDescription());
        assertEquals("10.5", receipt1.getProducts().get(0).getTotalPrice().toString());
        assertTrue(receipt1.getProducts().get(0).getImported());

        assertEquals(1, receipt1.getProducts().get(1).getQuantity().intValue());
        assertEquals("47.5", receipt1.getProducts().get(1).getPrice().toString());
        assertEquals("bottle of perfume", receipt1.getProducts().get(1).getDescription());
        assertEquals("54.65", receipt1.getProducts().get(1).getTotalPrice().toString());
        assertTrue(receipt1.getProducts().get(1).getImported());

    }

    /**
     * Unit test for Item3
     */
    @Test
    public void getInvoiceLogicItem3() {
        Optional<Basket> basket = basketRepository.findOneByBasketId("268f1260-0215-4e36-8c4f-dbc0dcd56812");
        if (!basket.isPresent())
            fail("Should not be here!");

        Receipt receipt1 = billingService.getInvoice(basket.get());

        assertEquals("98.38", receipt1.getTotalAmount().toString());
        assertEquals("7.90", receipt1.getTaxesAmount().toString());

        assertEquals(4, receipt1.getProducts().size());

        assertEquals(1, receipt1.getProducts().get(0).getQuantity().intValue());
        assertEquals("27.99", receipt1.getProducts().get(0).getPrice().toString());
        assertEquals("bottle of perfume", receipt1.getProducts().get(0).getDescription());
        assertEquals("32.19", receipt1.getProducts().get(0).getTotalPrice().toString());
        assertTrue(receipt1.getProducts().get(0).getImported());

        assertEquals(1, receipt1.getProducts().get(1).getQuantity().intValue());
        assertEquals("18.99", receipt1.getProducts().get(1).getPrice().toString());
        assertEquals("bottle of perfume", receipt1.getProducts().get(1).getDescription());
        assertEquals("20.89", receipt1.getProducts().get(1).getTotalPrice().toString());
        assertFalse(receipt1.getProducts().get(1).getImported());

        assertEquals(1, receipt1.getProducts().get(2).getQuantity().intValue());
        assertEquals("9.75", receipt1.getProducts().get(2).getPrice().toString());
        assertEquals("packet of headache pills", receipt1.getProducts().get(2).getDescription());
        assertEquals("9.75", receipt1.getProducts().get(2).getTotalPrice().toString());
        assertFalse(receipt1.getProducts().get(2).getImported());

        assertEquals(3, receipt1.getProducts().get(3).getQuantity().intValue());
        assertEquals("11.25", receipt1.getProducts().get(3).getPrice().toString());
        assertEquals("box of chocolates", receipt1.getProducts().get(3).getDescription());
        assertEquals("35.55", receipt1.getProducts().get(3).getTotalPrice().toString());
        assertTrue(receipt1.getProducts().get(3).getImported());
    }


    @Test
    public void getAllInvoice() {

        List<Receipt> receipts = billingService.getAllInvoice();

        assertEquals(3, receipts.size());

        Receipt receipt1 = receipts.get(0);

        assertEquals("42.32", receipt1.getTotalAmount().toString());
        assertEquals("1.50", receipt1.getTaxesAmount().toString());

        assertEquals(3, receipt1.getProducts().size());

        assertEquals(2, receipt1.getProducts().get(0).getQuantity().intValue());
        assertEquals("12.49", receipt1.getProducts().get(0).getPrice().toString());
        assertEquals("book", receipt1.getProducts().get(0).getDescription());
        assertEquals("24.98", receipt1.getProducts().get(0).getTotalPrice().toString());
        assertFalse(receipt1.getProducts().get(0).getImported());

        assertEquals(1, receipt1.getProducts().get(1).getQuantity().intValue());
        assertEquals("14.99", receipt1.getProducts().get(1).getPrice().toString());
        assertEquals("music CD", receipt1.getProducts().get(1).getDescription());
        assertEquals("16.49", receipt1.getProducts().get(1).getTotalPrice().toString());
        assertFalse(receipt1.getProducts().get(1).getImported());

        assertEquals(1, receipt1.getProducts().get(2).getQuantity().intValue());
        assertEquals("0.85", receipt1.getProducts().get(2).getPrice().toString());
        assertEquals("chocolate bar", receipt1.getProducts().get(2).getDescription());
        assertEquals("0.85", receipt1.getProducts().get(2).getTotalPrice().toString());
        assertFalse(receipt1.getProducts().get(2).getImported());

        Receipt receipt2 = receipts.get(1);

        assertEquals("65.15", receipt2.getTotalAmount().toString());
        assertEquals("7.65", receipt2.getTaxesAmount().toString());

        assertEquals(2, receipt2.getProducts().size());

        assertEquals(1, receipt2.getProducts().get(0).getQuantity().intValue());
        assertEquals("10.0", receipt2.getProducts().get(0).getPrice().toString());
        assertEquals("box of chocolates", receipt2.getProducts().get(0).getDescription());
        assertEquals("10.5", receipt2.getProducts().get(0).getTotalPrice().toString());
        assertTrue(receipt2.getProducts().get(0).getImported());

        assertEquals(1, receipt2.getProducts().get(1).getQuantity().intValue());
        assertEquals("47.5", receipt2.getProducts().get(1).getPrice().toString());
        assertEquals("bottle of perfume", receipt2.getProducts().get(1).getDescription());
        assertEquals("54.65", receipt2.getProducts().get(1).getTotalPrice().toString());
        assertTrue(receipt2.getProducts().get(1).getImported());

        Receipt receipt3 = receipts.get(2);

        assertEquals("98.38", receipt3.getTotalAmount().toString());
        assertEquals("7.90", receipt3.getTaxesAmount().toString());

        assertEquals(4, receipt3.getProducts().size());

        assertEquals(1, receipt3.getProducts().get(0).getQuantity().intValue());
        assertEquals("27.99", receipt3.getProducts().get(0).getPrice().toString());
        assertEquals("bottle of perfume", receipt3.getProducts().get(0).getDescription());
        assertEquals("32.19", receipt3.getProducts().get(0).getTotalPrice().toString());
        assertTrue(receipt3.getProducts().get(0).getImported());

        assertEquals(1, receipt3.getProducts().get(1).getQuantity().intValue());
        assertEquals("18.99", receipt3.getProducts().get(1).getPrice().toString());
        assertEquals("bottle of perfume", receipt3.getProducts().get(1).getDescription());
        assertEquals("20.89", receipt3.getProducts().get(1).getTotalPrice().toString());
        assertFalse(receipt3.getProducts().get(1).getImported());

        assertEquals(1, receipt3.getProducts().get(2).getQuantity().intValue());
        assertEquals("9.75", receipt3.getProducts().get(2).getPrice().toString());
        assertEquals("packet of headache pills", receipt3.getProducts().get(2).getDescription());
        assertEquals("9.75", receipt3.getProducts().get(2).getTotalPrice().toString());
        assertFalse(receipt3.getProducts().get(2).getImported());

        assertEquals(3, receipt3.getProducts().get(3).getQuantity().intValue());
        assertEquals("11.25", receipt3.getProducts().get(3).getPrice().toString());
        assertEquals("box of chocolates", receipt3.getProducts().get(3).getDescription());
        assertEquals("35.55", receipt3.getProducts().get(3).getTotalPrice().toString());
        assertTrue(receipt3.getProducts().get(3).getImported());
    }
}