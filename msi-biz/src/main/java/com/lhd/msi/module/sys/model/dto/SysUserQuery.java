package com.lhd.msi.module.sys.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author lhd
 */
@Data
public class SysUserQuery {

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickname;
}

