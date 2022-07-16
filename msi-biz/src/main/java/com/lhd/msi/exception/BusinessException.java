package com.lhd.msi.exception;

import com.lhd.msi.constant.CommonConstants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lhd
 */
@Setter
@Getter
@ToString
public class BusinessException extends RuntimeException {

    /**
     * 错误编码
     */
    private Integer code;

    /**
     * 错误提示
     */
    private String msg;

    /**
     * 具体信息
     */
    private Throwable detail;

    public BusinessException(String msg) {
        this(CommonConstants.FAIL, msg, null);
    }

    public BusinessException(String msg, Throwable detail) {
        this(CommonConstants.FAIL, msg, detail);
    }

    public BusinessException(Integer code, String msg, Throwable detail) {
        this.code = code;
        this.msg = msg;
        this.detail = detail;
    }
}
