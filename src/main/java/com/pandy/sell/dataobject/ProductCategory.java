package com.pandy.sell.dataobject;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @Author: Pandy
 * @Date: 2019/5/3 17:15
 * @Version 1.0
 * 商品种类
 */
@Entity
@Data
public class ProductCategory {
    //类目id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    //类目名字
    private String categoryName;
    //类目编号
    private Integer categoryType;

    //private Date createTime;

    //private Date updateTime;

/*    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }*/
}
