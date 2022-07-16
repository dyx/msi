package com.lhd.msi.module.sys.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lhd.msi.module.sys.model.data.SysUser;
import com.lhd.msi.module.sys.model.dto.*;
import com.lhd.msi.module.sys.model.vo.SysUserDetailVo;
import com.lhd.msi.module.sys.model.vo.SysUserVo;

import java.util.List;

/**
 * @author lhd
 */
public interface SysUserService extends IService<SysUser> {

    /**
     * 用户分页列表
     * @param query
     * @return
     */
    Page<SysUserVo> getUserPage(SysUserPageQuery query);

    /**
     * 自定义分页
     * @param mode
     * @param dto
     * @return
     */
    Page<SysUserVo> getCustomPage(Integer mode, CustomPageDto dto);

    /**
     * 用户列表
     * @param query
     * @return
     */
    List<SysUserVo> getUserList(SysUserQuery query);

    /**
     * 用户详情
     * @param id
     * @return
     */
    SysUserDetailVo getUserDetail(Long id);

    /**
     * 新增用户
     * @param dto
     */
    void saveUser(SaveSysUserDto dto);

    /**
     * 修改用户
     * @param id
     * @param dto
     */
    void updateUser(Long id, UpdateSysUserDto dto);

    /**
     * 删除用户
     * @param id
     */
    void removeUser(Long id);
}
