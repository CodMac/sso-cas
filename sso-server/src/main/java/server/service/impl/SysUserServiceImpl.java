package server.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import server.entity.SysUser;
import server.mapper.SysUserMapper;
import server.service.SysUserService;

@Service
public class SysUserServiceImpl implements SysUserService {

	@Autowired
	private SysUserMapper userMapper;
	
	@Override
	public SysUser findUser(SysUser one) {
		return userMapper.selectOne(one);
	}

}
