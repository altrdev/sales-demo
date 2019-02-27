package com.sales.taxes.demo;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sales.taxes.demo.entity.Basket;
import com.sales.taxes.demo.repository.BasketRepository;
import com.sales.taxes.demo.service.BillingService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * Created by alessandrotravi on 27/02/2019.
 */
@Component
@AllArgsConstructor
public class MongoBootstrap implements CommandLineRunner {

    private final BasketRepository basketRepository;
    private final BillingService billingService;

    @Override
    public void run(String... args) throws Exception {
        // clear repository for demo purpose
        basketRepository.deleteAll();

        // set embedded database from json file
        setBasketRepository();

        //billingService.getInvoice("268f1260-0215-4e36-8c4f-dbc0dcd56812");
        billingService.getAllInvoice();

    }

    /**
     * Method for setting example dataset
     */
    private void setBasketRepository() {
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Basket>> typeReference = new TypeReference<>() {
        };
        InputStream inputStream = TypeReference.class.getResourceAsStream("/json/data_demo.json");
        try {
            List<Basket> invoices = mapper.readValue(inputStream, typeReference);
            basketRepository.saveAll(invoices);
            System.out.println("Baskets Saved!");
        } catch (IOException e) {
            System.out.println("Unable to save baskets: " + e.getMessage());
        }
    }

}
