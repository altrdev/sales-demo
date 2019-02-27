package com.sales.taxes.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.sales.taxes.demo.bean.Product;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * Created by alessandrotravi on 27/02/2019.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Document(collection = "basket")
public class Basket implements Serializable {
    private static final long serialVersionUID = 5023695862140341391L;

    private String basketId;
    private List<Product> products;
}
