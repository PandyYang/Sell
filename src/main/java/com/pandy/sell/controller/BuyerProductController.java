package com.pandy.sell.controller;

import com.pandy.VO.ProductInfoVO;
import com.pandy.VO.ProductVO;
import com.pandy.VO.ResultVO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @Author: Pandy
 * @Date: 2019/5/4 9:47
 * @Version 1.0
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @GetMapping("/list")
    public ResultVO list(){
        ResultVO resultVO = new ResultVO();
        ProductVO productVO = new ProductVO();
        ProductInfoVO productInfoVO = new ProductInfoVO();

        resultVO.setData(0);
        resultVO.setMessage("成功");
        resultVO.setData(Arrays.asList(productVO));

        productVO.setProductInfoVOList(Arrays.asList(productInfoVO));

        return resultVO;
    }
}
