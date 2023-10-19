package com.py.macrohex.inventory.response;

public class ProductResponseRest extends ResponseRest {

  private ProductResponse productResponse = new ProductResponse();

  public ProductResponse getProduct() {
    return productResponse;
  }

  public void setProduct(ProductResponse productResponse) {
    this.productResponse = productResponse;
  }
}
