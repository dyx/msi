package com.lhd.msi.module.sys.service.impl;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lhd.msi.constant.CustomPageMode;
import com.lhd.msi.exception.BusinessException;
import com.lhd.msi.module.sys.dao.SysUserMapper;
import com.lhd.msi.module.sys.model.converter.SysUserConverter;
import com.lhd.msi.module.sys.model.data.SysUser;
import com.lhd.msi.module.sys.model.dto.*;
import com.lhd.msi.module.sys.model.vo.SysUserDetailVo;
import com.lhd.msi.module.sys.model.vo.SysUserVo;
import com.lhd.msi.module.sys.service.SysUserService;
import com.lhd.msi.utils.CommonConvertUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author lhd
 */
@Service
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

	@Autowired
	private SysUserMapper sysUserMapper;

	@Override
	public Page<SysUserVo> getUserPage(SysUserPageQuery query) {

		Page<SysUser> doPage = page(
				CommonConvertUtil.pageQuery2Page(query, SysUser.class),
				Wrappers.<SysUser>lambdaQuery()
				.eq(StrUtil.isNotBlank(query.getUsername()), SysUser::getUsername, query.getUsername())
				.like(StrUtil.isNotBlank(query.getNickname()), SysUser::getNickname, query.getNickname()));
		return SysUserConverter.INSTANCE.doPage2voPage(doPage);
	}

	@Override
	public Page<SysUserVo> getCustomPage(Integer mode, CustomPageDto dto) {

		if (mode == null) {
			return null;
		}

		Page<SysUserVo> voPage = null;
		if (CustomPageMode.SQL_SEGMENT.getValue().equals(mode)) {
			voPage = sysUserMapper.selectUserPageWithCustomSqlSegment(new Page<>(1, 1),
					Wrappers.<SysUser>lambdaQuery()
							.eq(StrUtil.isNotBlank(dto.getUsername()), SysUser::getUsername, dto.getUsername())
							.like(StrUtil.isNotBlank(dto.getNickname()), SysUser::getNickname, dto.getNickname())
							.and(StrUtil.isNotBlank(dto.getStartCreateTime()) && StrUtil.isNotBlank(dto.getEndCreateTime()),
									e -> e.ge(SysUser::getCreateTime, dto.getStartCreateTime()).le(SysUser::getCreateTime, dto.getEndCreateTime())));
		}
		else if (CustomPageMode.DTO.getValue().equals(mode)) {
			voPage = sysUserMapper.selectUserPageWithDto(new Page<>(1, 1), dto);
		}

		return voPage;
	}

	@Override
	public List<SysUserVo> getUserList(SysUserQuery query) {
		List<SysUser> doList = list(Wrappers.<SysUser>lambdaQuery()
				.eq(StrUtil.isNotBlank(query.getUsername()), SysUser::getUsername, query.getUsername())
				.like(StrUtil.isNotBlank(query.getNickname()), SysUser::getNickname, query.getNickname()));
		return SysUserConverter.INSTANCE.doList2VoList(doList);
	}

	@Override
	public SysUserDetailVo getUserDetail(Long id) {
		return SysUserConverter.INSTANCE.do2DetailVo(getById(id));
	}

	@Override
	public void saveUser(SaveSysUserDto dto) {

		checkUsername(null, dto.getUsername());
		save(SysUserConverter.INSTANCE.saveDto2do(dto));
	}

	@Override
	public void updateUser(Long id, UpdateSysUserDto dto) {

		checkUsername(id, dto.getUsername());
		SysUser dataObj = SysUserConverter.INSTANCE.updateDto2do(dto);
		dataObj.setId(id);
		updateById(dataObj);
	}

	@Override
	public void removeUser(Long id) {
		removeById(id);
	}

	private void checkUsername(Long id, String username) {
		if (count(Wrappers.<SysUser>lambdaQuery()
				.ne(id != null, SysUser::getId, id)
				.eq(SysUser::getUsername, username)) > 0) {
			throw new BusinessException("用户名重复，请更换。");
		}
	}
}
