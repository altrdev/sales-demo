package com.sales.taxes.demo.controller;

import com.sales.taxes.demo.bean.Receipt;
import com.sales.taxes.demo.exception.NotFoundException;
import com.sales.taxes.demo.service.BillingService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by alessandrotravi on 27/02/2019.
 */
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(value = "/v1/receipts", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@Slf4j
public class ReceiptController {

    private final BillingService billingService;

    /**
     * Api that returns Receipt list
     *
     * @return response json
     */
    @RequestMapping(method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<List<Receipt>> getAll() {
        log.info("Incoming getOne request");
        try {
            return new ResponseEntity<>(billingService.getAllInvoice(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error getOne: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Api that returns single invoice
     *
     * @param basketId unique identifier for basket item
     * @return
     */
    @RequestMapping(value = "/{basketId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Receipt> getOne(@PathVariable("basketId") String basketId) {
        log.info("Incoming getOne request");
        try {
            return new ResponseEntity<>(billingService.getInvoice(basketId), HttpStatus.OK);
        } catch (NotFoundException nfe) {
            throw new NotFoundException(nfe.getMessage());
        } catch (Exception e) {
            log.error("Error getOne: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
