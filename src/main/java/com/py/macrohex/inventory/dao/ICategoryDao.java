package com.py.macrohex.inventory.dao;

import com.py.macrohex.inventory.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface ICategoryDao extends CrudRepository<Category, Long> {
}