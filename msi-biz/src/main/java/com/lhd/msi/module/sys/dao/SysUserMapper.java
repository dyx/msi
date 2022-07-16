/*
 *    Copyright (c) 2018-2025, lengleng All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: lengleng (wangiegie@gmail.com)
 */

package com.lhd.msi.module.sys.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhd.msi.module.sys.model.data.SysUser;
import com.lhd.msi.module.sys.model.dto.CustomPageDto;
import com.lhd.msi.module.sys.model.vo.SysUserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 *  @author: lhd
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    /**
     * 使用 sql段 分页查询
     * @param page
     * @param queryWrapper
     * @return
     */
    Page<SysUserVo> selectUserPageWithCustomSqlSegment(IPage<SysUser> page, @Param("ew") Wrapper<SysUser> queryWrapper);

    /**
     * 使用 dto 分页查询
     * @param page
     * @param dto
     * @return
     */
    Page<SysUserVo> selectUserPageWithDto(IPage<SysUser> page, @Param("dto") CustomPageDto dto);
}
