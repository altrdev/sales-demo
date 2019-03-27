package com.sales.taxes.demo.controller;

import com.sales.taxes.demo.bean.Receipt;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * Created by alessandrotravi on 27/02/2019.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReceiptControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAll() {

        HttpHeaders headers = new HttpHeaders();
        HttpEntity<Void> entity = new HttpEntity<>(headers);
        ResponseEntity<List<Receipt>> response = this.restTemplate.exchange("/v1/receipts", HttpMethod.GET, entity, new ParameterizedTypeReference<List<Receipt>>() {
        });
        assertEquals("HttpStatus", HttpStatus.OK, response.getStatusCode());

        List<Receipt> receipts = response.getBody();

        assertEquals(HttpStatus.OK, response.getStatusCode());

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

    @Test
    public void getOne() {
        ResponseEntity<Receipt> out = this.restTemplate.getForEntity("/v1/receipts/f0f56086-6f4c-4ede-9700-35bd351f78af", Receipt.class);
        assertEquals(HttpStatus.OK, out.getStatusCode());
        Receipt receipt1 = out.getBody();

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

    @Test
    public void getOneNotFound() {
        ResponseEntity<Receipt> out = this.restTemplate.getForEntity("/v1/receipts/1234", Receipt.class);
        assertEquals(HttpStatus.NOT_FOUND, out.getStatusCode());
    }
}