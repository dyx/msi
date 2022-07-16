package com.lhd.msi.module.sys.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lhd
 */
@Data
public class CustomPageDto {

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "开始创建时间按")
    private String startCreateTime;

    @ApiModelProperty(value = "结束创建时间")
    private String endCreateTime;
}

