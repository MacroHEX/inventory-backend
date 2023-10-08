// ::: Martin Medina
//
package com.py.macrohex.inventory.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.py.macrohex.inventory.model.Category;
import com.py.macrohex.inventory.dao.ICategoryDao;
import com.py.macrohex.inventory.response.CategoryResponseRest;

@Service
public class CategoryServiceImpl implements ICategoryService {

  @Autowired
  private ICategoryDao categoryDao;

  @Override
  @Transactional(readOnly = true)
  public ResponseEntity<CategoryResponseRest> search() {

    CategoryResponseRest response = new CategoryResponseRest();

    try {

      List<Category> category = (List<Category>) categoryDao.findAll();

      response.getCategoryResponse().setCategory(category);
      response.setMetada("Respuesta Ok", "00", "Respuesta Exitosa");

    } catch (Exception ex) {

      response.setMetada("Respuesta No Ok", "-1", "Error en la consulta");
      ex.getStackTrace();
      return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

    }
    return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
  }

  @Override
  @Transactional(readOnly = true)
  public ResponseEntity<CategoryResponseRest> searchById(Long id) {

    CategoryResponseRest response = new CategoryResponseRest();
    List<Category> list = new ArrayList<>();

    try {

      Optional<Category> category = categoryDao.findById(id);

      if (category.isPresent()) {
        list.add(category.get());
        response.getCategoryResponse().setCategory(list);
        response.setMetada("Respuesta Ok", "00", "Categoria Encontrada");
      } else {
        response.setMetada("Respuesta No Ok", "-1", "Categoría no Encontrada");
        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
      }

    } catch (Exception ex) {
      response.setMetada("Respuesta No Ok", "-1", "Error en la consulta por ID");
      ex.getStackTrace();
      return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

    }
    return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
  }

  @Override
  @Transactional()
  public ResponseEntity<CategoryResponseRest> save(Category category) {

    CategoryResponseRest response = new CategoryResponseRest();
    List<Category> list = new ArrayList<>();

    try {

      Category newCategory = categoryDao.save(category);
      list.add(newCategory);
      response.getCategoryResponse().setCategory(list);
      response.setMetada("Respuesta Ok", "00", "Categoria Añadida");

    } catch (Exception ex) {
      response.setMetada("Respuesta No Ok", "-1", "Error al insertar categoría");
      ex.getStackTrace();
      return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

    }
    return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
  }

  @Override
  @Transactional
  public ResponseEntity<CategoryResponseRest> update(Category category, Long id) {
    CategoryResponseRest response = new CategoryResponseRest();
    List<Category> list = new ArrayList<>();

    try {

      Optional<Category> categorySearch = categoryDao.findById(id);

      if (categorySearch.isPresent()) {
        categorySearch.get().setName(category.getName());
        categorySearch.get().setDescription(category.getDescription());

        Category categoryToUpdate = categoryDao.save(categorySearch.get());
        list.add(categoryToUpdate);

        response.getCategoryResponse().setCategory(list);
        response.setMetada("Respuesta Ok", "00", "Categoria Actualizada");

      } else {
        response.setMetada("Respuesta No Ok", "-1", "Categoría no Encontrada");
        return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.NOT_FOUND);
      }

    } catch (Exception ex) {
      response.setMetada("Respuesta No Ok", "-1", "Error al actualizar categoría");
      ex.getStackTrace();
      return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

    }
    return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
  }

  @Override
  @Transactional
  public ResponseEntity<CategoryResponseRest> delete(Long id) {

    CategoryResponseRest response = new CategoryResponseRest();

    try {

      categoryDao.deleteById(id);
      response.setMetada("Respuesta Ok", "00", "Respuesta Exitosa");

    } catch (Exception ex) {

      response.setMetada("Respuesta No Ok", "-1", "Error en la eliminación");
      ex.getStackTrace();
      return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

    }
    return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
  }

}
