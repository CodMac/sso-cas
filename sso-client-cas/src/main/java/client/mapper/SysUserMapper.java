package client.mapper;

import org.apache.ibatis.annotations.Mapper;

import client.entity.SysUser;

/**
 * 
 * @author mc
 */
@Mapper
public interface SysUserMapper {
	SysUser selectOne(SysUser one);
}
