package com.lhd.msi.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lhd
 */
@Data
public class BasePageQuery {

    private static int MIN_SIZE = 10;
    private static int MAX_SIZE = 100;
    private static int DEFAULT_CURRENT = 1;

    @ApiModelProperty(value = "当前页码")
    private Integer current;

    @ApiModelProperty(value = "每页大小")
    private Integer size;

    public Integer getCurrent() {
        if (current == null || current <= 0) {
            current = DEFAULT_CURRENT;
        }
        return current;
    }

    public Integer getSize() {
        if (size == null || size <= 0) {
            size = MIN_SIZE;
        }
        if (size > MAX_SIZE) {
            size = MAX_SIZE;
        }
        return size;
    }
}
