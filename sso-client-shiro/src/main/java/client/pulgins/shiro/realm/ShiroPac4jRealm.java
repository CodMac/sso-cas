package client.pulgins.shiro.realm;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;

import io.buji.pac4j.realm.Pac4jRealm;

public class ShiroPac4jRealm extends Pac4jRealm {
 
	@Override
    protected AuthorizationInfo doGetAuthorizationInfo(final PrincipalCollection principals) {
        Object user = super.getAvailablePrincipal(principals);
        System.out.println(user);
        
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        List<String> permissions = new ArrayList<String>();
        
        simpleAuthorizationInfo.addStringPermissions(permissions);
 
        return simpleAuthorizationInfo;
    }
    
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
    		throws AuthenticationException {
    	AuthenticationInfo doGetAuthenticationInfo = super.doGetAuthenticationInfo(authenticationToken);
    	return doGetAuthenticationInfo;
    }
 
}