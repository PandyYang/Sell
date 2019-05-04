package com.pandy.utils;

import com.pandy.VO.ResultVO;

/**
 * @Author: Pandy
 * @Date: 2019/5/4 11:47
 * @Version 1.0
 */
public class ResultVOUtil {
    public static ResultVO success(Object object){
        ResultVO resultVO = new ResultVO();

        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMessage("成功");

        return resultVO;
    }

    public static ResultVO success(){
        return success(null);
    }

    public static ResultVO error(Integer code,String messge){
        ResultVO resultVO = new ResultVO();
        resultVO.setCode(code);
        resultVO.setMessage(messge);
        return resultVO;
    }
}
