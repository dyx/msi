package com.lhd.msi.module.sys.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @author lhd
 */
@Data
public class SaveSysUserDto {

    @NotEmpty(message = "用户名不能为空")
    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickname;
}

