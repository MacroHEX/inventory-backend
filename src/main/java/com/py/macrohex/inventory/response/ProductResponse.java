package com.py.macrohex.inventory.response;

import com.py.macrohex.inventory.model.Product;

import java.util.List;

public class ProductResponse {

  List<Product> products;

  public List<Product> getProducts() {
    return products;
  }

  public void setProducts(List<Product> products) {
    this.products = products;
  }
}
