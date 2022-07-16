package com.lhd.msi.exception;

import com.lhd.msi.base.R;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.sql.DataTruncation;
import java.sql.SQLException;

/**
 * 全局异常处理
 * @author lhd
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final int SQL_ERROR_CODE_NO_DEFAULT_VALUE = 1364;

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R<String> handle(HttpRequestMethodNotSupportedException e) {
        return R.failed(String.format("请求方式错误，支持的请求方式为%s",
                e.getSupportedHttpMethods() != null ? e.getSupportedHttpMethods().toString() : ""));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public R<String> handle(MissingServletRequestParameterException e) {
        return R.failed(String.format("请求参数缺失，缺失参数【%s】", e.getParameterName()));
    }

    /**
     * 参数校验
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public R<String> handle(ConstraintViolationException e) {
        StringBuilder msgBuilder = new StringBuilder();
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            msgBuilder.append(violation.getMessage());
            msgBuilder.append(";");
        }
        log.debug(msgBuilder.toString());
        return R.failed(msgBuilder.toString());
    }

    /**
     * 参数校验
     * @param e
     * @return
     */
    @ExceptionHandler(BindException.class)
    public R<String> handle(BindException e) {
        StringBuilder msgBuilder = new StringBuilder();
        for (FieldError error : e.getBindingResult().getFieldErrors()) {
            msgBuilder.append(error.getDefaultMessage());
            msgBuilder.append(";");
        }
        log.debug(msgBuilder.toString());
        return R.failed(msgBuilder.toString());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public R<String> handle(HttpMessageNotReadableException e) {
        if (e.getCause() instanceof InvalidFormatException) {
            InvalidFormatException exception = (InvalidFormatException) e.getCause();
            return R.failed(String.format("请求参数转换异常，值【%s】转换为类型【%s】失败", exception.getValue(), exception.getTargetType().getName()));
        }
        log.error("请求参数转换异常，原因：{}", e.getMessage());
        return R.failed("请求参数转换异常");
    }

    /**
     * 违反数据完整性
     * @param e
     * @return
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public R<String> handle(DataIntegrityViolationException e) {
        log.error("数据库异常，原因：{}", e.getMessage());
        if (e.getCause() instanceof DataTruncation) {
            return R.failed("数据库异常，要保存的字段中有超长值");
        }
        if (e.getCause() instanceof SQLException) {
            SQLException exception = (SQLException) e.getCause();
            if (exception.getErrorCode() == SQL_ERROR_CODE_NO_DEFAULT_VALUE) {
                return R.failed("数据库异常，要保存的必填字段中有null值");
            }
        }
        return R.failed("数据库异常，请联系系统管理员");
    }

    /**
     * 自定义业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    public R<String> handle(BusinessException e) {
        log.error(e.getMsg(), e.getDetail());
        return R.failed(e.getMsg());
    }

    /**
     * 位置异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public R<String> handle(Exception e) {
        log.error(e.getMessage(), e);
        return R.failed("服务端异常，请联系系统管理员。");
    }
}
