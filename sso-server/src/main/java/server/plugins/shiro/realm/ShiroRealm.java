package server.plugins.shiro.realm;

import java.util.HashMap;
import java.util.Map;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import server.entity.SysUser;
import server.service.SysUserService;

/**
 * @description: 在Shiro中，最终是通过Realm来获取应用程序中的用户、角色及权限信息的
 * 在Realm中会直接从我们的数据源中获取Shiro需要的验证信息。可以说，Realm是专用于安全框架的DAO.
 */
public class ShiroRealm extends AuthorizingRealm {

	@Autowired
	private SysUserService sysUserService;

    /**
     * 验证用户身份
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @SuppressWarnings("unlikely-arg-type")
	@Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //获取用户名密码 第一种方式
//        String username = (String) authenticationToken.getPrincipal();
//        String password = new String((char[]) authenticationToken.getCredentials());

        //获取用户名 密码 第二种方式
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();

        SysUser one = new SysUser();
        one.setUsername(username);
		SysUser user = sysUserService.findUser(one);

        //可以在这里直接对用户名校验,或者调用 CredentialsMatcher 校验
        if (user == null) {
            throw new UnknownAccountException("用户名或密码错误！");
        }
        if ("1".equals(user.getExpired())) {
            throw new LockedAccountException("账号已被过期,请联系管理员！");
        }
        if ("1".equals(user.getDisable())) {
        	throw new LockedAccountException("账号已被锁定,请联系管理员！");
        }

        Map<String, Object> credentials = new HashMap<String, Object>();
        credentials.put("pwd", user.getPassword());
        credentials.put("salt", user.getUsername());
        
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(username, credentials, getName());
        return info;
    }

    /**
     * 授权用户权限 但是这个方法并不用,我们会在 ShiroAuthenticationHandler的 checkSubjectRolesAndPermissions 中单独去验证
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo =  new SimpleAuthorizationInfo();
        return authorizationInfo;
    }


}
