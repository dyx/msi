package com.lhd.msi.constant;

/**
 * @author lhd
 */
public enum CustomPageMode {

    /**
     * 自定义分页模式
     */
    SQL_SEGMENT(1, "sql段"),
    DTO(2, "dto");

    CustomPageMode(Integer value, String label) {
        this.value = value;
        this.label = label;
    }

    private final Integer value;
    private final String label;

    public Integer getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }
}
