package com.py.macrohex.inventory.services;

import com.py.macrohex.inventory.dao.ICategoryDao;
import com.py.macrohex.inventory.dao.IProductDAO;
import com.py.macrohex.inventory.model.Category;
import com.py.macrohex.inventory.model.Product;
import com.py.macrohex.inventory.response.ProductResponseRest;
import com.py.macrohex.inventory.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements IProductService {
  @Autowired
  private ICategoryDao categoryDao;
  @Autowired
  private IProductDAO productDAO;

  @Override
  @Transactional
  public ResponseEntity<ProductResponseRest> save(Product product, Long categoryId) {

    ProductResponseRest response = new ProductResponseRest();
    List<Product> list = new ArrayList<>();

    try {
      // ::: Search Category to set in the product object
      Optional<Category> category = categoryDao.findById(categoryId);

      if (category.isPresent()) {
        product.setCategory(category.get());
      } else {
        response.setMetada("No Ok", "-1", "Categoria no encontrada");
        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
      }

      Product productSaved = productDAO.save(product);

      list.add(productSaved);
      response.getProduct().setProducts(list);
      response.setMetada("Ok", "00", "Producto Guardado");

    } catch (Exception ex) {
      response.setMetada("No Ok", "-1", "Error al guardar producto");
      return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
  }

  @Override
  @Transactional(readOnly = true)
  public ResponseEntity<ProductResponseRest> searchById(Long id) {

    ProductResponseRest response = new ProductResponseRest();
    List<Product> list = new ArrayList<>();

    try {
      // ::: Search Category to set in the product object
      Optional<Product> product = productDAO.findById(id);

      if (product.isPresent()) {

        byte[] imageBase = Util.decompressZLib(product.get().getPicture());
        product.get().setPicture(imageBase);
        list.add(product.get());
        response.getProduct().setProducts(list);
        response.setMetada("Ok", "00", "Producto encontrado");
      } else {
        response.setMetada("No Ok", "-1", "Producto no encontrado");
        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
      }

    } catch (Exception ex) {
      response.setMetada("No Ok", "-1", "Error al buscar el producto");
      return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
  }

  @Override
  @Transactional(readOnly = true)
  public ResponseEntity<ProductResponseRest> searchByName(String name) {
    ProductResponseRest response = new ProductResponseRest();
    List<Product> list = new ArrayList<>();
    List<Product> listAux = new ArrayList<>();

    try {
      // :::
      listAux = productDAO.findByName(name);

      if (!listAux.isEmpty()) {

        listAux.forEach((p) -> {
          byte[] imageBase = Util.decompressZLib(p.getPicture());
          p.setPicture(imageBase);
          list.add(p);
        });

        response.getProduct().setProducts(list);
        response.setMetada("Ok", "00", "Productos encontrado");
      } else {
        response.setMetada("No Ok", "-1", "Producto no encontrado");
        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
      }

    } catch (Exception ex) {
      response.setMetada("No Ok", "-1", "Error al buscar el producto");
      return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
  }

  @Override
  @Transactional
  public ResponseEntity<ProductResponseRest> delete(Long id) {
    ProductResponseRest response = new ProductResponseRest();

    try {
      // :::
      productDAO.deleteById(id);
      response.setMetada("Ok", "00", "Producto eliminado con exito");
    } catch (Exception ex) {
      response.setMetada("No Ok", "-1", "Error al eliminar el producto");
      return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
  }

  @Override
  @Transactional(readOnly = true)
  public ResponseEntity<ProductResponseRest> search() {
    ProductResponseRest response = new ProductResponseRest();
    List<Product> list = new ArrayList<>();
    List<Product> listAux = new ArrayList<>();

    try {
      // :::
      listAux = (List<Product>) productDAO.findAll();

      if (!listAux.isEmpty()) {

        listAux.forEach((p) -> {
          byte[] imageBase = Util.decompressZLib(p.getPicture());
          p.setPicture(imageBase);
          list.add(p);
        });

        response.getProduct().setProducts(list);
        response.setMetada("Ok", "00", "Productos encontrado");
      } else {
        response.setMetada("No Ok", "-1", "Producto no encontrado");
        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
      }

    } catch (Exception ex) {
      response.setMetada("No Ok", "-1", "Error al buscar el producto");
      return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
  }

  @Override
  @Transactional
  public ResponseEntity<ProductResponseRest> update(Product product, Long categoryId, Long id) {
    ProductResponseRest response = new ProductResponseRest();
    List<Product> list = new ArrayList<>();

    try {
      // ::: Search Category to set in the product object
      Optional<Category> category = categoryDao.findById(categoryId);

      if (category.isPresent()) {
        product.setCategory(category.get());
      } else {
        response.setMetada("No Ok", "-1", "Categoria no encontrada");
        return new ResponseEntity<ProductResponseRest>(response, HttpStatus.NOT_FOUND);
      }

      Optional<Product> productSearch = productDAO.findById(id);

      if (productSearch.isPresent()) {

        productSearch.get().setQuantity(product.getQuantity());
        productSearch.get().setCategory(product.getCategory());
        productSearch.get().setName(product.getName());
        productSearch.get().setPicture(product.getPicture());
        productSearch.get().setPrice(product.getPrice());

        Product productToSave = productDAO.save(productSearch.get());

        list.add(productToSave);
        response.getProduct().setProducts(list);
        response.setMetada("Ok", "00", "Producto Actualizado");
      }

    } catch (Exception ex) {
      response.setMetada("No Ok", "-1", "Error al guardar producto");
      return new ResponseEntity<ProductResponseRest>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    return new ResponseEntity<ProductResponseRest>(response, HttpStatus.OK);
  }
}
