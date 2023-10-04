package com.py.macrohex.inventory.services;

import org.springframework.http.ResponseEntity;
import com.py.macrohex.inventory.response.CategoryResponseRest;

public interface ICategoryService {
  public ResponseEntity<CategoryResponseRest> search();
}
