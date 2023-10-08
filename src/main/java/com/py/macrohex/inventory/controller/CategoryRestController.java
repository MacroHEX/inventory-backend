// ::: Martin Medina
//
package com.py.macrohex.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.py.macrohex.inventory.model.Category;
import com.py.macrohex.inventory.services.ICategoryService;
import com.py.macrohex.inventory.response.CategoryResponseRest;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class CategoryRestController {

  @Autowired
  private ICategoryService service;

  @GetMapping("/category")
  public ResponseEntity<CategoryResponseRest> search() {
    return service.search();
  }

  @GetMapping("/category/{id}")
  public ResponseEntity<CategoryResponseRest> searchById(@PathVariable Long id) {
    return service.searchById(id);
  }

  @PostMapping("/category")
  public ResponseEntity<CategoryResponseRest> save(@RequestBody Category category) {
    return service.save(category);
  }

  @PutMapping("/category/{id}")
  public ResponseEntity<CategoryResponseRest> update(@PathVariable Long id, @RequestBody Category category) {
    return service.update(category, id);
  }

  @DeleteMapping("/category/{id}")
  public ResponseEntity<CategoryResponseRest> delete(@PathVariable Long id) {
    return service.delete(id);
  }
}
