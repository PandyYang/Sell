package com.pandy.sell.service;

import com.pandy.sell.dataobject.ProductCategory;

import java.util.List;

/**
 * @Author: Pandy
 * @Date: 2019/5/3 18:28
 * @Version 1.0
 */
public interface CategoryService {

    ProductCategory findOne(Integer categoryId);
    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);
    ProductCategory save(ProductCategory productCategory);


}
