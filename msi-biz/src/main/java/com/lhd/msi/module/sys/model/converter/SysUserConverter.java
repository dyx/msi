package com.lhd.msi.module.sys.model.converter;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lhd.msi.module.sys.model.data.SysUser;
import com.lhd.msi.module.sys.model.dto.SaveSysUserDto;
import com.lhd.msi.module.sys.model.dto.UpdateSysUserDto;
import com.lhd.msi.module.sys.model.vo.SysUserDetailVo;
import com.lhd.msi.module.sys.model.vo.SysUserVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author lhd
 */
@Mapper
public interface SysUserConverter {

    SysUserConverter INSTANCE = Mappers.getMapper(SysUserConverter.class);

    /**
     * doPage2voPage
     * @param doPage
     * @return
     */
    Page<SysUserVo> doPage2voPage(Page<SysUser> doPage);

    /**
     * doList2VoList
     * @param doList
     * @return
     */
    List<SysUserVo> doList2VoList(List<SysUser> doList);

    /**
     * do2DetailVo
     * @param dataObj
     * @return
     */
    SysUserDetailVo do2DetailVo(SysUser dataObj);

    /**
     * dto2do
     * @param dto
     * @return
     */
    SysUser saveDto2do(SaveSysUserDto dto);

    /**
     * updateDto2do
     * @param dto
     * @return
     */
    SysUser updateDto2do(UpdateSysUserDto dto);
}

