package com.lhd.msi.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lhd
 */
@Data
public class BasePageQuery {

    public static String ORDER_RULE_ASC = "ascending";
    public static String ORDER_RULE_DESC = "descending";
    public static int MIN_SIZE = 10;
    public static int MAX_SIZE = 100;
    public static int DEFAULT_CURRENT = 1;

    @ApiModelProperty(value = "当前页码")
    private Integer current;

    @ApiModelProperty(value = "每页大小")
    private Integer size;

    @ApiModelProperty(value = "排序",  example = "name.ascending,age.descending")
    private String orders;

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
