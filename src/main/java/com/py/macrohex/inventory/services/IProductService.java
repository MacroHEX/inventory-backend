package com.py.macrohex.inventory.services;

import com.py.macrohex.inventory.model.Product;
import com.py.macrohex.inventory.response.ProductResponseRest;
import org.springframework.http.ResponseEntity;

public interface IProductService {

  ResponseEntity<ProductResponseRest> save(Product product, Long categoryId);

  ResponseEntity<ProductResponseRest> searchById(Long id);

  ResponseEntity<ProductResponseRest> searchByName(String name);

  ResponseEntity<ProductResponseRest> delete(Long id);

  ResponseEntity<ProductResponseRest> search();

  ResponseEntity<ProductResponseRest> update(Product product, Long categoryId, Long id);

}
