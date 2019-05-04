package com.pandy.VO;

import lombok.Data;

/**
 * @Author: Pandy
 * @Date: 2019/5/4 9:51
 * @Version 1.0
 * 视图层 http请求返回给最外层的对象
 */
@Data
public class ResultVO<T> {

    //错误码 0 1
    private Integer code;
    //响应信息 成功 失败
    private String message;
    //返回的具体对象
    private T data;
}
