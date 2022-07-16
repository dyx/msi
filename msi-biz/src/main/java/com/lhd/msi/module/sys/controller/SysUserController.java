package com.lhd.msi.module.sys.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.github.xiaoymin.knife4j.annotations.ApiSort;
import com.lhd.msi.base.R;
import com.lhd.msi.module.sys.model.dto.*;
import com.lhd.msi.module.sys.model.vo.SysUserDetailVo;
import com.lhd.msi.module.sys.model.vo.SysUserVo;
import com.lhd.msi.module.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author lhd
 */
@Slf4j
@ApiSort(1)
@RestController
@RequestMapping("/sys/user")
@Api(tags = "用户管理")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "用户分页列表", response = SysUserVo.class)
    @GetMapping("/page")
    public R<Page<SysUserVo>> getUserPage(SysUserPageQuery query) {

        return R.ok(sysUserService.getUserPage(query));
    }

    @ApiOperationSupport(order = 1)
    @ApiOperation(value = "自定义分页", notes = "mybatis-plus自定义分页示例", response = SysUserVo.class)
    @ApiImplicitParams(@ApiImplicitParam(name = "mode", value="模式：1【sql段模式】 2【dto模式】"))
    @GetMapping("/page/mode/{mode}")
    public R<Page<SysUserVo>> getCustomPage(@PathVariable("mode") Integer mode, CustomPageDto dto) {

        return R.ok(sysUserService.getCustomPage(mode, dto));
    }

    @ApiOperationSupport(order = 2)
    @ApiOperation(value = "用户列表", response = SysUserVo.class)
    @GetMapping
    public R<List<SysUserVo>> getUserList(SysUserQuery query) {

        return R.ok(sysUserService.getUserList(query));
    }

    @ApiOperationSupport(order = 3)
    @ApiOperation(value = "用户详情", response = SysUserVo.class)
    @GetMapping("/{id}")
    public R<SysUserDetailVo> getUserDetail(@PathVariable("id") Long id) {

        return R.ok(sysUserService.getUserDetail(id));
    }

    @ApiOperationSupport(order = 4)
    @ApiOperation(value = "新增用户")
    @PostMapping
    public R<?> saveUser(@Validated @RequestBody SaveSysUserDto dto) {

        sysUserService.saveUser(dto);

        return R.ok();
    }

    @ApiOperationSupport(order = 5)
    @ApiOperation(value = "修改用户")
    @PutMapping("/{id}")
    public R<?> updateUser(@PathVariable("id") Long id, @Validated @RequestBody UpdateSysUserDto dto) {

        sysUserService.updateUser(id, dto);

        return R.ok();
    }

    @ApiOperationSupport(order = 6)
    @ApiOperation(value = "删除用户")
    @DeleteMapping("/{id}")
    public R<?> removeUser(@PathVariable("id") Long id) {

        sysUserService.removeUser(id);

        return R.ok();
    }
}
