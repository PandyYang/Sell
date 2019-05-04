package com.pandy.sell.dao;

import com.pandy.sell.dataobject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author: Pandy
 * @Date: 2019/5/3 17:19
 * @Version 1.0
 */
public interface ProductCategoryDao extends JpaRepository<ProductCategory,Integer> {

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeLisy);
}
