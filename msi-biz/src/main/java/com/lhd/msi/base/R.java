package com.lhd.msi.base;

import com.lhd.msi.constant.CommonConstants;
import lombok.Data;

import java.io.Serializable;

/**
 * 接口统一返回值
 * @param <T>
 * @author lhd
 */
@Data
public class R<T> implements Serializable {

    private Integer code;

    private String msg;

    private T data;

    public static <T> R<T> ok() {
        return restResult(null, CommonConstants.SUCCESS, "success");
    }

    public static <T> R<T> ok(T data) {
        return restResult(data, CommonConstants.SUCCESS, "success");
    }

    public static <T> R<T> ok(T data, String msg) {
        return restResult(data, CommonConstants.SUCCESS, msg);
    }

    public static <T> R<T> failed() {
        return restResult(null, CommonConstants.FAIL, null);
    }

    public static <T> R<T> failed(String msg) {
        return restResult(null, CommonConstants.FAIL, msg);
    }

    public static <T> R<T> failed(T data) {
        return restResult(data, CommonConstants.FAIL, null);
    }

    public static <T> R<T> failed(T data, String msg) {
        return restResult(data, CommonConstants.FAIL, msg);
    }

    private static <T> R<T> restResult(T data, int code, String msg) {
        R<T> result = new R<>();
        result.setCode(code);
        result.setData(data);
        result.setMsg(msg);
        return result;
    }

    public static <T> R<T> code(int code, String msg) {
        return restResult(null, code, msg);
    }

}
