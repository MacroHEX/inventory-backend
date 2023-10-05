package com.py.macrohex.inventory.services;

import com.py.macrohex.inventory.model.Category;
import org.springframework.http.ResponseEntity;
import com.py.macrohex.inventory.response.CategoryResponseRest;

public interface ICategoryService {
  ResponseEntity<CategoryResponseRest> search();

  ResponseEntity<CategoryResponseRest> searchById(Long id);

  ResponseEntity<CategoryResponseRest> save(Category category);

  ResponseEntity<CategoryResponseRest> update(Category category, Long id);

  ResponseEntity<CategoryResponseRest> delete(Long id);
}
