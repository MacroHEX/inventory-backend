package com.py.macrohex.inventory.services;

import com.py.macrohex.inventory.dao.ICategoryDao;
import com.py.macrohex.inventory.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.py.macrohex.inventory.response.CategoryResponseRest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

      response.setMetada("Respuesta No Ok", "-1", "Erro en la consulta");
      ex.getStackTrace();
      return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);

    }
    return new ResponseEntity<CategoryResponseRest>(response, HttpStatus.OK);
  }
}
