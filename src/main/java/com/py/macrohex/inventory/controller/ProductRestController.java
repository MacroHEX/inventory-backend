package com.py.macrohex.inventory.controller;

import com.py.macrohex.inventory.model.Product;
import com.py.macrohex.inventory.response.ProductResponseRest;
import com.py.macrohex.inventory.services.IProductService;
import com.py.macrohex.inventory.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Path;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class ProductRestController {

  @Autowired
  private IProductService productService;

  @PostMapping("/product")
  public ResponseEntity<ProductResponseRest> save(
          @RequestParam("picture") MultipartFile picture,
          @RequestParam("name") String name,
          @RequestParam("price") BigDecimal price,
          @RequestParam("quantity") BigDecimal quantity,
          @RequestParam("categoryId") Long categoryId) throws IOException {

    Product product = new Product();
    product.setName(name);
    product.setQuantity(quantity);
    product.setPrice(price);
    product.setPicture(Util.compressZLib(picture.getBytes()));

    return productService.save(product, categoryId);
  }

  @GetMapping("/product/{id}")
  public ResponseEntity<ProductResponseRest> searchById(@PathVariable Long id) {
    return productService.searchById(id);
  }

  @GetMapping("/product/filter/{name}")
  public ResponseEntity<ProductResponseRest> searchByName(@PathVariable String name) {
    return productService.searchByName(name);
  }

  @DeleteMapping("/product/{id}")
  public ResponseEntity<ProductResponseRest> delete(@PathVariable Long id) {
    return productService.delete(id);
  }

  @GetMapping("/product")
  public ResponseEntity<ProductResponseRest> search() {
    return productService.search();
  }

  @PutMapping("/product/{id}")
  public ResponseEntity<ProductResponseRest> update(
          @RequestParam("picture") MultipartFile picture,
          @RequestParam("name") String name,
          @RequestParam("price") BigDecimal price,
          @RequestParam("quantity") BigDecimal quantity,
          @RequestParam("categoryId") Long categoryId,
          @PathVariable Long id) throws IOException {

    Product product = new Product();
    product.setName(name);
    product.setQuantity(quantity);
    product.setPrice(price);
    product.setPicture(Util.compressZLib(picture.getBytes()));

    return productService.update(product, categoryId, id);
  }
}
