package com.sales.taxes.demo.controller;

import com.sales.taxes.demo.bean.Invoice;
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
public class InvoiceController {

    private final BillingService billingService;

    @RequestMapping(method = RequestMethod.GET, produces = {"application/json", "text/plain"})
    public ResponseEntity<List<Invoice>> getAll() {
        log.info("Incoming getOne request");
        try {
            return new ResponseEntity<>(billingService.getAllInvoice(), HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error getOne: {}", e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/{basketId}", method = RequestMethod.GET, produces = {"application/json", "text/plain"})
    public ResponseEntity<Invoice> getOne(@PathVariable("basketId") String basketId) {
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
