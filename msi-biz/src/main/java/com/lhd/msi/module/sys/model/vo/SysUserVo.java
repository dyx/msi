package com.lhd.msi.module.sys.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author lhd
 */
@Data
public class SysUserVo {

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickname;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;
}

