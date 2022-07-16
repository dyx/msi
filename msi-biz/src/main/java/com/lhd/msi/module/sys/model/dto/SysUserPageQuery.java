package com.lhd.msi.module.sys.model.dto;

import com.lhd.msi.base.BasePageQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author lhd
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class SysUserPageQuery extends BasePageQuery {

    @ApiModelProperty(value = "用户名")
    private String username;

    @ApiModelProperty(value = "昵称")
    private String nickname;
}

