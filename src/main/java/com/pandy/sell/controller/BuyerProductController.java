package com.pandy.sell.controller;

import com.pandy.VO.ProductInfoVO;
import com.pandy.VO.ProductVO;
import com.pandy.VO.ResultVO;
import com.pandy.sell.dataobject.ProductCategory;
import com.pandy.sell.dataobject.ProductInfo;
import com.pandy.sell.service.CategoryService;
import com.pandy.sell.service.ProductService;
import com.pandy.utils.ResultVOUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Pandy
 * @Date: 2019/5/4 9:47
 * @Version 1.0
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;


    @GetMapping("/list")
    public ResultVO list(){

        //1.查新所有的上架的商品
        List<ProductInfo> productInfoList = productService.findUpAll();
        //2.查询类目 一次性查询

            //传统方法
        /*List<Integer> categoryTypeList = new ArrayList<>();
        for (ProductInfo productInfo : productInfoList){
            categoryTypeList.add(productInfo.getCategoryType());
        }*/
            //精简方法(lambda)
        List<Integer> categoryTypeList = productInfoList.stream().map(e -> e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> productCategoryList = categoryService.findByCategoryTypeIn(categoryTypeList);
        //3.数据拼装
        List<ProductVO> productVOS = new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList){
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());
            //productVO.setProductInfoVOList();

            List<ProductInfoVO> productInfoVOList = new ArrayList<>();
            for (ProductInfo productInfo : productInfoList){
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo,productInfoVO);
                    productInfoVOList.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOList(productInfoVOList);
            productVOS.add(productVO);
        }
        //ResultVO resultVO = new ResultVO();
        //resultVO.setData(productVOS);

        /*ProductVO productVO = new ProductVO();
        ProductInfoVO productInfoVO = new ProductInfoVO();

        resultVO.setData(0);
        resultVO.setMessage("成功");
        resultVO.setData(Arrays.asList(productVO));

        productVO.setProductInfoVOList(Arrays.asList(productInfoVO));*/

        return ResultVOUtil.success(productVOS);
    }
}
