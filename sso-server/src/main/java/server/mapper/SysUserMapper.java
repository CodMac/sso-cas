package server.mapper;

import org.apache.ibatis.annotations.Mapper;

import server.entity.SysUser;

/**
 * 
 * @author mc
 */
@Mapper
public interface SysUserMapper {
	SysUser selectOne(SysUser one);
}
